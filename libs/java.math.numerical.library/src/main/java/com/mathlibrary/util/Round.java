package com.mathlibrary.util;

/**
 * 
 * @author Sergio Besada
 *
 */
public class Round {

	/**
	 * rint
	 * @param x
	 * @param numDecimals
	 * @return
	 */
	public static double rint(double x, int numDecimals){
		double factor = Math.pow(10, numDecimals);
		double result = Math.rint(x*factor)/factor;
		return result;		
	}
}
