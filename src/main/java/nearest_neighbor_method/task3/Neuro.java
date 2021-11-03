package nearest_neighbor_method.task3;

import nearest_neighbor_method.task2.Sample;

import java.util.List;
import java.util.function.DoubleFunction;

/**
 * 1 вход
 * 1 выход
 * // todo Пока 1 слой
 */
public class Neuro implements DoubleFunction<Double> {

    private final List<Sample> samples;
    private final DoubleFunction<Double> activationFunction;
    private double learningRate;
    // Количество нейронов.
    private final int numNeurons1;
    private final int numNeurons2;
    private double[] w0;
    private double[] w1;
    private double[] w2;
    private double[] w3;
    // общее количество дуг в сети.
    private final int numTotalArcs;

    public Neuro(List<Sample> samples, DoubleFunction<Double> activationFunction, final int M1, final int M2) {
        this.samples = samples;
        this.activationFunction = activationFunction;
        this.numNeurons1 = M1;
        this.numNeurons2 = M2;

        w0 = new double[numNeurons1 + 1];
        w1 = new double[numNeurons1 + 1];
        w2 = new double[(numNeurons1 + 1) * (numNeurons2 + 1)];
        w3 = new double[numNeurons2 + 1];
        numTotalArcs = w0.length + w1.length + w2.length + w3.length;

        this.learningRate = 0.001;

        learning();
    }

    @Override
    public Double apply(double value) {
        return evaluate(value,getTotalW(w0,w1,w2,w3));
    }

    private double evaluate(final double x, final double[] w) {
        final double[] w0 = getW0(w);
        final double[] w1 = getW1(w);
        final double[] w2 = getW2(w);
        final double[] w3 = getW3(w);

        final double[] a1 = new double[numNeurons1 + 1];

        for (int i = 0; i < a1.length; i++) {
            a1[i] = w0[i] + w1[i] * x;
        }

        final double[] z1 = getZ(a1);

        final double[] a2 = new double[numNeurons2 + 1];

        for (int i = 0; i < a2.length; i++) {
            for (int j = 0; j < z1.length; j++) {
                a2[i] += w2[i * a1.length + j] * z1[j];
            }
        }

        final double[] z2 = getZ(a2);

        return scalarProduct(z2, w3);
    }

    private double[] getZ(final double[] a) {
        final double[] z = new double[a.length];
        for (int i = 0; i < z.length; i++) {
            z[i] = activationFunction.apply(a[i]);
        }
        return z;
    }

    private void learning() {
        double[] bestW = new double[numTotalArcs];
        double bestError = Integer.MAX_VALUE;

        // Количество этапов обучения.
        final int T = 10;
        // Количество итераций приближения
        final int K = 10_000;

        for (int t = 0; t < T; t++) {

            initRandomWeight();

            double error = Integer.MAX_VALUE;
            double errorOld = Integer.MAX_VALUE;
            double[] totalW = new double[0];

            for (int k = 0; k < K; k++) {

                // вычисление градиента функции ошибки.
                double[] nablo = getNablo();

                // обновление весов
                updateWeight(nablo);

                totalW = getTotalW(w0, w1, w2, w3);

                error = getValueErrorFunctions(totalW);

                if (error < 0.01) {
                    bestError = error;
                    bestW = totalW;
                    break;
                }

                // сменить направление и уменьшить шаг
               /* if (errorOld <= error) {
                    learningRate = -0.5 * learningRate;
                }*/

                System.out.printf("эпоха = %d; этап = %d ; ошибка = %.3f ; bestErr = %.3f%n", t, k, error, bestError);
                errorOld = error;
            }


            if (error < bestError) {
                bestError = error;
                bestW = totalW;
            }

            System.out.printf("эпоха = %d ; ошибка = %.3f%n", t, error);

        }

        w0 = getW0(bestW);
        w1 = getW1(bestW);
        w2 = getW2(bestW);
        w3 = getW3(bestW);

        System.out.printf("Лучшая ошибка = %.3f%n", bestError);
    }

