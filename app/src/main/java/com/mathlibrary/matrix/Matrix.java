package com.mathlibrary.matrix;

import java.util.List;

import com.mathlibrary.equation.EquationSystem;
import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.util.Round;
import com.mathlibrary.util.UtilMatrix;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class Matrix implements Cloneable {
    public static final int numDecimals = 4;

    /**
     * num rows
     */
    private int rows = 0;

    /**
     * num colummns
     */
    private int columns = 0;

    /**
     * the matrix
     */
    private double[][] x;

    /**
     * Matrix
     * 
     * @param rows
     * @param columns
     */
    public Matrix(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        this.x = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.x[i][j] = 0.0;
            }
        }
    }

    /**
     * Matrix
     */
    public Matrix(final List<Vector> vectors) throws CalculatorException {
        boolean isRow = true;
        int vectorLenght = 0;
        if ((vectors != null) && !vectors.isEmpty()) {
            isRow = vectors.get(0).isRow();
            vectorLenght = vectors.get(0).getLenght();
            if (isRow) {
                this.rows = vectors.size();
                this.columns = vectorLenght;
                this.x = new double[this.rows][this.columns];

            } else {
                this.rows = vectorLenght;
                this.columns = vectors.size();
                this.x = new double[this.rows][this.columns];
            }

        } else {
            throw new CalculatorException("vectors is empty");
        }

        int numVector = 0;
        for (final Vector vector : vectors) {
            if (vector.isRow() != isRow) {
                throw new CalculatorException("there are rows and columns in the matrix");
            } else {
                if (vector.getLenght() == vectorLenght) {
                    if (isRow = true) {
                        for (int j = 0; j < vector.getLenght(); j++) {
                            this.x[numVector][j] = vector.getXi(j);
                        }
                    } else {
                        for (int i = 0; i < vector.getLenght(); i++) {
                            this.x[i][numVector] = vector.getXi(i);
                        }
                    }
                } else {
                    throw new CalculatorException("vectors' lenght is different");
                }
            }
            numVector++;
        }
    }

    /**
     * 
     * getElement
     * 
     * @param row
     * @param column
     * @return
     * @throws CalculatorException
     */
    public double getElement(final int row, final int column) throws CalculatorException {

        if ((row < this.rows) && (column < this.columns)) {
            return this.x[row][column];
        } else {
            throw new CalculatorException("there is no element");
        }

    }

    /**
     * 
     * setElement
     * 
     * @param row
     * @param column
     * @param xij
     * @throws CalculatorException
     */
    public void setElement(final int row, final int column, final double xij) throws CalculatorException {
        if ((row < this.rows) && (column < this.columns)) {
            this.x[row][column] = xij;
        } else {
            throw new CalculatorException("there is no element");
        }

    }

    /**
     * 
     * isSquare
     * 
     * @return
     */
    public boolean isSquare() {
        if (this.rows == this.columns) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 
     * getRows
     * 
     * @return
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * 
     * setRows
     * 
     * @param rows
     */
    public void setRows(final int rows) {
        this.rows = rows;
    }

    /**
     * 
     * getColumns
     * 
     * @return
     */
    public int getColumns() {
        return this.columns;
    }

    /**
     * 
     * setColumns
     * 
     * @param columns
     */
    public void setColumns(final int columns) {
        this.columns = columns;
    }

    /**
     * 
     * get determinant
     * 
     * @return
     * @throws CalculatorException
     */
    public double getDet() throws CalculatorException {
        return Round.rint(EquationSystem.Det(this), numDecimals);
    }

    /**
     * get inverse
     * 
     * 
     * @return
     * @throws CalculatorException
     */
    public Matrix getInv() throws CalculatorException {
        return EquationSystem.inverse(this);
    }

    /**
     * 
     * get row
     * 
     * @param row
     * @return
     * @throws CalculatorException
     */
    public Vector getRow(final int row) throws CalculatorException {
        final Vector v = new Vector(this.columns, true);
        for (int j = 0; j < this.columns; j++) {
            v.setXi(j, this.getElement(row, j));
        }
        return v;
    }

    /**
     * 
     * getRow
     * 
     * @param row
     * @param columns
     * @return
     * @throws CalculatorException
     */
    public Vector getRow(final int row, final int col) throws CalculatorException {

        final Vector v = new Vector(col, true);
        for (int j = 0; j < col; j++) {
            v.setXi(j, this.getElement(row, j));
        }

        return v;
    }

    /**
     * 
     * setRow
     * 
     * @param row
     * @param v
     * @throws CalculatorException
     */
    public void setRow(final int row, final Vector v) throws CalculatorException {
        if (v.isRow()) {
            if (v.getLenght() == this.columns) {

                for (int j = 0; j < this.columns; j++) {
                    this.setElement(row, j, v.getXi(j));
                }
            } else {
                throw new CalculatorException("Is not the same vector's lenght and matrix's columns");
            }
        } else {
            throw new CalculatorException("The vector is not row");
        }
    }

    /**
     * 
     * getColumn
     * 
     * @param column
     * @return
     * @throws CalculatorException
     */
    public Vector getColumn(final int column) throws CalculatorException {
        final Vector v = new Vector(this.rows, false);
        for (int i = 0; i < this.rows; i++) {
            v.setXi(i, this.getElement(i, column));
        }
        return v;
    }

    /**
     * 
     * getColumn
     * 
     * @param column
     * @param rows
     * @return
     * @throws CalculatorException
     */
    public Vector getColumn(final int column, final int rows) throws CalculatorException {

        final Vector v = new Vector(rows, false);
        for (int j = 0; j < rows; j++) {
            v.setXi(j, this.getElement(column, j));
        }

        return v;
    }

    /**
     * 
     * setColumn
     * 
     * @param column
     * @param v
     * @throws CalculatorException
     */
    public void setColumn(final int column, final Vector v) throws CalculatorException {
        if (!v.isRow()) {
            if (v.getLenght() == this.rows) {

                for (int i = 0; i < this.rows; i++) {
                    this.setElement(i, column, v.getXi(i));
                }
            } else {
                throw new CalculatorException("Is not the same vector's lenght and matrix's rows");
            }
        } else {
            throw new CalculatorException("The vector is not column");
        }
    }

    /**
     * 
     * changeRows
     * 
     * @param row1
     * @param row2
     * @throws CalculatorException
     */
    public void changeRows(final int row1, final int row2) throws CalculatorException {

        final Vector vrow1 = this.getRow(row1);
        final Vector vrow2 = this.getRow(row2);
        this.setRow(row2, vrow1);
        this.setRow(row1, vrow2);
    }

    /**
     * 
     * changeColumns
     * 
     * @param column1
     * @param column2
     * @throws CalculatorException
     */
    public void changeColumns(final int column1, final int column2) throws CalculatorException {
        final Vector vcolumn1 = this.getColumn(column1);
        final Vector vcolumn2 = this.getColumn(column2);
        this.setColumn(column2, vcolumn1);
        this.setColumn(column1, vcolumn2);

    }

    @Override
    public Matrix clone() {
        final Matrix matrix = new Matrix(this.getColumns(), this.getColumns());
        for (int i = 0; i < this.getRows(); i++) {
            try {
                final Vector vrow = this.getRow(i);
                matrix.setRow(i, vrow);
            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return matrix;
    }

    /**
     * 
     * getMaxij
     * 
     * @return
     */
    public MatrixElement getMaxij() {
        double xij = 0;
        int row = 0;
        int column = 0;

        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getColumns(); j++) {
                try {
                    if (Math.abs(this.getElement(i, j)) > xij) {
                        xij = this.getElement(i, j);
                        row = i;
                        column = j;
                    }
                } catch (final CalculatorException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        return new MatrixElement(row, column, xij);
    }

    /**
     * 
     * getMaxRow
     * 
     * @param arow
     * @param acolumn
     * @return
     * @throws CalculatorException
     */
    public MatrixElement getMaxRow(final int arow, final int acolumn) throws CalculatorException {
        if (acolumn >= this.columns) {
            throw new CalculatorException("there is no element");
        }
        double xij = 0;

        int column = 0;

        for (int j = acolumn; j < this.getColumns(); j++) {
            try {
                if (Math.abs(this.getElement(arow, j)) > xij) {
                    xij = this.getElement(arow, j);

                    column = j;
                }
            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return new MatrixElement(arow, column, xij);

    }

    /**
     * 
     * getMaxColumn
     * 
     * @param arow
     * @param acolumn
     * @return
     * @throws CalculatorException
     */
    public MatrixElement getMaxColumn(final int arow, final int acolumn) throws CalculatorException {
        if (acolumn >= this.columns) {
            throw new CalculatorException("there is no element");
        }
        double xij = 0;

        int row = 0;

        for (int i = arow; i < this.getRows(); i++) {
            try {
                if (Math.abs(this.getElement(i, acolumn)) > xij) {
                    xij = this.getElement(i, acolumn);

                    row = i;
                }
            } catch (final CalculatorException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return new MatrixElement(row, acolumn, xij);

    }

    /**
     * 
     * getMaxij
     * 
     * @param arow
     * @param acolumn
     * @return
     * @throws CalculatorException
     */
    public MatrixElement getMaxij(final int arow, final int acolumn) throws CalculatorException {

        if ((arow >= this.rows) && (acolumn >= this.columns)) {
            throw new CalculatorException("there is no element");
        }

        double xij = 0;
        int row = 0;
        int column = 0;

        for (int i = arow; i < this.getRows(); i++) {
            for (int j = acolumn; j < this.getColumns(); j++) {
                try {
                    if (Math.abs(this.getElement(i, j)) > xij) {
                        xij = this.getElement(i, j);
                        row = i;
                        column = j;
                    }
                } catch (final CalculatorException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

        return new MatrixElement(row, column, xij);
    }

    /**
     * 
     * diagonal
     * 
     * @return
     * @throws CalculatorException
     */
    public Vector diagonal() throws CalculatorException {
        if (this.isSquare()) {
            final Vector diag = new Vector(this.getRows(), true);
            for (int i = 0; i < this.getRows(); i++) {
                diag.setXi(i, this.getElement(i, i));
            }
            return diag;
        } else {
            throw new CalculatorException("A is not square");
        }
    }

    /**
     * 
     * trace
     * 
     * @return
     * @throws CalculatorException
     */
    public double trace() throws CalculatorException {
        double x = 0;
        if (this.isSquare()) {
            for (int i = 0; i < this.getRows(); i++) {
                x = x + this.getElement(i, i);
            }
        } else {
            throw new CalculatorException("The matrix is not square");
        }
        return x;
    }

    /**
     * 
     * multiply
     * 
     * @param v
     * @return
     * @throws CalculatorException
     */
    public Vector multiply(final Vector v) throws CalculatorException {
        Vector result;
        if (v.isRow()) {
            // v*A
            if (v.getLenght() == this.getColumns()) {
                result = new Vector(v.getLenght(), true);
                for (int j = 0; j < this.getColumns(); j++) {
                    final Vector column = this.getColumn(j);
                    final double x = Vector.multiplyRowByColumn(v, column);
                    result.setXi(j, x);
                }
            } else {
                throw new CalculatorException("vector's lenght is not appropriate");
            }
        } else {
            // A*v
            if (v.getLenght() == this.getRows()) {
                result = new Vector(v.getLenght(), false);
                for (int i = 0; i < this.getRows(); i++) {
                    final Vector row = this.getRow(i);
                    final double x = Vector.multiplyRowByColumn(row, v);
                    result.setXi(i, x);
                }
            } else {
                throw new CalculatorException("vector's lenght is not appropriate");
            }
        }

        return result;
    }

    /**
     * 
     * getMaxElementInRow
     * 
     * @param row
     * @return
     * @throws CalculatorException
     */
    public MatrixElement getMaxElementInRow(final int row) throws CalculatorException {

        if (row < this.getRows()) {
            final Vector v = this.getRow(row);
            double x = 0;
            int column = 0;
            for (int i = 0; i < v.getLenght(); i++) {
                if (Math.abs(v.getXi(i)) > x) {
                    x = v.getXi(i);
                    column = i;
                }
            }
            return new MatrixElement(row, column, x);
        } else {
            throw new CalculatorException("there is no element");
        }
    }

    /**
     * 
     * getMaxElementInColumn
     * 
     * @param column
     * @return
     * @throws CalculatorException
     */
    public MatrixElement getMaxElementInColumn(final int column) throws CalculatorException {

        if (column < this.getColumns()) {
            final Vector v = this.getRow(column);
            double x = 0;
            int row = 0;
            for (int i = 0; i < v.getLenght(); i++) {
                if (Math.abs(v.getXi(i)) > x) {
                    x = v.getXi(i);
                    row = i;
                }
            }
            return new MatrixElement(row, column, x);
        } else {
            throw new CalculatorException("there is no element");
        }
    }

    /**
     * 
     * swap
     * 
     * @param a
     * @param b
     * @throws CalculatorException
     */
    public void swap(final MatrixElement a, final MatrixElement b) throws CalculatorException {
        this.setElement(a.getRow(), a.getColumn(), b.getXij());
        this.setElement(b.getRow(), b.getColumn(), a.getXij());
    }

    /**
     * 
     * isSimetric
     * 
     * @return
     * @throws CalculatorException
     */
    public boolean isSimetric() throws CalculatorException {
        boolean result = true;
        if (this.isSquare()) {
            for (int i = 0; i < this.getRows(); i++) {
                if (!result) {
                    break;
                }
                for (int j = i; j < this.getColumns(); j++) {
                    final double value1 = this.getElement(i, j);
                    final double value2 = this.getElement(j, i);
                    if (value1 != value2) {
                        result = false;
                        break;
                    } else {
                        result = true;
                    }
                }
            }

        }
        return result;
    }

    /**
     * 
     * add
     * 
     * @param A
     * @param B
     * @return
     * @throws CalculatorException
     */
    public static Matrix add(final Matrix A, final Matrix B) throws CalculatorException {
        Matrix result = null;
        if ((A.getColumns() == B.getColumns()) && (A.getRows() == B.getRows())) {
            result = new Matrix(A.getRows(), A.getColumns());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.setElement(i, j, A.getElement(i, j) + B.getElement(i, j));
                }
            }
        } else {
            throw new CalculatorException("The matrices have not the same columns or the same rows");
        }

        return result;
    }

    /**
     * 
     * sub
     * 
     * @param A
     * @param B
     * @return
     * @throws CalculatorException
     */
    public static Matrix sub(final Matrix A, final Matrix B) throws CalculatorException {
        Matrix result = null;
        if ((A.getColumns() == B.getColumns()) && (A.getRows() == B.getRows())) {
            result = new Matrix(A.getRows(), A.getColumns());
            for (int i = 0; i < result.getRows(); i++) {
                for (int j = 0; j < result.getColumns(); j++) {
                    result.setElement(i, j, A.getElement(i, j) - B.getElement(i, j));
                }
            }

        } else {
            throw new CalculatorException("The matrices have not the same columns or the same rows");
        }

        return result;
    }

    /**
     * 
     * mul
     * 
     * @param x
     * @param M
     * @return
     */
    public static Matrix mul(final double x, final Matrix M) {
        final Matrix result = M.clone();
        for (int i = 0; i < M.getRows(); i++) {
            for (int j = 0; j < M.getColumns(); j++) {
                try {
                    result.setElement(i, j, x * M.getElement(i, j));
                } catch (final CalculatorException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 
     * mul
     * 
     * @param A
     * @param B
     * @return
     * @throws CalculatorException
     */
    public static Matrix mul(final Matrix A, final Matrix B) throws CalculatorException {
        final Matrix result = new Matrix(A.getRows(), B.getColumns());
        for (int i = 0; i < A.getRows(); i++) {
            final Vector row = A.getRow(i);
            for (int j = 0; j < B.getColumns(); j++) {
                final Vector column = B.getColumn(j);
                final double xij = Vector.multiplyRowByColumn(row, column);
                result.setElement(i, j, xij);
            }
        }

        return result;
    }

    /**
     * 
     * HouseHolderMatrix
     * 
     * @param v
     * @return
     * @throws CalculatorException
     */
    public static Matrix HouseHolderMatrix(final Vector v) throws CalculatorException {
        if (!v.isRow()) {
            Vector u = v.clone();
            Vector e1 = new Vector(u.getLenght(), false);
            e1.setXi(0, 1);
            e1 = Vector.scalarMul(e1, u.EuclideanNorm());
            u = Vector.sub(u, e1);

            final Matrix In = UtilMatrix.getI(u.getLenght());
            final Vector row = Vector.traspose(u);
            Matrix M = Vector.multiplyColumnbyRow(u, row);
            double x = Vector.multiplyRowByColumn(row, u);
            x = 2 / x;
            M = Matrix.mul(x, M);
            M = Matrix.sub(In, M);
            return M;
        } else {
            throw new CalculatorException("Vector is not a column");
        }

    }

}
