package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReport {
	public static ExtentReports report;

	public static ExtentReports getReportInstance() {

		if (report == null) {
			String reportName = DateUtils.getTimeStamp() + ".html";
			ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(
					System.getProperty("user.dir") + "/reports/" + reportName);
			report = new ExtentReports();
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 11");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Browser", "Mozilla/Edge");

			htmlReporter.config().setDocumentTitle("Automation Results");
			htmlReporter.config().setReportName("Finding Hospitals Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}

		return report;
	}
}



