package date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalendarTest {

    @Test
    void shouldReturnTrueIfHoliday() {
        int[] dateArray = new int[]{3, 8, 2024};
        Date date = new Date(dateArray);

        Assertions.assertTrue(new Calendar(date).isHoliday());
    }

    @Test
    void shouldReturnFalseIfNotHoliday() {
        int[] dateArray = new int[]{2, 8, 2024};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isHoliday());
    }

    @Test
    void shouldReturnTrueIfNotHoliday29Feb() {
        int[] dateArray = new int[]{29, 2, 2020};
        Date date = new Date(dateArray);

        Assertions.assertTrue(new Calendar(date).isHoliday());
    }

    @Test
    void shouldReturnFalseIfHoliday29Feb() {
        int[] dateArray = new int[]{29, 2, 2024};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isHoliday());
    }

    @Test
    void shouldExistValidDate() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date date = new Date(dateArray);

        Assertions.assertTrue(new Calendar(date).isExist());
    }

    @Test
    void shouldExist29FebInLeap() {
        int[] dateArray = new int[]{29, 2, 2024};
        Date date = new Date(dateArray);

        Assertions.assertTrue(new Calendar(date).isExist());
    }

    @Test
    void shouldNotExist29FebInNotLeap() {
        int[] dateArray = new int[]{29, 2, 2023};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isExist());
    }

    @Test
    void shouldNotExistInvalidDay() {
        int[] dateArray = new int[]{32, 8, 2024};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isExist());
    }

    @Test
    void shouldNotExistInvalidMonth() {
        int[] dateArray = new int[]{1, 16, 2024};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isExist());
    }

    @Test
    void shouldNotExistInvalidYearBelowInterval() {
        int[] dateArray = new int[]{1, 8, 1999};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isExist());
    }

    @Test
    void shouldNotExistInvalidYearAboveInterval() {
        int[] dateArray = new int[]{1, 8, 2101};
        Date date = new Date(dateArray);

        Assertions.assertFalse(new Calendar(date).isExist());
    }

    @Test
    void shouldCorrectIncrementDay() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date date = new Date(dateArray);

        String expected = "02.08.2024";

        Date actual = new Calendar(date).increment(DateElement.DAY, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementMonth() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date date = new Date(dateArray);

        String expected = "01.09.2024";

        Date actual = new Calendar(date).increment(DateElement.MONTH, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementYear() {
        int[] dateArray = new int[]{1, 8, 2024};
        Date date = new Date(dateArray);

        String expected = "01.08.2025";

        Date actual = new Calendar(date).increment(DateElement.YEAR, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementMonthInDecember() {
        int[] dateArray = new int[]{1, 12, 2024};
        Date date = new Date(dateArray);

        String expected = "01.01.2025";

        Date actual = new Calendar(date).increment(DateElement.MONTH, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementDayOn31December() {
        int[] dateArray = new int[]{31, 12, 2024};
        Date date = new Date(dateArray);

        String expected = "01.01.2025";

        Date actual = new Calendar(date).increment(DateElement.DAY, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementDayOn29Feb() {
        int[] dateArray = new int[]{29, 2, 2024};
        Date date = new Date(dateArray);

        String expected = "01.03.2024";

        Date actual = new Calendar(date).increment(DateElement.DAY, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementDayOn28FebLeap() {
        int[] dateArray = new int[]{28, 2, 2024};
        Date date = new Date(dateArray);

        String expected = "29.02.2024";

        Date actual = new Calendar(date).increment(DateElement.DAY, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementDayOn28FebNotLeap() {
        int[] dateArray = new int[]{28, 2, 2023};
        Date date = new Date(dateArray);

        String expected = "01.03.2023";

        Date actual = new Calendar(date).increment(DateElement.DAY, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementMonthOn28FebLeap() {
        int[] dateArray = new int[]{28, 1, 2024};
        Date date = new Date(dateArray);

        String expected = "28.02.2024";

        Date actual = new Calendar(date).increment(DateElement.MONTH, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementMonthOn29FebLeap() {
        int[] dateArray = new int[]{29, 1, 2024};
        Date date = new Date(dateArray);

        String expected = "29.02.2024";

        Date actual = new Calendar(date).increment(DateElement.MONTH, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementYearOn29FebLeap() {
        int[] dateArray = new int[]{29, 2, 2024};
        Date date = new Date(dateArray);

        String expected = "01.03.2025";

        Date actual = new Calendar(date).increment(DateElement.YEAR, 1);

        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldCorrectIncrementLastDayOfMonth() {
        int[] dateArray = new int[]{31, 5, 2024};
        Date date = new Date(dateArray);

        String expected = "01.07.2024";

        Date actual = new Calendar(date).increment(DateElement.MONTH, 1);

        Assertions.assertEquals(expected, actual.toString());
    }
}
