package com.mathlibrary.series;

import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionXs;
import com.mathlibrary.matrix.Vector;

/**
 * MultidimensionalFunctionSeries
 * 
 * @author Sergio Besada
 * 
 */
public class MultidimensionalFunctionSeries {

    /**
     * f (x,y,z,....)
     */
    private final FunctionXs f;

    /**
     * MultidimensionalFunctionSeries
     * 
     * @param f
     */
    public MultidimensionalFunctionSeries(final String f) {
        this.f = new FunctionXs(f);
    }

    /**
     * 
     * 
     * 
     * @param values
     *            (sort the values taking into account the variables)
     * @param vars
     *            x,y,z,...
     * @return
     * @throws CalculatorException
     */
    // sum f(xi,yi,..)
    public double sumXi(final List<Vector> values, final List<String> vars) throws CalculatorException {
        double result = 0;
        final int dimension = values.get(0).getLenght();
        for (final Vector vector : values) {
            if (vector.getLenght() != dimension) {
                throw new CalculatorException("Vectors have not the same lenght");
            }
            result += this.f.getValue(vector.getList(), vars);
        }

        return result;
    }

    /**
     * 
     * mean
     * 
     * @param values
     * @param vars
     * @return
     * @throws CalculatorException
     */
    public double mean(final List<Vector> values, final List<String> vars) throws CalculatorException {
        final double sumXi = sumXi(values, vars);
        final double mean = sumXi / values.size();
        return mean;
    }
}
