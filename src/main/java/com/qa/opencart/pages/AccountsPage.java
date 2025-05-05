package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_FRACTION_URL;
import static com.qa.opencart.constants.AppConstants.HOME_PAGE_TITLE;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private final By headers = By.tagName("h2");
	
	private final By searchBox = By.name("search");
	private final By searchButton = By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {
		String title = eleUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("home page title: " + title);
		return title;
	}

	public String getAccPageURL() {
		String url = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("home page url: " + url);
		return url;
	}
	
	public List<String> getAccPageHeaders() {
		List<WebElement> headerList = eleUtil.getElements(headers);
		List<String> headersValList = new ArrayList<String>();
		for(WebElement list:headerList) {
			headersValList.add(list.getText());
		}
		System.out.println("Account page headers : "+headersValList);
		return headersValList;
	}
	
	public SearchResultsPage doSearch(String searchKey) {
		eleUtil.doSendKeys(searchBox, searchKey);
		eleUtil.doClick(searchButton);
		
		return new SearchResultsPage(driver);
	}
	
	
}
