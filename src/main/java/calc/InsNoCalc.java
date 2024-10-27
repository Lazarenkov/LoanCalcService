package calc;

import data.CalcResult;
import data.Target;
import calc.interfaces.Calculable;
import org.apache.logging.log4j.LogManager;
import util.valueExtractor.ValueExtractor;
import util.valueExtractor.Values;

import static util.valueExtractor.Extractions.*;

public class InsNoCalc implements Calculable {

    private Target target;
    private CalcResult calcResult = new CalcResult();

    @Override
    public void run(Target target) {
        LogManager.getLogger(InsNoCalc.class.getName()).info("Расчет без страхования");
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

    protected void setInsuranceAmount() {
        calcResult.setInsuranceAmount(0);
    }

    @Values
    protected void setClearCreditAmount() {
        ValueExtractor.init(target);

        calcResult.setClearCreditAmount(FULL_CREDIT_AMOUNT());
        target.setCalcResult(calcResult);
    }
}
