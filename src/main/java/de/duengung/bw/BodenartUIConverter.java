package de.duengung.bw;

import java.util.HashMap;
import java.util.Map;

public class BodenartUIConverter {

	private static final String SCHWACH_TONIGER_LEHM_BIS_TON = "schwach toniger Lehm bis Ton, t'L,tL,lT,T";
	private static final String SANDIGER_BIS_SCHLUFFIGER_LEHM = "sandiger bis schluffiger Lehm, sL - uL";

	public static String toUIBezeichnungBodenart(String bodenart) {
		Map<Bodenart, String> bodenartDictionary = buildBodenartUIDictionary();

		return bodenartDictionary.get(findBodenartTypeFromBezeichnung(bodenart));
	}

	public static String toUIBezeichnungBodenartGruppe(String bodenart) {
		Map<BodenartGruppe, String> bodenartGruppeDictionary = buildBodenartgruppeUIDictionary();

		return bodenartGruppeDictionary.get(findBodenartTypeFromBezeichnung(bodenart).getGruppe());
	}


	private static Bodenart findBodenartTypeFromBezeichnung(String bodenart) {
		return Bodenart.getFromBezeichnung(bodenart);
	}
	
	private static Map<Bodenart, String> buildBodenartUIDictionary() {
		Map<Bodenart, String> bodenartDictionary = new HashMap<>();
		bodenartDictionary.put(Bodenart.SAND, "Sand");
		bodenartDictionary.put(Bodenart.SCHWACH_LEHMIGER_SAND, "schwach lehmiger Sand,l'S");

		bodenartDictionary.put(Bodenart.STARK_LEHMIGER_SAND, "stark lehmiger Sand, lS");
		bodenartDictionary.put(Bodenart.SANDIGER_LEHM, SANDIGER_BIS_SCHLUFFIGER_LEHM);
		bodenartDictionary.put(Bodenart.SCHLUFFIGER_LEHM, SANDIGER_BIS_SCHLUFFIGER_LEHM);

		bodenartDictionary.put(Bodenart.SCHWACH_TONIGER_LEHM, SCHWACH_TONIGER_LEHM_BIS_TON);
		bodenartDictionary.put(Bodenart.TONIGER_LEHM, SCHWACH_TONIGER_LEHM_BIS_TON);
		bodenartDictionary.put(Bodenart.LEHMIGER_TON, SCHWACH_TONIGER_LEHM_BIS_TON);
		bodenartDictionary.put(Bodenart.TON, SCHWACH_TONIGER_LEHM_BIS_TON);
		return bodenartDictionary;
	}

	private static Map<BodenartGruppe, String> buildBodenartgruppeUIDictionary() {
		Map<BodenartGruppe, String> bodenartGruppeDictionary = new HashMap<>();
		bodenartGruppeDictionary.put(BodenartGruppe.LEICHT, "leicht");
		bodenartGruppeDictionary.put(BodenartGruppe.MITTEL, "mittel");
		bodenartGruppeDictionary.put(BodenartGruppe.SCHWER, "schwer");
		return bodenartGruppeDictionary;
	}

}
