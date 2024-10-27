package calc;

import data.CalcParams;
import data.Target;
import calc.interfaces.Calculable;
import db.service.DbService;
import main.ArgsChecker;
import util.valueExtractor.ValueExtractor;
import util.valueExtractor.Values;

import static data.enums.PaymentType.*;
import static util.valueExtractor.Extractions.*;

public class CalcManager {

    private Target target = new Target();
    private Calculable paymentCalc;
    private Calculable insCalc;

    @Values
    public CalcManager(CalcParams calcParams) {
        target.setCalcParams(calcParams);

        ValueExtractor.init(target);

        if (INSURANCE() & INSURANCE_INCLUDING()) {
            insCalc = new InsRecountCalc();
        } else if (INSURANCE() & !INSURANCE_INCLUDING()) {
            insCalc = new InsNoRecountCalc();
        } else if (!INSURANCE()) {
            insCalc = new InsNoCalc();
        }

        if (PAYMENT_TYPE() == ANNUAL) {
            paymentCalc = new AnnualCalc();
        } else if (PAYMENT_TYPE() == DIFF) {
            paymentCalc = new DiffCalc();
        }
    }

    @Values
    public void setResult() {
        ValueExtractor.init(target);

        insCalc.run(target);
        target = insCalc.getTarget();

        paymentCalc.run(target);
        target = paymentCalc.getTarget();

        if (ArgsChecker.isDbEnable()) {
            target.setId(DbService.save(target));
        }
    }

    public Target getTarget() {
        return target;
    }
}
