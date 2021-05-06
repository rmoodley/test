//TestNG
import org.testng.annotations.*;

//Excel
import org.apache.poi.ss.usermodel.*;

//RestAssured
import io.restassured.*;
import io.restassured.response.*;

public class API {
	
	public int iRow;
	public Sheet sheet;
	public String sRun;
	public String timeStamp;
	String sMethod;
	String sBody;
	String sField;
	String sElement;
	String sCompare;
	Response response;
	
	DataFunctions data = new DataFunctions();
	Reporting repo = new Reporting();
	
	@BeforeMethod
	public void beforeMethod() throws Exception
	{
		//Get Sheet
		sheet = data.readExcel("\\TestData\\API.xlsx","Sheet1");
		
		//Initialize Extent report with applicable name
		repo.setExtent(repo.initializeExtentReports("RestAssured Test Updated"));
	}
	
	@Test
	public void GetAPI() throws Exception
	{
		//Get Number of Rows from Excel
		iRow = DataFunctions.rowCount(sheet);
		
		for(int x=1; x<iRow; x++)
		{
			//Check Run Status
			sRun = DataFunctions.getExcelCellData("Run", x, sheet);
			
			if(sRun.toUpperCase().equals("YES"))
			{
				System.out.println("**** Starting Iteration: "+ x+" ****");
				
				//Wait 1 Second
				Thread.sleep(1000);
				
				//Creates the name of the Extent Report
				repo.setExtentTest(repo.getExtent().createTest(DataFunctions.getExcelCellData("TestName", x, sheet)));
				
				//Get BaseURI
				RestAssured.baseURI = DataFunctions.getExcelCellData("BaseURI", x, sheet);
				
				//Get sMethod information
		        sMethod = DataFunctions.getExcelCellData("Method", x, sheet);
		        
		        //Check which method to run
		        switch(sMethod.toUpperCase())
		        {
		        	case "GET":
		        		//Get Path information
						response = RestAssured.get(DataFunctions.getExcelCellData("GET", x, sheet));
		        		break;
		        	case "POST":
		        		//Post Path information
						response = RestAssured.get(DataFunctions.getExcelCellData("POST", x, sheet));
		        	break;
		        }	
				
				//Assign StatusCode
				int iStatusCode = response.getStatusCode();
				String sStatusCode = Integer.toString(iStatusCode);
				
				//Write Status Code to Excel
				DataFunctions.writeExcel("StatusCode", x, sheet, sStatusCode);
				
				//Write Body to Excel
				DataFunctions.writeExcel("Body", x, sheet, response.getBody().asString());
		        
		        //Get sField information
		        sField = DataFunctions.getExcelCellData("Field", x, sheet);
				
		        //Get API Elements
		        sElement = data.API_Elements(response.getBody().asString(), sField);
		        
		        //Get sCompare information
		        sCompare = DataFunctions.getExcelCellData("TextCheck", x, sheet);
		          
		        
				//Step 1 - Status Code
				if(repo.ReportStepAPIContains("200", sStatusCode, "Step 1 - Status Code", x))
				{
					//Step 2 - Text is contained within Element
					repo.ReportStepAPIContains(sCompare, sElement, "Step 2 - Text is contained within Element", x);
				}
				else
				{
					System.out.println("**** Status Code does not match ****");
				}
				
				
				System.out.println("**** Ending Iteration: "+ x+" ****");
			}
		}
	}
	
	@AfterMethod
	public void afterMethod() throws Exception
	{
		//Close and Save Extent Report
		repo.getExtent().flush();
	}
}
