package com.mathlibrary.differential;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.FunctionXs;
import com.mathlibrary.util.Interval;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 *         Differential System Ecuation
 * 
 */
public class DiffEquationSys {


    /**
     * ecuations: f1(x,y,....), f2(x,y,....)
     */
    private final List<FunctionXs> ecuations;
    /**
     * vars x,y...
     */
    private final List<String> vars;

    /**
     * DiffSysEcuation
     * 
     * @param ecuations
     * @param vars
     */
    public DiffEquationSys(final List<FunctionXs> ecuations, final List<String> vars) {
        this.ecuations = ecuations;
        this.vars = vars;

    }

    /**
     * 
     * solveRungeKuta Order 4
     * 
     * @param xos
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<List<Double>> solveRungeKutaO4(final List<Double> xos, final Interval interval, final int n)
            throws CalculatorException {
        final List<List<Double>> result = new ArrayList<List<Double>>();
        for (int i = 0; i < this.vars.size(); i++) {
            final List<Double> list = new ArrayList<Double>();
            result.add(list);
        }

        final double h = (interval.getB() - interval.getA()) / n;
        for (int i = 0; i < this.vars.size(); i++) {
            result.get(i).add(xos.get(i));
        }

        for (int i = 1; i < n; i++) {
            final List<Double> zos = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                zos.add(result.get(j).get(result.get(j).size() - 1));
            }

            final List<Double> xis = rungeKuttaOrder4(h, zos);

            for (int j = 0; j < xis.size(); j++) {
                result.get(j).add(xis.get(j));
            }

        }

        final List<Double> zos = new ArrayList<Double>();
        for (int j = 0; j < this.vars.size(); j++) {
            zos.add(result.get(j).get(result.get(j).size() - 1));
        }
        result.get(0).add(interval.getB());
        final List<Double> xis = rungeKuttaOrder4(h, zos);
        for (int j = 1; j < xis.size(); j++) {
            result.get(j).add(xis.get(j));
        }

        return result;

    }

    /**
     * 
     * solveRungeKuta Order 6
     * 
     * @param xos
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<List<Double>> solveRungeKutaO6(final List<Double> xos, final Interval interval, final int n)
            throws CalculatorException {
        final List<List<Double>> result = new ArrayList<List<Double>>();
        for (int i = 0; i < this.vars.size(); i++) {
            final List<Double> list = new ArrayList<Double>();
            result.add(list);
        }

        final double h = (interval.getB() - interval.getA()) / n;
        for (int i = 0; i < this.vars.size(); i++) {
            result.get(i).add(xos.get(i));
        }

        for (int i = 1; i < n; i++) {
            final List<Double> zos = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                zos.add(result.get(j).get(result.get(j).size() - 1));
            }

            final List<Double> xis = rungeKuttaOrder6(h, zos);

            for (int j = 0; j < xis.size(); j++) {
                result.get(j).add(xis.get(j));
            }

        }

        final List<Double> zos = new ArrayList<Double>();
        for (int j = 0; j < this.vars.size(); j++) {
            zos.add(result.get(j).get(result.get(j).size() - 1));
        }
        result.get(0).add(interval.getB());
        final List<Double> xis = rungeKuttaOrder6(h, zos);
        for (int j = 1; j < xis.size(); j++) {
            result.get(j).add(xis.get(j));
        }

        return result;

    }

    /**
     * 
     * rungeKuttaOrder4
     * 
     * @param h
     * @param xos
     * @return
     * @throws CalculatorException
     */
    private List<Double> rungeKuttaOrder4(final double h, final List<Double> xos) throws CalculatorException {

        final List<Double> values1 = new ArrayList<Double>();
        values1.add(new Double(xos.get(0)));
        for (int i = 1; i < xos.size(); i++) {
            values1.add(xos.get(i));
        }
        final List<Double> k1s = new ArrayList<Double>();

        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K1 = this.ecuations.get(k).getValue(values1, this.vars);
            k1s.add(K1);
        }

