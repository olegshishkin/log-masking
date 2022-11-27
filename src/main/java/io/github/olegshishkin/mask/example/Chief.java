package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.api.declaration.MaskRule.FULL;

import io.github.olegshishkin.mask.api.declaration.Masked;
import java.time.LocalDate;
import java.util.List;

public record Chief(String firstName,
                    @Masked(FULL) int age,
                    @Masked(FULL) LocalDate birthDate,
                    List<Account> accounts) {

}
