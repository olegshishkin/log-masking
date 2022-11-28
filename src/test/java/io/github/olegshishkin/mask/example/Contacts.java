package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.example.MaskRules.emailRule;
import static io.github.olegshishkin.mask.example.MaskRules.full;

import io.github.olegshishkin.mask.annotation.Mask;

public record Contacts(@Mask(emailRule) String email, String phone, @Mask(full) String address) {

}
