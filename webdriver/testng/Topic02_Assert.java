package testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
public class Topic02_Assert {
	 @Test (groups = {"admin","regression"})
	  public void TC01() {
		  Assert.assertTrue(3<5);
		  
		  //Static method
		  assertTrue(3<5);
		  
		  Assert.assertFalse(3>5);
		  
		  Assert.assertEquals("Automation", "Automation");
	  }


}
