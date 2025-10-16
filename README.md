# 🚀 Selenium + RestAssured Test Automation Framework (Java)

This is a **ready-to-use automation framework** built using:
- **Selenium WebDriver** for UI testing  
- **RestAssured** for API testing
- **TestNG** as the test runner
- **Allure** for beautiful reports
- **Page Object Model (POM)** design pattern

## ⚙️ Prerequisites

Before running the framework, ensure you have the following installed on your system:

| Tool | Version | Description |
|------|----------|-------------|
| **Java JDK** | 17 or later | Required to compile and run the project |
| **Apache Maven** | 3.8+ | For managing dependencies and running tests |
| **Allure Commandline** | Latest | To generate and view HTML reports |
| **Web Browser** | Chrome / Firefox | For Selenium tests execution |

### 🔧 Verify installations
Run these commands in your terminal or PowerShell:
```bash
java -version
mvn -version
allure --version
```

## 🚀 Quick Start

### 1. Clone or Unzip
```bash
cd selenium-restassured-framework-v2
```

### 2. Run Tests ()
```bash
mvn test                                # for running all the tests
mvn test -Dtest=SauceDemoTest           # for running the UI tests
mvn test -Dtest=UserApiTest             # for running API tests
```

### 3. Generate & View Allure Report
```bash
allure serve allure-results
```

## ⚙️ Configuration
Edit `src/test/resources/config.properties`:

```
baseUrl=https://www.saucedemo.com/
browser=chrome   # or firefox
```

## 🧩 Folder Structure
```
├── src
│   ├── main/java/com/framework/base
│   ├── main/java/com/framework/pages
│   ├── test/java/com/framework/tests/ui
│   ├── test/java/com/framework/tests/api
│   └── test/resources/config.properties
├── testng.xml
├── pom.xml
└── README.md
```

---
Author: **Syed Khizer Ahmed**
