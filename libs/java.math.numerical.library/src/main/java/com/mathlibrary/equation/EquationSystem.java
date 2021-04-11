package com.mathlibrary.equation;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.matrix.Matrix;
import com.mathlibrary.matrix.MatrixElement;
import com.mathlibrary.matrix.Vector;
import com.mathlibrary.util.UtilMatrix;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 *         System Ecuation
 * 
 */
public class EquationSystem {

    // Ax=b
    private final Matrix A;
    private final Vector b;

    /**
     * definition
     * 
     * @param A
     * @param b
     * @throws CalculatorException
     */
    public EquationSystem(final Matrix A, final Vector b) throws CalculatorException {
        if (!A.isSquare()) {
            throw new CalculatorException("The matrix is not square");
        }
        if (b.getLenght() != A.getRows()) {
            throw new CalculatorException("Is not the same b's lenght and A's rows ");
        }

        this.A = A;
        this.b = b;
        final Vector x = new Vector(b.getLenght(), false);
        int n = 0;
        for (int i = 0; i < x.getLenght(); i++) {
            x.setXi(i, n);
            n++;
        }
    }

    /**
     * solve
     * 
     * 
     * @return
     * @throws CalculatorException
     */
    public Vector solve() throws CalculatorException {
        if (this.A.isSquare()) {
            final Vector x = createXvector(this.A);
            return solveEcuation(this.A, this.b, x);
        } else {
            throw new CalculatorException("The matrix is not square");
        }
    }

    /**
     * 
     * solveEcuation
     * 
     * @param A
     * @param b
     * @param x
     * @return
     * @throws CalculatorException
     */
    private Vector solveEcuation(final Matrix A, final Vector b, final Vector x) throws CalculatorException {
        // Gauss method

        for (int k = 0; k < (A.getRows() - 1); k++) {
            sortSystem(A, b, x, k, k);
            for (int i = k + 1; i < A.getRows(); i++) {
                final double xkk = A.getElement(k, k);
                if (xkk == 0) {
                    throw new CalculatorException("Det(A) = 0 => the matrix has not inverse");

                } else {

                    final double xik = A.getElement(i, k);
                    if (xik == 0) {
                        continue;
                    }
                    // Matrix
                    final Vector operation = Vector.scalarMul(A.getRow(k), xik / xkk);
                    final Vector newRow = Vector.sub(A.getRow(i), operation);
                    A.setRow(i, newRow);

                    // Vector b
                    final double xk = b.getXi(k);
                    final double xi = b.getXi(i);
                    b.setXi(i, xi - ((xik / xkk) * xk));
                }

            }
        }

        final Vector solution = new Vector(A.getColumns(), false);
        for (int k = A.getColumns() - 1; k >= 0; k--) {
            for (int i = k; i >= 0; i--) {
                double xi = 0;
                if (k == (A.getColumns() - 1)) {
                    if (A.getElement(k, k) == 0) {
                        throw new CalculatorException("Det(A) = 0 => the matrix has not inverse");

                    } else {
                        xi = b.getXi(k) / A.getElement(k, k);
                    }
                } else {
                    if (A.getElement(k, k) == 0) {
                        throw new CalculatorException("Det(A) = 0 => the matrix has not inverse");

                    } else {
                        xi = b.getXi(k) / A.getElement(k, k);
                        for (int l = k + 1; l < A.getColumns(); l++) {
                            xi = xi - ((A.getElement(k, l) / A.getElement(k, k)) * solution.getXi(l));
                        }
                    }
                }
                solution.setXi(k, xi);
            }

        }

        return sortSolution(solution, x);
    }

    /**
     * 
     * createXvector
     * 
     * @param A
     * @return
     * @throws CalculatorException
     */
    private Vector createXvector(final Matrix A) throws CalculatorException {
        final Vector x = new Vector(A.getColumns(), true);
        for (int i = 0; i < x.getLenght(); i++) {
            x.setXi(i, i);
        }
        return x;
    }

    /**
     * 
     * sortSolution
     * 
     * @param solution
     * @param x
     * @return
     * @throws CalculatorException
     */
    private Vector sortSolution(final Vector solution, final Vector x) throws CalculatorException {
        if (solution.getLenght() == x.getLenght()) {
            final Vector sol = new Vector(x.getLenght(), false);
            for (int i = 0; i < x.getLenght(); i++) {
                sol.setXi((int) x.getXi(i), solution.getXi(i));
            }
            return sol;
        } else {
            throw new CalculatorException("General Error");
        }

    }

    /**
     * 
     * sortSystem
     * 
     * @param A
     * @param b
     * @param x
     * @param row
     * @param column
     * @throws CalculatorException
     */
    private void sortSystem(final Matrix A, final Vector b, final Vector x, final int row, final int column)
            throws CalculatorException {

        final MatrixElement element = A.getMaxij(row, column);
        if ((element.getRow() < row) || (element.getColumn() < column)) {
            throw new CalculatorException("General Error");
        } else {
            if (row != element.getRow()) {
                A.changeRows(row, element.getRow());
                b.changeElement(row, element.getRow());
            }

            if (column != element.getColumn()) {
                A.changeColumns(column, element.getColumn());
                x.changeElement(column, element.getColumn());
            }
        }

    }

