package com;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class MakeMytripCalender {

	WebDriver driver;
	@Test
	public void GetWindowsIDS() throws ParseException
	{
		//.//*[@id='ui-datepicker-div']/div[3]/div/div //*[@id='ui-datepicker-div']/div[2]/div/div
		//System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		
		driver.navigate().refresh();
		driver.findElement(By.xpath("//*[@id='start_date_sec']/span[3]")).click();
		
		//driver.findElement(By.xpath("//*[@id='start_date_sec']/span[1]/span[3]")).click();
		
		//driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
		//*[@id='ui-datepicker-div']/div[1]/div/a/span
		selectDate("27/2/2017");
		
	}
	private void selectDate(String date) throws ParseException {
		
		SimpleDateFormat SF = new SimpleDateFormat("dd/MM/yyyy");
		Date dateToBeSelected = SF.parse(date);
		
		String Month =new SimpleDateFormat("MMMM").format(dateToBeSelected);
		String Year =new SimpleDateFormat("yyyy").format(dateToBeSelected);
		String Day = new SimpleDateFormat("d").format(dateToBeSelected);
		int CheckYears = Integer.parseInt(Year);
		
		Date currentDate = new Date();
		String CurrentDateYear = new SimpleDateFormat("yyyy").format(currentDate);
		int Year2 = Integer.parseInt(CurrentDateYear);
		int NextYear =Year2+1;
		
		System.out.println(NextYear);
		
		String WebSiteMonthYeartoBeSelected = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/div")).getText();
		String WebSiteMonthYeartoBeSelected1= driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/div")).getText();
		// Website month and year get printed.
		System.out.println(WebSiteMonthYeartoBeSelected);
		System.out.println(WebSiteMonthYeartoBeSelected1);
		String GivenDate = Month+" "+Year;
		//our given date month and year is printed.
		System.out.println(GivenDate);
		System.out.println(Day);
		System.out.println("CHECK THE ONE YEARS MONTHS ONLY PRESENT AND GET PRINTED>>>> MONTH AND YEARS");
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		
		
		while(true)
		{
			if(WebSiteMonthYeartoBeSelected.equals(GivenDate))
				
			{
				//System.out.println(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/div")).getText());
				String GetSelectedDate = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table//a[text()='"+Day+"']")).getText();
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table//a[text()='"+Day+"']")).click();
				System.out.println("Given Date Selected Successfully!!!");
				
				System.out.println(GetSelectedDate+"--"+WebSiteMonthYeartoBeSelected);
				break;
			}
			else if((WebSiteMonthYeartoBeSelected1.equals(GivenDate)))
			{
				//System.out.println(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/div")).getText());
				String GetSelectedDate = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/table//a[text()='"+Day+"']")).getText();
				
				driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]//a[text()='"+Day+"']")).click();
				System.out.println("Given Date Selected Successfully!!!");
				
				System.out.println(GetSelectedDate+"--"+WebSiteMonthYeartoBeSelected1);
				break;
			}
			else
			{
				String Year1 = new SimpleDateFormat("YYYY").format(currentDate);
				int incrementYear = Integer.parseInt(Year1);
				if(CheckYears==NextYear || (CheckYears==incrementYear))
				{
					
				if(dateToBeSelected.after(currentDate))
				{
					driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/a/span")).click();
					System.out.println(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/div")).getText());
				}
				else
				{
					driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/a/span")).click();
					System.out.println(driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/div")).getText());
				}
				
			}
				else
				{
					System.out.println("YOUR DATE IS INVALID...IT IS PAST DATE>>CHANGE FUTURE DATE!!!");
					break;
				}
			
			WebSiteMonthYeartoBeSelected = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/div")).getText();
			WebSiteMonthYeartoBeSelected1= driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/div")).getText();
		}
		
		
	}
}
}
