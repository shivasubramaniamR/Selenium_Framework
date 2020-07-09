package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrangeHRMLogin {
	
	WebDriver driver;
	
	By userName = By.id("txtUsername");
	By password = By.id("txtPassword"); 
	By login = By.name("Submit");
	public By welcome_msg = By.xpath("//a[contains(text(),'Welcome')]");
	
	
	public OrangeHRMLogin(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void login(String uname,String pwd) {
		driver.findElement(userName).sendKeys(uname);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(login).click();
		
	}

}
