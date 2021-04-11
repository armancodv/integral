package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.differential.CauchyEquation;
import com.mathlibrary.differential.Point;
import com.mathlibrary.util.Interval;

public class DifferentialEquationsTest {

	@Test
	public void test() {
		// TODO Auto-generated method stub
		// String f_xy = "cos(x)"; --> y' = cos(x);
		
		
		String f_xy = "-4*x+1"; // y' = -4*x+1
		// String f_xy = "4*x+1+y"; --> y' = 4*x+1+y
		// String f_xy = "4*x+1"; --> y' = 4*x+1
		Point po = new Point(0, 0);
		List<String> vars = new ArrayList<String>();
		vars.add("X");
		vars.add("Y");

		CauchyEquation ec = new CauchyEquation(f_xy, po, vars);
		Interval interval;
		try {
			interval = new Interval(0, 2);
			List<Point> puntosRK4 = ec.solveRungeKutta(interval, 1000);
			List<Point> puntosKuta6 = ec.solveRungeKuttaO6(interval, 1000);
			List<Point> puntosRKA = ec
					.solveRungeKuttaAdaptative(interval, 1000);
			// /List<Point> puntosRKA2 = ec.solveRungeKuttaAdaptative2(interval,
			// 1000);
			List<Point> puntos1 = ec.solveOrder1(interval, 1000);
			List<Point> puntos2 = ec.solveOrder2(interval, 1000);
			// List<Point> puntos3 = ec.solveOrder3(interval, 1000);
			List<Point> puntos4 = ec.solveOrder4(interval, 1000);
			List<Point> puntos5 = ec.solveOrder5(interval, 1000);
			Point p = puntosRK4.get(puntosRK4.size() - 1);
			System.out.println("x:" + p.getXi() + " y:" + p.getYi());
			Point pKuta6 = puntosKuta6.get(puntosKuta6.size() - 1);
			System.out.println("x:" + pKuta6.getXi() + " y:" + pKuta6.getYi());
			Point pRKA = puntosRKA.get(puntosRKA.size() - 1);
			System.out.println("Adaptative " + "x:" + pRKA.getXi() + " y:"
					+ pRKA.getYi());
			// Point pRKA2 = puntosRKA2.get(puntosRKA2.size()-1);
			// System.out.println("x:"+pRKA2.getXi()+" y:"+pRKA2.getYi());
			Point p1 = puntos1.get(puntos1.size() - 1);
			System.out.println("order 1 " + "x:" + p1.getXi() + " y:"
					+ p1.getYi());
			Point p2 = puntos2.get(puntos2.size() - 1);
			System.out.println("order 2 " + "x:" + p2.getXi() + " y:"
					+ p2.getYi());
			// Point p3 = puntos3.get(puntos3.size()-1);
			// System.out.println("x:"+p3.getXi()+" y:"+p3.getYi());
			Point p4 = puntos4.get(puntos4.size() - 1);
			System.out.println("order 4 " + "x:" + p4.getXi() + " y:"
					+ p4.getYi());
			Point p5 = puntos5.get(puntos5.size() - 1);
			System.out.println("order 5 " + "x:" + p5.getXi() + " y:"
					+ p5.getYi());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
