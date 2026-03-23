package first;

public class Secant {

    private static final double[] SEC_COEFFS = {
            1.0,
            1.0 / 2.0,
            5.0 / 24.0,
            61.0 / 720.0,
            1385.0 / 40320.0,
            50521.0 / 3628800.0,
            2702765.0 / 479001600.0,
            199360981.0 / 87178291200.0,
            19391512145.0 / 20922789888000.0
    };

    public double calculate(double x, double precision) {
        if (Double.isNaN(x) || Math.abs(x) >= Math.PI / 2.0) {
            throw new IllegalArgumentException("Значение x вне радиуса сходимости (-pi/2, pi/2)");
        }
        if (precision <= 0) {
            throw new IllegalArgumentException("Точность должна быть положительным числом");
        }

        double result = 0.0;
        double x2 = x * x;
        double xPower = 1.0;

        for (double coeff : SEC_COEFFS) {
            double term = coeff * xPower;
            result += term;

            if (Math.abs(term) < precision) {
                return result;
            }

            xPower *= x2;
        }

        return result;
    }
}