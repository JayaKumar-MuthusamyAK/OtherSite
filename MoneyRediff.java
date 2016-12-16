package exercises;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MoneyRediff {

	//http://money.rediff.com/gainers/bse
	
	WebDriver driver;
	WebDriverWait wait;
	
	@Test
	public void Test1() throws InterruptedException
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("http://money.rediff.com/gainers/bse");
		
		// Give your Company Name: GeeCee Ventures
		
		String Chk_MyCompanyName="Rathi Bars Ltd.";
		String Price=null;
	
		
		
		List<WebElement> WebSite_Company_Name_List = driver.findElements(By.xpath("//*[@id='leftcontainer']/table/tbody/tr/td/a"));
		List<WebElement> WebSite_Price_List = driver.findElements(By.xpath("//*[@id='leftcontainer']/table/tbody/tr/td[4]"));
		
		System.out.println(WebSite_Company_Name_List.size());
		System.out.println(WebSite_Price_List.size());
		
		for(int com=0; com<WebSite_Company_Name_List.size();com++)
		{
			if(WebSite_Company_Name_List.get(com).getText().equals(Chk_MyCompanyName))
			{
				Price = WebSite_Price_List.get(com).getText();
				System.out.println("YOUR COMPANY NAME IS:"+Chk_MyCompanyName+" RESPECTIVE PRICE IS :"+Price);
			}
		}
		
		// Search box enter your company name:
		
		driver.findElement(By.xpath("//input[@id='srchword']")).sendKeys(Chk_MyCompanyName);
		driver.findElement(By.xpath("//input[@id='srchword']")).sendKeys(Keys.ENTER);
		
		// Verify the Page :
		
		String Opened_PageCompany_Name= driver.findElement(By.xpath("//*[@id='leftcontainer']/div[5]/h2/b")).getText();
		
		try
		{
	     Assert.assertEquals(Opened_PageCompany_Name, Chk_MyCompanyName);
	     
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		// verify the price is same as the once which you had read earlier from the grid
		
		String Current_Page_Price= driver.findElement(By.xpath("//span[@id='ltpid']")).getText();
		
		try
		{
		Assert.assertEquals(Current_Page_Price, Price);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
