package util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class FinHelperTest {

    @Test
    public void shouldCalculatePercentPositiveTest() {
        double value = 100;
        double rate = 12;
        double expected = 1;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateZeroPercentWhenZeroValue() {
        double value = 0;
        double rate = 12;
        double expected = 0;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateZeroPercentWhenZeroRate() {
        double value = 12355;
        double rate = 0;
        double expected = 0;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateCorrectMinorValue() {
        double value = 456789.78;
        double rate = 6;
        double expected = 2283.95;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateCorrectMinorRate() {
        double value = 123987.13;
        double rate = 6.56;
        double expected = 677.80;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateZeroWhenAllZeros() {
        double value = 0;
        double rate = 0;
        double expected = 0;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateCorrectWhenValueLessOne() {
        double value = 0.13;
        double rate = 14;
        double expected = 0.01;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateCorrectWhenRateLessOne() {
        double value = 23456;
        double rate = 0.03;
        double expected = 0.59;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldCalculateCorrectWhenAllLessOne() {
        double value = 0.13;
        double rate = 0.56;
        double expected = 0.01;
        double actual = FinHelper.getPercentValue(value, rate);
        Assertions.assertEquals(expected, actual);
    }

}
