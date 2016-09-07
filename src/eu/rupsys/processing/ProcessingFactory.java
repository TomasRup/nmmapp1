package eu.rupsys.processing;

import eu.rupsys.processing.point.PointProcessing;

public class ProcessingFactory {

    private static ProcessingFactory instance;

    private ProcessingFactory() {
    }

    public static ProcessingFactory get() {
        return instance == null ? instance = new ProcessingFactory() : instance;
    }

    public Processing create(Type type) {

        switch (type) {
            case POINT:
                return new PointProcessing();

            default:
                throw new UnsupportedOperationException();
        }

    }

    public enum Type {
        POINT
    }

}
