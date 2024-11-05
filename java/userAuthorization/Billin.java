package userAuthorization;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import userinfo.vob;

public class Billin {

	
		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			
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
			XSSFSheet sheet=wb.getSheet("Bill in");
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
				
				
				driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
				driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
				
				
				int rowcount=sheet.getLastRowNum();
				int colcount=sheet.getRow(0).getLastCellNum();
				System.out.println("rowcount :"+rowcount+"colcount"+colcount);
				
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";
				
				String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>From</th><th>to</th><th>schedule</th><th>status</th><th>statusmessage</th></tr>";
				saveReport("Billin"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Patient Authentication</center></th>"+Columnames);

				
			//	driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[3]")).click();************nonbill
				Thread.sleep(1000);
		WebElement patient=	driver.findElement(By.xpath("//div[text()='Select Patient']"));
		patient.click();
		
		       for(int i=6;i<=8;i++) {

		           	XSSFRow cell=sheet.getRow(i);
		           	
		           	String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();
					String From=cell.getCell(2).getStringCellValue();
					String to=cell.getCell(3).getStringCellValue();
					String schedule=cell.getCell(4).getStringCellValue();
					String status=cell.getCell(5).getStringCellValue();
					System.out.println(Testcase_ID);
					
					
					Robot r=new Robot();
				
					 driver.findElement(By.xpath("//div[@class='filter-option-inner-inner']"));
					  
					  WebElement n=driver.findElement(By.xpath("//input[@type='search']"));
						n.sendKeys(name);
					  r.keyPress(KeyEvent.VK_ENTER);
		
			     
			 
		    WebElement ins = driver.findElement(By.xpath("(//select[contains(@class,'form-control form-control-sm')])[10]"));
	         ins.click();
//		     Select s = new Select(ins);
//		     s.selectByIndex(2);     
//				Robot r1=new Robot();
//			     r1.keyPress(KeyEvent.VK_DOWN);
//			     r1.keyPress(KeyEvent.VK_DOWN);
//			     r1.keyRelease(KeyEvent.VK_ENTER);
	           driver.findElement(By.xpath("(//span[text()='None selected'])[3]")).click();
	           Thread.sleep(1000);
		 WebElement d=    driver.findElement(By.xpath("(//button[contains(@type,'button')])[19]"));
		 d.click();
		    
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("(//button[@type='button'])[18]")).click();
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("//span[@id='select2-sc_provider_id-container']")).click();
		     
		     //Robot r2=new Robot();
		     //r2.keyPress(KeyEvent.VK_DOWN);
		     //r2.keyPress(KeyEvent.VK_DOWN);
		     //r2.keyPress(KeyEvent.VK_DOWN);
		     //r2.keyRelease(KeyEvent.VK_ENTER);
		     driver.findElement(By.xpath("//li[text()='Edwards, Simone']")).click();
		     
		    
		
		    // driver.findElement(By.xpath("//select[@id='sc_location']")).click();
		WebElement da= driver.findElement(By.xpath("//input[@id='datepicker_appoint']"));
		 da.click();
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
			
		   Thread.sleep(1000);
		   driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[6]")).click();
		   driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning']")).click();
		    // Thread.sleep(1000);
		    // driver.findElement(By.xpath("//button[text()='09:00 AM']")).click();
	      Thread.sleep(2000);
		     driver.findElement(By.xpath("(//button[@class='btn btn-danger'])[6]")).click();
		   
		    driver.findElement(By.xpath("//input[@id='sc_from_time']")).sendKeys(From);
		    driver.findElement(By.xpath("//input[@id='sc_to_time']")).sendKeys(to);
		    Thread.sleep(1000);
		    driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[5]")).click();
		  driver.findElement(By.xpath("//input[@name='repeat_every']")).sendKeys("2");
		     
			WebElement t=driver.findElement(By.xpath("//select[@name='repeat_each']"));
			t.click();
			Select s1=new Select(t);
			s1.selectByVisibleText(schedule);
				driver.findElement(By.xpath("//select[@name='repeat_each']")).click();
				Thread.sleep(1000);
		   driver.findElement(By.xpath("//input[@id='datepicker_endpoint']")).click();
		   driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
			
 		   Thread.sleep(1000);
 		   driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[24]")).click();
		 
		   driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[5]")).click();
		     
		WebElement st=driver.findElement(By.xpath("//select[@id='sc_status']"));
		st.click();
		Select s2=new Select(st);
		s2.selectByVisibleText(status);
		    Thread.sleep(1000);
		    
		     
		     
		     driver.findElement(By.xpath("//button[@id='sc_sub_btn']")).click();
		     
		   

		     Thread.sleep(1000);
				WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
				System.out.println("Toastmessage: " + toastMessage.getText());
				  String statusmessage=toastMessage.getText();
				  
				
					saveReport("Billin"+date+".html",patientBillhtml(Testcase_ID,
					    	name,
					    	From ,
					    	to,
					    	schedule,
					    	status,statusmessage));
					
					Thread.sleep(2000);
					
					driver.navigate().refresh(); 

					  driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
						driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
						Thread.sleep(1000);
						WebElement p=	driver.findElement(By.xpath("//div[text()='Select Patient']"));
						p.click();
		       }
		       
		       saveReport("Billin"+date+".html","</table>");
		       
			    
			    Nonbill b=new Nonbill();
			    b.main(null);
			    
			    Noauthorization c1 =new Noauthorization();
			    c1.main(null);
			    
			    VOB d=new VOB();
			    d.main(null);
			    
			    Authrequest e=new Authrequest();
			    e.main(null);
			    
			  //  BillGroup a=new BillGroup();
			   // a.main(null);
			    
			    
			    
			    
			    
		}
		
					 public static String  patientBillhtml(String Test_Case_ID,
						    	String name,
						    	String From ,
						    	String to,
						    	String schedule,
						    	String status,
						    	String statusmessage)
					 
					 {
						 
							 String retval="";
							 
							 retval="<tr><td>"+Test_Case_ID+"</td>"+
						    	"<td>"+name+"</td>"+
						    	"<td>"+From +"</td>"+
						    	"<td>"+to+"</td>"+
						    	"<td>"+schedule+"</td>"+
						    	"<td>"+status+"</td>"+
						    	"<td>"+statusmessage+"</td></tr>";
							 return retval;
			      
		       }   
					 public static void saveReport(String filename,String file_value)
					 {
					 Path path
				     = Paths.get("C:\\Users\\Automation\\Testoutput"+filename);

				 // Custom string as an input
				 String str
				     = "Geeks for Geeks \nWelcome to computer science portal \nHello Geek";

				 // Try block to check for exceptions
				 try {
				     // Now calling Files.writeString() method
				     // with path , content & standard charsets
				     Files.writeString(path, file_value,
				    		 StandardOpenOption.CREATE,
								StandardOpenOption.APPEND);
				 }

				 // Catch block to handle the exception
				 catch (IOException ex) {
				     // Print messqage exception occurred as
				     // invalid. directory local path is passed
				     System.out.print("Invalid Path");
				 }
					 }
					 
		     
			     
		}




