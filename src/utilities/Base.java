package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.google.common.io.Files;

public class Base {
	
	public static WebDriver driver;
	WebDriverWait wait;
	public static String chromedriverexecutablepath = "E:\\workspace\\CaseStudy\\src\\libraries\\chromedriver.exe";
	public static String firefoxdriverexecutablepath = "E:\\workspace\\CaseStudy\\src\\libraries\\geckodriver.exe";
	
	
	
	public static void intialize(String browsername) {
		
		if (browsername.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromedriverexecutablepath); 
			driver = new ChromeDriver();
			
		}
		else if(browsername.equalsIgnoreCase("ie")) 
			driver = new InternetExplorerDriver();
		else if(browsername.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", firefoxdriverexecutablepath); 
			driver = new FirefoxDriver();
		}
		else
			System.out.println("Please select a valid Browser");
		
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
					
		
	}
	
	public WebDriver returnDriver() {
		return driver;
	}
	
	
	public void loadURL(String url) {
		driver.get(url);
	}
	
	public String getText(By ele) {
		
		return driver.findElement(ele).getText();
	}
	
	public String getaattribute(By ele) {
		
		return driver.findElement(ele).getAttribute("value");
	}
	

	public String CurrentDateandTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss");  
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
		   
	}
	
	public void screenShot(String filename,String testMethodName) throws IOException{
		
		
		  String fileName=filename+testMethodName+"_"+CurrentDateandTime()+".jpeg";
		  System.out.println("filename="+filename);
		  File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  File screenshotName = new File (fileName);
		  FileHandler.copy(scrFile, screenshotName);
		  System.out.println("filename="+filename);
		  Reporter.log("<br><img src='"+fileName+"' height='300' width='300'/><br>");
		 
	}
	
	public void ElementWaitClick(String xpath) {
		wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).click();
	}
	
	public void ElementWait(By xpath) {
		wait=new WebDriverWait(driver, 900);
		wait.until(ExpectedConditions.visibilityOfElementLocated((xpath)));
		
	}
	
	public void ElementWaitClickable(By xpath) {
		 Actions action = new Actions(driver);
		 WebElement we = driver.findElement(xpath);
		 action.moveToElement(we).moveToElement(driver.findElement(xpath)).click().build().perform();
		
	}
	
//	public void fileMove(String source,String dest,String methodname) {
//		String fileName=dest+methodname+"_"+CurrentDateandTime()+".html";
//		File sourceFile = new File(source);
//		File destFile = new File(fileName);
//		try {
//			sourceFile.renameTo(destFile);
//		}catch (Exception e) {
//			Assert.fail(e.getMessage());
//		}
//	}
	
	public void fileMove(String source,String dest) {
		String fileName=dest+"_"+CurrentDateandTime()+".html";
		File sourceFile = new File(source);
		File destFile = new File(fileName);
		try {
			Files.copy(sourceFile,destFile);
		}catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
	
	public  Map<String,  Map<String, String>> setMapData(int index) throws IOException {
		
		String path = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"testData"+File.separator+"TestDataSheet.xlsx").replace("\\", "/") ;
//		  String path = "E:/workspace/orangehrm/src/testData/TestDataSheet.xlsx";
		  System.out.println("path"+path);
		  FileInputStream fis = new FileInputStream(path);
	
		  Workbook workbook = new XSSFWorkbook(fis);
		
		  Sheet sheet = workbook.getSheetAt(index);
		  
		  int lastRow = sheet.getLastRowNum();
		  
		  Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String,String>>();
		  
		  Map<String, String> dataMap = new HashMap<String, String>();
		  
		  //Looping over entire row
		  for(int i=0; i<=lastRow; i++){
			  
			  Row row = sheet.getRow(i);
			  
			  //1st Cell as Value
			  Cell valueCell = row.getCell(1);
				  
			  //0th Cell as Key
			  Cell keyCell = row.getCell(0);
				  
			  String value = valueCell.getStringCellValue().trim();
			  String key = keyCell.getStringCellValue().trim();
				  
			  //Putting key & value in dataMap
			  dataMap.put(key, value);
				  
			  //Putting dataMap to excelFileMap
			  excelFileMap.put("Datasheet", dataMap);
		  }
		  workbook.close();
		 //Returning excelFileMap
		return excelFileMap;
	
	}
	
	public  String getMapData(String key,int index) throws IOException{
		 
		Map<String, String> m = setMapData(index).get("Datasheet");
			String value = m.get(key);
			
			return value;
			
		}
	
	public void selectDropDown(By element,String value) {
		
		Select dropdown = new Select(driver.findElement(element));  
		dropdown.selectByVisibleText(value);  
	}
	
	public void setText(By element,String value) {
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value);
	}
	
	public void click(By element) {
		driver.findElement(element).click();
	}
	
	public String randomString(int length) {
		
		String value = "ABCDEFGHIJKLMN";
		 // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(length); 
	  
	        for (int i = 0; i < length; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(value.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(value 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 
	    
	}
	
	public String randomStringPassword(String value,int length) {
		 // create StringBuffer size of AlphaNumericString 
	        StringBuilder sb = new StringBuilder(length); 
	        value = value +"ABCDEFGHIJKLMNOP"+"0123456789"+"!@#$%^&*";
	        for (int i = 0; i < length; i++) { 
	  
	            // generate a random number between 
	            // 0 to AlphaNumericString variable length 
	            int index 
	                = (int)(value.length() 
	                        * Math.random()); 
	  
	            // add Character one by one in end of sb 
	            sb.append(value 
	                          .charAt(index)); 
	        } 
	  
	        return sb.toString(); 
	    
	}
	
	public void writeToExcel(int index,String uname,String pwd) throws EncryptedDocumentException, IOException {
		String path = (System.getProperty("user.dir")+File.separator+"src"+File.separator+"testData"+File.separator+"TestDataSheet.xlsx").replace("\\", "/") ;
		InputStream inp = new FileInputStream(path); 
	    Workbook wb = WorkbookFactory.create(inp); 
	    Sheet sheet = wb.getSheetAt(index); 
	    int num = sheet.getLastRowNum(); 
	    Row row = sheet.createRow(++num); 
	    row.createCell(0).setCellValue(uname);
	    row.createCell(1).setCellValue(pwd); 
	    
	    FileOutputStream fileOut = new FileOutputStream(path); 
	    wb.write(fileOut); 
	    fileOut.close(); 
    } 
	
	public void  isElementPresent(By we){ 
	    try{ 
	      WebElement w=new WebDriverWait(driver, 6).until(ExpectedConditions.presenceOfElementLocated((we)));
	        while(new WebDriverWait(driver, 6).until(ExpectedConditions.presenceOfElementLocated((we)))!=null){
	       
	          w.click();
	        
	        }
	    } 
	    catch (Throwable e) {
	        //System.err.println("WebElement isn't present!!");

	    } 
	    
    }
	
	public String getCellValueFromExcel(String filename,int index,int col) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(filename);
		int last_row = wb.getSheetAt(index).getLastRowNum();
		String content = wb.getSheetAt(index).getRow(last_row).getCell(col).getStringCellValue();
		wb.close();
		return content;
	}
	
	public void javascriptElemClick(By ele) {
		
		WebElement element = driver.findElement(ele);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	
	public int webtablecount(By ele) {
		
		return driver.findElements(ele).size();
		
	}
	
	public void checkboxClick(By ele ) {
		WebElement element = driver.findElement(ele);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}
	
	
}
