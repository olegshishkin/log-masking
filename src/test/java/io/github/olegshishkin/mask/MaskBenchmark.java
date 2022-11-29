package io.github.olegshishkin.mask;

import static io.github.olegshishkin.mask.example.MaskRules.account;
import static io.github.olegshishkin.mask.example.MaskRules.emailRule;
import static io.github.olegshishkin.mask.example.MaskRules.full;
import static java.time.ZoneId.systemDefault;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

import com.github.javafaker.Faker;
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
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

public class MaskBenchmark {

    public static void main(String[] args) throws IOException {
        org.openjdk.jmh.Main.main(args);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
    @OutputTimeUnit(MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = MILLISECONDS)
    public void maskedRecursionToString(Context context, Blackhole blackhole) {
        for (int i = 0; i < context.dataSize; i++) {
            var str = MaskedToStringBuilder.masked(context.next());
            blackhole.consume(str);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
    @OutputTimeUnit(MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = MILLISECONDS)
    public void recursionToString(Context context, Blackhole blackhole) {
        for (int i = 0; i < context.dataSize; i++) {
            var str = ToStringBuilder.reflectionToString(context.next());
            blackhole.consume(str);
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Fork(value = 1, warmups = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
    @OutputTimeUnit(MILLISECONDS)
    @Warmup(iterations = 1, time = 1, timeUnit = MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = MILLISECONDS)
    public void toString(Context context, Blackhole blackhole) {
        for (int i = 0; i < context.dataSize; i++) {
            String str = context.next().toString();
            blackhole.consume(str);
        }
    }

    @State(Scope.Benchmark)
    public static class Context {

        private Iterator<Company> iterator;

        @Param({"10", "100", "1000"})
        private int dataSize;

        private Company next() {
            if (iterator.hasNext()) {
                return iterator.next();
            }
            throw new IllegalStateException();
        }

        @Setup(Level.Invocation)
        public void setUp() {
            MaskerResolver.putMasker(full, new FullMasker());
            MaskerResolver.putMasker(emailRule, new EmailMasker());
            MaskerResolver.putMasker(account, new AccountMasker());

            Deque<Company> companies = new ArrayDeque<>(dataSize);

            var f1 = new Faker(new Random(23));
            var f2 = new Faker(new Random(65));
            var f3 = new Faker(new Random(3));

            for (int i = 0; i < dataSize; i++) {
                var accounts = List.of(
                        new Account(f1.finance().bic(), f1.finance().iban(), dateTime(f1)),
                        new Account(f2.finance().bic(), f2.finance().iban(), dateTime(f2)),
                        new Account(f3.finance().bic(), f3.finance().iban(), dateTime(f3))
                );

                var chief = new Chief(
                        f1.name().firstName(),
                        f1.name().lastName(),
                        f1.name().name(),
                        f1.number().randomDigit(),
                        date(f1),
                        accounts
                );

                var contacts = List.of(
                        new Contacts(f1.bothify("???????##@gmail.com"),
                                f1.phoneNumber().cellPhone(), f1.address().fullAddress()),
                        new Contacts(f2.bothify("???????##@gmail.com"),
                                f2.phoneNumber().cellPhone(), f2.address().fullAddress()),
                        new Contacts(f3.bothify("???????##@gmail.com"),
                                f3.phoneNumber().cellPhone(), f3.address().fullAddress())
                );

                Map<String, Set<String>> currencies = new HashMap<>();
                currencies.put(f1.currency().code(), Set.of(f1.currency().code(), f1.currency().name()));
                currencies.put(f2.currency().code(), Set.of(f2.currency().code(), f2.currency().name()));
                currencies.put(f3.currency().code(), Set.of(f3.currency().code()));

                var contracts = new Contract[]{
                        new Contract(f1.number().digits(8), dateTime(f1)),
                        new Contract(f2.number().digits(8), dateTime(f2)),
                        new Contract(f3.number().digits(8), dateTime(f3))
                };

                var company = new Company(
                        f1.company().name(),
                        Rating.valueOf(f1.regexify("[ABC]")),
                        date(f2),
                        chief,
                        contacts,
                        f3.number().digits(10),
                        currencies,
                        contracts,
                        f1.lorem().fixedString(100)
                );

                companies.add(company);
            }

            iterator = companies.iterator();
        }

        private LocalDate date(Faker faker) {
            return LocalDate.ofInstant(faker.date().birthday().toInstant(), systemDefault());
        }

        private LocalDateTime dateTime(Faker faker) {
            return LocalDateTime.ofInstant(faker.date().birthday().toInstant(), systemDefault());
        }
    }
}
