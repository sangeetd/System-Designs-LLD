package com.sangeetdas.project.payment;

public class CashPayment implements IPaymentStrategy{
    @Override
    public void doPayment(double amount) {
        System.out.println("Payment at the time of checkout");
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CASH;
    }

    @Override
    public boolean isSuccessfull() {
        return true;
    }
}
