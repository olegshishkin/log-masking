package io.github.olegshishkin.mask;

import static io.github.olegshishkin.mask.example.MaskRules.account;
import static io.github.olegshishkin.mask.example.MaskRules.emailRule;
import static io.github.olegshishkin.mask.example.MaskRules.full;

import io.github.olegshishkin.mask.builder.masker.MaskerResolver;
import io.github.olegshishkin.mask.example.maskers.AccountMasker;
import io.github.olegshishkin.mask.example.maskers.EmailMasker;
import io.github.olegshishkin.mask.example.maskers.FullMasker;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public final class TestUtils {

    static final Pattern TEST_PATTERN = Pattern.compile(String.join("|",
            "rating=([ABC])[,\\]]",
            "Chief\\[.*lastName=(\\S*)[,\\]]",
            "Chief\\[.*age=(\\d*)[,\\]]",
            "Chief\\[.*hired=([\\d-]*)[,\\]]",
            "Account\\[.*?number=(\\d{15})\\d{5}[,\\]]",
            "Contacts\\[email=(.*?)@.*?[,\\]]",
            "Contacts\\[.*?address=(.*?)[,\\]]",
            "code=(\\d*),",
            "contracts=.*?Contract;@(.*?),",
            "information=.*[,\\]]"), Pattern.MULTILINE);

    private TestUtils() {
    }

    static void prepareMaskerResolver() {
        MaskerResolver.putMasker(full, new FullMasker());
        MaskerResolver.putMasker(emailRule, new EmailMasker());
        MaskerResolver.putMasker(account, new AccountMasker());
    }

    static String patternMatcherToString(String str) {
        StringBuilder sb = new StringBuilder(str);
        Matcher m = TEST_PATTERN.matcher(sb);
        while (m.find()) {
            IntStream.rangeClosed(1, m.groupCount()).forEach(group -> {
                if (m.group(group) != null) {
                    IntStream.range(m.start(group), m.end(group))
                            .forEach(i -> sb.setCharAt(i, '*'));
                }
            });
        }
        return sb.toString();
    }
}
