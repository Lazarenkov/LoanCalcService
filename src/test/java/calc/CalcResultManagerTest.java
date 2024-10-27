package calc;

import data.CalcParams;
import data.enums.PaymentType;
import date.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalcResultManagerTest {
    CalcParams calcParams = new CalcParams();

    void setTestObject(PaymentType paymentType,
                       boolean insurance,
                       boolean insuranceIncluding,
                       double insuranceK,
                       double value,
                       int term,
                       double rate) {
        calcParams.setPaymentType(paymentType);
        calcParams.setInsurance(insurance);
        calcParams.setInsuranceIncluding(insuranceIncluding);
        calcParams.setInsuranceK(insuranceK);
        calcParams.setAmount(value);
        calcParams.setTerm(term);
        calcParams.setRate(rate);
        calcParams.setStartDate(new Date("13.08.2024"));
    }

    @ParameterizedTest
    @DisplayName("Annual payment calculation test - all positive variables")
    @CsvSource({"true, true, 0.196, 100000, 60, 17, 3091.11",
            "true, false, 0.196, 100000, 60, 17, 2485.25",
            "false, true, 0.196, 100000, 60, 17, 2485.25",
            "false, false, 0, 100000, 60, 17, 2485.25"})
    void shouldCalculateCorrectAnnualPayment(boolean insurance,
                                             boolean insuranceIncluding,
                                             double insuranceK,
                                             double value,
                                             int term,
                                             double rate,
                                             double expected) {
        setTestObject(PaymentType.ANNUAL, insurance, insuranceIncluding, insuranceK, value, term, rate);

        CalcManager calcManager = new CalcManager(calcParams);
        calcManager.setResult();

        double actual = calcManager.getTarget().getCalcResult().getPaymentAmount();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("Diff payment calculation test - all positive variables")
    @CsvSource({"true, true, 0.196, 100000, 60, 17, 2102.33, 3834.98",
            "true, false, 0.196, 100000, 60, 17, 1690.27, 3083.32",
            "false, true, 0.196, 100000, 60, 17, 1690.27, 3083.32",
            "false, false, 0, 100000, 60, 17, 1690.27, 3083.32"})
    void shouldCalculateCorrectDiffPayment(boolean insurance,
                                           boolean insuranceIncluding,
                                           double insuranceK,
                                           double value,
                                           int term,
                                           double rate,
                                           double expectedMinPaymentValue,
                                           double expectedMaxPaymentValue) {
        setTestObject(PaymentType.DIFF, insurance, insuranceIncluding, insuranceK, value, term, rate);

        CalcManager calcManager = new CalcManager(calcParams);
        calcManager.setResult();
        double actualMinPaymentValue = calcManager.getTarget().getCalcResult().getMinPaymentAmount();
        double actualMaxPaymentValue = calcManager.getTarget().getCalcResult().getMaxPaymentAmount();

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedMinPaymentValue, actualMinPaymentValue),
                () -> Assertions.assertEquals(expectedMaxPaymentValue, actualMaxPaymentValue)
        );
    }
}

