package io.console;

import data.*;
import date.Date;
import util.valueExtractor.ValueExtractor;

import java.util.Scanner;

import static util.valueExtractor.Extractions.*;
import static data.enums.PaymentType.*;

public class ConsoleHelper {

    public static String askUser(String question) {
        Scanner input = new Scanner(System.in);
        System.out.println(question);
        String answer = input.next();
        while (!isCorrectAnswer(answer)) {
            typeIncorrectInputMsg();
            System.out.println(question);
            answer = input.next();
        }
        return answer;
    }

    private static boolean isCorrectAnswer(String input) {
        return input.equals("Y") || input.equals("N");
    }

    public static int getIntFromInput() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static double getDoubleFromInput() {
        Scanner input = new Scanner(System.in);
        return input.nextDouble();
    }

    public static Date getDateFromInput() {
        Scanner input = new Scanner(System.in);
        Date date = new Date();
        date.setDate(input.next());
        return date;
    }

    public static void typeIncorrectInputMsg() {
        System.out.println("Введено некорректное значение");
    }

    public static void type(String msg) {
        System.out.println(msg);
    }

    public static void printCalcResult(Target target) {
        ValueExtractor.init(target);
        System.out.println("Сумма кредита   " + FULL_CREDIT_AMOUNT() + " руб.");
        System.out.println("На руки         " + CLEAR_CREDIT_AMOUNT() + " руб.");
        System.out.println("Сумма страховки " + INSURANCE_AMOUNT() + " руб.");
        System.out.println("Срок            " + TERM() + " мес.");
        if (PAYMENT_TYPE() == ANNUAL) {
            System.out.println("Платеж в месяц  " + (PAYMENT_AMOUNT() + " руб."));
        } else {
            System.out.println("Платеж в месяц от  " + MAX_PAYMENT() + " руб.");
            System.out.println("Платеж в месяц до  " + MIN_PAYMENT() + " руб.");
        }
        System.out.println("Ставка          " + RATE() + " %");

        String answer = askUser("Сформировать график платежей?");
        if (answer.equals("Y")) {
            printPaymentList(target);
        }
    }

    public static void printPaymentList(Target target) {
        Payment[] payments = target.getCalcResult().getRepo().getPayments();
        System.out.println("№ п/п    "
                + "Дата платежа        "
                + "Сумма платежа      "
                + "Проценты           "
                + "Основной долг");
        for (int i = 0; i <= payments.length - 1; i++) {
            int number = payments[i].getNumber();
            String date = payments[i].getDate();
            double paymentValue = payments[i].getPaymentValue();
            double percentValue = payments[i].getPercentValue();
            double debtValue = payments[i].getDebtValue();
            System.out.println(number
                    + "        "
                    + date
                    + "          "
                    + paymentValue
                    + " руб.       "
                    + percentValue
                    + " руб.        "
                    + debtValue
                    + " руб.");
        }
    }
}
