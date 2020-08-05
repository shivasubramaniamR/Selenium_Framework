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
	//WebDriver driver;
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
		Reporter.log("Browser Initialized");
		
		
		
	}
	
	@AfterMethod
	public void cleanup(ITestResult result) {
		driver.quit();
		Reporter.setCurrentTestResult(result);
		Reporter.log("<br>Browser Closed");

	}
	
	@Test(enabled=true)
	public void orageHRM_create_user() {
		
		try {
				
		login = new OrangeHRMLogin(b.returnDriver());
		cuser = new OrangeHRMCreateUser();
		pu = new ProjectUtils();
		b.loadURL(URL);
		Reporter.log("<br>Url Loaded");
		
		login.login(b.getMapData("username", 1),b.getMapData("password", 1));
		Reporter.log("<br>Application logged in");
		

		
		b.selectDropDown(cuser.User_Role,b.getMapData("user_role",1));
		
		b.setText(cuser.emp_name, "e");
		String empName = pu.emp_name(pu.getempNames(cuser.emp_names_list));
		b.setText(cuser.emp_name, empName);
		System.out.println(empName);
		
		String uname = b.randomString(empName.length()).trim();
		
		b.setText(cuser.usernmae, uname);
		System.out.println(uname);
		
		b.selectDropDown(cuser.emp_status, b.getMapData("emp_status", 1));
		String pass = b.randomStringPassword(empName, 9).trim();
		
		b.setText(cuser.password, pass);
		System.out.println(pass);
		
		b.setText(cuser.confirm_password, pass);
		
		b.click(cuser.save);
		b.isElementPresent(cuser.save);
		
		b.ElementWait(cuser.search_username);
		
		b.setText(cuser.search_username, uname);
		
		b.click(cuser.search);
		String Currentusername = b.getText(cuser.get_username);
		
		Assert.assertEquals(Currentusername, uname, "Validation of Username after Save");
		b.screenShot(screenshotFilePath, this.getClass().getName());
		Reporter.log("<br>Validating the Username after Save");
		
		b.writeToExcel(2, uname, pass);
		
		}catch (Exception e) {
			Assert.fail(e.getMessage());
			
		}
		
	}
	
	
	
	

}
