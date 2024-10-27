package io.http;

import conn.CalcHttpServer;
import data.Target;
import db.service.DbService;
import calc.CalcManager;
import exeptions.NoDBModeException;
import main.ArgsChecker;
import org.apache.logging.log4j.LogManager;

import java.io.*;
import java.util.UUID;

public class HttpService {

    private static boolean isSetService;

    public static void setService() throws IOException {
        if (!isSetService) {
            CalcHttpServer calcHttpServer = new CalcHttpServer();
            calcHttpServer.setServer();
            isSetService = true;
            LogManager.getLogger(HttpService.class.getName()).info("Запущен HTTP-сервер на порту " + ArgsChecker.getPort());
            System.out.println("Server started");
        }
    }

    public static byte[] getNewTarget(InputStream body) {
        LogManager.getLogger(HttpService.class.getName()).info("Получен запрос на расчет нового Target");

        CalcManager calcResultManager = new CalcManager(HttpHelper.getCalcParamsFromBody(body));
        calcResultManager.setResult();
        Target target = calcResultManager.getTarget();
        return HttpHelper.serializeResponseBody(target);
    }

    public static byte[] getOldTarget(String uuid) {
        LogManager.getLogger(HttpService.class.getName()).info("Получен запрос на извлечение Target с UUID " + uuid);

        if (ArgsChecker.isDbEnable()) {
            Target target = DbService.retrieveTargetByID(UUID.fromString(uuid));
            return HttpHelper.serializeResponseBody(target);
        } else {
            LogManager.getLogger(HttpService.class.getName()).info("База данных не используется");
            throw new NoDBModeException();
        }
    }

    public static void deleteOldTarget(String uuid) {
        LogManager.getLogger(HttpService.class.getName()).info("Получен запрос на удаление Target с UUID " + uuid);

        if (ArgsChecker.isDbEnable()) {
            DbService.deleteTargetByID(UUID.fromString(uuid));
        } else {
            LogManager.getLogger(HttpService.class.getName()).info("База данных не используется");
            throw new NoDBModeException();
        }
    }
}
