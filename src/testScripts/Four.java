package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Four {
	
	@Test
	public void check() {
		System.out.println("from four 1st method");
		Assert.assertEquals(8, 9);
	}
	
	@Test
	public void check1() {
		System.out.println("from four 2nd method");
		System.out.format("%s, %s", "Hello", "world");
	}
	
	@Test
	public void check2() {
		//String format = "%-20s %-15s %-20s %-15s %-10s";
		System.out.println("\033[0;1mfrom four 3rd method");
	}
	@Test
	public void path() {
		System.out.println(System.getProperty("user.dir"));
	}


}
