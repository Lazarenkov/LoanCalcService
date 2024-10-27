package repo;

import data.Payment;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentRepositoryTest {

    static Payment payment = new Payment();
    static PaymentRepository repo = new PaymentRepository();

    @BeforeAll
    static void setTestObject() {
        payment.setNumber(1);
        payment.setDate("23.09.2024");
        payment.setPaymentValue(12356.88);
        payment.setPercentValue(2356);
        payment.setDebtValue(10000.88);
        repo.addPayment(payment);
    }

    @Test
    void shouldReturnArrayListOfPaymentsToString() {
        ArrayList<String> expected = new ArrayList<>();
        expected.add("1, 23.09.2024, 12356.88, 2356.0, 10000.88");
        ArrayList<String> actual = repo.getPaymentsAsArrayString();
        Assertions.assertEquals(expected, actual);

    }
}
