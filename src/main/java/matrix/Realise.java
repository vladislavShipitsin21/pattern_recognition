package matrix;


public class Realise {

    public static double[] doGouse(double[][] matrix,double[] f){
        int s = 0;
        double kof = 0;
        for (int i = 0; i < matrix.length; i++) {

            s = i;
            while(! (Double.compare(Math.abs(matrix[s][i]),1.e-15) > 0)){
                s++;
            }
            Operations.swapLines(matrix,i,s);
            Operations.swapElem(f,i,s);
            for (int j = i + 1; j < matrix.length; j++) {
                kof =  - (matrix[j][i] / matrix[i][i]);
                for (int l = i; l < matrix.length; l++) {
                    matrix[j][l] += kof * matrix[i][l];
                }
                f[j] += kof * f[i];
            }
        }
        return getSolutions(matrix,f);
    }

    public static double[] reflectionsLines(double[][] matrix,double[] f){

        boolean allZero = true;

        int n = matrix.length;

        double signScalMulti = 0;

        double[] vectorY = new double[n];
        double[] vectorE = new double[n];
        double[] vectorW = new double[n];

        double[][] matrixU = null;
        double[][] matrixE = new double[n][n];
        double[][] matrix_wwT = null;


        for (int i = 0; i < matrixE.length; i++) {
            matrixE[i][i] = 1;
        }

        for (int l = 0; l < n - 1 ; l++) {
            // (n-1) шаг
            for (int i = 0; i < vectorY.length; i++) {
                vectorY[i] = 0;
            }
            for (int i = l; i < vectorY.length ; i++) {
                vectorY[i] = matrix[i][l];
            }
            Operations.normalVector(vectorY,vectorY.length);

            if(l - 1 >= 0) vectorE[l - 1] = 0;
            vectorE[l] = 1;


            allZero = true;
            for (int i = l + 1; i < matrix.length; i++) {
                if(matrix[i][l] != 0){
                    allZero = false;
                }
            }
            if(!allZero){
                // V(l) = E(n-l+1) - 2w(l)w(l)T

                signScalMulti = Math.signum(Operations.scalMulti(vectorY,vectorE,vectorE.length));

                for (int i = 0; i < vectorW.length ; i++) {
                    vectorW[i] = vectorY[i] + signScalMulti * vectorE[i];
                }
                Operations.normalVector(vectorW,vectorW.length);

                matrix_wwT = Operations.multiArrOnArrT(vectorW);
                Operations.multiOnNumber(matrix_wwT,-2);

                matrixU =  Operations.plusMatrix(matrixE,matrix_wwT);

                matrix = Operations.multiMatrix(matrixU,matrix);
                f = Operations.aMultiX(matrixU,f);

            }
        }
        return getSolutions(matrix,f);
    }

    private static double[] getSolutions(double[][] matrixA,double[] vectorF){
        double[] resultX = new double[matrixA.length];
        double summa = 0;
        for (int k = matrixA.length - 1 ;k >= 0  ; k--) {
            summa = 0;
            for (int i = k + 1; i < matrixA.length ; i++) {
                summa += matrixA[k][i] * resultX[i];
            }
            resultX[k] = (vectorF[k] - summa) / matrixA[k][k];
        }
        return resultX;
    }

    public static void invertMatrixGoodReflect(double[][] matrix){

        boolean allZero = true;
        int n = matrix.length;
        int s = 0;
        double summa = 0;
        double scalMulti = 0;
        int[] arrSwaps    = new int[n];
        double[] colomMatrix = new double[n];
        double[] lineMatrix  = new double[n];
        double[] vectorW     = new double[n];
        double[] diogonalW   = new double[n];


        for (int i = 0; i < n ; i++) {
            arrSwaps[i] = -1;
        }

        for (int l = 0; l < n - 1 ; l++) {

            allZero = true;
            for (int i = l + 1; i < n; i++) {
                if (Double.compare(matrix[i][l],0) != 0) {
                    allZero = false;
                }
            }
            if (!allZero) {

                for (int i = 0; i < l; i++) {
                    vectorW[i] = 0;
                }
                for (int i = l; i < n; i++) {
                    vectorW[i] = matrix[i][l];
                }
                Operations.normalVector(vectorW, n - l);

                if(Math.signum(vectorW[l]) == 0){
                    vectorW[l] += 1;
                }else{
                    vectorW[l] += Math.signum(vectorW[l]);
                }

                Operations.normalVector(vectorW, n - l);

                for (int j = l; j < n; j++) {

                    Operations.getColomJ(matrix, colomMatrix, j);

                    scalMulti = Operations.scalMulti(vectorW,colomMatrix,vectorW.length - l);

                    scalMulti += scalMulti;

                    for (int i = l; i < n; i++) {
                        matrix[i][j] -= vectorW[i] * scalMulti;
                    }
                }
                diogonalW[l] = vectorW[l];
                for (int i = l + 1; i < n; i++) {
                    matrix[i][l] = vectorW[i];
                }
            }
        }
        Operations.invertMatrixTreangle(matrix);
        // matrix = L^-1
        for (int l = n - 2; l >= 0 ; l--) {

            for (int i = 0; i < n; i++) {
                vectorW[i] = 0;
            }
            vectorW[l] = diogonalW[l];
            for (int i = l + 1; i < n ; i++) {
                vectorW[i] = matrix[i][l];
                matrix[i][l] = 0;
            }

            for (int i = 0; i < n; i++) {

                Operations.getLineI(matrix,lineMatrix,i);

                scalMulti = Operations.scalMulti(lineMatrix,vectorW,vectorW.length - l);
                scalMulti += scalMulti;

                for (int j = l; j < n; j++) {
                    matrix[i][j] -= scalMulti * vectorW[j];
                }
            }
        }
    }

