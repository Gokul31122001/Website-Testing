package Createappointment_New;
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

public class No_authGroup {
	
		public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
			IndividualTheraphy Noauth_Group=new IndividualTheraphy();
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
			XSSFSheet sheet=wb.getSheet("No_authGroup");
			XSSFRow c=sheet.getRow(1);

			String eMail =c.getCell(0).getStringCellValue();
			String password=c.getCell(1).getStringCellValue();


			WebElement username = driver.findElement(By.name("email"));
			username.sendKeys(eMail);
			WebElement pass = driver.findElement(By.name("password"));
			pass.sendKeys(password);
			WebElement signin = driver.findElement(By.xpath("//button[@type='submit']"));
			signin.click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("//label[@class='custom-control-label']")).click();
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
			driver.manage().window().maximize();
			
			int rowcount=sheet.getLastRowNum();
			int colcount=sheet.getRow(0).getLastCellNum();
			System.out.println("rowcount :"+rowcount+"colcount"+colcount);
			
			String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

			String Columnames="<tr><th>Testcase_ID</th><th>Name1</th><th>Name2</th><th>Therapy</th><th>Provider</th><th>Pos</th><th>From</th><th>To</th><th>schedule</th><th>status</th><th>statusmessage</th></tr>";
			Noauth_Group.saveReport("No_authGroup"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Create Appointment</center></th>"+Columnames);



			
			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
			
			
			Thread.sleep(1000);
			
			driver.findElement(By.xpath("(//div[@class='card-icon'])[4]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[2]")).click();
			
			
			for(int i=6;i<=rowcount;i++) {

				XSSFRow cell=sheet.getRow(i);

				String Testcase_ID=cell.getCell(0).getStringCellValue(); 
				String name1 =cell.getCell(1).getStringCellValue();
				String name2 =cell.getCell(2).getStringCellValue();
				String Therapy=cell.getCell(3).getStringCellValue();
				String provider=cell.getCell(9).getStringCellValue();
				String pos=cell.getCell(4).getStringCellValue();
				String From=cell.getCell(5).getStringCellValue();
				String To=cell.getCell(6).getStringCellValue();
				String schedule=cell.getCell(7).getStringCellValue();
				String status=cell.getCell(8).getStringCellValue();
				
				System.out.println(Testcase_ID);
				
				
			Thread.sleep(1000);
			WebElement w=driver.findElement(By.xpath("(//span[@class='selection'])[4]"));
			w.click();
			
			Thread.sleep(1000);
//			 Robot r=new Robot();
//			 r.keyPress(KeyEvent.VK_DOWN);
//			 r.keyRelease(KeyEvent.VK_ENTER);
//			 r.keyPress(KeyEvent.VK_DOWN);
//			 r.keyRelease(KeyEvent.VK_ENTER);
			 Thread.sleep(1000);
			 
		WebElement a=driver.findElement(By.xpath("(//textarea[@class='select2-search__field'])[1]"));
		Robot r=new Robot();
		a.sendKeys(name1);
		r.keyPress(KeyEvent.VK_ENTER);
		a.sendKeys(name2);
		r.keyPress(KeyEvent.VK_ENTER);
		
		
			
			 
			 Thread.sleep(1000);
			 WebElement we=driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_treatment_type']"));
				we.click();
				Thread.sleep(1000);
				Select s=new Select(we); 
				s.selectByVisibleText(Therapy);
			
				 Thread.sleep(1000);
				 driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[1]")).click();		 
				 Thread.sleep(2000);
				 driver.findElement(By.xpath("(//input[@class='form-check-input'])[4]")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.xpath("(//input[@class='form-check-input'])[6]")).click();
			
			
				 WebElement webe=driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_provider_id']"));
					Select sss=new Select(webe); 
					sss.selectByVisibleText(provider);
					
				 Thread.sleep(1000);
				 WebElement web=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[5]"));
				 web.click();
				 Select ss=new Select(web); 
					ss.selectByVisibleText(pos);
					 Thread.sleep(1000);
			
					
			
					 Thread.sleep(1000);
					 driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[5]")).click();
					 Thread.sleep(1000);
					 driver.findElement(By.xpath("//td[@class='mc-date mc-date--active mc-date--picked mc-date--today']")).click();
					 
					 driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[6]")).sendKeys(From);
					 
					 driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[7]")).sendKeys(To);
					 
					 Thread.sleep(1000);
					 driver.findElement(By.xpath("(//label[@class='custom-control-label'])[3]")).click();
					 
					 driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[8]")).sendKeys("1");
					 
					WebElement wew= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[6]"));
					 wew.click();
					 Select ses=new Select(wew);
					 ses.selectByVisibleText(schedule);
					 
					 Thread.sleep(1000);
						driver.findElement(By.xpath("//input[@id='datepicker_endpoint']")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[23]")).click();

						driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[5]")).click();

					 Thread.sleep(1000);
					 WebElement wsw= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[7]"));
					 wsw.click();
					 Select ess=new Select(wsw);
					 ess.selectByVisibleText(status);
					 
					 driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();
					 
					// Thread.sleep(1000);
						//Robot b=new Robot();
						//b.keyPress(KeyEvent.VK_ENTER);
							//	driver.findElement(By.xpath("//button[text()='Proceed']")).click();
							
					 
			
					 Thread.sleep(1000);
						WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
						System.out.println("Toastmessage: " + toastMessage.getText());
						  String statusmessage=toastMessage.getText();
						  
						  Noauth_Group.saveReport("No_authGroup"+date+".html",patientBillhtml(Testcase_ID,
									name1,
									name2,
									Therapy,
								    provider,
								    pos,
									From ,
									To,
									schedule,
									status,statusmessage));
			
					 
					 Thread.sleep(1000);
					  driver.navigate().refresh(); 
	  
					  driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
						driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();
						

						Thread.sleep(1000);

						driver.findElement(By.xpath("(//div[@class='card-icon'])[4]")).click();
						Thread.sleep(1000);
						driver.findElement(By.xpath("(//label[@class='custom-control-label'])[2]")).click();
						

	}
			Noauth_Group.saveReport("No_authGroup"+date+".html","</table>");
	}
		
		public static String  patientBillhtml(String Test_Case_ID,
				String name1,
				String name2,
				String Therapy,
				String provider,
				String pos,
				String From,
				String To,
				String schedule,
				String status,
				String statusmessage)
		{
			String retval="";

			retval="<tr><td>"+Test_Case_ID+"</td>"+
					"<td>"+name1+"</td>"+
					"<td>"+name2+"</td>"+
					"<td>"+Therapy+"</td>"+
					"<td>"+provider+"</td>"+
					"<td>"+pos+"</td>"+
					"<td>"+From +"</td>"+
					"<td>"+To+"</td>"+
					"<td>"+schedule+"</td>"+
					"<td>"+status+"</td>"+
					"<td>"+statusmessage+"</td></tr>";
			return retval;

}
}