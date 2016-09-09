package eu.rupsys.plot;

import eu.rupsys.plot.function.PlotFunction;
import eu.rupsys.plot.function.PlotPoint;

import java.util.ArrayList;
import java.util.List;

public class PlotManager {

    private final static double LOWER_BOUND_INDEX = -6;
    private final static double UPPER_BOUND_INDEX = 0;
    private final static double RESOLUTION = 100;
    private final static double H = Math.abs((LOWER_BOUND_INDEX - UPPER_BOUND_INDEX) / RESOLUTION);

    private final List<Double> tValues;
    private final int n;

    public PlotManager(final List<Double> tValues, final int n) {
        this.tValues = tValues;
        this.n = n;
    }

    // Scos(f) = (2 / tN − t0) * ( sum from k=0 to N of (cos(2πf * tk)) )^2
    public List<PlotPoint> executeSinFunction() {
        return this.executeGenericFunction(FunctionType.COS);
    }

    // Ssin(f) = (2 / tN − t0) * ( sum from k=0 to N of (sin(2πf * tk)) )^2
    public List<PlotPoint> executeCosFunction() {
        return this.executeGenericFunction(FunctionType.SIN);
    }

    // Since both functions differ only by usage of cos or sin in the second clause
    // we move out it's execution to a separate one
    private List<PlotPoint> executeGenericFunction(final FunctionType functionType) {
        return new PlotFunction() {

            @Override
            public List<PlotPoint> apply(final List<Double> tValues, final int n) {
                final List<PlotPoint> sValues = new ArrayList<>(n + 1);

                // (2 / tN − t0) is constant
                final double firstClausesResult = (2 / (tValues.get(n) - tValues.get(0)));

                // minimum possible f value is a constant
                final double fMin = Math.pow(10, LOWER_BOUND_INDEX);

                // Iterating through resolution indexes
                for (int resolutionIndex = 0 ; resolutionIndex <= RESOLUTION ; resolutionIndex++) {

                    // Accumulating f by discreting formula
                    final double f = fMin * Math.pow(10, resolutionIndex * H);

                    double secondClausesResult = 0;
                    for (int k = 0 ; k <= n ; k++) {
                        final double secondClausesInnerClauseSum = 2 * Math.PI * f * tValues.get(k);

                        if (functionType == FunctionType.COS) {
                            secondClausesResult += Math.cos(secondClausesInnerClauseSum);
                        } else if (functionType == FunctionType.SIN) {
                            secondClausesResult += Math.sin(secondClausesInnerClauseSum);
                        }
                    }

                    final double formulaResult = firstClausesResult * Math.pow(secondClausesResult, 2);
                    sValues.add(new PlotPoint(f, formulaResult));
                }

                return sValues;

            }

        }.apply(this.tValues, this.n);
    }

    private enum FunctionType {
        SIN, COS
    }
}
