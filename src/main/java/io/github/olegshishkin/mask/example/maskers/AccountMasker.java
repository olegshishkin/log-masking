package io.github.olegshishkin.mask.example.maskers;

import io.github.olegshishkin.mask.builder.masker.Masker;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RegExUtils;

public class AccountMasker implements Masker {

    private static final String PATTERN = Pattern.compile("\\d{15}").pattern();

    @Override
    public String mask(Object o) {
        if (o == null) {
            return null;
        }
        return RegExUtils.replacePattern(o.toString(), PATTERN, "*".repeat(15));
    }
}
