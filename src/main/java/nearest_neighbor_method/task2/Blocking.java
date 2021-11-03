package nearest_neighbor_method.task2;

import java.util.ArrayList;
import java.util.List;

public class Blocking {

    private Blocking() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Функция возвращает блоки полученные из выборки.
     * 2 элемента попадают в 1 блок, если разница между ними не больше eps
     * Значения в блоках не пересекаются.
     *
     * @param samples выборка
     * @param eps     величина малого порядка
     * @return Список блоков.
     */
    public static List<Block> getBlocksFromSamples(final List<Sample> samples, final double eps) {

        final List<Block> blocks = new ArrayList<>();

        Block currentBlock = new Block();
        for (int i = 0; i < samples.size() - 1; i++) {

            currentBlock.addSample(samples.get(i));

            // если соседние отличаются больше чем на eps
            if (Double.compare(samples.get(i + 1).getX() - samples.get(i).getX(), eps) > 0) {
                // создаем новый блок
                blocks.add(currentBlock);
                currentBlock = new Block();
            }
        }
        currentBlock.addSample(samples.get(samples.size() - 1));
        blocks.add(currentBlock);
        return blocks;
    }
}
