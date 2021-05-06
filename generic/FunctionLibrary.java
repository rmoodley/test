import org.apache.poi.ss.usermodel.*;

public class FunctionLibrary {
	
	boolean bStepCheck;
	String sStatus;
	
	public static String sURL;
	public static String sUsername;
	public static String sPassword;
	public static int iIteration;
	public static Sheet sheetGlobal;
	
	UtilityFunctions utils = new UtilityFunctions();
	Reporting repo = new Reporting();
	
	public void NavigateToURL(Sheet sheet, int i) throws Exception
	{
		//Set iIteration to i
		iIteration = i;
		
		//Set sheetGlobal to sheet
		sheetGlobal = sheet;
		
		sURL = DataFunctions.getExcelCellData("URL", i, sheet);
		
		//Navigate to the URL
		utils.NavigateToURL(sURL);
		
		//Validate Navigation to URL
		repo.ReportStep("name", "userName", "Navigate to URL");
	}
	
	public void Login(Sheet sheet, int i) throws Exception
	{
		sUsername = DataFunctions.getExcelCellData("Username", i, sheet);
		sPassword = DataFunctions.getExcelCellData("Password", i, sheet);
		
		// Capture User name
		utils.EnterText("name", "userName", sUsername);
		
		// Capture the Password
		utils.EnterText("name", "password", sPassword);
		
		// Click the Submit Button
		utils.ClickObject("name","submit");
		
		//Validate Login
		repo.ReportStep("linktext", "SIGN-OFF", "Login in to DemoTours");
	}
	
	public void ClickFlightLink() throws Exception
	{
		utils.ClickObject("linktext","Flights");
		
		//Validate Login
		repo.ReportStep("xpath", "//input[@value='roundtrip']", "Click Flight Link");
	}
	
	public void SelectFlight(Sheet sheet, int i) throws Exception
	{
		//Select Flight Type
		utils.ClickObject("xpath","//input[@value='roundtrip']");
		
		//Select from the 'Passengers' DropDown
		utils.SelectDropDownVisibleText("name", "passCount", DataFunctions.getExcelCellData("Passengers", i, sheet));
		
		//Select from the 'DepartingFrom' DropDown
		utils.SelectDropDownVisibleText("name", "fromPort", DataFunctions.getExcelCellData("DepartingFrom", i, sheet));
		
		//Select from the 'DepartingMonth' DropDown
		utils.SelectDropDownVisibleText("name", "fromMonth", DataFunctions.getExcelCellData("DepartingMonth", i, sheet));
		
		//Select from the 'DepartingDay' DropDown
		utils.SelectDropDownVisibleText("name", "fromDay", DataFunctions.getExcelCellData("DepartingDay", i, sheet));
		
		//Page Down
		utils.PageDown();
		
		//Select from the 'ArrivingTo' DropDown
		utils.SelectDropDownVisibleText("name", "toPort", DataFunctions.getExcelCellData("ArrivingTo", i, sheet));
		
		//Select from the 'ArrivalMonth' DropDown
		utils.SelectDropDownVisibleText("name", "toMonth", DataFunctions.getExcelCellData("ArrivalMonth", i, sheet));
		
		//Select from the 'ArrivalDay' DropDown
		utils.SelectDropDownVisibleText("name", "toDay", DataFunctions.getExcelCellData("ArrivalDay", i, sheet));
		
		//Select Service Class
		utils.ClickObject("xpath", "//input[@value='"+ DataFunctions.getExcelCellData("ServiceClass", i, sheet) +"']");
		
		//Select from the 'Airline' DropDown
		utils.SelectDropDownVisibleText("name", "airline", DataFunctions.getExcelCellData("Airline", i, sheet));
		
		//Click Continue Button
		utils.ClickObject("name", "findFlights");
		
		
		//Validate Login
		repo.ReportStep("xpath", "/html/body/div[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[2]/td/a/im", "Select Flight Type");
	}
}
