package baseClasses;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


import pageClasses.LandingPage;
import utilities.DateUtils;
import utilities.ExtentReport;

public class PageBaseClasses {
	
	public WebDriver driver;
	public ExtentReports report=ExtentReport.getReportInstance();
	public ExtentTest logger;
	
	
	
	public void invokeBrowsers(String browserName) {
		try {
			if(browserName.equalsIgnoreCase("mozilla")){
				System.setProperty("webdriver.gecko.driver","D:\\eclipse\\FindingHospitals\\drivers\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			else if(browserName.equalsIgnoreCase("edge")){
				System.setProperty("webdriver.edge.driver","D:\\eclipse\\StoreNameMandatoryCheck\\driver\\msedgedriver.exe");
				driver=new EdgeDriver();
		}
	}catch (Exception e) {
		System.out.println(e.getMessage());
	}
//		log=Logger.getLogger("FindingHospitals");
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(70));
		driver.manage().window().maximize();

	}
	public LandingPage OpenApplication(String url) {
		driver.get(url);
		return PageFactory.initElements(driver, LandingPage.class);
	}
	public void takeScreenshot(String name) throws IOException {
	    TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
	    File destFile = new File(System.getProperty("user.dir") + "/Screenshots/" + name + DateUtils.getTimeStamp() + ".png");
	    FileUtils.copyFile(sourceFile, destFile);
	    if (logger != null) {
	        logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "/Screenshots/" + name + DateUtils.getTimeStamp() + ".png");
	    } else {
	        System.out.println("logger object is null");
	    }
	}

	public void closeBrowser() {
		driver.quit();
	}
	
	@AfterMethod
	public void afterMethod() {
	    
	    report.flush();
	}

	
	
}
