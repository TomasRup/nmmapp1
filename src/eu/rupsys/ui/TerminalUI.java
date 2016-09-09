package eu.rupsys.ui;

import eu.rupsys.plot.function.PlotPoint;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TerminalUI {

    public static void printLine(final String line) {
        System.out.println(line);
    }

    public static void printSageReadyResult(final List<PlotPoint> sinValues, final List<PlotPoint> cosValues) {
        System.out.print("sinValues = ");
        printPlotPoints(sinValues);
        System.out.println();
        System.out.print("cosValues = ");
        printPlotPoints(cosValues);
        System.out.println();
        System.out.println("list_plot(cosValues, scale='loglog', legend_label='Scos(f)')");
        System.out.println("list_plot(sinValues, scale='loglog', legend_label='Ssin(f)')");
    }

    private static void printPlotPoints(final List<PlotPoint> list) {
        final NumberFormat formatter = new DecimalFormat("#0.00000000");

        System.out.print("[\n");

        for (final PlotPoint item : list) {
            System.out.print("(" + formatter.format(item.getX()) + ", " + formatter.format(item.getY()) + ")");

            if (list.lastIndexOf(item) != list.size() - 1) {
                System.out.print(",");
            }

            System.out.println();
        }
        System.out.print("]");
        System.out.println();
    }

    public static boolean enoughArgumentsProvided(final String[] args, int neededNumber) {
        return args != null && args.length >= neededNumber;
    }
}
