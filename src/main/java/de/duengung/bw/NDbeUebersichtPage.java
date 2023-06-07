package de.duengung.bw;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NDbeUebersichtPage {

	private Page page;

	public NDbeUebersichtPage(Page page) {
		this.page = page;
	}
	
	public void deleteNDuengebedarfsermittlungen() {
		Locator deleteButtonLocator = page.locator("#bearbeitungsstandTabelleForm\\:bearbeitungsstandTable_data tr:first-child button").nth(0);
		while (deleteButtonLocator.count() == 1) {
			deleteButtonLocator.click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Ja")).click();
			Sleeper.sleepALittleBit();
			deleteButtonLocator = page.locator("#bearbeitungsstandTabelleForm\\:bearbeitungsstandTable_data tr:first-child button").nth(0);
		}

	}

	
	
	
}
