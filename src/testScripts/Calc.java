package testScripts;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


public class Calc {
	
	@Test
	public  void cal() throws AWTException {
		System. setProperty("webdriver.chrome.driver","E:\\workspace\\CaseStudy\\src\\libraries\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions().setHeadless(true);
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("online Calculator");
		
		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		driver.findElement(By.xpath("//table[@class='ElumCf']//div[contains(text(),'5')]")).click();
		driver.findElement(By.xpath("//table[@class='ElumCf']//div[contains(text(),'3')]")).click();
		driver.findElement(By.xpath("//table[@class='ElumCf']//div[contains(text(),'+')]")).click();
		driver.findElement(By.xpath("//table[@class='ElumCf']//div[contains(text(),'4')]")).click();
		driver.findElement(By.xpath("//table[@class='ElumCf']//div[contains(text(),'7')]")).click();
		driver.findElement(By.xpath("//table[@class='ElumCf']//div[contains(text(),'=')]")).click();
		
		
		String result = driver.findElement(By.xpath("//span[@jsname='VssY5c']")).getText();
		System.out.println(result);
		
	}

}
