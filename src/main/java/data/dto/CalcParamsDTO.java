package data.dto;

public class CalcParamsDTO {

    String paymentType;
    boolean insurance;
    boolean insuranceIncluding;
    double insuranceK;
    double amount;
    int term;
    double rate;
    String startDate;

    public CalcParamsDTO() {
    }

    public CalcParamsDTO(String paymentType,
                         boolean insurance,
                         boolean insuranceIncluding,
                         double insuranceK,
                         double amount,
                         int term,
                         double rate,
                         String startDate) {
        this.paymentType = paymentType;
        this.insurance = insurance;
        this.insuranceIncluding = insuranceIncluding;
        this.insuranceK = insuranceK;
        this.amount = amount;
        this.term = term;
        this.rate = rate;
        this.startDate = startDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public boolean isInsuranceIncluding() {
        return insuranceIncluding;
    }

    public double getInsuranceK() {
        return insuranceK;
    }

    public double getAmount() {
        return amount;
    }

    public int getTerm() {
        return term;
    }

    public double getRate() {
        return rate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public void setInsuranceIncluding(boolean insuranceIncluding) {
        this.insuranceIncluding = insuranceIncluding;
    }

    public void setInsuranceK(double insuranceK) {
        this.insuranceK = insuranceK;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
