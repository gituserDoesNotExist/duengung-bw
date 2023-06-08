package de.duengung.bw;

import static de.duengung.bw.BodenartGruppe.LEICHT;
import static de.duengung.bw.BodenartGruppe.MITTEL;
import static de.duengung.bw.BodenartGruppe.SCHWER;
import static java.util.Arrays.asList;

public enum Bodenart {

	SAND("Sand", LEICHT), //
	SCHWACH_LEHMIGER_SAND("schwach lehmiger Sand", LEICHT), //

	STARK_LEHMIGER_SAND("stark lehmiger Sand", MITTEL), //
	SANDIGER_LEHM("sandiger Lehm", MITTEL), //
	SCHLUFFIGER_LEHM("schluffiger Lehm", MITTEL), //

	SCHWACH_TONIGER_LEHM("schwach toniger Lehm", SCHWER), //
	TONIGER_LEHM("toniger Lehm", SCHWER), //
	LEHMIGER_TON("lehmiger Ton", SCHWER), //
	TON("Ton", SCHWER);

	/**
	 * Bezeichnung in der internen Schlagliste
	 */
	private String bezeichnung;
	private BodenartGruppe gruppe;

	private Bodenart(String bezeichnung, BodenartGruppe gruppe) {
		this.bezeichnung = bezeichnung;
		this.gruppe = gruppe;
	}

	public static Bodenart getFromBezeichnung(String bezeichnung) {
		Bodenart defaultBodenart = SANDIGER_LEHM;
		return asList(Bodenart.values()).stream()//
				.filter(bodenart -> bodenart.getBezeichnung().equals(bezeichnung))//
				.findFirst()//
				.orElse(defaultBodenart);
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	
	public BodenartGruppe getGruppe() {
		return gruppe;
	}

}
