package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.equation.NonLinearSys;
import com.mathlibrary.function.FunctionXs;

public class NonLinearSysEqTest {

	@Test
	public void test() {
		String f1 = "2.35*e^(-3)*(x+y)^1.75-75+z";
		String f2 = "4.67*e^(-3)*x^1.75+20-z";
		String f3 = "3.72*e^(-2)*y^1.75+15-z";
		List<String> functions = new ArrayList<String>();
		functions.add(f1);
		functions.add(f2);
		functions.add(f3);
		List<String> vars = new ArrayList<String>();
		vars.add("x");
		vars.add("y");
		vars.add("z");
		List<Double> xo = new ArrayList<Double>();
		xo.add(16.0);
		xo.add(7.0);
		xo.add(50.0);
		
		FunctionXs fXs1 = new FunctionXs(f1);
		FunctionXs fXs2 = new FunctionXs(f2);
		FunctionXs fXs3 = new FunctionXs(f3);
		
		List<Double> test = new ArrayList<Double>();
		test.add(14.1355467);
		test.add(10.1303043);
		test.add(43.9596517);
		
        try {
			NonLinearSys nls = new NonLinearSys(functions,vars,xo);
			List<Double> result = nls.calc(0.1e-6, 1000);
			for (Double d : result) {
				System.out.println(d);				
			}
			System.out.println("Test1:"+fXs1.getValue(result, vars));
			System.out.println("Test2:"+fXs2.getValue(result, vars));
			System.out.println("Test3:"+fXs3.getValue(result, vars));
			
			//System.out.println("Prueba1:"+fXs1.getValue(test, vars));
			//System.out.println("Prueba2:"+fXs2.getValue(test, vars));
			//System.out.println("Prueba3:"+fXs3.getValue(test, vars));
			
			//System.out.println("Fxo1:"+fXs1.getValue(xo, vars));
			//System.out.println("Fxo2:"+fXs2.getValue(xo, vars));
			//System.out.println("Fxo3:"+fXs3.getValue(xo, vars));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
