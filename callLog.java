package userinfo;
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
public class callLog {


		
			public static void main(String[] args) throws InterruptedException, AWTException, IOException {
			  usercreation pad=new usercreation();


				WebDriver driver = new ChromeDriver();
				driver.get("https://app.therapypms.com");
				Locale locale = new Locale("fr", "FR");
				DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
				String pattern = "MMddyyyyhhMMss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(new Date());
				System.out.print(date);

				FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				XSSFSheet sheet=wb.getSheet("Call Log");
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
		 		
				Thread.sleep(2000);
				WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
				Actions a = new Actions(driver);
				a.moveToElement(mo).perform();

				Thread.sleep(1000);
				driver.findElement(By.linkText("Patient(S)")).click();
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; }td { border: 1px solid black; }</style>\r\n";

				String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>addition</th><th>year</th><th>statusmessage</th></tr>";
				pad.saveReport("Usercall_"+date+".html",tblcss+"<table border='1'>"+Columnames);
						
				for(int i=6;i<=7;i++) {
		        	
			         XSSFRow cell=sheet.getRow(i);
			    
			         
			        
			         String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			         String name =cell.getCell(1).getStringCellValue();
			         String addition=cell.getCell(2).getStringCellValue();
			         String year=cell.getCell(3).getStringCellValue();;
			         
			         System.out.println(Testcase_ID);


				
				driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name); 

				Thread.sleep(4000);
				WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
				search.click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//a[contains(@class,'nav-link')])[9]")).click();
				driver.findElement(By.xpath("//button[contains(@class,'btn btn-sm btn-warning mb-2')]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//textarea[contains(@class,'form-control form-control-sm add_comment_box')])[1]")).sendKeys(addition);
				driver.findElement(By.xpath("(//input[contains(@class,'form-control form-control-sm ')])[1]")).sendKeys(year);
				driver.findElement(By.xpath("(//button[contains(@class,'btn btn-warning ladda-button')])[1]")).click();
			
				 WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
					System.out.println("Toastmessage: " + toastMessage.getText());
					String statusmessage=toastMessage.getText();
						
					
					
					pad.saveReport("Usercall_"+date+".html",updatePatienthtml(Testcase_ID,
							name,
							addition,
							year,statusmessage));
							
				
					
					Thread.sleep(2000);
			        WebElement m = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
			     
			         a.moveToElement(m).perform();
			WebElement first=	 driver.findElement(By.partialLinkText("Patient(S)"));
			first.click();
				}
				
				pad.saveReport("Usercall_"+date+".html","</table>");      
			}
				public static String  updatePatienthtml(String Testcase_ID,
						String name,
						String addition,
						String year,
		                String statusmessage)

				
				
				
				
					{
						String retval="";

						retval="<tr><td>"+Testcase_ID+"</td>"+
								"<td>"+name+"</td>"+
								"<td>"+addition+"</td>"+
								"<td>"+year+"</td>"+
								"<td>"+statusmessage+"</td></tr>";
						
						return retval;

			}
			
			 
			private static String convernumericval(double d)
			{
				   String retval="";
				   System.out.println(d);
				 	 retval=String.valueOf(d);
				 	
			
				 	 return retval;
			}

		}




