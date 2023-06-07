package de.duengung.bw;

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
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByLabel("Schlagname*").fill(schlaginfo.schlagname);
		Sleeper.sleepALittleBit();
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByLabel("Schlagnummer").fill(schlaginfo.schlagnummer);
		Sleeper.sleepALittleBit();

		page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id$='dienstbezirkInput_label']").click();
		Sleeper.sleepALittleBit();
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName("Rems-Murr-Kreis")).click();
		Sleeper.sleepALittleBit();
		page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id$='gemarkungDetailDialogInput']").click();
		Sleeper.sleepALittleBit();
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(schlaginfo.germarkung)).click();
		Sleeper.sleepALittleBit();

		page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id=\"schlagDetailForm\\:schlagDetailGrid\\:j_idt69\\:nitratgebiet\\:nitratgebietOpt1\"] span").click();
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByLabel("Schlaggröße*").fill(schlaginfo.schlaggroesse);
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByLabel("Durchwurzelungstiefe*").fill("30");

		if (schlaginfo.ackerzahl <= 40) {
			page.frameLocator(IFRAME_NEUER_SCHLAG).getByText("bis 40").click();

		} else if (schlaginfo.ackerzahl > 40 && schlaginfo.ackerzahl <= 60) {
			page.frameLocator(IFRAME_NEUER_SCHLAG).getByText("40 - 60").click();
		} else {
			page.frameLocator(IFRAME_NEUER_SCHLAG).getByText("über 60").click();
		}

		if (schlaginfo.wasserschutzgebiet) {
			page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id$='wasserschutzgebietOpt0'] div").nth(2).click();
		} else {
			page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id$='wasserschutzgebietOpt0'] div").nth(1).click();
		}

		page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id$='gehalteEingebenOpt1']").click();
		page.frameLocator(IFRAME_NEUER_SCHLAG).locator("[id$='pGehaltsklasseInput_label']").click();
		Sleeper.sleepALittleBit();
		page.frameLocator(IFRAME_NEUER_SCHLAG).getByRole(AriaRole.OPTION, new FrameLocator.GetByRoleOptions().setName(schlaginfo.pGehaltsklasse).setExact(true))
				.click();

		page.frameLocator(IFRAME_NEUER_SCHLAG).getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("Speichern")).click();
	}

}
