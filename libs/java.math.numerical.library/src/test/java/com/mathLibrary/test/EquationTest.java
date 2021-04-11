package com.mathLibrary.test;


import org.junit.Test;

import com.mathlibrary.equation.EquationX;
import com.mathlibrary.exception.CalculatorException;

public class EquationTest {

	
	@Test
	public void test_one() {
		 String f_x ="sin(x)";
		 boolean degree = true;
		 EquationX ec_x = new EquationX(f_x, degree);
		 try {
			System.out.println(ec_x.getRoot(1, 360, 0.1, 0.1e-10));
		 } catch (CalculatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}

	
	@Test
	public void test_two() {
		 String f_x = "2*x^2 +5*x -18";
		 EquationX ec_x = new EquationX(f_x);
		 try {
			 System.out.println(ec_x.getRoot(0.1e-10));
		} catch (CalculatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void test_three() {
		String f_x = "89-acos(x)"; 
		
		EquationX ec_x = new EquationX(f_x,true);
		 try {
			 System.out.println(ec_x.getRoot(0,100, 0.01, 0.1e-10));
		} catch (CalculatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	
	

}

