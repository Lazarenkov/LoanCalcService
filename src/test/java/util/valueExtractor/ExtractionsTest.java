package util.valueExtractor;

import data.*;
import data.enums.PaymentType;
import date.Date;
import org.junit.jupiter.api.*;

import static util.valueExtractor.Extractions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExtractionsTest {
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
        calcParams.setStartDate(new Date("12.08.2024"));
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
    void shouldReturnCorrectPaymentType() {
        String expected = "ANNUAL";
        PaymentType actual = PAYMENT_TYPE();
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldReturnCorrectInsurance() {
        boolean expected = true;
        boolean actual = INSURANCE();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectInsuranceIncluding() {
        boolean expected = true;
        boolean actual = INSURANCE_INCLUDING();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectInsuranceK() {
        double expected = 0.014;
        double actual = INSURANCE_K();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectAmount() {
        double expected = 101;
        double actual = AMOUNT();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectTerm() {
        int expected = 12;
        int actual = TERM();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectRate() {
        double expected = 17;
        double actual = RATE();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectPaymentAmount() {
        double expected = 999;
        double actual = PAYMENT_AMOUNT();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    void shouldReturnCorrectInsuranceAmount() {
        double expected = 1000;
        double actual = INSURANCE_AMOUNT();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectFullCreditAmount() {
        double expected = 10000;
        double actual = FULL_CREDIT_AMOUNT();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectClearCreditAmount() {
        double expected = 9000;
        double actual = CLEAR_CREDIT_AMOUNT();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectStartDate() {
        String expected = "12.08.2024";
        Date actual = START_DATE();
        Assertions.assertEquals(expected, actual.toString());
    }

    @Test
    void shouldReturnCorrectMaxPaymentAmount() {
        double expected = 888;
        double actual = MAX_PAYMENT();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCorrectMinPaymentAmount() {
        double expected = 777;
        double actual = MIN_PAYMENT();
        Assertions.assertEquals(expected, actual);
    }
}
