package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pages.PageDependencies;
import com.qa.utility.DataSupplier;

public class LoginTestCase extends BaseTest {

	@Test(dataProvider = "ValidTestData", dataProviderClass = DataSupplier.class)
	void LoginWithValidCredentials(String email, String password) {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToLoginPage()
			.EnterLoginDetails(email, password)
			.ClickOnLoginButton()
			.ConfirmSuccessfulLogin();
	}
	
	@Test(dataProvider = "ParallelTestData", dataProviderClass = DataSupplier.class)
	void LoginWithValidCredsAndParrallelAttribute(String email, String password) {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToLoginPage()
			.EnterLoginDetails(email, password)
			.ClickOnLoginButton()
			.ConfirmSuccessfulLogin();
	}

	@Test(dataProvider = "InvalidTestData", dataProviderClass = DataSupplier.class)
	void LoginWithInvalidCredentials(String email, String password) {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToLoginPage()
			.EnterLoginDetails(email, password)
			.ClickOnLoginButton()
			.ConfirmLoginFailure();
	}

	@Test(groups = "smoke")
	public void ValidateForgotPasswordLink() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToLoginPage()
			.ClickOnForgotPasswordLink()
			.ConfirmForgotPasswordLinkIsVisible();
	}
}
