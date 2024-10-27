package util.valueExtractor;

import data.enums.PaymentType;
import date.Date;

import java.util.UUID;

public class Extractions {

    private Extractions() {
    }

    public static UUID ID() {
        return (UUID) ValueExtractor.getValue(ValueTypes.ID);
    }

    public static PaymentType PAYMENT_TYPE() {
        return (PaymentType) ValueExtractor.getValue(ValueTypes.PAYMENTTYPE);
    }

    public static boolean INSURANCE() {
        return (boolean) ValueExtractor.getValue(ValueTypes.INSURANCE);
    }

    public static boolean INSURANCE_INCLUDING() {
        return (boolean) ValueExtractor.getValue(ValueTypes.INSURANCEINCLUDING);
    }

    public static double INSURANCE_K() {
        return (double) ValueExtractor.getValue(ValueTypes.INSURANCEK);
    }

    public static double AMOUNT() {
        return (double) ValueExtractor.getValue(ValueTypes.AMOUNT);
    }

    public static double RATE() {
        return (double) ValueExtractor.getValue(ValueTypes.RATE);
    }

    public static int TERM() {
        return (int) ValueExtractor.getValue(ValueTypes.TERM);
    }

    public static double PAYMENT_AMOUNT() {
        return (double) ValueExtractor.getValue(ValueTypes.PAYMENTAMOUNT);
    }

    public static double MAX_PAYMENT() {
        return (double) ValueExtractor.getValue(ValueTypes.MAXPAYMENT);
    }

    public static double MIN_PAYMENT() {
        return (double) ValueExtractor.getValue(ValueTypes.MINPAYMENT);
    }

    public static double INSURANCE_AMOUNT() {
        return (double) ValueExtractor.getValue(ValueTypes.INSURANCEAMOUNT);
    }

    public static double FULL_CREDIT_AMOUNT() {
        return (double) ValueExtractor.getValue(ValueTypes.FULLCREDITAMOUNT);
    }

    public static double CLEAR_CREDIT_AMOUNT() {
        return (double) ValueExtractor.getValue(ValueTypes.CLEARCREDITAMOUNT);
    }

    public static Date START_DATE() {
        return (Date) ValueExtractor.getValue(ValueTypes.STARTDATE);
    }
}

