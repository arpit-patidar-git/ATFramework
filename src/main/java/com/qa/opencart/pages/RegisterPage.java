package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;
import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.utils.StringUtils.*;

public class RegisterPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private final By firstName = By.id("input-firstname");
	private final By lastName = By.id("input-lastname");
	private final By email = By.id("input-email");
	private final By telephone = By.id("input-telephone");
	private final By password = By.id("input-password");
	private final By confirm = By.id("input-confirm");
	private final By agreeCheckBox = By.name("agree");
	private final By continueButton = By.className("btn-primary");
	private final By headerText = By.tagName("h1");

	private final By logoutLink = By.linkText("Logout");
	private final By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtil = new ElementUtil(driver);
	}

	public boolean userRegistration(String firstName, String lastName, String telephone,
			String password) {
		eleUtil.doSendKeys(this.firstName, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		System.out.println("email is :" +email);
		eleUtil.doSendKeys(this.email, randomEmail());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirm, password);

		eleUtil.doClick(agreeCheckBox);
		eleUtil.doClick(continueButton);
		
		eleUtil.waitForAllElementsPresence(headerText, DEFAULT_TIMEOUT);
		
		if (eleUtil.doElementGetText(headerText).equals(REGISTRATION_SUCCESS_MESSAGE)) {
			System.out.println("REGISTRATION SUCCESSFUL");
			eleUtil.clickWhenReady(logoutLink, DEFAULT_TIMEOUT);
			eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
			return true;
		}
		return false;
	}

}
