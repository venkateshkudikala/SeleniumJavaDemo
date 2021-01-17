package com.demowebshop.testBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	/**
	 * Configuration of Browsers All reusable Methods Reporting utilies Property
	 * readers Xml readers
	 * 
	 */
	
	public static WebDriver driver;
	public static Properties prop;
	public static FileInputStream fis;
	public static DesiredCapabilities cap;
    public static ExtentTest test;
    public static ExtentReports extent;
	static {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		
		extent = new ExtentReports(System.getProperty("user.dir")+"/testreport/"+formatter.format(cal.getTime())+".html",false);
	}
	public void properties() throws IOException {
		prop = new Properties();
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\java\\com\\demowebshop\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Property file data :" + prop);
	}

	public static void click(WebDriver driver, By ElementLocator, String name) {
		try {
			
		
		WebElement ele = driver.findElement(ElementLocator);
		if (ele.isDisplayed()) {
			ele.click();
			test.log(LogStatus.PASS, "To Verify user able to click on "+name,  name+ "Clicked successfully" );
		}
		
		} catch (Exception e) {
  test.log(LogStatus.FAIL, "To Verify "+ name +" is visible or clickable in provided time ", "An Exception occured wait for element "+ name + " to click");
		}
	}
	
	
	public static void EnterText(WebDriver driver, By ElementLocator, String value , String name) {
		try {

			WebElement ele = driver.findElement(ElementLocator);
			if (ele.isDisplayed()) {
				ele.clear();
				ele.sendKeys(value);
				test.log(LogStatus.PASS, "To Verify user able to enter value in "+name, value+ " Entered Successfully");
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			test.log(LogStatus.FAIL, "To Verify "+ name +" is visible or enter in provided time ", "An Exception occured wait for element "+ name + " to Enter");

		}
		
	}
		
	
	
	public static String  randomEmail() {
		Random rand = new Random();
		int num = rand.nextInt(1000);
		String email = "test"+num+"@"+"gmail.com";
		return email;
	}
	
	public void invoke() throws IOException {
		properties();
		System.out.println("Running Browser name");
		invokeBrowser("Chrome");
		driver.get("http://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		
	}
	@SuppressWarnings("deprecation")
	public void invokeBrowser(String browser) {

	System.out.println("Browser name");
			if (browser.contains("Chrome")) {
				System.out.println("Browser chrome");
			   WebDriverManager.chromedriver().setup();
			   ChromeOptions options = new ChromeOptions();
			//   options.addArguments("--incognito");
			    new DesiredCapabilities();
				cap = DesiredCapabilities.chrome();
			   cap.setCapability(ChromeOptions.CAPABILITY, options);
			   driver = new ChromeDriver(cap);
				
			} else if (browser.contains("Firefox")) {

				
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions fp = new FirefoxOptions();
				String path = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
				fp.setBinary(path);
				driver = new FirefoxDriver(fp);
			} else {

			}
		
	}

}