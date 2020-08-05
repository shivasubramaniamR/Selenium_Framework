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
	//WebDriver driver;
	OrangeHRMLogin login;
	Base b ;
	public String screenshotFilePath="";
	public String reportspath="";
	public String URL="";
	public String intialReport = "";
	
	
	
	
	
	@BeforeMethod
	public void load(ITestResult result) throws IOException {
		b = new Base();
//		screenshotFilePath = System.getProperty("user.dir")+"\\src\\libraries\\screenshots\\" ;
		screenshotFilePath = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"screenshots"+File.separator).replace("\\", "/") ;
//		screenshotFilePath = b.getMapData("screenshotFilePath", 0);
//		System.out.println("checking"+screenshotFilePath);
		reportspath=(System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"reports"+File.separator).replace("\\", "/") ;
//		reportspath = System.getProperty("user.dir")+"\\src\\libraries\\reports\\" ;
		URL = b.getMapData("URL", 0);
		intialReport=(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"OrangeHRM-emailable-report-template.html").replace("\\", "/") ;
//		intialReport = System.getProperty("user.dir")+"\\test-output\\OrangeHRM-emailable-report-template.html";
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
