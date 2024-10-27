package data.dto;

import java.util.UUID;

public class CalcResultDTO {

    private String id;
    double fullCreditAmount;
    double clearCreditAmount;
    int term;
    double rate;
    double insuranceAmount;
    String[] payments;

    public CalcResultDTO() {
    }

    public CalcResultDTO(String id,
                         double fullCreditAmount,
                         double clearCreditAmount,
                         int term,
                         double rate,
                         double insuranceAmount,
                         String[] payments) {
        this.id = id;
        this.fullCreditAmount = fullCreditAmount;
        this.clearCreditAmount = clearCreditAmount;
        this.term = term;
        this.rate = rate;
        this.insuranceAmount = insuranceAmount;
        this.payments = payments;
    }

    public String getId() {
        return id;
    }

    public void setId(UUID id) {
        try {
            this.id = id.toString();
        } catch (NullPointerException e) {
            this.id = null;
        }
    }

    public double getFullCreditAmount() {
        return fullCreditAmount;
    }

    public void setFullCreditAmount(double fullCreditAmount) {
        this.fullCreditAmount = fullCreditAmount;
    }

    public double getClearCreditAmount() {
        return clearCreditAmount;
    }

    public void setClearCreditAmount(double clearCreditAmount) {
        this.clearCreditAmount = clearCreditAmount;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public String[] getPayments() {
        return payments;
    }

    public void setPayments(String[] payments) {
        this.payments = payments;
    }
}
