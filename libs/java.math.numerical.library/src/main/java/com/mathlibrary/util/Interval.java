package com.mathlibrary.util;

import com.mathlibrary.exception.CalculatorException;

/**
 * 
 * @author Sergio Besada
 *
 */
public class Interval {

	private double a;
	
	private double b;
	
	/**
	 * Interval
	 * @param a
	 * @param b
	 * @throws CalculatorException
	 */
	public Interval(double a, double b) throws CalculatorException{
		if(a> b){
			throw new CalculatorException("Interval is not well define");
		}
		this.a= a;
		this.b=b;
		
	}

	/**
	 * getter point A
	 * @return
	 */
	public double getA() {
		return a;
	}

	/**
	 * setter Point A
	 * @param a
	 */
	public void setA(double a) {
		this.a = a;
	}

	/**
	 * getter point B
	 * @return
	 */
	public double getB() {
		return b;
	}

	/**
	 * setter point B
	 * @param b
	 */
	public void setB(double b) {
		this.b = b;
	}
	
	
	
}
