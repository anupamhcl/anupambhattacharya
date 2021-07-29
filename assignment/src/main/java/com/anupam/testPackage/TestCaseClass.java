package com.anupam.testPackage;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.automation.uiPackage.FormFillup;
import com.automation.uiPackage.LoginPage;



public class TestCaseClass extends HelperClass {
	private static String stGlobalParentURL = "https://www.lambdatest.com/automation-demos/";	
	private static String strGlobalUserName = "lambda";
	private static String stGlobalPassword = "lambda123";
	
	public TestCaseClass(){
		
	}

	@SuppressWarnings("unused")
	@Test
	public void lamda() {
		try {
			System.out.println("in lamda login");
			driver.get(stGlobalParentURL);
			
			LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
			loginPage.loginLamda(strGlobalUserName, stGlobalPassword);
			
			FormFillup formFillup = PageFactory.initElements(driver, FormFillup.class);
			FormFillup.formFillupLamda();	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
