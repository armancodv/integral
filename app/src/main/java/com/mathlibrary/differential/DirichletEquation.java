package com.mathlibrary.differential;

import java.util.ArrayList;
import java.util.List;

//import com.mathemagic.matrix.MatrixOpertations;


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
 *         Dirichlet Ecuation
 */
public class DirichletEquation {
	
	//ODE equation --> a(x)*y'' + b(x)*y' + c(x)*y = f(x)
	    
		//Dirichlet boundary condition --> y(a)=A , y(b)=B || A = alpha, B = Beta

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
     * 
     * Dirichlet Ecuation
     * 
     * @param a_x
     * @param b_x
     * @param c_x
     * @param f_x
     */
    public DirichletEquation(final String a_x, final String b_x, final String c_x, final String f_x) {

        this.a_x = new FunctionX(a_x);
        this.b_x = new FunctionX(b_x);
        this.c_x = new FunctionX(c_x);
        this.f_x = new FunctionX(f_x);
    }

    /**
     * 
     * solve problem
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

        Matrix A = new Matrix(m, m);
        final Vector f = new Vector(m, false);

        result.add(new Point(interval.getA(), alpha));

        // j == 0 => m==1
        final double x1 = interval.getA() + h;
        final Vector a0 = new Vector(m, true);
        a0.setXi(0, (h * h * this.c_x.getF_xo(x1)) - (2 * this.a_x.getF_xo(x1)));
        a0.setXi(1, this.a_x.getF_xo(x1) + ((h * this.b_x.getF_xo(x1)) / (2)));
        A.setRow(0, a0);
        f.setXi(0, this.f_x.getF_xo(x1)
                - (alpha * ((this.a_x.getF_xo(x1) / (h * h)) - ((this.b_x.getF_xo(x1) / 2) * h))));

        double x = x1;
        for (int j = 1; j <= (m - 2); j++) {
            x += h;
            final Vector v = new Vector(m, true);
            v.setXi(j - 1, this.a_x.getF_xo(x) - ((h * this.b_x.getF_xo(x)) / (2)));
            v.setXi(j, (h * h * this.c_x.getF_xo(x)) - (2 * this.a_x.getF_xo(x)));
            v.setXi(j + 1, this.a_x.getF_xo(x) + ((h * this.b_x.getF_xo(x)) / (2)));
            A.setRow(j, v);
            f.setXi(j, this.f_x.getF_xo(x));

        }

        final double xm = x + h;

        final Vector am_1 = new Vector(m, true);
        am_1.setXi(m - 2, this.a_x.getF_xo(xm) - ((h * this.b_x.getF_xo(xm)) / (2)));
        am_1.setXi(m - 1, (h * h * this.c_x.getF_xo(xm)) - (2 * this.a_x.getF_xo(xm)));
        A.setRow(m - 1, am_1);
        f.setXi(m - 1, this.f_x.getF_xo(xm)
                - (beta * ((this.a_x.getF_xo(xm) / (h * h)) + (this.b_x.getF_xo(xm) / (2 * h)))));

        A = Matrix.mul(1 / (h * h), A);
        final EquationSystem se = new EquationSystem(A, f);

        final Vector solution = se.solve();
        x = interval.getA();
        for (int i = 0; i < solution.getLenght(); i++) {
            x += h;
            result.add(new Point(x, solution.getXi(i)));
        }

        result.add(new Point(interval.getB(), beta));

        return result;
    }

}
