package main;

import data.enums.IOSourceType;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static main.ArgsChecker.ParamType.*;
import static data.enums.IOSourceType.*;

public class ArgsChecker {

    private static boolean error;
    private static IOSourceType ioSourceType;
    private static int port;
    private static boolean dbEnable;
    private static Map<String, String> params = new HashMap<>();
    private static String[] args = null;

    enum ParamType {
        ARGSCOUNT,
        MODE,
        PORT,
        DB;
    }

    public static void checkParams(String[] argsInput) {
        if (argsInput.length > 3 | argsInput.length < 2) {
            setError(ARGSCOUNT);
        } else if (args == null) {
            args = argsInput;
            split();
            checkMode();
            checkPort();
            checkDB();
        }
    }

    private static void split() {
        for (String arg : args) {
            try {
                params.put(arg.split("=")[0].toLowerCase(), arg.split("=")[1].toLowerCase());
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
    }

    private static void checkMode() {
        try {
            if (params.get("mode").equals("console")) {
                ioSourceType = CONSOLE;
            } else if (params.get("mode").equals("http")) {
                ioSourceType = HTTP;
            } else {
                setError(MODE);
            }
        } catch (NullPointerException e) {
            error = true;
            System.out.println("Не корректный параметр \"MODE\"");
        }
    }

    private static void checkPort() {
        if (ioSourceType == HTTP) {
            try {
                port = Integer.parseInt(params.get("port"));
                if (port < 0 | port > 65535) {
                    setError(PORT);
                }
            } catch (NullPointerException | NumberFormatException e) {
                setError(PORT);
            }
        } else if (ioSourceType == CONSOLE) {
            System.out.println("Задан режим работы \"CONSOLE\", параметр \"PORT\" игнорируется");
        }
    }

    private static void checkDB() {
        try {
            if (params.get("db").equals("true")) {
                dbEnable = true;
            } else if (params.get("db").equals("false")) {
                dbEnable = false;
            } else {
                setError(DB);
            }
        } catch (NullPointerException e) {
            setError(DB);
        }
    }

    private static void setError(ParamType type) {
        error = true;
        switch (type) {
            case ARGSCOUNT -> System.out.println("Не корректные параметры запуска");
            case MODE -> System.out.println("Не корректный параметр \"MODE\"");
            case PORT -> System.out.println("Не корректный параметр \"PORT\"");
            case DB -> System.out.println("Не корректный параметр \"DB\"");
        }
        LogManager.getLogger(ArgsChecker.class.getName()).info("Не корректные параметры запуска: " + Arrays.toString(getArgs()) + " Приложенние не запущено.");
    }

    static void reset() {
        error = false;
        args = null;
        params = new HashMap<>();
        ioSourceType = null;
        port = 0;
        dbEnable = false;
    }

    public static boolean isError() {
        return error;
    }

    public static IOSourceType getMode() {
        return ioSourceType;
    }

    public static int getPort() {
        return port;
    }

    public static boolean isDbEnable() {
        return dbEnable;
    }

    public static String[] getArgs() {
        return args;
    }
}
