package password;

import java.util.Random;

public class Generator {

    private final Alphabet alphabet;
    private final int size;

    private final Random random;
    private final long seed = 21;

    public Generator(Alphabet alphabet, int size) {
        this.alphabet = alphabet;
        this.size = size;

        random = new Random(seed);
    }

    public Password getWord() {
        Symbol[] result = new Symbol[size];

        for (int i = 0; i < result.length; i++) {
            final int index = random.nextInt(size);
            final Symbol s = alphabet.getSymbol(index);
            result[i] = s;
        }
        return new Password(result);
    }
}
