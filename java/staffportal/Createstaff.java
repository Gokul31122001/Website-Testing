package staffportal;
import java.awt.AWTException;
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

public class Createstaff {
	
		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			Createprovider staff=new Createprovider();
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
			XSSFSheet sheet=wb.getSheet("staff creation");
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
          	
          	int rowcount=sheet.getLastRowNum();
    		int colcount=sheet.getRow(0).getLastCellNum();
    		System.out.println("rowcount :"+rowcount+"colcount"+colcount);
				
    	   String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";
			
			String Columnames="<tr><th>Testcase_ID</th><th> Staffname</th><th> Middlename</th> <th> Lastname</th><th> Nickname</th><th> DOB</th> <th> SSN</th> <th> Officephone</th><th> Fax</th><th> Email</th><th> License</th><th> Expiration</th><th> Title</th><th> Hiring</th><th> Credential</th><th> NPI</th><th> Id</th><th> Zip</th><th> Termination</th><th> Language</th><th> Tax</th><th>statusmessage</th></tr>";
			staff.saveReport("Create staff"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);


				
		
		          Thread.sleep(2000);
		        WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
		         Actions a = new Actions(driver);
		         a.moveToElement(mo).perform();
		         	
		         Thread.sleep(1000);
		         driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
		         Thread.sleep(1000);  
		         
		         
		         
		         driver.findElement(By.xpath("//a[@class='btn btn-sm text-white btn-warning dropdown-toggle']")).click();
		         
		         driver.findElement(By.xpath("(//a[@class='dropdown-item'])[12]")).click();
		         
		         for(int i=6;i<=8;i++) {

						XSSFRow cell=sheet.getRow(i);
						
						String Testcase_ID=cell.getCell(0).getStringCellValue(); 
						String Staffname =cell.getCell(1).getStringCellValue();
						String Middlename =cell.getCell(2).getStringCellValue();
						String Lastname =cell.getCell(3).getStringCellValue();
						String Nickname=cell.getCell(4).getStringCellValue();
						String DOB=cell.getCell(5).getStringCellValue(); 
						String SSN =cell.getCell(6).getStringCellValue();
						String Officephone =cell.getCell(7).getStringCellValue();
						String Fax =cell.getCell(8).getRawValue();
						String email=cell.getCell(9).getStringCellValue();
						String License=cell.getCell(10).getStringCellValue();
						String Expiration=cell.getCell(11).getStringCellValue();
						String Title=cell.getCell(12).getStringCellValue();
						String Hiring=cell.getCell(13).getStringCellValue();
						String Credential=cell.getCell(14).getStringCellValue();
					//	String Therapy=cell.getCell(15).getStringCellValue();
						String NPI=cell.getCell(16).getRawValue();
						String Id=cell.getCell(17).getRawValue();
						String Zip=cell.getCell(18).getRawValue();
						String Termination=cell.getCell(19).getStringCellValue();
						String Language=cell.getCell(20).getStringCellValue();
						String Tax=cell.getCell(21).getRawValue();
						
						
						System.out.println(Testcase_ID);
		         
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]")).sendKeys(Staffname);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]")).sendKeys(Middlename);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]")).sendKeys(Lastname);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[5]")).sendKeys(Nickname);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[6]")).sendKeys(DOB);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[7]")).sendKeys(SSN);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[8]")).sendKeys(Officephone);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[9]")).sendKeys(Fax);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[10]")).sendKeys(email);
		         
		           
		         driver.findElement(By.xpath("//input[@class='form-control form-control-sm mr-2']")).sendKeys(License);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[11]")).sendKeys(Expiration);
		         
		         
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[12]")).sendKeys(Title);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[13]")).sendKeys(Hiring);
		         
		      WebElement w= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]"));      
		         w.click();
			      Select s= new Select(w);
			      s.selectByVisibleText(Credential);
			      
			  
				//  driver.findElement(By.xpath("//span[@class='placeholder']")).click();    
			//	  WebElement t= driver.findElement(By.xpath("//input[@class='form-control']"));
			
				  
				     
			      
			      driver.findElement(By.xpath("//div[@class='viewbar form-control dropdown-toggle']")).click();
			      Thread.sleep(2000);     
				  driver.findElement(By.xpath("(//div[@class='dropdown-item custom-control'])[3]")).click();  
				  Thread.sleep(2000);
				  driver.findElement(By.xpath("//div[@class='viewbar form-control dropdown-toggle']")).click();
				  
				  
				  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[14]")).sendKeys(NPI);
				           
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[15]")).sendKeys(Id);
		         
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[16]")).sendKeys(Zip);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[17]")).sendKeys(Termination);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[18]")).sendKeys(Language);
		         driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[19]")).sendKeys(Tax);
		     
		         driver.findElement(By.xpath("(//div[@class='custom-control custom-radio custom-control-inline mb-1'])[2]")).click();    
		         
		         driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
		         
		         Thread.sleep(1000);
					WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
					System.out.println("Toastmessage: " + toastMessage.getText());
					  String statusmessage=toastMessage.getText();
					  
					staff.saveReport("Create staff"+date+".html",patientBillhtml(Testcase_ID,
							     Staffname ,
								 Middlename ,
								 Lastname, 
								 Nickname,
								 DOB, 
								 SSN, 
								 Officephone, 
								 Fax, 
								 email,
								 License,
								 Expiration,
								 Title,
								 Hiring,
								 Credential,
								 NPI,
								 Id,
								 Zip,
								 Termination,
								 Language,
								 Tax,statusmessage));
						
					  
					  Thread.sleep(2000);
						
						driver.navigate().refresh(); 
						  Thread.sleep(1000);
					         driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();		 

		        driver.findElement(By.xpath("//a[@class='btn btn-sm text-white btn-warning dropdown-toggle']")).click();
		         
		         driver.findElement(By.xpath("(//a[@class='dropdown-item'])[12]")).click();
		         
		         
		         }
		        staff.saveReport("Create staff"+date+".html","</table>");
		         
		}

		 public static String  patientBillhtml(String Test_Case_ID,
				    String Staffname, 
					String Middlename, 
					String Lastname, 
					String Nickname,
					String DOB, 
					String SSN, 
					String Officephone, 
					String Fax, 
					String email,
					String License,
					String Expiration,
					String Title,
					String Hiring,
					String Credential,
					String NPI,
					String Id,
					String Zip,
					String Termination,
					String Language,
					String Tax,
			    	String statusmessage)
		   
		         
		 {
			 
			 String retval="";
			 
			 retval="<tr><td>"+Test_Case_ID+"</td>"+
					 "<td>"+ Staffname+"</td>"+ 
						"<td>"+ Middlename+"</td>"+ 
						"<td>"+ Lastname+"</td>"+ 
						"<td>"+ Nickname+"</td>"+
						"<td>"+ DOB+"</td>"+ 
						"<td>"+ SSN+"</td>"+ 
						"<td>"+ Officephone+"</td>"+ 
						"<td>"+ Fax+"</td>"+ 
						"<td>"+ email+"</td>"+
						"<td>"+ License+"</td>"+
						"<td>"+ Expiration+"</td>"+
						"<td>"+ Title+"</td>"+
						"<td>"+ Hiring+"</td>"+
						"<td>"+ Credential+"</td>"+
						"<td>"+ NPI+"</td>"+
						"<td>"+ Id+"</td>"+
						"<td>"+ Zip+"</td>"+
						"<td>"+ Termination+"</td>"+
						"<td>"+ Language+"</td>"+
						"<td>"+ Tax+"</td>"+						
		             	"<td>"+statusmessage+"</td></tr>";
			 return retval;
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		         
		}

		}
		


