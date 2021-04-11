package com.mathlibrary.equation;

import com.mathlibrary.derivative.DerivativeX;
import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionX;
import com.mathlibrary.util.Interval;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 *         Ecuation ax +bx^2 + ..= C
 * 
 */
public class EquationX {

    /**
     * f(x)
     */
    private final FunctionX f_x;

    /**
     * f'(x)
     */
    private final DerivativeX der_x;

    /**
     * EcuationX
     * 
     * @param f_x
     */
    public EquationX(final String f_x) {
        this.f_x = new FunctionX(f_x);
        this.der_x = new DerivativeX(f_x);
    }

    /**
     * EcuationX
     * @param f_x
     * @param degree
     */
    public EquationX(final String f_x, final boolean degree) {
        this.f_x = new FunctionX(f_x,degree);
        this.der_x = new DerivativeX(f_x);
    }

    /**
     * 
     * roots
     * 
     * @param xo
     * @param error
     * @return
     * @throws CalculatorException
     */
    public double getRoot(double xo, final double error) throws CalculatorException {
        double result = 0;

        while (true) {
            result = func_newton_raphson(xo);

            if ((result - xo) < error) {
                return result;
            } else {
                xo = result;
            }
        }

    }

    /**
     * roots
     * 
     * 
     * @param error
     * @return
     * @throws CalculatorException
     */
    public double getRoot(final double error) throws CalculatorException {
        final double interval_a1 = 0.1e-10;
        final double interval_b1 = 1e15;
        final double interval_a2 = -0.1e-10;
        final double interval_b2 = 1e15;
        final double interval_a3 = -0.1e-10;
        final double interval_b3 = -1e15;
        final double interval_a4 = 0.1e-10;
        final double interval_b4 = -1e15;

        if ((this.f_x.getF_xo(interval_a1) * this.f_x.getF_xo(interval_b1)) <= 0) {
            return getRoot(interval_a1, interval_b1, error);
        } else if ((this.f_x.getF_xo(interval_a2) * this.f_x.getF_xo(interval_b2)) <= 0) {
            return getRoot(interval_a2, interval_b2, error);
        } else if ((this.f_x.getF_xo(interval_a3) * this.f_x.getF_xo(interval_b3)) <= 0) {
            return getRoot(interval_a3, interval_b3, error);
        } else if ((this.f_x.getF_xo(interval_a4) * this.f_x.getF_xo(interval_b4)) <= 0) {
            return getRoot(interval_a4, interval_b4, error);
        } else {
            throw new CalculatorException("there is no root");
        }

    }

    /**
     * 
     * get root
     * 
     * @param interval_a
     * @param interval_b
     * @param step
     * @param error
     * @return
     * @throws CalculatorException
     */
    public double getRoot(final double interval_a, final double interval_b, final double step, final double error)
            throws CalculatorException {
        if (interval_a > interval_b) {
            throw new CalculatorException("Interval a > interval b");
        }

        if ((this.f_x.getF_xo(interval_a) * this.f_x.getF_xo(interval_b)) <= 0) {
            return getRoot(interval_a, interval_b, error);
        } else {
            final Interval interval = aprox_shift(interval_a, interval_b, step);
            return getRoot(interval.getA(), interval.getB(), error);
        }
    }

    /**
     * 
     * getter Toot
     * 
     * @param interval_a
     * @param interval_b
     * @param error
     * @return
     * @throws CalculatorException
     */
    public double getRoot(final double interval_a, final double interval_b, final double error)
            throws CalculatorException {

        final double xo = aprox(interval_a, interval_b);
        return getRoot(xo, error);
    }

    /**
     * 
     * newton_raphson
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    private double func_newton_raphson(final double xo) throws CalculatorException {
        double result = 0;
        final double der = this.der_x.getDerivative_xo_accurate(xo);
        final double func = this.f_x.getF_xo(xo);

        if (func == 0) {
            result = xo;
        } else if (der == 0) {
            result = xo - (func / 0.1e-10);
        } else {
            result = xo - (func / der);
        }

        return result;
    }

    /**
     * 
     * aprox_shift
     * 
     * @param interval_a
     * @param interval_b
     * @param step
     * @return
     * @throws CalculatorException
     */
    private Interval aprox_shift(final double interval_a, final double interval_b, final double step)
            throws CalculatorException {
        double subinterval_a = interval_a;
        double subinterval_b = interval_a + step;

        while (subinterval_b < interval_b) {
            if ((this.f_x.getF_xo(subinterval_a) * this.f_x.getF_xo(subinterval_b)) <= 0) {
                return new Interval(subinterval_a, subinterval_b);
            } else {
                subinterval_a = subinterval_b;
                subinterval_b = subinterval_b + step;
            }
        }

        return new Interval(interval_a, interval_b);
    }

    /**
     * 
     * aprox
     * 
     * @param interval_a
     * @param interval_b
     * @return
     * @throws CalculatorException
     */
    private double aprox(double interval_a, double interval_b) throws CalculatorException {
        final double result = 0;

        while (true) {
            final double f_a = this.f_x.getF_xo(interval_a);
            final double f_b = this.f_x.getF_xo(interval_b);
            if ((f_a * f_b) <= 0) {

                if (Math.abs(interval_a - interval_b) <= 0.5) {
                    return dichotomy(interval_a, interval_b);
                }

                if (f_a == 0) {
                    return interval_a;
                } else if (f_b == 0) {
                    return interval_b;
                }

                final double xn = dichotomy(interval_a, interval_b);

                final double f_xn = this.f_x.getF_xo(xn);

                if (f_a < 0) {
                    if (f_xn > 0) {
                        interval_b = xn;
                    }
                }
                if (f_a > 0) {
                    if (f_xn < 0) {
                        interval_b = xn;
                    }
                }
                if (f_b < 0) {
                    if (f_xn > 0) {
                        interval_a = xn;
                    }
                }
                if (f_b > 0) {
                    if (f_xn < 0) {
                        interval_a = xn;
                    }
                }

            } else {
                throw new CalculatorException("there is no root in this interval");

            }
        }

    }

    /**
     * 
     * dichotomy
     * 
     * @param interval_a
     * @param interval_b
     * @return
     */
    private double dichotomy(final double interval_a, final double interval_b) {
        return (interval_a + interval_b) / 2;

    }

}
