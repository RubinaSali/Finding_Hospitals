package pageClasses;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.Status;

import baseClasses.PageBaseClasses;

public class HospitalPage extends PageBaseClasses{
	public HospitalPage(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(xpath="//div[@class='inner']/child::div[1]")
	public List<WebElement> box; 

	@FindBy(xpath="//div[@class='text-1']/span[1] /parent::div[1]/parent::div[1]/parent::div[1]/preceding-sibling::div[1]/div/a")
	public List<WebElement> Hsp;
	
	@FindBy(xpath="//div/a[(@class=\"line-1\")]")
	public List<WebElement> HospitalNames; 
	
	@FindBy(xpath="//div/span[(@class=\"uv2-spacer--lg-left\")]")
	public List<WebElement> Time; 
	@FindBy(xpath="//div[@class='text-1']//span[1]")
	public List<WebElement> Ratings; 
	
	List<Float> rate=new ArrayList<Float>();  
	List<String> Hospital=new ArrayList<String>();  

	List<String> hsptltime=new ArrayList<String>();  
	List<String> hsptlrate=new ArrayList<String>();  

	public void hospitalList() throws InterruptedException, IOException{
		logger = report.createTest("Finding Hospital Names");
		int y=0;

		
		logger.log(Status.INFO, "searched with city bangalore and hospital");
		try {
			for(int i=0;i<2;i++) {
	   			JavascriptExecutor js = (JavascriptExecutor) driver;
	   	   		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	   			try {
	   				Thread.sleep(2000);
	   			} catch (InterruptedException e) {
	   				e.printStackTrace();
	   			}
	   			js.executeScript("window.scrollBy(0,-700)");
	   			try {
	   				Thread.sleep(2000);
	   			} catch (InterruptedException e) {
	   				e.printStackTrace();
	   			}
	   		}
			takeScreenshot("Hospitals");
			System.out.println("Total hospitals : "+Hsp.size());
			
			
//			full ratings
			for (WebElement R : Ratings) {
//				System.out.println("rating is "+R.getText());
				float r = Float.parseFloat(R.getText());
				rate.add(r);	
			}
			
			
//			Full hopitals
			for (WebElement H : Hsp) {
//				System.out.println("hospital name is "+H.getText());
				Hospital.add(H.getText());
			}
			
			
//			filter - based on rating
			System.out.println("===============================================");
			System.out.println("Filtering based on Rating>3.5: ");
			System.out.println("===============================================");
			for(int i=0;i<rate.size();i++)
			{
				if(rate.get(i)>3.5)
				{
					System.out.println("rating is "+rate.get(i)+" name is "+Hospital.get(i));
					hsptlrate.add(Hospital.get(i));				
				}
			}
			System.out.println("size : "+hsptlrate.size());
		
			
//			Filtering based on 24/7
			System.out.println("===============================================");
			System.out.println("Hospitals that are open 24/7 : ");
			System.out.println("===============================================");

			for (WebElement time : Time) {
				y++;
				if(time.getText().equalsIgnoreCase("MON - SUN 00:00AM - 11:59PM")) {
					System.out.println(HospitalNames.get(y-1).getText());
					hsptltime.add(HospitalNames.get(y-1).getText());
					continue;
				}
			}
			System.out.println("size : "+hsptltime.size());
  

		}catch(Exception e) {
			logger.log(Status.FAIL, "failed");
			takeScreenshot("HospitalFailed");
		}
		

		List<String> commonElements = new ArrayList<String>(hsptltime);
		commonElements.retainAll(hsptlrate);
		
		System.out.println("===============================================");
		System.out.println("Hospitals after filtering based on 24/7 and rating>3.5 : ");
		System.out.println("===============================================");

		for(String c:commonElements) {
			System.out.println(c);
		}
		System.out.println("size : "+ commonElements.size());
		
		
		logger.log(Status.PASS, "list of hospitals printed");
		driver.navigate().back();
		
	}
	
}
