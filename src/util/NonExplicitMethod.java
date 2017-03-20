package util;

import javafx.scene.chart.XYChart;

public class NonExplicitMethod {

    public NonExplicitMethod() {

    }

    private static double[][] getCiclicProgonMatrix(double R, double T, int I, int K) {
        double L = 2.0 * Math.PI * R;
        double hx = L / I;
        double ht = T / K;
        I++;
        K++;
        double[][] P = new double[I][K];
        double[] Pk = new double[I];

        double[] a = new double[I];
        double[] c = new double[I];
        double[] b = new double[I];

        double g = Constants.K * ht / (Constants.C * hx * hx);

        for (int i = 0; i < I; i++) {
            a[i == 0 ? I - 1 : i - 1] = -g;
            c[i] = 1.0 + 2.0 * g;
            b[i == I - 1 ? 0 : i + 1] = -g;
        }

        //заполняем начальным условием
        for (int i = 0; i < I; i++) {
            P[i][0] = psi(i * hx, R);
            Pk[i] = P[i][0];
        }

        //заполняем остальную матрицу
        for (int k = 1; k < K; k++) {
            double[] x = solveMatrix(I, a, c, b, Pk);
            for (int i = 0; i < I; i++) {
                P[i][k] = x[i];
                Pk[i] = P[i][k];
            }
        }

        show(P);
        return P;
    }

    /**
     * n - число уравнений (строк матрицы)
     * b - диагональ, лежащая над главной (нумеруется: [0;n-2])
     * c - главная диагональ матрицы A (нумеруется: [0;n-1])
     * a - диагональ, лежащая под главной (нумеруется: [1;n-1])
     * f - правая часть (столбец)
     * x - решение, массив x будет содержать ответ
     */
    private static double[] solveMatrix(int n, double[] a, double[] c, double[] b, double[] f) {
        double[] alf = new double[n + 1];
        double[] bet = new double[n + 1];
        double[] gam = new double[n + 1];
        double[] p = new double[n];
        double[] q = new double[n];
        double[] x = new double[n];

        alf[1] = b[1] / c[0];
        bet[1] = f[0] / c[0];
        gam[1] = a[0] / c[0];

        for (int i = 1; i <= n - 1; i++) {
            double m = c[i] - a[i] * alf[i];
            alf[i + 1] = b[i] / m;
            bet[i + 1] = (f[i] + a[i] * bet[i]) / m;
            gam[i + 1] = a[i] * gam[i] / m;
        }

        p[n - 2] = bet[n - 1];
        q[n - 2] = alf[n - 1] - gam[n - 1];

        for (int i = n - 3; i >= 0; i--) {
            p[i] = alf[i + 1] * p[i + 1] + bet[i + 1];
            q[i] = alf[i + 1] * q[i + 1] + gam[i + 1];
        }

        x[n - 1] = (bet[n] + alf[n] * p[0]) / (1 - alf[n] * q[0] - gam[n]);
        for (int i = 0; i <= n - 1; i++) {
            x[i] = p[i] + x[n - 1] * q[i];
        }

        return x;
    }


    private static double[][] getMatrix(double R, double T, int I, int K) {
        double L = 2.0 * Math.PI * R;
        double hx = L / I;
        double ht = T / K;
        double[][] P = new double[I + 1][K + 1];

        for (int i = 0; i <= I; i++) {
            P[i][0] = psi(i * hx, R);
        }


        for (int k = 0; k <= K - 1; k++) {
            for (int i = 0; i <= I; i++) {
                double m = P[i][k];
                double m1 = i == 0 ? P[I][k] : P[i - 1][k];
                double m2 = i == I ? P[1][k] : P[i + 1][k];

                P[i][k + 1] =
                        (ht * Constants.K * (m2 - 2 * m + m1)) / (Constants.C * hx * hx) + m;
            }
            P[0][k] = P[I][k];
        }
        show(P);
        return P;
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

    private static double psi(double x, double R) {
        if (0 <= x && x <= Math.PI * R / 2.0) {
            return 2.0 * x / (R * Math.PI);
        } else if (Math.PI * R / 2.0 < x && x <= Math.PI * R) {
            return 2.0 - (2.0 * x / (R * Math.PI));
        }
        return 0;
    }

    public static XYChart.Series<Number, Number> getSeries(double R, double T, int I, int K, double time) {
        double[][] matrix = getMatrix(R, T, I, K);
        double L = 2.0 * Math.PI * R;
        double hx = L / I;
        I++;
        XYChart.Series<Number, Number> series = new XYChart.Series();
        series.setName("Явная схема");
        int t = (int) Math.round(time);
        for (int i = 0; i < I; i++) {
            series.getData().add(new XYChart.Data(hx * i, matrix[i][t]));
        }
        return series;
    }
}
