package com.mathLibrary.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mathlibrary.derivative.PartialDerivative;

public class PartialDerivativesTest {

	@Test
	public void test() {
		//String f = "x*Y +y^2+ 5*x^4";
				String f = "2.35+(e^-3)*((x+y)^1.75) -75 +z";
				
				List<Double> values = new ArrayList<Double>();
				values.add(new Double(16));
				values.add(new Double(7));
				values.add(new Double(50));
				
				List<String> variables = new ArrayList<String>();
				variables.add("X");
				variables.add("Y");
				variables.add("z");
				
				String partial ="x";
				
				PartialDerivative pd = new PartialDerivative(f);
				
				try {
					System.out.println("Der:"+ pd.getDerivative(values, variables, partial));
					System.out.println("Der accurate:"+ pd.getDerivative_accurate(values, variables, partial));
					System.out.println("Der accurate_two:"+ pd.getDerivative_accurateTwo(values, variables, partial));
					
					System.out.println("Der2:"+ pd.getDerivativeOrderTwo(values, variables, partial));
					System.out.println("Der2 accurate_two:"+ pd.getDerivativeOrderTwo_accurate(values, variables, partial));
					
					System.out.println("Der3:"+ pd.getDerivativeOrderThree(values, variables, partial));
					System.out.println("Der3 accurate_two:"+ pd.getDerivativeOrderThree_accurate(values, variables, partial));
					
					System.out.println("Der4:"+ pd.getDerivativeOrderFour(values, variables, partial));
					System.out.println("Der4 accurate_two:"+ pd.getDerivativeOrderFour_accurate(values, variables, partial));

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	

}
