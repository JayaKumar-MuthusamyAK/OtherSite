package com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmericanGolf {

	WebDriver driver= null;
	@Test
	public void Sum_Projects() throws InterruptedException
	{
		//.//*[@id='secondary']/div/div[4]/div/ul/li/a/span[2]
		
		// url : http://www.americangolf.co.uk/clothing-shoes/shirts
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://www.americangolf.co.uk/clothing-shoes/shirts");
		
		List<WebElement> Products = driver.findElements(By.xpath("//*[@id='secondary']/div/div[4]/div/ul/li/a/span[2]"));
		
		System.out.println(Products.size());
		
		int sum =0;
		for(int i=0;i<Products.size();i++)
		{
			String add = Products.get(i).getText();
			String add1 = add.replaceAll("[()]", "");
			int n = Integer.parseInt(add1);
			//System.out.println(Products.get(i).getText());
			sum = sum+n;
		}
		System.out.println(sum);
		boolean s =verifyProductCount(sum);
		System.out.println(s);
		
		WebElement slide1 = driver.findElement(By.xpath("//*[@id='secondary']/div[1]/div[2]/div[1]/div[1]/span[1]"));
		WebElement slide2 = driver.findElement(By.xpath("//*[@id='secondary']/div[1]/div[2]/div[1]/div[1]/span[2]"));
		SlideBarCheck(slide1,slide2);
	}
	private void SlideBarCheck(WebElement slide1, WebElement slide2) throws InterruptedException {
		
		Actions act = new Actions(driver);
		act.dragAndDropBy(slide1, 50, 0).build().perform();
		//Thread.sleep(4000);
		WebDriverWait wait1 = new WebDriverWait(driver, 10);
		//*[@id='primary']/div[2]/div[1]/div[1]/span[text()='55']
		wait1.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//*[@id='primary']/div[2]/div[1]/div[1]/span"),"204"));
				
		slide2 = driver.findElement(By.xpath("//*[@id='secondary']/div[1]/div[2]/div[1]/div[1]/span[2]"));	
		act.dragAndDropBy(slide2, (-50), 10).build().perform();
	}
	
	private boolean verifyProductCount(int sum) {
		
		String test = driver.findElement(By.xpath("//*[@id='primary']/div[2]/div[1]/div[1]/span")).getText();
		int s = Integer.parseInt(test);
		if(s==sum)
			return true;
		else
			return false;	
	}
}
