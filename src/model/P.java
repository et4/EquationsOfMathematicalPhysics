package model;

import util.Constants;

public class P extends Function {

    public P(double t) {
        super(t, 2 * Math.PI * Constants.R);
    }

    @Override
    public double getValue(double x) {
        return 0;
    }
}
