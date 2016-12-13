package model;

import static java.lang.Math.*;
import static util.Constants.*;

public class P extends Function {

    public P(double t) {
        super(t, 2.0 * Math.PI * R);
    }


    static private double getA(int k) {
        return 2.0 * (2.0 * cos(k * PI / 2.0) - 1.0 - cos(k * PI)) / (k * k * PI * PI);
    }

    static private double getB(int k) {
        return 2.0 * (2.0 * sin(k * PI / 2.0) - sin(k * PI)) / (k * k * PI * PI);
    }


    @Override
    public double getValue(double x) {
        double epsilon = 0.0001;
        int n = computeDepth(epsilon, t);
        double result = 0.0;
        for (int k = 1; k < n; k++) {
            double sum = 0.0;
            sum += getB(k) * sin(k * x / R);
            sum += getA(k) * cos(k * x / R);
            result += sum * exp(-t * (k * A / R) * (k * A / R));
        }
        double a0 = 0.5;
        result += a0 / 2.0;
        return result;
    }

    static private double func(double precision, double n, double t) {
        return ((6.0 * C * R * R) / (PI * PI * n * n * K * t))
                * exp(-(n * n * K * t) / (C * R * R)) - precision;
    }

    // численно ищем
    static private double estDepthRoot(double a, double b, double eps, double t) {
        double fasr = func(eps, a, t);
        do {
            // Вычисляем значение функции в середине промежутка.
            double m = (a + b) / 2;
            double fm = func(eps, m, t);
            // Вычисляем новый отрезок

            if ((fasr * fm) > 0) {
                a = m;
            } else {
                b = m;
            }
        } while (b - a > 1e-4);
        // Нужная точность достигнута.
        return (b + a) / 2;
    }

    static public int computeDepth(double eps, double t) {
        return (int) (estDepthRoot(0, 1000, eps, t) + 1);
    }

    static public int estimatingDepth(double precision, double epsilon, double x, double t) {
        int depthTheor = computeDepth(epsilon, t);

        double fullSum = 0.0;
        for (int k = 0; k <= depthTheor; k++) {
            fullSum += oneItemForSum(k, t, x);
        }
        int depthPract = 0;
        double partSum = 0.0;
        while (Math.abs(fullSum - partSum) > precision * epsilon) {
            depthPract++;
            partSum += oneItemForSum(depthPract, t, x);
        }
        return depthPract;
    }

    static private double oneItemForSum(int k, double t, double x) {
        double result = 0.0;
        double sum = 0.0;
        sum += getB(k) * sin(k * x / R);
        sum += getA(k) * cos(k * x / R);
        result += sum * exp(-t * (k * A / R) * (k * A / R));
        double a0 = 0.5;
        result += a0 / 2.0;
        return result;
    }

    public static void main(String[] args) {
        double t = 0.0;
        double x = 0.0;
        double eps = 0.0001;
        System.out.println(computeDepth(eps, t));
        System.out.println(estimatingDepth(1, eps, x, t));
    }
}
