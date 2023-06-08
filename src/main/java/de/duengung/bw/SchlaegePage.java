package de.duengung.bw;

import static de.duengung.bw.BodenartUIConverter.toUIBezeichnungBodenart;
import static de.duengung.bw.BodenartUIConverter.toUIBezeichnungBodenartGruppe;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class SchlaegePage {

	private static final String IFRAME_NEUER_SCHLAG = "iframe[title=\"Neuer Schlag\"]";

	private Page page;

	public SchlaegePage(Page page) {
		this.page = page;
	}

	public void deleteAllSchlaege() {
		page.waitForSelector("#schlaegeTabelleForm\\:schlaegeTable_data tr:first-child");
		Locator tableRowLocator = page.locator("#schlaegeTabelleForm\\:schlaegeTable_data tr:first-child");
		while (tableRowLocator.count() == 1) {
			tableRowLocator.click();
			page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Löschen")).click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ja")).click();
			Sleeper.sleepALittleBit();
			tableRowLocator = page.locator("#schlaegeTabelleForm\\:schlaegeTable_data tr:first-child");
		}

	}

	public void createNewSchlag(Schlaginfo schlaginfo) {
		System.out.println("Create Schlag with name " + schlaginfo.schlagname);
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Neuen Schlag anlegen")).click();
		Sleeper.sleepALittleBit();
		FrameLocator schlagFrameLocator = page.frameLocator(IFRAME_NEUER_SCHLAG);

		schlagFrameLocator.getByLabel("Schlagname*").fill(schlaginfo.schlagname);
		Sleeper.sleepALittleBit();
		schlagFrameLocator.getByLabel("Schlagnummer").fill(schlaginfo.schlagnummer);
		Sleeper.sleepALittleBit();

		schlagFrameLocator.locator("[id$='dienstbezirkInput_label']").click();
		Sleeper.sleepALittleBit();
		schlagFrameLocator.getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Rems-Murr-Kreis")).click();
		Sleeper.sleepALittleBit();
		schlagFrameLocator.locator("[id$='gemarkungDetailDialogInput']").click();
		Sleeper.sleepALittleBit();
		schlagFrameLocator.getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(schlaginfo.germarkung)).click();
		Sleeper.sleepALittleBit();

		schlagFrameLocator.locator("[id$='nitratgebiet']").getByText("nein").click();
		schlagFrameLocator.getByLabel("Schlaggröße*").fill(schlaginfo.schlaggroesse);
		schlagFrameLocator.getByLabel("Durchwurzelungstiefe*").fill("30");

		schlagFrameLocator.locator("[id$='bodenschwere']").getByText(toUIBezeichnungBodenartGruppe(schlaginfo.bodenart)).click();
		if (isBodenartLeichtOrMittel(schlaginfo)) {
			schlagFrameLocator.locator("[id$='bodenartInput_label']").click();
			Sleeper.sleepALittleBit();
			schlagFrameLocator.getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(toUIBezeichnungBodenart(schlaginfo.bodenart))).click();
			Sleeper.sleepALittleBit();
		}

		if (schlaginfo.ackerzahl <= 40) {
			schlagFrameLocator.getByText("bis 40").click();

		} else if (schlaginfo.ackerzahl > 40 && schlaginfo.ackerzahl <= 60) {
			schlagFrameLocator.getByText("40 - 60").click();
		} else {
			schlagFrameLocator.getByText("über 60").click();
		}

		Locator divWsg = schlagFrameLocator.locator("[id$='gridWasserschutzPnl']");
		if (schlaginfo.wasserschutzgebiet) {
			divWsg.getByText("Normalgebiet").click();
			divWsg.getByText("B-Boden");
		} else {
			divWsg.getByText("nein");
		}

		schlagFrameLocator.locator("[id$='gehalteEingebenOpt1']").click();
		schlagFrameLocator.locator("[id$='pGehaltsklasseInput_label']").click();
		Sleeper.sleepALittleBit();
		schlagFrameLocator.getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(schlaginfo.pGehaltsklasse).setExact(true)).click();

		schlagFrameLocator.getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Speichern")).click();
	}

	private boolean isBodenartLeichtOrMittel(Schlaginfo schlaginfo) {
		return Bodenart.getFromBezeichnung(schlaginfo.bodenart).getGruppe().isLeichtOrMittel();
	}

}
