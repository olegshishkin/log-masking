package io.github.olegshishkin.mask.example.maskers;

import io.github.olegshishkin.mask.builder.masker.Masker;
import org.apache.commons.lang3.StringUtils;

public class FullMasker implements Masker {

    @Override
    public String mask(Object o) {
        if (o == null) {
            return null;
        }
        return StringUtils.repeat(ASTERISK.charAt(0), o.toString().length());
    }
}
