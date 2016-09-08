package eu.rupsys.processing.points;

import eu.rupsys.processing.Processing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointProcessing implements Processing {

    private final static double INITIAL_T = 0;
    private final static double INITIAL_TAU = 10;

    private final int n;
    private final double sigma;
    private final double gamma;

    public PointProcessing(
            final int n,
            final double sigma,
            final double gamma) {

        this.n = n;
        this.sigma = sigma;
        this.gamma = gamma;
    }

    @Override
    public List<Double> generate() {
        final List<Double> tValues = new ArrayList<>(this.n + 1);
        tValues.add(INITIAL_T);

        final List<Double> tauValues = new ArrayList<>(this.n + 1);
        tauValues.add(INITIAL_TAU);

        for (int k = 1 ; k <= this.n ; k++) {

            // τk = τk−1 − γ(τk−1 − 1) + σεk
            final double tauK = tauValues.get(k - 1)
                    - (this.gamma * (tauValues.get(k - 1) - 1)
                    + (this.sigma * randomEpsilon()));

            // tk = tk−1 + τk
            final double tK = tValues.get(k - 1) + tauK;

            tauValues.add(tauK);
            tValues.add(tK);
        }

        return tValues;
    }

    private double randomEpsilon() {
        return new Random().nextGaussian();
    }
}
