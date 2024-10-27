package data.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.enums.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CalcParamsDtoTest {

    private static String query = """
                {
                "paymentType" :"ANNUAL",
                        "insurance" : true,
                        "insuranceIncluding" : true,                        
                        "insuranceK" : 0.014,
                        "amount" : 100000,
                        "term" : 12,
                        "rate" : 10,
                        "startDate" : "19.09.2024"
            }""";

    private static CalcParamsDTO queryParamsExpected = new CalcParamsDTO();
    private static CalcParamsDTO queryParamsActual;

    @BeforeAll
    static void setTestObject() throws JsonProcessingException {
        queryParamsExpected.setPaymentType(String.valueOf(PaymentType.ANNUAL));
        queryParamsExpected.setInsurance(true);
        queryParamsExpected.setInsuranceIncluding(true);
        queryParamsExpected.setInsuranceK(0.014);
        queryParamsExpected.setAmount(100000);
        queryParamsExpected.setTerm(12);
        queryParamsExpected.setRate(10);
        queryParamsExpected.setStartDate("19.09.2024");

        ObjectMapper mapper = new ObjectMapper();
        queryParamsActual = mapper.readValue(query, CalcParamsDTO.class);
    }

    @Test
    void shouldReturnCorrectPaymentType() {
        Assertions.assertEquals(queryParamsExpected.getPaymentType(), queryParamsActual.getPaymentType());
    }

    @Test
    void shouldReturnCorrectInsurance() {
        Assertions.assertEquals(queryParamsExpected.isInsurance(), queryParamsActual.isInsurance());
    }

    @Test
    void shouldReturnCorrectInsuranceIncluding() {
        Assertions.assertEquals(queryParamsExpected.isInsuranceIncluding(), queryParamsActual.isInsuranceIncluding());
    }

    @Test
    void shouldReturnCorrectInsuranceK() {
        Assertions.assertEquals(queryParamsExpected.getInsuranceK(), queryParamsActual.getInsuranceK());
    }

    @Test
    void shouldReturnCorrectAmount() {
        Assertions.assertEquals(queryParamsExpected.getAmount(), queryParamsActual.getAmount());
    }

    @Test
    void shouldReturnCorrectTerm() {
        Assertions.assertEquals(queryParamsExpected.getTerm(), queryParamsActual.getTerm());
    }

    @Test
    void shouldReturnCorrectRate() {
        Assertions.assertEquals(queryParamsExpected.getRate(), queryParamsActual.getRate());
    }

    @Test
    void shouldReturnCorrectStartDate() {
        Assertions.assertEquals(queryParamsExpected.getStartDate(), queryParamsActual.getStartDate());
    }
}
