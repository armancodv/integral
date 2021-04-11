package com.mathlibrary.matrix;

import java.util.ArrayList;
import java.util.List;

import com.mathlibrary.exception.CalculatorException;
import com.mathlibrary.function.Complex;
import com.mathlibrary.util.UtilMatrix;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class CalcEigenvaule {
    public static final int maxiterations = 250;
    public static final double threshold = 10e20;

    public static double radix = 1e4;

    /**
     * 
     * calc
     * 
     * @param A
     * @return
     * @throws CalculatorException
     */
    public static List<Complex> calc(final Matrix A) throws CalculatorException {

        Matrix M = A.clone();
        if (!M.isSimetric()) {
            M = balance(M, radix);
        }
        M = getHessenberg(M);
        return QRAlgorithm(M);
    }

    /**
     * 
     * getHessenberg
     * 
     * @param A
     * @return
     * @throws CalculatorException
     */
    public static Matrix getHessenberg(final Matrix A) throws CalculatorException {

        if (!A.isSquare()) {
            throw new CalculatorException("A is not square");
        }
        final Matrix M = A.clone();

        final int n = A.getRows();
        int i;
        double x, y;

        for (int m = 1; m < n; m++) {
            x = 0.0;
            i = m;
            for (int j = m; j < n; j++) {
                if (Math.abs(M.getElement(j, m - 1)) > Math.abs(x)) {
                    x = M.getElement(j, m - 1);
                    i = j;
                }
            }

            if (i != m) {
                for (int j = m - 1; j < n; j++) {
                    final MatrixElement a = new MatrixElement(i, j, M.getElement(i, j));
                    final MatrixElement b = new MatrixElement(m, j, M.getElement(m, j));
                    M.swap(a, b);
                }

                for (int j = 0; j < n; j++) {
                    final MatrixElement a = new MatrixElement(j, i, M.getElement(j, i));
                    final MatrixElement b = new MatrixElement(j, m, M.getElement(j, m));
                    M.swap(a, b);
                }
            }

            if (Math.abs(x) > 0) {
                for (i = m + 1; i < n; i++) {
                    y = M.getElement(i, m - 1);

                    if (y != 0.0) {
                        y /= x;
                        M.setElement(i, m - 1, y);
                        for (int j = m; j < n; j++) {
                            final double value = M.getElement(i, j) - (y * M.getElement(m, j));
                            M.setElement(i, j, value);
                        }

                        for (int j = 0; j < n; j++) {
                            final double value = M.getElement(j, m) + (y * M.getElement(j, i));
                            M.setElement(j, m, value);
                        }

                    }

                }

            }

        }

        return M;
    }

    /**
     * 
     * QRAlgorithm
     * 
     * @param A
     * @return
     * @throws CalculatorException
     */
    public static List<Complex> QRAlgorithm(final Matrix A) throws CalculatorException {
        if (!A.isSquare()) {
            throw new CalculatorException("A is not square");
        }
        final Matrix M = A.clone();
        final int n = M.getRows();
        final List<Complex> eigenvalues = new ArrayList<Complex>();
        final double[] wr = new double[A.getRows()];
        final double[] wi = new double[A.getRows()];

        int nn, m, l, k, j, its, i, mmin;
        double z, y, x, w, v, u, t, s, anorm;
        double p = 0;
        double q = 0;
        double r = 0;
        anorm = 0.0;
        for (i = 0; i < n; i++) {
            for (j = IMAX(i - 1, 0); j < n; j++) {
                anorm += Math.abs(M.getElement(i, j));
            }
        }

        nn = n - 1;
        t = 0.0;
        while (nn >= 0) {
            its = 0;
            do {
                for (l = nn; l >= 1; l--) {
                    s = Math.abs(M.getElement(l - 1, l - 1)) + Math.abs(M.getElement(l, l));
                    if (s == 0.0) {
                        s = anorm;
                    }
                    final double value = Math.abs(M.getElement(l, l - 1)) + s;
                    if (value == s) {
                        M.setElement(l, l - 1, 0.0);
                        break;
                    }
                }

                x = M.getElement(nn, nn);
                if (l == nn) {

                    wr[nn] = x + t;
                    wi[nn--] = 0.0;
                } else {
                    y = M.getElement(nn - 1, nn - 1);
                    w = M.getElement(nn, nn - 1) * M.getElement(nn - 1, nn);
                    if (l == (nn - 1)) {
                        p = 0.5 * (y - x);
                        q = (p * p) + w;
                        z = Math.sqrt(Math.abs(q));
                        x += t;
                        if (q >= 0.0) {
                            z = p + SIGN(z, p);
                            wr[nn - 1] = wr[nn] = x + z;
                            if (Math.abs(z) > 0) {
                                wr[nn] = x - (w / z);
                            }
                            wi[nn - 1] = wi[nn] = 0.0;
                        } else {
                            wr[nn - 1] = wr[nn] = x + p;
                            wi[nn - 1] = -(wi[nn] = z);
                        }
                        nn -= 2;
                    } else {
                        if (its == 30) {
                            throw new CalculatorException("Too many iterations");
                        }
                        if ((its == 10) || (its == 20)) {
                            t += x;
                            for (i = 0; i <= nn; i++) {
                                final double value = M.getElement(i, i);
                                M.setElement(i, i, value - x);
                            }
                            s = Math.abs(M.getElement(nn, nn - 1)) + Math.abs(M.getElement(nn - 1, nn - 2));
                            y = x = 0.75 * s;
                            w = -0.4375 * s * s;
                        }
                        its++;
                        for (m = (nn - 2); m >= l; m--) {
                            z = M.getElement(m, m);
                            r = x - z;
                            s = y - z;
                            p = (((r * s) - w) / M.getElement(m + 1, m)) + M.getElement(m, m + 1);
                            q = M.getElement(m + 1, m + 1) - z - r - s;
                            r = M.getElement(m + 2, m + 1);
                            s = Math.abs(p) + Math.abs(q) + Math.abs(r);
                            p /= s;
                            q /= s;
                            r /= s;
                            if (m == l) {
                                break;
                            }
                            u = Math.abs(M.getElement(m, m - 1)) * (Math.abs(q) + Math.abs(r));
                            v = Math.abs(p)
                                    * (Math.abs(M.getElement(m - 1, m - 1)) + Math.abs(z) + Math.abs(M.getElement(
                                            m + 1, m + 1)));
                            final double u_v = u + v;
                            if (u_v == v) {
                                break;
                            }
                        }
                        for (i = m + 2; i <= nn; i++) {
                            M.setElement(i, i - 2, 0.0);
                            if (i != (m + 2)) {
                                M.setElement(i, i - 3, 0.0);
                            }
                        }
                        for (k = m; k <= (nn - 1); k++) {
                            if (k != m) {
                                p = M.getElement(k, k - 1);
                                q = M.getElement(k + 1, k - 1);
                                r = 0.0;
                                if (k != (nn - 1)) {
                                    r = M.getElement(k + 2, k - 1);
                                }
                                if ((x = Math.abs(p) + Math.abs(q) + Math.abs(r)) != 0.0) {
                                    p /= x;
                                    q /= x;
                                    r /= x;
                                }
                            }
                            if ((s = SIGN(Math.sqrt((p * p) + (q * q) + (r * r)), p)) != 0.0) {
                                if (k == m) {
                                    if (l != m) {
                                        M.setElement(k, k - 1, -1 * A.getElement(k, k - 1));
                                    }
                                } else {
                                    M.setElement(k, k - 1, -1 * s * x);
                                }
                                p += s;
                                x = p / s;
                                y = q / s;
                                z = r / s;
                                q /= p;
                                r /= p;
                                for (j = k; j <= nn; j++) {
                                    p = M.getElement(k, j) + (q * M.getElement(k + 1, j));
                                    if (k != (nn - 1)) {
                                        p += r * M.getElement(k + 2, j);
                                        final double value = M.getElement(k + 2, j);
                                        M.setElement(k + 2, j, value - (p * z));
                                    }
                                    double value = M.getElement(k + 1, j);
                                    M.setElement(k + 1, j, value - (p * y));
                                    value = M.getElement(k, j);
                                    M.setElement(k, j, value - (p * x));
                                }
                                mmin = nn < (k + 3) ? nn : k + 3;
                                for (i = l; i <= mmin; i++) {
                                    p = (x * M.getElement(i, k)) + (y * M.getElement(i, k + 1));
                                    if (k != (nn - 1)) {
                                        p += z * M.getElement(i, k + 2);
                                        final double value = M.getElement(i, k + 2);
                                        M.setElement(i, k + 2, value - (p * r));
                                    }
                                    double value = M.getElement(i, k + 1);
                                    M.setElement(i, k + 1, value - (p * q));
                                    value = M.getElement(i, k);
                                    M.setElement(i, k, value - p);

                                }
                            }
                        }
                    }
                }
            } while (l < (nn - 1));
        }

        for (int o = 0; o < wr.length; o++) {
            Complex eigenvalue;
            double real;
            double imaginary;
            if (Math.abs(wr[o]) < 1e-12) {
                real = 0.0;
            } else {
                real = wr[o];
            }
            if (Math.abs(wi[o]) < 1e-12) {
                imaginary = 0.0;
            } else {
                imaginary = wi[o];
            }
            eigenvalue = new Complex(real, imaginary);
            eigenvalues.add(eigenvalue);
        }

        return eigenvalues;
    }

    /**
     * 
     * IMAX
     * 
     * @param a
     * @param b
     * @return
     */
    private static int IMAX(final int a, final int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * 
     * SIGN
     * 
     * @param a
     * @param b
     * @return
     */
    private static double SIGN(final double a, final double b) {
        double result = 0;
        int value = 0;
        if (b < 0) {
            value = -1;
        } else {
            value = 1;
        }
        result = a * value;
        return result;
    }

    /**
     * 
     * balance
     * 
     * @param A
     * @param RADIX
     * @return
     * @throws CalculatorException
     */
    public static Matrix balance(final Matrix A, final double RADIX) throws CalculatorException {
        if (!A.isSquare()) {
            throw new CalculatorException("A is not square");
        }
        final Matrix M = A.clone();
        // double RADIX = 1e4;
        final int n = M.getRows();
        int last, j, i;
        double s, r, g, f, c, sqrdx;
        sqrdx = RADIX * RADIX;
        last = 0;
        while (last == 0) {
            last = 1;
            for (i = 0; i < n; i++) {
                r = c = 0.0;
                for (j = 0; j < n; j++) {
                    if (j != i) {
                        c += Math.abs(M.getElement(j, i));
                        r += Math.abs(M.getElement(i, j));
                    }
                }
                if ((c != 0.0) && (r != 0.0)) {
                    g = r / RADIX;
                    f = 1.0;
                    s = c + r;
                    while (c < g) {
                        f *= RADIX;
                        c *= sqrdx;
                    }
                    g = r * RADIX;
                    while (c > g) {
                        f /= RADIX;
                        c /= sqrdx;
                    }
                    if (((c + r) / f) < (0.95 * s)) {
                        last = 0;
                        g = 1.0 / f;
                        for (j = 0; j < n; j++) {
                            final double value = M.getElement(i, j);
                            M.setElement(i, j, value * g);
                        }
                        for (j = 0; j < n; j++) {
                            final double value = M.getElement(j, i);
                            M.setElement(j, i, value * f);
                        }
                    }
                }
            }
        }
        return M;
    }

    /**
     * 
     * powerIteration
     * 
     * @param A
     * @param u0
     * @return
     * @throws Exception
     */
    public static Complex powerIteration(final Matrix A, final Vector u0) throws Exception {

        if (u0.isRow()) {
            throw new Exception("The vector is not column");
        }
        if (u0.getLenght() != A.getColumns()) {
            throw new Exception("vector's lenght is not appropriate");
        }

        final MatrixElement elemnt = A.getMaxElementInRow(0);
        final List<Vector> vectorsList = new ArrayList<Vector>(); // num columns
        vectorsList.add(u0);
        Vector uk = u0;

        for (int i = 0; i < maxiterations; i++) {
            uk = A.multiply(uk);
            vectorsList.add(uk);
            if (uk.getMaxValue() > threshold) {
                break;
            }
        }

        final Matrix M = new Matrix(A.getColumns(), vectorsList.size() - 1);

        for (int j = 0; j < (vectorsList.size() - 1); j++) {
            final Vector u1 = vectorsList.get(j);
            final Vector u2 = vectorsList.get(j + 1);
            for (int k = 0; k < u1.getLenght(); k++) {
                final double xi = u1.getXi(k);
                final double xj = u2.getXi(k);
                double xij = 0;
                if (xj == 0) {
                    xij = 0;
                } else {
                    xij = xj / xi;
                }
                M.setElement(k, j, xij);
            }

        }

        double eigenvalue = 0;
        for (int i = 0; i < M.getRows(); i++) {
            final double value = M.getElement(elemnt.getColumn(), M.getColumns() - 1);
            if (Math.abs(value) > Math.abs(eigenvalue)) {
                eigenvalue = value;
            }
        }
        final Complex sol = new Complex(eigenvalue, 0.0);

        return sol;
    }

    /**
     * 
     * inverseIteration
     * 
     * @param A
     * @param eigenvalue
     * @param u0
     * @return
     * @throws Exception
     */
    public static Vector inverseIteration(final Matrix A, final double eigenvalue, final Vector u0) throws Exception {

        if (u0.isRow()) {
            throw new Exception("The vector is not column");
        }
        if (u0.getLenght() != A.getColumns()) {
            throw new Exception("vector's lenght is not appropriate");
        }

        if (!A.isSquare()) {
            throw new Exception("Matrix is not square");
        }

        Matrix M = A.clone();
        Matrix I = UtilMatrix.getI(M.getRows());
        I = Matrix.mul(eigenvalue, I);
        M = Matrix.sub(M, I);
        M = M.getInv();

        final List<Vector> vectorsList = new ArrayList<Vector>(); // num columns
        vectorsList.add(u0);
        Vector uk = u0;

        for (int i = 0; i < maxiterations; i++) {
            uk = M.multiply(uk);
            vectorsList.add(uk);
            if (uk.getMaxValue() > threshold) {
                break;
            }
        }

        Vector v = vectorsList.get(vectorsList.size() - 1);
        v = Vector.scalarDiv(v, v.maximunNorm());

        return v;
    }

}
