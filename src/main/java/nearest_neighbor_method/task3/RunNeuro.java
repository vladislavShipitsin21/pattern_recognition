package nearest_neighbor_method.task3;

import gui.Graphics;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import nearest_neighbor_method.task2.Generator;
import nearest_neighbor_method.task2.Sample;

import java.util.List;
import java.util.function.DoubleFunction;

public class RunNeuro extends Application {
    /**
     * Функция активации := h
     * Примеры:
     * 1) Логистический сигмоид
     * 2) Гиперболический тангенс
     */
    static DoubleFunction activationFunction = Math::tanh;

    // todo Можно использовать велечину производной (как было в степике) ?
    /**
     * Коэффициент обучения.
     */
    private final double learningRate = 0.005;

    @Override
    public void start(Stage stage) throws Exception {

        final DoubleFunction<Double> function = Math::sin;
        final double maxEpsError = 0.2;

        // todo выводить этапы обучения

        final List<Sample> samples = Generator.getSamples(2*Math.PI, 100, function, maxEpsError);

        final DoubleFunction<Double> neuro = new Neuro(samples, activationFunction, 3,3);

        print(stage, samples, function, neuro);
    }

    public static void print(Stage stage, final List<Sample> samples, DoubleFunction<Double> function, DoubleFunction<Double> neuro) {
        Graphics graphics = new Graphics(50, 400, 2 * Math.PI, 1);
        graphics.addSamples(samples);
        graphics.addFunction(Color.BLACK, function);
        graphics.addFunction(Color.RED, neuro);

        // вывести
        stage.setScene(graphics.getScene());

        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
