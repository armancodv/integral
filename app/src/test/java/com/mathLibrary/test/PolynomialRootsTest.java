package com.mathLibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathlibrary.function.Complex;
import com.mathlibrary.polynomial.Polynomial;

public class PolynomialRootsTest {

	@Test
	public void test_one() {
		
        //double[] x = {1,-9, 9 ,1};
		 //double[] x = {1.2, 2.2, 2.2};
		double[] x ={1, 0, 0, 0, -2};
		
      try {
    
		Polynomial.rootCalc(x);
		System.out.println("***************");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}


	@Test
	public void test_two() {
		
		Complex c = new Complex(1,1);
		Complex c2 = new Complex(0.5,-0.5);	
	    Complex[] polynomial={c,c2};
       
		
      try {
      
		Polynomial.rootCalc(polynomial);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	

}
