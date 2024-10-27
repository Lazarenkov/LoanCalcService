package data;

public class Payment {

    private int number;
    private String date;
    private double paymentValue;
    private double percentValue;
    private double debtValue;

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setPaymentValue(double paymentValue) {
        this.paymentValue = paymentValue;
    }

    public double getPaymentValue() {
        return paymentValue;
    }

    public void setPercentValue(double percentValue) {
        this.percentValue = percentValue;
    }

    public double getPercentValue() {
        return percentValue;
    }

    public void setDebtValue(double debtValue) {
        this.debtValue = debtValue;
    }

    public double getDebtValue() {
        return debtValue;
    }

    @Override
    public String toString(){
        return (number + ", " + date + ", " + paymentValue + ", " + percentValue + ", " + debtValue);
    }
}
