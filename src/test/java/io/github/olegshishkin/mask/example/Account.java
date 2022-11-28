package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.example.MaskRules.account;

import io.github.olegshishkin.mask.annotation.Mask;
import java.time.LocalDateTime;

public record Account(String name, @Mask(account) String number, LocalDateTime created) {

}
