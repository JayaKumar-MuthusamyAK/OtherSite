package exercises;

import java.util.ArrayList;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Cathay_Pacific {

	WebDriver driver;
	WebDriverWait wait;
	@Test
	public void Test1() throws InterruptedException {
		
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("javascript.enabled", true);
		driver = new FirefoxDriver(profile);
		
		//System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		//driver = new ChromeDriver();
	    wait = new WebDriverWait(driver, 60);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("http://www.cathaypacific.com/");
		
		List<String> compare_amount = new ArrayList<String>();
		//driver.findElement(By.xpath("//*[@id='change-region']/i")).click();
		
		//driver.findElement(By.xpath("//div[@class='regions-wrapper']/ul/li/a[text()='India - English']")).click();
		
		
		// Fill the all flight details:
		
		driver.findElement(By.xpath("//*[@id='book-trip-tab-panel']/div/div/form/fieldset[1]/div/label[2]")).click();
		
		driver.findElement(By.xpath("//*[@id='depart-label']")).clear();
		driver.findElement(By.xpath("//*[@id='depart-label']")).sendKeys("Cape");
		driver.findElement(By.xpath("//*[@id='ui-id-1']/li[1]")).click();
		
		driver.findElement(By.xpath("//*[@id='destination-label']")).clear();
		driver.findElement(By.xpath("//*[@id='destination-label']")).sendKeys("Del");
		driver.findElement(By.xpath("//*[@id='ui-id-2']/li[1]")).click();
		
		driver.findElement(By.xpath("//span[@class='date-selected from has-date']")).click();
		
		// All radio button links : //div[@id='business']/form[2]/table/tbody[2]/tr/td[8]/span
		//button[@class='button-date-picker field-button from-button has-dates input-filled']
		
		String Your_Date ="22-July 2016";
		
		String Str[] = Your_Date.split("-");
		String Day = Str[0];
		String Month_year = Str[1];
		
		System.out.println(Day+"--->"+Month_year);
		
		WebElement Web_SiteMonth_Year1= driver.findElement(By.xpath("//div/div[1]/table/caption"));
		WebElement Web_SiteMonth_Year2= driver.findElement(By.xpath("//div/div[2]/table/caption"));
		
		if(Month_year.equals(Web_SiteMonth_Year1.getText()))
		{
			List<WebElement> Web_SiteDay =driver.findElements(By.xpath("//div/div[1]/table/tbody/tr/td/a"));
			//driver.findElement(By.xpath("//*[@id='dp1468316214182']/div/div[1]/table/tbody/tr/td/a[text()='']")).click();
			for(int i=0; i<Web_SiteDay.size();i++)
			{
			if(Day.equals(Web_SiteDay.get(i).getText()))
			{
				Web_SiteDay.get(i).click();
				break;
			}
			}
		}
		else if(Month_year.equals(Web_SiteMonth_Year2.getText()))
		{
			List<WebElement> Web_SiteDay =driver.findElements(By.xpath("//div/div[2]/table/tbody/tr/td/a"));
			//driver.findElement(By.xpath("//*[@id='dp1468316214182']/div/div[1]/table/tbody/tr/td/a[text()='']")).click();
			for(int i=0; i<Web_SiteDay.size();i++)
			{
			if(Day.equals(Web_SiteDay.get(i).getText()))
			{
				Web_SiteDay.get(i).click();
				break;
			}
			}
		}
		
		
		driver.findElement(By.xpath("//*[@id='book-trip-tab-panel']/div/div/form/button")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='body']/div[2]/div[2]/div/div/div/div[3]/div[1]/input")));
		
		driver.findElement(By.xpath("//*[@id='body']/div[2]/div[2]/div/div/div/div[3]/div[1]/input")).click();
		Thread.sleep(5000);
		
		// get the list of radio buttons:
		
		List<WebElement> list_ofRadioBtn = driver.findElements(By.xpath("//div[@id='business']/form[2]/table/tbody[2]/tr/td[8]/span"));
		
		
		for(int i=0; i<list_ofRadioBtn.size();i++)
		{
			list_ofRadioBtn.get(i).click();
			
			driver.findElement(By.xpath("//*[@id='buttons']/div/input")).click();
			Thread.sleep(4000);
			String Total_Amount = driver.findElement(By.xpath("//*[@id='fareAmount']/div/div/span")).getText();
			compare_amount.add(Total_Amount);
			driver.navigate().back();
			list_ofRadioBtn = driver.findElements(By.xpath("//div[@id='business']/form[2]/table/tbody[2]/tr/td[8]/span"));
		}
		
		System.out.println(compare_amount.size());
		
	}
	}
