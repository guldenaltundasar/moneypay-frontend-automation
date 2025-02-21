MoneyPay Frontend Test Automation Project
=====================================
![frontendAutomation](https://github.com/user-attachments/assets/4bab2ffa-46c6-4a1a-a722-128bd01bbb02)

## Overview
This project is a test automation suite developed for Migros's website. It utilizes Selenium WebDriver 
for UI testing, focusing on product price sorting functionality in the Pet Shop category. 
The automation scripts are written in Java and use Cucumber framework for BDD test execution.

## Features
- Cross Browser Testing: Supports testing on both Chrome and Firefox browsers
- Page Object Model (POM): Implements POM design pattern for better maintainability
- Price Sorting: Verifies products can be sorted by price in ascending order
- Screenshot Capture: Automatically captures screenshots on test failures
- Cucumber Reporting: Generates detailed HTML and JSON test reports

### Prerequisites
- Java 11
- Maven
- Selenium WebDriver
- Cucumber
- WebDriverManager
- SLF4J

### Dependencies
The project uses Maven for dependency management. Key dependencies include:
- Selenium Java: 4.16.1 (Browser automation)
- WebDriverManager: 5.6.2 (Driver management)
- Cucumber: 7.14.0 (BDD framework)
- JUnit: 4.13.2 (Test execution)
- SLF4J: 2.0.7 (Logging)

### Installing and Running Tests
1. Clone the repository
2. Navigate to the project directory
3. Run tests using Maven:
   mvn clean test

## Project Structure

### Page Objects
• PetShopPage.java: Manages all Pet Shop page interactions including:
  - Cookie and popup handling
  - Category navigation
  - Product sorting
  - Price verification

### Core Classes
• BaseTest.java: Core test setup and utilities
  - WebDriver initialization
  - Screenshot capture
  - Browser management

### Test Classes
• PetShopSteps.java: Contains step definitions for test scenarios
  - Browser setup and teardown
  - Test execution steps
  - Assertions and verifications

### Test Scenarios
1. Pet Shop Price Sorting Verification
   - Open Migros website
   - Accept cookies and close popup
   - Navigate to Pet Shop category
   - Verify page navigation
   - Sort products by lowest price
   - Verify correct price sorting

## Reporting
- HTML reports are generated at: target/cucumber-reports/report.html
- JSON reports are generated at: target/cucumber-reports/report.json
- Failed scenarios are logged at: target/failed_scenarios.txt
- Screenshots are captured for failed tests in: screenshots/

## Error Handling
- Implements explicit waits for reliable element interactions
- Captures screenshots automatically on test failures
- Uses SLF4J for comprehensive logging
- Handles dynamic web elements with proper waits 