    private void updateWeight(double[] nablo) {
        for (int i = 0; i < w0.length; i++) {
            w0[i] -= learningRate * nablo[i];
        }
        for (int i = 0; i < w1.length; i++) {
            w1[i] -= learningRate * nablo[i + w0.length];
        }
        for (int i = 0; i < w2.length; i++) {
            w2[i] -= learningRate * nablo[w0.length + w1.length + i];
        }
        for (int i = 0; i < w3.length; i++) {
            w3[i] -= learningRate * nablo[w0.length + w1.length + w2.length + i];
        }
    }

    private double[] getTotalW(final double[] w0, final double[] w1, final double[] w2, final double[] w3) {
        final double[] res = new double[numTotalArcs];
        for (int i = 0; i < w0.length; i++) {
            res[i] = w0[i];
        }
        for (int i = 0; i < w1.length; i++) {
            res[w0.length + i] = w1[i];
        }
        for (int i = 0; i < w2.length; i++) {
            res[w0.length + w1.length + i] = w2[i];
        }
        for (int i = 0; i < w3.length; i++) {
            res[w0.length + w1.length + w2.length + i] = w3[i];
        }
        return res;
    }

    private double[] getW0(final double[] totalW) {
        final double[] res = new double[w0.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = totalW[i];
        }
        return res;
    }

    private double[] getW1(final double[] totalW) {
        final double[] res = new double[w1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = totalW[i + w0.length];
        }
        return res;
    }

    private double[] getW2(final double[] totalW) {
        final double[] res = new double[w2.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = totalW[i + w0.length + w1.length];
        }
        return res;
    }

    private double[] getW3(final double[] totalW) {
        final double[] res = new double[w3.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = totalW[i + w0.length + w1.length + w2.length];
        }
        return res;
    }

    private double[] getNablo() {
        final double[] nablo = new double[numTotalArcs];
        for (int i = 0; i < nablo.length; i++) {
            nablo[i] = getValuePartialDerivative(i);
        }
        return nablo;
    }


    /**
     * dE / dw_i (w).
     * Вычисление частной производной функции ошибки.
     *
     * @param i номер частной производной.
     * @return значение частной производной.
     */
    private double getValuePartialDerivative(final int i) {
        // todo пока приближенное.

        // todo вынести
        final double delta = 1e-5;

        // 0.5 * E(w_i + delta_i) - E(w_i - delta_i) / delta

        final double[] totalW = getTotalW(w0, w1, w2, w3);

        double[] wPlus = getCopyWeight(totalW);
        double[] wMinus = getCopyWeight(totalW);

        wPlus[i] += delta;
        wMinus[i] -= delta;

        final double errPlus = getValueErrorFunctions(wPlus);
        final double errMinus = getValueErrorFunctions(wMinus);

        return 0.5 * (errPlus - errMinus) / delta;
    }

    private double[] getCopyWeight(double[] w) {
        final double[] copy = new double[w.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = w[i];
        }
        return copy;
    }

    private double getValueErrorFunctions(double[] w) {
        double error = 0.0;

        for (final Sample sample : samples) {

            final double valueNeuro = evaluate(sample.getX(), w);

            error += 0.5 * (valueNeuro - sample.getY()) * (valueNeuro - sample.getY());

        }
        return error;
    }

    // todo задать диапозон подумать как можно разбить область на части
    private void initRandomWeight() {
        final double r = 2 * Math.PI;
        fillRandom(w0,r);
        fillRandom(w1,r);
        fillRandom(w2,r);
        fillRandom(w3,r);
    }

    private void fillRandom(final double[] arr,final double r){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() * 2 * r - r;
        }
    }

    // todo вынести от сюда !!!
    private double scalarProduct(final double[] arr1, final double[] arr2) {
        if (arr1.length != arr2.length) throw new IllegalArgumentException("размеры массивов разные!");
        double sum = 0.0;
        for (int i = 0; i < arr1.length; i++) {
            sum += arr1[i] * arr2[i];
        }
        return sum;
    }

}
