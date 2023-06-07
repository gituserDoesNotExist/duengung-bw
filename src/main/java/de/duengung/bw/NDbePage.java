package de.duengung.bw;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NDbePage {

	private Page page;

	public NDbePage(Page page) {
		this.page = page;
	}

	public void createNDuengebedarfsermittlung(Schlaginfo schlaginfo) {
		System.out.println("Create N-DBE for "+ schlaginfo.schlagname);
		clickDropDown("[id$='schlagnameInput_label']");
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.schlagname)).click();
		clickDropDown("[id$='selectedLandwirtKulturInput_label']");
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.hauptfrucht)).click();

		if (schlaginfo.isGetreide) {
			clickDropDown("[id$='vorfruchtInput_label']");
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.hauptfruchtVorjahr)).click();
			clickDropDown("[id$='zwischenfruchtInput_label']");
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.zwischenfrucht)).click();
			page.locator("[id$='strohabfuhrOpt0']").click();
			page.locator("[id$='vorjahrDuengungArtOpt1']").click();
			page.locator("[id$='kompostGeduengtOpt1']").click();
			page.locator("[id$='humusOpt0']").click();
			page.locator("[id$='ackerBodenSchwereOpt1Lbl']").click();
			page.locator("[id$='ichRechneMitReferenzwertOpt0']").click();
			page.getByLabel("Nitratgehalt").fill("" + schlaginfo.nMin);
		} else if (schlaginfo.isFeldfutter) {
			selectOptionErtragsanteilLeguminosen("Klee-/Luzernegras 21 - 30 % Ertragsanteil Leg.");
			selectOptionHumusgehaltGruendlandOrFeldfutter();
		} else if (schlaginfo.isGruenland) {
			selectOptionErtragsanteilLeguminosen("Ertragsanteil von Leg. 5 bis 10 %");
			selectOptionHumusgehaltGruendlandOrFeldfutter();
		}

		if (schlaginfo.wurdeImVorjahrGeduengt()) {
			page.locator("[id$='orgDuengungVorjahrRadioOpt0']").click();
		} else {
			page.locator("[id$='orgDuengungVorjahrRadioOpt1']").click();
		}
		fillDungVorjahr(schlaginfo.dungVorjahrGuelle, "NEU Eigene Guelle");
		fillDungVorjahr(schlaginfo.dungVorjahrGaerrest, "NEU Gaerrest");
		fillDungVorjahr(schlaginfo.dungVorjahrPferdemist, "NEU Pferdemist");
		fillDungVorjahr(schlaginfo.dungVorjahrRindermist, "NEU Jungrindermist");

		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Berechnen und online speichern")).click();
		if (page.getByText("Die Berechnung wurde durchgeführt").count() != 1) {
			throw new RuntimeException("Fehler beim Erstellen der N-DBE für " + schlaginfo.schlagname);
		}
	}

	private void selectOptionHumusgehaltGruendlandOrFeldfutter() {
		clickDropDown("[id$='gruenlandHumusgehaltInput']");
		page.getByRole(AriaRole.OPTION,
				new Page.GetByRoleOptions().setName("Sehr schwach bis stark humose Grünlandböden (weniger als 8 % organische Substanz)")).click();
	}

	private void selectOptionErtragsanteilLeguminosen(String optionText) {
		clickDropDown("[id$='leguminosenanteilInput']");
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(optionText)).click();
	}

	private void fillDungVorjahr(int dungVorjahr, String bezeichnungDuengungBw) {
		if (dungVorjahr > 0) {
			clickDropDown("[id$='nextDuengerInput_label']");
			page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(bezeichnungDuengungBw)).click();
			page.getByLabel("Menge").fill("" + dungVorjahr);
		}
	}

	private void clickDropDown(String selector) {
		page.locator(selector).click();
		Sleeper.sleepALittleBit();
	}

}
