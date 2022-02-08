package com.myob.payslip_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;

public class IncomeUtils {

    private static int MONTHS = 12;

    private static List<TaxThresholdAndRate> taxThresholdsAndRates;

    static {
        taxThresholdsAndRates = new ArrayList<>();
        taxThresholdsAndRates.add(new TaxThresholdAndRate(new BigDecimal("180000.00"), new BigDecimal("0.40")));
        taxThresholdsAndRates.add(new TaxThresholdAndRate(new BigDecimal("80000.00"), new BigDecimal("0.30")));
        taxThresholdsAndRates.add(new TaxThresholdAndRate(new BigDecimal("40000.00"), new BigDecimal("0.20")));
        taxThresholdsAndRates.add(new TaxThresholdAndRate(new BigDecimal("20000.00"), new BigDecimal("0.10")));
        taxThresholdsAndRates.add(new TaxThresholdAndRate(new BigDecimal("80000.00"), new BigDecimal("0.00")));
    }

    private IncomeUtils() {
    }

    public static BigDecimal monthlyAccountFromYearly(BigDecimal yearlyAccount) {
        // 12 months in a year, rounding to nearest cent
        return yearlyAccount.divide(BigDecimal.valueOf(MONTHS), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal monthlyIncomeTaxFromYearlyGrossIncome(BigDecimal yearlyGrossIncome) {
        // Calculate annual tax, then divide by 12 months
        return monthlyAccountFromYearly(yearlyIncomeTax(yearlyGrossIncome));
    }

    public static BigDecimal yearlyIncomeTax(BigDecimal yearlyGrossIncome) {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (TaxThresholdAndRate taxThresholdAndRate : taxThresholdsAndRates) {
            // For each threshold, check if our yearly income is greater than it
            // If so, for the amount that is over the threshold, calculate the applicable tax
            // Finally, take the threshold to be the new yearly income and continue
            BigDecimal taxThreshold = taxThresholdAndRate.getTaxThreshold();
            if (yearlyGrossIncome.compareTo(taxThreshold) > 0) {
                BigDecimal incomeTaxableAtThisRate = yearlyGrossIncome.subtract(taxThreshold);
                BigDecimal taxRate = taxThresholdAndRate.getTaxRate();
                totalTax = totalTax.add(incomeTaxableAtThisRate.multiply(taxRate));
                yearlyGrossIncome = taxThreshold;
            }
        }
        return totalTax;
    }

    public static BigDecimal monthlyNetIncomeFromYearlyGrossIncome(BigDecimal yearlyGrossIncome) {
        return monthlyAccountFromYearly(yearlyGrossIncome).subtract(monthlyIncomeTaxFromYearlyGrossIncome(yearlyGrossIncome));
    }
}
