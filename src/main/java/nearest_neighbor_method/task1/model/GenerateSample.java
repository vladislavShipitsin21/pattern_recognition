package nearest_neighbor_method.task1.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class GenerateSample {

    public static List<Sample> getAll() throws FileNotFoundException {
        // todo сделать более гибко
        final int size = 9;

        List<Sample> result = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            result.add(new Sample(i));
        }
        return result;
    }

    public static List<Sample> getAll(final int background, final int foreground) throws FileNotFoundException {
        // todo сделать более гибко
        final int size = 9;

        List<Sample> result = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            result.add(new Sample(i,background,foreground));
        }
        return result;
    }
}
