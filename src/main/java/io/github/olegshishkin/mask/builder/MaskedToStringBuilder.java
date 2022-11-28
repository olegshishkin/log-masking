package io.github.olegshishkin.mask.builder;

public class MaskedToStringBuilder {

    public static String masked(Object o) {
        if (o == null) {
            return null;
        }
        MaskedRecursiveToStringStyle style = new MaskedRecursiveToStringStyle();
        return new MaskedReflectionToStringBuilder(o, style).toString();
    }

    public static String prettyMasked(Object o) {
        if (o == null) {
            return null;
        }
        MaskedMultilineRecursiveToStringStyle style = new MaskedMultilineRecursiveToStringStyle();
        return new MaskedReflectionToStringBuilder(o, style).toString();
    }
}
