package eu.rupsys.ui;

import java.util.List;

public class TerminalUI {

    public static void printLine(final String line) {
        System.out.println(line);
    }

    public static void printPointsListWithIdenticalXY(final List<Double> list) {
        System.out.print("[");

        for (final double item : list) {
            System.out.print("(" + item + ", " + item + ")");

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
