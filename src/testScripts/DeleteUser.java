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
	//WebDriver driver;
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
	public void orageHRM_delete_user() {
		
		try {
				
		login = new OrangeHRMLogin(b.returnDriver());
		cuser = new OrangeHRMCreateUser();
		euser = new OrangeHRMEditUser();
		duser = new OrangeHRMDeleteUser();
		
		b.loadURL(URL);
		Reporter.log("<br>Url Loaded");
		
		login.login(b.getMapData("username", 1),b.getMapData("password", 1));
		Reporter.log("<br>Application logged in");
		

		
		b.click(euser.Admin);
		
		b.setText(cuser.search_username, b.getCellValueFromExcel(TestDataSheet, 2, 0));
		
		b.click(cuser.search);
		
		
		b.ElementWait(cuser.get_username);

		
		b.javascriptElemClick(duser.checkbox);
		
		b.ElementWait(duser.delete);		
		b.click(duser.delete);
		
		b.click(duser.ok);
		
		b.setText(cuser.search_username, b.getCellValueFromExcel(TestDataSheet, 2, 0));
		
		b.ElementWait(cuser.search);
		
		b.click(cuser.search);
		
		int count = b.webtablecount(duser.table_count);
		
		System.out.println(count);
		
		Assert.assertEquals(count,2);
		
		
		b.screenShot(screenshotFilePath, this.getClass().getName());
		Reporter.log("<br>Validating the Username  after Deletion");
		
		
		
		}catch (Exception e) {
			Assert.fail(e.getMessage());
			
		}
		
	}
	
	
	
	

}
