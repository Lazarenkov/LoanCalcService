package main;

import io.http.HttpService;
import io.console.ConsoleService;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;

import static data.enums.IOSourceType.*;

public class Main {
    public static void main(String[] args) throws IOException {
        LogManager.getLogger(Main.class.getName()).info("Запуск программы");
        System.out.println("LoanCalcService");

        //args = new String[]{"MODE=HTTP", "PORT=8000", "DB=true"};
        ArgsChecker.checkParams(args);

        if (ArgsChecker.isError()) return;
        if (ArgsChecker.getMode() == HTTP) {
            HttpService.setService();
        } else if (ArgsChecker.getMode() == CONSOLE) {
            ConsoleService.setService();
        }
    }
}