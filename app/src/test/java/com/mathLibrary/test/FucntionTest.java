package com.mathLibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathlibrary.function.FunctionX;

public class FucntionTest {

	@Test
	public void test_one() {

		//String s = "+3 +5*5*(+1)";
		//String s = "2.35*e^(-3)*x";
        //String s = "2*e^(-3)";
		
	    String s= "sin(x)";
        double X0=1.0;
        
		FunctionX f_x = new FunctionX(s);
        try {
			double result = f_x.getF_xo(X0);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	
	@Test
	public void test_two() {

		Double x0 = new Double("2");
		//String s= "5*(x +3)";
		//String s= "5*(x +3)";		
		//String s= "5*(2*(sqrt((x+2)^2)) +3)";
		//String s= "5*(2*(sqrt((x+2)^2)/2) +3)";
		//String s= "cosh(6+(2/0))";
		String s = " 2*(-(((x*3)*sqrt(x^(2)))+3))";
		//String s = " (2)-(5)";
		//String s = "((2)+(5))";
		
		FunctionX f_x = new FunctionX(s);
		double value = 0.0;
		try {
			value = f_x.getF_xo(x0 );
			System.out.println("value:"+value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}


}
