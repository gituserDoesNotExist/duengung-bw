package de.duengung.bw;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class BodenartUIConverterTest {

	@Test
		void testToUIBezeichnungBodenart_ValidInput_Success() {
			assertThat(BodenartUIConverter.toUIBezeichnungBodenart("sandiger Lehm")).isEqualTo("sandiger bis schluffiger Lehm, sL - uL");
		}

	@Test
		void testToUIBezeichnungBodenart_EmptyInput_ReturnsSandigerLehm() {
			assertThat(BodenartUIConverter.toUIBezeichnungBodenart("")).isEqualTo("sandiger bis schluffiger Lehm, sL - uL");
		}
	
	@Test
		void testToUIBezeichnungBodenartGruppe() {
			assertThat(BodenartUIConverter.toUIBezeichnungBodenartGruppe("sandiger Lehm")).isEqualTo("mittel");
			
		}

}
