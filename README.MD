# Payslip Calculator Project

### Usage
This Java 11 project uses Gradle 5.2.1 to manage dependencies. The only external dependencies are JUnit5 framework related.  
To run tests, run `./gradlew test` in the root directory.
To run the project, run `./gradlew assemble` in the root directory to generate the jar, and then ```java -jar .\build\libs\payslip_calculator-1.0-SNAPSHOT.jar <employee name> <yearly gross income>``` to run it.  
Exactly two arguments must be provided, in that order. The first is a string, and can include spaces if surrounded by double quotes. The latter is a positive number.  
A valid example might be ```java -jar .\build\libs\payslip_calculator-1.0-SNAPSHOT.jar "Mary Song" 60000```.

### Design Decisions
Design decisions and assumptions made include:
  * I decided to use Java 11, Gradle 5.2.1 and JUnit5 to build and test my project. 
  * The example output showed Mary Song's income being printed with no decimals/cents. To simplify, all monetary values are printed with cents.
  * I considered having the thresholds and tax rates being contained in a properties file and loaded at runtime, to enable changing these values without a code change, but ultimately decided not to.
  * I decided to use a List of pairs of thresholds and tax rates to represent the progressive income tax system given. This approach might be less appropriate in the case of a more complex system involving levies, deductions etc.
  * A static utilities class was used to house all core logic. The actual runner is in the Main.java class.
  * I assumed the program should not accept negative income. I also assumed there was no requirement to accept numbers formatted differently from the example, e.g. with a dollar or other currency sign or comma separators.
  * The native Java class BigDecimal was chosen to store monetary values due to its precision.
