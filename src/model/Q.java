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
        int n = 10;
        double result = 0;
        for (int m = 0; m < n; m++) {
            result += (2 / L) * (sin(3 * PI * m / 4) - sin(PI * m / 4)) *
                    cos(PI * m * x / L) * exp(-t * (pow(PI * m * A / L, 2) +
                    2 * pow(A, 2) * AL / D));
        }
        return result;
    }
}
