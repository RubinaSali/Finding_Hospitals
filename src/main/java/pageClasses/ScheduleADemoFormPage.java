package pageClasses;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClasses;

public class ScheduleADemoFormPage extends PageBaseClasses {
	public ScheduleADemoFormPage(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(id="name")
	public WebElement name_textBox;
	
	@FindBy(id="organizationName")
	public WebElement Orgname_textBox;
	
	@FindBy(id="contactNumber")
	public WebElement ContactNo_textBox;
	
	@FindBy(name="officialEmailId")
	public WebElement email_textBox;
	
	@FindBy(name="organizationSize")
	public WebElement size_drpdown;
	
	@FindBy(xpath="/html/body/div[1]/div/div/header[1]/div[2]/div/form/div[5]/select/option[2]")
	public WebElement size;
	
	@FindBy(name="interestedIn")
	public WebElement interest_drpdown;
	
	@FindBy(xpath="/html/body/div[1]/div/div/header[1]/div[2]/div/form/div[6]/select/option[3]")
	public WebElement interest;
	
	@FindBy(xpath="/html/body/div[1]/div/div/header[1]/div[2]/div/form/button")
	public WebElement Submit;
	
	public void invalidDetails(String name,String organizationName, String contactNumber, String officialEmailId ) throws InterruptedException, IOException{
		logger = report.createTest("Fill Invalid Details And Print Alert Message");
		logger.log(Status.PASS, "clicked Plans");
		try {
			name_textBox.sendKeys(name);
			Orgname_textBox.sendKeys(organizationName);
			ContactNo_textBox.sendKeys(contactNumber);
			email_textBox.sendKeys(officialEmailId);
			logger.log(Status.PASS, "Invalid details entered successfully");
	        
	        try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			size_drpdown.click();
			size.click();
			interest_drpdown.click();
			interest.click();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, 80)");
			if(Submit.isEnabled()){
				Submit.click();
			}else {
				logger.log(Status.INFO, "'Schedule a demo' button cannot be clicked as the details entered are invalid ");
			}
	        
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}catch(Exception e) {
			logger.log(Status.FAIL, "failed");
	        takeScreenshot("formFailed");

		}
		}
		public void alertPresent() throws IOException {
			try {
				Alert alt=driver.switchTo().alert();
			}catch(NoAlertPresentException e) {
				logger.log(Status.FAIL, "No alert appeared");
				takeScreenshot("AlertFail");
			}finally {
				driver.navigate().back();
			}
			
		}
		


 //IF INVALID DETAILS ENTERED, Schedule a demo button is not enabled hence error message won't appear and cannot be printed.
}
