package eu.rupsys.processing;

import java.math.BigDecimal;
import java.util.List;

public interface Processing {

    List<BigDecimal> getResult();

    enum Type {

        POINT
    }
}
