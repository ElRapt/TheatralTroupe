# TheatralTroupe Kata

![GitHub License](https://img.shields.io/github/license/ElRapt/TheatralTroupe)
![Language](https://img.shields.io/badge/Language-Java-orange)
![Engine](https://img.shields.io/badge/Engine-Gradle-yellow)
![Size](https://img.shields.io/badge/Size-1MB-yellowgreen)

## Objective

The core objective of this project is to refactor and extend the original codebase for managing theatrical performances and invoicing in the TheatralTroupe kata. The codebase will introduce new features such as customer loyalty points and discounts, and will also add HTML invoice rendering capabilities.

## Installation and Environment Setup

Before starting, make sure to have JDK 17 installed on your machine. This project is not compatible with Java 19. The project uses Git for version control and Gradle for build automation.

## Refactoring and Code Cleanup

We start off by refactoring the existing code, especially the `StatementPrinter` class. The refactoring efforts aim to improve code readability and performance. Some key areas of focus include :

- Utilizing `StringBuilder` for string concatenation.
- Introducing more meaningful variable names.
- Modifying financial calculations to use floating-point numbers instead of integers.

Unit tests were added to further validate parts of the application not covered by the existing acceptance tests.

## Approach

A polymorphic approach has been utilized to resolve this problem. We have various types of `Plays` (Comedy and Tragedy) with different pricing policies, and each has its subclass extending from the base `Play` class. Each subclass contains its logic for calculating the cost and earned fidelity points.

We have a `StatementPrinter` class, which is used to contain all the logic for Invoice printing : in HTML and Text format.

## HTML Rendering

The client requested the addition of HTML invoice rendering. To accomplish this without breaking existing functionality, we separate the invoice calculation logic from the rendering logic. The program will then feature two methods: `toHTML()` and `toText()`, for generating invoices in different formats.

## Customer Management and Loyalty Points

We introduce a `Customer` object to replace the existing `customer` string. The `Customer` object includes:

- Customer name
- Customer UUID
- Fidelity point balance

A discount mechanism is also introduced. Customers with more than 150 loyalty points will receive a €15 discount on their invoice.

## Testing

### Unit Tests

Extensive unit tests are present to ensure the validity of each functional component.

### Validation Tests

Validation tests are included to verify that discounts and calculations are correctly applied, and that the new HTML and text invoice rendering methods are accurate.

## Tools Used

- **Build Automation**: Gradle
- **Testing Framework**: JUnit 5
