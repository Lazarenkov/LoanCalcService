package data;

import date.Date;
import jakarta.persistence.*;
import util.valueExtractor.ExtractValue;
import data.enums.PaymentType;

@Entity
@Table(name = "calc_params")
public class CalcParams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ExtractValue
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @ExtractValue
    private boolean insurance;
    @ExtractValue
    private boolean insuranceIncluding;
    @ExtractValue
    private double insuranceK;
    @ExtractValue
    private double amount;
    @ExtractValue
    private int term;
    @ExtractValue
    private double rate;
    @ExtractValue
    @Transient
    private Date startDate;
    @Column(name = "startDate")
    private String startDateString;

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isInsurance() {
        return insurance;
    }

    public void setInsurance(boolean insurance) {
        this.insurance = insurance;
    }

    public boolean isInsuranceIncluding() {
        return insuranceIncluding;
    }

    public void setInsuranceIncluding(boolean insuranceIncluding) {
        this.insuranceIncluding = insuranceIncluding;
    }

    public double getInsuranceK() {
        return insuranceK;
    }

    public void setInsuranceK(double insuranceK) {
        this.insuranceK = insuranceK;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public Date getStartDate() {
        return startDate;
    }
    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
        this.startDateString = startDate.toString();
    }
}
