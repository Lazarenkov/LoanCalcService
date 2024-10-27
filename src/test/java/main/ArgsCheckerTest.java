package main;

import data.enums.IOSourceType;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ArgsCheckerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void resetArgsChecker() {
        ArgsChecker.reset();
    }

    @BeforeEach
    public void setUpStreamsAndReset() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1000 DB=TRUE")
    void shouldCorrectArgsWhenAllCorrectAndHttpMode() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1000", "DB=TRUE"});
        Assertions.assertAll(
                () -> assertFalse(ArgsChecker.isError()),
                () -> assertEquals(IOSourceType.HTTP.toString(), ArgsChecker.getMode().toString()),
                () -> assertEquals(1000, ArgsChecker.getPort()),
                () -> Assertions.assertTrue(ArgsChecker.isDbEnable()),

                () -> Assertions.assertEquals(
                        "",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=CONSOLE PORT=1000 DB=TRUE")
    void shouldCorrectArgsWhenAllCorrectAndConsoleMode() {
        ArgsChecker.checkParams(new String[]{"MODE=CONSOLE", "PORT=1000", "DB=TRUE"});
        Assertions.assertAll(
                () -> assertFalse(ArgsChecker.isError()),
                () -> assertEquals(IOSourceType.CONSOLE.toString(), ArgsChecker.getMode().toString()),
                () -> assertEquals(0, ArgsChecker.getPort()),
                () -> Assertions.assertTrue(ArgsChecker.isDbEnable()),

                () -> Assertions.assertEquals(
                        "Задан режим работы \"CONSOLE\", параметр \"PORT\" игнорируется",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=CONSOLE DB=FALSE")
    void shouldCorrectArgsWhenNoPortAndConsoleMode() {
        ArgsChecker.checkParams(new String[]{"MODE=CONSOLE", "DB=FALSE"});
        Assertions.assertAll(
                () -> assertFalse(ArgsChecker.isError()),
                () -> assertEquals(IOSourceType.CONSOLE.toString(), ArgsChecker.getMode().toString()),
                () -> assertEquals(0, ArgsChecker.getPort()),
                () -> Assertions.assertFalse(ArgsChecker.isDbEnable()),

                () -> Assertions.assertEquals(
                        "Задан режим работы \"CONSOLE\", параметр \"PORT\" игнорируется",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTPS PORT=6553: DB=TRUeE")
    void shouldPromptWhenAllInvalidParams() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTPS", "PORT=6553:", "DB=TRUeE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE1=HTTP PORTy=1234 DB!=TRUE")
    void shouldPromptWhenAllInvalidFlags() {
        ArgsChecker.checkParams(new String[]{"MODE1=HTTP", "PORTy=1234", "DB!=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT1=1234 DB=TRUE")
    void shouldPromptWhenPortInvalidFlag() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT1=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE= PORT=1234 DB=TRUE")
    void shouldPromptWhenModeNoParam() {
        ArgsChecker.checkParams(new String[]{"MODE=", "PORT=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT= DB=TRUE")
    void shouldPromptWhenPortNoParam() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234 DB=")
    void shouldPromptWhenDbNoParam() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234", "DB="});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE= PORT= DB=")
    void shouldPromptWhenAllNoParams() {
        ArgsChecker.checkParams(new String[]{"MODE=", "PORT=", "DB="});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("=HTTP PORT=1234 DB=TRUE")
    void shouldPromptWhenModeNoFlag() {
        ArgsChecker.checkParams(new String[]{"=HTTP", "PORT=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP =1234 DB=TRUE")
    void shouldPromptWhenPortNoFlag() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234 =TRUE")
    void shouldPromptWhenDbNoFlag() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234", "=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("=HTTP =1234 =TRUE")
    void shouldPromptWhenAllNoFlags() {
        ArgsChecker.checkParams(new String[]{"=HTTP", "=1234", "=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("PORT=1234 DB=TRUE")
    void shouldPromptWhenNoMode() {
        ArgsChecker.checkParams(new String[]{"PORT1=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP DB=TRUE")
    void shouldPromptWhenNoPort() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234")
    void shouldPromptWhenNoDb() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE-HTTP PORT=1234 DB=TRUE")
    void shouldPromptWhenModeInvalidSplit() {
        ArgsChecker.checkParams(new String[]{"MODE-HTTP", "PORT=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT+1234 DB=TRUE")
    void shouldPromptWhenPortInvalidSplit() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT+1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234 DB/TRUE")
    void shouldPromptWhenDbInvalidSplit() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234", "DB/TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODEHTTP PORT*1234 DB!TRUE")
    void shouldPromptWhenAllInvalidSplit() {
        ArgsChecker.checkParams(new String[]{"MODEHTTP", "PORT*1234", "DB!TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"MODE\"Не корректный параметр \"DB\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=-1234 DB=TRUE")
    void shouldPromptWhenPortInvalidNegative() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=-1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=65537 DB=TRUE")
    void shouldPromptWhenPortInvalidExceedWindowsPorts() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=65537", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234.56 DB=TRUE")
    void shouldPromptWhenPortInvalidFloat() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234.56", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректный параметр \"PORT\"",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=CONSOLE PORT=1234.56 DB=TRUE")
    void shouldCorrectArgsWhenPortInvalidFloatAndConsoleMode() {
        ArgsChecker.checkParams(new String[]{"MODE=CONSOLE", "PORT=1234.56", "DB=TRUE"});
        Assertions.assertAll(
                () -> assertFalse(ArgsChecker.isError()),
                () -> assertEquals(IOSourceType.CONSOLE.toString(), ArgsChecker.getMode().toString()),
                () -> assertEquals(0, ArgsChecker.getPort()),
                () -> Assertions.assertTrue(ArgsChecker.isDbEnable()),

                () -> Assertions.assertEquals(
                        "Задан режим работы \"CONSOLE\", параметр \"PORT\" игнорируется",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234 DB=TRUE FOO=Bar")
    void shouldPromptWhenMoreThan3Params() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234", "DB=TRUE", "FOO=Bar"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректные параметры запуска",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP MODE=CONSOLE PORT=1234 DB=TRUE")
    void shouldPromptWhenModeDoubled() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "MODE=CONSOLE", "PORT=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректные параметры запуска",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234 PORT=1234 DB=TRUE")
    void shouldPromptWhenPortDoubled() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234", "PORT=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректные параметры запуска",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=CONSOLE PORT=1234 PORT=1234 DB=TRUE")
    void shouldPromptWhenPortDoubledAndConsoleMode() {
        ArgsChecker.checkParams(new String[]{"MODE=CONSOLE", "PORT=1234", "PORT=1234", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректные параметры запуска",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("MODE=HTTP PORT=1234 DB=TRUE DB=TRUE")
    void shouldPromptWhenDbDoubled() {
        ArgsChecker.checkParams(new String[]{"MODE=HTTP", "PORT=1234", "DB=TRUE", "DB=TRUE"});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректные параметры запуска",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }

    @Test
    @DisplayName("=empty=")
    void shouldPromptWhenAllNothing() {
        ArgsChecker.checkParams(new String[]{""});
        Assertions.assertAll(
                () -> Assertions.assertTrue(ArgsChecker.isError()),

                () -> Assertions.assertEquals(
                        "Не корректные параметры запуска",
                        outContent.toString().replaceAll("\\r\\n", ""))
        );
    }
}
