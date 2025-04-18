package com.hl.pages.companydirectory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hl.base.TestBase;

public class LoginPage extends TestBase {
	
	WebDriver webDriver;
	
	public LoginPage(WebDriver driver) {
		webDriver = driver;
	}

	By byUsername = By.name("username");
	public void setUserName(String userName) {
		setTextBoxText(byUsername, userName, "Username");
	}
	
	By byPassword = By.name("password");
	public void setPassword(String password) {
		setTextBoxText(byPassword, password, "Password");
	}
	
	By byContinue = By.xpath("//button[@type='submit']");
	public void clickContinueButton() {
		clickButton(byContinue, "Continue");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	By bySigninFailed = By.xpath("//span[text()='Incorrect username or password. ']");
	public boolean isSigninFailedVisible() {
		return isElementVisible(bySigninFailed, "Login failed");
	}
	public boolean isNotSigninFailedVisible() {
		return isNotElementVisible(bySigninFailed, "Login failed");
	}
}

//Card View:  //span[contains(text(),'Industry Group')]
//Table View: //table/tbody/tr/td[4]

	


