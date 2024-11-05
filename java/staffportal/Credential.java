package staffportal;
import java.awt.AWTException;
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


public class Credential {
		
		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			Createprovider Credential=new Createprovider();
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
			XSSFSheet sheet=wb.getSheet("Credential");
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
					
					String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>Description 1</th><th>Date Issue 1</th><th>Date Expired 1</th><th>Description 2</th> <th>Date Issue2</th><th>Date Expired 2</th><th>Description 3</th><th>Date Issue 3</th><th>Date Expired 3</th><th>Description 4</th><th>Date Issue 4</th><th>Date Expired 4</th><th>status 1</th><th>status 2</th><th>status 3</th><th>statusmessage</th></tr>";
					Credential.saveReport("staff Credential"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);



				for(int i=6;i<=7;i++) {

					XSSFRow cell=sheet.getRow(i);

					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();
					String Description1=cell.getCell(2).getStringCellValue();
					String Dateissue1=cell.getCell(3).getStringCellValue();
					String Dateexpired1=cell.getCell(4).getStringCellValue();
					String Description2=cell.getCell(5).getStringCellValue();
					String Dateissue2=cell.getCell(6).getStringCellValue();
					String Dateexpired2=cell.getCell(7).getStringCellValue();
					String Description3=cell.getCell(8).getStringCellValue();
					String Dateissue3=cell.getCell(9).getStringCellValue();
					String Dateexpired3=cell.getCell(10).getStringCellValue();
					String Description4=cell.getCell(11).getStringCellValue();
					String Dateissue4=cell.getCell(12).getStringCellValue();
					String Dateexpired4=cell.getCell(13).getStringCellValue();
					
					System.out.println(Testcase_ID);
					
					
					
		         
					driver.findElement(By.xpath("//input[@class='form-control form-control-sm search_name common_selector']")).sendKeys(name);

					Thread.sleep(2000);
					driver.findElement(By.xpath("//a[@class='mr-2']")).click();


			    
			    driver.findElement(By.xpath("(//a[@class='nav-link'])[3]")).click();
			   // Thread.sleep(1000); 
			//    driver.findElement(By.xpath("(//a[@class='btn btn-primary text-left btn-block w-100'])[1]")).click();
			    
			    Thread.sleep(1000); 
			    driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-primary mb-2'])[1]")).click();
			    Thread.sleep(1000); 
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control cred_save_type']")).sendKeys(Description1);
			    Thread.sleep(1000);  
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control cred_save_date_issue']")).sendKeys(Dateissue1);
			    
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control cred_save_date_expired']")).sendKeys(Dateexpired1);
			    Thread.sleep(2000); 
			 //  driver.findElement(By.xpath("(//div[@class='col-md-6 mb-2'])[24]")).click();
			    
			   // Thread.sleep(9000); 
			    
			   driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[1]")).click();
			   
				WebElement Toast1=driver.findElement(By.xpath("//div[text()='Staff Credentials Successfully Created.']"));
				System.out.println("Toastmessage: " + Toast1.getText());
				String status1=Toast1.getText(); 

			    Thread.sleep(1000);  
				
			    
			    driver.findElement(By.xpath("(//a[@class='btn btn-primary text-left btn-block w-100'])[2]")).click();
			    
			    Thread.sleep(1000);  
				
			    driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-primary mb-2'])[2]")).click();
			    Thread.sleep(1000);  
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control clear_save_type']")).sendKeys(Description2);
			    
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control clear_save_date_issue']")).sendKeys(Dateissue2);
			    Thread.sleep(1000);  
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control clear_save_date_expired']")).sendKeys(Dateexpired2);
			    Thread.sleep(3000);  
			//    driver.findElement(By.xpath("(//div[@class='col-md-6 mb-2'])[29]")).click();
			   // Thread.sleep(9000); 
			    driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]")).click();
			    
