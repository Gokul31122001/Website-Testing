package Createappointment_New;

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
import org.openqa.selenium.support.ui.Select;

public class VOB {
	
 
		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			IndividualTheraphy Vob=new IndividualTheraphy();
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
			XSSFSheet sheet=wb.getSheet("vob auth");
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

			String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>DOB</th><th>provider</th><th>Diagnosis</th><th>additional</th><th>statusmessage</th></tr>";
			Vob.saveReport("Vob"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>VOB Authentication</center></th>"+Columnames);




				Thread.sleep(1000);
				driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//a[@class='dropdown-item'])[5]")).click();
				driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning select-form-grid-btn']")).click();
				
				  for(int i=6;i<=rowcount;i++) {

			           	XSSFRow cell=sheet.getRow(i);
			           	
			           	String Testcase_ID=cell.getCell(0).getStringCellValue(); 
						String name =cell.getCell(1).getStringCellValue();
						String DOB =cell.getCell(2).getStringCellValue();
						String provider =cell.getCell(3).getStringCellValue();
						String Diagnosis =cell.getCell(4).getStringCellValue();
						String additional =cell.getCell(5).getStringCellValue();
						
						System.out.println(Testcase_ID);

				
				Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='filter-option-inner-inner'])[1]")).click();
			//we.click();
			Thread.sleep(1000);
			 Robot r13=new Robot();
		     r13.keyPress(KeyEvent.VK_DOWN);
		     r13.keyPress(KeyEvent.VK_DOWN);
		     r13.keyRelease(KeyEvent.VK_ENTER);
		     driver.findElement(By.xpath("//a[@class='dropdown-item active']")).click();
		     driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary new_patientOption']")).click();
		     driver.findElement(By.xpath("//input[@id='newPatientOptionInput_0']")).sendKeys(name);
		     driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning confirmPatient']")).click();
		     
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("//input[@class='form-control form-control-sm ']")).sendKeys(DOB);
		 	Thread.sleep(1000);
		   WebElement we=  driver.findElement(By.xpath("(//span[@class='placeholder'])[1]"));
		   we.click();
		  // Thread.sleep(2000);
		   //Robot r1=new Robot();
		     //r1.keyPress(KeyEvent.VK_DOWN); 
		     //r1.keyPress(KeyEvent.VK_DOWN);
		     //r1.keyRelease(KeyEvent.VK_ENTER);  
		   Thread.sleep(2000);
		  driver.findElement(By.xpath("(//label[@class='custom-control-label'])[2]")).click();
		  Thread.sleep(1000);
		  WebElement wed=driver.findElement(By.xpath("(//div[@class='viewbar form-control dropdown-toggle'])[2]"));
		  wed.click();
		 // Thread.sleep(1000);
		  //Robot r11=new Robot();
		    // r11.keyPress(KeyEvent.VK_DOWN); 
		     //r11.keyPress(KeyEvent.VK_DOWN);
		     //r11.keyRelease(KeyEvent.VK_ENTER);
		    Thread.sleep(3000);
	          driver.findElement(By.xpath("(//label[@class='custom-control-label'])[10]")).click();
		  
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("(//div[@class='viewbar form-control dropdown-toggle'])[3]")).click();	
		   //  Robot r12=new Robot();
		    // r12.keyPress(KeyEvent.VK_DOWN); 
		     //r12.keyPress(KeyEvent.VK_DOWN);
		     //r12.keyRelease(KeyEvent.VK_ENTER);
		  //   Thread.sleep(1000);
		   driver.findElement(By.xpath("(//label[@class='custom-control-label'])[32]")).click();
		     Thread.sleep(2000);
		     driver.findElement(By.xpath("(//div[@class='viewbar form-control dropdown-toggle'])[3]")).click();	
		     
		     Thread.sleep(2000);
		     driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]")).sendKeys(provider);
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]")).sendKeys(Diagnosis);
		     Thread.sleep(2000);
		     
		     WebElement web= driver.findElement(By.xpath("(//div[@class='filter-option-inner-inner'])[2]"));
			    web.click();
			    Robot r22=new Robot();
			     r22.keyPress(KeyEvent.VK_DOWN);
			     r22.keyPress(KeyEvent.VK_DOWN);
			     r22.keyPress(KeyEvent.VK_DOWN);
			     r22.keyRelease(KeyEvent.VK_ENTER);
			     Thread.sleep(2000);
			     driver.findElement(By.xpath("(//span[text()='ABA Medicaid'])[1]")).click();
		     
		     WebElement webe= driver.findElement(By.xpath("(//div[@class='filter-option-inner-inner'])[3]"));
		    webe.click();
		    Thread.sleep(1000);
		    Robot r23=new Robot();
		     r23.keyPress(KeyEvent.VK_DOWN);
		     r23.keyPress(KeyEvent.VK_DOWN);
		     r23.keyPress(KeyEvent.VK_DOWN);
		     r23.keyRelease(KeyEvent.VK_ENTER);
		     driver.findElement(By.xpath("(//span[text()='AARP - United healthcare'])[2]")).click();
		     
		   
		     driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]")).sendKeys(additional);
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("//div[@class='attachment-group mb-2']")).click();
		     Thread.sleep(9000);
		     
		     driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
		     
		     Thread.sleep(1000);
				WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
				System.out.println("Toastmessage: " + toastMessage.getText());
				  String statusmessage=toastMessage.getText();
				  
				  Vob.saveReport("Vob"+date+".html",patientBillhtml(Testcase_ID,
							name,
						    DOB,
							provider ,
							Diagnosis,
							additional,statusmessage));
				  
				
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//a[@class='dropdown-item'])[5]")).click();
				driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning select-form-grid-btn']")).click();
				

	}
				  Vob.saveReport("Vob"+date+".html","</table>");
				  
		}
					  public static String  patientBillhtml(String Test_Case_ID,
				  
							String name,
							String DOB,
							String provider ,
							String Diagnosis,
							String addtional,
							String statusmessage)

						{

							String retval="";

							retval="<tr><td>"+Test_Case_ID+"</td>"+
									"<td>"+name+"</td>"+
									"<td>"+DOB+"</td>"+
									"<td>"+provider+"</td>"+ 
									"<td>"+Diagnosis +"</td>"+
									"<td>"+addtional+"</td>"+
									"<td>"+statusmessage+"</td></tr>";
							return retval;
	}

}