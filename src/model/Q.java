package model;

import util.Constants;

import static util.Constants.*;
import static java.lang.Math.sin;
import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.PI;

public class Q extends Function {

    public Q(double t) {
        super(t, Constants.L);
    }

    @Override
    public double getValue(double x) {
        int n = 1000;
        double result = 0.5;
        for (int m = 1; m < n; m++) {
            result += (sin(3 * PI * m / 4) - sin(PI * m / 4)) *
                    exp(-t * (pow(PI * m * A / L, 2))) *
                    cos(PI * m * x / L);
        }
        return (2 / L) * result * exp(-t * 2 * pow(A, 2) * AL / D);
    }
}
