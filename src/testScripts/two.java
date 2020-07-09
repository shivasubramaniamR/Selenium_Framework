package testScripts;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;

import utilities.Base;

public class two {
	
	Base b ;
	public String screenshotFilePath="" ;
	public String reportspath ="";
	public String URL="";
	public String intialReport = "";
	
	@Test
	public void dis() throws IOException {
		b = new Base();
//		System.out.println("two");
//		System.out.println(this.getClass().getName());
		screenshotFilePath = b.getMapData("screenshotFilePath", 0);
		System.out.println("checking"+screenshotFilePath);
		reportspath = b.getMapData("reportspath", 0);
		System.out.println("checking"+reportspath);
		URL = b.getMapData("URL", 0);
		System.out.println("checking"+URL);
		intialReport = b.getMapData("intialReport", 0);
		System.out.println("checking"+intialReport);
	}
	
	

}
