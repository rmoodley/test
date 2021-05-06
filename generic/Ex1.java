import org.testng.annotations.*;


public class Ex1 {
	
	UtilityFunctions utils = new UtilityFunctions();
	
	//@Test
	public void Test() throws Exception
	{
		//Open Chrome browser
		utils.SetupWebDriver("Chrome");
		
		//Wait
		utils.WaitTime(2);
		
		//Navigate to URL
		utils.NavigateToURL("http://demo.guru99.com/test/newtours/");
		
		//Wait
		utils.WaitTime(2);
		
		//Expected Title
		String sExpectedTitle = "Welcome: Mercury Tours";
		
		//Actual Title
		String sActualTitle = utils.getWebdriver().getTitle();
		
		//Compare
		if(sActualTitle.equals(sExpectedTitle))
		{
			//Print to Console
			System.out.println("Title Matches, Test Passed");
		}
		else
		{
			//Print to Console
			System.out.println("Title does not match, Test Failed");
		}
		
		//Close Chrome Browser		
		utils.CloseBrowser();
	}
	
	//@Test
	public void Test_1() throws Exception
	{
		//Open Chrome browser
		utils.SetupWebDriver("Chrome");
		
		//Wait
		utils.WaitTime(2);
		
		//Navigate to URL
		utils.NavigateToURL("https://demoqa.com/automation-practice-form");
		
		//Wait
		utils.WaitTime(2);
		
		//Enter the First Name
		//utils.getWebdriver().findElement(By.id("firstName")).sendKeys("Rishen");
		utils.EnterText("ID", "firstName", "Rishen");
		
		//Enter the Last Name
		//utils.getWebdriver().findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys("Moodley");
		utils.EnterText("xpath", "//input[@placeholder='Last Name']", "Moodley");

		//Wait
		utils.WaitTime(2);
		
		//Click Button
		//utils.getWebdriver().findElement(By.id("submit")).click();
		utils.ClickObject("ID", "submit");
		
		//Close Chrome Browser		
		utils.CloseBrowser();
		
	}
	
	//@Test
	public void Test_2() throws Exception
	{
		//Open Chrome browser
		utils.SetupWebDriver("Chrome");
		
		//Wait
		utils.WaitTime(2);
		
		//Navigate to URL
		utils.NavigateToURL("https://demoqa.com/automation-practice-form");
		
		//Wait
		utils.WaitTime(2);
	
		//Page down
		utils.PageDown();
		
		//Wait
		utils.WaitTime(2);
		
		//Click on Book Store Application
		//utils.getWebdriver().findElement(By.xpath("//*[text()='Book Store Application']")).click();
		utils.ClickObject("XPATH", "//*[text()='Book Store Application']");

		//Wait
		utils.WaitTime(2);	
		
		//Click on Book Store
		//utils.getWebdriver().findElement(By.xpath("//*[text()='Book Store']")).click();
		utils.ClickObject("XPATH", "//*[text()='Book Store']");

		//Wait
		utils.WaitTime(2);	
		
		//Click on the First book using Partial Link Text
		//utils.getWebdriver().findElement(By.partialLinkText("Git")).click();
		utils.ClickObject("PARTIALLINKTEXT", "Git");

		//Wait
		utils.WaitTime(2);	
		
		//Back to Book Store
		//utils.getWebdriver().findElement(By.id("addNewRecordButton")).click();
		utils.ClickObject("ID", "addNewRecordButton");

		//Wait
		utils.WaitTime(2);	
		
		//Click on another book using Link Text
		//utils.getWebdriver().findElement(By.linkText("Learning JavaScript Design Patterns")).click();
		utils.ClickObject("LINKTEXT", "Learning JavaScript Design Patterns");

		//Wait
		utils.WaitTime(2);	
		
		//Close Chrome Browser		
		utils.CloseBrowser();
	}
	
