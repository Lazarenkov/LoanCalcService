package repo;

import data.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentTest {

    static Payment payment = new Payment();

    @BeforeAll
    static void setTestObject() {
        payment.setNumber(1);
        payment.setDate("23.09.2024");
        payment.setPaymentValue(12356.88);
        payment.setPercentValue(2356);
        payment.setDebtValue(10000.88);

    }

    @Test
    void toStringTest() {
        String expected = "1, 23.09.2024, 12356.88, 2356.0, 10000.88";
        String actual = payment.toString();
        Assertions.assertEquals(expected, actual);
    }
}
