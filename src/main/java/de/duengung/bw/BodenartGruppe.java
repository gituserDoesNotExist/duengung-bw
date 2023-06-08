package de.duengung.bw;

public enum BodenartGruppe {

	LEICHT, MITTEL, SCHWER;

	public boolean isLeichtOrMittel() {
		return this == LEICHT || this == MITTEL;
	}
}
