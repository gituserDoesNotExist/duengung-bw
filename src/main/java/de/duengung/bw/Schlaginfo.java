package de.duengung.bw;

public class Schlaginfo {

	String schlagname;
	String schlagnummer;
	String germarkung;
	String schlaggroesse;
	String humusgehalt;
	String pGehaltsklasse;
	String zwischenfrucht;
	String hauptfrucht;
	String hauptfruchtVorjahr;
	boolean wasserschutzgebiet;
	int ackerzahl;
	int nMin;
	boolean isGetreide;
	boolean isGruenland;
	boolean isFeldfutter;
	int dungVorjahrGuelle;
	int dungVorjahrRindermist;
	int dungVorjahrPferdemist;
	int dungVorjahrGaerrest;
	String bodenart;

	public boolean wurdeImVorjahrGeduengt() {
		return dungVorjahrGaerrest > 0 || dungVorjahrGuelle > 0 || dungVorjahrRindermist > 0 || dungVorjahrPferdemist > 0;
	}

}
