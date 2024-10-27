package date;

import java.util.ArrayList;
import java.util.List;

public class DateHelper {

    public static List<String> getPayDates(Date startDate, int term) {
        List<String> payDates = new ArrayList<>();
        payDates.add(startDate.toString());

        for (int i = 1; i <= term; i++) {
            Date nextDate = new Calendar(startDate).increment(DateElement.MONTH, i);
            while (new Calendar(nextDate).isHoliday()) {
                nextDate = new Calendar(nextDate).increment(DateElement.DAY, 1);
            }
            payDates.add(nextDate.toString());
        }
        return payDates;
    }
}


