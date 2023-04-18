package pageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import baseClasses.PageBaseClasses;

public class LandingPage extends PageBaseClasses {
	
	public LandingPage(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div/div/div/div[1]/div/input")
	public WebElement cityName;
	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div/div/div/div[1]/div[1]/span[2]/span/i")
	public WebElement clr;
	@FindBy(xpath="/html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div[2]/div[1]")
	public WebElement cityNameSel;
	

	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div/div/div/div[2]/div/input")
	public WebElement search_textbox;
	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/div/div/div/div[2]/div[2]/div[1]/div[1]/span[2]/span")
	public WebElement sel_hsptl;
	
	
	//1. For Bangalore city, identify Hospitals that is Open 24/7, has Parking facility with rating more than 3.5; Display the hospital names
	public HospitalPage hospital(String city,String search) {
		cityName.click();
		clr.click();
		cityName.sendKeys(city);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		cityNameSel.click();
		search_textbox.sendKeys(search);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		sel_hsptl.click();
	
//		search_textbox.sendKeys(Keys.ENTER);
		return PageFactory.initElements(driver,HospitalPage.class);

	}
	
	
	// 2. In Diagnostics page, pick all the top cities name & store in a List; Display the same
	@FindBy(linkText="Book Diagnostic Tests")
	public WebElement Diagnostics;
	
	public TopCitiesPage clickDiagnostics() {
		Diagnostics.click();
		return PageFactory.initElements(driver, TopCitiesPage.class);
	}
	
    // 3. Go to Corporate Wellness, fill invalid details, schedule & capture the warning message from the alert
	@FindBy(xpath="/html/body/div[1]/div/div/div[1]/div[1]/div[2]/div/div[3]/div[1]/span/span[2]")
	public WebElement Corporates;
	
	@FindBy(linkText="Health & Wellness Plans")
	public WebElement plans;
	
	
	public ScheduleADemoFormPage clickPlans() {
		Corporates.click();
		plans.click();
		return PageFactory.initElements(driver, ScheduleADemoFormPage.class);
	}
	
}
