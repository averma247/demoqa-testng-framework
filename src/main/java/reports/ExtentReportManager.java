package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void initReports() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/ExtentReport.html");
        spark.config().setDocumentTitle("Test Report");
        spark.config().setReportName("Build Report");
        spark.config().setTheme(Theme.STANDARD);
        spark.config().setEncoding("utf-8");
        spark.config().setTimelineEnabled(true);
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("User", System.getProperty("user.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");


        extent.attachReporter(spark);
    }

    public static void flushReports() {
        extent.flush();
    }

    public static void createTest(String testName) {
        test.set(extent.createTest(testName));
    }

    public static ExtentTest getTest() {
        return test.get();
    }

}