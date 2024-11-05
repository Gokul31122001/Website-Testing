package staffportal;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class staffinfo {

	public static void main(String[]args) throws InterruptedException, AWTException, IOException {
		Createprovider Details=new Createprovider();
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String
		pattern = "MMddyyyyhhMMss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.print(date);
		WebDriver driver=new ChromeDriver();
		driver.get("https://app.therapypms.com");
		
			
		FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("staff info");
		XSSFRow c=sheet.getRow(1);

		String eMail =c.getCell(0).getStringCellValue();
		String password=c.getCell(1).getStringCellValue();


		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys(eMail);
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(password);
		WebElement signin = driver.findElement(By.xpath("//button[@type='submit']"));
		signin.click();
			driver.manage().window().maximize();
			

		


		Thread.sleep(2000);
		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
		Actions a = new Actions(driver);
		a.moveToElement(mo).perform();
		
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
		
		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);
		
		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";
		
		String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>Degree</th><th>Region</th><th>Notes</th><th>statusmessage</th></tr>";
		Details.saveReport("Staff Details"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);

		
		 for(int i=6;i<=rowcount;i++) {

	           	XSSFRow cell=sheet.getRow(i);
	           	
	           	String Testcase_ID=cell.getCell(0).getStringCellValue(); 
				String name =cell.getCell(1).getStringCellValue();
				String Degree=cell.getCell(2).getStringCellValue();
				String Region=cell.getCell(3).getStringCellValue();
				String Notes=cell.getCell(4).getStringCellValue();
				System.out.println(Testcase_ID);
				

				driver.findElement(By.xpath("//input[@class='form-control form-control-sm search_name common_selector']")).sendKeys(name);

				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[@class='mr-2']")).click();

		WebElement d=driver.findElement(By.xpath("//select[@class='form-control-sm form-control']"));
		d.click();
		Select s=new Select(d);
		s.selectByVisibleText(Degree);
		
	WebElement re=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[2]"));
	Thread.sleep(1000);
	Select s1=new Select(re);
	s1.selectByVisibleText(Region);

		

		//driver.findElement(By.xpath("(//label[@class='custom-control-label'])[9]")).click();
		//Thread.sleep(1000);
		//driver.findElement(By.xpath("(//label[@class='custom-control-label'])[10]")).click();
		
	//	driver.findElement(By.xpath("(//label[@class='custom-control-label'])[11]")).click();
		driver.findElement(By.xpath("(//label[@class='custom-control-label'])[12]")).click();
		driver.findElement(By.xpath("(//label[@class='custom-control-label'])[13]")).click();
		driver.findElement(By.xpath("(//label[@class='custom-control-label'])[15]")).click();
		driver.findElement(By.xpath("(//label[@class='custom-control-label'])[16]")).click();
		driver.findElement(By.xpath("(//label[@class='custom-control-label'])[17]")).click();
	//	driver.findElement(By.xpath("(//label[@class='custom-control-label'])[18]")).click();
		//driver.findElement(By.xpath("(//label[@class='custom-control-label'])[19]")).click();

	WebElement n=driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]"));
	n.clear();
	n.sendKeys(Notes);

		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary mr-2 remove_validation_popup']")).click();

		Thread.sleep(1000);
		WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
		System.out.println("Toastmessage: " + toastMessage.getText());
		  String statusmessage=toastMessage.getText();

		  Details.saveReport("Staff Details"+date+".html",patientBillhtml(Testcase_ID,
				      name, 
					 Degree,
					 Region,
					 Notes,statusmessage));

		  Thread.sleep(1000);

	    driver.navigate().refresh();
	    

		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
		
	   
		

			}
		Details.saveReport("Staff Details"+date+".html","</table>");
		      
	}    
	 public static String  patientBillhtml(String Test_Case_ID,
			    String name, 
				String Degree,
				String Region,
				String Notes,
		    	String statusmessage)
	 
	 {
		 
		 String retval="";
		 
		 retval="<tr><td>"+Test_Case_ID+"</td>"+
        		"<td>"+name+"</td>"+ 
				"<td>"+Degree+"</td>"+
				"<td>"+Region+"</td>"+
				"<td>"+Notes+"</td>"+
	    	"<td>"+statusmessage+"</td></tr>";
		 return retval;


	}
}



