package eu.rupsys;

import eu.rupsys.processing.points.PointProcessing;

import java.math.BigDecimal;
import java.util.List;

public class App {

    public static void main(final String[] args) {
        // Checking user input
        checkEnoughArgumentsProvided(args, 3);
        final int n = Integer.valueOf(args[0]);
        final BigDecimal sigma = BigDecimal.valueOf(Double.valueOf(args[1]));
        final BigDecimal gamma = BigDecimal.valueOf(Double.valueOf(args[2]));

        // Executing point processing
        final PointProcessing pointProcessing = PointProcessing.generate(n, sigma, gamma);
        final List<BigDecimal> tValues = pointProcessing.getResult();

        // Giving output to the user
        System.out.println(tValues);
    }

    private static void checkEnoughArgumentsProvided(final String[] args, int neededNumber) {
        if (args == null || args.length < neededNumber) {
            System.out.println("Usage: java App [n] [sigma] [gamma]");
            System.exit(1);
        }
    }
}
