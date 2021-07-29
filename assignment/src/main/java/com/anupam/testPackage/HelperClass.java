package com.anupam.testPackage;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.automation.frameworkPackage.BrowserFactory;

public class HelperClass {
	public static WebDriver driver;
	BrowserFactory objBrwFact = new BrowserFactory();
	
	
	public HelperClass(){
	}

	@BeforeSuite
	public void beforeSuite(){
		System.out.println("in @beforeSuite");
	}

	@BeforeClass
	public void beforeClass(){
		System.out.println("in @BeforeClass");
	}

	@BeforeMethod
	public void beforeMethodClass()
	{
		System.out.println("in @BeforeMethod");
		HelperClass.driver = BrowserFactory.getdriver("chrome");

	}

	@AfterMethod
	public void close()
	{
		System.out.println("@AfterMethod");
		driver.close(); 
		driver.quit();	
	}

	@AfterClass
	public void afterClass(){
		System.out.println("@afterClass");
	}

	@AfterSuite
	public void afterSuite() throws IOException{

		System.out.println("@@AfterSuite");
		//BrowserFactory.getFinalizer();
	}
}
