package data.dto;

public class DiffCalcResultDTO extends CalcResultDTO {

    double maxPaymentAmount;
    double minPaymentAmount;

    public DiffCalcResultDTO() {
    }

    public DiffCalcResultDTO(String id,
                             double fullCreditAmount,
                             double clearCreditAmount,
                             int term,
                             double rate,
                             double insuranceAmount,
                             String[] payments,
                             double maxPaymentAmount,
                             double minPaymentAmount) {
        super(
                id,
                fullCreditAmount,
                clearCreditAmount,
                term,
                rate,
                insuranceAmount,
                payments);
        this.maxPaymentAmount = maxPaymentAmount;
        this.minPaymentAmount = minPaymentAmount;
    }

    public double getMaxPaymentAmount() {
        return maxPaymentAmount;
    }

    public void setMaxPaymentAmount(double maxPaymentAmount) {
        this.maxPaymentAmount = maxPaymentAmount;
    }

    public double getMinPaymentAmount() {
        return minPaymentAmount;
    }

    public void setMinPaymentAmount(double minPaymentAmount) {
        this.minPaymentAmount = minPaymentAmount;
    }
}
