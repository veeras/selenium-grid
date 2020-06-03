package testCases;

//import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import operation.ReadObject;
import operation.UIOperation;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import excelExportAndFileIO.ReadGuru99ExcelFile;


import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;



/**
 * THIS IS THE EXAMPLE OF KEYWORD DRIVEN TEST CASE
 *
 */
public class ExecuteTest {
	
	static WebDriver webdriver;
	
	
	  public static void main(String[] args) throws Exception { new
	  ExecuteTest().setup(); }
	 
	
    @BeforeTest
	public void setup() throws Exception {
  //   public static void main(String[] args) throws Exception {
      // TODO Auto-generated method 
   
    //   System.setProperty("webdriver.gecko.driver", "src/main/java/testCases/geckodriver.exe");
    //   System.setProperty("webdriver.chrome.driver", "src/main/java/testCases/chromedriver.exe");
     //  WebDriver webdriver = new FirefoxDriver();
     //  WebDriver webdriver = new ChromeDriver();
        
       DesiredCapabilities dc = DesiredCapabilities.firefox();
       dc.setBrowserName("firefox");
     //  dc.setPlatform(Platform.WIN10);
		/*
		 * if (System.getProperty("browser").equals("firefox")) dc =
		 * DesiredCapabilities.firefox();
		 */
     //  String host = System.getProperty("seleniumHubHost"); 
      // String host = System.getProperty("localhost"); 
       webdriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), dc);
      
       
       
        //Read keyword sheet
     sampleTest();
	}

    @Test
    public void sampleTest() throws Exception {
    	
    	  ReadObject object = new ReadObject();
          ReadGuru99ExcelFile file = new ReadGuru99ExcelFile();
          Properties allObjects =  object.getObjectRepository();
          UIOperation operation = new UIOperation(webdriver);
          
    	   Sheet guru99Sheet = file.readExcel(System.getProperty("user.dir")+"\\","TestCase.xlsx" , "KeywordFramework");
    	      //Find number of rows in excel file
    	    	int rowCount = guru99Sheet.getLastRowNum()-guru99Sheet.getFirstRowNum();
    	    	//Create a loop over all the rows of excel file to read it
    	    	for (int i = 1; i < rowCount+1; i++) {
    	    		//Loop over all the rowss
    	    		Row row = guru99Sheet.getRow(i);
    	    		//Check if the first cell contain a value, if yes, That means it is the new testcase name
    	    		if(row.getCell(0).toString().length()==0){
    	    		//Print testcase detail on console
    	    			System.out.println(row.getCell(1).toString()+"----"+ row.getCell(2).toString()+"----"+
    	    			row.getCell(3).toString()+"----"+ row.getCell(4).toString());
    	    		//Call perform function to perform operation on UI
    	    			operation.perform(allObjects, row.getCell(1).toString(), row.getCell(2).toString(),
    	    				row.getCell(3).toString(), row.getCell(4).toString());
    	    	    }
    	    		else{
    	    			//Print the new  testcase name when it started
    	    				System.out.println("New Testcase->"+row.getCell(0).toString() +" Started");
    	    			}
    	    		}
    	
    }
    @AfterTest
    public void afterTest() {
    	System.out.println("after test");
    	webdriver.quit();
    }
}
