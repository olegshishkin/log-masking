package io.github.olegshishkin.mask.builder.util;

import java.util.regex.Pattern;
import org.apache.commons.lang3.ClassUtils;

public final class MaskedToStringUtils {

    private static final Pattern JAVA_PACKAGES = Pattern.compile(String.join("|", "^java.*", "^com\\.sun\\..*"));

    private MaskedToStringUtils() {
        // no op
    }

    public static boolean isRawType(Class<?> clazz) {
        return ClassUtils.isPrimitiveWrapper(clazz) ||
                String.class.equals(clazz) ||
                isJdkClass(clazz);
    }

    public static boolean isJdkClass(Class<?> clazz) {
        return JAVA_PACKAGES.matcher(clazz.getPackageName()).matches();
    }
}
