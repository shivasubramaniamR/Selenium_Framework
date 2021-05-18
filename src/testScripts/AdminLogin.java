package testScripts;

import java.io.File;
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

	OrangeHRMLogin login;
	Base b ;
	public String screenshotFilePath="";
	public String reportspath="";
	public String URL="";
	public String intialReport = "";
	
	
	
	
	
	@BeforeMethod
	public void load(ITestResult result) throws IOException {
		b = new Base();
		screenshotFilePath = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"screenshots"+File.separator).replace("\\", "/") ;
		reportspath=(System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"reports"+File.separator).replace("\\", "/") ;
		URL = b.getMapData("URL", 0);
		intialReport=(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"Orangehrm-emailable-report-template.html").replace("\\", "/") ;
		System.out.println(intialReport);
		
		intialize("chrome");
		Reporter.setCurrentTestResult(result);
		Reporter.log("Step 1 : Browser Initialized------PASS");
		
		
		
		
		
	}
	
	@AfterMethod
	public void cleanup(ITestResult result) {
		driver.quit();
		Reporter.setCurrentTestResult(result);
		Reporter.log("<br>Browser Closed------PASS");

	}
	
	@Test(enabled=true)
	public void orageHRM_admin_login() {
		
		try {
				
		login = new OrangeHRMLogin(b.returnDriver());
		b.loadURL(URL);
		Reporter.log("<br>Step 2 : Url Loaded------PASS");
		
		
		login.login(b.getMapData("username", 1),b.getMapData("password", 1));
		Reporter.log("<br>Step 3 : Application logged in------PASS");
		
		b.ElementWait(login.welcome_msg);
		Assert.assertEquals(b.getText(login.welcome_msg),"Welcome Admin");
		
		b.screenShot(screenshotFilePath, this.getClass().getName());
		Reporter.log("<br>Step 4 : Validating the Welcome Message------PASS");
		
		}catch (Exception e) {
			Reporter.log("<br>Test Step Failed------FAIL");
			Assert.fail(e.getMessage());
			
			
		}
		
	}
	

	
	
	
	

}
