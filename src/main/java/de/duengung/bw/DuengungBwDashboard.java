package de.duengung.bw;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class DuengungBwDashboard {

	private Page page;

	public DuengungBwDashboard(Page page) {
		this.page = page;
	}

	public SchlaegePage goToSchlaege() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Stammdaten").setExact(true)).hover();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Schläge")).click();
		return new SchlaegePage(page);
	}

	public NDbePage goFromStartpageToNDuengebedarfsermittlung() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dienste").setExact(true)).hover();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("N-Düngebedarfsermittlung")).click();
		return new NDbePage(page);
	}

	public NDbeUebersichtPage goFromStartpageToNDuengebedarfsermittlungUebersicht() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Übersicht").setExact(true)).hover();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("N-Düngebedarfsermittlung")).click();
		return new NDbeUebersichtPage(page);
	}

	public PDbePage goFromStartpageToPDuengebedarfsermittlung() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Dienste").setExact(true)).hover();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("P2O5-, K2O-, MgO- und CaO-Düngebedarfsermittlung")).click();
		return new PDbePage(page);
	}

	public PDbeUebersichtPage goFromStartpageToPDuengebedarfsermittlungUebersicht() {
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Übersicht").setExact(true)).hover();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("P2O5-, K2O-, MgO- und CaO-Düngebedarfsermittlung")).click();
		return new PDbeUebersichtPage(page);
	}

}
