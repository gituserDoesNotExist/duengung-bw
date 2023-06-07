package de.duengung.bw;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PDbeUebersichtPage {

	private Page page;

	public PDbeUebersichtPage(Page page) {
		this.page = page;
	}

	public void deletePDuengebedarfsermittlungen() {
		Locator deleteButtonLocator = page.locator("#form\\:gnsTabelle_data tr:first-child button").nth(0);
		while (deleteButtonLocator.count() == 1) {
			deleteButtonLocator.click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ja")).click();
			Sleeper.sleepALittleBit();
			deleteButtonLocator = page.locator("#form\\:gnsTabelle_data tr:first-child button").nth(0);
		}
	}

}
