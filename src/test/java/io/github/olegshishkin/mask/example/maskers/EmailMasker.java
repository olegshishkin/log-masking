package io.github.olegshishkin.mask.example.maskers;

import io.github.olegshishkin.mask.builder.masker.Masker;
import org.apache.commons.lang3.StringUtils;

public class EmailMasker implements Masker {

    private static final char AT = '@';

    @Override
    public String mask(Object o) {
        if (o == null) {
            return null;
        }
        String email = o.toString();
        int index = email.indexOf(AT);
        return StringUtils.repeat(ASTERISK.charAt(0), index) + email.substring(index);
    }
}
