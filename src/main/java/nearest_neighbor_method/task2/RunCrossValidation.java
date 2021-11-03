package nearest_neighbor_method.task2;

import gui.Graphics;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.function.DoubleFunction;

public class RunCrossValidation extends Application {

    // todo изображение выборки кружочками )
    @Override
    public void start(Stage stage) throws Exception {

        final DoubleFunction<Double> startF = x -> Math.sin(x * x);
//        final DoubleFunction<Double> startF = x -> -x * x + x + 1;

        final int sizeSamples = 100;

        final double boundX = 2 * Math.PI;

        final double maxEps = 0.1;

        List<Sample> samples = Generator.getSamples(boundX, sizeSamples, startF, maxEps);

        // выполнить

        final Logics logics = new Logics(samples);

        Polynomial resultPolynomial = logics.calculate();

        System.out.println(resultPolynomial);

        // добавить в консоль
//        Graphics graphics = new Graphics(1000,800,500,400,100,100 );
        Graphics graphics = new Graphics(400, 400, boundX, 5);
        graphics.addSamples(samples);
        graphics.addFunction(Color.BLACK, startF);
        graphics.addFunction(Color.RED, resultPolynomial.getFunctions());
// вывести
        stage.setScene(graphics.getScene());

        stage.show();

        System.out.println(samples);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
