package eu.rupsys;

import eu.rupsys.plot.PlotManager;
import eu.rupsys.plot.function.PlotPoint;
import eu.rupsys.processing.points.PointProcessing;
import eu.rupsys.ui.TerminalUI;

import java.util.List;

public class App {

    public static void main(final String[] args) {
        // Validating user parameters
        if (!TerminalUI.enoughArgumentsProvided(args, 3)) {
            TerminalUI.printLine("Usage: java App [n] [sigma] [gamma]");
            System.exit(1);
        }

        // Parsing user parameters
        final int n = Integer.valueOf(args[0]);
        final double sigma = Double.valueOf(args[1]);
        final double gamma = Double.valueOf(args[2]);

        // Generate point processing
        final PointProcessing pointProcessing = new PointProcessing(n, sigma, gamma);
        final List<Double> tValues = pointProcessing.generate();

        // Generating plot points for both functions
        final PlotManager plotManager = new PlotManager(tValues, n);
        final List<PlotPoint> sinFunctionValues = plotManager.executeCosFunction();
        final List<PlotPoint> cosFunctionValues = plotManager.executeSinFunction();

        // Printing values to terminal
        TerminalUI.printLine("### SIN VALUES");
        TerminalUI.printPlotPoints(sinFunctionValues);

        TerminalUI.printLine("### COS VALUES");
        TerminalUI.printPlotPoints(cosFunctionValues);
    }
}
