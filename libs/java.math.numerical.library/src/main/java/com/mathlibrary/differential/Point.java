package com.mathlibrary.differential;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 *         Point (xi,yi)
 * 
 */
public class Point {

    double xi;
    double yi;

    /**
     * Point
     * 
     * @param xi
     * @param yi
     */
    public Point(final double xi, final double yi) {
        this.xi = xi;
        this.yi = yi;
    }

    /**
     * getter xi
     * 
     * 
     * @return
     */
    public double getXi() {
        return this.xi;
    }

    /**
     * setter xi
     * 
     * 
     * @param xi
     */
    public void setXi(final double xi) {
        this.xi = xi;
    }

    /**
     * getter yi
     * 
     * 
     * @return
     */
    public double getYi() {
        return this.yi;
    }

    /**
     * 
     * setter yi
     * 
     * @param yi
     */
    public void setYi(final double yi) {
        this.yi = yi;
    }

    /**
     * 
     * getList
     * 
     * @return
     */
    public List<Double> getList() {
        final List<Double> result = new ArrayList<Double>();
        result.add(new Double(this.xi));
        result.add(new Double(this.yi));
        return result;
    }

}
