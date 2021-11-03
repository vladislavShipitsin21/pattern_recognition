package gui;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import nearest_neighbor_method.task2.Generator;
import nearest_neighbor_method.task2.Sample;

import java.util.List;
import java.util.function.DoubleFunction;

public class Graphics {

    private final int width;
    private final int height;
    private final int sx; // новое начало координат в комп. координатах Ox
    private final int sy; //  новое начало координат в комп. координатах Oy

    private final double bx; // правая граница ограничивающая Ox
    private final double by; // правая граница ограничивающая Oy

    private final double w; // отступ от правого края окна
    private final double h; // отступ от верхней границы окна

    private final double cofX;
    private final double cofY;

    private final Group group;

    public Graphics(final int width, final int height, final double w, final double h, final int sx, final int sy, final double bx, final double by) {
        this.width = width;
        this.height = height;
        this.sx = sx;
        this.sy = sy;

        this.bx = bx;
        this.by = by;

        this.w = w;
        this.h = h;

        cofX = (width - w - sx) / bx;
        cofY = (h - sy) / by;

        group = new Group();
        addAxes();
    }

    public Graphics() {
        this(1000, 800, 50, 50, 50, 400, 2 * Math.PI, 1);
    }

    public Graphics(final double boundX, final double boundY) {
        this(1000, 800, 50, 50, 50, 400, boundX, boundY);
    }

    public Graphics(final int sx, final int sy, final double boundX, final double boundY) {
        this(1000, 800, 50, 50, sx, sy, boundX, boundY);
    }

    public Scene getScene() {
        return new Scene(group, width, height);
    }

    public void addFunction(Color color, double[] arrX, final DoubleFunction<Double> f) {
        for (int i = 0; i < arrX.length - 1; i++) {
            Line line = new Line(
                    getScreenX(arrX[i]),
                    getScreenY(f.apply(arrX[i])),
                    getScreenX(arrX[i + 1]),
                    getScreenY(f.apply(arrX[i + 1]))
            );
            line.setStroke(color);
            // сглаживание линии
            line.setSmooth(true);

            group.getChildren().add(line);
        }
    }

    public void addFunction(Color color, final DoubleFunction<Double> f) {
        addFunction(color, Generator.getIniformX(-bx, bx, 1000), f);
    }

    public void addSamples(final List<Sample> samples) {
        final double r =   Double.min(width,height) / 100;
        for (final Sample sample : samples) {
            Circle circle = new Circle(getScreenX(sample.getX()),getScreenY(sample.getY()),r);
            circle.setStroke(Color.BLUE);
            circle.setFill(Color.TRANSPARENT);
            group.getChildren().add(circle);
        }
    }

    private void addAxes() {
        group.getChildren().addAll(
                new Line(0, sy, width, sy), // Ox
                new Line(sx, 0, sx, height) // Oy
        );
        group.getChildren().addAll(
                new Line(getScreenX(bx), (sy - 10), getScreenX(bx), (sy + 10)),
                new Line((sx - 10), getScreenY(by), (sx + 10), getScreenY(by))
        );
    }

    private double getScreenX(final double wordX) {
        return cofX * wordX + sx;
    }

    private double getScreenY(final double wordY) {
        return cofY * wordY + sy;
    }
}
