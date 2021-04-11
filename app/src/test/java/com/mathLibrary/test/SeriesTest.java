package com.mathLibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathlibrary.series.FunctionsSeries;
import com.mathlibrary.series.Series;

public class SeriesTest {

	@Test
	public void test_one() {
		
	    String f_k = "2^k"; 
		
		Series s = new Series(f_k);
		
		try {
		   System.out.println(s.solve(1, 3));
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@Test
	public void test_two() {
		
		String f_x = "2*x";

		String f_k = "k";
		
		FunctionsSeries fs = new FunctionsSeries(f_x, f_k);
		
		try {
			System.out.println(fs.solve(1, 2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	


}
