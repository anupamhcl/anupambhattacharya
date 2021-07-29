package com.automation.frameworkPackage;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Hello world!
 *
 */
public class BrowserFactory 
{
	static String strUserDirectory = System.getProperty("user.dir");

	static String strBasePath = "src/main/resources";
	static String strDownloadPath = "src/main/downloads";
	static String strChromeDriverExeName = "chromedriver92.exe";
	static String strFirefoxDriverExeName = "geckodriver.exe";
	static String strInternetExplorerDriverExeName =  "IEDriverServer.exe";

	/* static String strEdgeDriverExeName = ""; */

	static WebDriver wbDriverInstance = null;

	public BrowserFactory()
	{
		 
	} 
	
	/*
	 * public static void main( String[] args ) throws InterruptedException {
	 * System.out.println("Initalized......."); String strBrowserName = "chrome" ;
	 * getdriver(strBrowserName); Thread.sleep(50000); getFinalizer(); }
	 */

	public static WebDriver getdriver(String strBrowser)
	{
		if(wbDriverInstance == null)
		{
			if(strBrowser.equalsIgnoreCase("chrome"))
				wbDriverInstance = getChromeDriver();
			else if(strBrowser.equalsIgnoreCase("firefox")) 
				wbDriverInstance = getFirefoxDriver();
			else if(strBrowser.equalsIgnoreCase("ie")) 
				wbDriverInstance = getInternetExplorerDriver();
			else if(strBrowser.equalsIgnoreCase("edge")) 
				wbDriverInstance = getFirefoxDriver();   
		}
		return wbDriverInstance;

	}		

	@SuppressWarnings("deprecation")
	public static WebDriver getChromeDriver()
	{
		System.setProperty("webdriver.chrome.driver", strBasePath + File.separator + strChromeDriverExeName);
		getCapabilitiesOptions().merge(getChromeOptions());
		
		wbDriverInstance = new ChromeDriver(getCapabilitiesOptions());   
		//wbDriverInstance.get("https://www.google.co.in");
		wbDriverInstance.manage().window().maximize();
		wbDriverInstance.manage().deleteAllCookies();
		wbDriverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wbDriverInstance.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		return wbDriverInstance;
	}

	@SuppressWarnings("deprecation")
	public static WebDriver getFirefoxDriver()
	{
		System.setProperty("webdriver.gecko.driver", strBasePath + File.separator + strFirefoxDriverExeName );

		FirefoxProfile fxProfile = new FirefoxProfile();
		DesiredCapabilities fxCap = DesiredCapabilities.firefox();
		fxCap.setCapability(FirefoxDriver.PROFILE, fxProfile);
		wbDriverInstance = new FirefoxDriver(fxCap);   

		//wbDriverInstance.get("https://www.google.co.in");
		wbDriverInstance.manage().window().maximize();
		wbDriverInstance.manage().deleteAllCookies();
		wbDriverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wbDriverInstance.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		return wbDriverInstance;
	}

	public static WebDriver getInternetExplorerDriver()
	{
		System.setProperty("webdriver.ie.driver",strBasePath + File.separator + strInternetExplorerDriverExeName);
		wbDriverInstance = new InternetExplorerDriver();  
		//wbDriverInstance.get("https://www.google.co.in");
		wbDriverInstance.manage().window().maximize();
		wbDriverInstance.manage().deleteAllCookies();
		wbDriverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wbDriverInstance.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		return wbDriverInstance;
	}


	/*
	 * public static void getEdgeDriver() {
	 * System.setProperty("webdriver.ie.driver",strBasePath + File.separator +
	 * "IEDriverServer.exe"); wbDriverInstance = new InternetExplorerDriver();
	 * wbDriverInstance.get("https://www.google.co.in");
	 * wbDriverInstance.manage().window().maximize();
	 * wbDriverInstance.manage().deleteAllCookies();
	 * wbDriverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * wbDriverInstance.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS); }
	 */

	public static DesiredCapabilities getCapabilitiesOptions()
	{
		DesiredCapabilities chrCapabilities = DesiredCapabilities.chrome();

		/*
		 * chrCapabilities.setCapability("build", "your build name");
		 * chrCapabilities.setCapability("name", "your test name");
		 * chrCapabilities.setCapability("resolution","1280x800");
		 * chrCapabilities.setCapability("console",true);
		 * chrCapabilities.setCapability("network",true);
		 * chrCapabilities.setCapability("video",false);
		 * chrCapabilities.setCapability("visual",true);
		 * chrCapabilities.setCapability("timezone","UTC+07:00");
		 * chrCapabilities.setCapability("geoLocation","IN");
		 * chrCapabilities.setCapability("chrome.driver","91.0");
		 * chrCapabilities.setCapability("headless",false);
		 */
		
		chrCapabilities.setCapability("version","92.0");
		chrCapabilities.setCapability("selenium_version","3.141.59");
		chrCapabilities.setCapability("platform", "Windows 10");
		chrCapabilities.setCapability("browserName", "Chrome");
		chrCapabilities.setPlatform(Platform.WINDOWS);
		chrCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chrCapabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);

		chrCapabilities.setCapability(ChromeOptions.CAPABILITY, getChromeOptions());

		return chrCapabilities;

	}    

	public static ChromeOptions getChromeOptions()
	{
		ChromeOptions chrOptions = new ChromeOptions();
		chrOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
		chrOptions.setExperimentalOption("prefs", getChromePrefs());
		chrOptions.addArguments("--disable-notifications");
		chrOptions.addArguments("--incognito");
		chrOptions.addArguments("--disable-popup-blocking");

		/*
		 * chrOptions.addArguments("--no-first-run");
		 * chrOptions.addArguments("--homepage=about:blank");
		 * chrOptions.addArguments("--test-type");
		 * chrOptions.addArguments("--disable-extensions");
		 * chrOptions.addArguments("--start-maximized");
		 * chrOptions.addArguments("--start-fullscreen");
		 * chrOptions.addArguments("-start-in-incognito");
		 * chrOptions.addArguments("--ignore-certificate-errors");
		 * 
		 * chrOptions.addArguments("--window-size=1920,1080");
		 * chrOptions.addArguments("--enable-precise-memory-info");
		 * chrOptions.addArguments("--disable-default-apps");
		 * chrOptions.addArguments("--allow-running-insecure-content");
		 * chrOptions.addArguments("--test-type=browser");
		 * chrOptions.addArguments("--safebrowsing-manual-download-blacklist");
		 * chrOptions.addArguments("--safebrowsing-disable-extension-blacklist");
		 * chrOptions.addArguments("--safebrowsing-disable-download-protection");
		 * chrOptions.addArguments("--headless");
		 */

		return chrOptions;
	}

	public static HashMap<String,Object> getChromePrefs()
	{
		HashMap<String,Object> chrPrefs = new HashMap<String, Object>();
		chrPrefs.put("profile.default_content_settings.popups", 0);
		/*
		 * chrPrefs.put("download.default_directory", strDownloadPath );
		 * chrPrefs.put("profile.managed_default_content_settings.notifications", 2);
		 * chrPrefs.put("profile.managed_default_content_settings.popups", 2);
		 * chrPrefs.put("safebrowsing.enabled","true");
		 * chrPrefs.put("plugins.plugins_disabled", new String[] { "Adobe Flash Player",
		 * "Chrome PDF Viewer" });
		 */

		return chrPrefs;
	}



	public static void getFinalizer()
	{
		wbDriverInstance.close();
		wbDriverInstance.quit();

		System.gc();
		Runtime.getRuntime().gc();
		System.runFinalization();
	


	}


}
