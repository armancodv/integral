package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.function.Complex;
import com.mathlibrary.matrix.CalcEigenvaule;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.matrix.Vector;

public class EigenvalueTest {

	@Test
	public void test() {
		double[] x1 = {100, 0, 1};
		Vector v1 = new Vector(x1,true);
		double[] x2 = {200, 0, 2};
		Vector v2 = new Vector(x2,true);
		double[] x3 = {300, 0, 2};
		Vector v3 = new Vector(x3,true);
		List<Vector> vectors = new ArrayList<Vector>();
		vectors.add(v1);
		vectors.add(v2);
		vectors.add(v3);
		try {
			Matrix A = new Matrix (vectors);
			
			System.out.println(A.isSimetric());
			List<Complex> list = CalcEigenvaule.calc(A);
			System.out.println("End");
			
			double[] t ={1, 1, 1};
			Vector u0 = new Vector(t,false);
			Vector v = CalcEigenvaule.inverseIteration(A, list.get(0).r, u0);
			
			System.out.println("End");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
