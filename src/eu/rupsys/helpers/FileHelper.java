package eu.rupsys.helpers;

import eu.rupsys.plot.function.PlotPoint;

import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FileHelper {

    private static FileHelper instance;
    private final static NumberFormat FORMATTER = new DecimalFormat("#0.00000000");


    public static FileHelper get() {
        return instance == null ? instance = new FileHelper() : instance;
    }

    public List<Double> readValuesFromFile(final String location) {
        try {
            final List<Double> values = new ArrayList<>();

            for (final String line : Files.readAllLines(Paths.get(location), Charset.defaultCharset())) {
                values.add(Double.valueOf(line));
            }

            return values;

        } catch (Exception e) {
            System.out.println("Error reading values" + e);
            System.exit(3);
        }

        return new ArrayList<>();
    }

    public void writePlotPointValuesToAFile(final String location, final List<PlotPoint> values) {
        try {
            final PrintWriter writer = new PrintWriter(location, "UTF-8");

            for (int i = 0 ; i < values.size() ; i++) {
                final PlotPoint point = values.get(i);
                final String stringToPrint = "("
                        + FORMATTER.format(point.getX())
                        + ", "
                        + FORMATTER.format(point.getY())
                        + ")"
                        + (i != values.size() - 1
                            ? ","
                            : "");

                writer.println(stringToPrint);
            }

            writer.close();

        } catch (Exception  e) {
            System.out.println("Error writing values" + e);
            System.exit(2);
        }
    }

    public void writeDoubleValuesToAFile(final String location, final List<Double> values) {
        try {
            final PrintWriter writer = new PrintWriter(location, "UTF-8");

            for (int i = 0 ; i < values.size() ; i++) {
                writer.println(FORMATTER.format(values.get(i)));
            }

            writer.close();

        } catch (Exception  e) {
            System.out.println("Error writing values" + e);
            System.exit(2);
        }
    }
}