        final List<Double> values2 = new ArrayList<Double>();
        values2.add(new Double(xos.get(0) + (0.5 * h)));
        for (int i = 1; i < xos.size(); i++) {
            values2.add(xos.get(i) + (k1s.get(i - 1) * h * 0.5));
        }
        final List<Double> k2s = new ArrayList<Double>();

        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K2 = this.ecuations.get(k).getValue(values2, this.vars);
            k2s.add(K2);
        }

        final List<Double> values3 = new ArrayList<Double>();
        values3.add(new Double(xos.get(0) + (0.5 * h)));
        for (int i = 1; i < xos.size(); i++) {
            values3.add(xos.get(i) + (k2s.get(i - 1) * h * 0.5));
        }
        final List<Double> k3s = new ArrayList<Double>();

        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K3 = this.ecuations.get(k).getValue(values3, this.vars);
            k3s.add(K3);
        }

        final List<Double> values4 = new ArrayList<Double>();
        values4.add(new Double(xos.get(0) + h));
        for (int i = 1; i < xos.size(); i++) {
            values4.add(xos.get(i) + (k3s.get(i - 1) * h));
        }
        final List<Double> k4s = new ArrayList<Double>();

        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K4 = this.ecuations.get(k).getValue(values4, this.vars);
            k4s.add(K4);
        }
        final List<Double> result = new ArrayList<Double>();
        result.add(new Double(xos.get(0) + h));
        for (int i = 1; i < xos.size(); i++) {
            result.add(new Double(xos.get(i)
                    + ((h / 6.0) * (k1s.get(i - 1) + (2 * k2s.get(i - 1)) + (2 * k3s.get(i - 1)) + k4s.get(i - 1)))));
        }

        return result;
    }

    /**
     * 
     * rungeKuttaOrder6
     * 
     * @param h
     * @param xos
     * @return
     * @throws CalculatorException
     */
    private List<Double> rungeKuttaOrder6(final double h, final List<Double> xos) throws CalculatorException {
        final List<Double> result = new ArrayList<Double>();

        final List<Double> values1 = new ArrayList<Double>();
        values1.add(new Double(xos.get(0)));
        for (int i = 1; i < xos.size(); i++) {
            values1.add(xos.get(i));
        }

        final List<Double> k1s = new ArrayList<Double>();

        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K1 = h * this.ecuations.get(k).getValue(values1, this.vars);
            k1s.add(K1);
        }

        final List<Double> values2 = new ArrayList<Double>();
        values2.add(new Double(xos.get(0) + h));
        for (int i = 1; i < xos.size(); i++) {
            values2.add(new Double(xos.get(i) + k1s.get(i - 1)));
        }

        final List<Double> k2s = new ArrayList<Double>();
        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K2 = h * this.ecuations.get(k).getValue(values2, this.vars);
            k2s.add(K2);
        }

        final List<Double> values3 = new ArrayList<Double>();
        values3.add(new Double(xos.get(0) + (h / 2)));
        for (int i = 1; i < xos.size(); i++) {
            values3.add(new Double(xos.get(i) + (((3 * k1s.get(i - 1)) + k2s.get(i - 1)) / (8))));
        }

        final List<Double> k3s = new ArrayList<Double>();
        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K3 = h * this.ecuations.get(k).getValue(values3, this.vars);
            k3s.add(K3);
        }

        final List<Double> values4 = new ArrayList<Double>();
        values4.add(new Double(xos.get(0) + ((2 * h) / (3))));
        for (int i = 1; i < xos.size(); i++) {
            values4.add(new Double(xos.get(i) + ((8 * k1s.get(i - 1)) + (2 * k2s.get(i - 1)) + (8 * k3s.get(i - 1)))) / (27));
        }

        final List<Double> k4s = new ArrayList<Double>();
        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K4 = h * this.ecuations.get(k).getValue(values4, this.vars);
            k4s.add(K4);
        }

        final List<Double> values5 = new ArrayList<Double>();
        values5.add(new Double(xos.get(0) + (((7 - 4.58257569495584) * h) / (14))));
        for (int i = 1; i < xos.size(); i++) {
            values5.add(new Double(
                    xos.get(i)
                            + ((((3 * ((3 * 4.58257569495584) - 7) * k1s.get(i - 1)) - (8 * (7 - 4.58257569495584) * k2s
                                    .get(i - 1))) + (48 * (7 - 4.58257569495584) * k3s.get(i - 1))) - (3 * (21 - 4.58257569495584) * k4s
                                    .get(i - 1)))) / (392));
        }

        final List<Double> k5s = new ArrayList<Double>();
        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K5 = h * this.ecuations.get(k).getValue(values5, this.vars);
            k5s.add(K5);
        }

        final List<Double> values6 = new ArrayList<Double>();
        values6.add(new Double(xos.get(0) + (((7 + 4.58257569495584) * h) / (14))));
        for (int i = 1; i < xos.size(); i++) {
            values6.add(new Double(
                    xos.get(i)
                            + (((-5 * (231 + (51 * 4.58257569495584)) * k1s.get(i - 1))
                                    - (40 * (7 + 4.58257569495584) * k2s.get(i - 1)) - (320 * 4.58257569495584 * k3s
                                    .get(i - 1))) + (3 * (21 + (121 * 4.58257569495584)) * k4s.get(i - 1)) + (392 * (6 + 4.58257569495584) * k5s
                                    .get(i - 1)))) / (1960));
        }

        final List<Double> k6s = new ArrayList<Double>();
        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K6 = h * this.ecuations.get(k).getValue(values6, this.vars);
            k6s.add(K6);
        }

        final List<Double> values7 = new ArrayList<Double>();
        values7.add(new Double(xos.get(0) + h));
        for (int i = 1; i < xos.size(); i++) {
            values7.add(new Double(
                    xos.get(i)
                            + ((((15 * (22 + (7 * 4.58257569495584)) * k1s.get(i - 1)) + (120 * k2s.get(i - 1)) + (40 * ((7 * 4.58257569495584) - 5) * k3s
                                    .get(i - 1))) - (63 * ((3 * 4.58257569495584) - 2) * k4s.get(i - 1)) - (14 * (49 + (9 * 4.58257569495584)) * k5s
                                    .get(i - 1))) + (70 * (7 - 4.58257569495584) * k6s.get(i - 1)))) / (180));
        }

        final List<Double> k7s = new ArrayList<Double>();
        for (int k = 0; k < this.ecuations.size(); k++) {
            final double K7 = h * this.ecuations.get(k).getValue(values7, this.vars);
            k7s.add(K7);
        }

        result.add(xos.get(0) + h);
        for (int k = 1; k < xos.size(); k++) {
            final double x1 = xos.get(k)
                    + (((9 * k1s.get(k - 1)) + (64 * k3s.get(k - 1)) + (49 * k5s.get(k - 1)) + (49 * k6s.get(k - 1)) + (9 * k7s
                            .get(k - 1))) / (180));
            result.add(new Double(x1));
        }

        return result;
    }

    /**
     * 
     * solvePredictorCorrector Order 4
     * 
     * @param xos
     * @param interval
     * @param n
     * @return
     * @throws CalculatorException
     */
    public List<List<Double>> solvePredictorCorrectorO4(final List<Double> xos, final Interval interval, final int n)
            throws CalculatorException {
        final List<List<Double>> result = new ArrayList<List<Double>>();
        for (int i = 0; i < this.vars.size(); i++) {
            final List<Double> list = new ArrayList<Double>();
            result.add(list);
        }

        final double h = (interval.getB() - interval.getA()) / n;
        for (int i = 0; i < this.vars.size(); i++) {
            result.get(i).add(xos.get(i));
        }

        for (int i = 1; i < 4; i++) {
            final List<Double> zos = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                zos.add(result.get(j).get(result.get(j).size() - 1));
            }

            final List<Double> zis = rungeKuttaOrder4(h, zos);

            for (int j = 0; j < zis.size(); j++) {
                result.get(j).add(zis.get(j));
            }
        }

        for (int i = 4; i < n; i++) {

            final List<Double> zos = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                zos.add(result.get(j).get(result.get(j).size() - 4));
            }

            final List<Double> z1s = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                z1s.add(result.get(j).get(result.get(j).size() - 3));
            }

            final List<Double> z2s = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                z2s.add(result.get(j).get(result.get(j).size() - 2));
            }

            final List<Double> z3s = new ArrayList<Double>();
            for (int j = 0; j < this.vars.size(); j++) {
                z3s.add(result.get(j).get(result.get(j).size() - 1));
            }

            final List<Double> zis = predictorCorrectorO4(h, zos, z1s, z2s, z3s);

            for (int j = 0; j < zis.size(); j++) {
                result.get(j).add(zis.get(j));
            }
        }

        final List<Double> zos = new ArrayList<Double>();
        for (int j = 0; j < this.vars.size(); j++) {
            zos.add(result.get(j).get(result.get(j).size() - 4));
        }

        final List<Double> z1s = new ArrayList<Double>();
        for (int j = 0; j < this.vars.size(); j++) {
            z1s.add(result.get(j).get(result.get(j).size() - 3));
        }

        final List<Double> z2s = new ArrayList<Double>();
        for (int j = 0; j < this.vars.size(); j++) {
            z2s.add(result.get(j).get(result.get(j).size() - 2));
        }

        final List<Double> z3s = new ArrayList<Double>();
        for (int j = 0; j < this.vars.size(); j++) {
            z3s.add(result.get(j).get(result.get(j).size() - 1));
        }

        final List<Double> zis = predictorCorrectorO4(h, zos, z1s, z2s, z3s);

        result.get(0).add(interval.getB());
        for (int j = 1; j < zis.size(); j++) {
            result.get(j).add(zis.get(j));
        }

        return result;
    }

    List<Double> predictorCorrectorO4(final double h, final List<Double> zos, final List<Double> z1s,
            final List<Double> z2s, final List<Double> z3s) throws CalculatorException {
        final List<Double> results = new ArrayList<Double>();

        final List<Double> zns = new ArrayList<Double>();
        final double xn = z3s.get(0) + h;
        zns.add(xn);
        results.add(xn);

        for (int i = 1; i < zos.size(); i++) {
            final double zn = z3s.get(i)
                    + ((h / 24) * ((((55 * this.ecuations.get(i - 1).getValue(z3s, this.vars)) - (59 * this.ecuations
                            .get(i - 1).getValue(z2s, this.vars))) + (37 * this.ecuations.get(i - 1).getValue(z1s,
                            this.vars))) - (9 * this.ecuations.get(i - 1).getValue(zos, this.vars))));
            zns.add(zn);
        }

        for (int i = 1; i < zos.size(); i++) {
            final double result = z3s.get(i)
                    + ((h / 24) * ((((9 * this.ecuations.get(i - 1).getValue(zns, this.vars)) + (19 * this.ecuations
                            .get(i - 1).getValue(z3s, this.vars))) - (5 * this.ecuations.get(i - 1).getValue(z2s,
                            this.vars))) + this.ecuations.get(i - 1).getValue(z1s, this.vars)));
            results.add(result);

        }

        return results;
    }
}
