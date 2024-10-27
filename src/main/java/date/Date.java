package date;

import exeptions.InvalidDateException;
import org.apache.logging.log4j.LogManager;

public class Date {
    private int[] date = new int[3];

    public Date() {
    }

    Date(int[] date) {
        this.date = date;
    }

    public Date(String date) {
        setDate(date);
    }

    public void setDate(String value) {
        try {
            date[0] = Integer.parseInt(value.split("\\.")[0]);
            date[1] = Integer.parseInt(value.split("\\.")[1]);
            date[2] = Integer.parseInt(value.split("\\.")[2]);
        } catch (NumberFormatException e) {
            LogManager.getLogger(Date.class.getName()).info("Некорректная дата: " + value);
            date = new int[3];
            throw new InvalidDateException("Некорректная дата: " + value);
        }

        if (!new Calendar(this).isExist()) {
            date = new int[3];
            throw new InvalidDateException("Некорректная дата: " + value);
        }
    }

    public Date setElement(DateElement element, int value) {
        switch (element) {
            case DAY:
                date[0] = value;
                break;
            case MONTH:
                date[1] = value;
                break;
            case YEAR:
                date[2] = value;
                break;
        }
        return this;
    }

    public Object getElement(DateElement element, Class<?> cls) {
        return switch (cls.getName()) {
            case "int":
                yield date[element.ordinal()];
            case "java.lang.String":
                yield String.valueOf(date[element.ordinal()]);
            default:
                throw new IllegalStateException("Can't return class: " + cls.getName());
        };
    }

    @Override
    public String toString() {
        String day = String.valueOf(date[0]);
        String month = String.valueOf(date[1]);
        if (date[0] < 10) {
            day = "0" + date[0];
        }
        if (date[1] < 10) {
            month = "0" + date[1];
        }
        return day + "." + month + "." + date[2];
    }
}

