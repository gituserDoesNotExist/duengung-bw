package de.duengung.bw;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

	private Page page;

	public LoginPage(Page page) {
		this.page = page;
	}
	
	public DuengungBwDashboard login() {
		page.locator("[name=username]").first().fill("081190670394");
		page.locator("#passwordInput").fill("Annette+Hermann539");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Anmelden")).click();
		return new DuengungBwDashboard(page);
	}

}
