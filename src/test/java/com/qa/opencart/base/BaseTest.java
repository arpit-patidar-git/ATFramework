package com.qa.opencart.base;

import java.io.File;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

//@Listeners(ChainTestListener.class)
@Listeners(TestAllureListener.class)
public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	protected LoginPage loginPage;
	protected Properties prop;
	protected AccountsPage accPage;
	protected SearchResultsPage searchResultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegisterPage registerPage;
	
	public static Logger log = LogManager.getLogger(BaseTest.class);

	
	@BeforeSuite
	public void beforeSuite() {
		deleteFilesFromDirectory(System.getProperty("user.dir")+"//allure-results");
	}
	
	private void deleteFilesFromDirectory(String folderpath) {
		File path = new File(folderpath);
		File[] files = path.listFiles();
		if(files!=null) {
			for(File file:files) {
				file.delete();
				System.out.println("File deleted : " + file.getName());
			}
		}
		else {
			System.out.println("No file present");
		}
		}
	
	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browserName != null) {
			prop.setProperty("browser", browserName);
			log.info("browser : "+browserName);
		}
		driver = df.initDriver(prop);
		loginPage = new LoginPage(driver);
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
