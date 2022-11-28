package io.github.olegshishkin.mask.builder.masker;

public interface Masker {

    String ASTERISK = "*";

    String mask(Object o);
}
