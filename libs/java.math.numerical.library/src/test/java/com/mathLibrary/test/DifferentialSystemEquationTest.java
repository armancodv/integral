package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.differential.DiffEquationSys;
import com.mathlibrary.function.FunctionXs;
import com.mathlibrary.util.Interval;

public class DifferentialSystemEquationTest {

	@Test
	public void test() {
		//SYSTEM OF ODE
		
		//String f1 = "-4*x+1";
		//String f2 = "-2y+x";
		
		String f1="y";	
		String f2="-x";	
			
	   // String f1 = "sin(x+y) +e^y-1";
//		String f2 = "e^-4*x+tan(y) -cos(x) +4*x";	
			
			
		List<FunctionXs> funs = new ArrayList<FunctionXs>();
	    FunctionXs fxs1 = new FunctionXs(f1);
	    FunctionXs fxs2 = new FunctionXs(f2);
	    funs.add(fxs1);
	    funs.add(fxs2);
	    List<String> vars = new ArrayList<String>();
	    vars.add("t");
	    vars.add("x");
	    vars.add("y");
	    List<Double> puntos = new ArrayList<Double>();
	    puntos.add(new Double(0));
	    puntos.add(new Double(0));
	    puntos.add(new Double(1));
	    
	    DiffEquationSys sysEc = new DiffEquationSys(funs,vars);
	    Interval interval;
		try {
			interval = new Interval(0, 2);
			List<List<Double>> lista = sysEc.solveRungeKutaO6(puntos, interval, 10);
		    for (int i = 0; i < 3; i++) {
				System.out.println(lista.get(i).get(lista.get(i).size()-1));
			}
		    
		    List<List<Double>> lista2 = sysEc.solveRungeKutaO4(puntos, interval, 1000);
		    for (int i = 0; i < 3; i++) {
				System.out.println(lista2.get(i).get(lista2.get(i).size()-1));
			}
		    
		    List<List<Double>> lista3 = sysEc.solvePredictorCorrectorO4(puntos, interval, 1000);
		    for (int i = 0; i < 3; i++) {
				System.out.println(lista3.get(i).get(lista2.get(i).size()-1));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
