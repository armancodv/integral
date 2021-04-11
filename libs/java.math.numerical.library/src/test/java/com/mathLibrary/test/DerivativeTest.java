package com.mathLibrary.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mathlibrary.derivative.DerivativeX;

public class DerivativeTest {

	@Test
	public void test() {
		// TODO Auto-generated method stub

				//String f_x = "10*x^4+5*x";
				//String f_x = "(10.5*10^(5)*x^2)*x";
				//String f_x = "(10.5*x^2)*x";
				//String f_x = "10*x^4+5*x+4*x^2";
				//String f_x = "log(5)";
				//String f_x = "1/x^2";
				String f_x = "cos(x)";
				
				//String f_x = "log(x)";
				double xo =2;
				DerivativeX der = new DerivativeX(f_x);
				
				try {
					System.out.println("der:"+der.getDerivative_xo(xo));
					System.out.println("der accurate:"+der.getDerivative_xo_accurate(xo));
					System.out.println("der accurate two:"+der.getDerivative_xo_accurateTwo(xo));
					System.out.println("der order two:"+der.getDerivativeOrderTwo_xo(xo));
					System.out.println("der order two accurate:"+der.getDerivativeOrderTwo_xo_accurate(xo));
					System.out.println("der order three:"+der.getDerivativeOrderThree_xo(xo));
					System.out.println("der order three accurate:"+der.getDerivativeOrderThree_xo_accurate(xo));
					System.out.println("der order four:"+der.getDerivativeOrderFour_xo(xo));
					System.out.println("der order four accurate:"+der.getDerivativeOrderFour_xo_accurate(xo));
				//	System.out.println("der order four :"+der.getDerivativeOrderFour_xo1(xo));
					//System.out.println("der:"+der.getDerivative_xo(xo));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}

}
