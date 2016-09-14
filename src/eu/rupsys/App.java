package eu.rupsys;

import eu.rupsys.helpers.FileHelper;
import eu.rupsys.plot.PlotManager;
import eu.rupsys.plot.function.PlotPoint;
import eu.rupsys.processing.points.PointProcessing;

import java.util.List;

public class App {

    public static void main(final String[] args) {
        // Validating user parameters
        if (!enoughArgumentsProvided(args, 5)) {
            System.out.println(
                    "Usage: java App [mode] [n] [sigma] [gamma] [t_file_location] [SIN/COS] [output_file_location]");
            System.exit(1);
        }

        // Parsing user parameters
        final String mode = args[0];
        final int n = Integer.valueOf(args[1]);
        final double sigma = Double.valueOf(args[2]);
        final double gamma = Double.valueOf(args[3]);
        final String tFileLocation = args[4];

        if ("point-processing".equalsIgnoreCase(mode)) {
            runPointProcessingMode(n, sigma, gamma, tFileLocation);

        } else if ("plot-creation".equalsIgnoreCase(mode)) {
            final String rawFunctionType = args[5];
            final String outputFileLocation = args[6];

            final PlotManager.FunctionType functionType = PlotManager.FunctionType.valueOf(rawFunctionType);
            runPlotCreationMode(n, functionType, tFileLocation, outputFileLocation);
        }

    }

    private static void runPlotCreationMode(
            final int n,
            final PlotManager.FunctionType functionType,
            final String tFileLocation,
            final String outputFileLocation) {

        // Reading t values from a file
        final List<Double> tValues = FileHelper.get().readValuesFromFile(tFileLocation);

        // Generating plot points for both functions
        final PlotManager plotManager = new PlotManager(tValues, n);
        final List<PlotPoint> functionValues = plotManager.executeFunction(functionType);

        // Outputting final values
        FileHelper.get().writePlotPointValuesToAFile(outputFileLocation, functionValues);
    }

    private static void runPointProcessingMode(
            final int n,
            final double sigma,
            final double gamma,
            final String tFileLocation) {

        // Generate point processing
        final PointProcessing pointProcessing = new PointProcessing(n, sigma, gamma);
        final List<Double> tValues = pointProcessing.generate();

        // Writing t values to a file
        FileHelper.get().writeDoubleValuesToAFile(tFileLocation, tValues);
    }

    private static boolean enoughArgumentsProvided(final String[] args, int neededNumber) {
        return args != null && args.length >= neededNumber;
    }
}
