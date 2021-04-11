package com.mathLibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathlibrary.integral.Integral;

public class IntegralTest {

	@Test
	public void test() {

		//String f_x ="cos(x)";
		//String f_x ="sin(x)";
		//String f_x="x^3+1/x";
		//String f_x="2.7^x";
		String f_x="1/x";
		Integral integral = new Integral(f_x);
		try {
			//System.out.println("trapezoidal:"+integral.Trapezoidal(1, 100));
			//System.out.println("simpsom:"+integral.Simpson(1, 2));
			System.out.println("romberg:"+integral.Romberg(1, 2, 15));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}



}
