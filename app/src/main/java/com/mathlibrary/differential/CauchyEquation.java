package com.mathlibrary.differential;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionXs;
import com.mathlibrary.util.Interval;

//Cauchy Problem
/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class CauchyEquation {

    /**
     * f(x,y)
     */
    private final FunctionXs f_xy;
    /**
     * x,y vars
     */
    private final List<String> vars;

    /**
     * (x0,y0)
     */
    private final Point po;

    /**
     * CauchyEcuation
     * 
     * @param f_xy
     * @param po
     * @param vars
     */
    public CauchyEquation(final String f_xy, final Point po, final List<String> vars) {
        this.f_xy = new FunctionXs(f_xy);
        this.po = po;
        this.vars = vars;
    }

    /**
     * 
     * 
     * 
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveOrder1(final Interval interval, final int n) throws CalculatorException {

        if (n < 10) {
            throw new CalculatorException("the number n is too small");
        }
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        for (int i = 1; i < n; i++) {
            x += h;
            result.add(new Point(x, predictorCorrectorOrder1(h, x, result.get(i - 1))));
        }
        result.add(new Point(interval.getB(), predictorCorrectorOrder1(h, interval.getB(),
                result.get(result.size() - 1))));

        return result;
    }

    /**
     * 
     * 
     * 
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveOrder2(final Interval interval, final int n) throws CalculatorException {

        if (n < 10) {
            throw new CalculatorException("the number n is too small");
        }
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        x += h;
        result.add(new Point(x, predictorCorrectorOrder1(h, x, result.get(result.size() - 1))));
        // result.add(new Point(x,predictorCorrectorOrder3(h, x, result.get(result.size()-3),
        // result.get(result.size()-2),result.get(result.size()-1))));
        for (int i = 2; i < n; i++) {
            x += h;
            result.add(new Point(x, predictorCorrectorOrder2(h, x, result.get(i - 2), result.get(i - 1))));
        }
        result.add(new Point(interval.getB(), predictorCorrectorOrder2(h, interval.getB(),
                result.get(result.size() - 2), result.get(result.size() - 1))));

        return result;
    }

    /**
     * 
     * 
     * 
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveOrder3(final Interval interval, final int n) throws CalculatorException {

        if (n < 10) {
            throw new CalculatorException("the number n is too small");
        }
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        x += h;
        result.add(new Point(x, rungeKuttaOrder6(h, this.po.xi, this.po.yi)));
        x += h;
        result.add(new Point(x, predictorCorrectorOrder2(h, x, result.get(result.size() - 2),
                result.get(result.size() - 1))));
        // result.add(new Point(x, rungeKutta(h, result.get(1).xi, result.get(1).yi)));

        // result.add(new Point(x,predictorCorrectorOrder3(h, x, result.get(result.size()-3),
        // result.get(result.size()-2),result.get(result.size()-1))));
        for (int i = 3; i < n; i++) {
            x += h;
            result.add(new Point(x, predictorCorrectorOrder3(h, x, result.get(i - 3), result.get(i - 2),
                    result.get(i - 1))));
        }
        result.add(new Point(interval.getB(), predictorCorrectorOrder3(h, interval.getB(),
                result.get(result.size() - 3), result.get(result.size() - 2), result.get(result.size() - 1))));

        return result;
    }

    public List<Point> solveOrder4(final Interval interval, final int n) throws CalculatorException {
        if (n < 10) {
            throw new CalculatorException("the number n is too small");
        }
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        x += h;
        // result.add(new Point(x, rungeKuttaOrder6(h, po.xi, po.yi)));
        result.add(new Point(x, predictorCorrectorOrder1(h, x, result.get(result.size() - 1))));

        x += h;
        result.add(new Point(x, predictorCorrectorOrder2(h, x, result.get(result.size() - 2),
                result.get(result.size() - 1))));
        // result.add(new Point(x, rungeKutta(h, result.get(1).xi, result.get(1).yi)));

        x += h;
        result.add(new Point(x, predictorCorrectorOrder2(h, x, result.get(result.size() - 2),
                result.get(result.size() - 1))));

        // result.add(new Point(x,predictorCorrectorOrder3(h, x, result.get(result.size()-3),
        // result.get(result.size()-2),result.get(result.size()-1))));

        // result.add(new Point(x, rungeKutta(h, result.get(2).xi, result.get(2).yi)));
        // result.add(new Point(x,predictorCorrectorOrder3(h, x, result.get(result.size()-3),
        // result.get(result.size()-2),result.get(result.size()-1))));
        for (int i = 4; i < n; i++) {
            x += h;
            result.add(new Point(x, predictorCorrectorOrder4(h, x, result.get(i - 4), result.get(i - 3),
                    result.get(i - 2), result.get(i - 1))));
        }
        result.add(new Point(interval.getB(), predictorCorrectorOrder4(h, interval.getB(),
                result.get(result.size() - 4), result.get(result.size() - 3), result.get(result.size() - 2),
                result.get(result.size() - 1))));

        return result;
    }

    public List<Point> solveOrder5(final Interval interval, final int n) throws CalculatorException {
        if (n < 10) {
            throw new CalculatorException("the number n is too small");
        }
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        x += h;
        result.add(new Point(x, predictorCorrectorOrder1(h, x, result.get(result.size() - 1))));

        x += h;
        // result.add(new Point(x, rungeKutta(h, result.get(1).xi, result.get(1).yi)));
        result.add(new Point(x, predictorCorrectorOrder2(h, x, result.get(result.size() - 2),
                result.get(result.size() - 1))));

        x += h;
        // result.add(new Point(x, rungeKutta(h, result.get(2).xi, result.get(2).yi)));
        result.add(new Point(x, predictorCorrectorOrder2(h, x, result.get(result.size() - 2),
                result.get(result.size() - 1))));

        x += h;
        result.add(new Point(x, predictorCorrectorOrder4(h, x, result.get(result.size() - 4),
                result.get(result.size() - 3), result.get(result.size() - 2), result.get(result.size() - 1))));

        for (int i = 5; i < n; i++) {
            x += h;
            result.add(new Point(x, predictorCorrectorOrder5(h, x, result.get(i - 5), result.get(i - 4),
                    result.get(i - 3), result.get(i - 2), result.get(i - 1))));
        }
        result.add(new Point(interval.getB(), predictorCorrectorOrder5(h, interval.getB(),
                result.get(result.size() - 5), result.get(result.size() - 4), result.get(result.size() - 3),
                result.get(result.size() - 2), result.get(result.size() - 1))));

        return result;
    }

    /**
     * 
     * predictorCorrectorOrder1
     * 
     * @param h
     * @param xn
     * @param po
     * @return
     * @throws CalculatorException
     */
    private double predictorCorrectorOrder1(final double h, final double xn, final Point po) throws CalculatorException {
        final double zn = rungeKuttaAdaptative(h, po.getXi(), po.getXi());
        final List<Double> values_n = new ArrayList<Double>();
        values_n.add(xn);
        values_n.add(zn);
        double yn = po.getYi() + (h * this.f_xy.getValue(values_n, this.vars));
        final List<Double> values_n1 = new ArrayList<Double>();
        values_n1.add(xn);
        values_n1.add(yn);
        yn = po.getYi() + (h * this.f_xy.getValue(values_n1, this.vars));
        final List<Double> values_n2 = new ArrayList<Double>();
        values_n2.add(xn);
        values_n2.add(yn);
        yn = po.getYi() + (h * this.f_xy.getValue(values_n2, this.vars));
        final List<Double> values_n3 = new ArrayList<Double>();
        values_n3.add(xn);
        values_n3.add(yn);
        yn = po.getYi() + (h * this.f_xy.getValue(values_n3, this.vars));
        return yn;

    }

    /**
     * 
     * predictorCorrectorOrder2
     * 
     * @param h
     * @param xn
     * @param po
     * @param p1
     * @return
     * @throws CalculatorException
     */
    private double predictorCorrectorOrder2(final double h, final double xn, final Point po, final Point p1)
            throws CalculatorException {
        final double division = (h / 2);
        final double zn = p1.getYi()
                + (division * ((3 * this.f_xy.getValue(p1.getList(), this.vars)) - this.f_xy.getValue(po.getList(),
                        this.vars)));
        final List<Double> values_n = new ArrayList<Double>();
        values_n.add(xn);
        values_n.add(zn);
        final double yn = p1.getYi()
                + (division * (this.f_xy.getValue(values_n, this.vars) + this.f_xy.getValue(p1.getList(), this.vars)));

        return yn;
    }

    /**
     * 
     * predictorCorrectorOrder3
     * 
     * @param h
     * @param xn
     * @param po
     * @param p1
     * @param p2
     * @return
     * @throws CalculatorException
     */
    private double predictorCorrectorOrder3(final double h, final double xn, final Point po, final Point p1,
            final Point p2) throws CalculatorException {
        final double division = (h / 12);
        final double zn = p2.getYi()
                + (division * (((23 * this.f_xy.getValue(p2.getList(), this.vars)) - (16 * this.f_xy.getValue(
                        p1.getList(), this.vars))) + (5 * this.f_xy.getValue(po.getList(), this.vars))));
        final List<Double> values_n = new ArrayList<Double>();
        values_n.add(xn);
        values_n.add(zn);
        final double yn = p2.getYi()
                + (division * (((5 * this.f_xy.getValue(values_n, this.vars)) + (8 * this.f_xy.getValue(p1.getList(),
                        this.vars))) - this.f_xy.getValue(po.getList(), this.vars)));
        return yn;
    }

    /**
     * 
     * predictorCorrectorOrder4
     * 
     * @param h
     * @param xn
     * @param po
     * @param p1
     * @param p2
     * @param p3
     * @return
     * @throws CalculatorException
     */
    private double predictorCorrectorOrder4(final double h, final double xn, final Point po, final Point p1,
            final Point p2, final Point p3) throws CalculatorException {

        final double zn = p3.getYi()
                + ((h / 24) * ((((55 * this.f_xy.getValue(p3.getList(), this.vars)) - (59 * this.f_xy.getValue(
                        p2.getList(), this.vars))) + (37 * this.f_xy.getValue(p1.getList(), this.vars))) - (9 * this.f_xy
                        .getValue(po.getList(), this.vars))));
        final List<Double> values_n = new ArrayList<Double>();
        values_n.add(xn);
        values_n.add(zn);
        final double yn = p3.getYi()
                + ((h / 24) * ((((9 * this.f_xy.getValue(values_n, this.vars)) + (19 * this.f_xy.getValue(p3.getList(),
                        this.vars))) - (5 * this.f_xy.getValue(p2.getList(), this.vars))) + this.f_xy.getValue(
                        p1.getList(), this.vars)));

        return yn;
    }

    /**
     * 
     * predictorCorrectorOrder5
     * 
     * @param h
     * @param xn
     * @param p0
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     * @throws CalculatorException
     */
    private double predictorCorrectorOrder5(final double h, final double xn, final Point p0, final Point p1,
            final Point p2, final Point p3, final Point p4) throws CalculatorException {
        final double zn = p4.getYi()
                + ((h / 720) * ((((1901 * this.f_xy.getValue(p4.getList(), this.vars))
                        - (2274 * this.f_xy.getValue(p3.getList(), this.vars))) + (2616 * this.f_xy.getValue(
                        p2.getList(), this.vars)))
                        - (1274 * this.f_xy.getValue(p1.getList(), this.vars)) - (251 * this.f_xy.getValue(
                        p0.getList(), this.vars))));
        final List<Double> values_n = new ArrayList<Double>();
        values_n.add(xn);
        values_n.add(zn);
        final double yn = p4.getYi()
                + ((h / 720) * (((((251 * this.f_xy.getValue(values_n, this.vars)) +
                (646 * this.f_xy.getValue(p4.getList(), this.vars))) - (264 * this.f_xy.getValue(p3.getList(),
                        this.vars))) +
                (106 * this.f_xy.getValue(p2.getList(), this.vars))) - (19 * this.f_xy
                        .getValue(p1.getList(), this.vars))));

        return yn;
    }

    /**
     * 
     * solveRungeKutta
     * 
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveRungeKutta(final Interval interval, final int n) throws CalculatorException {
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        for (int i = 1; i < n; i++) {
            x += h;
            final Point p = new Point(x, rungeKutta(h, x, result.get(i - 1).getYi()));
            result.add(p);
        }
        final Point p = new Point(interval.getB(), rungeKutta(h, x, result.get(result.size() - 1).getYi()));
        result.add(p);
        return result;
    }

    /**
     * solveRungeKutta Order 6
     * 
     * 
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveRungeKuttaO6(final Interval interval, final int n) throws CalculatorException {
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        for (int i = 1; i < n; i++) {
            x += h;
            final Point p = new Point(x, rungeKuttaOrder6(h, x, result.get(i - 1).getYi()));
            result.add(p);
        }
        final Point p = new Point(interval.getB(), rungeKuttaOrder6(h, x, result.get(result.size() - 1).getYi()));
        result.add(p);
        return result;
    }

    /**
     * 
     * solveRungeKuttaAdaptative
     * 
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<Point> solveRungeKuttaAdaptative(final Interval interval, final int n) throws CalculatorException {
        final List<Point> result = new ArrayList<Point>();
        final double h = (interval.getB() - interval.getA()) / n;
        double x = this.po.xi;
        result.add(this.po);
        for (int i = 1; i < n; i++) {
            x += h;
            final Point p = new Point(x, rungeKuttaAdaptative(h, x, result.get(i - 1).getYi()));
            result.add(p);
        }
        final Point p = new Point(interval.getB(), rungeKuttaAdaptative(h, x, result.get(result.size() - 1).getYi()));
        result.add(p);
        return result;
    }

    /**
     * 
     * rungeKutta
     * 
     * @param h
     * @param xo
     * @param yo
     * @return
     * @throws CalculatorException
     */
    private double rungeKutta(final double h, final double xo, final double yo) throws CalculatorException {
        double result = 0;
        final List<Double> values1 = new ArrayList<Double>();
        values1.add(new Double(xo));
        values1.add(new Double(yo));
        final double K1 = this.f_xy.getValue(values1, this.vars);
        final List<Double> values2 = new ArrayList<Double>();
        final double half_h = h / 2;
        values2.add(new Double(xo + half_h));
        values2.add(new Double(yo + (half_h * K1)));
        final double K2 = this.f_xy.getValue(values2, this.vars);
        final List<Double> values3 = new ArrayList<Double>();
        values3.add(new Double(xo + half_h));
        values3.add(new Double(yo + (half_h * K2)));
        final double K3 = this.f_xy.getValue(values3, this.vars);
        final List<Double> values4 = new ArrayList<Double>();
        values4.add(new Double(xo + half_h));
        values4.add(new Double(yo + ((h / half_h) * K3)));
        final double K4 = this.f_xy.getValue(values4, this.vars);
        result = yo + ((h / 6) * (K1 + (2 * K2) + (2 * K3) + K4));
        return result;
    }

    /**
     * rungeKutta Order 6
     * 
     * 
     * @param h
     * @param xo
     * @param yo
     * @return
     * @throws CalculatorException
     */
    private double rungeKuttaOrder6(final double h, final double xo, final double yo) throws CalculatorException {
        double result = 0;

        final List<Double> values1 = new ArrayList<Double>();
        values1.add(new Double(xo));
        values1.add(new Double(yo));
        final double K1 = h * this.f_xy.getValue(values1, this.vars);

        final List<Double> values2 = new ArrayList<Double>();
        values2.add(new Double(xo + h));
        values2.add(new Double(yo + K1));
        final double K2 = h * this.f_xy.getValue(values2, this.vars);

        final List<Double> values3 = new ArrayList<Double>();
        values3.add(new Double(xo + (h / 2)));
        values3.add(new Double(yo + (((3 * K1) + K2) / (8))));
        final double K3 = h * this.f_xy.getValue(values3, this.vars);

        final List<Double> values4 = new ArrayList<Double>();
        values4.add(new Double(xo + ((2 * h) / (3))));
        values4.add(new Double(yo + ((8 * K1) + (2 * K2) + (8 * K3))) / (27));
        final double K4 = h * this.f_xy.getValue(values4, this.vars);

        final List<Double> values5 = new ArrayList<Double>();
        values5.add(new Double(xo + (((7 - 4.58257569495584) * h) / (14))));
        values5.add(new Double(
                yo
                        + ((((3 * ((3 * 4.58257569495584) - 7) * K1) - (8 * (7 - 4.58257569495584) * K2)) + (48 * (7 - 4.58257569495584) * K3)) - (3 * (21 - 4.58257569495584) * K4))) / (392));
        final double K5 = h * this.f_xy.getValue(values5, this.vars);

        final List<Double> values6 = new ArrayList<Double>();
        values6.add(new Double(xo + (((7 + 4.58257569495584) * h) / (14))));
        values6.add(new Double(
                yo
                        + (((-5 * (231 + (51 * 4.58257569495584)) * K1) - (40 * (7 + 4.58257569495584) * K2) - (320 * 4.58257569495584 * K3))
                                + (3 * (21 + (121 * 4.58257569495584)) * K4) + (392 * (6 + 4.58257569495584) * K5))) / (1960));
        final double K6 = h * this.f_xy.getValue(values6, this.vars);

        final List<Double> values7 = new ArrayList<Double>();
        values7.add(new Double(xo + h));
        values7.add(new Double(
                yo
                        + ((((15 * (22 + (7 * 4.58257569495584)) * K1) + (120 * K2) + (40 * ((7 * 4.58257569495584) - 5) * K3))
                                - (63 * ((3 * 4.58257569495584) - 2) * K4) - (14 * (49 + (9 * 4.58257569495584)) * K5)) + (70 * (7 - 4.58257569495584) * K6))) / (180));
        final double K7 = h * this.f_xy.getValue(values7, this.vars);

        result = yo + (((9 * K1) + (64 * K3) + (49 * K5) + (49 * K6) + (9 * K7)) / (180));

        return result;
    }

    /**
     * 
     * rungeKuttaAdaptative
     * 
     * @param h
     * @param xo
     * @param yo
     * @return
     * @throws CalculatorException
     */
    private double rungeKuttaAdaptative(final double h, final double xo, final double yo) throws CalculatorException {
        double result = 0;
        final List<Double> values1 = new ArrayList<Double>();
        values1.add(new Double(xo));
        values1.add(new Double(yo));
        final double K1 = this.f_xy.getValue(values1, this.vars);
        final List<Double> values2 = new ArrayList<Double>();
        final double half_h = h / 2;
        values2.add(new Double(xo + half_h));
        values2.add(new Double(yo + (half_h * K1)));
        final double K2 = this.f_xy.getValue(values2, this.vars);
        final List<Double> values3 = new ArrayList<Double>();
        values3.add(new Double(xo + (h * 0.75)));
        values3.add(new Double(yo + (h * 0.75 * K2)));
        final double K3 = this.f_xy.getValue(values3, this.vars);

        final double y1 = yo + (h * (((2.0 / 9.0) * K1) + ((1.0 / 3.0) * K2) + ((4.0 / 9.0) * K3)));

        final List<Double> values4 = new ArrayList<Double>();
        values4.add(new Double(xo + h));
        values4.add(new Double(y1));
        final double K4 = this.f_xy.getValue(values4, this.vars);
        result = yo + (h * (((7.0 / 24.0) * K1) + ((1.0 / 4.0) * K2) + ((1.0 / 3.0) * K3) + ((1.0 / 8.0) * K4)));

        return result;
    }

}
