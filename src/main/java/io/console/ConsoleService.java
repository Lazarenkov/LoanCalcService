package io.console;

import data.CalcParams;
import data.Target;
import data.enums.PaymentType;
import calc.CalcManager;

public class ConsoleService {

    private static CalcParams calcParams;

    public static void setService() {
        setParams();
        setResult();
    }

    private static void setParams() {
        calcParams = new CalcParams();
        String answer;

        answer = ConsoleHelper.askUser("Аннуитетные платежи? (Y/N): ");
        if (answer.equals("Y")) {
            calcParams.setPaymentType(PaymentType.ANNUAL);
        } else {
            calcParams.setPaymentType(PaymentType.DIFF);
        }

        answer = ConsoleHelper.askUser("Со страхованием? (Y/N): ");
        calcParams.setInsurance(answer.equals("Y"));

        if (calcParams.isInsurance()) {
            answer = ConsoleHelper.askUser("Включить страховку в сумму кредита? (Y/N): ");
            calcParams.setInsuranceIncluding(answer.equals("Y"));
            ConsoleHelper.type("Страховой коэффициент (абс., от суммы кредита за весь срок): ");
            calcParams.setInsuranceK(ConsoleHelper.getDoubleFromInput());
        }

        ConsoleHelper.type("Сумма кредита (руб.): ");
        calcParams.setAmount(ConsoleHelper.getDoubleFromInput());

        ConsoleHelper.type("Срок кредита (мес): ");
        calcParams.setTerm(ConsoleHelper.getIntFromInput());

        ConsoleHelper.type("Процентная ставка (%): ");
        calcParams.setRate(ConsoleHelper.getDoubleFromInput());

        ConsoleHelper.type("Дата выдачи кредита 'ЧЧ.ММ.ГГГГ': ");
        calcParams.setStartDate(ConsoleHelper.getDateFromInput());
    }

    private static void setResult() {
        CalcManager calcResultManager = new CalcManager(calcParams);
        calcResultManager.setResult();
        Target target = calcResultManager.getTarget();
        ConsoleHelper.printCalcResult(target);
        setService();
    }

}
