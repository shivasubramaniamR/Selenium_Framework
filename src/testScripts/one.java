package testScripts;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import utilities.Base;

public class one {
	
	Base b;
	
	@Test(enabled=false)
	public void dis() {
		System.out.println("one");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy HH mm ss");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
		   System.out.println();
	}
	
	@Test(enabled=false)
	public void check() {
		
		
		List<String> list = new ArrayList<>(); 
        // add 5 element in ArrayList 
        list.add("one"); 
        list.add("two"); 
        list.add("three"); 
        list.add("four"); 
        list.add("five"); 
  
        Random rand = new Random();
  
        // take a random element from list and print them 
        System.out.println(list.get(rand.nextInt(list.size()))); 
		
	}
	
	@Test
	public void excel() throws IOException {
		b = new Base();
		String text = b.getCellValueFromExcel("E:/workspace/orangehrm/src/testData/TestDataSheet.xlsx", 2,0);
		String text1 = b.getCellValueFromExcel("E:/workspace/orangehrm/src/testData/TestDataSheet.xlsx", 2,1);
		System.out.println(text);
		System.out.println(text1);
	}

}
