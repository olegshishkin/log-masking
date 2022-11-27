package io.github.olegshishkin.mask.example;

import java.util.Set;

public record Company(String name, Set<String> codes, Contract[] contracts) {

}
