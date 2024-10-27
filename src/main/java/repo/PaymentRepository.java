package repo;

import data.Payment;

import java.util.ArrayList;

public class PaymentRepository {

    private Payment[] payments = new Payment[0];

    private ArrayList<String> stringPayments = new ArrayList<>();

    public void addPayment(Payment payment) {
        Payment[] tmp = new Payment[payments.length + 1];
        System.arraycopy(payments, 0, tmp, 0, payments.length);
        tmp[tmp.length - 1] = payment;
        payments = tmp;
    }

    public Payment[] getPayments() {
        return payments;
    }

    public ArrayList<String> getPaymentsAsArrayString() {
        for (Payment payment : payments) {
            stringPayments.add(payment.toString());
        }
        return stringPayments;
    }

    public Payment getPayment(int i) {
        return payments[i];
    }

    public Payment getLastPayment() {
        return payments[payments.length - 1];
    }
}
