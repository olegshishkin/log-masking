package io.github.olegshishkin.mask.example.maskers;

import io.github.olegshishkin.mask.builder.masker.Masker;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RegExUtils;

public class FullMasker implements Masker {

    private static final Pattern PATTERN = Pattern.compile("[\\s\\S]");

    @Override
    public String mask(Object o) {
        if (o == null) {
            return null;
        }
        return RegExUtils.replaceAll(o.toString(), PATTERN, "*");
    }
}
