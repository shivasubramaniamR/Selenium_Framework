package testScripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.OrangeHRMLogin;
import utilities.Base;
import utilities.CustomListeners;

@Listeners(CustomListeners.class)
public class AdminLogin extends Base {
	//WebDriver driver;
	OrangeHRMLogin login;
	Base b ;
	public String screenshotFilePath="" ;
	public String reportspath="" ;
	public String URL="";
	public String intialReport = "";
	
	
	
	
	
	@BeforeMethod
	public void load(ITestResult result) throws IOException {
		b = new Base();
		screenshotFilePath = b.getMapData("screenshotFilePath", 0);
		System.out.println("checking"+screenshotFilePath);
		reportspath = b.getMapData("reportspath", 0);
		URL = b.getMapData("URL", 0);
		intialReport = b.getMapData("intialReport", 0);
		intialize("chrome");
		Reporter.setCurrentTestResult(result);
		Reporter.log("Browser Initialized");
		
		
		
	}
	
	@AfterMethod
	public void cleanup(ITestResult result) {
		driver.quit();
		Reporter.setCurrentTestResult(result);
		Reporter.log("<br>Browser Closed");
//		b.fileMove(intialReport, reportspath,this.getClass().getName());
	}
	
	@Test(enabled=true)
	public void orageHRM_admin_login() {
		
		try {
				
		login = new OrangeHRMLogin(b.returnDriver());
		b.loadURL(URL);
		Reporter.log("<br>Url Loaded");
		
		
		login.login(b.getMapData("username", 1),b.getMapData("password", 1));
		Reporter.log("<br>Application logged in");
		
		b.ElementWait(login.welcome_msg);
		Assert.assertEquals(b.getText(login.welcome_msg),"Welcome Admin");
		
		b.screenShot(screenshotFilePath, this.getClass().getName());
		Reporter.log("<br>Validating the Welcome Message");
		
		}catch (Exception e) {
			Assert.fail(e.getMessage());
			
		}
		
	}
	

	
	
	
	

}
