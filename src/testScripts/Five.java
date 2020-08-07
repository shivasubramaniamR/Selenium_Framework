package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Five {
	
	@Test
	public void check3() {
		System.out.println("from four 4th method");
		Assert.assertEquals(1, 2);
	}
	
	@Test
	public void check4() {
		System.out.println("from four 5th method");
	}
	
	@Test
	public void check5() {
		System.out.println("from four 6th method");
		Assert.assertEquals(5, 6);
	}


}
