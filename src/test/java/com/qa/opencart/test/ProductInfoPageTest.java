package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.CSVUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 1001")
@Feature("Test Product Page details")
@Story("Story - 1002")
public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Severity(SeverityLevel.NORMAL)
	@Description("This case is Work in progress, Check later")
	@Test(enabled = false, description = "WIP")
	public void productHeaderTest() {	
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		String actualProductHeader = productInfoPage.getProductHeader();
		ChainTestListener.log("actual product header : "+ actualProductHeader);
		Assert.assertEquals(actualProductHeader, "MacBook");
	}

	@DataProvider
	public Object[][] getProductImageData() {
		return new Object[][] {
			{"MacBook","MacBook",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"MacBook","MacBook Air",4}
		};
	}
	

	@DataProvider
	public Object[][] userProductCSVData() {
		return CSVUtil.getCSVData();
	}
	
	@Test(dataProvider = "userProductCSVData")
	public void productImageCountTest(String searchKey, String productName, String expImageCount ) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);
		String actImageCount = String.valueOf(productInfoPage.getProductImageCount());
		Assert.assertEquals(actImageCount, expImageCount);
	}

	@Test
	public void productMetaDataTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		Map<String, String> actProductMetaDataList = productInfoPage.getProductMetaData();
		System.out.println("actual: " + actProductMetaDataList);
		Map<String, String> expProductMetaDataList = Map.of("Brand", "Apple", "Availability", "In Stock",
				"Product Code", "Product 16", "Reward Points", "600");
		System.out.println("expected: " + expProductMetaDataList);
		Assert.assertEquals(actProductMetaDataList, expProductMetaDataList);
	}

	@Test
	public void productPriceDataTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		Map<String, String> actProductPriceMap = productInfoPage.getProductPriceData();
		Map<String, String> expProductPriceMap = Map.of("Product Price", "$602.00", "Excluding tax Price", "$500.00");
		Assert.assertEquals(actProductPriceMap, expProductPriceMap);
	}
	
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","MacBook",4},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"MacBook","MacBook Air",4}
		};
	}

	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Test All product details")
	public void productDetailsTest() {
		searchResultsPage = accPage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Product Header"), "MacBook");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Product Image count"), "5");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Product Price"), "$602.00");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Excluding tax Price"), "$500.00");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Brand"), "Apple");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Product Code"), "Product 16");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Reward Points"), "600");
		softAssert.assertEquals(productInfoPage.getProductDetails().get("Availability"), "In Stock");
		softAssert.assertAll();
	}
}
