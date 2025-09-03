
# DemoQA TestNG Framework

This is a robust automation testing framework built using **Selenium WebDriver**, **TestNG**, **ExtentReports**, **Log4j**, and **WebDriverManager**. It supports **parallel execution**, **remote execution**, **screenshot capture on failure**, and **CI/CD integration with GitHub Actions**.

## 🚀 Features

- **Selenium WebDriver with Java**
- **TestNG** for test execution and configuration
- **ExtentReports** for rich HTML reporting
- **Log4j** for logging test execution
- **WebDriverManager** for automatic driver management
- **DriverManager Singleton** with ThreadLocal for parallel-safe WebDriver instances
- **Screenshot capture** on test failure
- **Local and Remote execution** support
- **Parallel execution** via TestNG and Maven
- **GitHub Actions** workflow for CI/CD

## 📁 Project Structure

```
demoqa-testng-framework/
├── src/main/java/
│   ├── config/
│   ├── driver/
│   ├── reports/
│   ├── utils/
├── src/main/resources/
├── test/java/
│   ├── base/
│   ├── tests/
├── .github/workflows/
├── Screenshots/
├── Reports/
├── logs/
├── testng.xml
├── pom.xml
├── .gitignore
└── README.md
```

## 🧪 How to Run Tests

### Using IntelliJ:
- Right-click on `testng.xml` → **Run** or **Debug**

### Using Maven:
```bash
mvn clean test -Dbrowser=chrome -DisRemote=false
```

### Parallel Execution:
Defined in `testng.xml`:
```xml
<suite name="Parallel Suite" parallel="tests" thread-count="2">
```

## 🔧 GitHub Actions CI/CD

- Workflow file: `.github/workflows/testng-ci.yml`
- Runs tests on Chrome and Firefox in parallel
- Uploads ExtentReports and screenshots as artifacts

## 📸 Screenshot on Failure
Automatically captured and saved in `Screenshots/` directory.

## 📄 Reports
Generated HTML reports saved in `Reports/ExtentReport.html`

---

Happy Testing! 🎯
