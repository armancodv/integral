package com.mathlibrary.integral;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionXs;
import com.mathlibrary.matrix.Vector;
import com.mathlibrary.series.MultidimensionalFunctionSeries;
import com.mathlibrary.util.Interval;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class MultidimensionalIntegral {

    /**
     * f(x,y,z,...)
     */
    private final FunctionXs f;

    /**
     * variables x,y,z, ....
     */
    private final List<String> vars;

    /**
     * MultidimensionalIntegral
     * 
     * @param f
     * @param vars
     */
    public MultidimensionalIntegral(final String f, final List<String> vars) {
        this.f = new FunctionXs(f);
        this.vars = vars;
    }

    /**
     * quasiMontecarlo
     * 
     * @param intervals
     * @param numPoints
     * @return
     * @throws CalculatorException
     */
    public double quasiMontecarlo(final List<Interval> intervals, final int numPoints) throws CalculatorException {
        double result = 0;
        final List<Vector> values = equallySpace(intervals, numPoints);
        final MultidimensionalFunctionSeries series = new MultidimensionalFunctionSeries(this.f.getF());
        final double mean = series.mean(values, this.vars);
        result = subIntervalsProduct(intervals) * mean;
        return result;
    }

    /**
     * subIntervalsProduct
     * @param intervals
     * @return
     */
    private double subIntervalsProduct(final List<Interval> intervals) {
        double result = 1;
        for (final Interval interval : intervals) {
            result *= (interval.getB() - interval.getA());
        }
        return result;
    }

    
    /**
     * equallySpace
     * @param intervals
     * @param numPoints
     * @return
     * @throws CalculatorException
     */
    private List<Vector> equallySpace(final List<Interval> intervals, final int numPoints) throws CalculatorException {
        final List<Vector> result = new ArrayList<Vector>();
        boolean finish = false;
        final List<List<Double>> points = new ArrayList<List<Double>>();
        for (final Interval interval : intervals) {
            points.add(intervalPoints(interval, numPoints));
        }

        List<Integer> indexes = new ArrayList<Integer>();

        final Vector point = new Vector(points.size(), true);
        for (int i = 0; i < points.size(); i++) {
            final Integer ni = new Integer(0);
            indexes.add(ni);
        }

        while (!finish) {
            if (indexes.get(indexes.size() - 1).intValue() == numPoints) {
                finish = true;
            }
            final Vector vector = new Vector(indexes.size(), true);
            for (int i = 0; i < indexes.size(); i++) {
                vector.setXi(i, points.get(i).get(indexes.get(i)));
            }

            result.add(vector);
            final List<Integer> newIndexes = new ArrayList<Integer>();
            for (final Integer index : indexes) {
                newIndexes.add(modNumber(index, numPoints + 1));
            }
            indexes = newIndexes;

        }

        return result;
    }

    /**
     * modNumber
     * @param number
     * @param module
     * @return
     */
    private Integer modNumber(final Integer number, final int module) {

        if (number.intValue() == (module - 1)) {
            return new Integer(0);
        } else {
            return new Integer(number.intValue() + 1);
        }
    }

    
    /**
     * intervalPoints
     * @param interval
     * @param numPoints
     * @return
     */
    private List<Double> intervalPoints(final Interval interval, final int numPoints) {
        final List<Double> result = new ArrayList<Double>();
        final double space = interval.getB() - interval.getA();
        final double h = space / numPoints;
        double step = interval.getA();
        for (int i = 0; i < numPoints; i++) {
            final Double d = new Double(step);
            step += h;
            result.add(d);
        }
        result.add(interval.getB());
        return result;
    }

}
