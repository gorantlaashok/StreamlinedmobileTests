package utility;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.testng.ITestResult;

import webDriverExtensionPage.Page;

/// <summary>
/// This class is created to fill the need for Soft Asserts. 
/// Check class is data structure to hold a single check.
/// </summary>
class Check
{
	public String CheckName;
    public String ErrorMessage;
    public Boolean Status;

    public Check()
    {
        CheckName = "";
        ErrorMessage = "";
        Status = false;
    }

    public Check(String _CheckName, String _ErrorMessage, Boolean _Status)
    {
        CheckName = _CheckName;
        ErrorMessage = _ErrorMessage;
        Status = _Status;
    }
}

/// <summary>
/// This class uses a List of Check class and boolean Pass. During tests
/// a check can be added at any given time from anywhere using the Add method
/// All checks added are stored as a list. At any point if a check is added that has a status of false
/// the Pass variable is set to false to indicate that at least one check has failed.
/// At the end of the test case we can print all of our checks and use a final assert to pass/fail the test.
/// NOTE: The CheckUtil must be reset(ResetChecks) in between tests, otherwise it will keep holding the checks from the previous test.
/// </summary>
public class CheckUtil {

	private static List<Check> Checks;
    private static Boolean Pass;
            
    public static void initializeChecks()
    {
    	 Checks = new ArrayList<Check>();
         Pass = true;
    }
    
    public static void AddCheck(Check aCheck)
    {
        Checks.add(aCheck);
        if (!aCheck.Status)
        {
            Pass = false;
            Page.takeScreenShot(aCheck.CheckName);
        }
    }

    public static void AddCheck(String _Name, String _ErrorMessage, Boolean _Status)
    {
        Checks.add(new Check(_Name, _ErrorMessage, _Status));
        if (!_Status)
        {
            Pass = _Status;            
            Page.takeScreenShot(_Name);
        }
    }
    
    public static void AddCheck(String _Name, String _ErrorMessage, Boolean _Status, Boolean _TakeScreenShot)
    {
        Checks.add(new Check(_Name, _ErrorMessage, _Status));
        if (!_Status)
        {
            Pass = _Status;
            if(_TakeScreenShot)
            	Page.takeScreenShot(_Name);
        }
    }

    public static Boolean GetStatus()
    {
        return Pass;
    }

    public static void ResetChecks()
    {    	
        Checks.clear();
        Pass = true;
    }

    public static void PrintChecks(Boolean _PrintFailOnly)
    {
        int lineNumber = 1;
        for(Check aCheck : Checks)
        {
            if (aCheck.Status)
            {
                if (!(_PrintFailOnly))
                    System.out.println(String.format("%1$04d) %2$s %3$s", lineNumber, "[PASS]", aCheck.CheckName));
            }
            else
            {  
            	System.out.println(String.format("%1$04d) %2$s %3$s %4$s", lineNumber, "[FAIL]", aCheck.CheckName, aCheck.ErrorMessage));                
            }
            lineNumber++;
        }            
    }

    public static void WriteToFile(Boolean _PrintFailOnly, ITestResult result)
    {
        try
        {
            String fileName = result.getName();
            java.util.Date myDate = new Date();             
            SimpleDateFormat formatter = new SimpleDateFormat("hhmmss");
            fileName = "logs\\" + fileName + "-" + formatter.format(myDate) + ".log";
            if (Checks.size() > 0)
            {                
            	 FileWriter fw = new FileWriter(fileName);
            	 PrintWriter pw = new PrintWriter(fw);
                {
                    int lineNumber = 1;
                    for(Check aCheck : Checks)
                    {
                        if (aCheck.Status)
                        {
                            if (!(_PrintFailOnly))
                                pw.println(String.format("%1$04d) %2$s %3$s", lineNumber, "[PASS]", aCheck.CheckName));
                        }
                        else
                        {
                            pw.println(String.format("%1$04d) %2$s %3$s %4$s", lineNumber, "[FAIL]", aCheck.CheckName, aCheck.ErrorMessage));
                        }
                        lineNumber++;
                    }
                }
                pw.close();
                pw.flush();                
            }
        }catch (Exception e)
        {
            System.out.println("Error CheckUtil.WriteToFile() " + e.getMessage());
        }
    }   
    
}	
