package eu.rupsys.ui;

import java.util.List;

public class TerminalUI {

    public static void printLine(final String line) {
        System.out.println(line);
    }

    public static void printList(final List<Double> list) {
        for (final double item : list) {
            System.out.println(item + ", ");
        }
    }

    public static boolean enoughArgumentsProvided(final String[] args, int neededNumber) {
        return args != null && args.length >= neededNumber;
    }
}
