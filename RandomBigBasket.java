package exercises;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RandomBigBasket {

	// all :
	// standard links:
	 // //li/div/div[2]/div/div/div/label
	// Add button all links:
	// //div/ul/li/div/div[2]/div/a/span
	// Products Names links:7
	//li/div/span[2]/a/span
	WebDriver driver;
	WebDriverWait wait;
	@Test
	public void Test1() throws InterruptedException {
		FirefoxProfile prof = new FirefoxProfile();
		prof.setEnableNativeEvents(true);
		driver = new FirefoxDriver(prof);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		
		//Step 1 : a) Go to http://bigbasket.com/
		
		
		driver.get("http://www.bigbasket.com/pb/amul/?nc=nb#!page=2");
		
		driver.findElement(By.xpath("//*[@id='skip_explore']")).click();
		
		//driver.findElement(By.xpath("//button[@id='skip_explore']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='facetsContainer']/div[5]/ul/li/label")).click();
		
		
		//driver.findElement(By.xpath("//*[@id='basket_menu']/ul/li[9]/a")).click();
		
		//driver.findElement(By.xpath("//*[@id='filter_brands_list']/div/div[1]/li[10]/label")).click();
		//li/div[3]/span[2]/a
		
		int Add_CartSelection=5;
		
		List<WebElement> list_Of_Products = driver.findElements(By.xpath("//*[@class='uiv2-shopping-lis-sku-cards']/ul/li/div/span/a"));
        
		System.out.println("TOTAL LINKS IN PAGE :"+list_Of_Products.size());
		
		
		
		list_Of_Products = driver.findElements(By.xpath("//*[@class='uiv2-shopping-lis-sku-cards']/ul/li/div/span/a"));
		List<WebElement> Add_Btns = driver.findElements(By.xpath("//*[@class='uiv2-shopping-lis-sku-cards']/ul/li/div/div/div/a/span"));
		
		System.out.println(Add_Btns.size());
		Actions act = new Actions(driver);
		Random random = new Random();
		for(int i=0;i<list_Of_Products.size();i++)
		{
			System.out.println(list_Of_Products.get(i).getAttribute("innerHTML"));
			
		}
		
		// Now U want Array list :
		
		List<String> Add_cart_UserSelection = new ArrayList<>();
		act.moveToElement(list_Of_Products.get(list_Of_Products.size()-1)).build().perform();
		for(int i=0;i<Add_CartSelection;i++)
		{
			//list_Of_Products = driver.findElements(By.xpath("//div[@class='uiv2-shopping-list-wrapper']/div[2]/div[7]/div[2]/div/div/ul/li/div/span[2]/a"));
			//Add_Btns = driver.findElements(By.xpath("//div[@class='uiv2-shopping-list-wrapper']/div[2]/div[7]/div[2]/div/div/ul/li/div/div[2]/div/a"));
			list_Of_Products = driver.findElements(By.xpath("//*[@class='uiv2-shopping-lis-sku-cards']/ul/li/div/span/a"));
			Add_Btns = driver.findElements(By.xpath("//*[@class='uiv2-shopping-lis-sku-cards']/ul/li/div/div/div/a/span"));
			//System.out.println(list_Of_Products.get(i).getText());
			int Number = random.nextInt(list_Of_Products.size());
			act.moveToElement(list_Of_Products.get(Number)).build().perform();
			System.out.println("********************************************************");
			Thread.sleep(3000);
			System.out.println("Added Product Name is :"+list_Of_Products.get(Number).getText().replace("AMUL", "").replace("MEAT", "").trim());
			Add_cart_UserSelection.add(list_Of_Products.get(Number).getText().replace("AMUL", "").replace("MEAT", "").trim());
			Thread.sleep(5000);
			Add_Btns.get(Number).click();
			
		}
		Thread.sleep(5000);
		// Top (scroll up) if it is not present 
		if(driver.findElements(By.xpath("html/body/div[19]/div[3]/a")).size()!=0)
		{
			driver.findElement(By.xpath("html/body/div[19]/div[3]/a")).click();
		}
		
		act.moveToElement(driver.findElement(By.xpath("//span[@class='uiv2-num-basket-items']"))).build().perform();
		String Website_AddCartTotal = driver.findElement(By.xpath("//span[@class='uiv2-num-basket-items']")).getText().replace("items", "").trim();
		int Website_AddCartTotal1 = Integer.parseInt(Website_AddCartTotal);
		
		try
		{
		Assert.assertEquals(Website_AddCartTotal1, Add_CartSelection);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		// Verify the all products added correctly or Not?
		
		System.out.println("***********************************************************");
		act.moveToElement(driver.findElement(By.xpath("//div[@class='uiv2-basket-items']"))).build().perform();
		List<WebElement> Add_Cart_Baskets = driver.findElements(By.xpath("//div[@class='uiv2-items-content-wrapper']/div/p/a"));
		
		for(int j=0; j<Add_Cart_Baskets.size();j++)
		{
			System.out.println(Add_Cart_Baskets.get(j).getText().replace("AMUL", "").replace("MEAT", "").trim());
			if(Add_Cart_Baskets.get(j).getText().replace("AMUL", "").replace("MEAT", "").trim().contains(Add_cart_UserSelection.get(j)))
			{
				System.out.println("VERIFIED THE PRODUCTS ->>>>>"+Add_Cart_Baskets.get(j).getText());
			}
			else
			{
				System.out.println("PRODUCT IS NOT MATCHING PLEASE CHECK THE CART AND PRODUCT LIST...");
			}
					
		}
	
	}
}
