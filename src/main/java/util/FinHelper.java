package util;

public class FinHelper {

    public static double getAnnualPaymentK(int term, double rate) {
        return rate * (Math.pow((1 + rate), term)) / (Math.pow((1 + rate), (term)) - 1);
    }

    public static double getPercentValue(double value, double rate) {
        double percentValue = value * rate/100/12;
        return Math.ceil(percentValue * 100) / 100;
    }

    public static double getRateMonthly(double rateYearly){
        return rateYearly / 12 / 100;
    }

}
