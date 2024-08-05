package com.qa.testcases;

import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pages.PageDependencies;

public class SearchProductTestCase extends BaseTest {

	@Test(groups = "smoke")
	void searchExistingProduct() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.SearchProduct("iMac");
	}

	@Test(groups = "regression")
	void searchNonExistentProduct() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.checkIfProductExists("FitBit");
	}

	@Test()
	void searchWithoutProductName() {
		PageDependencies dependencies = new PageDependencies(getDriver());
		dependencies.homePage()
			.checkIfProductExists("");
	}

}
