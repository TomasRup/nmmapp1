package eu.rupsys.plot.function;

import java.util.List;

public abstract class PlotFunction {

    public abstract List<Double> apply(List<Double> tValues, int n);

}
