package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mathlibrary.differential.DirichletEquation;
import com.mathlibrary.differential.Point;
import com.mathlibrary.util.Interval;

public class DiritchletEqTest {

	@Test
	public void test() {
		//ODE equation --> a(x)*y'' + b(x)*y' + c(x)*y = f(x)
		//Diritchlet boundary condition --> y(a)=A , y(b)=B || A = alpha, B = Beta		
		
		
		String a_x = "-1";
		String b_x = "0";
		String c_x = "0";
		String f_x = "-e^x";
		
		DirichletEquation deq = new DirichletEquation(a_x, b_x, c_x, f_x);
		
		double alpha = 1;
		double beta = 7.38905609893065;	
		try {
		Interval interval = new Interval(0, 2);
		//List<Point> sol = dec.prueba(interval, 1000, alpha, beta);	
	    //System.out.println("x="+sol.get(500).getXi()+" y="+sol.get(500).getYi());
	    List<Point> sol2 = deq.solve(interval, 1000, alpha, beta);	
	    System.out.println("x="+sol2.get(500).getXi()+" y="+sol2.get(500).getYi());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
