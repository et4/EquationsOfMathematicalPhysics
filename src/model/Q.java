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
        int n = 200;//2 6 10
        double result = 0.5;
        for (int m = 1; m < n; m++) {
            result += (sin(3 * PI * m / 4) - sin(PI * m / 4)) *
                    exp(-t * ((K / C) * pow(PI * m / L, 2))) *
                    cos(PI * m * x / L) * (2 / (PI * m));
//            System.out.println(result);
        }
        return result * exp(-t * 2 * AL / (D * C));
    }
}
