package util;

import static java.lang.Math.sqrt;

public class Constants {
    private Constants() {

    }

    public static final int AMOUNT_SAMPLING_INTERVAL = 1000;
    public static final double K = 1800;
    public static final double C = 7.6e6;
    public static final double L = 1.2;
    public static final double R = 0.2;
    public static final double D = 2e-3;
    public static final double AL = 10;
    public static final double Uc = 18;
    public static final double T = 200;
    public static final double A = sqrt(K/C);
}
