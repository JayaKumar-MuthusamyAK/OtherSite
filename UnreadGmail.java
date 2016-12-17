package exercises;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.net.SyslogAppender;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class UnreadGmail {

	WebDriver driver;
	WebDriverWait wait;
	@Test
	public void Inbox() throws InterruptedException {
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		driver.get("http://gmail.com");
		
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("jakay507@gmail.com");
		driver.findElement(By.xpath("//*[@id='Email']")).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys("***********");
		driver.findElement(By.xpath("//*[@id='Passwd']")).sendKeys(Keys.ENTER);
		
		List<WebElement> list_ofMsgs= driver.findElements(By.xpath("//*[@class='yW']/span"));
		
		System.out.println(list_ofMsgs.size());
		
		for(int i=0;i<list_ofMsgs.size();i++)
		{
			System.out.println(list_ofMsgs.get(i).getText());
		}
		
		// Unread Message:
		
		//*[@class='yW']/span[@class='zF']
		
		//*[@class='yW']/span[@class='yP']  Read..
	
		
		// List of Read Messages :
		
		List<WebElement> ReadMsgs = driver.findElements(By.xpath(isReadMsgPresent("//*[@class='yW']/span[@class='yP']")));
		
		System.out.println("********************************************************************");
		System.out.println("READ MESSAGES LISTS");
		
			for(int k =0; k< ReadMsgs.size(); k++)
			{
				System.out.println(ReadMsgs.get(k).getText());
			}
			
			//*[@class='yW']/span[@class='yP']
			
			
			// First get the unread messages list:
			
			List<WebElement> UnReadMsgs = driver.findElements(By.xpath("//*[@class='yW']/span[@class='zF']"));
			System.out.println("********************************************************************");
			System.out.println("UNREAD MESSAGES LISTS");
			int j=0;
			while(j<UnReadMsgs.size())
			{
				System.out.println("Open Message Name is :"+UnReadMsgs.get(j).getText());
				UnReadMsgs.get(j).click();
				j++;
				break;
			}
			
		
			
	}
	
	
	private String isReadMsgPresent(String string) 
	{
		
		if(driver.findElements(By.xpath(string)).size()!=0)
		return string;
		else
		return "Not Present Readable Message";
	}
	
	

}
