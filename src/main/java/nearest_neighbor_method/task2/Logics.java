package nearest_neighbor_method.task2;

import java.util.List;

import static nearest_neighbor_method.task2.Validator.isCorrectBlocks;

public class Logics {

    private final List<Sample> samples;

    public Logics(final List<Sample> samples) {
        this.samples = samples;
    }

    public Polynomial calculate() throws Exception {
        // разбиваем на блоки
        final double epsBlock = 0.01;
        List<Block> blocks = Blocking.getBlocksFromSamples(samples, epsBlock);
        isCorrectBlocks(blocks, samples.size(), epsBlock);

        int[] degree = new int[100];
        for (int i = 0; i < degree.length; i++) {
            degree[i] = i + 1;
        }

        // проводим кросс-валидацию

        return CrossValidation.calculate(blocks, degree);
    }
}
