package com.mathlibrary.util;

import com.mathlibrary.matrix.Vector;

/**
 * 
 * @author Sergio Besada
 *
 */
public class UtilVector {

  /**
   * rowOnes	
   * @param numElements
   * @return
   */
  public static Vector rowOnes(int numElements){
	  
	  Vector v = new Vector(numElements, true);
	  for (int i = 0; i < v.getLenght(); i++) {
		try{
		  v.setXi(i, 1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	  }	  
	  return v;
  }
  
 /**
  * columnOnes
  * @param numElements
  * @return
  */
 public static Vector columnOnes(int numElements){
	  
	  Vector v = new Vector(numElements, false);
	  for (int i = 0; i < v.getLenght(); i++) {
		try{
		  v.setXi(i, 1);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	  }	  
	  return v;
  }
}
