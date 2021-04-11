package com.mathlibrary.matrix;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class MatrixElement {

    /**
     * element
     */
    private double xij = 0;

    /**
     * row
     */
    private int row = 0;

    /**
     * column
     */
    private int column = 0;

    /**
     * 
     * 
     * @param row
     * @param column
     * @param xij
     */
    public MatrixElement(final int row, final int column, final double xij) {
        this.row = row;
        this.column = column;
        this.xij = xij;
    }

    /**
     * 
     * getXij
     * 
     * @return
     */
    public double getXij() {
        return this.xij;
    }

    /**
     * 
     * setXij
     * 
     * @param xij
     */
    public void setXij(final double xij) {
        this.xij = xij;
    }

    /**
     * 
     * getRow
     * 
     * @return
     */
    public int getRow() {
        return this.row;
    }

    /**
     * 
     * setRow
     * 
     * @param row
     */
    public void setRow(final int row) {
        this.row = row;
    }

    /**
     * 
     * getColumn
     * 
     * @return
     */
    public int getColumn() {
        return this.column;
    }

    /**
     * 
     * setColumn
     * 
     * @param column
     */
    public void setColumn(final int column) {
        this.column = column;
    }

}
