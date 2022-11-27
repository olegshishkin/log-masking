package io.github.olegshishkin.mask.builder.masker;

import io.github.olegshishkin.mask.api.declaration.MaskRule;
import java.util.HashMap;
import java.util.Map;

public final class MaskerResolver {

    private static final Map<MaskRule, Masker> MASKERS = new HashMap<>();

    private MaskerResolver() {
        // no op
    }

    public static Masker masker(MaskRule rule) {
        return MASKERS.get(rule);
    }

    public static void putMasker(MaskRule rule, Masker masker) {
        MASKERS.put(rule, masker);
    }
}
