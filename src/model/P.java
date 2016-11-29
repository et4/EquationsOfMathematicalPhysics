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
        int n = 50;
        double result = 0.0;
        for (int k = 1; k < n; k++) {
            double sum = 0.0;
            sum += (2.0 * sin(k * PI / 4.0) - sin(k * PI / 2.0)) * sin(k * x / (2.0 * R));
            sum += (2.0 * cos(k * PI / 4.0) + cos(k * PI / 2.0) - 1.0) * cos(k * x / (2.0 * R));
            result += sum * (1.0 / (k * k)) * exp(-t * (k * A / (2.0 * R)) * (k * A / (2.0 * R)));
        }
        result *= (4.0 / (PI * PI));

        return result + 1.0 / 8.0 - (R - 1.0) / (2.0 * PI * R);
    }
}
