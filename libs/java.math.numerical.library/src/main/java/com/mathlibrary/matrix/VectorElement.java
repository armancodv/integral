package com.mathlibrary.matrix;


/**
 * 
 * @author Sergio Besada
 *
 */
public class VectorElement {

    /**
     * values
     */
    private double xi;

    /**
     * position
     */
    private int i;

    /**
     * VectorElement
     * 
     * @param xi
     * @param i
     */
    public VectorElement(final double xi, final int i) {
        this.xi = xi;
        this.i = i;
    }

    /**
     * 
     * getXi
     * 
     * @return
     */
    public double getXi() {
        return this.xi;
    }

    /**
     * 
     * setXi
     * 
     * @param xi
     */
    public void setXi(final double xi) {
        this.xi = xi;
    }

    /**
     * 
     * getter i
     * 
     * @return
     */
    public int getI() {
        return this.i;
    }

    /**
     * setter i
     * 
     * 
     * @param i
     */
    public void setI(final int i) {
        this.i = i;
    }

}
