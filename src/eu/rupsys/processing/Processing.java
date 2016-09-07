package eu.rupsys.processing;

public interface Processing<P, R> {

    Processing generate(P parameters);
    R getResult();
}
