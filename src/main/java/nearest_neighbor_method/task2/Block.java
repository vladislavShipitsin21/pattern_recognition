package nearest_neighbor_method.task2;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private final List<Sample> samples;

    public Block() {
        this.samples = new ArrayList<>();
    }

    public void addSample(Sample sample){
        samples.add(sample);
    }

    public List<Sample> getSamples() {
        return samples;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[ ");
        for(Sample sample : samples){
            sb.append(sample);
        }
        sb.append("];");
        return sb.toString();
    }
}
