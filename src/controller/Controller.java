package controller;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import model.Q;
import util.Constants;
import util.ExplicitMethod;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    LineChart<Number, Number> chart;
    @FXML
    Slider slider;
    @FXML
    NumberAxis yAxis;
    @FXML
    TextField textBoxI;
    @FXML
    TextField textBoxK;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chart.setCreateSymbols(false);
//        chart.setLegendVisible(false);
//        ExplicitMethod.show(ExplicitMethod.getMatrix(Constants.L, Constants.T,
//                Integer.parseInt(textBoxI.getText()),
//                Integer.parseInt(textBoxK.getText())));
        chart.getYAxis().setAutoRanging(false);
//        yAxis.setLowerBound(0);
//        yAxis.setUpperBound(0.0001);
    }

    @FXML
    private void paint() {
//        W w = new W(slider.getValue());
//        chart.getData().add(w.getSeries());

//        P p = new P(slider.getValue());
//        chart.getData().add(p.getSeries());
//        ExplicitMethod.show(ExplicitMethod.getMatrix(Constants.L, Constants.T, 1000, 1000));
    }

    public void okEvent() {
        chart.getData().clear();

        Q q = new Q(slider.getValue());
        chart.getData().add(q.getSeries());

        int I = Integer.parseInt(textBoxI.getText());
        int K = Integer.parseInt(textBoxK.getText());
        double time = slider.getValue();
        chart.getData().add(ExplicitMethod.getSeries(Constants.L, Constants.T,
                I, K, time));
    }
}
