import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Calendar;

public class IncomeUtils {

    private static int MONTHS = 12;

    private IncomeUtils() {
    }

    public static BigDecimal grossMonthlyIncomeFromYearly(BigDecimal yearlyIncome) {
        return yearlyIncome.divide(BigDecimal.valueOf(MONTHS), twoDecimalMathContext());
    }

    public static MathContext twoDecimalMathContext() {
        return new MathContext(2, RoundingMode.HALF_UP);
    }
}
