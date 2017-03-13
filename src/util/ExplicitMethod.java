package util;

import javafx.scene.chart.XYChart;

public class ExplicitMethod {

    public ExplicitMethod() {

    }


    private static double[][] getMatrix(double L, double T, int I, int K) {
        double hz = L / I;
        double ht = T / K;
        double[][] Q = new double[I + 1][K + 1];

        for (int i = 0; i <= I; i++) {
            Q[i][0] = psi(i * hz, L);
        }


        for (int k = 0; k <= K - 1; k++) {
            if (k != 0) {
                Q[0][k] = Q[1][k];
                Q[I][k] = Q[I - 1][k];
            }
            for (int i = 1; i <= I - 1; i++) {
                Q[i][k + 1] = -2 * Constants.AL * Q[i][k] * ht / (Constants.D * Constants.C) +
                        (ht * Constants.K * (Q[i + 1][k] - 2 * Q[i][k] + Q[i - 1][k])) / (Constants.C * hz * hz) + Q[i][k];
            }
//            if (k != 0) {
//                Q[0][k] = Q[1][k];
//                Q[I][k] = Q[I - 1][k];
//            }
        }

        for (int k = 1; k <= K; k++) {
            Q[0][k] = Q[1][k];
            Q[I][k] = Q[I - 1][k];
        }

        return Q;
    }

    public static void show(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%8.2f", matrix[i][j]);
            }
            System.out.println();
        }

//        for (int i = 0; i < matrix.length; i++) {
//            System.out.println(matrix[i][6]);
//        }
    }

    private static double psi(double z, double L) {
        return (L / 4 <= z && z <= 3 * L / 4) ? 1 : 0;
    }

    public static XYChart.Series<Number, Number> getSeries(double L, double T, int I, int K, double time) {
        double[][] matrix = getMatrix(L, T, I, K);
        double hz = L / I;
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName("Явная схема");
        int t = (int) Math.round(time);
        for (int i = 0; i <= I; i++) {
            series.getData().add(new XYChart.Data(hz * i, matrix[i][t]));
        }
        return series;
    }
}
