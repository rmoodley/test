//Excel Classes
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;//Use for .xlsx file extensions
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.apache.poi.ss.usermodel.Cell;
import java.io.*;

//Date Time Classes
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DataFunctions
{
	//Declare variables
	public static String sDefaultPath;
	public static Sheet automationSheet;
	static FileInputStream inputStream;
	static String globalFilePath;
	static String globalSheetName;
	
	
	public Sheet readExcel(String filePath, String sheetName) throws Exception
	{
		//Get Workspace path
		sDefaultPath = System.getProperty("user.dir");
	    sDefaultPath = sDefaultPath.replace("batch", "");
	    globalFilePath = filePath;
	    globalSheetName = sheetName;
		
		//create an object of FileInputStream class to read excel file
		inputStream = new FileInputStream(new File(sDefaultPath + filePath));
		
		//Set Workbook to null
		@SuppressWarnings("resource")
		Workbook mentorshipWorkbook = new XSSFWorkbook(inputStream);
		//Read the sheet inside of the workbook
		automationSheet = mentorshipWorkbook.getSheet(sheetName);
		
		return automationSheet;
	}
	
	public static String getExcelCellData(String sColumn, int iRow, Sheet sheet) throws Exception
	{
		String sValue = null;
		String sColValue = null;
		
		Row row = sheet.getRow(0);
		for(int i=0;i<columnCount(sheet);i++)
		{
			sColValue = row.getCell(i).getStringCellValue().trim();
			
			if(sColValue.equals(sColumn))
			{
				Row row1 = sheet.getRow(iRow);
				Cell cell = row1.getCell(i);
				DataFormatter formatter = new DataFormatter();
				sValue = formatter.formatCellValue(cell);
				break;
			}
			
		}
		return sValue;
	}
	
	public static int columnCount(Sheet sheet)
	{
		int iCount = 0;
		
		iCount = sheet.getRow(0).getLastCellNum();
				
		return iCount;
	}
	
	public static int rowCount(Sheet sheet) throws Exception
	{
		int count = 0;
		
		count = sheet.getPhysicalNumberOfRows();
		
		return count;
	}
	
	public static void writeExcel(String sColumn, int iRow, Sheet sheet, String sMessage) throws Exception
	{	
		inputStream = new FileInputStream(new File(sDefaultPath + globalFilePath));
		Workbook mentorshipWorkbook = new XSSFWorkbook(inputStream);
		automationSheet = mentorshipWorkbook.getSheet(globalSheetName);
		
		Row row = automationSheet.getRow(0);

        for (int c = 0;c< columnCount(automationSheet); c++)
        {
            for( int i=0; i<row.getLastCellNum();i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(sColumn))
                {
                    Row row1 = automationSheet.getRow(iRow);
                    Cell cell = row1.createCell(i);
                    cell.setCellValue(sMessage);
                    break;
                }
            }
        }
        //Close InputStream
        inputStream.close();
        
        //create an object of FileInputStream class to read excel file
        FileOutputStream outputStream = new FileOutputStream(new File(sDefaultPath + globalFilePath));
        
        //Write changes to Spreadsheet and Save
        mentorshipWorkbook.write(outputStream);
        
        //Close OutputStream
        outputStream.close();
	}
	
	public static String getTimeStamp()
	{
		//Get Current Time stamp
        return(new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()));
	}

	public String API_Elements(String sBody, String sField) throws Exception
	{

		//Check sBody for '[', and remove '[' and ']'
		if(sBody.charAt(0)=='[')
    	{
			//Remove '[' and ']'
    		sBody = sBody.substring(1, sBody.length()-1);
    	}
    	
		//Create Object
    	Object obj=JSONValue.parse(sBody);
    	
    	//Create JSON Object
    	JSONObject jsonObject = (JSONObject) obj;    
        
        //Return the value to main function
        return jsonObject.get(sField).toString();  
	}
	
	public int CountNumberOfOccuringChar(String sTemp, char sChar)
	{
		//Set iCount to 0
		int iCount = 0;
		
		for(int i=0;i<sTemp.length();i++)
	    {
			if(sTemp.charAt(i) == sChar)
			{
				//Increment iCount
				iCount++;
			}
	    }
		//Return iCount to main function
		return iCount;
	}
	
	public boolean DataCompareAPI(String sTestActual, String sTestExpected)
	{
		boolean bDataCheck = false;
		
		//Check sBody for '[', and remove '[' and ']'
		if(sTestActual.charAt(0)=='[')
    	{
			//Remove '[' and ']'
			sTestActual = sTestActual.substring(1, sTestActual.length()-1);
			
			//Split string and store in the array
			String [] arr = sTestActual.split("\",\"");
			
			//Run through the elements of the array
			for(int i=0;i<=arr.length-1;i++)
			{
				//Replace '"' with ''
				arr[i] = arr[i].replace("\"", "");
				
				//Check if Actual equals Expected
				if(arr[i].equalsIgnoreCase(sTestExpected))
				{
					//Update bDataCheck
					bDataCheck = true;
				}
			}
    	}
		else
		{
			//Check if Actual equals Expected
			if(sTestActual.equalsIgnoreCase(sTestExpected))
			{
				//Update bDataCheck
				bDataCheck = true;
			}
		}
			
		//Return bDataCheck to main function
		return bDataCheck;
	}

}