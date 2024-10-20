package utilities;
import org.testng.annotations.DataProvider;

public class DataPro				// DataProvider methods cannot extends
{
	//Data Provider-1
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws Exception
	{
		String path=".\\testData\\LoginTestDDT_TestData.xlsx";		// taking xl file from testData
		
		ExcelUtility xlutil=new ExcelUtility(path);				// Creating an object for ExcelUtility
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcolumns=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]=new String[totalrows][totalcolumns];	// created 2 dimensional array which can store data from xl file
		
		for(int i=1; i<=totalrows; i++)		// 1	Read data from excel storing into 2 dimensional array
		{
			for(int j=0; j<totalcolumns; j++)	// 0	i is rows & j is columns
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);	// 0, 0
			}
		}
		return logindata;	// returning 2 dimensional array
	}

}