    public static void doJordan(double[][] matrix){
        int n = matrix.length;
        double[] colom = new double[n];
        double[] line = new double[n];

        for (int k = -1; k < n - 1; k++) {

            Operations.getColomJ(matrix,colom,k + 1);
            Operations.getLineI(matrix,line,k + 1);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(i == k + 1){
                        if(j == k + 1){
                            matrix[i][j] = 1./line[k + 1];
                        }else{
                            matrix[i][j] = line[j] / line[k + 1];
                        }
                    }else{
                        if(j == k + 1){
                            matrix[i][j] =  -colom[i] / line[k + 1];
                        }else{
                           matrix[i][j] -= (line[j] *colom[i]) / (line[k + 1]);
                        }
                    }
                }
            }
        }
    }

// ----------------------------------------------------------------------*

    public static void invertMatrixLU(double[][] matrix){
        int[] transposition = luDecomposition(matrix);
        Operations.invertMatrixTreangleDown(matrix);
        Operations.invertMatrixTreangle(matrix);
        Operations.multiplyUL(matrix);

        int buf = 0;
        for (int i = transposition.length - 1; i >= 0 ; i--) {
            buf += transposition[i] == i ? 0 : transposition[i];
        }
        for (int i = 0; i< transposition.length ; i++) {
            if(transposition[i] != i && buf > 0) {
                Operations.swapLines(matrix, i, transposition[i]);
                buf -= transposition[i];
            }
        }
        Operations.printArr(transposition,"trans: ");
    }

    public static int[] luDecomposition(double[][] matrix) {
        int[] matrixTransposition = transpositionInit(matrix.length);
        int bufferTranspositionPos = maxAbsElementNext(matrix, 0, 1);
        transposition(matrix, 0, bufferTranspositionPos, matrixTransposition);

        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] /= matrix[0][0];
        }

        double bufferU = 0, bufferL;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {

                bufferU = 0;

                for (int k = 0; k < i; k++) {
                    bufferU += matrix[i][k] * matrix[k][j];
                }
                matrix[i][j] -= bufferU;
            }

            for (int j = i; j < matrix.length; j++) {
                bufferL = 0;

                if (j + 1 < matrix.length) {
                    bufferTranspositionPos = maxAbsElementNext(matrix, 0, i);
                    transposition(matrix, 0, bufferTranspositionPos, matrixTransposition);

                    for (int k = 0; k < i; k++) {
                        bufferL += matrix[j + 1][k] * matrix[k][i];
                    }
                    matrix[j + 1][i] = (matrix[j + 1][i] - bufferL) / matrix[i][i];
                }
            }
        }
        return matrixTransposition;
    }

    private static int[] transpositionInit(int size) {
        int[] transposition = new int[size];
        for (int i = 0; i < size; i++) {
            transposition[i] = i;
        }
        return transposition;
    }

    private static int maxAbsElementNext(double[][] matrix, int row, int from) {
        int maxElementPosition = 0;
        double maxElement = Math.abs(matrix[row][0]);

        for (int i = from; i < matrix.length; i++) {
            if (maxElement < Math.abs(matrix[row][i])) {
                maxElement = Math.abs(matrix[row][i]);
                maxElementPosition = i;
            }
        }
        return maxElementPosition;
    }

    private static void transposition(double[][] matrix, int positionFrom, int positionTo, int[] transposition) {
        double buffer = 0;
        int bufferTrans = 0;
        for (int i = positionFrom; i < matrix.length; i++) {
            buffer = matrix[i][positionFrom];
            matrix[i][positionFrom] = matrix[i][positionTo];
            matrix[i][positionTo] = buffer;
        }

        bufferTrans = transposition[positionFrom];
        transposition[positionFrom] = transposition[positionTo];
        transposition[positionTo] = bufferTrans;
    }

}
