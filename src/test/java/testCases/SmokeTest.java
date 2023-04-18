package testCases;

import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import baseClasses.PageBaseClasses;
import pageClasses.HospitalPage;
import pageClasses.LandingPage;
import pageClasses.ScheduleADemoFormPage;
import pageClasses.TopCitiesPage;
import utilities.ReadConfig;

public class SmokeTest extends PageBaseClasses{
	HospitalPage hospitals;
	LandingPage landingPage;
	ScheduleADemoFormPage form=new ScheduleADemoFormPage(driver);
	TopCitiesPage cities;
	ReadConfig readconfig;
	PageBaseClasses pageBase=new PageBaseClasses();
	
	public SmokeTest() {
        this.readconfig = new ReadConfig();
    }
	
	@BeforeTest
	public void test() {
		pageBase.invokeBrowsers(readconfig.getBrowser());
		landingPage=pageBase.OpenApplication(readconfig.getApplicationURL());

	}
	
	@Test
	public void hospitalTest() throws InterruptedException, IOException {
		hospitals=landingPage.hospital(readconfig.getCity(), readconfig.getSearch());
		hospitals.hospitalList();
	}
	
	@AfterTest
	public void closeBrowser() {
		hospitals.closeBrowser();
	}
	
}
