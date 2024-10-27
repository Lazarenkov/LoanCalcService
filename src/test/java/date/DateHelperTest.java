package date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DateHelperTest {

    @Test
    void shouldReturnCorrectPayDate() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date startDate = new Date(dateArray);
        List<String> payDates = DateHelper.getPayDates(startDate, 12);

        String expected = "01.10.2024";
        String actual = payDates.get(2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectPayDateIfSaturday() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date startDate = new Date(dateArray);
        List<String> payDates = DateHelper.getPayDates(startDate, 12);

        String expected = "03.02.2025";
        String actual = payDates.get(6);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectPayDateIfSunday() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date startDate = new Date(dateArray);
        List<String> payDates = DateHelper.getPayDates(startDate, 12);

        String expected = "02.09.2024";
        String actual = payDates.get(1);

        Assertions.assertEquals(expected, actual);
    }
}
