package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productMap;

	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//li//img");

	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		productMap = new HashMap<String, String>();
	}

	public String getProductHeader() {
		String productHeaderText = eleUtil.getElement(productHeader).getText();
//		/System.out.println(productHeaderText);
		ChainTestListener.log("Locators : "+ String.valueOf(productHeader));
		return productHeaderText;
		
	} 

	public String getProductImageCount() {
		int imageCount = eleUtil.getElementsCount(productImages);
		//System.out.println("image count : " + imageCount);
		return String.valueOf(imageCount);
	}
	
	public Map<String,String> getProductDetails(){
		productMap = new HashMap<String, String>();
		productMap.put("Product Header", getProductHeader());
		productMap.put("Product Image count", String.valueOf(getProductImageCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("All product details Map is: " + productMap);
		return productMap;
		
		
	}

	public Map<String, String> getProductMetaData() {
		//productMap = new HashMap<String, String>();
		List<WebElement> metaData = eleUtil.waitForAllElementsVisible(productMetaData, DEFAULT_TIMEOUT);
		for (WebElement lineData : metaData) {
			String row = lineData.getText();
			System.out.println("row : " + row);
			String[] meta = row.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productMap.put(metaKey, metaValue);
		}

		System.out.println(productMap);
		return productMap;

	}
	
	public Map<String, String> getProductPriceData() {
		//productMap = new HashMap<String, String>();
		List<WebElement> priceList = eleUtil.waitForAllElementsVisible(productPriceData, DEFAULT_TIMEOUT);
			String productPrice = priceList.get(0).getText().trim();
			String exTaxPrice = (priceList.get(1).getText().split(":"))[1].trim();
			productMap.put("Product Price", productPrice);
			productMap.put("Excluding tax Price", exTaxPrice);
			return productMap;
			
	}
		
}