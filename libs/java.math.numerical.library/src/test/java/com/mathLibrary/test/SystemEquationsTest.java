package com.mathLibrary.test;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.equation.EquationSystem;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.matrix.Vector;

public class SystemEquationsTest {

	@Test
	public void test_one() {
		try {
			List vectors = new ArrayList<Vector>();
			double x1[] = {0, 0 , 0 ,1};
			Vector v1 = new Vector(x1, true); //true means is row
			double x2[] = {0, 0, 1, 0};
			Vector v2 = new Vector(x2, true);
			double x3[] = {0, 1, 0, 0};
			Vector v3 = new Vector(x3, true);
			double x4[] = {1, 0, 0, 0};
			Vector v4 = new Vector(x4, true);
			
			vectors.add(v1);
			vectors.add(v2);
			vectors.add(v3);
			vectors.add(v4);
			
		
				Matrix A = new Matrix(vectors);
				double xi[] = {1,2,3,4};
				Vector b = new Vector(xi, true);
				
				EquationSystem se = new EquationSystem(A, b);
				Vector x = se.solve();
				System.out.println("solution");
				for (int i = 0; i < x.getLenght(); i++) {
					System.out.println(x.getXi(i));
				}
				System.out.println("*****************");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
	
	@Test
	public void test_two(){
		
		try {
			List vectors = new ArrayList<Vector>();
			double x1[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1};
			Vector v1 = new Vector(x1, true);
			double x2[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0};
			Vector v2 = new Vector(x2, true);
			double x3[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0};
			Vector v3 = new Vector(x3, true);
			double x4[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0};
			Vector v4 = new Vector(x4, true);
			double x5[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0};
			Vector v5 = new Vector(x5, true);
			double x6[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0};
			Vector v6 = new Vector(x6, true);
			double x7[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0};
			Vector v7 = new Vector(x7, true);
			double x8[] = {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0};
			Vector v8 = new Vector(x8, true);
			double x9[] = {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0};
			Vector v9 = new Vector(x9, true);
			double x10[] = {0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0};
			Vector v10 = new Vector(x10, true);
			double x11[] = {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0};
			Vector v11 = new Vector(x11, true);
			double x12[] = {0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0};
			Vector v12 = new Vector(x12, true);
			double x13[] = {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v13 = new Vector(x13, true);
			double x14[] = {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v14 = new Vector(x14, true);
			double x15[] = {0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v15 = new Vector(x15, true);
			double x16[] = {0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v16 = new Vector(x16, true);
			double x17[] = {0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v17 = new Vector(x17, true);
			double x18[] = {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v18 = new Vector(x18, true);
			double x19[] = {0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v19 = new Vector(x19, true);
			double x20[] = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
			Vector v20 = new Vector(x20, true);
			
			vectors.add(v1);
			vectors.add(v2);
			vectors.add(v3);
			vectors.add(v4);
			vectors.add(v5);
			vectors.add(v6);
			vectors.add(v7);
			vectors.add(v8);
			vectors.add(v9);
			vectors.add(v10);
			vectors.add(v11);
			vectors.add(v12);
			vectors.add(v13);
			vectors.add(v14);
			vectors.add(v15);
			vectors.add(v16);
			vectors.add(v17);
			vectors.add(v18);
			vectors.add(v19);
			vectors.add(v20);
			
			
		        long time1= System.currentTimeMillis();
				Matrix A = new Matrix(vectors);
				double xi[] = {1,2, 3, 4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
				Vector b = new Vector(xi, true);
				
				System.out.println("Det A:"+ A.getDet());
				EquationSystem se = new EquationSystem(A, b);
			    Vector x = se.solve();
			    
				//Vector x = se.solveEcuation(A, b, true);
			    System.out.println("solution");
			    for (int i = 0; i < x.getLenght(); i++) {
					System.out.println(x.getXi(i));
				}
			    long time2= System.currentTimeMillis();
			    System.out.println("time:"+( time2-time1) +"ms" );
			    
			    System.out.println("*****************");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	 
	

