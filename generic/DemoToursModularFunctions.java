//TestNG
import org.testng.annotations.*;

//Excel
import org.apache.poi.ss.usermodel.*;

public class DemoToursModularFunctions {
	
	public int iRow;
	public Sheet sheet;
	public String sRun;
	public String timeStamp;
	
	UtilityFunctions utils = new UtilityFunctions();
	FunctionLibrary funcLib = new FunctionLibrary();
	DataFunctions data = new DataFunctions();
	Reporting repo = new Reporting();
	
	@BeforeMethod
	public void beforeMethod() throws Exception
	{
		//Setup WebDriver and Initialize Extent Reports
		utils.Setup("chrome");
		
		//Wait
		utils.WaitTime(2);
	}
	
	@Test()
	public void main() throws Exception {
		// TODO Auto-generated method stub
		
		//Get Sheet
		sheet = data.readExcel("\\TestData\\DemoTours.xlsx","Sheet1");
		
		//Get Number of Rows from Excel
		iRow = DataFunctions.rowCount(sheet);
		
		
		for(int x=1; x<iRow; x++)
		{
			//Check Run Status
			sRun = DataFunctions.getExcelCellData("Run", x, sheet);
			
			if(sRun.toUpperCase().equals("YES"))
			{
				System.out.println("**** Starting Iteration: "+ x+" ****");
				
				//Creates the name of the Extent Report
				repo.setExtentTest(repo.getExtent().createTest("Test - "+x));
				
				//Navigate to URL
				funcLib.NavigateToURL(sheet, x);
				
				//Login to Site
				funcLib.Login(sheet, x);
				
				//Click Flights Link
				funcLib.ClickFlightLink();
				
				//Select Flight Type
				funcLib.SelectFlight(sheet, x);
				
				System.out.println("**** Ending Iteration: "+ x+" ****");
				
				//Get Current Timestamp
		        timeStamp = DataFunctions.getTimeStamp();
				
				DataFunctions.writeExcel("Status", x, sheet, "Passed_"+timeStamp);
			}
		}
	}
	
	@AfterMethod
	public void afterMethod() throws Exception
	{
		//Wait
		utils.WaitTime(5);
		
		//Close Browser and WebDriver
		utils.CloseBrowser();
		
		//Close and Save Extent Report
		repo.getExtent().flush();
	}
}

