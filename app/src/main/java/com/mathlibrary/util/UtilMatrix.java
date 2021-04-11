package com.mathlibrary.util;

import com.mathlibrary.matrix.Matrix;

/**
 * 
 * @author Sergio Besada
 *
 */
public class UtilMatrix {

	
	/**
	 * getI
	 * @param rows
	 * @return
	 */
	public static Matrix getI(int rows){
		Matrix I = new Matrix(rows, rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				if (i == j){
					try {
						I.setElement(i, j, 1);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return I;
		
	}
	
	
	
}
