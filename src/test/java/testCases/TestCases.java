package testCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClasses;
import pageClasses.HospitalPage;
import pageClasses.LandingPage;
import pageClasses.ScheduleADemoFormPage;
import pageClasses.TopCitiesPage;
import utilities.ReadConfig;
import utilities.TestDataProvider;

public class TestCases extends PageBaseClasses{
	HospitalPage hospitals;
	LandingPage landingPage;
	ScheduleADemoFormPage form;
	TopCitiesPage cities;
	ReadConfig readconfig;


	public TestCases() {
        this.readconfig = new ReadConfig();
    }
	
	PageBaseClasses pageBase=new PageBaseClasses();
	@BeforeTest
	public void test() {
		
		logger=report.createTest("Finding Hospitals");

		pageBase.invokeBrowsers(readconfig.getBrowser());
		logger.log(Status.INFO, "Opening Browser");
		
		landingPage=pageBase.OpenApplication(readconfig.getApplicationURL());
		logger.log(Status.INFO, "Opened Practo.com");
	}
	
	//1. For Bangalore city, identify Hospitals that is Open 24/7, has Parking facility with rating more than 3.5; Display the hospital names
	@Test(priority=1)
	public void HospitalPageTest() throws InterruptedException, IOException{
		
		hospitals=landingPage.hospital(readconfig.getCity(), readconfig.getSearch());
		hospitals.hospitalList();
	}
		
	// 2. In Diagnostics page, pick all the top cities name & store in a List; Display the same
	@Test(priority=2)
	public void topCitiesTest() throws InterruptedException, IOException {
		cities=landingPage.clickDiagnostics();
		cities.printCities();
	}

    // 3. Go to Corporate Wellness, fill invalid details, schedule & capture the warning message from the alert
	@Test(dataProvider="getInputData",priority=3)
	public void formTest(Hashtable<String,String> testData) throws InterruptedException, IOException{
		form=landingPage.clickPlans();
		form.invalidDetails(testData.get("name"),testData.get("organizationName"),testData.get("contactNumber"),testData.get("officialEmailId"));
		form.alertPresent();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@AfterTest
	public void closeBrowser() {
	   form.closeBrowser();
	    
	}

	@DataProvider
	public Object[][] getInputData(){
		return TestDataProvider.getTestData("FindingHospitals.xlsx","FindingHospital","INPUTS");
	}
	

}
