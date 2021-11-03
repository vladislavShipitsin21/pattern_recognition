package nearest_neighbor_method.task2;

import matrix.Operations;
import matrix.Realise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrossValidation {

    // todo добавить регуляризацию
    // todo выбрав лучшую степень обучить сетку на всей выборке
    // todo плохо обусловленная матрицы -> рещультат не соответсвует ожиданиям.

    private CrossValidation() {
        throw new IllegalStateException("Utility class");
    }

    public static Polynomial calculate(List<Block> blocks, int... degreePolynomial) {
        // основной цикл кросс-валидации

        final double[][] tableError = new double[degreePolynomial.length][blocks.size()];

        final Polynomial[][] polynomials = new Polynomial[degreePolynomial.length][blocks.size()];

        // фиксируем степень полинома!
        for (int i = 0; i < degreePolynomial.length; i++) {
            final int degree = degreePolynomial[i];
            System.out.println(degree);

            // фиксируем контрольную выборку!
            for (int j = 0; j < blocks.size(); j++) {
                final Block controlBlock = blocks.get(j);

                final List<Block> teachSample = new ArrayList<>(blocks);
                teachSample.remove(controlBlock);

                // получить матрицу по выборке !
                final double[][] matrixA = getMatrixBySample(teachSample, degree);

                final double[] arrB = getArrB(teachSample, degree);

//                Operations.printMatrixAndF(matrixA, arrB, "SLAU : ");

                // решить слау

                final double[] solutionW = Realise.doGouse(matrixA, arrB); // коэффиценты полинома

                final Polynomial polynomial = new Polynomial(solutionW);
                // сохраняем результат в таблицу полиномов
                polynomials[i][j] = polynomial;

                Validator.checkSLAUSolutions(matrixA, solutionW, arrB);

                // проверяю его ошибку с контрольной выборкой
                tableError[i][j] = getError(controlBlock, polynomial);;
            }
        }
        final Polynomial resultPolynomial = getBestPolynomial(tableError, polynomials);
        Printer.printTableError(tableError, blocks.size(), degreePolynomial);
        return resultPolynomial;
    }

    private static double[][] getMatrixBySample(List<Block> teachSample, final int degree) {
        double[][] resultMatrixA = new double[degree][degree];

        for (int i = 0; i < resultMatrixA.length; i++) {
            for (int j = 0; j < resultMatrixA[i].length; j++) {
                resultMatrixA[i][j] = 0;
                for (Block block : teachSample) {
                    for (Sample sample : block.getSamples()) {
                        resultMatrixA[i][j] += Math.pow(sample.getX(), (i + j));
                    }
                }
            }
        }
        return resultMatrixA;
    }

    private static double[] getArrB(List<Block> teachSample, final int degreePolinom) {
        double[] resultArr = new double[degreePolinom];

        for (int i = 0; i < resultArr.length; i++) {
            resultArr[i] = 0;
            for (Block block : teachSample) {
                for (Sample sample : block.getSamples()) {
                    resultArr[i] += Math.pow(sample.getX(), i) * sample.getY();
                }
            }

        }
        return resultArr;
    }

    /**
     * Считает ошибку.
     *
     * @param controlSample контрольная выборка
     * @param polynomial    полином
     * @return Максимальную абсолютную разницу между значениями полинома и выборкой.
     */
    private static double getError(final Block controlSample, final Polynomial polynomial) {
        double normaError = Integer.MIN_VALUE;

        for (Sample sample : controlSample.getSamples()) {
            double actualValue = polynomial.getValue(sample.getX());

            double expectedValue = sample.getY();

            double error = Math.abs(expectedValue - actualValue);

            if (Double.compare(error, normaError) > 0) {
                normaError = error;
            }
        }
        return normaError;
    }

    private static Polynomial getBestPolynomial(final double[][] tableError, final Polynomial[][] polynomials) {
        double minSumDegree = Integer.MAX_VALUE;
        int indexDegree = -1;
        for (int i = 0; i < tableError.length; i++) {
            final double currentSum = Arrays.stream(tableError[i]).sum();

            if (currentSum < minSumDegree) {
                minSumDegree = currentSum;
                indexDegree = i;
            }
        }
        double minErrorBlock = Integer.MAX_VALUE;
        int indexMinErrorBlock = -1;
        for (int j = 0; j < tableError[indexDegree].length; j++) {
            double currentError = tableError[indexDegree][j];
            if (currentError < minErrorBlock) {
                minErrorBlock = currentError;
                indexMinErrorBlock = j;
            }
        }
        return polynomials[indexDegree][indexMinErrorBlock];
    }
}
