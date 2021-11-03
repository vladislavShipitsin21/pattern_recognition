package nearest_neighbor_method.task1.model;

import java.util.Random;

public class WhiteNoise {
    private static Random random = new Random();
    /**
     * наклыдвает помехи на изображение
     * @param sample изображение
     * @param percent процент помех
     * @return искаженное изображение
     */
    public static Sample apply(final Sample sample, int percent, int radius) {

        Sample resultSample = new Sample(sample);

        int size = sample.getSize();

        final boolean[][] alreadyChanged = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                alreadyChanged[i][j] = false;
            }
        }

        int countElement = percent * size * size / 100;
        int counter = 0;
        while (counter < countElement){
            changeElement(resultSample,alreadyChanged,radius);
            counter++;
        }
//        Printer.printSample(resultSample);
        return resultSample;
    }

    private static void changeElement(Sample sample, boolean[][] alreadyChanged, int radius){
        int size = sample.getSize();

        int coordX = random.nextInt(size);
        int coordY = random.nextInt(size);

        while (alreadyChanged[coordX][coordY]){
            coordX = random.nextInt(size);
            coordY = random.nextInt(size);
        }
        alreadyChanged[coordX][coordY] = true;

        int noise = random.nextInt(radius * 2 + 1);
        while (noise == radius){
            noise = random.nextInt(radius * 2 + 1);
        }


        int oldValue = sample.getElement(coordX,coordY);

        int newValue = oldValue + noise - radius;

        sample.setElement(coordX,coordY,newValue);
    }
}
