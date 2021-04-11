package com.mathlibrary.differential;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.equation.EquationSystem;
import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionX;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.matrix.Vector;
import com.mathlibrary.util.Interval;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 *         Neumann Ecuation
 * 
 */
public class NeumannEquation {

    //ODE equation --> a(x)*y'' + b(x)*y' + c(x)*y = f(x)
			
		//Neumann boundary condition 
	        //solveInitCondition  --> y'(a)=A   y(b)=B  || A = alpha, B = beta
		    //solveFinalCondition --> y(a)=A    y'(b)=B || A = alpha, B = beta
	    	//solve               --> y'(a)=A   y'(b)=B || A = alpha, B = bet
    /**
     * a(x)
     */
    private FunctionX a_x = null;

    /**
     * b(x)
     */
    private FunctionX b_x = null;

    /**
     * c(x)
     */
    private FunctionX c_x = null;

    /**
     * f(x)
     */
    private FunctionX f_x = null;

    /**
     * NeumannEcuation
     * 
     * @param a_x
     * @param b_x
     * @param c_x
     * @param f_x
     */
    public NeumannEquation(final String a_x, final String b_x, final String c_x, final String f_x) {

        this.a_x = new FunctionX(a_x);
        this.b_x = new FunctionX(b_x);
        this.c_x = new FunctionX(c_x);
        this.f_x = new FunctionX(f_x);
    }

    /**
     * 
     * solve y'(a)=A y'(b)=B
     * 
     * @param interval
     * @param m
     * @param alpha
     * @param beta
     * @return
     * @throws CalculatorException
     */
    public List<Point> solve(final Interval interval, final int m, final double alpha, final double beta)
            throws CalculatorException {
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / (m + 1);

        final Matrix A = new Matrix(m + 2, m + 2);
        final Vector f = new Vector(m + 2, false);

        final double x0 = interval.getA();
        final Vector a0 = new Vector(m + 2, true);
        a0.setXi(0, (-this.a_x.getF_xo(x0) / (h)) + ((this.c_x.getF_xo(x0) * h) / (2)));
        a0.setXi(1, this.a_x.getF_xo(x0) / (h));
        A.setRow(0, a0);
        f.setXi(0,
                (((this.f_x.getF_xo(x0) * h) / (2)) + (this.a_x.getF_xo(x0) * alpha))
                        - ((alpha * h * this.b_x.getF_xo(x0)) / (2)));

        double x = x0;
        for (int j = 1; j <= m; j++) {
            x += h;
            Vector v = new Vector(m + 2, true);
            v.setXi(j - 1, this.a_x.getF_xo(x) - ((h * this.b_x.getF_xo(x)) / (2)));
            v.setXi(j, (h * h * this.c_x.getF_xo(x)) - (2 * this.a_x.getF_xo(x)));
            v.setXi(j + 1, this.a_x.getF_xo(x) + ((h * this.b_x.getF_xo(x)) / (2)));
            v = Vector.scalarMul(v, 1 / (h * h));
            A.setRow(j, v);
            f.setXi(j, this.f_x.getF_xo(x));

        }

        final double xm_1 = interval.getB();

        final Vector am_1 = new Vector(m + 2, true);
        am_1.setXi(m, this.a_x.getF_xo(xm_1) / h);
        am_1.setXi(m + 1, (-this.a_x.getF_xo(xm_1) / h) + ((this.c_x.getF_xo(xm_1) * h) / 2));
        A.setRow(m + 1, am_1);
        f.setXi(m + 1,
                (((this.f_x.getF_xo(xm_1) * h) / (2)) - (beta * this.a_x.getF_xo(xm_1)))
                        + ((this.b_x.getF_xo(xm_1) * h) / (2)));

        final EquationSystem se = new EquationSystem(A, f);

        final Vector solution = se.solve();
        x = interval.getA();
        for (int i = 0; i < solution.getLenght(); i++) {

            if (i == (solution.getLenght() - 1)) {
                x = interval.getB();
                result.add(new Point(x, solution.getXi(i)));
            } else {
                x += h;
                result.add(new Point(x, solution.getXi(i)));
            }
        }

        return result;
    }

