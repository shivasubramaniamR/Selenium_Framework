package utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProjectUtils extends Base{
	
	public List<String> getempNames(By element) {
		
		
		List<String> list=new ArrayList<String>();
		List<WebElement> elelist=driver.findElements(element);
		
		for (WebElement ele:elelist) {
			list.add(ele.getText());
		}
		
		
		return list;
	}
	
	public String emp_name(List<String> list) {
		Random rand = new Random();
		String employee_name=list.get(rand.nextInt(list.size()));
		
		return employee_name;
	}
	
	
}
