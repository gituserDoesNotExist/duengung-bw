package de.duengung.bw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

class AppTest {

	private List<Schlaginfo> schlaege;
	private Playwright playwright;
	private Page page;

	@BeforeEach
	void setUp() {
		playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext context = browser.newContext();
		page = context.newPage();
		schlaege = new SchlaginfoRepository().getSchlaege();
	}

	@AfterEach
	void tearDown() {
		if (this.playwright != null) {
			playwright.close();
		}
	}

	@Test
	public void deleteSchlaege() {
		LoginPage loginPage = goToLoginPage();
		DuengungBwDashboard dashboard = loginPage.login();
		SchlaegePage schlaegePage = dashboard.goToSchlaege();
		Sleeper.sleepALittleBit();
		schlaegePage.deleteAllSchlaege();
	}

	@Test
	public void deleteNDbe() {
		LoginPage loginPage = goToLoginPage();
		DuengungBwDashboard dashboard = loginPage.login();
		NDbeUebersichtPage nDbeUebersicht = dashboard.goFromStartpageToNDuengebedarfsermittlungUebersicht();
		nDbeUebersicht.deleteNDuengebedarfsermittlungen();
	}

	@Test
	public void deletePDbe() {
		LoginPage loginPage = goToLoginPage();
		DuengungBwDashboard dashboard = loginPage.login();
		PDbeUebersichtPage pDbeUebersicht = dashboard.goFromStartpageToPDuengebedarfsermittlungUebersicht();
		pDbeUebersicht.deletePDuengebedarfsermittlungen();
	}

	@Test
	public void schlaegeAnlegen() {
		LoginPage loginPage = goToLoginPage();
		DuengungBwDashboard dashboard = loginPage.login();
		SchlaegePage schlaegePage = dashboard.goToSchlaege();

		schlaege.forEach(schlaginfo -> {
			Sleeper.sleepALittleBit();
			schlaegePage.createNewSchlag(schlaginfo);
		});
	}

	@Test
	public void nDbe() throws FileNotFoundException, IOException, InterruptedException {
		LoginPage loginPage = goToLoginPage();
		DuengungBwDashboard dashboard = loginPage.login();

		schlaege.forEach(schlaginfo -> {
			Sleeper.sleepALittleBit();
			NDbePage nDbePage = dashboard.goFromStartpageToNDuengebedarfsermittlung();
			nDbePage.createNDuengebedarfsermittlung(schlaginfo);
		});

	}

	@Test
	public void pDbe() throws FileNotFoundException, IOException, InterruptedException {
		LoginPage loginPage = goToLoginPage();
		DuengungBwDashboard dashboard = loginPage.login();

		schlaege.forEach(schlaginfo -> {
			Sleeper.sleepALittleBit();
			PDbePage pDbePage = dashboard.goFromStartpageToPDuengebedarfsermittlung();
			pDbePage.createPDuengebedarfsermittlung(schlaginfo);
		});

	}
	
	private LoginPage goToLoginPage() {
		page.navigate("https://www.duengung-bw.de/landwirtschaft/");
		return new LoginPage(page);
	}

}
