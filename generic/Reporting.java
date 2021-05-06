import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Reporting {
	
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	String sReportStatus;
	String sScreenshot;
	String fileName;
	
	DataFunctions data = new DataFunctions();
	
	public void setExtent(ExtentReports extentTest)
    {
        extent = extentTest;
    }

    public ExtentTest getExtentTest()
    {
        return extentTest;
    }
    
    public void setExtentTest(ExtentTest extentTestTemp)
    {
        extentTest = extentTestTemp;
    }
    
    public ExtentReports getExtent()
    {
        return extent;
    }
	
	public ExtentReports initializeExtentReports(String sReportName)
	{
		htmlReporter = new ExtentSparkReporter("report/"+sReportName+".html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		//htmlReporter.setAppendExisting(true);
		return extent;
	}
	
	public void ReportStep(String sObject, String sIdentifier, String sReportMessage) throws Exception
	{				
		//Get Screenshot Status
		sScreenshot = DataFunctions.getExcelCellData("Screenshot", FunctionLibrary.iIteration, FunctionLibrary.sheetGlobal);
		
		if(UtilityFunctions.waitForElement(sObject, sIdentifier))
		{
			ExtentLogPass(sReportMessage);
			sReportStatus = "Passed";
			
			if(sScreenshot.toUpperCase().equals("ALWAYS"))
			{
				fileName = takeScreenshot("ExtentLogPass");
				getExtentTest().pass(sReportMessage, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
			}
		}
		else
		{
			ExtentLogFail(sReportMessage);
			sReportStatus = "Failed";
			
			if(!sScreenshot.toUpperCase().equals("NEVER"))
			{
				fileName = takeScreenshot("ExtentLogFail");
				getExtentTest().fail(sReportMessage, MediaEntityBuilder.createScreenCaptureFromPath(fileName).build());
			}
		}
		System.out.println(sReportMessage+" - "+sReportStatus);
	}
	
	public void ExtentLogPass(String sReportPassMessage)
	{
		getExtentTest().pass(sReportPassMessage);
	}
	
	public void ExtentLogFail(String sReportFailMessage)
	{
		getExtentTest().fail(sReportFailMessage);
	}
	
	public String takeScreenshot(String FileName) throws Exception
	{
        String fileName="Empty";
        try
        {
            String sDefaultPath = System.getProperty("user.dir");
            sDefaultPath = sDefaultPath.replace("batch", "");
            File scrFile = ((TakesScreenshot)UtilityFunctions.driver).getScreenshotAs(OutputType.FILE);

            sDefaultPath = sDefaultPath.replace("/","\\");

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            fileName =sDefaultPath+"\\screenshots\\"+FileName+timeStamp+".png";

            FileUtils.copyFile(scrFile, new File(fileName));
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        return fileName;
	}
	
	public boolean ReportStepAPI(String sTestExpected, String sTestActual, String sReportMessage) throws Exception
	{
		boolean bStepCheck = false;
		
		if(sTestExpected.toUpperCase().equals(sTestActual.toUpperCase()))
		{
			ExtentLogPass(sReportMessage);
			sReportStatus = "Passed";
			bStepCheck = true;
		}
		else
		{
			ExtentLogFail(sReportMessage);
			sReportStatus = "Failed";
		}
		System.out.println(sReportMessage+" - "+sReportStatus);
		
		return bStepCheck;
	}
	
	public boolean ReportStepAPIContains(String sTestExpected, String sTestActual, String sReportMessage, int x) throws Exception
	{
		boolean bStepCheck = false;
		
		//Check if Actual equals Expected
		if(data.DataCompareAPI(sTestActual, sTestExpected))
		{
			//Report the Pass
			ExtentLogPass(sReportMessage + " | Test Expected: " + sTestExpected + " | Test Actual: " + sTestActual + " |");
			sReportStatus = "Passed";
			bStepCheck = true;
			
			DataFunctions.writeExcel("Status", x, DataFunctions.automationSheet, "Passed_"+DataFunctions.getTimeStamp());
		}
		else
		{
			//Report the Fail
			ExtentLogFail(sReportMessage + " | Test Expected: " + sTestExpected + " | Test Actual: " + sTestActual + " |");
			sReportStatus = "Failed";
			
			DataFunctions.writeExcel("Status", x, DataFunctions.automationSheet, "Failed_"+DataFunctions.getTimeStamp());
		}
		System.out.println(sReportMessage+" - "+sReportStatus);
		
		
		return bStepCheck;
	}
}