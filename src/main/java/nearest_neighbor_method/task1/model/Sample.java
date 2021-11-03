package nearest_neighbor_method.task1.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Sample implements Comparable<Sample> {
    private final String name;
    private final int value;
    private final int[][] matrix;
    private final int background;
    private final int foreground;

    public Sample(final Sample copy) {
        this.name = copy.name;
        this.value = copy.value;
        this.background = copy.background;
        this.foreground = copy.foreground;
        final int size = copy.matrix.length;
        matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(copy.matrix[i], 0, matrix[i], 0, size);
        }
    }

    public Sample(final int num) throws FileNotFoundException {
        String nameFile = "D:\\university\\pattern_recognition\\src\\main\\java\\nearest_neighbor_method\\task1\\standart\\" + num + ".txt";
        name = String.valueOf(num);
        value = num;
        background = 0;
        foreground = 1;

        final int size = 9;
        Scanner scanner = new Scanner(new File(nameFile));

        matrix = new int[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    public Sample(final int num, final int background, final int foreground) throws FileNotFoundException {
        String nameFile = "D:\\university\\pattern_recognition\\src\\main\\java\\nearest_neighbor_method\\task1\\standart\\" + num + ".txt";
        name = String.valueOf(num);
        value = num;

        final int size = 9;
        Scanner scanner = new Scanner(new File(nameFile));

        matrix = new int[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                final int value = scanner.nextInt() == 0 ? background : foreground;
                matrix[i][j] = value;
            }
        }
        this.background = background;
        this.foreground = foreground;
    }

    public void setElement(int i, int j, int elem) {
        matrix[i][j] = elem;
    }

    public int getElement(int i, int j) {
        return matrix[i][j];
    }

    public int getSize() {
        return matrix.length;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getBackground() {
        return background;
    }

    public int getForeground() {
        return foreground;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                char c = ints[j] == background ? '.' : '*';
                sb.append(c);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sample sample = (Sample) o;
        return Arrays.equals(matrix, sample.matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(matrix);
    }

    @Override
    public int compareTo(Sample s) {
        return Integer.compare(value, s.value);
    }
}
