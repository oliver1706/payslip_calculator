import com.myob.payslip_calculator.IncomeUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

public class IncomeUtilsTest {
    @ParameterizedTest
    @CsvSource({
        "0.48,0.04",
        "14000,1166.67",
        "120000,10000",
        "6000000,500000"
    })
    void monthlyAccountFromYearlyTest(String grossYearlyIncome, String expectedMonthlyIncome) {
        BigDecimal grossMonthlyIncome = IncomeUtils.monthlyAccountFromYearly(new BigDecimal(grossYearlyIncome));
        assertBigDecimalEqual(new BigDecimal(expectedMonthlyIncome), grossMonthlyIncome);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "12000,0",
            "60000,6000",
            "150000,31000",
            "200000,48000"
    })
    void yearlyIncomeTaxTest(String grossYearlyIncome, String expectedYearlyIncomeTax) {
        BigDecimal yearlyIncomeTax = IncomeUtils.yearlyIncomeTax(new BigDecimal(grossYearlyIncome));
        assertBigDecimalEqual(new BigDecimal(expectedYearlyIncomeTax), yearlyIncomeTax);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "12000,0",
            "60000,500",
            "150000,2583.33",
            "200000,4000"
    })
    void monthlyIncomeTaxFromYearlyGrossTest(String grossYearlyIncome, String expectedMonthlyIncomeTax) {
        BigDecimal monthlyIncomeTax = IncomeUtils.monthlyIncomeTaxFromYearlyGrossIncome(new BigDecimal(grossYearlyIncome));
        assertBigDecimalEqual(new BigDecimal(expectedMonthlyIncomeTax), monthlyIncomeTax);
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "12000,1000",
            "60000,4500",
            "150000,9916.67",
            "200000,12666.67"
    })
    void monthlyNetIncomeFromYearlyGrossTest(String grossYearlyIncome, String expectedMonthlyNetIncome) {
        BigDecimal monthlyNetIncome = IncomeUtils.monthlyNetIncomeFromYearlyGrossIncome(new BigDecimal(grossYearlyIncome));
        assertBigDecimalEqual(new BigDecimal(expectedMonthlyNetIncome), monthlyNetIncome);
    }

    private void assertBigDecimalEqual(BigDecimal expected, BigDecimal actual) {
        Assertions.assertEquals(0, actual.compareTo(expected));
    }
}
