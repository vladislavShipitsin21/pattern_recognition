package nearest_neighbor_method.task1.model;

import java.util.*;

public class Experiment {
    private final double[][] resultMatrix;
    private final List<Sample> samples;
    private final int countIterations;
    private final int persentVoise;
    private final int radius;

    private final double share;


    public double[][] getResultMatrix() {
        return resultMatrix;
    }

    // todo посылать норму как интерфейс !!!
    public Experiment(List<Sample> samples, int countIterations, int persentVoise,int radius) {
        this.samples = samples;
        this.radius = radius;

        this.countIterations = countIterations;
        share = 1. / countIterations * 100;

        this.persentVoise = persentVoise;

        int sizeResult = samples.size();
        resultMatrix = new double[sizeResult][sizeResult];

        this.samples.sort(Sample::compareTo);
    }

    public  double[][] collection(){
        for (int i = 0; i < countIterations; i++) {
            for (Sample sample : samples){

                // накладываем шум
                Sample voiseSample = WhiteNoise.apply(sample,persentVoise,radius);

                // определяем результат
                Sample resultSample = samples.
                        stream().
                        min(Comparator.comparingDouble(s -> Norma.value(s, voiseSample))).
                        get();

                resultMatrix[sample.getValue()][resultSample.getValue()] += share;
            }
        }
        return resultMatrix;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("count iterations = " + countIterations + "\n");
        sb.append("percent = " + persentVoise + "\n");

        addCap(sb);

        final String ANSI_RED = "\u001B[31m";
        final String ANSI_RESET = "\u001B[0m";

        for (int i = 0; i < samples.size(); i++) {
            sb.append(" " + samples.get(i).getName() + " |");
            for (int j = 0; j < samples.size(); j++) {
                String tempColor = ANSI_RESET;
                if(i == j){
                    tempColor = ANSI_RED;
                }
                sb.append(String.format(tempColor + "%5.1f" + ANSI_RESET,resultMatrix[i][j]) + "|");
            }
            addLine(sb);
        }
        return sb.toString();
    }
    private void addCap(StringBuffer sb){
        sb.append(" № |");
        for (Sample sample : samples){
            sb.append(String.format("%5s", sample.getName())).append("|");
        }
        addLine(sb);
    }
    private void addLine(StringBuffer sb){
        sb.append("\n");
        for (int i = 0; i < 64; i++) {
            sb.append('-');
        }
        sb.append("\n");
    }
}
