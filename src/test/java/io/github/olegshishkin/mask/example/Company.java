package io.github.olegshishkin.mask.example;

import static io.github.olegshishkin.mask.example.MaskRules.full;

import io.github.olegshishkin.mask.annotation.Mask;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

public record Company(String name,
                      @Mask(full) Rating rating,
                      LocalDate founded,
                      Chief chief,
                      List<Contacts> contacts,
                      @Mask(full) String code,
                      Map<String, Set<String>> currencies,
                      Contract[] contracts,
                      @Mask(full) Object information) {

}
