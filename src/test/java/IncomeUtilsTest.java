import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class IncomeUtilsTest {
    @Test
    void incomeUtilsTest() {
        BigDecimal grossMonthlyIncome = IncomeUtils.grossMonthlyIncomeFromYearly(BigDecimal.valueOf(120_000L));
        Assertions.assertEquals(0, grossMonthlyIncome.compareTo(BigDecimal.valueOf(10_000L)));

        grossMonthlyIncome = IncomeUtils.grossMonthlyIncomeFromYearly(BigDecimal.valueOf(6_000_000L));
        Assertions.assertEquals(0, grossMonthlyIncome.compareTo(BigDecimal.valueOf(500_000L)));
    }
}
