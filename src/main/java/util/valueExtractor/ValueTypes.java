package util.valueExtractor;

public enum ValueTypes {

    ID("id"),
    PAYMENTTYPE("paymentType"),
    INSURANCEK("insuranceK" ),
    INSURANCE("insurance"),
    INSURANCEINCLUDING("insuranceIncluding"),
    AMOUNT("amount"),
    TERM("term"),
    RATE("rate"),
    PAYMENTAMOUNT("paymentAmount"),
    MAXPAYMENT("maxPaymentAmount"),
    MINPAYMENT("minPaymentAmount"),
    INSURANCEAMOUNT("insuranceAmount"),
    FULLCREDITAMOUNT("fullCreditAmount"),
    CLEARCREDITAMOUNT("clearCreditAmount"),
    STARTDATE("startDate");

    private final String title;

    ValueTypes(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
