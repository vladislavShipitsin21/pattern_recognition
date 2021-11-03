package checkTables;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomLi {
    private static final Map<Integer, Integer> map = new HashMap<>();
    private static final Random random = new Random();
    private static final int size = 3;
    private static final int iter = 1_000_000;

    public static void main(String[] args) {
        fill();
        print();
    }

    private static void fill() {
        for (int i = 0; i < iter; i++) {
            final int number = random.nextInt(size);
            map.putIfAbsent(number, 1);
            map.computeIfPresent(number, (n, c) -> c += 1);
        }
    }

    private static void print() {
        int max = map.values().stream().max(Integer::compareTo).get();
        int min = map.values().stream().min(Integer::compareTo).get();
        for (final Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("dif = " + (max - min));
    }

}
