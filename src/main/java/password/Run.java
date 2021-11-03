package password;

import java.util.Arrays;
import java.util.Random;

public class Run {

    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();
        final int iterations = 1_000;

        for (int i = 0; i < iterations; i++) {

            int password = new Random().nextInt(Integer.MAX_VALUE);

            int word = 0;

            while (word != password){
                word++;
            }
//            System.out.println("password = " + password);
//            System.out.println("word = " + word);
            System.out.println(i);
        }
        final long finishTime = System.currentTimeMillis();


        System.out.println("time = " + (finishTime - startTime));
        final double avgTime = (double) (finishTime - startTime) / iterations;
        System.out.println("avg time = " + avgTime);
    }
}
