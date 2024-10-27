package db.service;

import data.CalcParams;
import data.CalcResult;
import data.Target;
import db.dao.DAO;
import exeptions.DBConnectionException;
import exeptions.DBRetrievingException;
import org.apache.logging.log4j.LogManager;
import org.hibernate.exception.JDBCConnectionException;

import java.util.List;
import java.util.UUID;

public class DbService {

    public static UUID save(Target obj) {
        LogManager.getLogger(DbService.class.getName()).info("Сохранение Target");

        try {
            DAO<Target> dao = new DAO<>();
            UUID uuid = dao.save(obj);
            LogManager.getLogger(DbService.class.getName()).info("Успешно сохранен Target с id: " + uuid.toString());
            return uuid;
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Target не сохранен");
            throw new DBConnectionException();
        }
    }

    public static Target retrieveTargetByID(UUID id) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка Target с id " + id);

        try {
            DAO<Target> dao = new DAO<>();
            Target target = dao.retrieveById(id, Target.class);
            LogManager.getLogger(DbService.class.getName()).info("Найден объект с id " + target.getId());
            return target;
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объекта с id" + id);
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объекты не получены");
            throw new DBConnectionException();
        }
    }

    public static void deleteTargetByID(UUID id) {
        LogManager.getLogger(DbService.class.getName()).info("Удаление Target с id " + id);

        try {
            Target target = retrieveTargetByID(id);
            DAO<Target> dao = new DAO<>();
            dao.delete(target);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объекта с id" + id);
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не удален");
            throw new DBConnectionException();
        }
    }

    public static List<Target> retrieveAllTargets() {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка всех Target из БД");

        try {
            DAO<Target> dao = new DAO<>();
            return dao.retrieveAll(Target.class);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объекты не получены");
            throw new DBConnectionException();
        }
    }

    public static List<Target> retrieveTargetWhere(String column, int value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка Target где " + column + "=" + value);

        try {
            DAO<Target> dao = new DAO<>();
            return dao.retrieveWhere(Target.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBRetrievingException();
        }
    }

    public static List<CalcParams> retrieveCalcParamsWhere(String column, int value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка CalcParams где " + column + "=" + value);

        try {
            DAO<CalcParams> dao = new DAO<>();
            return dao.retrieveWhere(CalcParams.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBConnectionException();
        }
    }

    public static List<CalcParams> retrieveCalcParamsWhere(String column, double value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка CalcParams где " + column + "=" + value);

        try {
            DAO<CalcParams> dao = new DAO<>();
            return dao.retrieveWhere(CalcParams.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBConnectionException();
        }
    }

    public static List<CalcParams> retrieveCalcParamsWhere(String column, String value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка CalcParams где " + column + "=" + value);

        try {
            DAO<CalcParams> dao = new DAO<>();
            return dao.retrieveWhere(CalcParams.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBConnectionException();
        }
    }

    public static List<CalcParams> retrieveCalcParamsWhere(String column, boolean value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка CalcParams где " + column + "=" + value);

        try {
            DAO<CalcParams> dao = new DAO<>();
            return dao.retrieveWhere(CalcParams.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBConnectionException();
        }
    }

    public static List<CalcResult> retrieveCalcResultsWhere(String column, int value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка CalcResult где " + column + "=" + value);

        try {
            DAO<CalcResult> dao = new DAO<>();
            return dao.retrieveWhere(CalcResult.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBConnectionException();
        }
    }

    public static List<CalcResult> retrieveCalcResultsWhere(String column, double value) {
        LogManager.getLogger(DbService.class.getName()).info("Загрузка CalcResult где " + column + "=" + value);

        try {
            DAO<CalcResult> dao = new DAO<>();
            return dao.retrieveWhere(CalcResult.class, column, value);
        } catch (NullPointerException e) {
            LogManager.getLogger(DbService.class.getName()).info("Не найдено объектов");
            throw new DBRetrievingException();
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Объект не получен");
            throw new DBConnectionException();
        }
    }

    public static void truncateAll() {
        String query = """
                TRUNCATE target CASCADE;
                TRUNCATE calc_params CASCADE;
                TRUNCATE calc_result CASCADE;""";
        LogManager.getLogger(DbService.class.getName()).info("Выполнение SQL: " + query);

        try {
            DAO<Target> dao = new DAO<>();
            dao.executeNativeQuery(query);
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Скрипт не выполнен.");
            throw new DBConnectionException();
        }
    }

    public static void dropAll() {
        String query = """
                DROP TABLE "public"."target" CASCADE;
                DROP TABLE "public"."calc_params" CASCADE;
                DROP TABLE "public"."calc_result" CASCADE;
                """;
        LogManager.getLogger(DbService.class.getName()).info("Выполнение SQL " + query);

        try {
            DAO<Target> dao = new DAO<>();
            dao.executeNativeQuery(query);
        } catch (JDBCConnectionException e) {
            LogManager.getLogger(DbService.class.getName()).error("Не удалось подключиться к БД. Скрипт не выполнен.");
            throw new DBConnectionException();
        }
    }
}
