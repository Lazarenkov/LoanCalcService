package util.valueExtractor;

import data.*;
import data.enums.PaymentType;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ValueExtractorTest {

    Target target = new Target();
    CalcParams calcParams = new CalcParams();
    CalcResult calcResult = new CalcResult();

    @BeforeAll
    public void setTestObject() {
        calcParams.setPaymentType(PaymentType.ANNUAL);
        calcParams.setInsurance(true);
        calcParams.setInsuranceIncluding(true);
        calcParams.setInsuranceK(0.014);
        calcParams.setAmount(101);
        calcParams.setRate(17);
        calcParams.setTerm(12);
        target.setCalcParams(calcParams);

        calcResult.setInsuranceAmount(1000);
        calcResult.setFullCreditAmount(10000);
        calcResult.setClearCreditAmount(9000);
        calcResult.setPaymentAmount(999);
        calcResult.setMaxPaymentAmount(888);
        calcResult.setMinPaymentAmount(777);
        target.setCalcResult(calcResult);

        ValueExtractor.init(target);
    }

    @Test
    @Order((1))
    public void shouldExtractCorrectAmount() {
        double expected = 101;
        double actual = (double) ValueExtractor.getValue(ValueTypes.AMOUNT);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((2))
    public void shouldExtractCorrectTerm() {
        int expected = 12;
        int actual = (int) ValueExtractor.getValue(ValueTypes.TERM);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((3))
    public void shouldExtractCorrectRate() {
        double expected = 17;
        double actual = (double) ValueExtractor.getValue(ValueTypes.RATE);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((4))
    public void shouldExtractCorrectInsValue() {
        double expected = 1000;
        double actual = (double) ValueExtractor.getValue(ValueTypes.INSURANCEAMOUNT);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((5))
    public void shouldExtractCorrectFullCreditAmount() {
        double expected = 10000;
        double actual = (double) ValueExtractor.getValue(ValueTypes.FULLCREDITAMOUNT);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((6))
    public void shouldExtractCorrectClearCreditAmount() {
        double expected = 9000;
        double actual = (double) ValueExtractor.getValue(ValueTypes.CLEARCREDITAMOUNT);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((7))
    public void shouldExtractCorrectPaymentAmount() {
        double expected = 999;
        double actual = (double) ValueExtractor.getValue(ValueTypes.PAYMENTAMOUNT);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((8))
    public void shouldExtractCorrectMaxPaymentAmount() {
        target.setCalcResult(calcResult);

        double expected = 888;
        double actual = (double) ValueExtractor.getValue(ValueTypes.MAXPAYMENT);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @Order((9))
    public void shouldExtractCorrectMinPaymentAmount() {

        double expected = 777;
        double actual = (double) ValueExtractor.getValue(ValueTypes.MINPAYMENT);
        Assertions.assertEquals(expected, actual);
    }
}
