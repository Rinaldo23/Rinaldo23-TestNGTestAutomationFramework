package com.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.base.BaseTest;

public class TestListener extends BaseTest implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getName());
		System.out.println("onTestStart");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getName());
		System.out.println("onTestSuccess");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getName());
		System.out.println("onTestFailure");

		CaptureScreenShot(result.getTestContext().getName() + "_" + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getName());
		System.out.println("onTestSkipped");
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println(result.getName());
		System.out.println("onTestFailedWithTimeout");
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getName());
		System.out.println("onStart");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getName());
		System.out.println("onFinish");
	}

}
