package io.github.olegshishkin.mask;

import static io.github.olegshishkin.mask.api.declaration.MaskRule.ACCOUNT;
import static io.github.olegshishkin.mask.api.declaration.MaskRule.FULL;

import io.github.olegshishkin.mask.builder.MaskedToStringBuilder;
import io.github.olegshishkin.mask.builder.masker.MaskerResolver;
import io.github.olegshishkin.mask.example.Account;
import io.github.olegshishkin.mask.example.Chief;
import io.github.olegshishkin.mask.example.Company;
import io.github.olegshishkin.mask.example.Contract;
import io.github.olegshishkin.mask.example.maskers.AccountMasker;
import io.github.olegshishkin.mask.example.maskers.FullMasker;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaskApplicationTests {

	@BeforeEach
	public void setUp() {
		MaskerResolver.putMasker(FULL, new FullMasker());
		MaskerResolver.putMasker(ACCOUNT, new AccountMasker());
	}

	@Test
	void test() {
		Set<String> codes = Set.of("001", "9876543", "0123456789", "1");
		Company company = new Company("The Best Company", codes, new Contract[]{firstContract(), secondContract()});

		System.out.println(MaskedToStringBuilder.masked(company));
		System.out.println(MaskedToStringBuilder.prettyMasked(company));
	}

	private Contract firstContract() {
		var account1 = new Account("40817810000000000001");
		var account2 = new Account("40817810000000000002");
		var account3 = new Account("40817810000000000003");
		var chief = new Chief("John Doe", 34, LocalDate.now(), List.of(account1, account2, account3));
		return new Contract("00000001", LocalDateTime.now(), chief);
	}

	private Contract secondContract() {
		var account1 = new Account("40817810000000000010");
		var account2 = new Account("40817810000000000020");
		var account3 = new Account("40817810000000000030");
		var chief = new Chief("Jane Doe", 32, LocalDate.now(), List.of(account1, account2, account3));
		return new Contract("00000002", LocalDateTime.now(), chief);
	}
}
