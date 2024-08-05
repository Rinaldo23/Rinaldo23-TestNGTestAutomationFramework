package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.base.BaseTest;
import com.qa.pages.PageDependencies;

public class RegisterTestCase extends BaseTest {
	
	@Test(groups = "sanity")
	void registerNewUser() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToRegisterPage()
			.EnterUserDetails()
			.SelectPrivacyCheckBox()
			.Continue()
			.CheckAccountCreatedMessage();
		Assert.assertEquals(dependencies.accountPage().IsNewsLetterSubscribed(), false);
	}

	@Test
	void registerNewUserWithNewsLetter() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToRegisterPage()
			.EnterUserDetails()
			.SelectNewsLetterCheckBox()
			.SelectPrivacyCheckBox()
			.Continue()
			.CheckAccountCreatedMessage();
		Assert.assertEquals(dependencies.accountPage().IsNewsLetterSubscribed(), true);
	}

	@Test(groups = "regression")
	void registerNewUserWithNoInfo() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToRegisterPage()
			.EnterBlankUserDetails()
			.Continue()
			.PrivacyPolicyAlert()
			.verifyErrorTextMessages();
	}

	@Test(groups = "smoke")
	void verifyRegisterPageDetails() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.NavigateToRegisterPage()
			.verifyPageTitle()
			.verifyPageUrl();
	}
}
