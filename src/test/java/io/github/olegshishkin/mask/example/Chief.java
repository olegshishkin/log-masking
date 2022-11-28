package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.example.MaskRules.full;

import io.github.olegshishkin.mask.annotation.Mask;
import java.time.LocalDate;
import java.util.List;

public record Chief(String firstName,
                    @Mask(full) String lastName,
                    String patronymic,
                    @Mask(full) int age,
                    @Mask(full) LocalDate hired,
                    List<Account> accounts) {

}
