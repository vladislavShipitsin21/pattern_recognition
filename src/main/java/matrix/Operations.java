package matrix;

import java.util.Random;
import java.util.Scanner;

public class Operations {

    public static void fillRandom(double[][] matrix,int bound){
        Random random = new Random();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length ; j++) {
                matrix[i][j] = random.nextInt(bound);
            }
        }
    }
    public static void fillConsole(double[][] matrix){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entet values matrix " + matrix.length + "*" + matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length ; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
    }
    public static void multiOnNumber(double[][] matrix,double kof){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] *= kof;
            }
        }
    }
    public static void swapLines(double[][] matrix,int i,int j){
        double[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }
    public static void getColomJ(double[][] matrix,double[] colom,int j){
        for (int i = 0; i < matrix.length; i++) {
            colom[i] = matrix[i][j];
        }
    }
    public static void getLineI(double[][] matrix,double[] line,int i){
        for (int j = 0; j < matrix.length; j++) {
            line[j] = matrix[i][j];
        }
    }
    public static void printMatrixAndF(double[][] matrix,double[] f,String s){
        if(s == null) {
            System.out.println("Matrix: ");
        }else{
            System.out.println(s);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%10.5f  ",matrix[i][j]);
            }
            System.out.printf(" |%10.5f\n", f[i]);

        }
        System.out.println();
    }
    public static void printMatrix(double[][] matrix,String s){
        if(s == null) {
            System.out.println("Matrix: ");
        }else{
            System.out.println(s);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%10.5f  ",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printMatrix(int[][] matrix,String s){
        if(s == null) {
            System.out.println("Matrix: ");
        }else{
            System.out.println(s);
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.printf("%5d  ",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void swapColom(int[][] matrix,int i,int j){
        int temp = 0;
        for (int k = 0; k < matrix.length ; k++) {
            temp = matrix[k][i];
            matrix[k][i] = matrix[k][j];
            matrix[k][j] = temp;
        }
    }
    public static void transportMatrix(double[][] matrix){
        double temp = 0;
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
    public static void normalVector(double[] arr,int size){
        double normArr = Operations.normaEuclid(arr);
        for (int i = arr.length - size; i < arr.length ; i++) {
            arr[i] /= normArr;
        }
    }
    public static void normalVector(double[] arr){
        double normArr = Operations.normaEuclid(arr);
        for (int i = arr.length; i < arr.length ; i++) {
            arr[i] /= normArr;
        }
    }
    public static void invertMatrixTreangle(double[][] matrix){

        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                if(i == j){
                    matrix[i][j] = 1 / matrix[i][j];
                }else {
                    sum = 0;
                    for (int s = i; s < j; s++) {
                        sum += matrix[i][s] * matrix[s][j];
                    }
                    matrix[i][j] = - sum / matrix[j][j];
                }
            }
        }
    }
    public static void invertMatrixTreangleDown(double[][] matrix){

        double sum = 0;
        for (int j = 0; j < matrix.length; j++) {
            for (int i = j + 1; i < matrix.length; i++) {
                sum = 0;
                for (int s = j + 1; s < i; s++) {
                    sum += matrix[i][s] * matrix[s][j];
                }
                sum += matrix[i][j];
                matrix[i][j] = - sum;
            }
        }
    }
    public static void multiplyUL(double[][] matrix) {
        double[] values = new double[matrix.length];
        double buffer = 0;

        for(int i = 0; i < matrix.length; i++) {
            for (int k = 0; k < matrix.length; k++) {
                for (int j = k; j < matrix.length; j++) {
                    if(j >= i) {
                        if (k == j) {
                            buffer += matrix[i][j];
                        } else {
                            buffer += matrix[i][j] * matrix[j][k];
                        }
                    }
                }
                values[k] = buffer;
                buffer = 0;
            }
            for(int cp = 0; cp < values.length; cp++) {
                matrix[i][cp] = values[cp];
            }
        }
    }

    public static void swapElem(double[] arr,int i,int j){
        double temp;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void printArr(double[] arr,String s){
        if(s == null) {
            System.out.println("Array: ");
        }else{
            System.out.println(s);
        }
        for(double el : arr){
            System.out.printf("%10.5f ",el);
        }
        System.out.println();
        System.out.println();
    }
    public static void printArr(int[] arr,String s){
        if(s == null) {
            System.out.println("Array: ");
        }else{
            System.out.println(s);
        }
        for(int el : arr){
            System.out.printf("%2d  ",el);
        }
        System.out.println();
        System.out.println();
    }


    public static boolean equalsMatrixE(double[][] matrix){
        double error = 1e-9;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length ; j++) {
                if(i == j){
                    if(Double.compare(Math.abs(matrix[i][j] - 1),error) >= 0) return false;
                }else{
                    if(Double.compare(Math.abs(matrix[i][j]),error) >= 0) return false;
                }
            }
        }
        return true;
    }

    public static double maxArrAbs(double[] arr){
        double result = Math.abs(arr[0]);
        for (int i = 1; i < arr.length ; i++) {
            if(Math.abs(arr[i]) > result){
                result = Math.abs(arr[i]);
            }
        }
        return result;
    }
    public static double normaEuclid(double[] arr){
        double result = 0;
        for(double el : arr){
            result += el * el;
        }
        result = Math.sqrt(result);
        return result;
    }
    public static double scalMulti(double[] arr1,double[] arr2,int size){
        double result = 0;
        for (int i = arr1.length - size; i < arr1.length; i++) {
            result += arr1[i] * arr2[i];
        }
        return result;
    }
    public static double getDeterminant(double[][] matrix){
        int n = matrix.length;
        int nm1 = n - 1;
        int kp1;
        double p;
        double det=1;
        for (int k = 0; k < nm1; k++)
        {
            kp1 = k + 1;
            for(int i=kp1;i<n;i++)
            {
                p = matrix[i][ k] / matrix[k][ k];
                for (int j = kp1; j < n; j++)
                    matrix[i][ j] = matrix[i][ j] - p * matrix[k][ j];
            }
        }
        for (int i = 0; i < n; i++)
            det = det * matrix[i][ i];
        return det;
    }


    public static double[] aMultiX(double[][] a,double[] x){
        double[] f = new double[x.length];
        double element = 0;
        for (int i = 0; i < x.length ; i++) {
            element = 0;
            for (int j = 0; j < x.length; j++) {
                element += x[j] * a[i][j];
            }
            f[i] = element;
        }
        return f;
    }

    public static double[] copyArr(double[] arr){
        double[] result = new double[arr.length];
        for (int i = 0; i < result.length ; i++) {
            result[i] = arr[i];
        }
        return result;
    }
    public static double[] nevazka(double[][] a,double[] x,double[] f){
        double[] temp = aMultiX(a,x);
        double[] result = new double[temp.length];

        for (int i = 0; i < result.length ; i++) {
            result[i] =f[i] - temp[i];
        }
        return result;
    }
    public static double[] generateSolutions(int size){
        double[] result = new double[size];
        for (int i = 0; i < result.length ; i++) {
            result[i] = Math.sin(i);
        }
        return result;
    }

    public static double[][] multiMatrix(double[][] oneMatrix,double[][] twoMatrix){

        double[][] resultMatrix = new double[oneMatrix.length][twoMatrix.length];
        double element = 0;
        for (int i = 0; i < oneMatrix.length; i++) {
            for (int j = 0; j < twoMatrix.length; j++) {
                element = 0;
                for (int k = 0; k < oneMatrix.length; k++) {
                    element += oneMatrix[i][k] * twoMatrix[k][j];
                }
                resultMatrix[i][j] = element;
            }
        }
        return resultMatrix;
    }
    public static double[][] getMatrixMultiOnNumber(double[][] matrix,double kof){
        double[][] resultMatrix = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                resultMatrix[i][j] = matrix[i][j] * kof;
            }
        }
        return resultMatrix;
    }
    public static double[][] minusMatrix(double[][] oneMatrix,double[][] twoMatrix){
        double[][] resultMatrix = new double[oneMatrix.length][oneMatrix.length];
        for (int i = 0; i < oneMatrix.length; i++) {
            for (int j = 0; j < oneMatrix.length; j++) {
                resultMatrix[i][j] = oneMatrix[i][j] - twoMatrix[i][j];
            }
        }
        return resultMatrix;
    }
    public static double[][] plusMatrix(double[][] oneMatrix,double[][] twoMatrix){
        double[][] resultMatrix = new double[oneMatrix.length][oneMatrix.length];
        for (int i = 0; i < oneMatrix.length; i++) {
            for (int j = 0; j < oneMatrix.length; j++) {
                resultMatrix[i][j] = oneMatrix[i][j] + twoMatrix[i][j];
            }
        }
        return resultMatrix;
    }
    public static double[][] getTransportMatrix(double[][] matrix){
        double[][] result = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[j][i];
            }
        }
        return result;
    }
    public static double[][] copyMatrix(double[][] arr){
        double[][] result = new double[arr.length][arr.length];
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j < arr.length; j++) {
                result[i][j] = arr[i][j];
            }
        }
        return result;
    }
    public static double[][] multiArrOnArrT(double[] arr){
        double[][] result = new double[arr.length][arr.length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result.length ; j++) {
                result[i][j] = arr[i] * arr[j];
            }
        }
        return result;
    }
    public static double[][] getMatrixE(int size){
        double[][] matrixE = new double[size][size];
        for (int i = 0; i < size ; i++) {
            matrixE[i][i] = 1;
        }
        return matrixE;
    }

    public static double[][] invertMatrixL(double[][] matrix){
        double[][] resultMatrix = new double[matrix.length][matrix.length];
        double sum = 0;
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = i; j < resultMatrix.length; j++) {
                if(i == j){
                    resultMatrix[i][j] = 1 / matrix[i][j];
                }else {
                    sum = 0;
                    for (int s = 0; s < j; s++) {
                        sum += resultMatrix[i][s] * matrix[s][j];
                    }
                    resultMatrix[i][j] = - sum / matrix[j][j];
                }
            }
        }
        return resultMatrix;
    }

    public static double[][] generateMatrixL(int size){
        double[][] resultMatrix = new double[size][size];
        Random random = new Random();
        for (int i = 0; i < resultMatrix.length; i++) {
            for (int j = i; j < resultMatrix.length; j++) {
                resultMatrix[i][j] = random.nextInt(100) + 1;
            }
        }
        return resultMatrix;
    }

    public static double[] nevazka(final double[] expected,final double[] actual){
        if(expected.length != actual.length) throw new IllegalStateException("Развые размеры массивов");

        double[] result = new double[expected.length];
        for (int i = 0; i < result.length ; i++) {
            result[i] = Math.abs(expected[i] - actual[i]);
        }
        return result;
    }

}
