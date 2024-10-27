package data.dto;

public class AnnualCalcResultDTO extends CalcResultDTO {

    private double paymentAmount;

    public AnnualCalcResultDTO() {
    }

    public AnnualCalcResultDTO(String id,
                               double fullCreditAmount,
                               double clearCreditAmount,
                               int term,
                               double rate,
                               double insuranceAmount,
                               double paymentAmount,
                               String[] payments) {
        super(
                id,
                fullCreditAmount,
                clearCreditAmount,
                term,
                rate,
                insuranceAmount,
                payments);
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }


    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
