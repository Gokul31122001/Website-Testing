package staffportal;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
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

import userAuthorization.Billin;


public class Payroll {


	public static void main(String[]args) throws InterruptedException, AWTException, IOException {
		Createprovider payroll=new Createprovider();
		WebDriver driver=new ChromeDriver();
		driver.get("https://app.therapypms.com");


		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String pattern = "MMddyyyyhhMMss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.print(date);

		FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Payroll");
		XSSFRow c=sheet.getRow(1);

		String eMail =c.getCell(0).getStringCellValue();
		String password=c.getCell(1).getStringCellValue();


		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys(eMail);
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(password);
		WebElement signin = driver.findElement(By.xpath("//button[@type='submit']"));
		signin.click();

		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);

		
		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>Name</th><th>Payperiod</th><th>Duration</th><th>Earning code</th><th>Calculation</th><th>Rate</th><th>Milage</th><th>statusmessage</th></tr>";
		payroll.saveReport("Payroll"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);

       
		Thread.sleep(2000);
		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
		Actions a = new Actions(driver);
		a.moveToElement(mo).perform();


        Thread.sleep(1000);
        driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
        
		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue();
			String Payperiod=cell.getCell(2).getStringCellValue();
			String Duration=cell.getCell(3).getStringCellValue();
			String Earningcode=cell.getCell(4).getStringCellValue();
			String Calculation=cell.getCell(5).getStringCellValue();
			String Rate	=cell.getCell(6).getRawValue();
			String Milage=cell.getCell(7).getRawValue();

			System.out.println(Testcase_ID);


 			driver.findElement(By.xpath("//input[@class='form-control form-control-sm search_name common_selector']")).sendKeys(name);
 			
 			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='mr-2']")).click();

	    
 			
 			 driver.findElement(By.xpath("(//a[@class='nav-link'])[5]")).click();
			    

			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
			Thread.sleep(2000);
			WebElement w=  driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[2]"));

			Select s= new Select(w);
			s.selectByVisibleText(Payperiod);
			Thread.sleep(1000);

			WebElement ww=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[3]"));
			Select s1=new Select(ww);
			s1.selectByVisibleText(Duration);

			Thread.sleep(1000);

			WebElement web=   driver.findElement(By.xpath("//select[@class='form-control form-control-sm earning_code_new']"));
			web.click();
			Select se= new Select(web);
			se.selectByVisibleText(Earningcode);
			Thread.sleep(1000);

			WebElement webe=   driver.findElement(By.xpath("//select[@class='form-control form-control-sm calculation_type_new']"));
			webe.click();
			Select sel= new Select(webe);
			sel.selectByVisibleText(Calculation);
			Thread.sleep(1000);

			driver.findElement(By.xpath("//input[@class='form-control hourly_rate form-control-sm']")).sendKeys(Rate);

			driver.findElement(By.xpath("//input[@class='form-control milage_rate form-control-sm mb-1']")).sendKeys(Milage);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")).click();

			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText(); 
			
			payroll.saveReport("Payroll"+date+".html",patientBillhtml(Testcase_ID,
					name,
					Payperiod,
					Duration ,
					Earningcode,
					Calculation,
					Rate,
					Milage,statusmessage));
			
			driver.navigate().refresh();
			
	         Thread.sleep(1000);
	         driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
	        

		}
		payroll.saveReport("Payroll"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
                     String name,
                     String Payperiod,
                     String Duration ,
                     String Earningcode,
                     String Calculation,
                     String Rate,
                     String Milage,
                     String statusmessage)
	{
		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+Payperiod+"</td>"+ 
				"<td>"+Duration+"</td>"+
				"<td>"+ Earningcode+"</td>"+
				"<td>"+Calculation+"</td>"+
				"<td>"+Rate+"</td>"+
				"<td>"+Milage+"</td>"+
				"<td>"+statusmessage+"</td></tr>";
		return retval;
	}

}
