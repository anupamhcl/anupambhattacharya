package com.automation.uiPackage;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage {
	WebDriver wbdriver;

	public LoginPage(WebDriver driver)
	{
		this.wbdriver= driver;
	}

	@FindBy(how=How.XPATH,using="//h2[contains(text(),'Login to Selenium Playground')]")
	@CacheLookup
	static WebElement webPageLabel;
	
	
	@FindBy(how=How.ID,using="username")
	@CacheLookup	
	WebElement username;
	
	@FindBy(how=How.XPATH,using="//input[@id='password']")
	@CacheLookup
	WebElement password;
	
	@FindBy(how=How.XPATH,using="//button[contains(text(),'Login')]")
	@CacheLookup
	WebElement login;

	public void loginLamda(String use, String pass)
	{
		try 
		{
			if("Login to Selenium Playground".equals(webPageLabel.getText()))		
			{
				System.out.println("@Login to Selenium Playground");	
				assertTrue(true, "Login to Selenium Playground");	
			}	
			else
			{	
				System.out.println("NOT @Login to Selenium Playground");
				assertTrue(false, "Faield to Login to Selenium Playground");	
			}
			
			username.sendKeys(use);
			Thread.sleep(3000);
			password.sendKeys(pass);
			Thread.sleep(3000);
			login.click();
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
