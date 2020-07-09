package testScripts;

import java.io.IOException;


import org.testng.annotations.Test;

import utilities.Base;

public class Cleanup  {

	Base b;
	public String reportspath="" ;
	public String intialReport = "";
	
	@Test
	public void cleanupTask() throws IOException {
		b=new Base();
		intialReport = b.getMapData("intialReport", 0);
		reportspath = b.getMapData("reportspath", 0);
		b.fileMove(intialReport, reportspath);
		
	}
}
