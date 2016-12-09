package model;

import static java.lang.Math.*;
import static util.Constants.A;
import static util.Constants.R;

public class P extends Function {

    public P(double t) {
        super(t, 2.0 * Math.PI * R);
    }


    private double getA(int k) {
        return 8.0 * (2.0 * cos(k * PI / 4.0) - 1.0 - cos(k * PI / 2.0)) / (k * k * PI * PI);
    }

    private double getB(int k) {
        return 8.0 * (2.0 * sin(k * PI / 4.0) - sin(k * PI / 2.0)) / (k * k * PI * PI);
    }


    @Override
    public double getValue(double x) {
        int n = 100;
        double result = 0.0;
        for (int k = 1; k < n; k++) {
            double sum = 0.0;
            sum += getB(k) * sin(k * x / (2.0 * R));
            sum += getA(k) * cos(k * x / (2.0 * R));
            result += sum * exp(-t * (k * A / (2.0 * R)) * (k * A / (2.0 * R)));
        }
        double a0 = 0.5;
        result += a0 / 2.0;
        return result;
    }
}
