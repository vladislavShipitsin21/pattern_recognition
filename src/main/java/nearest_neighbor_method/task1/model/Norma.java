package nearest_neighbor_method.task1.model;

public class Norma {

    public static double value(Sample one, Sample two) {
        int resultValue = 0;
        int size = one.getSize();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                resultValue += Math.abs(one.getElement(i,j) - two.getElement(i,j));
//                resultValue += (one.getElement(i, j) - two.getElement(i, j)) *
//                        (one.getElement(i, j) - two.getElement(i, j));
            }
        }
        return Math.sqrt(resultValue);
    }

    public static double normaResultMatrix(double[][] matrix) {
        double result = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length && i != j; j++) {
                if (matrix[i][j] > result) result = matrix[i][j];
            }
        }
        return result;
    }

    public static double summaryError(double[][] matrix, int countSamples) {
        double result = 0;

        for (int i = 0; i < matrix.length; i++) {
            result += 100 - matrix[i][i];
        }
        return result / countSamples;
    }
}