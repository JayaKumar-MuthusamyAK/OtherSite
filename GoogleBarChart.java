package exercises;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import net.sf.saxon.trans.XPathException.Circularity;

public class GoogleBarChart {

	WebDriver driver;
	WebDriverWait wait;
	@Test
	public void Test1() throws InterruptedException
	{
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://developers.google.com/chart/interactive/docs/gallery/combochart");
		
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		System.out.println("Total Frames :"+frames.size());
		
		driver.switchTo().frame(0);
		// Way 1: 
		//https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/6441
		
		String Heading = driver.findElement(By.xpath("//*[local-name()='svg']/*[local-name()='g']/*[local-name()='text']")).getText();
		
		System.out.println("Pi_CHART HEADING IS :"+Heading);
		
		// List of test xpath : //*[local-name()='svg']/*[local-name()='g']/*[local-name()='g']/*[local-name()='g']/*[local-name()='text']
		

		List<WebElement> Cities_Lst = driver.findElements(By.xpath("//*[local-name()='svg']/*[local-name()='g']"
				+ "/*[local-name()='g']/*[local-name()='g']/*[local-name()='text'][@text-anchor='start']"));
		
		System.out.println("--------------------------------------------------------------");
		
		
		System.out.println("THIS PI_CHART CONTAINS THE FOLLOWING CITY NAMES :");
		
		for(int j=0; j<Cities_Lst.size()-1;j++)
		{
			System.out.println(Cities_Lst.get(j).getText());
		}
		
		System.out.println("--------------------------------------------------------------");
		
		
		
		// Get the List of month in the chart
		
		System.out.println("***********MONTH OF CHART************");
		//*[local-name()='svg']/*[local-name()='g'][3]/*[local-name()='g'][3]/*[local-name()='g']/*[local-name()='text'][@text-anchor='middle']
		
		List <WebElement> Month_List = driver.findElements(By.xpath("//*[local-name()='svg']/*[local-name()='g'][3]/*[local-name()"
				+ "='g'][3]/*[local-name()='g']/*[local-name()='text'][@text-anchor='middle']"));
		
		for(int j=0; j<Month_List.size();j++)
		{
			System.out.println(Month_List.get(j).getText());
		}
		
		
		
		
		System.out.println("--------------------------------------------------------------");
		
		// Get the List of cups in the chart:
		System.out.println("***********CUPS OF CHART************");
		
		List<WebElement> Cups_List= driver.findElements(By.xpath("//*[local-name()='svg']/*[local-name()='g'][3]/*[local-name()='g']"
				+ "[3]/*[local-name()='g']/*[local-name()='text'][@text-anchor='end']"));
		//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr[1]/td[6]
		//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr[1]/td
		List<WebElement> Each_FirstcityProduction1=driver.findElements(By.xpath("//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr[1]/td"));
		
		
		for(int j=0; j<Cups_List.size();j++)
		{
			System.out.println(Cups_List.get(j).getText());
		}
		
		for(int l=2;l<=Each_FirstcityProduction1.size()-1;l++)
		{
			int Var1 = Integer.parseInt(driver.findElement(By.xpath("//div[@aria-label='A tabular representation"
					+ " of the data in the chart.']/table/tbody/tr[1]/td["+l+"]")).getAttribute("innerHTML").replaceAll(",", "").trim());
			if(Var1<Integer.parseInt(Cups_List.get(1).getText().replaceAll(",", "")))
			{
				System.out.println(Var1+"--->>>>>>>>>"+"It is inside "+Cups_List.get(1).getText());
			
			}
			else if(Var1<Integer.parseInt(Cups_List.get(2).getText().replaceAll(",", "")))
			{
				System.out.println(Var1+"--->>>>>>>>>"+"It is inside "+Cups_List.get(2).getText());
				
			}
			else if(Var1<Integer.parseInt(Cups_List.get(3).getText().replaceAll(",", "")))
			{
				System.out.println(Var1+"--->>>>>>>>>"+"It is inside "+Cups_List.get(3).getText());
				
			}
			else if(Var1<Integer.parseInt(Cups_List.get(4).getText().replaceAll(",", "")))
			{
				System.out.println(Var1+"--->>>>>>>>>"+"It is inside "+Cups_List.get(4).getText());
			
			}
			
		}
		
	
		System.out.println("--------------------------------------------------------------");
		
		
		// Get the Each and every month individual sales cups in city based:
	
		
		List<WebElement> City_Names = driver.findElements(By.xpath("//div[@aria-label='A tabular representation of the data in the chart.']/table/thead/tr/th"));
		List<WebElement> MonthlyBased= driver.findElements(By.xpath("//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr/td[1]"));
		int totalCity= City_Names.size()-1;
		
		List<WebElement> Averages = driver.findElements(By.xpath("//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr/td[7]"));
		int i =2;
		
		System.out.println(totalCity);
		while(i<=totalCity)
		{
		System.out.println(City_Names.get(i-1).getAttribute("innerHTML").toUpperCase());
		System.out.println("----------------------------------------------------------");
		List<WebElement> Products_Sales=driver.findElements(By.xpath("//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr/td["+i+"]"));
		for(int k=0;k<Products_Sales.size();k++)
		{
			System.out.print(MonthlyBased.get(k).getAttribute("innerHTML")+"->");
			System.out.print(Products_Sales.get(k).getAttribute("innerHTML")+"---");
			System.out.println();
		}
		i++;
		}
		System.out.println("**********************************************************");
		totalCity= City_Names.size();
		
		// calculations :
		
		
			//div[@aria-label='A tabular representation of the data in the chart.']/table/tbody/tr[1]/td
			
		for(int cal=0;cal<Averages.size();cal++)
		{
			Double sum =0.0;
			List<WebElement> EachCity_Values = driver.findElements(By.xpath("//div[@aria-label='A tabular "
					+ "representation of the data in the chart.']/table/tbody/tr["+(cal+1)+"]/td"));
			for(int rem=1;rem<EachCity_Values.size()-1; rem++)
			{
				String Text =EachCity_Values.get(rem).getAttribute("innerHTML").replaceAll(",", "").trim();
				Double Calculate = Double.parseDouble(Text) ;
				sum= Calculate+sum;
			}
			System.out.println("Actual values:"+sum/5);
			System.out.println("Exppected values :"+Averages.get(cal).getAttribute("innerHTML"));
			
			Double ExpectedAvg = Double.parseDouble(Averages.get(cal).getAttribute("innerHTML"));
			Double ActualAvg = sum/5;
			try{
			Assert.assertEquals(ActualAvg, ExpectedAvg);}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			
						
		}
		driver.switchTo().defaultContent();
		}
		
		
}


// unWanted codes:
/*
 List<WebElement> CheckingIndiv = driver.findElements(By.xpath("//*[local-name()='svg']/*"
				+ "[local-name()='g'][3]/*[local-name()='g'][1]/*[local-name()='g'][2]/*[local-name()='rect']"));
				
for(int y =0;y<CheckingIndiv.size();y++)
{
	Point point = CheckingIndiv.get(y).getLocation();
	int xYard = point.getX();
	int yYard = point.getY();
	System.out.println(xYard+"--"+yYard);
	System.out.println(CheckingIndiv.get(y).getLocation());
}
*/