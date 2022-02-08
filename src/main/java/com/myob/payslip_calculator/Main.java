package com.myob.payslip_calculator;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            argumentsError();
            System.exit(1);
        }
        String name = args[0];
        BigDecimal yearlyIncome = null;
        try {
            yearlyIncome = new BigDecimal(args[1]);
        } catch (NumberFormatException e) {
            System.err.printf("Could not parse %s as a BigDecimal/number.\n", args[1]);
            argumentsError();
            System.exit(1);
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
    }
}
