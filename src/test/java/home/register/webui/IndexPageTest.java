package home.register.webui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class IndexPageTest {
	
	@Test
	public void addNewMonthlyPaymentRecord() throws Exception {
		WebDriver driver = new HtmlUnitDriver();
		driver.get("http://localhost:8080/home.register/");
		driver.findElement(By.id("rent")).sendKeys("25000");
		driver.findElement(By.id("date")).sendKeys("20-Aug-1985");
		driver.findElement(By.id("photo")).sendKeys("/home/vilidmi/Pictures/Screenshot from 2016-08-29 21-07-15.png");
		driver.findElement(By.id("t1")).sendKeys("700.005");
		driver.findElement(By.id("t2")).sendKeys("612.340");
		driver.findElement(By.id("toPay")).sendKeys("3700");
		driver.findElement(By.id("SaveMonthlyPayment")).submit();
		
				
		assertEquals(driver.getPageSource()
				,"HTTP method POST is not supported by this URL"
				,driver.findElement(By.tagName("body")).getText());
	}

}
