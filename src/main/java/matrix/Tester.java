package matrix;

public class Tester {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            test(1);
        }
    }

    public static void test(int variant){
        // точное решение задаем сами

        if(variant == 1){
            System.out.println("GAUSE:");
            System.out.println();
        }else {
            System.out.println("REFLECT:");
            System.out.println();
        }
        int size = 10;
        double[] exactSolutions = new double[size];

        for (int i = 0; i < exactSolutions.length ; i++) {
            exactSolutions[i] = Math.sin(i);
        }
        double[][] a = new double[size][size];
        Operations.fillRandom(a,100);

        double[][] copyA = Operations.copyMatrix(a);

        double[] f = Operations.aMultiX(a,exactSolutions);
        Operations.printMatrixAndF(a,f,"Исходная система: ");

        double[] copyF = Operations.copyArr(f);

        double[] approximateSolutions = new double[size];

        if(variant == 1){
            approximateSolutions = Realise.doGouse(a,f);
        }else{
            approximateSolutions = Realise.reflectionsLines(a,f);
        }

        Operations.printArr(exactSolutions,"Точное решение: ");
        Operations.printMatrixAndF(a,f,"СЛАУ: ");
        Operations.printArr(approximateSolutions,"Приблеженное решение: ");

        double[] error = new double[size];
        for (int i = 0; i < error.length ; i++) {
            error[i] = approximateSolutions[i] - exactSolutions[i];
        }

        double normError = Operations.maxArrAbs(error);
        System.out.println("Норма ошибки: " + normError);
        System.out.println();

        double[] nevazka = Operations.nevazka(copyA,approximateSolutions,copyF);
        Operations.printArr(nevazka,"Невязка: ");

        double normNevazka = Operations.maxArrAbs(nevazka);
        System.out.println("Норма невязки: " + normNevazka);
        System.out.println();

        double normF = Operations.maxArrAbs(copyF);
        double rrr = normNevazka / normF; // relative residual rate
        System.out.println("Относительная норма невязки: " + rrr);
        System.out.println();

        double normExactSolutions = Operations.maxArrAbs(exactSolutions);
        double rer = normNevazka / normExactSolutions; // relative error rate

        System.out.println("Относительная норма ошибки: " + rer);
        System.out.println();

    }

    public static double[][] generateMatrix(int size){
        double[][] matrix = new double[size][size];
        Operations.fillRandom(matrix,100);
        return matrix;
    }

    public static double[] generateSolutions(int size){
        double[] solutions = new double[size];
        for (int i = 0; i < solutions.length ; i++) {
            solutions[i] = Math.sin(i);
        }
        return solutions;
    }

}
