package ExtentReportManager;

import DriverSetup.Driver;
import Utils.DateUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReport{
    // For designing report in HTML.

    // This class object report will actually create report.
    public static ExtentReports report;

    public static ExtentReports getReportInstance() {
        if (report == null) {
            String reportName = DateUtils.getTimeStamp() + ".html";
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
                    System.getProperty("user.dir" + "\\Reports") + reportName);
            report = new ExtentReports();
            report.attachReporter(htmlReporter);

            report.setSystemInfo("OS", "Windows 10");
            report.setSystemInfo("Environment", "UAT");
            report.setSystemInfo("IDE", "IntelliJ Idea");
            report.setSystemInfo("Browser", "Chrome");

            htmlReporter.config().setDocumentTitle("Automation Results");
            htmlReporter.config().setReportName("Trivago results");

        }
        return report;
    }

}
