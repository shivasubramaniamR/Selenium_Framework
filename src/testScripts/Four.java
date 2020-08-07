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
	}
	
	@Test
	public void check2() {
		System.out.println("from four 3rd method");
	}
	@Test
	public void path() {
		System.out.println(System.getProperty("user.dir"));
	}


}
