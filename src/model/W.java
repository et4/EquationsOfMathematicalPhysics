package model;

public class W extends Function {

    private Function P;
    private Function Q;

    public W(double t) {
        super(t);
        this.P = new P(t);
        this.Q = new Q(t);
    }

    @Override
    public double getValue(double x) {
        return Q.getValue(x) * P.getValue(x);
    }
}