			    WebElement Toast2=driver.findElement(By.xpath("//div[text()='Staff Clearance Successfully Created.']"));
				System.out.println("Toastmessage: " + Toast2.getText());
				String status2=Toast2.getText();
				
				
			    Thread.sleep(1000);
			    driver.findElement(By.xpath("(//a[@class='btn btn-primary text-left btn-block w-100'])[3]")).click();
			    Thread.sleep(1000);  
			    driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-primary mb-2'])[3]")).click();
			    Thread.sleep(1000);  
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control qual_save_type']")).sendKeys(Description3);
			    
			    
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control qual_save_date_issue']")).sendKeys(Dateissue3);
			    
			    
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control qual_save_date_expired']")).sendKeys(Dateexpired3);
			    Thread.sleep(2000);  
			  // driver.findElement(By.xpath("(//div[@class='col-md-6 mb-2'])[34]")).click();
			//   Thread.sleep(9000);
			    driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")).click();
			    
			    WebElement Toast3=driver.findElement(By.xpath("//div[text()='Staff Qualification Successfully Created.']"));
				System.out.println("Toastmessage: " + Toast3.getText());
				String status3=Toast3.getText();
				

			   
			    
			    driver.findElement(By.xpath("(//a[@class='btn btn-primary text-left btn-block w-100'])[4]")).click();
			    Thread.sleep(1000);  
			    driver.findElement(By.xpath("(//a[@class='btn btn-sm btn-primary mb-2'])[4]")).click();
			    Thread.sleep(3000);  
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control other_save_type']")).sendKeys(Description4);
			    
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control other_save_date_issue']")).sendKeys(Dateissue4);
			    
			    driver.findElement(By.xpath("//input[@class='form-control-sm form-control other_save_date_expired']")).sendKeys(Dateexpired4);
			    Thread.sleep(2000);  
			 //  driver.findElement(By.xpath("(//div[@class='col-md-6 mb-2'])[39]")).click();
			 //   Thread.sleep(9000);  
			    driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[4]")).click();
			    
			    
				Thread.sleep(1000);
				WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
				System.out.println("Toastmessage: " + toastMessage.getText());
				String statusmessage=toastMessage.getText(); 

				Credential.saveReport("staff Credential "+date+".html",patientBillhtml(Testcase_ID,
						name,
						Description1,
						Dateissue1,
						Dateexpired1,
						Description2,
						Dateissue2,
						Dateexpired2,
						Description3,
						Dateissue3,
						Dateexpired3,
						Description4,
						Dateissue4,
						Dateexpired4,
						status1,
						status2,			
						status3,statusmessage));

			    
			    
				Thread.sleep(1000);

				driver.navigate().refresh();

				Thread.sleep(1000);
				driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();


			    

	}
				Credential.saveReport("staff Credential"+date+".html","</table>");
			      
	}
		public static String  patientBillhtml(String Test_Case_ID,
				String name,
				String Description1,
				String Dateissue1,
				String Dateexpired1,
				String Description2,
				String Dateissue2,
				String Dateexpired2,
				String Description3,
				String Dateissue3,
				String Dateexpired3,
				String Description4,
				String Dateissue4,
				String Dateexpired4,
				String status1,
				String status2,			
				String status3,
				String statusmessage)
		{		
				String retval="";
		 
		 retval="<tr><td>"+Test_Case_ID+"</td>"+
				 "<td>"+name+"</td>"+
					"<td>"+Description1+"</td>"+
					"<td>"+Dateissue1+"</td>"+
					"<td>"+Dateexpired1+"</td>"+
					"<td>"+Description2+"</td>"+
					"<td>"+Dateissue2+"</td>"+
					"<td>"+Dateexpired2+"</td>"+
					"<td>"+Description3+"</td>"+
					"<td>"+Dateissue3+"</td>"+
					"<td>"+Dateexpired3+"</td>"+
					"<td>"+Description4+"</td>"+
					"<td>"+Dateissue4+"</td>"+
					"<td>"+Dateexpired4+"</td>"+
					"<td>"+status1+"</td>"+
					"<td>"+status2+"</td>"+			
					"<td>"+status3+"</td>"+
	    	        "<td>"+statusmessage+"</td></tr>";
	
		 return retval;
	
				
}
}