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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BillGroup {
	


			public static void main(String[]args) throws InterruptedException, AWTException, IOException {
				Billin group=new Billin();
				Locale locale = new Locale("fr", "FR");
				DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
				String pattern = "MMddyyyyhhMMss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(new Date());
				System.out.print(date);
				 WebDriver driver = new ChromeDriver();
				driver.get("https://app.therapypms.com");


				FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				XSSFSheet sheet=wb.getSheet("Bill Group");
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
				
				String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>name2</th><th>From</th><th>To</th><th>schedule</th><th>status</th><th>provider</th><th>repeat</th><th>statusmessage</th></tr>";
				group.saveReport("Billgroup"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Patient Authentication</center></th>"+Columnames);


				Thread.sleep(1000);
				driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[4]")).click();


				

				for(int i=6;i<=rowcount;i++) {

					XSSFRow cell=sheet.getRow(i);

					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();
					String name2 =cell.getCell(2).getStringCellValue();
					//String service =cell.getCell(3).getStringCellValue();
					String From =cell.getCell(4).getStringCellValue();
					String To =cell.getCell(5).getStringCellValue();
					String schedule =cell.getCell(6).getStringCellValue();
					String status =cell.getCell(7).getStringCellValue();
					String provider =cell.getCell(8).getStringCellValue();
					String repeat =cell.getCell(9).getRawValue();
					System.out.println(Testcase_ID);



					Thread.sleep(1000);
					WebElement n=	driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_client_id']"));
					n.sendKeys(name);
					n.click();

					//select[@class='form-control form-control-sm scg_client_id']
					driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_client_id']")).click();
					Thread.sleep(1000);

					driver.findElement(By.xpath("//button[contains(@id,'addGroupTherapyDiv')]")).click();
					WebElement n2=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm scg_client_id'])[2]"));
					n2.sendKeys(name2);
					n2.click();
					
		
					WebElement t2=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm scg_act_id'])[2]"));
					t2.click();
					Thread.sleep(1000);
					Robot a=new Robot();
					a.keyPress(KeyEvent.VK_DOWN);
					a.keyPress(KeyEvent.VK_DOWN);	
					a.keyPress(KeyEvent.VK_DOWN);
					a.keyPress(KeyEvent.VK_DOWN);
					a.keyRelease(KeyEvent.VK_ENTER);
					

					driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_act_id']")).click();
					Thread.sleep(1000);
					Robot r1=new Robot();
					r1.keyPress(KeyEvent.VK_DOWN);
					r1.keyPress(KeyEvent.VK_DOWN);
					r1.keyPress(KeyEvent.VK_DOWN);
					r1.keyPress(KeyEvent.VK_DOWN);
					r1.keyRelease(KeyEvent.VK_ENTER);
					driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_act_id']")).click();

				WebElement p=driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_provider_id']"));
				p.click();
				Select s3=new Select(p);
				s3.selectByVisibleText(provider);	
					
					
					driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_provider_id']")).click();
					driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[5]")).click();
					Robot r12=new Robot();
					r12.keyPress(KeyEvent.VK_DOWN);
					r12.keyPress(KeyEvent.VK_DOWN);
					r12.keyRelease(KeyEvent.VK_ENTER);
					driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[5]")).click();
					driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]")).click();
					driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[4]")).click();

					driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[5]")).sendKeys(From);
					driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[6]")).sendKeys(To);
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//label[@class='custom-control-label'])[5]")).click();

					driver.findElement(By.xpath("//input[@type='number']")).sendKeys(repeat);
					WebElement m=	driver.findElement(By.xpath("//select[@name='repeat_each']"));
					m.click();
					Select s4=new Select(m);
					s4.selectByVisibleText(schedule);

		         
					driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[8]")).click();
					
				//	driver.findElement(By.xpath("(//button[@class='mc-select__nav mc-select__nav--next'])[1]")).click();
					driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[25]")).click();

					driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[2]")).click();
					Thread.sleep(1000);
					WebElement st=driver.findElement(By.xpath("//select[@id='sc_status']"));
					st.click();
					Select s5=new Select(st);
					s5.selectByVisibleText(status);

				driver.findElement(By.xpath("//button[@id='sc_sub_btn']")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[@class='btn btn-secondary']")).click();

				  Thread.sleep(1000);
					WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
					System.out.println("Toastmessage: " + toastMessage.getText());
					  String statusmessage=toastMessage.getText();
					  
					group.saveReport("Billgroup"+date+".html",patientBillhtml(Testcase_ID,
						    	name,
						    	name2,
						    	From ,
						    	To,
						    	schedule,
						    	status,
						    	provider,
						    	repeat,statusmessage));
						
					Thread.sleep(1000);

					driver.navigate().refresh();

					Thread.sleep(3000);
					driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
					driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
	      
			
						Thread.sleep(1000);
						driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[4]")).click();

					
			}
				group.saveReport("Billgroup"+date+".html","</table>");
			}
			
						 public static String  patientBillhtml(String Test_Case_ID,
							    	String name,
							    	String name2 ,
							    	String From,
							    	String To,
							    	String schedule,
							    	String status,
							    	String provider,
							    	String repeat,
							    	String statusmessage)
						 
						 {
							 
							 String retval="";
							 
							 retval="<tr><td>"+Test_Case_ID+"</td>"+
						    	"<td>"+name+"</td>"+
						    	"<td>"+name2+"</td>"+ 
						    	"<td>"+From +"</td>"+
						    	"<td>"+To+"</td>"+
						    	"<td>"+schedule+"</td>"+
						    	"<td>"+status+"</td>"+
						    	"<td>"+provider+"</td>"+
						    	"<td>"+repeat+"</td>"+
						    	"<td>"+statusmessage+"</td></tr>";
							 return retval;
			      
		
			}
}
