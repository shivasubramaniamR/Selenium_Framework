package pageObjects;

import org.openqa.selenium.By;


public class OrangeHRMCreateUser {
	
	public By User_Role = By.xpath("//select[@id='systemUser_userType']");
	public By emp_name = By.xpath("//li[label[contains(text(),'Employee Name')]]/input[1]");
	public By emp_names_list = By.xpath("//div[@class='ac_results']//li");
	public By usernmae = By.xpath("//input[@id='systemUser_userName']");
	public By emp_status = By.xpath("//select[@id='systemUser_status']");
	public By password = By.xpath("//input[@id='systemUser_password']");
	public By confirm_password = By.xpath("//input[@id='systemUser_confirmPassword']");
	public By save = By.xpath("//input[@id='btnSave']");
	public By search_username = By.xpath("//input[@id='searchSystemUser_userName']");
	public By search = By.xpath("//input[@id='searchBtn']");
	public By get_username = By.xpath("//table[@id='resultTable']//td[2]");
	public By get_username1 = By.xpath("//table[@id='resultTable']//td[2]/a");
	public By get_userrole = By.xpath("//table[@id='resultTable']//td[3]");
}
