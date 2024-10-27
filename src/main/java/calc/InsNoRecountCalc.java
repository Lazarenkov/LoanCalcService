package calc;

import data.CalcResult;
import data.Target;
import calc.interfaces.Calculable;
import org.apache.logging.log4j.LogManager;
import util.MathHelper;
import util.valueExtractor.ValueExtractor;
import util.valueExtractor.Values;

import static util.valueExtractor.Extractions.*;

public class InsNoRecountCalc implements Calculable {

    private Target target;
    private CalcResult calcResult = new CalcResult();

    @Override
    public void run(Target target) {
        LogManager.getLogger(InsNoRecountCalc.class.getName()).info("Расчет со страхованием без включения в сумму");
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
    protected void setFullCreditAmount() {
        ValueExtractor.init(target);

        calcResult.setFullCreditAmount(AMOUNT());
        target.setCalcResult(calcResult);
    }

    @Values
    protected void setInsuranceAmount() {
        ValueExtractor.init(target);

        calcResult.setInsuranceAmount(MathHelper.roundCeil(FULL_CREDIT_AMOUNT() * INSURANCE_K()));
        target.setCalcResult(calcResult);
    }

    @Values
    protected void setClearCreditAmount() {
        ValueExtractor.init(target);

        calcResult.setClearCreditAmount(FULL_CREDIT_AMOUNT() - INSURANCE_AMOUNT());
        target.setCalcResult(calcResult);
    }
}
