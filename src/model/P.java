package model;

import static java.lang.Math.*;
import static util.Constants.A;
import static util.Constants.R;

public class P extends Function {

    public P(double t) {
        super(t, 2.0 * Math.PI * R);
    }


    private double getA(int k) {
        return 2.0 * (2.0 * cos(k * PI / 2.0) - 1.0 - cos(k * PI)) / (k * k * PI * PI);
    }

    private double getB(int k) {
        return 2.0 * (2.0 * sin(k * PI / 2.0) - sin(k * PI)) / (k * k * PI * PI);
    }


    @Override
    public double getValue(double x) {
        int n = 100;
        double result = 0.0;
        for (int k = 1; k < n; k++) {
            double sum = 0.0;
            sum += getB(k) * sin(k * x /  R);
            sum += getA(k) * cos(k * x / R);
            result += sum * exp(-t * (k * A / R) * (k * A / R));
        }
        double a0 = 0.5;
        result += a0 / 2.0;
        return result;
    }
}
