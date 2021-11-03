package nearest_neighbor_method.task1.model;

import java.io.FileNotFoundException;
import java.util.List;

public class Start {
    public static void main(String[] args) throws FileNotFoundException {
        final int center = 128;
        final int delta = 10;

        final int background = center - delta / 2;
        final int foreground = center + delta / 2;

        final int radius = 10;

        final int countIter = 1_000;
        final int percent = 50;

        List<Sample> samples = GenerateSample.getAll(background,foreground);
        samples.stream().sorted().forEach(System.out::println);


        Experiment experiment = new Experiment(samples,countIter,percent,radius);
        experiment.collection();

        double[][] resultMatrix = experiment.getResultMatrix();

        System.out.println(experiment);
        System.out.println("norma result matrix = " + Norma.normaResultMatrix(resultMatrix));
        System.out.println("общий процент не совпадения = " + Norma.summaryError(resultMatrix,samples.size()) + "%");
    }
}
