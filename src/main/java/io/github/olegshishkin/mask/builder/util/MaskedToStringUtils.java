package io.github.olegshishkin.mask.builder.util;

import java.util.regex.Pattern;

public final class MaskedToStringUtils {

    private static final Pattern JAVA_PACKAGES = Pattern.compile(String.join("|", "^java.*", "^com\\.sun\\..*"));

    private MaskedToStringUtils() {
        // no op
    }

    public static boolean isJdkClass(Class<?> clazz) {
        return JAVA_PACKAGES.matcher(clazz.getPackageName()).matches();
    }
}
