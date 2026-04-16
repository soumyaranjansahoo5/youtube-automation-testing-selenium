package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports getReport() {

        String reportPath = System.getProperty("user.dir") + "/reports/index.html";

        // create reports folder automatically
        new java.io.File(System.getProperty("user.dir") + "/reports").mkdirs();

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Automation Report");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);

        // add system info (professional touch)
        extent.setSystemInfo("Tester", "Soumyaranjan");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));

        return extent;
    }
}