package com.sangeetdas.project.payment;

public interface IPaymentStrategy {
    void doPayment(double amount);
    PaymentType getPaymentType();
    boolean isSuccessfull();
}
