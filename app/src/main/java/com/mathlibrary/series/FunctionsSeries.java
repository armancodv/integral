package com.mathlibrary.series;

import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionX;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class FunctionsSeries {

    /**
     * f(k)
     */
    private FunctionX f_k;

    /**
     * f(x)
     */
    private final FunctionX f_x;

    /**
     * FunctionsSeries
     * 
     * @param f_x
     * @param f_k
     */
    public FunctionsSeries(final String f_x, final String f_k) {
        this.f_x = new FunctionX(f_x);
        this.f_k = new FunctionX(f_k);
    }

    /**
     * FunctionsSeries
     * 
     * @param f_x
     */
    public FunctionsSeries(final String f_x) {
        this.f_x = new FunctionX(f_x);
    }

    /**
     * 
     * solve
     * 
     * @param init
     * @param end
     * @return
     * @throws CalculatorException
     */
    public double solve(final int init, final int end) throws CalculatorException {
        double result = 0;
        for (int k = init; k <= end; k++) {
            result += this.f_x.getF_xo(this.f_k.getF_xo(k));
        }
        return result;
    }

    /**
     * 
     * sumXi
     * 
     * @param values
     * @return
     * @throws CalculatorException
     */
    // sum f(xi)
    public double sumXi(final List<Double> values) throws CalculatorException {
        double result = 0;
        for (final Double xi : values) {
            result += this.f_x.getF_xo(xi.doubleValue());
        }

        return result;
    }
}
