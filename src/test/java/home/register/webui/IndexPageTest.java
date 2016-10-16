package home.register.webui;

import static org.junit.Assert.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class IndexPageTest {
	
	private WebDriver driver = new HtmlUnitDriver(BrowserVersion.FIREFOX_38,true);
	
	
	@Rule
	public final TestRule failurHandler = new TestRule() {
		
		@Override
		public Statement apply(final Statement base,final Description arg1) {
			// TODO Auto-generated method stub
			return new Statement() {
				
				@Override
				public void evaluate() throws Throwable {
					try{
						base.evaluate();
					}catch (Throwable e) {
						BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(new File("./" + arg1.getMethodName())));
						try{
							buffer.write(driver.getPageSource().getBytes());
						} finally {
							buffer.close();
						}
						throw e;
					}
					
				}
			};
		}
	};
	
	@Test
	public void addNewMonthlyPaymentRecord() throws Exception {
		
		driver.get("http://localhost:8080/home.register/");
		driver.findElement(By.id("rent")).sendKeys("25000");
		driver.findElement(By.id("date")).sendKeys("20-Aug-1985");
		driver.findElement(By.id("photo")).sendKeys("/home/vilidmi/Pictures/Screenshot from 2016-08-29 21-07-15.png");
		driver.findElement(By.id("t1")).sendKeys("700.005");
		driver.findElement(By.id("t2")).sendKeys("612.340");
		driver.findElement(By.id("toPay")).sendKeys("3700");
		
		driver.findElement(By.id("saveMonthlyPayment")).submit();
		
		driver.get("http://localhost:8080/home.register/");
		List<WebElement> row = driver.findElements(By.cssSelector("#monthlyPaymentList tbody tr td"));
		assertEquals("25000", row.get(0).getText());
		assertEquals("20-Aug-1985", row.get(1).getText());
		assertEquals("http://localhost:8080/home.register/image/1", row.get(2).findElement(By.cssSelector("img")).getAttribute("src"));
		assertEquals("700.005", row.get(3).getText());
		assertEquals("612.340", row.get(4).getText());
		assertEquals("3700", row.get(5).getText());
		
	}

}
