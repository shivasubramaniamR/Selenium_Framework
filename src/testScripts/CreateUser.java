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
import pageObjects.OrangeHRMLogin;
import utilities.Base;
import utilities.CustomListeners;
import utilities.ProjectUtils;

@Listeners(CustomListeners.class)
public class CreateUser extends Base {
	OrangeHRMLogin login;
	OrangeHRMCreateUser cuser;
	Base b ;
	ProjectUtils pu;
	public String screenshotFilePath="" ;
	public String reportspath="" ;
	public String URL="";
	public String user_url="";
	public String intialReport = "";
	
	
	
	
	
	
	@BeforeMethod
	public void load(ITestResult result) throws IOException {
		b = new Base();
		screenshotFilePath = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"screenshots"+File.separator).replace("\\", "/") ;
		reportspath=(System.getProperty("user.dir")+File.separator+"src"+File.separator+"libraries"+File.separator+"reports"+File.separator).replace("\\", "/") ;
		URL = b.getMapData("URL",1);
		user_url=b.getMapData("user_url",1);
		intialReport=(System.getProperty("user.dir")+File.separator+"test-output"+File.separator+"OrangeHRM-emailable-report-template.html").replace("\\", "/") ;
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
	public void orageHRM_create_user() {
		
		try {
				
		login = new OrangeHRMLogin(b.returnDriver());
		cuser = new OrangeHRMCreateUser();
		pu = new ProjectUtils();
		b.loadURL(URL);
		Reporter.log("<br>Step 2 : Url Loaded------PASS");
		
		login.login(b.getMapData("username", 1),b.getMapData("password", 1));
		Reporter.log("<br>Step 3 : Application logged in------PASS");
		
		
		b.selectDropDown(cuser.User_Role,b.getMapData("user_role",1));
		Reporter.log("<br>Step 4 : User Role Selected------PASS");
		
		b.setText(cuser.emp_name, "e");
		String empName = pu.emp_name(pu.getempNames(cuser.emp_names_list));
		b.setText(cuser.emp_name, empName);
		Reporter.log("<br>Step 5 : Employee Name Entered------PASS");
		
		String uname = b.randomString(empName.length()).trim();
		b.setText(cuser.usernmae, uname);
		Reporter.log("<br>Step 6 : User Name Entered------PASS");
		
		b.selectDropDown(cuser.emp_status, b.getMapData("emp_status", 1));
		Reporter.log("<br>Step 7 : Employee Status Selected------PASS");
		
		String pass = b.randomStringPassword(empName, 9).trim();
		b.setText(cuser.password, pass);
		Reporter.log("<br>Step 8 : Password  Entered------PASS");
		
		b.setText(cuser.confirm_password, pass);
		Reporter.log("<br>Step 9 : Confirm Password  Entered------PASS");
		
		b.click(cuser.save);
		Reporter.log("<br>Step 10 : Save Button Clicked------PASS");
		
		b.isElementPresent(cuser.save);
		b.ElementWait(cuser.search_username);
		b.setText(cuser.search_username, uname);
		Reporter.log("<br>Step 11 : Username  Entered in search Field------PASS");
		
		b.click(cuser.search);
		Reporter.log("<br>Step 12 : Search button Clicked------PASS");
		
		String Currentusername = b.getText(cuser.get_username);
		Assert.assertEquals(Currentusername, uname, "Validation of Username after Save");
		b.screenShot(screenshotFilePath, this.getClass().getName());
		Reporter.log("<br>Step 13 : Validating the Username after Save------PASS");
		
		b.writeToExcel(2, uname, pass);
		
		}catch (Exception e) {
			Reporter.log("<br>Test Step Failed------FAIL");
			Assert.fail(e.getMessage());
			
		}
		
	}
	
	
	
	

}
