package db.service;

import data.*;
import data.enums.PaymentType;
import date.Date;
import exeptions.DBRetrievingException;
import org.junit.jupiter.api.*;
import repo.PaymentRepository;

import java.util.List;
import java.util.UUID;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DbServiceTest {

    Target target;
    CalcParams calcParams;
    CalcResult calcResult;
    UUID uuid;
    int i;

    @BeforeAll
    @AfterAll
    public void clearDB() {
        DbService.dropAll();
    }

    public void setTestObject(int i) {
        target = new Target();
        calcParams = new CalcParams();
        calcResult = new CalcResult();

        calcParams.setPaymentType(PaymentType.ANNUAL);
        calcParams.setInsurance(true);
        calcParams.setInsuranceIncluding(false);
        calcParams.setInsuranceK(0.041);
        calcParams.setAmount(i);
        calcParams.setRate(i);
        calcParams.setTerm(i);

        calcParams.setStartDate(new Date("01.01.2024"));
        target.setCalcParams(calcParams);

        calcResult.setInsuranceAmount(i);
        calcResult.setFullCreditAmount(i);
        calcResult.setClearCreditAmount(i);
        calcResult.setPaymentAmount(i);

        PaymentRepository repo = new PaymentRepository();
        Payment payment = new Payment();
        payment.setDate("11.10.2024");
        payment.setPaymentValue(1000);
        payment.setDebtValue(900);
        payment.setPercentValue(100);
        repo.addPayment(payment);
        calcResult.setRepo(repo);

        target.setCalcResult(calcResult);
    }

    @Order(1)
    @RepeatedTest(3)
    void shouldSaveTarget() {
        setTestObject(++i);
        uuid = DbService.save(target);
    }

    @Test
    @Order(2)
    void shouldGetTargetFromDBById() {
        List<Target> allTargets = DbService.retrieveAllTargets();
        Target targetExpected = allTargets.get(0);
        Target targetActual = DbService.retrieveTargetByID(targetExpected.getId());
        Assertions.assertEquals(targetExpected.getId(), targetActual.getId());
    }

    @Test
    @Order(3)
    void shouldGetAllTargetsFromDB() {
        List<Target> allTargets = DbService.retrieveAllTargets();
        Assertions.assertEquals(3, allTargets.size());
    }

    @Test
    @Order(4)
    void shouldGetCalcParamsWhereInt() {
        List<CalcParams> calcParamslist = DbService.retrieveCalcParamsWhere("term", 1);
        CalcParams calcParamsActual = calcParamslist.get(0);
        Assertions.assertEquals(1, calcParamsActual.getAmount());
    }

    @Test
    @Order(5)
    void shouldGetCalcParamsWhereDouble() {
        List<CalcParams> calcParamslist = DbService.retrieveCalcParamsWhere("amount", 1.0);
        CalcParams calcParamsActual = calcParamslist.get(0);
        Assertions.assertEquals(1, calcParamsActual.getTerm());
    }

    @Test
    @Order(6)
    void shouldGetCalcParamsWhereString() {
        List<CalcParams> calcParamslist = DbService.retrieveCalcParamsWhere("paymentType", "ANNUAL");
        CalcParams calcParamsActual = calcParamslist.get(0);
        Assertions.assertEquals(1, calcParamsActual.getRate());
    }

    @Test
    @Order(7)
    void shouldGetCalcParamsWhereBool() {
        List<CalcParams> calcParamslist = DbService.retrieveCalcParamsWhere("insurance", true);
        CalcParams calcParamsActual = calcParamslist.get(0);
        Assertions.assertEquals(1, calcParamsActual.getRate());
    }

    @Test
    @Order(8)
    void shouldGetCalcResultsWhereInt() {
        List<CalcResult> calcResultslist = DbService.retrieveCalcResultsWhere("id", 1);
        CalcResult calcResultActual = calcResultslist.get(0);
        Assertions.assertEquals(1, calcResultActual.getPaymentAmount());
    }

    @Test
    @Order(9)
    void shouldGetCalcResultsWhereDouble() {
        List<CalcResult> calcResultslist = DbService.retrieveCalcResultsWhere("clearCreditAmount", 1.0);
        CalcResult calcResultActual = calcResultslist.get(0);
        Assertions.assertEquals(1, calcResultActual.getPaymentAmount());
    }

    @Test
    @Order((10))
    void shouldThrowDBRetrievingException() {
        Assertions.assertThrows(DBRetrievingException.class, () -> DbService.retrieveTargetByID(UUID.fromString("a5afed2f-e859-42bb-a7ac-a3ee954cb223")));
    }

    @Test
    @Order((11))
    void shouldDeleteTarget() {
        DbService.deleteTargetByID(uuid);
        Assertions.assertThrows(DBRetrievingException.class, () -> DbService.retrieveTargetByID(uuid));
    }
}
