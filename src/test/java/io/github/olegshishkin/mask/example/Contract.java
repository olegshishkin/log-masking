package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.example.MaskRules.full;

import io.github.olegshishkin.mask.annotation.Mask;
import java.time.LocalDateTime;

public record Contract(@Mask(full) String number, LocalDateTime date) {

}
