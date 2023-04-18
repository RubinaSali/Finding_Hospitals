package pageClasses;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClasses;

public class TopCitiesPage extends PageBaseClasses{
	
	public TopCitiesPage(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(xpath="//li/div[(@class=\"u-margint--standard o-f-color--primary\")]")
	public List<WebElement> TopCities; 
	List<String> Top_Cities=new ArrayList<String>();  

	public void printCities() throws InterruptedException, IOException{
		logger = report.createTest("Top Cities");
		logger.log(Status.PASS, "clicked diagnostics");
		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
		try {
			System.out.println("===============================================");
			System.out.println("Top cities: ");
			System.out.println("===============================================");

	        takeScreenshot("TopCities");
			for (WebElement cities : TopCities) {
				Top_Cities.add(cities.getText());
			}
			System.out.println(Top_Cities);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			logger.log(Status.PASS, "list of top cities printed");
		}catch(Exception e) {
			logger.log(Status.FAIL, "failed");
	        takeScreenshot("TopCitiesFailed");
		}
		
		
		driver.navigate().back();
	}

}
