package com.mathLibrary.test;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.function.FunctionXs;

public class FunctionVarsTest {

	@Test
	public void test() {

		String s ="x+5*y+(3 -y)";
		
		FunctionXs f = new FunctionXs(s);
		List<Double> values = new ArrayList<Double>();
		values.add(new Double(2));
		values.add(new Double(5));
		List<String> variables = new ArrayList<String>();
		variables.add("x");
		variables.add("y");
		
		try {
			double result = f.getValue(values, variables);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
