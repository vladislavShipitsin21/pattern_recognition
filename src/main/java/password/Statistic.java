package password;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Statistic {

    public static void main(String[] args) {
        calculate();
    }

    static List<Experiment> data = new ArrayList<>();

    public static void calculate() {

        final int min = 4;
        final int max = 10;

        // регистр + цифр
        int maxSizeA = 26 + 26 + 10;

        for (int sizeAlfavit = 10; sizeAlfavit <= maxSizeA; sizeAlfavit++) {

        Experiment experiment = new Experiment(min, max, sizeAlfavit);

        data.add(experiment);

        }

        prentData();
    }

    public static void prentData() {
        for(Experiment experiment : data){
            System.out.println(experiment);
        }
    }


    private static class Experiment {
        final int minBond;
        final int maxBond;
        final int sizeAlfabet;
        BigInteger value;


        public Experiment(int minBond, int maxBond, int sizeAlfabet) {
            this.minBond = minBond;
            this.maxBond = maxBond;
            this.sizeAlfabet = sizeAlfabet;

            value = getCount();
        }

        private BigInteger getCount() {
            BigInteger sum = BigInteger.ZERO;

            for (int i = minBond; i <= maxBond; i++) {
                sum = sum.add(new BigInteger(sizeAlfabet + "").pow(i));
            }
            return sum;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("min = ").append(minBond);
            sb.append(", max = ").append(maxBond);
            sb.append(", sizeAlfabet = ").append(sizeAlfabet);
            sb.append(", value =").append(value);
            sb.append('}');
            return sb.toString();
        }
    }
}
