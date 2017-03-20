package model;

import static java.lang.Math.*;
import static util.Constants.*;

public class P extends Function {

    public P(double t) {
        super(t, 2.0 * Math.PI * R);
    }


    static double getA(int k) {
        return 2.0 * (2.0 * cos(k * PI / 2.0) - 1.0 - cos(k * PI)) / (k * k * PI * PI);
    }

    static double getB(int k) {
        return 2.0 * (2.0 * sin(k * PI / 2.0) - sin(k * PI)) / (k * k * PI * PI);
    }


    @Override
    public double getValue(double x) {
        int n = computeDepth(t, 0.0001);
        double result = 0.0;
        for (int k = 1; k < n; k++) {
            double sum = 0.0;
            sum += getB(k) * sin(k * x / R);
            sum += getA(k) * cos(k * x / R);
            result += sum * exp(-t * (K / C) * (k / R) * (k / R));
        }
        double a0 = 0.5;
        result += a0 / 2.0;
        return result;
    }

    static private double func(double n, double t) {
        return ((6.0 * C * R * R) / (PI * PI * n * n * n * K * t))
                * exp(-(n * n * K * t) / (C * R * R));
    }

    // численно ищем
    static private double estDepthRoot(double a, double b, double eps, double t) {
        do {
            // Вычисляем значение функции в середине промежутка.
            double m = (a + b) / 2;
            double fm = func(m, t);
            // Вычисляем новый отрезок

            if (fm > eps) {
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
        int depthTheor = computeDepth(t, epsilon);

        double fullSum = 0.0;
        for (int k = 0; k <= depthTheor; k++) {
            fullSum += oneItemForSum(k, t, x);
        }
        int depthPract = depthTheor;
        double partSum = fullSum;
        while (Math.abs(fullSum - partSum) > epsilon) {
            partSum -= oneItemForSum(depthPract, t, x);
            depthPract--;
        }
        return depthPract;
    }

    static private double oneItemForSum(int k, double t, double x) {
        double result = 0.0;
        double sum = 0.0;
        sum += getB(k) * sin(k * x / R);
        sum += getA(k) * cos(k * x / R);
        result += sum * exp(-t * (K / C) * (k / R) * (k / R));
        double a0 = 0.5;
        result += a0 / 2.0;
        return result;
    }

    public static void main(String[] args) {
        double t = 0.5;
        double x = 0.0;
        double eps = 0.01;
        System.out.println(computeDepth(t,eps));
        System.out.println(estimatingDepth(1, eps, x, t));
    }
}
