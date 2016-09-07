package eu.rupsys.processing.points;

import eu.rupsys.processing.Processing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointProcessing implements Processing {

    private final static BigDecimal INITIAL_T = BigDecimal.valueOf(0);
    private final static BigDecimal INITIAL_TAU = BigDecimal.valueOf(10);

    private final int n;
    private final BigDecimal sigma;
    private final BigDecimal gamma;

    private PointProcessing(
            final int n,
            final BigDecimal sigma,
            final BigDecimal gamma) {

        this.n = n;
        this.sigma = sigma;
        this.gamma = gamma;
    }

    public static PointProcessing generate(
            final int n,
            final BigDecimal sigma,
            final BigDecimal gamma) {

        if (sigma == null || gamma == null) {
            throw new NullPointerException();
        }

        return new PointProcessing(n, sigma, gamma);
    }

    @Override
    public List<BigDecimal> getResult() {
        final List<BigDecimal> tValues = new ArrayList<>(this.n);
        tValues.add(INITIAL_T);

        final List<BigDecimal> tauValues = new ArrayList<>(this.n);
        tauValues.add(INITIAL_TAU);

        for (int k = 1 ; k < this.n ; k++) {

            // τk = τk−1 − γ(τk−1 − 1) + σεk
            final BigDecimal tauK = tauValues.get(k - 1)
                    .subtract(this.gamma
                            .multiply(tauValues.get(k - 1)
                                    .subtract(BigDecimal.ONE)))
                    .add(this.sigma
                            .multiply(randomEpsilon()));

            // tk = tk−1 + τk
            final BigDecimal tK = tValues.get(k - 1).add(tauK);

            tauValues.add(tauK);
            tValues.add(tK);
        }

        return tValues;
    }

    private BigDecimal randomEpsilon() {
        return new BigDecimal(new Random().nextGaussian());
    }
}
