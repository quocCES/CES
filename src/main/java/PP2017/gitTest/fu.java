package PP2017.gitTest;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class fu {
	
	String baseUrl = "http://www.adactin.com/HotelApp/index.php";
    WebDriver driver = new ChromeDriver();
	
	
	@Test
	public void SearchHotel_001() {
		// SearchHotel_001: Verify that an error message is showed after user try to search hotel with "checkout" date is before "checkin" date.

		System.setProperty("webdriver.chrome.driver", "/home/quoccao/eclipse-workspace/chromedriver");
	    driver.get(baseUrl);
        
        //1. Login A_hotel app with a valid account (nguyenmoon/mun12345).
        
        driver.findElement(By.id("username")).sendKeys("nguyenmoon");
        driver.findElement(By.id("password")).sendKeys("mun12345");
        driver.findElement(By.id("login")).click();
        
		//2. In the "Search Hotel" form, select "Sydney" as location.
		Select dropdownContry =  new Select (driver.findElement(By.id("location")));
		dropdownContry.selectByVisibleText("Sydney");
		
		//3. Input value of "checkout" date is before "checkin" date. 
		//(e.g. out: 19/09/2017; in: 22/09/2017).
		
		WebElement txt_in = driver.findElement(By.id("datepick_in"));
		WebElement txt_out = driver.findElement(By.id("datepick_out"));
		
		txt_in.clear();
		txt_out.clear();
		txt_in.sendKeys("22/09/2017");
		txt_out.sendKeys("19/09/2017");
				
		//4. Click on "Search" button.
		driver.findElement(By.id("Submit")).click();
		
		String observedMes = driver.findElement(By.id("checkout_span")).getText();
		String expectedMes = "Check-Out Date shall be after than Check-In Date";
		
		
		//Expected: An error message should show.
		assertEquals(observedMes, expectedMes);
		
	}
  

	@AfterTest
	public void afterTest() {
		
		//driver.close();
	}
}
