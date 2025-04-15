package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

  static ExtentReports extentReport;

  public static ExtentReports getExtentReport() {

    String extentReportPath = System.getProperty("user.dir") + "\\reports\\extentreport.html";
    ExtentSparkReporter reporter = new ExtentSparkReporter(extentReportPath);
    reporter.config().setReportName("Booking.com Automation Results");
    reporter.config().setDocumentTitle("Test Results");

    extentReport = new ExtentReports();
    extentReport.attachReporter(reporter);
    extentReport.setSystemInfo("Operating System", "Windows 11");
    extentReport.setSystemInfo("Tested By", "Hashan(E2145313)");

    return extentReport;
  }
}
