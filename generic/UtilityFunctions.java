import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.codeborne.selenide.WebDriverRunner;

public class UtilityFunctions {
	
	public static WebDriver driver;
	public static Sheet sheet;
	boolean bStepCheck;
	String sStatus;
	
	DataFunctions data = new DataFunctions();
	Reporting repo = new Reporting();
	
	public void SetupWebDriver(String sBrowser)
	{
		//Initialize WebDriver
		switch(sBrowser.toUpperCase().trim())
		{
		case "CHROME"	:	System.setProperty("browser","chrome");
							WebDriverManager.chromedriver().setup();		
							driver = new ChromeDriver();
							break;
		case "FIREFOX"	:	System.setProperty("browser","firefox");
							WebDriverManager.firefoxdriver().setup();		
							driver = new FirefoxDriver();
							break;
		case "IE"		:	System.setProperty("browser","ie");
							WebDriverManager.iedriver().setup();		
							driver = new InternetExplorerDriver();
							break;
		}
		WebDriverRunner.setWebDriver(driver);
	}
	
	public void Setup(String sBrowser) throws Exception
	{
		SetupWebDriver(sBrowser);
		
		repo.setExtent(repo.initializeExtentReports("Test"));
	}
	
	public void CloseBrowser()
	{
		//Close Browser
		driver.close();
				
		//Close WebDriver
		driver.quit();
	}
	
	public void NavigateToURL(String sURL)
	{
		//Navigate to the URL
		driver.get(sURL);
		
		//Maximize Browser window	
		driver.manage().window().maximize();
	}
		
	public void ClickObject(String sObject, String sName)
	{
		switch(sObject.toUpperCase().trim())
		{
		case "XPATH":
			driver.findElement(By.xpath(sName)).click();
			break;
		case "LINKTEXT":
			driver.findElement(By.linkText(sName)).click();
			break;
		case "NAME":
			driver.findElement(By.name(sName)).click();
			break;
		case "ID":
			driver.findElement(By.id(sName)).click();
			break;
		case "PARTIALLINKTEXT":
			driver.findElement(By.partialLinkText(sName)).click();
			break;
		case "CLASS":
			driver.findElement(By.className(sName)).click();
			break;
		}
	}
	
	public void EnterText(String sObject, String sName, String sText)
	{
		switch(sObject.toUpperCase().trim())
		{
		case "XPATH":
			driver.findElement(By.xpath(sName)).sendKeys(sText);
			break;
		case "LINKTEXT":
			driver.findElement(By.linkText(sName)).sendKeys(sText);
			break;
		case "NAME":
			driver.findElement(By.name(sName)).sendKeys(sText);
			break;
		case "ID":
			driver.findElement(By.id(sName)).sendKeys(sText);
			break;
		case "PARTIALLINKTEXT":
			driver.findElement(By.partialLinkText(sName)).sendKeys(sText);
			break;
		case "CLASS":
			driver.findElement(By.className(sName)).sendKeys(sText);
			break;
		}
	}
	
	public void WaitTime(int iTime) throws Exception
	{
		Thread.sleep(iTime * 1000);
	}
	
