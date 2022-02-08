import com.myob.payslip_calculator.IncomeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class IncomeUtilsTest {
    @Test
    void monthlyAccountFromYearlyTest() {
        BigDecimal grossMonthlyIncome = IncomeUtils.monthlyAccountFromYearly(new BigDecimal("0.48"));
        Assertions.assertEquals(0, grossMonthlyIncome.compareTo(new BigDecimal("0.04")));

        grossMonthlyIncome = IncomeUtils.monthlyAccountFromYearly(BigDecimal.valueOf(14_000L));
        Assertions.assertEquals(0, grossMonthlyIncome.compareTo(new BigDecimal("1166.67")));

        grossMonthlyIncome = IncomeUtils.monthlyAccountFromYearly(BigDecimal.valueOf(120_000L));
        Assertions.assertEquals(0, grossMonthlyIncome.compareTo(BigDecimal.valueOf(10_000L)));

        grossMonthlyIncome = IncomeUtils.monthlyAccountFromYearly(BigDecimal.valueOf(6_000_000L));
        Assertions.assertEquals(0, grossMonthlyIncome.compareTo(BigDecimal.valueOf(500_000L)));
    }

    @Test
    void yearlyIncomeTaxTest() {
        BigDecimal grossYearlyIncome = BigDecimal.valueOf(60_000L);
        BigDecimal totalIncomeTax = IncomeUtils.yearlyIncomeTax(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(BigDecimal.valueOf(6_000L)));

        grossYearlyIncome = BigDecimal.valueOf(150_000L);
        totalIncomeTax = IncomeUtils.yearlyIncomeTax(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(BigDecimal.valueOf(31_000L)));

        grossYearlyIncome = BigDecimal.valueOf(200_000L);
        totalIncomeTax = IncomeUtils.yearlyIncomeTax(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(BigDecimal.valueOf(48_000L)));
    }

    @Test
    void monthlyIncomeTaxFromYearlyGrossTest() {
        BigDecimal grossYearlyIncome = BigDecimal.valueOf(60_000L);
        BigDecimal totalIncomeTax = IncomeUtils.monthlyIncomeTaxFromYearlyGross(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(BigDecimal.valueOf(500L)));

        grossYearlyIncome = BigDecimal.valueOf(150_000L);
        totalIncomeTax = IncomeUtils.monthlyIncomeTaxFromYearlyGross(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(new BigDecimal("2583.33")));

        grossYearlyIncome = BigDecimal.valueOf(200_000L);
        totalIncomeTax = IncomeUtils.monthlyIncomeTaxFromYearlyGross(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(BigDecimal.valueOf(4000L)));
    }

    @Test
    void monthlyNetIncomeFromYearlyGrossTest() {
        BigDecimal grossYearlyIncome = BigDecimal.valueOf(60_000L);
        BigDecimal totalIncomeTax = IncomeUtils.monthlyNetIncomeFromYearlyGross(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(BigDecimal.valueOf(4500L)));

        grossYearlyIncome = BigDecimal.valueOf(150_000L);
        totalIncomeTax = IncomeUtils.monthlyNetIncomeFromYearlyGross(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(new BigDecimal("9916.67")));

        grossYearlyIncome = BigDecimal.valueOf(200_000L);
        totalIncomeTax = IncomeUtils.monthlyNetIncomeFromYearlyGross(grossYearlyIncome);
        Assertions.assertEquals(0, totalIncomeTax.compareTo(new BigDecimal("12666.67")));
    }
}
