package com.myob.payslip_calculator;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            // Exactly two arguments must be given
            argumentsError();

        }
        String name = args[0];
        BigDecimal yearlyIncome = null;
        // Check if the second argument can be parsed as a BigDecimal
        try {
            yearlyIncome = new BigDecimal(args[1]);
            if (yearlyIncome.compareTo(BigDecimal.ZERO) < 0) {
                // The number parsed was negative. In some contexts a negative income might be acceptable, consult with BA
                System.err.printf("%s is negative! Yearly income must be positive.\n", yearlyIncome);
                argumentsError();
            }
        } catch (NumberFormatException e) {
            System.err.printf("Could not parse %s as a BigDecimal/number.\n", args[1]);
            argumentsError();
        }

        // Note that due to the parameter sets in the division method in monthlyAccountFromYearly, all values will be given with two decimal places
        // As opposed to zero for the example output.
        System.out.printf("Monthly Payslip for: \"%s\"\n", name);
        System.out.printf("Gross Monthly Income: $%s\n", IncomeUtils.monthlyAccountFromYearly(yearlyIncome));
        System.out.printf("Monthly Income Tax: $%s\n", IncomeUtils.monthlyIncomeTaxFromYearlyGrossIncome(yearlyIncome));
        System.out.printf("Net Monthly Income: $%s\n", IncomeUtils.monthlyNetIncomeFromYearlyGrossIncome(yearlyIncome));
    }

    private static void argumentsError() {
        System.err.println("Exactly two arguments must be provided - a name, as a string, and a number representing yearly income.");
        System.err.println("e.g. 'java -jar payslip_calculator.jar \"Mary Song\" 60000'");
        System.exit(1);
    }
}
