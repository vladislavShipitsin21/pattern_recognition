import org.junit.Test;
import org.junit.jupiter.api.DynamicTest;

import java.util.Random;

public class TestRandom {
    @Test
    public void test(){
        Random random = new Random();

        for (int i = 0; i < 10 ; i++) {
            System.out.println(random.nextInt(1));
        }

    }
}
