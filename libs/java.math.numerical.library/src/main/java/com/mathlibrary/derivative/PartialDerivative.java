package com.mathlibrary.derivative;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionXs;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class PartialDerivative {

    /**
     * f(x,y,...)
     */
    private FunctionXs f;

    /**
     * 
     * 
     * @param f
     */
    public PartialDerivative(final String f) {
        this.f = new FunctionXs(f);
    }

    /**
     * 
     * getter f
     * 
     * @return
     */
    public FunctionXs getF() {
        return this.f;
    }

    /**
     * 
     * setter f
     * 
     * @param f
     */
    public void setF(final FunctionXs f) {
        this.f = f;
    }

    /**
     * 
     * f'x (x,y,...)
     * 
     * @param values
     * @param variables
     * @param partial
     *            we use this param to create the partial derivative ex: f'x (x,y,z,...)
     * @return
     * @throws CalculatorException
     */
    public double getDerivative(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1e-10;
        double result = 0.0;
        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        final double x = x1.get(n) + h;
        x1.set(n, new Double(x));

        result = (this.f.getValue(x1, variables) - this.f.getValue(values, variables)) / h;

        return result;
    }

    /**
     * 
     * f'x (x,y,...) better accurate
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivative_accurate(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^2)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1e-4;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x_2 = new ArrayList<Double>();
        x_2.addAll(values);
        x = x_2.get(n) - (2 * h);
        x_2.set(n, new Double(x));

        result = (((3 * this.f.getValue(values, variables)) - (4 * this.f.getValue(x_1, variables))) + this.f.getValue(
                x_2, variables)) / (2 * h);

        return result;
    }

    /**
     * 
     * f'x (x,y,...) best accurate
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivative_accurateTwo(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^4)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x_2 = new ArrayList<Double>();
        x_2.addAll(values);
        x = x_2.get(n) - (2 * h);
        x_2.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        final List<Double> x2 = new ArrayList<Double>();
        x2.addAll(values);
        x = x2.get(n) + (2 * h);
        x2.set(n, new Double(x));

        result = (((this.f.getValue(x_2, variables) - (8 * this.f.getValue(x_1, variables))) + (8 * this.f.getValue(x1,
                variables))) - this.f.getValue(x2, variables)) / (12 * h);
        return result;
    }

    /**
     * 
     * f''x (x,y,...)
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderTwo(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^2)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        result = ((this.f.getValue(x1, variables) - (2 * this.f.getValue(values, variables))) + this.f.getValue(x_1,
                variables)) / (h * h);

        return result;
    }

    /**
     * 
     * f''x (x,y,...) best accurate
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderTwo_accurate(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^4)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x_2 = new ArrayList<Double>();
        x_2.addAll(values);
        x = x_2.get(n) - (2 * h);
        x_2.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        final List<Double> x2 = new ArrayList<Double>();
        x2.addAll(values);
        x = x2.get(n) + (2 * h);
        x2.set(n, new Double(x));

        result = ((((-this.f.getValue(x2, variables) + (16 * this.f.getValue(x1, variables))) - (30 * this.f.getValue(
                values, variables))) + (16 * this.f.getValue(x_1, variables))) - this.f.getValue(x_2, variables))
                / (12 * h * h);

        return result;
    }

    /**
     * f'''x (x,y,...)
     * 
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderThree(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^2)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x_2 = new ArrayList<Double>();
        x_2.addAll(values);
        x = x_2.get(n) - (2 * h);
        x_2.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        final List<Double> x2 = new ArrayList<Double>();
        x2.addAll(values);
        x = x2.get(n) + (2 * h);
        x2.set(n, new Double(x));

        result = (((this.f.getValue(x2, variables) - (2 * this.f.getValue(x1, variables))) + (2 * this.f.getValue(x_1,
                variables))) - this.f.getValue(x_2, variables)) / (2 * h * h * h);

        return result;
    }

    /**
     * f''x (x,y,...) best accurate
     * 
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderThree_accurate(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^2)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x_2 = new ArrayList<Double>();
        x_2.addAll(values);
        x = x_2.get(n) - (2 * h);
        x_2.set(n, new Double(x));

        final List<Double> x_3 = new ArrayList<Double>();
        x_3.addAll(values);
        x = x_3.get(n) - (3 * h);
        x_3.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        final List<Double> x2 = new ArrayList<Double>();
        x2.addAll(values);
        x = x2.get(n) + (2 * h);
        x2.set(n, new Double(x));

        final List<Double> x3 = new ArrayList<Double>();
        x3.addAll(values);
        x = x3.get(n) + (3 * h);
        x3.set(n, new Double(x));

        result = (((((this.f.getValue(x_3, variables) - (8 * this.f.getValue(x_2, variables))) + (13 * this.f.getValue(
                x_1, variables))) - (13 * this.f.getValue(x1, variables))) + (8 * this.f.getValue(x2, variables))) - this.f
                .getValue(x3, variables))
                / (8 * h * h * h);

        return result;
    }

    /**
     * f(iv) x (x,y,...) better accurate
     * 
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderFour(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^2)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Can't find variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        final List<Double> x2 = new ArrayList<Double>();
        x2.addAll(values);
        x = x2.get(n) + (2 * h);
        x2.set(n, new Double(x));

        final List<Double> x3 = new ArrayList<Double>();
        x3.addAll(values);
        x = x3.get(n) + (3 * h);
        x3.set(n, new Double(x));

        final List<Double> x4 = new ArrayList<Double>();
        x4.addAll(values);
        x = x4.get(n) + (4 * h);
        x4.set(n, new Double(x));

        result = ((((this.f.getValue(x4, variables) - (4 * this.f.getValue(x3, variables))) + (6 * this.f.getValue(x2,
                variables))) - (4 * this.f.getValue(x1, variables))) + this.f.getValue(values, variables))
                / (h * h * h * h);

        return result;
    }

    /**
     * f(iv)x (x,y,...) best accurate
     * 
     * 
     * @param values
     * @param variables
     * @param partial
     * @return
     * @throws CalculatorException
     */
    public double getDerivativeOrderFour_accurate(final List<Double> values, List<String> variables, String partial)
            throws CalculatorException {
        // Error O(h^2)
        partial = partial.toLowerCase();
        variables = toLowerCase(variables);
        final double h = 0.1;
        double result = 0.0;

        final List<Double> x_1 = new ArrayList<Double>();
        x_1.addAll(values);
        final int n = variables.indexOf(partial);
        if (n < 0) {
            throw new CalculatorException("Cannot find the variable");
        }
        double x = x_1.get(n) - h;
        x_1.set(n, new Double(x));

        final List<Double> x_2 = new ArrayList<Double>();
        x_2.addAll(values);
        x = x_2.get(n) - (2 * h);
        x_2.set(n, new Double(x));

        final List<Double> x1 = new ArrayList<Double>();
        x1.addAll(values);
        x = x1.get(n) + h;
        x1.set(n, new Double(x));

        final List<Double> x2 = new ArrayList<Double>();
        x2.addAll(values);
        x = x2.get(n) + (2 * h);
        x2.set(n, new Double(x));

        result = ((((this.f.getValue(x2, variables) - (4 * this.f.getValue(x1, variables))) + (6 * this.f.getValue(
                values, variables))) - (4 * this.f.getValue(x_1, variables))) + this.f.getValue(x_2, variables))
                / (h * h * h * h);

        return result;
    }

    /**
     * 
     * toLowerCase
     * 
     * @param list
     * @return
     */
    private List<String> toLowerCase(final List<String> list) {
        final List<String> result = new ArrayList<String>();
        for (final String string : list) {
            result.add(string.toLowerCase());
        }
        return result;

    }

}
