package io.github.olegshishkin.mask.builder;

public class MaskedToStringBuilder {

    public static String masked(Object o) {
        MaskedRecursiveToStringStyle style = new MaskedRecursiveToStringStyle();
        return new MaskedReflectionToStringBuilder(o, style).toString();
    }

    public static String prettyMasked(Object o) {
        MaskedMultilineRecursiveToStringStyle style = new MaskedMultilineRecursiveToStringStyle();
        return new MaskedReflectionToStringBuilder(o, style).toString();
    }
}
