package exercises;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Dice_KeywordSearch {

	WebDriver driver;
	WebDriverWait wait;
	@Test
	public void Search_Box() throws InterruptedException, IOException {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.get("http://dice.com");
		
		driver.findElement(By.xpath("//input[@id='search-field-keyword']")).sendKeys("Java");
		
		driver.findElement(By.xpath("//input[@id='search-field-location']")).clear();
		
		driver.findElement(By.xpath("//input[@id='search-field-location']")).sendKeys(Keys.ENTER);
		
		List<WebElement> element_List = driver.findElements(By.xpath("//div[@id='serp']/div/h3/a[starts-with(@onclick,'cookieJobID')]"));
		
		System.out.println(element_List.size());
		//  Get the whole search keyword title..
		// Pagination xpath with text:
		
		int j=2;
		while(isPresent_Btn("//div[@id='dice_paging_top']/ul/li/a[text()='"+j+"']"))
		{
			
			System.out.println(j+"ST PAGE LINK IS :");
			System.out.println("****************************************************");
			for(int i=0; i<element_List.size();i++)
			{
				element_List = driver.findElements(By.xpath("//div[@id='serp']/div/h3/a[starts-with(@onclick,'cookieJobID')]"));
				System.out.println(element_List.get(i).getText());
				if(element_List.get(i).getText().contains("Java"))
				{
					System.out.println("It is Reflected on the Title");
				}
				else 
				{
					System.out.println("Not reflected on the title ");
				}
				System.out.println();
			}
			driver.findElement(By.xpath("//div[@id='dice_paging_top']/ul/li/a[text()='"+j+"']")).click();
			Thread.sleep(7000);
			if(j==5)
				break;
			else
			j++;
		}
		
		
		
		
	}
	private boolean isPresent_Btn(String string) {
		int Number = driver.findElements(By.xpath(string)).size();
		if(Number==0)
		return false;
		else
			return true;
	}
}