    /**
     * 
     * sortSystem
     * 
     * @param A
     * @param I
     * @param row
     * @param column
     * @throws CalculatorException
     */
    private static void sortSystem(final Matrix A, final Matrix I, final int row, final int column)
            throws CalculatorException {

        final MatrixElement element = A.getMaxRow(row, column);

        if ((element.getRow() < row) || (element.getColumn() < column)) {
            throw new CalculatorException("General Error");
        } else {
            if (row != element.getRow()) {
                A.changeRows(row, element.getRow());
                I.changeRows(row, element.getRow());
            }

            /*
             * if (column != element.getColumn()){ A.changeColumns(column, element.getColumn()); I.changeColumns(column,
             * element.getColumn()); }
             */
        }

    }

    /**
     * 
     * sortSystem
     * 
     * @param A
     * @param numChanges
     * @param row
     * @param column
     * @return
     * @throws CalculatorException
     */
    private static int sortSystem(final Matrix A, int numChanges, final int row, final int column)
            throws CalculatorException {

        final MatrixElement element = A.getMaxij(row, column);

        if ((element.getRow() < row) || (element.getColumn() < column)) {
            throw new CalculatorException("General Error");
        } else {
            if (row != element.getRow()) {
                A.changeRows(row, element.getRow());
                numChanges++;
            }

            if (column != element.getColumn()) {
                A.changeColumns(column, element.getColumn());
                numChanges++;
            }
        }

        return numChanges;

    }

    /**
     * 
     * Determinat
     * 
     * @param M
     * @return
     * @throws CalculatorException
     */
    public static double Det(final Matrix M) throws CalculatorException {
        if (M.isSquare()) {
            final Matrix A = M.clone();
            double result = 1;
            int numchanges = 0;

            for (int k = 0; k < (A.getRows() - 1); k++) {
                numchanges = sortSystem(A, numchanges, k, k);
                for (int i = k + 1; i < A.getRows(); i++) {
                    final double xkk = A.getElement(k, k);
                    if (xkk == 0) {
                        return 0;
                    } else {

                        final double xik = A.getElement(i, k);
                        if (xik == 0) {
                            continue;
                        }
                        // Matrix
                        final Vector operation = Vector.scalarMul(A.getRow(k), xik / xkk);
                        final Vector newRow = Vector.sub(A.getRow(i), operation);
                        A.setRow(i, newRow);
                    }
                }
            }

            for (int k = 0; k < A.getRows(); k++) {
                result = result * A.getElement(k, k);
            }
            result = result * Math.pow(-1, numchanges);
            return result;
        } else {
            throw new CalculatorException("The matrix is not square");
        }
    }

    /**
     * inverse
     * 
     * 
     * @param M
     * @return
     * @throws CalculatorException
     */
    public static Matrix inverse(final Matrix M) throws CalculatorException {
        if (M.isSquare()) {
            final Matrix I = UtilMatrix.getI(M.getRows());
            final Matrix A = M.clone();
            for (int k = 0; k < (A.getRows() - 1); k++) {
                sortSystem(A, I, k, k);
                for (int i = k + 1; i < A.getRows(); i++) {
                    final double xkk = A.getElement(k, k);
                    if (xkk == 0) {
                        throw new CalculatorException("Det(A) = 0 => the matrix has not inverse");

                    } else {

                        final double xik = A.getElement(i, k);
                        if (xik == 0) {
                            continue;
                        }
                        // Matrix
                        final Vector operation = Vector.scalarMul(A.getRow(k), xik / xkk);
                        final Vector newRow = Vector.sub(A.getRow(i), operation);
                        A.setRow(i, newRow);

                        final Vector operationI = Vector.scalarMul(I.getRow(k), xik / xkk);
                        final Vector newRowI = Vector.sub(I.getRow(i), operationI);
                        I.setRow(i, newRowI);
                    }
                }
            }

            for (int k = 0; k < (A.getRows() - 1); k++) {

                for (int i = k + 1; i < A.getRows(); i++) {
                    final double xii = A.getElement(i, i);
                    if (xii == 0) {
                        throw new CalculatorException("Det(A) = 0 => the matrix has not inverse");

                    } else {

                        final double xki = A.getElement(k, i);
                        if (xki == 0) {
                            continue;
                        }
                        // Matrix
                        final Vector operation = Vector.scalarMul(A.getRow(i), xki / xii);
                        final Vector newRow = Vector.sub(A.getRow(k), operation);
                        A.setRow(k, newRow);

                        final Vector operationI = Vector.scalarMul(I.getRow(i), xki / xii);
                        final Vector newRowI = Vector.sub(I.getRow(k), operationI);
                        I.setRow(k, newRowI);
                    }
                }
            }

            //

            for (int i = 0; i < A.getRows(); i++) {
                final double xii = A.getElement(i, i);
                final Vector newRow = Vector.scalarDiv(A.getRow(i), xii);
                A.setRow(i, newRow);
                final Vector newRowI = Vector.scalarDiv(I.getRow(i), xii);
                I.setRow(i, newRowI);
            }

            return I;

        } else {
            throw new CalculatorException("The matrix is not square");
        }

    }

}
