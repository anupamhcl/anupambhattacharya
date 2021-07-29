package com.automation.uiPackage;

import static org.testng.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

public class FormFillup {
	static WebDriver wbdriver;
	private static String stGlobalEmail = "anupam.bhattacharya@hcl.com";
	
	private static String stGlobalTabURL = "https://www.lambdatest.com/selenium-automation";
	
	public FormFillup(WebDriver driver)
	{
		this.wbdriver= driver;
	}
		
	
	@FindBy(how=How.CSS,using = "#developer-name")
	@CacheLookup
	static WebElement webInputEmail;
	
	@FindBy(how=How.XPATH,using = "//input[@id='populate']")
	@CacheLookup
	static WebElement webBtnPopulate;

	
	@FindBy(how=How.XPATH,using = "//input[@id='6months']")
	@CacheLookup
	static WebElement weRadiobtnFreqTime;
	
	@FindBy(how=How.XPATH,using = "//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[1]/label[1]")
	@CacheLookup
	static WebElement weChkBoxDecisiveFactorsOne;
	
	@FindBy(how=How.XPATH,using = "//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[2]/label[1]")
	@CacheLookup
	static WebElement weChkBoxDecisiveFactorsTwo;
	
	
	@FindBy(how=How.XPATH,using = "//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[3]/label[1]")
	@CacheLookup
	static WebElement weChkBoxFreqFactorsThree;
	
	
	@FindBy(how=How.XPATH,using = "//body/div[@id='__next']/div[1]/section[2]/div[1]/div[1]/div[2]/p[4]/label[1]")
	@CacheLookup
	static WebElement weChkBoxFreqFactorsFour;
	
	
	@FindBy(how=How.XPATH,using = "//select[@id='preferred-payment']")
	@CacheLookup
	static WebElement weDropdownModePayment;
	
	@FindBy(how=How.CSS,using = "#tried-ecom")
	@CacheLookup
	static WebElement weChkBoxDeclaration;
	
	
	@FindBy(how=How.XPATH,using = "//div[contains(text(),'5')]")
	@CacheLookup
	static WebElement fromElement;
	
	@FindBy(how=How.XPATH,using = "//div[contains(text(),'9')]")
	@CacheLookup
	static WebElement toElement;
	
	@FindBy(how=How.XPATH,using = "//textarea[@id='comments']")
	@CacheLookup
	static WebElement weFeedback;
	
	@FindBy(how=How.XPATH,using = "//body/div[@id='__next']/div[1]/section[7]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/img[1]")
	@CacheLookup
	static WebElement logoJenkins;
	
	@FindBy(how=How.XPATH,using = "//label[@id='img']")
	@CacheLookup
	static WebElement uploadfiles;	
	
	@FindBy(how=How.XPATH,using = "//button[@id='submit-button']")
	@CacheLookup
	static WebElement btnSubmit;
	
	@FindBy(how=How.XPATH,using = "//p[contains(text(),'You have successfully submitted the form.')]")
	@CacheLookup
	static WebElement weFormSuccess;
	
	
	public static void formFillupLamda() throws InterruptedException, IOException
	{
		Thread.sleep(5000);	
		System.out.println();
		webInputEmail.sendKeys(stGlobalEmail);
		webBtnPopulate.click();
		
		Alert alert = wbdriver.switchTo().alert();
		String alertPopupMessage = wbdriver.switchTo().alert().getText();
		System.out.println(alertPopupMessage);            
		alert.accept();
		
		weRadiobtnFreqTime.click();
		weChkBoxDecisiveFactorsOne.click();
		weChkBoxDecisiveFactorsTwo.click();
		weChkBoxFreqFactorsThree.click();
		weChkBoxFreqFactorsFour.click();
		
		if(weDropdownModePayment.isSelected())
			weDropdownModePayment.clear();
		
		
		Select dropdownSelect = new Select(weDropdownModePayment);
		dropdownSelect.selectByIndex(1);

		weChkBoxDeclaration.click();

		Actions act=new Actions(wbdriver); 
		act.dragAndDrop(fromElement,toElement).build().perform();

		if(toElement.getText().equals("9"))
		{
			assertTrue(true, "9");			
		}
		else
			assertTrue(false, "Failed");			

		Thread.sleep(2000);
		
		weFeedback.sendKeys("Thanks");
		weFeedback.sendKeys(Keys.RETURN);
		weFeedback.sendKeys("Thanks again");

		((JavascriptExecutor) wbdriver).executeScript("window.open()");             
		ArrayList<String> tabs = new ArrayList<String>(wbdriver.getWindowHandles());
		wbdriver.switchTo().window(tabs.get(1));
		wbdriver.navigate().to(stGlobalTabURL);

		wbdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
		String logoSRC = logoJenkins.getAttribute("src");
		System.out.println(logoSRC);
		URL imageURL = new URL(logoSRC);            

		BufferedImage saveImage = ImageIO.read(imageURL);			
		ImageIO.write(saveImage, "svg", new File("externalFiles/jenkins.svg"));

		wbdriver.switchTo().window(tabs.get(0));
		
		uploadfiles.click(); 
		Thread.sleep(2000);         

		uploadfiles.sendKeys("externalFiles/jenkins.svg");

		Alert alertImage = wbdriver.switchTo().alert();
		String alertImageMsg = wbdriver.switchTo().alert().getText();
		System.out.println(alertImageMsg);            
		alertImage.accept();

		if(alertImageMsg.equals("your image upload sucessfully!!"))
			assertTrue(true,"Form Submitted successfully");
		else
			assertTrue(false,"Image upload Failed");

		
		btnSubmit.click();	
		Thread.sleep(5000);
		
		if(weFormSuccess.getText().equals("You have successfully submitted the form."))
		{
			assertTrue(true,"You have successfully submitted the form.");
		}	
		else
			assertTrue(false,"Failedsubmitted the form.");
		
	}
}
