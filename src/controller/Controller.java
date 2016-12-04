package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import model.P;
import model.Q;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    LineChart<Number, Number> chart;
    @FXML
    Slider slider;
    @FXML
    NumberAxis yAxis;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart.setCreateSymbols(false);
        chart.setLegendVisible(false);
//        chart.getYAxis().setAutoRanging(false);
        yAxis.setLowerBound(0);
        yAxis.setUpperBound(0.0001);
    }

    @FXML
    private void paint() {
        chart.getData().clear();

//        W w = new W(slider.getValue());
//        chart.getData().add(w.getSeries());
       /* Q q = new Q(slider.getValue());
        chart.getData().add(q.getSeries());*/
        P p = new P(slider.getValue());
        chart.getData().add(p.getSeries());
    }
}
