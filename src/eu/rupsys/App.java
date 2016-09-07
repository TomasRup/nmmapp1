package eu.rupsys;

import eu.rupsys.processing.points.PointProcessing;
import eu.rupsys.processing.ui.TerminalUI;

public class App {

    public static void main(final String[] args) {
        TerminalUI.checkEnoughArgumentsProvided(args, 3);

        final PointProcessing pointProcessing = PointProcessing.generate(
                TerminalUI.getN(args),
                TerminalUI.getSigma(args),
                TerminalUI.getGamma(args));

        TerminalUI.printListOfBigDecimals(pointProcessing.getResult());
    }
}
