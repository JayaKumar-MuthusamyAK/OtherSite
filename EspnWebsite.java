package com;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class EspnWebsite {

	WebDriver driver = null;
	
	@Test
	public void CricketScore(){
		
		//http://www.espncricinfo.com/icc-womens-championship-2014-16/engine/match/946549.html
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://www.espncricinfo.com/icc-womens-championship-2014-16/engine/match/946549.html");
		
		// Icc word cup women serious page opened or Not?
		
	    String seriesName = driver.findElement(By.xpath("//div[@class='match-information-strip']")).getText();
		System.out.println(seriesName);
		
		// Identify the valuable xpath : //*[@id='full-scorecard']/div[2]/div/table[1]/tbody/tr/td[4]
		
		List<WebElement> Score = driver.findElements(By.xpath("//*[@id='full-scorecard']/div[2]/div/table[1]/tbody/tr/td[4]"));
	
		int GetTot =CalcultingScore(Score);
		System.out.println("Calculated Total is :"+GetTot);
		
		String Total = driver.findElement(By.xpath("//*[@id='full-scorecard']/div[2]/div/table[1]/tbody/tr[15]/td[4]/b")).getText();
		int ConTotal = Integer.parseInt(Total);
		
		VerificationTotal(ConTotal,GetTot);
		
	}


	private void VerificationTotal(int conTotal, int getTot) {
		
		if(conTotal==getTot)
			System.out.println("Total is Correct");
		else
			System.out.println("Total is incorrect");
		// Here u can use assert command also..
		
	}


	private int CalcultingScore(List<WebElement> score) {
		int i = 0,sum =0;
		while(i<score.size()-1)
		{
			System.out.println(score.get(i).getText());
			String x = score.get(i).getText();
			int scr = Integer.parseInt(x);
			sum = sum+scr;
			i++;
		}
		//System.out.println(sum);
		return sum;
	}
}
