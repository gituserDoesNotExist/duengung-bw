package de.duengung.bw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class BodenartGruppeTest {

	
	@Test
	void testIsLeichtOrMittel() throws Exception {
		assertThat(BodenartGruppe.LEICHT.isLeichtOrMittel()).isTrue();
		assertThat(BodenartGruppe.MITTEL.isLeichtOrMittel()).isTrue();
		assertThat(BodenartGruppe.SCHWER.isLeichtOrMittel()).isFalse();
	}
}