	//@Test
	public void Test_3() throws Exception
	{
		//Open Chrome browser
		utils.SetupWebDriver("Chrome");
		
		//Wait
		utils.WaitTime(2);
		
		//Navigate to URL
		utils.NavigateToURL("http://demo.automationtesting.in/Register.html");
		
		//Wait
		utils.WaitTime(2);
		
		//Enter First Name
		utils.EnterText("xpath", "//input[@placeholder='First Name']", "Rishen");
		
		//Enter Last Name
		utils.EnterText("xpath", "//input[@placeholder='Last Name']", "Moodley");
		
		//Enter Address
		utils.EnterText("xpath", "//*[@id=\"basicBootstrapForm\"]/div[2]/div/textarea", "Test");
		
		//Enter Email Address
		utils.EnterText("xpath", "//input[@type='email']", "test@test.com");
		
		//Enter Phone Number
		utils.EnterText("xpath", "//input[@type='tel']", "0123456789");
		
		//Select Gender
		//utils.ClickObject("xpath", "//input[@type='radio' and @value='Male']");
		utils.MultiSelect("Radio", "Male");
		
		//Select Hobbies
		//utils.ClickObject("xpath", "//input[@type='checkbox' and @value='Movies']");
		utils.MultiSelect("checkbox", "Movies");
		
		//Select Language/s
		utils.ClickObject("xpath", "//*[@id=\"msdd\"]");
		utils.WaitTime(1);
		utils.ClickObject("xpath", "//*[@id=\"basicBootstrapForm\"]/div[7]/div/multi-select/div[2]/ul/li[8]/a");
		
		//Select Skills
		utils.SelectDropDownVisibleText("id", "Skills", "Adobe InDesign");
		
		//Select Country
		utils.SelectDropDownVisibleText("id", "countries", "South Africa");
		
		//Select D.O.B Year
		utils.SelectDropDownVisibleText("id", "yearbox", "2000");
		
		//Select D.O.B Month
		utils.SelectDropDownVisibleText("xpath", "//*[@placeholder='Month']", "May");
		
		//Select D.O.B Day
		utils.SelectDropDownVisibleText("id", "daybox", "1");
		
		//Enter Password
		utils.EnterText("id", "firstpassword", "Test123");
		
		//Confirm Password
		utils.EnterText("id", "secondpassword", "Test123");
		
		//Upload Document - *Not required Bonus*
		utils.FileUpload("‪C:\\Users\\rmoodley\\workspace\\maven-project\\screenshots\\ExtentLogFail20210304_174722.png");
		
		//Wait
		utils.WaitTime(2);	
		
		//Close Chrome Browser		
		utils.CloseBrowser();
	}
	
	@Test
	public void Test_4() throws Exception
	{
		//Open Chrome browser
		utils.SetupWebDriver("Chrome");
		
		//Wait
		utils.WaitTime(2);
		
		//Navigate to URL
		utils.NavigateToURL("http://demo.guru99.com/test/newtours/register.php");
		
		//Wait
		utils.WaitTime(2);
		
		//Enter First Name
		utils.EnterText("xpath", "//*[contains(text(),'First')]/following::input[1]", "Rishen");
		
		
		//Enter Last Name
		utils.EnterText("xpath", "//*[contains(text(),'Last')]/following::input[1]", "Moodley");
		
		//Enter Phone Number
		utils.EnterText("xpath", "//*[contains(text(),'Phone')]/following::input[1]", "123456789");
		
		//Enter Email Address
		utils.EnterText("xpath", "//*[contains(text(),'Email')]/following::input[1]", "test@test.com");
		
		//Scroll
		utils.PageDown();
		
		//Enter Address
		utils.EnterText("xpath", "//*[contains(text(),'Address')]/following::input[1]", "123 Street");
		
		//Enter City
		utils.EnterText("xpath", "//*[contains(text(),'City')]/following::input[1]", "Johannesburg");
		
		//Enter State/Province
		utils.EnterText("xpath", "//*[contains(text(),'State/Province')]/following::input[1]", "Gauteng");
		
		//Enter Postal Code
		utils.EnterText("xpath", "//*[contains(text(),'Postal')]/following::input[1]", "1234");
		
		//Scroll
		utils.PageDown();
		
		//Enter Postal Code
		utils.SelectDropDownVisibleText("xpath", "//*[contains(text(),'Country')]/following::select[1]", "SOUTH AFRICA");
		
		//Enter User Name
		utils.EnterText("xpath", "//*[contains(text(),'User')]/following::input[1]", "test");
		
		//Enter Password
		utils.EnterText("xpath", "//*[contains(text(),'Password')]/following::input[1]", "1234");
		
		//Enter Confirm Password
		utils.EnterText("xpath", "//*[contains(text(),'Confirm')]/following::input[1]", "1234");
		
		//Click Submit Button
		utils.ClickObject("xpath", "//*[@type='submit']");
		
		//Wait
		utils.WaitTime(2);
		
		//Get User Name Message
		System.out.println(utils.GetText("xpath", "//*[contains(text(),'Note: Your user name is')]"));
		
		//Click Submit Button
		utils.ClickObject("xpath", "//*[contains(text(),'SIGN-OFF')]");
		
		//Checking if 'SIGN-ON' is available
		utils.waitForElement("xpath", "//*[contains(text(),'SIGN-ON')]");
		
		//Wait
		utils.WaitTime(2);	
		
		//Close Chrome Browser		
		utils.CloseBrowser();
	}
}
