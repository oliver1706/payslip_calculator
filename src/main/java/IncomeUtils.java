import java.math.BigDecimal;
import java.math.MathContext;
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

    public static BigDecimal monthlyAccountFromYearly(BigDecimal yearlyIncome) {
        // 12 months in a year, rounding to nearest cent
        return yearlyIncome.divide(BigDecimal.valueOf(MONTHS), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal monthlyIncomeTaxFromYearlyGross(BigDecimal yearlyIncome) {
        // Calculate annual tax, then divide by 12 months
        return monthlyAccountFromYearly(yearlyIncomeTax(yearlyIncome));
    }

    public static BigDecimal yearlyIncomeTax(BigDecimal yearlyIncome) {
        BigDecimal totalTax = BigDecimal.ZERO;
        for (TaxThresholdAndRate taxThresholdAndRate : taxThresholdsAndRates) {
            // For each threshold, check if our yearly income is greater than it
            // If so, for the amount that is over the threshold, calculate the applicable tax
            // Finally, take the threshold to be the new yearly income and continue
            BigDecimal taxThreshold = taxThresholdAndRate.getTaxThreshold();
            if (yearlyIncome.compareTo(taxThreshold) > 0) {
                BigDecimal incomeTaxableAtThisRate = yearlyIncome.subtract(taxThreshold);
                BigDecimal taxRate = taxThresholdAndRate.getTaxRate();
                totalTax = totalTax.add(incomeTaxableAtThisRate.multiply(taxRate));
                yearlyIncome = taxThreshold;
            }
        }
        return totalTax;
    }

    public static BigDecimal monthlyNetIncomeFromYearlyGross(BigDecimal yearlyIncome) {
        return monthlyAccountFromYearly(yearlyIncome).subtract(monthlyIncomeTaxFromYearlyGross(yearlyIncome));
    }
}
