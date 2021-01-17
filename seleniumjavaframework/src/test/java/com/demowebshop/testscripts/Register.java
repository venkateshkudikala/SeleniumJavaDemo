package com.demowebshop.testscripts;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.demowebshop.pages.RegisterPage;
import com.demowebshop.testBase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class Register extends TestBase{

	RegisterPage registerPage;
	
	@BeforeClass
	public void setup() throws IOException {
		// Invoke the Browser
		invoke();
		registerPage = new RegisterPage(driver);
	}
	
	@Test
	public void VerifyDemowebShopRegisterWithValidData() {
		test = extent.startTest("TC01_VerifyDemowebShopRegisterWithValidData");
		registerPage.clickRegisterLink();
		test.log(LogStatus.PASS, "Demo Web Shop. Register");
		String title = registerPage.getTitle();
		Assert.assertEquals(title, "Demo Web Shop. Register");
		registerPage.clickGenderMale();
		test.log(LogStatus.PASS, "Demo Web Shop. Register");
		registerPage.EnterFirstName("Ramesh");
		registerPage.EnterLastName("Kudikala");
		registerPage.EnterEmail();
		test.log(LogStatus.PASS, "Demo Web Shop. Register");
		registerPage.EnterPassword("test123");
		registerPage.EnterConfirmPassword("test123");
		registerPage.clickRegisterbtn();
	}
	
	@AfterClass
	public void closeBrowser() {
		extent.flush();
		extent.endTest(test);
		driver.close();
	}
}
