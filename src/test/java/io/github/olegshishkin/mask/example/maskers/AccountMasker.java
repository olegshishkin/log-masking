package io.github.olegshishkin.mask.example.maskers;

import io.github.olegshishkin.mask.builder.masker.Masker;
import java.util.regex.Pattern;

public class AccountMasker implements Masker {

    private static final String PATTERN = Pattern.compile("^\\d{15}").pattern();
    private static final String REPLACEMENT = ASTERISK.repeat(15);

    @Override
    public String mask(Object o) {
        if (o == null) {
            return null;
        }
        return o.toString().replaceFirst(PATTERN, REPLACEMENT);
    }
}
