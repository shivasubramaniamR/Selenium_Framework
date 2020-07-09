package pageObjects;

import org.openqa.selenium.By;


public class OrangeHRMDeleteUser {
	
	public By Admin = By.xpath("//a/b[contains(text(),'Admin')]");
//	public By checkbox = By.xpath("//table[@id='resultTable']//td[1]");
	public By checkbox = By.xpath("//table[thead[tr[th[a[contains(text(),'Username')]]]]]/tbody/tr/td[1]/input");
	public By delete = By.xpath("//input[@id='btnDelete']");
	public By table_count = By.xpath("//table[@id='resultTable']//tr");
	public By ok = By.xpath("//input[@id='dialogDeleteBtn']");
	
	
}
