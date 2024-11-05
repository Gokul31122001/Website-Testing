package userAuthorization;
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
import org.openqa.selenium.support.ui.Select;

public class Authrequest {
	
		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			Billin authreq=new Billin();
			Locale locale = new Locale("fr", "FR");
			DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
			String pattern = "MMddyyyyhhMMss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String d = simpleDateFormat.format(new Date());
			System.out.print(d);

			
			
			WebDriver driver=new ChromeDriver();
			driver.get("https://app.therapypms.com");
			
			FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFSheet sheet=wb.getSheet("Authrequest");
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

			String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>date</th><th>request</th><th>additional</th><th>statusmessage</th></tr>";
			authreq.saveReport("Authrequest"+d+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Patient Authentication</center></th>"+Columnames);

				
				driver.manage().window().maximize();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//a[@class='dropdown-item'])[6]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning select-form-grid-btn']")).click();
				Thread.sleep(1000);
				
				for(int i=6;i<=7;i++) {

					XSSFRow cell=sheet.getRow(i);

					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();
					String date =cell.getCell(2).getStringCellValue();
					String request =cell.getCell(3).getStringCellValue();
					String additional =cell.getCell(4).getStringCellValue();
					System.out.println(Testcase_ID);

			WebElement we=	driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]"));
			we.click();
			Thread.sleep(1000);
			Select s=  new Select(we);
			s.selectByIndex(2);
			Thread.sleep(1000);
			WebElement web=	driver.findElement(By.xpath("//div[@class='filter-option-inner-inner']"));
			web.click();
			Robot r=new Robot();
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyPress(KeyEvent.VK_DOWN);
				r.keyPress(KeyEvent.VK_ENTER);
				r.keyRelease(KeyEvent.VK_ENTER);
		
				driver.findElement(By.xpath("//button[@id='addPatientOptionBtn_0']")).click();
				Thread.sleep(1000);
			WebElement n=driver.findElement(By.xpath("//input[@id='newPatientOptionInput_0']"));
			n.sendKeys(name);
				Thread.sleep(1000);
				driver.findElement(By.xpath("//button[@id='confirmAddPatientOptionBtn_0']")).click();
			
			//	driver.findElement(By.xpath("//a[@class='dropdown-item active']")).click();
				Thread.sleep(1000);
				
				driver.findElement(By.xpath("//input[@class='form-control form-control-sm ']")).sendKeys(date);
				Thread.sleep(1000);		
			driver.findElement(By.xpath("//div[@class='viewbar form-control dropdown-toggle']")).click();
			
			Thread.sleep(1000);
			Robot r2=new Robot();
			r2.keyPress(KeyEvent.VK_DOWN);
			r2.keyPress(KeyEvent.VK_DOWN);
			r2.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(1000);	
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[2]")).click();
				
			WebElement webe=driver.findElement(By.xpath("//select[@class='form-control form-control-sm request_type']"));	
			webe.click();
			Select se=  new Select(webe);
			se.selectByVisibleText(request);
			driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]")).sendKeys(additional);
			Thread.sleep(3000);	
			driver.findElement(By.xpath("(//div[@class='col-md-12 mb-2'])[2]")).click();
			Thread.sleep(9000);
			driver.findElement(By.xpath("(//button[@class='btn btn-warning'])[1]")).click();
			
			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();
			
			authreq.saveReport("Authrequest"+d+".html",patientBillhtml(Testcase_ID,
					name,
					date ,
					request,
					additional,statusmessage));

			Thread.sleep(1000);
			driver.navigate().refresh();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//a[@class='dropdown-item'])[6]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning select-form-grid-btn']")).click();
			Thread.sleep(1000);
			
			
			
			
				}
				authreq.saveReport("Authrequest"+d+".html","</table>");
		}
		public static String  patientBillhtml(String Test_Case_ID,
				String name,
				String date ,
				String request,
				String additional,
				String statusmessage)
		{

			String retval="";

			retval="<tr><td>"+Test_Case_ID+"</td>"+
					"<td>"+name+"</td>"+
					"<td>"+date+"</td>"+ 
					"<td>"+request +"</td>"+
					"<td>"+additional+"</td>"+
					"<td>"+statusmessage+"</td></tr>";
			return retval;
			
			
			
			
			
			
			
			
			

	}
	}


