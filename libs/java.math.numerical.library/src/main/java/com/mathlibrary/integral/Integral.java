package com.mathlibrary.integral;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionX;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.series.FunctionsSeries;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Integral {

    /**
     * f(x)
     */
    private final FunctionX f_x;

    /**
     * Integral
     * 
     * @param f_x
     */
    public Integral(final String f_x) {
        this.f_x = new FunctionX(f_x);
    }

    /**
     * 
     * Trapezoidal
     * 
     * @param a
     * @param b
     * @return
     * @throws CalculatorException
     */
    public double trapezoidal(final double a, final double b) throws CalculatorException {
        double result = 0;
        final int m = numInterval(a, b);
        final double h = (b - a) / m;
        final FunctionsSeries fs = new FunctionsSeries(this.f_x.getF_x(), new Double(a).toString() + "+k*"
                + new Double(h).toString());
        result = h * (((1 / 2) * (this.f_x.getF_xo(a) + this.f_x.getF_xo(b))) + fs.solve(1, m - 1));
        return result;
    }

    /**
     * 
     * simpson
     * 
     * @param a
     * @param b
     * @return
     * @throws CalculatorException
     */
    public double simpson(final double a, final double b) throws CalculatorException {

        double result = 0;
        final int m = numInterval(a, b);
        final double h = (b - a) / (2 * (double) m);
        final FunctionsSeries fs = new FunctionsSeries(this.f_x.getF_x(), new Double(a).toString() + "+2*k*"
                + new Double(h).toString());
        final FunctionsSeries fs2 = new FunctionsSeries(this.f_x.getF_x(), new Double(a).toString() + "+(2*k-1)*"
                + new Double(h).toString());

        result = (h / 3)
                * (this.f_x.getF_xo(a) + this.f_x.getF_xo(b) + (2 * fs.solve(1, m - 1)) + (4 * fs2.solve(1, m)));
        return result;

    }

    /**
     * 
     * numInterval
     * 
     * @param a
     * @param b
     * @return
     */
    private int numInterval(final double a, final double b) {
        int m = 0;
        final double interval = Math.abs(Math.abs(a) - Math.abs(b));
        final int n = (int) interval;
        if (n < 1) {
            m = 10;
        } else {
            m = n * 10;
        }
        return m;
    }

    /**
     * 
     * Romberg
     * 
     * @param a
     * @param b
     * @param n
     * @return
     * @throws CalculatorException
     */
    public double Romberg(final double a, final double b, final int n) throws CalculatorException {

        final Matrix R = new Matrix(n + 1, n + 1);
        R.setElement(0, 0, ((b - a) / 2) * ((this.f_x.getF_xo(a) + this.f_x.getF_xo(b))));

        for (int i = 1; i <= n; i++) {
            final double h = (b - a) / (Math.pow(2, i));
            double s = 0;
            for (int k = 1; k <= Math.pow(2, i - 1); k++) {
                s += this.f_x.getF_xo(a + (((2 * k) - 1) * h));
            }
            R.setElement(i, 0, ((R.getElement(i - 1, 0)) / 2) + (h * s));

        }

        for (int j = 1; j <= n; j++) {
            final double fac = 1 / (Math.pow(4, j) - 1);
            for (int m = j; m <= n; m++) {
                R.setElement(m, j, R.getElement(m, j - 1)
                        + (fac * (R.getElement(m, j - 1) - R.getElement(m - 1, j - 1))));
            }

        }

        return R.getElement(n, n);
    }

}
