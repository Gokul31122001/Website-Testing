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

public class GroupTheraphy {


	public static void main(String[]args) throws InterruptedException, AWTException, IOException {
		IndividualTheraphy Group=new IndividualTheraphy();
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
		XSSFSheet sheet=wb.getSheet("Group Therapy");
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

		driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='col-2'])[2]")).click();

		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);

		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>Name</th><th>Name2</th><th>From</th><th>To</th><th>schedule</th><th>status</th><th>provider</th><th>statusmessage</th></tr>";
		Group.saveReport("Group Therapy"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Create Appointment</center></th>"+Columnames);



		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue();
			String name2 =cell.getCell(2).getStringCellValue();
			String From =cell.getCell(4).getStringCellValue();
			String To =cell.getCell(5).getStringCellValue();
			String notes=cell.getCell(10).getStringCellValue();
			String schedule =cell.getCell(6).getStringCellValue();
			String status =cell.getCell(7).getStringCellValue();
			String provider =cell.getCell(8).getStringCellValue();
			System.out.println(Testcase_ID);


			Thread.sleep(1000);

			WebElement web=driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_client_id']"));
			web.sendKeys(name);
			web.click();

			Thread.sleep(1000);
			WebElement we=   driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_act_id']"));
			we.click();
			Thread.sleep(1000);
			Select ss=new Select(we);
			ss.selectByIndex(1);


			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@class='btn btn-sm text-primary']")).click();
			Thread.sleep(1000);
			WebElement cd=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm scg_client_id'])[2]"));
			cd.sendKeys(name2);
			cd.click();

			WebElement id= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm scg_act_id'])[2]")); 		    
			Thread.sleep(1000);
			Select rd=new Select(id);
			rd.selectByIndex(1);

			WebElement pd=   driver.findElement(By.xpath("//select[@class='form-control form-control-sm scg_provider_id']"));
			pd.click();
			Select rr=new Select(pd);
			rr.selectByVisibleText(provider);

			WebElement sm= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[5]"));
			sm.click();
			Select rm=new Select(sm);
			rm.selectByIndex(2);

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[5]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[15]")).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[6]")).sendKeys(From);
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[7]")).sendKeys(To);
			
			driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[2]")).sendKeys(notes);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[3]")).click();

			driver.findElement(By.xpath("//input[@name='repeat_every']")).click();
			WebElement re=driver.findElement(By.xpath("//select[@name='repeat_each']"));
			Select rs=new Select(re);
			rs.selectByVisibleText(schedule);

			driver.findElement(By.xpath("//select[@name='repeat_each']")).click();

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[9]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[25]")).click();

			driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[2]")).click();
			Thread.sleep(1000);
			WebElement st=driver.findElement(By.xpath("//select[@id='sc_status']"));
			Select sss=new Select(st);
			sss.selectByVisibleText(status);
			driver.findElement(By.xpath("//button[@id='sc_sub_btn']")).click();

			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();

			Group.saveReport("Group Therapy"+date+".html",patientBillhtml(Testcase_ID,
					name,
					name2,
					From ,
					To,
					schedule,
					status,
					provider,statusmessage));



			Thread.sleep(2000);

			driver.navigate().refresh(); 
			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();


			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='col-2'])[2]")).click();

		}
		Group.saveReport("Group Therapy"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String name2 ,
			String From,
			String To,
			String schedule,
			String status,
			String provider,
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
				"<td>"+statusmessage+"</td></tr>";
		return retval;

	}
}