package com.sangeetdas.project.payment;

public class UPIPayment implements IPaymentStrategy{

    private String upiId;
    private String upiPIN;
    private boolean isSuccessful;

    public UPIPayment(String upiId, String upiPIN) {
        this.upiId = upiId;
        this.upiPIN = upiPIN;
        this.isSuccessful= false;
    }

    @Override
    public void doPayment(double amount) {
        System.out.println("Payment done via UPI mode through UPID: "+this.upiId);
        this.isSuccessful = true;
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.UPI;
    }

    @Override
    public boolean isSuccessfull() {
        return this.isSuccessful;
    }
}
