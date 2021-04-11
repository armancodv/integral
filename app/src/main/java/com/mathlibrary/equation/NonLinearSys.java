package com.mathlibrary.equation;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.derivative.PartialDerivative;
import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionXs;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.matrix.Vector;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 *         Non linear system
 * 
 */
public class NonLinearSys {

    /**
     * f1(x,y,z,...),f2(x,y,z,...)
     */
    private final List<FunctionXs> functionList;

    /**
     * f1(x,y,z,...),f2(x,y,z,...) ... but in strings
     */
    private final List<String> functions;

    /**
     * (x0,y0,z0...)
     */
    private final List<Double> xo;

    /**
     * x,y,z ...
     */
    private final List<String> vars;

    /**
     * NonLinearSys definition
     * 
     * @param functions
     * @param vars
     * @param xo
     * @throws CalculatorException
     */
    public NonLinearSys(final List<String> functions, final List<String> vars, final List<Double> xo)
            throws CalculatorException {
        this.functionList = new ArrayList<FunctionXs>();
        this.functions = functions;
        this.xo = xo;
        this.vars = vars;
        if (vars == null) {
            throw new CalculatorException("vars not found");
        } else if (xo.size() == 0) {
            throw new CalculatorException("xo not found");
        } else if (vars.size() != xo.size()) {
            throw new CalculatorException("xo length and the number of vars is different");
        }

        for (final String function : functions) {
            final FunctionXs f = new FunctionXs(function);
            this.functionList.add(f);
        }
    }

    /**
     * solve
     * 
     * 
     * @param threshold
     * @param iter
     * @return
     * @throws CalculatorException
     */
    public List<Double> calc(final double threshold, final int iter) throws CalculatorException {
        List<Double> x = this.xo;
        for (int i = 0; i < iter; i++) {
            final Matrix A = getDf_x(x);
            Vector b = getF_xo(x);
            b = Vector.scalarMul(b, -1);

            final EquationSystem se = new EquationSystem(A, b);
            final Vector xi = se.solve();
            x = (Vector.add(new Vector(x, false), xi)).getList();
            if ((xi.EuclideanNorm() < threshold) && (b.EuclideanNorm() < threshold)) {
                break;
            }
        }

        return x;

    }

    /**
     * 
     * getDf_x
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    private Matrix getDf_x(final List<Double> xo) throws CalculatorException {
        final Matrix M = new Matrix(xo.size(), xo.size());

        int i = 0;

        for (final String f : this.functions) {
            int j = 0;
            for (final String var : this.vars) {
                final PartialDerivative pd = new PartialDerivative(f);
                M.setElement(i, j, pd.getDerivative_accurate(xo, this.vars, var));
                j++;
            }
            i++;
        }

        return M;
    }

    /**
     * 
     * getF_xo
     * 
     * @param xo
     * @return
     * @throws CalculatorException
     */
    private Vector getF_xo(final List<Double> xo) throws CalculatorException {
        final Vector v = new Vector(xo.size(), false);
        int i = 0;
        for (final FunctionXs f : this.functionList) {
            v.setXi(i, f.getValue(xo, this.vars));
            i++;
        }

        return v;
    }

}
