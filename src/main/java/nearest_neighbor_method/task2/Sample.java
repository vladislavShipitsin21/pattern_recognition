package nearest_neighbor_method.task2;

public class Sample implements Comparable<Sample> {
    private final double x;
    private final double y;

    public Sample(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("( %f.3 , %f.3 )", x, y);
    }

    @Override
    public int compareTo(Sample o) {
        return Double.compare(x, o.x);
    }
}
