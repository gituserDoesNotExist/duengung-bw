package de.duengung.bw;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PDbePage {

	private Page page;

	public PDbePage(Page page) {
		this.page = page;
	}

	public void createPDuengebedarfsermittlung(Schlaginfo schlaginfo) {
		System.out.println("Create P-DBE for " + schlaginfo.schlagname);
		clickDropDown("[id$='schlagnameInput_label']");
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.schlagname)).click();
		clickDropDown("[id$='selectedLandwirtKulturInput_label']");
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.hauptfrucht)).click();

		page.locator("[id$='showKalkInput']").click();
		if (schlaginfo.isGetreide) {
			page.locator("[id$='resteAbgefahrenOpt0']").click();
		}
		clickDropDown("[id$='bodenartInput']");
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName("Sand, S")).click();

		page.locator("[id$='gehalteEingebenOpt1']").click();
		page.locator("[id$='pGehaltsklasseInput_label']").click();
		Sleeper.sleepALittleBit();
		page.getByRole(AriaRole.OPTION, new Page.GetByRoleOptions().setName(schlaginfo.pGehaltsklasse)).click();

		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Berechnen und online speichern")).click();

	}

	private void clickDropDown(String selector) {
		page.locator(selector).click();
		Sleeper.sleepALittleBit();
	}

}
