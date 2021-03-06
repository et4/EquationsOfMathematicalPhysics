package model;

import javafx.scene.chart.XYChart;

import static util.Constants.AMOUNT_SAMPLING_INTERVAL;
import static util.Constants.L;
import static util.Constants.R;

public abstract class Function {

    protected double[] y;
    protected double t;
    protected double border;

    public Function(double t, double border) {
        this.t = t;
        this.border = border;
        this.y = new double[AMOUNT_SAMPLING_INTERVAL];
        double x = 0;
        double h = border / AMOUNT_SAMPLING_INTERVAL;
        for (int i = 0; i < AMOUNT_SAMPLING_INTERVAL; i++) {
            y[i] = getValue(x);
            x += h;
        }
    }

    public abstract double getValue(double x);

    public double[] getValues() {
        return y;
    }

    public XYChart.Series<Number, Number> getSeries() {
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName("Методом Фурье");
        double x = 0;
        double h = border / AMOUNT_SAMPLING_INTERVAL;
        for (int i = 0; i < y.length; i++) {
            series.getData().add(new XYChart.Data(x, y[i]));
            x += h;
        }
        return series;
    }
}
