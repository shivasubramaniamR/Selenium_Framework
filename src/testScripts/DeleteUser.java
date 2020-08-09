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

import pageObjects.OrangeHRMCreateUser;
import pageObjects.OrangeHRMDeleteUser;
import pageObjects.OrangeHRMEditUser;
import pageObjects.OrangeHRMLogin;
import utilities.Base;
import utilities.CustomListeners;


@Listeners(CustomListeners.class)
public class DeleteUser  extends Base{
	OrangeHRMLogin login;
	OrangeHRMCreateUser cuser;
	OrangeHRMEditUser euser;
	OrangeHRMDeleteUser duser;
	Base b ;
	
	public String screenshotFilePath="" ;
	public String reportspath="" ;
	public String URL="";
	public String user_url="";
	public String intialReport = "";
	public String TestDataSheet = "";
	
	
	
	
	
	@BeforeMethod
	public void load(ITestResult result) throws IOException {
		b = new Base();
		screenshotFilePath = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"screenshots"+File.separator).replace("\\", "/") ;
		reportspath=(System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"reports"+File.separator).replace("\\", "/") ;
		URL = b.getMapData("URL",0);
		user_url=b.getMapData("user_url",1);
		intialReport=(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"OrangeHRM-emailable-report-template.html").replace("\\", "/") ;
		TestDataSheet = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"testData"+File.separator+"TestDataSheet.xlsx").replace("\\", "/") ;
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
	public void orageHRM_delete_user() {
		
		try {
				
		login = new OrangeHRMLogin(b.returnDriver());
		cuser = new OrangeHRMCreateUser();
		euser = new OrangeHRMEditUser();
		duser = new OrangeHRMDeleteUser();
		
		b.loadURL(URL);
		Reporter.log("<br>Step 2 : Url Loaded------PASS");
		
		login.login(b.getMapData("username", 1),b.getMapData("password", 1));
		Reporter.log("<br>Step 3 : Application logged in------PASS");
		

		
		b.click(euser.Admin);
		Reporter.log("<br>Step 4 : Admin button Clicked------PASS");
		
		b.setText(cuser.search_username, b.getCellValueFromExcel(TestDataSheet, 2, 0));
		Reporter.log("<br>Step 5 : Username entered in search Field------PASS");
		
		b.click(cuser.search);
		Reporter.log("<br>Step 6 : Search button clicked------PASS");
		
		b.ElementWait(cuser.get_username);
		b.ElementWaitClickable(duser.checkbox);
		Reporter.log("<br>Step 7 : Checkbox clicked------PASS");
		
		b.ElementWait(duser.delete);		
		b.click(duser.delete);
		Reporter.log("<br>Step 8 : Delete Button clicked------PASS");
		
		b.click(duser.ok);
		Reporter.log("<br>Step 9 : Ok Prompt Button clicked------PASS");
		
		b.setText(cuser.search_username, b.getCellValueFromExcel(TestDataSheet, 2, 0));
		Reporter.log("<br>Step 10 : username entered in search field------PASS");
		
		b.ElementWait(cuser.search);
		b.click(cuser.search);
		Reporter.log("<br>Step 11 : Search Button Clicked------PASS");
		
		int count = b.webtablecount(duser.table_count);
		Assert.assertEquals(count,2);
		b.screenShot(screenshotFilePath, this.getClass().getName());
		Reporter.log("<br>Step 12 : Validating the Username  after Deletion------PASS");
		
		
		
		}catch (Exception e) {
			Reporter.log("<br>Test Step Failed------FAIL");
			Assert.fail(e.getMessage());
			
		}
		
	}
	
	
	
	

}
