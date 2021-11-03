package password;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {

    private final List<Symbol> symbols;

    public Alphabet() {
        symbols = new ArrayList<>();
        symbols.add(Symbol.A);
        symbols.add(Symbol.B);
        symbols.add(Symbol.C);
        symbols.add(Symbol.D);
    }

    public Symbol getSymbol(int index) {
        return symbols.get(index);
    }
}
