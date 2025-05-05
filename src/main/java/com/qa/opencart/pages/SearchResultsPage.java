package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;
import static com.qa.opencart.constants.AppConstants.*;

public class SearchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By searchResults = By.className("image");
	
	public SearchResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public int getResultsProductCount() {
		int resultsCount = eleUtil.waitForAllElementsVisible(searchResults, MEDIUM_DEFAULT_TIMEOUT).size();
		System.out.println("result count : "+resultsCount);
		//int resultsCount = eleUtil.getElementsCount(searchResults);
		return resultsCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		System.out.println("product name: "+ productName);
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}
	
	

}
