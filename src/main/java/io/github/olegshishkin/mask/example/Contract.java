package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.api.declaration.MaskRule.FULL;

import io.github.olegshishkin.mask.api.declaration.Masked;
import java.time.LocalDateTime;

public record Contract(@Masked(FULL) String number, LocalDateTime date, Chief chief) {

}
