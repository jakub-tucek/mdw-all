package cvut.fit.service;

import java.util.ArrayList;
import java.util.stream.Stream;

public class StringUtils {

    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isAnyBlank(String... s) {
        return Stream.of(s).anyMatch(StringUtils::isBlank);
    }
}
