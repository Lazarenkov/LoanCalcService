package date;

import java.util.Map;

import static java.util.Map.entry;

public class Calendar {

    private static final Map<Integer, Integer> monthKSet = Map.ofEntries(
            entry(1, 1),
            entry(2, 4),
            entry(3, 4),
            entry(4, 0),
            entry(5, 2),
            entry(6, 5),
            entry(7, 0),
            entry(8, 3),
            entry(9, 6),
            entry(10, 1),
            entry(11, 4),
            entry(12, 6));

    private final Date date;

    private int day;
    private int month;
    private int year;

    public Calendar(Date date) {
        this.date = date;
        this.day = (int) date.getElement(DateElement.DAY, int.class);
        this.month = (int) date.getElement(DateElement.MONTH, int.class);
        this.year = (int) date.getElement(DateElement.YEAR, int.class);
    }

    public boolean isHoliday() {
        int yearK = (int) (6 + (year - 2000) + ((double) (year - 2000) / 4)) % 7;
        int monthK = monthKSet.get(month);

        int weekDay = ((day + monthK + yearK) % 7);

        return weekDay <= 1;
    }

    public boolean isExist() {
        if (day <= 0 | year > 2100 | year < 2000) {
            return false;
        }

        return switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                yield day <= 31;
            case 4:
            case 6:
            case 9:
            case 11:
                yield day <= 30;
            case 2:
                if (year % 4 == 0) {
                    yield day <= 29;
                }
                yield day <= 28;
            default:
                yield false;
        };
    }

    public Date increment(DateElement dateElement, int value) {
        switch (dateElement) {
            case DAY -> day = day + value;
            case MONTH -> month = month + value;
            case YEAR -> year = year + value;
        }
        while (month > 12) {
            month = month - 12;
            year++;
        }

        if (!isExist()) {
            day = 1;
            month++;
        }

        if (!isExist()) {
            day = 1;
            month = 1;
            year++;
        }

        return new Date().setElement(DateElement.DAY, day)
                .setElement(DateElement.MONTH, month)
                .setElement(DateElement.YEAR, year);
    }
}
