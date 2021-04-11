package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


import com.mathlibrary.differential.NeumannEquation;
import com.mathlibrary.differential.Point;
import com.mathlibrary.util.Interval;

public class NeumannEqTest {

	@Test
	public void test() {
	
            //ODE equation --> a(x)*y'' + b(x)*y' + c(x)*y = f(x)
			
			//Neumann boundary condition 
	            //solveInitCondition  --> y'(a)=A   y(b)=B  || A = alpha, B = beta
				//solveFinalCondition --> y(a)=A    y'(b)=B || A = alpha, B = beta
				//solveFinalCondition --> y(a)=A    y'(b)=B || A = alpha, B = beta
				//solve               --> y'(a)=A   y'(b)=B || A = alpha, B = bet
			
			String a_x = "1";
			String b_x = "1";
			String c_x = "1";
			String f_x = "cos(x)";
				
	        NeumannEquation neq = new NeumannEquation(a_x, b_x, c_x, f_x);
			 	
			double alpha = 1;
			double beta = 1.35700810049458;	
			try {
			Interval interval = new Interval(0,0.5);
			List<Point> sol = neq.solve(interval, 500, alpha, beta);
			//List<Point> sol = neq.solveInitCondition(interval, 500, alpha, beta);	
			//List<Point> sol = neq.solveFinalCondition(interval, 500, alpha, beta);
			//List<Point> sol = neq.solveAllInitCondition(interval, 500, alpha, beta);
		    System.out.println("x="+sol.get(499).getXi()+" y="+sol.get(499).getYi());
		    
			
			}catch (Exception e) {
				e.printStackTrace();
			}
			

		}
	

}
