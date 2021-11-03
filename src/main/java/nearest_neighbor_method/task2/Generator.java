package nearest_neighbor_method.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleFunction;

import static nearest_neighbor_method.task2.Validator.checkSamples;

public class Generator {

    /**
     * Равномерное распределение
     *
     * @param leftBound  левая граница
     * @param rightBound правая граница
     * @param size       размер выборки
     * @return выборку
     */
    public static double[] getIniformX(final double leftBound, final double rightBound, final int size) {
        final double[] result = new double[size];
        final double part = (rightBound - leftBound) / size;

        for (int i = 0; i < result.length; i++) {
            result[i] = leftBound + part * i;
        }
        return result;
    }

    public static double[] getSimpleRandom(final double leftBound, final double rightBound, final int size) {
        final double[] result = new double[size];

        for (int i = 0; i < result.length; i++) {
            result[i] = leftBound + Math.random() * (rightBound - leftBound);
        }

        return result;
    }

    public static double[] getSimpleRandom(final double rightBound, final int size) throws Exception {
        return getSimpleRandom(0, rightBound, size);
    }

    public static double[] getSimpleRandom(final int size) throws Exception {
        return getSimpleRandom(0, 1, size);
    }

    public static List<Sample> getSamples(final double rightBound, final int size, final DoubleFunction<Double> f, final double maxEps) throws Exception {
        List<Sample> samples = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            final double eps = Math.random() * maxEps;
            final double x = Math.random() * rightBound;
            final double y = f.apply(x) + eps;
            samples.add(new Sample(x, y));
        }
        samples.sort(Sample::compareTo);
        checkSamples(samples, 0, rightBound);
        return samples;
    }


    // значения y = f(x_i) + eps_i
    public static double[] getArrY(final double[] arrX, final DoubleFunction<Double> function, final double maxEps) {
        final double[] arrY = new double[arrX.length];

        for (int i = 0; i < arrY.length; i++) {
            final double eps = Math.random() * maxEps;
            if (Math.abs(eps) > maxEps) {
                throw new IllegalStateException("Не корректный eps = " + eps);
            }
            arrY[i] = function.apply(arrX[i]) + eps;
        }
        return arrY;
    }

    public static double[] getRealValue(final DoubleFunction<Double> f, double[] x) {
        double[] realValue = new double[x.length];
        for (int i = 0; i < realValue.length; i++) {
            realValue[i] = f.apply(x[i]);
        }
        return realValue;
    }


    public static double getNextEps() {
        final int n = 12;
        final double[] arrRandom = new double[n];
        double summa = 0.0;
        for (int i = 0; i < arrRandom.length; i++) {
            arrRandom[i] = Math.random();
            summa += arrRandom[i];
        }
        return summa - 6.0;
    }
}
