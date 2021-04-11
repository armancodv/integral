package com.mathLibrary.test;



import org.junit.Test;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.Complex;

public class ComplexTest {

	@Test
	public void test() {
		Complex c = new Complex(1,1);
		Complex c2 = new Complex(0.5,-0.5);
		Complex c3 = new Complex(0,1);
		Complex result;
		try {
		    result = Complex.add(c,c2);	
			System.out.println("add:" +result.r+ " "+ result.i+"i");
			result = Complex.add(10,c2);	
			System.out.println("add:" +result.r+ " "+ result.i+"i");
			result = Complex.sub(c,c2);	
			System.out.println("sub:" +result.r+ " "+ result.i+"i");
			result = Complex.sub(10,c2);	
			System.out.println("sub:" +result.r+ " "+ result.i+"i");
			result = Complex.mul(c,c2);	
			System.out.println("mul:" +result.r+ " "+ result.i+"i");
			result = Complex.mul(10,c2);	
			System.out.println("mul:" +result.r+ " "+ result.i+"i");
			result = Complex.div(c,c2);	
			System.out.println("div:" +result.r+ " "+ result.i+"i");
			result = Complex.div(10,c2);	
			System.out.println("div:" +result.r+ " "+ result.i+"i");
			result = c.pow(c2);	
			System.out.println("pow complex:" +result.r+ " "+ result.i+"i");
			result = c.pow(10);	
			System.out.println("pow:" +result.r+ " "+ result.i+"i");
			result = c.pow(0.5);	
			System.out.println("pow:" +result.r+ " "+ result.i+"i");
			result = c.pow(-0.5);	
			System.out.println("pow:" +result.r+ " "+ result.i+"i");
			result = c3.pow(0);	
			System.out.println("pow i:" +result.r+ " "+ result.i+"i");
			result = c3.pow(1);	
			System.out.println("pow i:" +result.r+ " "+ result.i+"i");
			result = c3.pow(2);	
			System.out.println("pow i:" +result.r+ " "+ result.i+"i");
			result = c3.pow(3);	
			System.out.println("pow i:" +result.r+ " "+ result.i+"i");
			result = c3.pow(4);	
			System.out.println("pow i:" +result.r+ " "+ result.i+"i");
			result = c.sqrt();		
			System.out.println("sqrt:" +result.r+ " "+ result.i+"i");
			result = Complex.sqrt(c);		
			System.out.println("sqrt2:" +result.r+ " "+ result.i+"i");
			result = c.sin();		
			System.out.println("sin:" +result.r+ " "+ result.i+"i");
			result = c.cos();		
			System.out.println("cos:" +result.r+ " "+ result.i+"i");
			result = c.tan();		
			System.out.println("tan:" +result.r+ " "+ result.i+"i");
			result = c.asin();		
			System.out.println("asin:" +result.r+ " "+ result.i+"i");
			result = c.acos();		
			System.out.println("acos:" +result.r+ " "+ result.i+"i");
			result = c.atan();		
			System.out.println("atan:" +result.r+ " "+ result.i+"i");
			result = c.sinh();	
			System.out.println("sinh:" +result.r+ " "+ result.i+"i");
			result = c.cosh();		
			System.out.println("cosh:" +result.r+ " "+ result.i+"i");
			result = c.tanh();		
			System.out.println("tanh:" +result.r+ " "+ result.i+"i");
			result = c.ln();		
			System.out.println("ln:" +result.r+ " "+ result.i+"i");
			Complex c4 = new Complex(1000, 1000);
			result = c4.ln();		
			System.out.println("ln:" +result.r+ " "+ result.i+"i");
			
			result = c.log();		
			System.out.println("log:" +result.r+ " "+ result.i+"i");
			
			double r = Complex.abs(c2);		
			System.out.println("abs:" +r);
			result = c.inverse();		
			System.out.println("inv:" +result.r+ " "+ result.i+"i");
			r = c.module();		
			System.out.println("module:" +r);
			r = c.arg();		
			System.out.println("arg:" +r);
	
       } catch (CalculatorException e) {
    	   e.printStackTrace();
    	   
       }

	}
}
