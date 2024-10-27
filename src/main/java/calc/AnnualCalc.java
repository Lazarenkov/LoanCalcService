package calc;

import data.Payment;
import data.Target;
import date.DateHelper;
import calc.interfaces.Calculable;
import org.apache.logging.log4j.LogManager;
import repo.PaymentRepository;
import util.MathHelper;
import util.FinHelper;
import util.valueExtractor.ValueExtractor;
import util.valueExtractor.Values;

import java.util.List;

import static util.valueExtractor.Extractions.*;

public class AnnualCalc implements Calculable {

    private Target target;

    public AnnualCalc() {
    }

    public AnnualCalc(Target target, PaymentRepository repo) {
        this.target = target;
        target.getCalcResult().setRepo(repo);
    }

    @Override
    public void run(Target target) {
        LogManager.getLogger(AnnualCalc.class.getName()).info("Расчет аннуитетных платежей");
        this.target = target;

        calculatePaymentAmount();
        formPaymentList();
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Values
    private void calculatePaymentAmount() {
        ValueExtractor.init(target);

        if (RATE() == 0) {
            target.getCalcResult().setPaymentAmount(MathHelper.roundFloor(AMOUNT() / TERM()));
        }

        if (TERM() == 0) {
            target.getCalcResult().setPaymentAmount(0);
        }

        double rate = FinHelper.getRateMonthly(RATE());
        double k = FinHelper.getAnnualPaymentK(TERM(), rate);
        target.getCalcResult().setPaymentAmount(MathHelper.roundFloor(FULL_CREDIT_AMOUNT() * k));
    }

    @Values
    void formPaymentList() {
        ValueExtractor.init(target);

        PaymentRepository repo = new PaymentRepository();
        List<String> payDates = DateHelper.getPayDates(START_DATE(), TERM());
        double valueRemainder = FULL_CREDIT_AMOUNT();

        for (int i = 1; i <= TERM(); i++) {
            Payment payment = new Payment();
            payment.setNumber(i);
            payment.setDate(payDates.get(i - 1));
            payment.setPaymentValue(PAYMENT_AMOUNT());
            double currentPercentValue = FinHelper.getPercentValue(valueRemainder, RATE());
            payment.setPercentValue(currentPercentValue);
            double currentDebtValue = MathHelper.roundCeil((PAYMENT_AMOUNT()) - currentPercentValue);
            payment.setDebtValue(currentDebtValue);
            valueRemainder = valueRemainder - currentDebtValue;
            repo.addPayment(payment);
        }
        target.getCalcResult().setRepo(repo);
    }
}
