package model;

import static java.lang.Math.*;
import static util.Constants.A;
import static util.Constants.R;

public class P extends Function {

    public P(double t) {
        super(t, 2 * Math.PI * R);
    }

    @Override
    public double getValue(double x) {
        int n = 10;
        double result = 0;
        for (int k = 1; k < n; k++) {
            double sum = 0;
            sum += (2 * sin(k * PI / 4.0) - sin(k * PI / 2.0)) * sin(k * x / (2 * R));
            sum += (2 * cos(k * PI / 4.0) - cos(k * PI / 2.0) - 1) * cos(k * x / (2 * R));
            result += sum * (1 / (k * k)) * exp(-t * (k * A / (2.0 * R)) * (k * A / (2.0 * R)));
        }
        result *= (4 / (PI * PI));

        return result + 0.5;
    }
}
