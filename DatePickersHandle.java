package com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class DatePickersHandle {

	WebDriver driver;
	@Test
	public void Test1() throws InterruptedException
	{
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		driver.get("http://makemytrip.com");
		
		driver.navigate().refresh();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='start_date_sec']/span[3]")).click();
		
		String My_Date = "10-October 2016";
		String Str1[] = My_Date.split("-");
		String Day = Str1[0];
		String Month_Year =Str1[1];
		
		System.out.println(Month_Year);
		System.out.println(Day);
		
		SelectDate(Month_Year,Day);
		
		System.out.println(driver.findElement(By.xpath("//*[@id='start_date_sec']/span[4]")).getText());
		System.out.println("From Date is Selected Successfully!!!");
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id='return_date_sec']/span[3]")).click();
		
		String My_Date1 = "16-October 2016";
		String Str2[] = My_Date1.split("-");
		String Day1 = Str2[0];
		String Month_Year1 =Str2[1];
		
		SelectDate(Month_Year1,Day1);
		
		}
	private void SelectDate(String month_Year, String day) 
	{
			
		try
		{
		String Web_Month_year = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[2]/div/div")).getText();
	    String Web_Month_Year2 = driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/div")).getText();
		if(month_Year.equals(Web_Month_year))
		{
			List<WebElement> DayLists= driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[2]/table/tbody/tr/td/a"));
			
			for(int i=0;i<DayLists.size();i++)
			{
				
			   if(day.equals(DayLists.get(i).getText()))
				{
					DayLists.get(i).click();
					break;
				}
			  
			}
		}
		else if(month_Year.equals(Web_Month_Year2))
		{
			List<WebElement> DayLists2= driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[3]/table/tbody/tr/td/a"));
			for(int j=0;j<DayLists2.size();j++)
			{
				
			  if(day.equals(DayLists2.get(j).getText()))
				{
				DayLists2.get(j).click();
				break;
				}
			}
	     }

			else if(driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/a[@data-event='click']")).size()!=0)
			{
			driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/a/span")).click();
			driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div[3]/div/a/span")).click();
			SelectDate(month_Year, day);
			}
			else
			{
				System.out.println("Minimum 1 years dates should be allowed. Please check your dates");
			}
			
	}
		catch(Exception e)
		{
			e.getMessage();
		}
	}
}
