package eu.rupsys.processing.point;

import java.math.BigDecimal;

public class PointProcessingParameters {

    private final BigDecimal n;
    private final BigDecimal sigma;
    private final BigDecimal gamma;

    public PointProcessingParameters(
            final BigDecimal n,
            final BigDecimal sigma,
            final BigDecimal gamma) {

        this.n = n;
        this.sigma = sigma;
        this.gamma = gamma;
    }

    
}
