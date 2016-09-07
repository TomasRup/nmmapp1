package eu.rupsys.processing.ui;

import java.math.BigDecimal;
import java.util.List;

public class TerminalUI {

    public static void printListOfBigDecimals(final List<BigDecimal> list) {
        System.out.println("---- OUTPUT START ----");
        System.out.println("[");

        for (final BigDecimal item : list) {
            System.out.println(item + ", ");
        }

        System.out.print("]\n");
        System.out.println("---- OUTPUT END ----");
    }

    public static void checkEnoughArgumentsProvided(final String[] args, int neededNumber) {
        if (args == null || args.length < neededNumber) {
            System.out.println("Usage: java App [n] [sigma] [gamma]");
            System.exit(1);
        }
    }

    public static int getN(final String[] args) {
        return Integer.valueOf(args[0]);
    }

    public static BigDecimal getSigma(final String[] args) {
        return BigDecimal.valueOf(Double.valueOf(args[1]));
    }

    public static BigDecimal getGamma(final String[] args) {
        return BigDecimal.valueOf(Double.valueOf(args[2]));
    }
}
