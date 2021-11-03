package nearest_neighbor_method.task1.model;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Printer {

    public static void printSample(Sample sample){
        System.out.println(sample.toString());
    }

    public static void printNumber(int num) throws IOException {
        String nameFile = "C:\\java_university\\pattern_recognition\\src\\main\\java\\nearest_neighbor_method\\standart\\" + num + ".txt";
        File file = new File(nameFile);
        final int size = 9;
        Scanner scanner = new Scanner(file);

        for (int i = 0; i < size; i++) {
            String byteNum = scanner.nextLine();
            for (int j = 0; j < byteNum.length(); j++) {
                char c = byteNum.charAt(j) == '1' ? '1' : ' ';
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void printArr(final double[] arr){
        System.out.println(Arrays.toString(arr));
    }

    public static void printMatrix(final int[][] matrix){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        System.out.format("%5.2f",-12.12345);
    }
}
