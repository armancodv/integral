package com.mathlibrary.derivative;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionX;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class DerivativeX {

    /**
     * f(x)
     */
    private final FunctionX f_x;

    /**
     * *
     * 
     * @param f_x
     */
    public DerivativeX(final String f_x) {
        this.f_x = new FunctionX(f_x);
    }

    public double getDerivative_xo(final double xo) throws CalculatorException {
        // Error O(h)
        final double h = 0.1e-10;
        double result = 0.0;

        result = (this.f_x.getF_xo(xo + h) - this.f_x.getF_xo(xo)) / h;

        return result;
    }

    /**
     * 
     * f'(x0) return the derivatvative on one point
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivative_xo_accurate(final double xo) throws CalculatorException {
        // Error O(h^2)
        final double h = 0.1e-4;
        double result = 0.0;

        result = (((3 * this.f_x.getF_xo(xo)) - (4 * this.f_x.getF_xo(xo - h))) + this.f_x.getF_xo(xo - (2 * h)))
                / (2 * h);

        return result;
    }

    /**
     * 
     * f'(x0) return the derivative on one point, best accurate
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivative_xo_accurateTwo(final double xo) throws CalculatorException {
        // Error O(h^4)
        final double h = 0.1;
        double result = 0.0;

        result = (((this.f_x.getF_xo(xo - (2 * h)) - (8 * this.f_x.getF_xo(xo - h))) + (8 * this.f_x.getF_xo(xo + h))) - this.f_x
                .getF_xo(xo + (2 * h)))
                / (12 * h);
        return result;
    }

    /**
     * f''(xo)
     * 
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderTwo_xo(final double xo) throws CalculatorException {
        // Error O(h^2)
        final double h = 0.1;
        double result = 0.0;

        result = ((this.f_x.getF_xo(xo + h) - (2 * this.f_x.getF_xo(xo))) + this.f_x.getF_xo(xo - h)) / (h * h);

        return result;
    }

    /**
     * f''(x0) best accurate
     * 
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderTwo_xo_accurate(final double xo) throws CalculatorException {
        // Error O(h^4)
        final double h = 0.1;
        double result = 0.0;

        result = ((((-this.f_x.getF_xo(xo + (2 * h)) + (16 * this.f_x.getF_xo(xo + h))) - (30 * this.f_x.getF_xo(xo))) + (16 * this.f_x
                .getF_xo(xo - h))) - this.f_x.getF_xo(xo - (2 * h)))
                / (12 * h * h);

        return result;
    }

    /**
     * f'''(x0)
     * 
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderThree_xo(final double xo) throws CalculatorException {
        // Error O(h^2)
        final double h = 0.1;
        double result = 0.0;

        result = (((this.f_x.getF_xo(xo + (2 * h)) - (2 * this.f_x.getF_xo(xo + h))) + (2 * this.f_x.getF_xo(xo - h))) - this.f_x
                .getF_xo(xo - (2 * h)))
                / (2 * h * h * h);

        return result;
    }

    /**
     * f'''(x0) best accurate
     * 
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderThree_xo_accurate(final double xo) throws CalculatorException {
        // Error O(h^2)
        final double h = 0.1;
        double result = 0.0;

        result = (((((this.f_x.getF_xo(xo - (3 * h)) - (8 * this.f_x.getF_xo(xo - (2 * h)))) + (13 * this.f_x
                .getF_xo(xo - h))) - (13 * this.f_x.getF_xo(xo + h))) + (8 * this.f_x.getF_xo(xo + (2 * h)))) - this.f_x
                .getF_xo(xo + (3 * h)))
                / (8 * h * h * h);

        return result;
    }

    /**
     * f(iv) (x0)
     * 
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderFour_xo(final double xo) throws CalculatorException {
        // Error O(h^2)
        final double h = 0.1;
        double result = 0.0;

        result = ((((this.f_x.getF_xo(xo + (4 * h)) - (4 * this.f_x.getF_xo(xo + (3 * h)))) + (6 * this.f_x.getF_xo(xo
                + (2 * h)))) - (4 * this.f_x.getF_xo(xo + h))) + this.f_x.getF_xo(xo))
                / (h * h * h * h);

        return result;
    }

    /**
     * f iv(x0)
     * 
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderFour_xo_accurate(final double xo) throws CalculatorException {
        // Error O(h^2)
        final double h = 0.1;
        double result = 0.0;

        result = ((((this.f_x.getF_xo(xo + (2 * h)) - (4 * this.f_x.getF_xo(xo + h))) + (6 * this.f_x.getF_xo(xo))) - (4 * this.f_x
                .getF_xo(xo - h))) + this.f_x.getF_xo(xo - (2 * h)))
                / (h * h * h * h);

        return result;
    }

}
