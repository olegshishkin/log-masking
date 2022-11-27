package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.api.declaration.MaskRule.ACCOUNT;

import io.github.olegshishkin.mask.api.declaration.Masked;

public record Account(@Masked(ACCOUNT) String number) {

}
