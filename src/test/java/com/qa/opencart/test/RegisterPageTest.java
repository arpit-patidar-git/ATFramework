package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {

	@BeforeClass
	public void registerPageSetUp() {
		registerPage = loginPage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] userRegisterData() {
		return new Object[][] { { "Anchika", "Patidar", "9876543201", "Admin@123" },
				{ "Anchika", "Patidar", "9876543201", "Admin@123" },
				{ "Anchika", "Patidar", "9876543201", "Admin@123" } };
	}

	@DataProvider
	public Object[][] userRegisterExcelData() {
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}

	@Test(dataProvider = "userRegisterExcelData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password) {
		Assert.assertTrue(registerPage.userRegistration(firstName, lastName, telephone, password));
	}
}
