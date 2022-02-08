package com.myob.payslip_calculator;

import java.math.BigDecimal;

public class TaxThresholdAndRate {

    private BigDecimal taxThreshold;

    private BigDecimal taxRate;

    public TaxThresholdAndRate(BigDecimal taxThreshold, BigDecimal taxRate) {
        this.taxThreshold = taxThreshold;
        this.taxRate = taxRate;
    }

    public BigDecimal getTaxThreshold() {
        return taxThreshold;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
