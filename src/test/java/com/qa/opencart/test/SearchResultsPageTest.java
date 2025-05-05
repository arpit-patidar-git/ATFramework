package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Epic - 1001")
@Feature("Search Functionality")
@Story("Story - 1003")
public class SearchResultsPageTest extends BaseTest {

	@BeforeClass
	public void searchPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void searchTest() {
		searchResultsPage = accPage.doSearch("macbook");
		int actualResultCount = searchResultsPage.getResultsProductCount();
		int expectedResultCount = 3;
		Assert.assertEquals(actualResultCount, expectedResultCount);

	}

}
