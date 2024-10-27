package calc;

import data.Payment;
import data.Target;
import date.DateHelper;
import calc.interfaces.Calculable;
import org.apache.logging.log4j.LogManager;
import repo.PaymentRepository;
import util.FinHelper;
import util.MathHelper;
import util.valueExtractor.ValueExtractor;
import util.valueExtractor.Values;

import java.util.List;

import static util.valueExtractor.Extractions.*;

public class DiffCalc implements Calculable {

    private Target target;

    public DiffCalc() {
    }

    public DiffCalc(Target target, PaymentRepository repo) {
        this.target = target;
        target.getCalcResult().setRepo(repo);
    }

    @Override
    public void run(Target target) {
        LogManager.getLogger(DiffCalc.class.getName()).info("Расчет дифференцированных платежей");
        this.target = target;

        formPaymentList();
        setMinMaxPaymentsAmounts();
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Values
    void formPaymentList() {
        ValueExtractor.init(target);

        PaymentRepository repo = new PaymentRepository();
        List<String> payDates = DateHelper.getPayDates(START_DATE(), TERM());
        double debtAmount = FULL_CREDIT_AMOUNT();
        int term = TERM();
        double rate = FinHelper.getRateMonthly(RATE());
        double mainDebtPay = MathHelper.roundFloor(debtAmount / term);

        for (int i = 1; i <= term; i++) {
            double paymentValue = MathHelper.roundFloor(debtAmount * rate + mainDebtPay);

            Payment payment = new Payment();
            payment.setNumber(i);
            payment.setDate(payDates.get(i - 1));
            payment.setPaymentValue(paymentValue);
            payment.setPercentValue(MathHelper.roundFloor(debtAmount * rate));
            payment.setDebtValue(mainDebtPay);
            repo.addPayment(payment);

            debtAmount = debtAmount - mainDebtPay;
        }
        target.getCalcResult().setRepo(repo);
    }

    void setMinMaxPaymentsAmounts() {
        target.getCalcResult().setMaxPaymentAmount(target.getCalcResult().getRepo().getPayment(0).getPaymentValue());
        target.getCalcResult().setMinPaymentAmount(target.getCalcResult().getRepo().getLastPayment().getPaymentValue());
    }
}
