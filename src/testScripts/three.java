package testScripts;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class three {
	
	@Test
	public void dis() {
		//Assert.assertEquals(3, 4);
		System.out.println("three");
		
		File sourceFile = new File("E:/workspace/CaseStudy/test-output/custom-emailable-report.html");
		File destFile = new File("E:/workspace/CaseStudy/src/libraries/reports/newFile.html");
		 
		if (sourceFile.renameTo(destFile)) {
		    System.out.println("File moved successfully");
		} else {
		    System.out.println("Failed to move file");
		}
	}

}
