package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.equation.EquationSystem;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.matrix.Vector;

public class MatrixAndSystEqTest {

	@Test
	public void test_one() {
		try {
			List<Vector> vectors = new ArrayList<Vector>();

			Vector v1 = new Vector(3, true);

			v1.setXi(0, 1);
			v1.setXi(1, 2);
			v1.setXi(2, 3);
			vectors.add(v1);
			Vector v2 = new Vector(3, true);

			v2.setXi(0, 4);
			v2.setXi(1, 5);
			v2.setXi(2, 6);
			vectors.add(v2);

			Vector v3 = new Vector(3, true);

			v3.setXi(0, 7);
			v3.setXi(1, 8);
			v3.setXi(2, 10);
			vectors.add(v3);

			Matrix A = new Matrix(vectors);

			Vector b = new Vector(3, true);
			b.setXi(0, 0);
			b.setXi(1, 0);
			b.setXi(2, 1);
			EquationSystem se = new EquationSystem(A, b);
			Vector sol = se.solve();

			System.out.println("Solution:");
			for (int i = 0; i < sol.getLenght(); i++) {
				System.out.println(sol.getXi(i));
			}

			System.out.println("Det" + A.getDet());

			Matrix X = A.getInv();
			System.out.println("Inv:");
			for (int i = 0; i < X.getRows(); i++) {
				for (int j = 0; j < X.getColumns(); j++) {
					System.out.print(X.getElement(i, j));
					if (j != X.getColumns() - 1) {
						System.out.print(",");
					}
				}
				System.out.println();
			}

			System.out.println("**********************");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Test
	public void test_two() {
		try {
			List<Vector> vectors = new ArrayList<Vector>();

			Vector v1 = new Vector(3, true);

			v1.setXi(0, 1);
			v1.setXi(1, 3);
			v1.setXi(2, -1);
			vectors.add(v1);
			Vector v2 = new Vector(3, true);

			v2.setXi(0, 2);
			v2.setXi(1, 1);
			v2.setXi(2, 3);
			vectors.add(v2);

			Vector v3 = new Vector(3, true);

			v3.setXi(0, 3);
			v3.setXi(1, -1);
			v3.setXi(2, -2);
			vectors.add(v3);

			Matrix A = new Matrix(vectors);

			Vector b = new Vector(3, true);
			b.setXi(0, 0);
			b.setXi(1, 0);
			b.setXi(2, 1);
			EquationSystem se = new EquationSystem(A, b);
			
			Vector sol = se.solve();

			System.out.println("Solution:");
			for (int i = 0; i < sol.getLenght(); i++) {
				System.out.println(sol.getXi(i));
			}

			System.out.println("Det" + A.getDet());

			Matrix X = A.getInv();
			System.out.println("Inv:");
			for (int i = 0; i < X.getRows(); i++) {
				for (int j = 0; j < X.getColumns(); j++) {
					System.out.print(X.getElement(i, j));
					if (j != X.getColumns() - 1) {
						System.out.print(",");
					}
				}
				System.out.println();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
