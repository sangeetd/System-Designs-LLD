package com.sangeetdas.project.charge;

public class ServiceTaxCharge implements IBaseCharge{

    private IBaseCharge baseCharge;
    private double serviceTax;

    public ServiceTaxCharge(IBaseCharge baseCharge) {
        this.baseCharge = baseCharge;
    }

    public double getServiceTax() {
        return serviceTax;
    }

    public void setServiceTax(double serviceTax) {
        this.serviceTax = serviceTax;
    }

    @Override
    public double getCharge() {
        return this.baseCharge.getCharge()+this.serviceTax;
    }
}
