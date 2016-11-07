package model;

import javafx.scene.chart.XYChart;

import static util.Constants.AMOUNT_SAMPLING_INTERVAL;

public abstract class Function {

    protected double[] y;

    public Function(double t) {
        y = new double[AMOUNT_SAMPLING_INTERVAL];
        double x = 0;
        double h = x / AMOUNT_SAMPLING_INTERVAL;
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
        XYChart.Series series = new XYChart.Series();
        double x = 0;
        double h = x / AMOUNT_SAMPLING_INTERVAL;
        for (int i = 0; i < y.length; i++) {
            series.getData().add(new XYChart.Data(x, y[i]));
            x += h;
        }
        return series;
    }
}
