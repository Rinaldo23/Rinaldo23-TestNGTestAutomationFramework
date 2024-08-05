# TestNGTestAutomationFramework

## Overview

TestNGTestAutomationFramework is a comprehensive test automation framework designed for web application testing using Selenium WebDriver and TestNG. It utilizes the Page Object Model (POM) and data-driven testing principles to create a maintainable and scalable test suite.

## Features

- **Data-Driven Testing**: Leverage TestNG's data providers for flexible test case inputs.
- **Page Object Model (POM)**: Organizes web elements and interactions in dedicated page classes.
- **Configurable Properties**: Manage configuration through the `config.properties` file.
- **Test Categorization**: Organize tests into groups like "smoke" and "regression" for efficient execution.

## Project Structure

```
DataDrivenTestAutomationFramework/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── qa/
│   │               ├── base/
│   │               │   └── BasePage.java
|   |               │   └── BaseTest.java
│   │               ├── interactions/
│   │               │   └── PageInteraction.java
│   │               ├── listeners/
│   │                   ├── TestListener.java
│   │               ├── pages/
│   │               │   ├── LoginPage.java
│   │               │   └── HomePage.java
│   │               │   └── AccountPage.java
│   │               │   └── RegisterPage.java
│   │               │   └── PageDependencies.java
│   │               └── utility/
│   │                   ├── DataGenerator.java
│   │                   └── DataSupplier.java
|   |  
│   └── test/
│       └── java/
│           └── com/
│               └── qa/
│                   └── test/
│                       ├── LoginTestCase.java
│                       ├── package-info.java
│                       ├── RegisterTestCase.java
│                       └── SearchProductTestCase.java
└── src/
    └── test/
        └── resources/
            ├── Configuration/
            │   └── config.properties
            ├── ScreenShots/
            │   └── subFolderName
            └── XMLFolder/
               ├── dataprovider.xml
               ├── groupdependencies.xml
               ├── grouping.xml
               ├── listeners.xml
               ├── parallel.xml
               └── parameters.xml
```

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Apache Maven**: For project build and dependency management
- **Selenium WebDriver**: For browser automation
- **TestNG**: For test management and execution
- **WebDriverManager**: For automatic driver management

### Installation

1. **Clone the repository:**
   ```sh
   git clone https://github.com/Rinaldo23/TestNGTestAutomationFramework.git
   cd TestNGTestAutomationFramework
   ```

2. **Set up WebDriver:**
   Ensure WebDriverManager or the appropriate browser driver (e.g., ChromeDriver) is configured. WebDriverManager can handle driver binaries automatically.

3. **Configure Properties:**
   Update the `config.properties` file with the required details:
   ```properties
   baseUrl=https://demo-opencart.com/
   ```

4. **Run the Tests:**
   Execute the test cases using Maven:
   ```sh
   mvn test
   ```

## Usage

- **Test Cases**: Located in the `com.qa.testcases` package. Each test case class extends `BaseTest` to utilize common setup and teardown methods.
- **Page Objects**: The `com.qa.pages` package contains page classes with methods for interacting with web elements.
- **Utilities**: The `com.qa.utility` package includes helper classes for data generation and data-driven testing.

## Test Case Scenarios

### 1. Login Test Case

Tests various Login scenarios:
- Login with valid details.
- Login with Invalid details.
- Validating "ForgotPassword" link present on Login Page.

### 2. Register Test Case

Tests various registration scenarios:
- Registering with valid details.
- Registering with newsletter subscription.
- Registering with no details provided, verifying error messages.

### 3. Search Product Test Case

Tests product search functionality:
- Searching for an existing product.
- Searching for a non-existent product.
- Searching without entering a product name.

## Contributing

Contributions are welcome! Please open a pull request or issue to discuss any proposed changes.

## License

This project is licensed under the MIT License.

## Contact

For any questions or issues, please contact rinaldobadigar@gmail.com.
