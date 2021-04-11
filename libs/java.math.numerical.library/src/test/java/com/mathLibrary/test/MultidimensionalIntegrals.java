package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.integral.MultidimensionalIntegral;
import com.mathlibrary.util.Interval;

public class MultidimensionalIntegrals {

	@Test
	public void test() {
		String f = "x^2+y^2 +z^2";
		//String f = "sin(x)+sin(y)";

		List<String> vars = new ArrayList<String>();
		vars.add("x");
		vars.add("y");
		vars.add("z");
		
		List<Interval> intervals = new ArrayList<Interval>();
		try {
			Interval i1 = new Interval(1, 2);
			Interval i2 = new Interval(3, 4);
			Interval i3 = new Interval(5, 6);
			intervals.add(i1);
			intervals.add(i2);
			intervals.add(i3);
			MultidimensionalIntegral integral = new MultidimensionalIntegral(f, vars);
			System.out.println(integral.quasiMontecarlo(intervals, 300000));
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
