package calc;

import data.CalcResult;
import data.Target;
import exeptions.ServiceNotInitException;
import calc.interfaces.Calculable;
import org.apache.logging.log4j.LogManager;
import util.MathHelper;
import util.valueExtractor.ValueExtractor;

import util.valueExtractor.Values;

import static util.valueExtractor.Extractions.*;

public class InsRecountCalc implements Calculable {

    private Target target;
    CalcResult calcResult = new CalcResult();

    @Override
    public void run(Target target) {
        LogManager.getLogger(InsRecountCalc.class.getName()).info("Расчет со страхованием, включенным в сумму");
        this.target = target;

        setFullCreditAmount();
        setInsuranceAmount();
        setClearCreditAmount();
        target.setCalcResult(calcResult);
    }

    @Override
    public Target getTarget() {
        return target;
    }

    @Values
    protected void setFullCreditAmount() throws ServiceNotInitException {
        ValueExtractor.init(target);

        double initialValue = AMOUNT();
        double insK = INSURANCE_K();

        double value = initialValue * insK;

        while (value - (value * insK) <= initialValue) {
            value = value + 0.01;
        }
        calcResult.setFullCreditAmount(MathHelper.roundCeil(value));
        target.setCalcResult(calcResult);
    }

    @Values
    protected void setInsuranceAmount() {
        ValueExtractor.init(target);

        double insAmount = MathHelper.roundCeil(FULL_CREDIT_AMOUNT() * INSURANCE_K());
        calcResult.setInsuranceAmount(insAmount);
        target.setCalcResult(calcResult);
    }

    @Values
    protected void setClearCreditAmount() {
        ValueExtractor.init(target);

        calcResult.setClearCreditAmount(FULL_CREDIT_AMOUNT() - INSURANCE_AMOUNT());
        target.setCalcResult(calcResult);
    }
}
