import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class IncomeUtilsTest {
    @Test
    void monthlyAccountFromYearlyTest() {
        BigDecimal grossMonthlyIncome = IncomeUtils.monthlyAccountFromYearly(BigDecimal.valueOf(120_000L));
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
    }
}
