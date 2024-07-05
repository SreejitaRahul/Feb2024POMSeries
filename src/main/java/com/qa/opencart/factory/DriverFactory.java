package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.qa.opencart.exceptions.BrowserException;

public class DriverFactory {
	
	WebDriver driver;
	OptionsManager optionsManager;
	
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		System.out.println("browser name is:"+ browserName);
		
		optionsManager = new OptionsManager(prop);
		
		switch(browserName.toLowerCase().trim()) {
		
		case "chrome":
			break;
			
		case "firefox":
			break;
			
		case "edge":
			break;
		case "safari":
			break;
					
		default:
			System.out.println("plz pass the reight browser name");
			throw new BrowserException(browserName);
		
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		

		
		//cross browser logic
		
		return driver;
		
	}
	
	public void initProp() {
		// cross prop logic
	}
	

}
