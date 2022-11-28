package io.github.olegshishkin.mask;

import static io.github.olegshishkin.mask.example.MaskRules.account;
import static io.github.olegshishkin.mask.example.MaskRules.emailRule;
import static io.github.olegshishkin.mask.example.MaskRules.full;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MONTHS;
import static java.time.temporal.ChronoUnit.YEARS;

import io.github.olegshishkin.mask.builder.MaskedToStringBuilder;
import io.github.olegshishkin.mask.builder.masker.MaskerResolver;
import io.github.olegshishkin.mask.example.Account;
import io.github.olegshishkin.mask.example.Chief;
import io.github.olegshishkin.mask.example.Company;
import io.github.olegshishkin.mask.example.Contacts;
import io.github.olegshishkin.mask.example.Contract;
import io.github.olegshishkin.mask.example.Rating;
import io.github.olegshishkin.mask.example.maskers.AccountMasker;
import io.github.olegshishkin.mask.example.maskers.EmailMasker;
import io.github.olegshishkin.mask.example.maskers.FullMasker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaskTests {

    @BeforeEach
    public void setUp() {
        MaskerResolver.putMasker(full, new FullMasker());
        MaskerResolver.putMasker(emailRule, new EmailMasker());
        MaskerResolver.putMasker(account, new AccountMasker());
    }

    @Test
    void test() {
        var company = new Company(
                "The Best Company",
                Rating.A,
                LocalDate.now(),
                chief(),
                contacts(),
                "232123",
                ratings(),
                contract(),
                "some very impossible info");

        System.out.println(MaskedToStringBuilder.masked(company));
        System.out.println(MaskedToStringBuilder.prettyMasked(company));
    }

    private Chief chief() {
        return new Chief("John", "Doe", null, 32, LocalDate.now().minus(5, YEARS), accounts());
    }

    private List<Account> accounts() {
        return List.of(
                new Account("first", "40817810000000000001", LocalDateTime.now().minus(1, YEARS)),
                new Account("second", "40817810000000000002", LocalDateTime.now().minus(4, MONTHS)),
                new Account("third", "40817810000000000003", LocalDateTime.now().minus(5, DAYS)));
    }

    private List<Contacts> contacts() {
        var contact1 = new Contacts("mail1@m.m", "1234567890", "some address1");
        var contact2 = new Contacts("mail2@m.m", "1234567890", "some address2");
        var contact3 = new Contacts("mail3@m.m", "1234567890", "some address3");
        return List.of(contact1, contact2, contact3);
    }

    private static Map<String, Set<String>> ratings() {
        return Map.of("AA", Set.of("01", "02", "03"),
                "BB", Set.of("02"),
                "CC", Set.of("02", "03"));
    }

    private Contract[] contract() {
        return new Contract[]{
                new Contract("111", LocalDateTime.now()),
                new Contract("222", LocalDateTime.now().minus(3, YEARS)),
                new Contract("333", LocalDateTime.now().minus(10, MONTHS)),
        };
    }
}
