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


public class NonBill {
	public static void main(String[] args) throws InterruptedException, IOException, AWTException  {
		IndividualTheraphy Non=new IndividualTheraphy();
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
		XSSFSheet sheet=wb.getSheet("Nonbill");
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

		driver.findElement(By.xpath("(//div[@class='card-icon'])[3]")).click();

		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);

		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>service</th><th>Pos</th><th>From</th><th>To</th><th>note</th><th>status</th><th>statusmessage</th></tr>";
		Non.saveReport("Non Billable"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Create Appointment</center></th>"+Columnames);


		for(int i=6;i<=7;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			//	String name =cell.getCell(1).getStringCellValue();
			String service =cell.getCell(2).getStringCellValue();
			String POS =cell.getCell(3).getStringCellValue();
			String From =cell.getCell(4).getStringCellValue();
			String To =cell.getCell(5).getStringCellValue();
			String note =cell.getCell(6).getStringCellValue();
			String status =cell.getCell(7).getStringCellValue();
			System.out.println(Testcase_ID);




			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[3]")).click();



			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-check-input'])[5]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[3]")).click();


			WebElement w=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[4]"));

			Thread.sleep(1000);
			Select s=new Select(w);
			s.selectByVisibleText(service);


			WebElement we=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[5]"));
			we.click();
			Thread.sleep(1000);
			Select ss=new Select(we);
			ss.selectByVisibleText( POS);


			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[9]")).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]")).sendKeys(From);
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]")).sendKeys(To);
			driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]")).sendKeys(note);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[3]")).click();

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[8]")).click();

			WebElement re= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[7]"));
			Thread.sleep(1000);
			Select rs=new Select(re);
			rs.selectByIndex(2);

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='datepicker_endpoint']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[23]")).click();

			driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[5]")).click();

			WebElement st= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[8]"));
			Select sss=new Select(st);
			sss.selectByVisibleText(status);
			driver.findElement(By.xpath("(//button[@class='btn btn-warning'])[1]")).click();

			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();

			Non.saveReport("Non Billable"+date+".html",patientBillhtml(Testcase_ID,
					service,
					POS ,
					From,
					To,
					note,
					status,statusmessage));


			Thread.sleep(2000);

			driver.navigate().refresh(); 
			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath("(//div[@class='card-icon'])[3]")).click();



		}

		Non.saveReport("Non Billable"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String service,
			String Pos ,
			String From,
			String To,
			String note,
			String status,
			String statusmessage)

	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+service+"</td>"+
				"<td>"+Pos+"</td>"+ 
				"<td>"+From +"</td>"+
				"<td>"+To+"</td>"+
				"<td>"+note+"</td>"+
				"<td>"+status+"</td>"+
				"<td>"+statusmessage+"</td></tr>";
		return retval;

	}
}