package com.qa.opencart.factory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Logger log = LogManager.getLogger(DriverFactory.class);
	
	
	public Properties initProp() {
		prop = new Properties();
		
		//String envName = System.getProperty("env");
		//System.out.println(envName);
		
		FileInputStream file;
		try {
			file = new FileInputStream("./src/test/resources/config/config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		highlight = prop.getProperty("highlight");
		log.info(prop);
		System.out.println("Entered Browser - " + browserName);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;

		case "edge":
			driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;

		case "firefox":
			driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;

		case "safari":
			driver = new SafariDriver();
			break;

		default:
			System.out.println("Invalid browser name");
			log.error("-----Invalid Browser------");
			throw new BrowserException("==Invalid Browser");
		}

		tlDriver.set(driver);
		
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}
}
