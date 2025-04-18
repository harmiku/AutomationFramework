package com.hl.rough;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Test1{
	private Browser browser;
	private BrowserContext browserContext;
	private Page page;
	
	@BeforeMethod
	public void setUp() {
	Playwright pw = Playwright.create();
	browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
	browserContext = browser.newContext();
	page = browserContext.newPage();
	}
	
	@Test
	public void test1() throws InterruptedException {
	page.navigate("https://www.way2automation.com/angularjs-protractor/registeration/#/login");
	Thread.sleep(5000);
	page.locator("input >> nth=0").fill("angular");
	Thread.sleep(5000);
	page.locator("input >> nth=1").fill("password");
	Thread.sleep(5000);
	page.locator("input >> nth=2").fill("angular");
	Thread.sleep(5000);
	page.locator("button").click();
	Thread.sleep(5000);
	}
	
	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(40000);
	browser.close();
	page.close();
	}
	 
	}
