package nearest_neighbor_method.task2;

import matrix.Operations;

import java.util.List;

public class Validator {
    private static final double EPS = 1E-3;

    public static void checkSamples( List<Sample> samples, final double leftBound, final double rightBound) throws Exception {
        double prev = samples.get(0).getX();
        validSample(prev,leftBound,rightBound);
        for (int i = 1; i < samples.size() - 1; i++) {
            double next = samples.get(i).getX();
            validSample(next,leftBound,rightBound);
            validSortSample(prev, next);
            prev = next;
        }
        if (samples.size() >= 2) {
            double next = samples.get(samples.size() - 1).getX();
            validSample(next,leftBound,rightBound);
            validSortSample(prev, next);
        }
    }

    private static void validSample(double sample,final double leftBound,final double rightBound) throws Exception {
        if (Double.compare(sample, leftBound) < 0 || Double.compare(sample, rightBound) > 0) {
            throw new Exception("НЕ КОРРЕКТНАЯ ВЫБОРКА sample[i] = " + sample);
        }
    }

    private static void validSortSample(double prev, double next) throws Exception {
        if (Double.compare(next, prev) <= 0) throw new Exception("ВЫБОРКА НЕ ОТСОРТИРОВАНА");
    }

    public static void isCorrectBlocks(final List<Block> blocks, final int sizeSample, final double eps) throws Exception {
        int realSize = 0;

        for (final Block block : blocks) {
            List<Sample> currentSample = block.getSamples();
            realSize += currentSample.size();

            double prev = currentSample.get(0).getX();
            for (int i = 1; i < currentSample.size() - 1; i++) {
                double next = currentSample.get(i).getX();
                if (Double.compare(next - prev, eps) >= 0) {
                    throw new Exception("В БЛОКЕ НЕ СОИЗМЕРИМЫЕ ПЕРЕМЕНЫЕ \n" +
                            "block = " + block + " eps = " + eps);
                }
                prev = next;
            }
            if (currentSample.size() >= 2) {
                double next = currentSample.get(currentSample.size() - 1).getX();
                if (Double.compare(next - prev, eps) >= 0) {
                    throw new Exception("В БЛОКЕ НЕ СОИЗМЕРИМЫЕ ПЕРЕМЕНЫЕ \n" +
                            "block = " + block + " eps = " + eps);
                }
            }
        }
        if (realSize != sizeSample) throw new Exception("В БЛОКАХ МЕНЬШЕ ЭЛЕМЕНТОВ ЧЕМ В ВЫБОРКЕ");
    }

    public static void checkSLAUSolutions(final double[][] a, double[] x, double[] b) {
        final double[] nevazka = Operations.nevazka(a, x, b);
//        if (Operations.maxArrAbs(nevazka) > EPS) throw new IllegalStateException("Плохо решает СЛАУ");
    }
}