    /**
     * 
     * solve y'(a)=A y(b)=B
     * 
     * @param interval
     * @param m
     * @param alpha
     * @param beta
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveInitCondition(final Interval interval, final int m, final double alpha, final double beta)
            throws CalculatorException {

        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / (m + 1);

        final Matrix A = new Matrix(m + 1, m + 1);
        final Vector f = new Vector(m + 1, false);

        final double x0 = interval.getA();
        final Vector a0 = new Vector(m + 1, true);
        a0.setXi(0, (-this.a_x.getF_xo(x0) / (h)) + ((this.c_x.getF_xo(x0) * h) / (2)));
        a0.setXi(1, this.a_x.getF_xo(x0) / (h));
        A.setRow(0, a0);
        f.setXi(0,
                (((this.f_x.getF_xo(x0) * h) / (2)) + (this.a_x.getF_xo(x0) * alpha))
                        - ((alpha * h * this.b_x.getF_xo(x0)) / (2)));

        double x = x0;
        for (int j = 1; j <= (m - 1); j++) {
            x += h;
            Vector v = new Vector(m + 1, true);
            v.setXi(j - 1, this.a_x.getF_xo(x) - ((h * this.b_x.getF_xo(x)) / (2)));
            v.setXi(j, (h * h * this.c_x.getF_xo(x)) - (2 * this.a_x.getF_xo(x)));
            v.setXi(j + 1, this.a_x.getF_xo(x) + ((h * this.b_x.getF_xo(x)) / (2)));
            v = Vector.scalarMul(v, 1 / (h * h));
            A.setRow(j, v);
            f.setXi(j, this.f_x.getF_xo(x));

        }

        final double xm = interval.getB();

        Vector am = new Vector(m + 1, true);
        am.setXi(m - 1, this.a_x.getF_xo(xm) - ((h * this.b_x.getF_xo(xm)) / (2)));
        am.setXi(m, (h * h * this.c_x.getF_xo(xm)) - (2 * this.a_x.getF_xo(xm)));
        am = Vector.scalarMul(am, 1 / (h * h));
        A.setRow(m, am);
        f.setXi(m, this.f_x.getF_xo(xm)
                - (beta * ((this.a_x.getF_xo(xm) / (h * h)) + (this.b_x.getF_xo(xm) / (2 * h)))));

        final EquationSystem se = new EquationSystem(A, f);

        final Vector solution = se.solve();
        x = interval.getA();
        for (int i = 0; i < solution.getLenght(); i++) {

            if (i == (solution.getLenght() - 1)) {
                x = interval.getB();
                result.add(new Point(x, solution.getXi(i)));
            } else {
                x += h;
                result.add(new Point(x, solution.getXi(i)));
            }
        }

        return result;

    }

    /**
     * 
     * solve y(a)=A y'(b)=B
     * 
     * @param interval
     * @param m
     * @param alpha
     * @param beta
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveFinalCondition(final Interval interval, final int m, final double alpha, final double beta)
            throws CalculatorException {
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / (m + 1);

        final Matrix A = new Matrix(m + 1, m + 1);
        final Vector f = new Vector(m + 1, false);

        final double x1 = interval.getA() + h;
        Vector a0 = new Vector(m + 1, true);
        a0.setXi(0, (h * h * this.c_x.getF_xo(x1)) - (2 * this.a_x.getF_xo(x1)));
        a0.setXi(1, this.a_x.getF_xo(x1) + ((h * this.b_x.getF_xo(x1)) / (2)));
        a0 = Vector.scalarMul(a0, 1 / (h * h));
        A.setRow(0, a0);
        f.setXi(0, this.f_x.getF_xo(x1)
                - (alpha * ((this.a_x.getF_xo(x1) / (h * h)) - ((this.b_x.getF_xo(x1) / 2) * h))));

        double x = x1;
        for (int j = 1; j <= (m - 1); j++) {
            x += h;
            Vector v = new Vector(m + 1, true);
            v.setXi(j - 1, this.a_x.getF_xo(x) - ((h * this.b_x.getF_xo(x)) / (2)));
            v.setXi(j, (h * h * this.c_x.getF_xo(x)) - (2 * this.a_x.getF_xo(x)));
            v.setXi(j + 1, this.a_x.getF_xo(x) + ((h * this.b_x.getF_xo(x)) / (2)));
            v = Vector.scalarMul(v, 1 / (h * h));
            A.setRow(j, v);
            f.setXi(j, this.f_x.getF_xo(x));

        }

        final double xm = interval.getB();

        final Vector am = new Vector(m + 1, true);
        am.setXi(m - 1, this.a_x.getF_xo(xm) / h);
        am.setXi(m, (-this.a_x.getF_xo(xm) / h) + ((this.c_x.getF_xo(xm) * h) / 2));
        A.setRow(m, am);
        f.setXi(m, (((this.f_x.getF_xo(xm) * h) / (2)) - (beta * this.a_x.getF_xo(xm)))
                + ((this.b_x.getF_xo(xm) * h) / (2)));

        final EquationSystem se = new EquationSystem(A, f);

        final Vector solution = se.solve();
        x = interval.getA();
        for (int i = 0; i < solution.getLenght(); i++) {

            if (i == (solution.getLenght() - 1)) {
                x = interval.getB();
                result.add(new Point(x, solution.getXi(i)));
            } else {
                x += h;
                result.add(new Point(x, solution.getXi(i)));
            }
        }

        return result;
    }

}
