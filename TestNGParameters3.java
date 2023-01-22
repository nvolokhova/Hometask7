package testNGParameters;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNGParameters3
{

    @Test
    @Parameters ({"val1", "val2"})
    public void mul(int v1, int v2) {
    	    	int prod = v1*v2;
    	        System.out.println("The Product Of Value 1 and 2 is " + prod);
    }
        
     @Test
     @Parameters ({"val1", "val2"})
     public void Div(int v1, int v2) {
    	 try {
    		 int div = v1/v2;
    	 System.out.println("The Division Of Value 1 and 2 is " + div);    
    	 }
    	 catch (ArithmeticException e) {
    	 System.out.println("Cannot be divided by 0");
    	 }
     }
     
  }
