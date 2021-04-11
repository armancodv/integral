package com.mathlibrary.matrix;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.exception.CalculatorException;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Vector implements Cloneable {

    private boolean isRow = true; // if not is row is column

    /**
     * the vector values
     */
    private double[] x;

    /**
     * Vector
     * 
     * @param n
     * @param isRow
     */
    public Vector(final int n, final boolean isRow) {
        this.isRow = isRow;
        this.x = new double[n];
        for (int i = 0; i < n; i++) {
            this.x[i] = 0.0;
        }

    }

    /**
     * Vector
     * 
     * @param list
     * @param isRow
     */
    public Vector(final List<Double> list, final boolean isRow) {
        this.isRow = isRow;
        this.x = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.x[i] = list.get(i);
        }
    }

    /**
     * Vector
     * 
     * @param x
     * @param isRow
     */
    public Vector(final double[] x, final boolean isRow) {
        this.isRow = isRow;
        this.x = x;
    }

    /**
     * 
     * getLenght
     * 
     * @return
     */
    public int getLenght() {
        return this.x.length;
    }

    /**
     * 
     * getX
     * 
     * @return
     */
    public double[] getX() {
        return this.x;
    }

    /**
     * 
     * setX
     * 
     * @param x
     */
    public void setX(final double[] x) {
        this.x = x;
    }

    /**
     * 
     * setXi
     * 
     * @param i
     * @param xo
     * @throws CalculatorException
     */
    public void setXi(final int i, final double xo) throws CalculatorException {
        if (this.x.length > i) {
            this.x[i] = xo;
        } else {
            throw new CalculatorException("i > x.lenght");
        }
    }

    /**
     * 
     * getXi
     * 
     * @param i
     * @return
     * @throws CalculatorException
     */
    public double getXi(final int i) throws CalculatorException {
        if (this.x.length > i) {
            return this.x[i];
        } else {
            throw new CalculatorException("i > x.lenght");
        }
    }

    /**
     * 
     * isRow
     * 
     * @return
     */
    public boolean isRow() {
        return this.isRow;
    }

    /**
     * 
     * setRow
     * 
     * @param isRow
     */
    public void setRow(final boolean isRow) {
        this.isRow = isRow;
    }

    /**
     * 
     * changeElement
     * 
     * @param element1
     * @param element2
     * @throws CalculatorException
     */
    public void changeElement(final int element1, final int element2) throws CalculatorException {
        if ((element1 >= 0) && (element1 <= this.getLenght()) &&
                (element2 >= 0) && (element2 <= this.getLenght())) {
            final double x1 = this.getXi(element1);
            final double x2 = this.getXi(element2);
            this.setXi(element1, x2);
            this.setXi(element2, x1);

        } else {
            throw new CalculatorException("No valid elemnts");
        }

    }

    /**
     * 
     * Round
     * 
     * @param numDecimals
     */
    public void Round(final int numDecimals) {
        for (int i = 0; i < this.getLenght(); i++) {
            try {
                this.setXi(i, com.mathlibrary.util.Round.rint(this.getXi(i), numDecimals));
            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @Override
    public Vector clone() {
        final Vector v = new Vector(this.getLenght(), this.isRow);
        for (int i = 0; i < v.getLenght(); i++) {
            try {
                v.setXi(i, this.getXi(i));
            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return v;
    }

    /**
     * 
     * getMaxValue
     * 
     * @return
     * @throws CalculatorException
     */
    public double getMaxValue() throws CalculatorException {
        double result = 0;
        for (int i = 0; i < this.getLenght(); i++) {
            if (Math.abs(this.getXi(i)) > result) {
                result = this.getXi(i);
            }
        }
        return result;
    }

    /**
     * 
     * scalarMul
     * 
     * @param v1
     * @param x
     * @return
     * @throws CalculatorException
     */
    public static Vector scalarMul(final Vector v1, final double x) throws CalculatorException {
        final Vector sol = new Vector(v1.getLenght(), v1.isRow);
        for (int i = 0; i < v1.getLenght(); i++) {
            sol.setXi(i, x * v1.getXi(i));
        }
        return sol;
    }

    /**
     * 
     * scalarDiv
     * 
     * @param v1
     * @param x
     * @return
     * @throws CalculatorException
     */
    public static Vector scalarDiv(final Vector v1, final double x) throws CalculatorException {
        if (x == 0) {
            throw new CalculatorException("Is not possible divide by 0");
        }
        final Vector sol = new Vector(v1.getLenght(), v1.isRow);
        for (int i = 0; i < v1.getLenght(); i++) {
            sol.setXi(i, v1.getXi(i) / x);
        }
        return sol;
    }

    /**
     * 
     * add
     * 
     * @param v1
     * @param v2
     * @return
     * @throws CalculatorException
     */
    public static Vector add(final Vector v1, final Vector v2) throws CalculatorException {
        if (v1.getLenght() == v2.getLenght()) {
            if (v1.isRow == v2.isRow) {
                final Vector sol = new Vector(v1.getLenght(), v1.isRow);
                for (int i = 0; i < sol.getLenght(); i++) {
                    sol.setXi(i, v1.getXi(i) + v2.getXi(i));
                }
                return sol;
            } else {
                throw new CalculatorException("the vectors are not rows or columns");
            }

        } else {
            throw new CalculatorException("vectors' lenght are different");
        }
    }

    /**
     * 
     * sub
     * 
     * @param v1
     * @param v2
     * @return
     * @throws CalculatorException
     */
    public static Vector sub(final Vector v1, final Vector v2) throws CalculatorException {
        if (v1.getLenght() == v2.getLenght()) {
            if (v1.isRow == v2.isRow) {
                final Vector sol = new Vector(v1.getLenght(), v1.isRow);
                for (int i = 0; i < sol.getLenght(); i++) {
                    sol.setXi(i, v1.getXi(i) - v2.getXi(i));
                }
                return sol;
            } else {
                throw new CalculatorException("the vectors are not rows or columns");
            }

        } else {
            throw new CalculatorException("vectors' lenght are different");
        }
    }

    /**
     * 
     * multiplyRowByColumn
     * 
     * @param row
     * @param column
     * @return
     * @throws CalculatorException
     */
    public static double multiplyRowByColumn(final Vector row, final Vector column) throws CalculatorException {
        double x = 0;
        if (row.isRow && !column.isRow()) {
            if (row.getLenght() == column.getLenght()) {
                for (int i = 0; i < row.getLenght(); i++) {
                    x = x + (row.getXi(i) * column.getXi(i));
                }
            } else {
                throw new CalculatorException("vectors' lenght is different");
            }
        } else {
            throw new CalculatorException("vectors are not appropriate");
        }
        return x;
    }

    /**
     * 
     * multiplyColumnbyRow
     * 
     * @param column
     * @param row
     * @return
     * @throws CalculatorException
     */
    public static Matrix multiplyColumnbyRow(final Vector column, final Vector row) throws CalculatorException {
        final Matrix M = new Matrix(column.getLenght(), row.getLenght());
        if (row.isRow && !column.isRow()) {
            for (int i = 0; i < column.getLenght(); i++) {
                for (int j = 0; j < row.getLenght(); j++) {
                    final double xij = column.getXi(i) * row.getXi(j);
                    M.setElement(i, j, xij);
                }
            }
        } else {
            throw new CalculatorException("vectors are not appropiate");
        }

        return M;
    }

    /**
     * 
     * maximunNorm
     * 
     * @return
     */
    public double maximunNorm() {
        double result = 0;
        try {
            result = this.getXi(0);
            for (int i = 1; i < this.getLenght(); i++) {
                if (this.getXi(i) > result) {
                    result = this.getXi(i);
                }
            }
        } catch (final CalculatorException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 
     * EuclideanNorm
     * 
     * @return
     */
    public double EuclideanNorm() {
        double result = 0;
        try {
            for (int i = 0; i < this.getLenght(); i++) {
                result = result + Math.pow(this.getXi(i), 2);
            }
            result = Math.sqrt(result);
        } catch (final CalculatorException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 
     * getList
     * 
     * @return
     * @throws CalculatorException
     */
    public List<Double> getList() throws CalculatorException {
        final List<Double> list = new ArrayList<Double>();
        for (int i = 0; i < this.getLenght(); i++) {
            final Double d = new Double(this.getXi(i));
            list.add(d);
        }
        return list;
    }

    /**
     * 
     * min
     * 
     * @return
     * @throws CalculatorException
     */
    public VectorElement min() throws CalculatorException {
        final VectorElement min = new VectorElement(this.getXi(0), 0);
        for (int i = 1; i < this.getLenght(); i++) {
            if (this.getXi(i) < min.getXi()) {
                min.setXi(this.getXi(i));
                min.setI(i);
            }
        }
        return min;
    }

    /**
     * 
     * traspose
     * 
     * @param v
     * @return
     */
    public static Vector traspose(final Vector v) {
        final Vector result = v.clone();
        if (v.isRow()) {
            result.setRow(false);
        } else {
            result.setRow(true);
        }

        return result;
    }

}