	public static boolean waitForElement(String sObject, String sIdentifier)
	{
		boolean exists = false;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		try 
		{
			switch(sObject.toUpperCase().trim())
			{
			case "XPATH":
				exists = driver.findElements(By.xpath(sIdentifier)).size() > 0;
				break;
			case "LINKTEXT":
				exists = driver.findElements(By.linkText(sIdentifier)).size() > 0;
				break;
			case "NAME":
				exists = driver.findElements(By.name(sIdentifier)).size() > 0;
				break;
			case "ID":
				exists = driver.findElements(By.id(sIdentifier)).size() > 0;
				break;
			case "PARTIALLINKTEXT":
				exists = driver.findElements(By.partialLinkText(sIdentifier)).size() > 0;
				break;
			case "CLASS":
				exists = driver.findElements(By.className(sIdentifier)).size() > 0;
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println("Object not found");
		}

		return exists;
	}
	
	public void ReportStepCheckIfEnabled(String sObject, String sIdentifier, String sReportMessage)
	{
		bStepCheck = checkifElementIsEnabled(sObject, sIdentifier);
		
		if(bStepCheck)
		{
			sStatus = "Passed";
		}
		else
		{
			sStatus = "Failed";
		}
		System.out.println(sReportMessage+" - "+sStatus);
	}
	
	public boolean checkifElementIsEnabled(String sObject, String sIdentifier)
	{
		boolean exists = false;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		try 
		{
			switch(sObject.toUpperCase().trim())
			{
			case "XPATH":
				exists = driver.findElement(By.xpath(sIdentifier)).isEnabled() == true;
				break;
			case "LINKTEXT":
				exists = driver.findElement(By.linkText(sIdentifier)).isEnabled() == true;
				break;
			case "NAME":
				exists = driver.findElement(By.name(sIdentifier)).isEnabled() == true;
				break;
			case "ID":
				exists = driver.findElement(By.id(sIdentifier)).isEnabled() == true;
				break;
			case "PARTIALLINKTEXT":
				exists = driver.findElement(By.partialLinkText(sIdentifier)).isEnabled() == true;
				break;
			case "CLASS":
				exists = driver.findElement(By.className(sIdentifier)).isEnabled() == true;
				break;
			}
		}
		catch(Exception e)
		{
			System.out.println("Object not found");
		}

		return exists;
	}
	
	public void SelectDropDownVisibleText(String sObject, String sIdentifier, String sText)
	{
		switch(sObject.toUpperCase().trim())
		{
		case "XPATH":
			Select drp1 = new Select (driver.findElement(By.xpath(sIdentifier)));
			drp1.selectByVisibleText(sText);
			break;
		case "LINKTEXT":
			Select drp2 = new Select (driver.findElement(By.linkText(sIdentifier)));
			drp2.selectByVisibleText(sText);
			break;
		case "NAME":
			Select drp3 = new Select (driver.findElement(By.name(sIdentifier)));
			drp3.selectByVisibleText(sText);
			break;
		case "ID":
			Select drp4 = new Select (driver.findElement(By.id(sIdentifier)));
			drp4.selectByVisibleText(sText);
			break;
		case "PARTIALLINKTEXT":
			Select drp5 = new Select (driver.findElement(By.partialLinkText(sIdentifier)));
			drp5.selectByVisibleText(sText);
			break;
		case "CLASS":
			Select drp6 = new Select (driver.findElement(By.className(sIdentifier)));
			drp6.selectByVisibleText(sText);
			break;
		}
	}
	
	public void PageDown()
	{
		//Scroll using Page Down
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
	}

	public WebDriver getWebdriver() {
		// TODO Auto-generated method stub
		return driver;
	}
	
	public void MultiSelect(String sObject, String sText)
	{
		switch(sObject.toUpperCase().trim())
		{
		case "RADIO":
			driver.findElement(By.xpath("//input[@type='radio' and @value='"+ sText +"']")).click();
			break;
		case "CHECKBOX":
			driver.findElement(By.xpath("//input[@type='checkbox' and @value='"+ sText +"']")).click();
			break;
			
		}
	}
	
	public void FileUpload(String sPath)
	{
		WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));
		fileInput.sendKeys(sPath);
	}
	
	public String GetText(String sObject, String sName)
	{
		String sText = null;
		
		switch(sObject.toUpperCase().trim())
		{
		case "XPATH":
			sText = driver.findElement(By.xpath(sName)).getText();
			break;
		case "LINKTEXT":
			sText = driver.findElement(By.linkText(sName)).getText();
			break;
		case "NAME":
			sText = driver.findElement(By.name(sName)).getText();
			break;
		case "ID":
			sText = driver.findElement(By.id(sName)).getText();
			break;
		case "PARTIALLINKTEXT":
			sText = driver.findElement(By.partialLinkText(sName)).getText();
			break;
		case "CLASS":
			sText = driver.findElement(By.className(sName)).getText();
			break;
		}
		
		return sText;
	}
}
