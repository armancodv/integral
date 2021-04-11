package com.mathlibrary.series;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionX;

/**
 * 
 * @author Sergio Besada
 *
 */
public class Series {

	/**
	 * F(k)
	 */
	private FunctionX f_k;
	
	
	/**
	 * Series
	 * @param f_k
	 */
	public Series(String f_k){
		this.f_k= new FunctionX(f_k);		
	}
	
	
	/**
	 * Series
	 * @param init
	 * @param end
	 * @return
	 * @throws CalculatorException
	 */
	//summation from init until end
	public double solve(int init, int end) throws CalculatorException{
		double result =0;
		for (int k = init; k <= end ; k++) {
			result += f_k.getF_xo(k);
		}
		return result;
	}
	
}
