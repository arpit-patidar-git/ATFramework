package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil ;

	private final By email = By.id("input-email");
	private final By password = By.id("input-password");
	private final By forgotPassword = By.linkText("Forgotten Password");
	private final By loginButton = By.xpath("//input[@value='Login']");
	private final By registerLink = By.linkText("Register");
	public static Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtil = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {
		String title =  eleUtil.waitFotTitleIs(LOGIN_PAGE_TITLE,MEDIUM_DEFAULT_TIMEOUT);
		log.info("login page title is : " + title);
		return title;
	}

	public String getLoginPageURL() {
		return eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
	}

	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotPassword);
	}

	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(email, MEDIUM_DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(registerLink, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
		}

}
