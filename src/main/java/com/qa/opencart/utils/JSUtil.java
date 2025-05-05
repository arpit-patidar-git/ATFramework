package com.qa.opencart.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSUtil {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	
	public JSUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	public void doJSClick(WebElement element) {
		js.executeScript("arguments[0].click();", element);
	}
	
	public String getJSUrl() {
		String url = js.executeScript("document.URL").toString();
		return url;
	}
	
	public void doJSRefresh() {
		js.executeScript("history.go(0)");
	}
	
	public void doJSNavigateForward() {
		js.executeScript("history.go(1)");
	}
	
	public void doJSNavigateBack() {
		js.executeScript("history.go(-1)");
	}
	
	public String getJSTitle() {
		String title = js.executeScript("document.title").toString();
		return title;
	}
	
	public String getJSText() {
		String text = js.executeScript("return document.documentElement.innerText").toString();
		return text;
	}
	

	public void scrollJSPageDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollJSPageDown(String height) {
		js.executeScript("window.scrollTo(0, '" + height + "')");
	}

	public void scrollJSPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
	}
	
	public void scrollJSIntoView(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void doJSSendKeys(String id, String value) {
		js.executeScript("document.getElementById('"+id+"').value='"+value+"'");
	}
	
	public void doJSSendKeysCssSelector(String css, String value) {
		js.executeScript("document.cssSelector('"+css+"').value='"+value+"'");
	}
	
	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");//blue
		for (int i = 0; i < 3; i++) {
			changeColor("rgb(0,200,0)", element);// green
			changeColor(bgcolor, element);// blue
		}
	}
	
	private void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);//GBGBGBGBGBGB

		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
		}
	}
	
	public void drawBorder(WebElement element) {
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

}
