package exampleTests;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilitiesPackage.Excel;

public class DataDrivenExample {

	
	@Test(dataProvider="getExcelData")
	public void DataDrivenSampleTest(String val1, String val2, String val3, String val4, String val5)
	{		
		System.out.println("val1: " + val1 + ", val2: "+ val2 + ", val3: " + val3 + ", val4: " + val4 + ", val5: " + val5);
	}
	
	@DataProvider
	public Object[][] getExcelData()
	{
		String workingDir = System.getProperty("user.dir");		   
		List<List<String>> SheetRows = Excel.readExcelFile(workingDir + "/TestData/SampleDataFile1.xls",0);
		
		String[][] twoDimensionArray = Excel.ListToTwoDimensionArray(SheetRows);
						
		return twoDimensionArray;
	}
	
}
