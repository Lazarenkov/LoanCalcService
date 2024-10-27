package data;

import jakarta.persistence.*;
import repo.PaymentRepository;
import util.valueExtractor.ExtractValue;

import java.util.ArrayList;

@Entity
@Table(name = "calc_result")
public class CalcResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ExtractValue
    private double insuranceAmount;
    @ExtractValue
    private double fullCreditAmount;
    @ExtractValue
    private double clearCreditAmount;
    @ExtractValue
    private double paymentAmount;
    @ExtractValue
    private double maxPaymentAmount;
    @ExtractValue
    private double minPaymentAmount;
    @Transient
    private PaymentRepository repo;
    private ArrayList<String> paymentList;

    public double getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(double insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
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

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
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

    public void setRepo(PaymentRepository repo) {
        this.repo = repo;
        paymentList = repo.getPaymentsAsArrayString();
    }

    public PaymentRepository getRepo() {
        return repo;
    }

    public ArrayList<String> getPaymentList() {
        return paymentList;
    }
}
