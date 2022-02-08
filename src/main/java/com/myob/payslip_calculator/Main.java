package com.myob.payslip_calculator;

import java.math.BigDecimal;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Exactly two arguments must be provided - a name, as a string, and a number representing yearly income.");
            System.err.println("e.g. 'java -jar payslip_calculator.jar \"Mary Song\" 60000'");
            System.exit(1);
        }
        String name = args[0];
        BigDecimal yearlyIncome = new BigDecimal(args[1]);

        System.out.printf("Monthly Payslip for: \"%s\"\n", name);
        System.out.printf("Gross Monthly Income: $%s\n", IncomeUtils.monthlyAccountFromYearly(yearlyIncome));
        System.out.printf("Gross Monthly Income: $%s\n", IncomeUtils.monthlyIncomeTaxFromYearlyGross(yearlyIncome));
        System.out.printf("Net Monthly Income: $%s\n", IncomeUtils.monthlyNetIncomeFromYearlyGross(yearlyIncome));
    }
}
