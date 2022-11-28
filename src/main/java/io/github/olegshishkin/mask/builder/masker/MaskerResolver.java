package io.github.olegshishkin.mask.builder.masker;

import java.util.HashMap;
import java.util.Map;

public final class MaskerResolver {

    private static final Map<String, Masker> MASKERS = new HashMap<>();

    private MaskerResolver() {
        // no op
    }

    public static Masker masker(String rule) {
        return MASKERS.get(rule);
    }

    public static void putMasker(String rule, Masker masker) {
        MASKERS.put(rule, masker);
    }
}
