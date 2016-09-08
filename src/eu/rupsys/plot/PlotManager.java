package eu.rupsys.plot;

import eu.rupsys.plot.function.PlotFunction;

import java.util.ArrayList;
import java.util.List;

public class PlotManager {

    private final static int LOWER_BOUND_INDEX = -6;
    private final static int UPPER_BOUND_INDEX = 0;

    private final List<Double> tValues;
    private final int n;

    public PlotManager(final List<Double> tValues, final int n) {
        this.tValues = tValues;
        this.n = n;
    }

    // Scos(f) = (2 / tN − t0) * ( sum from k=0 to N of (cos(2πf * tk)) )^2
    public List<Double> executeSinFunction() {
        return this.executeGenericFunction(FunctionType.COS);
    }

    // Ssin(f) = (2 / tN − t0) * ( sum from k=0 to N of (sin(2πf * tk)) )^2
    public List<Double> executeCosFunction() {
        return this.executeGenericFunction(FunctionType.SIN);
    }

    // Since both functions differ only by usage of cos or sin in the second clause
    // we move out it's execution to a separate one
    private List<Double> executeGenericFunction(final FunctionType functionType) {
        return new PlotFunction() {

            @Override
            public List<Double> apply(final List<Double> tValues, final int n) {
                final List<Double> sCosValues = new ArrayList<>(n + 1);

                // (2 / tN − t0) is constant
                final double firstClausesResult = (2 / (tValues.get(n) - tValues.get(0)));

                // Iterating 10^-6 to 10^0
                for (int fPower = LOWER_BOUND_INDEX ; fPower <= UPPER_BOUND_INDEX ; fPower++) {
                    double f = Math.pow(10, fPower);

                    double secondClausesResult = 0;
                    for (int k = 0 ; k <= n ; k++) {
                        final double secondClausesInnerClauseSum = 2 * Math.PI * f * tValues.get(k);

                        if (functionType == FunctionType.COS) {
                            secondClausesResult += Math.cos(secondClausesInnerClauseSum);
                        } else if (functionType == FunctionType.SIN) {
                            secondClausesResult += Math.sin(secondClausesInnerClauseSum);
                        }
                    }

                    sCosValues.add(firstClausesResult * secondClausesResult);
                }

                return sCosValues;
            }

        }.apply(this.tValues, this.n);
    }

    private enum FunctionType {
        SIN, COS
    }
}
