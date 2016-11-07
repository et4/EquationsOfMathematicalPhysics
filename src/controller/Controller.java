package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Slider;
import model.Q;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    LineChart<Number, Number> chart;
    @FXML
    Slider slider;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void paint() {
//        W w = new W(slider.getValue());
//        chart.getData().add(w.getSeries());
        Q q = new Q(slider.getValue());
        chart.getData().add(q.getSeries());
    }
}
