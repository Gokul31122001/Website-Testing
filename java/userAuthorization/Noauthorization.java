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

public class Noauthorization {


		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			Billin noauth=new Billin();
			Locale locale = new Locale("fr", "FR");
			DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
			String pattern = "MMddyyyyhhMMss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			System.out.print(date);

			WebDriver driver=new ChromeDriver();
			driver.get("https://app.therapypms.com");
			
			FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFSheet sheet=wb.getSheet("Noauth");
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

			String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>Therapy</th><th>pos</th><th>From</th><th>To</th><th>schedule</th><th>status</th><th>statusmessage</th></tr>";
			noauth.saveReport("Noauthority"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Patient Authentication</center></th>"+Columnames);



		
			driver.manage().window().maximize();
				
				
				driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
				driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
				
				Thread.sleep(1000);
				 driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[2]")).click();
				 driver.findElement(By.xpath("//div[text()='Select Patient']")).click();
				
				 for(int i=6;i<=rowcount;i++) {

						XSSFRow cell=sheet.getRow(i);

						String Testcase_ID=cell.getCell(0).getStringCellValue(); 
						String name =cell.getCell(1).getStringCellValue();
						String Therapy =cell.getCell(2).getStringCellValue();
						String pos =cell.getCell(3).getStringCellValue();
						String From =cell.getCell(4).getStringCellValue();
						String To =cell.getCell(5).getStringCellValue();
						String schedule =cell.getCell(6).getStringCellValue();
						String status =cell.getCell(7).getStringCellValue();
						
						System.out.println(Testcase_ID);

						
						
						Robot r=new Robot();
				  
					//  WebElement n=driver.findElement(By.xpath("//a[contains(@id,'bs-select-1-0')]"));
				  driver.findElement(By.xpath("//div[@class='filter-option-inner-inner']"));
				  
				  WebElement n=driver.findElement(By.xpath("//input[@type='search']"));
					n.sendKeys(name);
				  r.keyPress(KeyEvent.VK_ENTER);
					
				    
			WebElement we = driver.findElement(By.xpath("//select[@id='sc_treatment_type']"));
			Thread.sleep(1000);
			 Select s = new Select(we);
			     s.selectByVisibleText(Therapy) ;

				     driver.findElement(By.xpath("//select[@id='sc_treatment_type']")).click();
				     
				     Thread.sleep(1000);
				  driver.findElement(By.xpath("(//button[@title='None selected'])[1]")).click();
				  driver.findElement(By.xpath("//label[text()=' Select all']")).click();
				  Thread.sleep(1000);
				  driver.findElement(By.xpath("(//span[@class='multiselect-selected-text'])[1]")).click();
				  
				  Thread.sleep(1000);
				 driver.findElement(By.xpath("//span[text()='Select Provider']")).click();
				  Thread.sleep(1000);
				  Robot ra=new Robot();
				     ra.keyPress(KeyEvent.VK_DOWN);
				     ra.keyPress(KeyEvent.VK_DOWN);
				     ra.keyRelease(KeyEvent.VK_ENTER);
				     driver.findElement(By.xpath("//li[text()='Black, Steven']")).click();
				     Thread.sleep(1000);
					   
				    WebElement d = driver.findElement(By.xpath("//select[@id='sc_location']"));
				    Select s1 = new Select(d);
	     		     s1.selectByVisibleText(pos); 
	     		    driver.findElement(By.xpath("(//input[@type='text'])[5]")).click();
	     		   driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
					
	     		   Thread.sleep(1000);
	     		   driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[6]")).click();
	     		    driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]")).sendKeys(From);
	   		     driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]")).sendKeys(To);
	   		     Thread.sleep(1000);
	   		   driver.findElement(By.xpath("(//label[@class='custom-control-label'])[5]")).click();

	   		  driver.findElement(By.xpath("//input[@name='repeat_every']")).sendKeys("3");
	   		  
	   		  driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[6]")).click(); 
	   		WebElement t=driver.findElement(By.xpath("//select[@name='repeat_each']"));
			t.click();
			Select s3=new Select(t);
			s3.selectByVisibleText(schedule);
			
		     driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[6]")).click();  
				  
				  
		     driver.findElement(By.xpath("//input[@id='datepicker_endpoint']")).click();
		     driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
				Thread.sleep(1000);
		     Thread.sleep(1000);
		     driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[20]")).click();
		     Thread.sleep(1000);
				  
		     driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[3]")).click();
		  WebElement dt  = driver.findElement(By.xpath("//select[@id='sc_status']"));
		     Select s2 = new Select(dt);
			     s2.selectByVisibleText(status); 
				  
			     driver.findElement(By.xpath("//button[@id='sc_sub_btn']")).click();
				  
				  Thread.sleep(1000);
				WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
				System.out.println("Toastmessage: " + toastMessage.getText());
				  String statusmessage=toastMessage.getText();
				  
				  noauth.saveReport("Noauthority"+date+".html",patientBillhtml(Testcase_ID,
							name,
						    Therapy,
							pos ,
							From,
							To,
							schedule,
							status,statusmessage));
				  
				 
				  Thread.sleep(1000);
				  driver.navigate().refresh(); 
  
				  driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
					driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
					
					Thread.sleep(1000);
					 driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[2]")).click();
					 driver.findElement(By.xpath("//div[text()='Select Patient']")).click();
					
				  
				  
	}
				 
		noauth.saveReport("Noauthority"+date+".html","</table>");
	}

	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String Therapy,
			String pos ,
			String From,
			String To,
			String schedule,
			String status,
			String statusmessage)

	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+Therapy+"</td>"+
				"<td>"+pos+"</td>"+ 
				"<td>"+From +"</td>"+
				"<td>"+To+"</td>"+
				"<td>"+schedule+"</td>"+
				"<td>"+status+"</td>"+
				"<td>"+statusmessage+"</td></tr>";
		return retval;



	}

}///div[@id='toast-container']

