package nearest_neighbor_method.task2;

public class Printer {

    // todo послать логгер
    public static void printTableError(double[][] tableError, int sizeBlocks, int... degrees) {
        System.out.println("Table error: ");

        System.out.print("   ");
        for (int i = 0; i < sizeBlocks; i++) {
            System.out.printf("  b%d   " ,(i+1));
        }
        System.out.println();
        System.out.println("---------------------------------------------------------------------------------------");
        for (int i = 0; i < tableError.length; i++) {
            System.out.print(degrees[i] + "| ");
            for (int j = 0; j < tableError[i].length; j++) {
                System.out.printf("%.3f  ",tableError[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
