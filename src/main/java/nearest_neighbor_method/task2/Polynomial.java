package nearest_neighbor_method.task2;

import java.util.Arrays;
import java.util.function.DoubleFunction;

/**
 * Класс многочлен. Степень многочлена = coefficients.lenght - 1
 * cof[0] + cof[1] * x + cof[2] * x^2 ...
 */
public class Polynomial {
    private final double[] coefficients;

    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public double getValue(final double x) {
        double sum = 0;
        for (int i = 0; i < coefficients.length; i++) {
            sum += coefficients[i] * Math.pow(x, i);
        }
        return sum;
    }

    public DoubleFunction<Double> getFunctions(){
        return this::getValue;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("degree = ").append(coefficients.length - 1).append("\n");
        sb.append("cof = ").append(Arrays.toString(coefficients));
        return sb.toString();
    }
}
