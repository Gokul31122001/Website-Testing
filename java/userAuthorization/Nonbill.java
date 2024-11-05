package userAuthorization;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Nonbill {


	private 
	WebElement toastMessage;
	private static WebDriver driver = new ChromeDriver();


	public static void main(String[]args) throws InterruptedException, AWTException, IOException {

		Billin non=new Billin();
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String pattern = "MMddyyyyhhMMss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.print(date);


		driver.get("https://app.therapypms.com");

		FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Non-bill");
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

		String Columnames="<tr><th>Testcase_ID</th><th>service</th><th>Pos</th><th>From</th><th>To</th><th>note</th><th>status</th><th>statusmessage</th></tr>";
		non.saveReport("NonBill"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Patient Authentication</center></th>"+Columnames);




		driver.manage().window().maximize();


		driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[3]")).click();


		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			
			String service =cell.getCell(1).getStringCellValue();
			String POS =cell.getCell(2).getStringCellValue();
			String From =cell.getCell(3).getStringCellValue();
			String To =cell.getCell(4).getStringCellValue();
			String note =cell.getCell(5).getStringCellValue();
			String status =cell.getCell(6).getStringCellValue();
			System.out.println(Testcase_ID);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@type='button'])[16]")).click();
			driver.findElement(By.xpath("//button[@title='Black, Steven']")).click();
			driver.findElement(By.xpath("(//button[@type='button'])[16]")).click();


			Thread.sleep(1000);
			WebElement se=driver.findElement(By.xpath("(//select[@name='activity_id[]'])[1]"));

			Select s=new Select(se);
			s.selectByVisibleText(service);

			driver.findElement(By.xpath("(//select[@name='activity_id[]'])[1]")).click();
			Thread.sleep(1000);
			WebElement po=driver.findElement(By.xpath("//select[@id='sc_location']"));
			po.click();
			Select s1=new Select(po); 
			s1.selectByVisibleText(POS);

			//Robot r1=new Robot();
			//r1.keyPress(KeyEvent.VK_DOWN);
			//r1.keyPress(KeyEvent.VK_DOWN);fx
			//r1.keyPress(KeyEvent.VK_DOWN);
			//r1.keyRelease(KeyEvent.VK_ENTER);
			driver.findElement(By.xpath("//select[@id='sc_location']")).click();	
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[4]")).click();
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]")).sendKeys(From);
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]")).sendKeys(To);
			driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]")).sendKeys(note);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[5]")).click();

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[7]")).sendKeys("1");

			driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[7]")).click();
			Robot rr=new Robot();
			rr.keyPress(KeyEvent.VK_DOWN);
			rr.keyRelease(KeyEvent.VK_ENTER);
			driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[7]")).click();

			driver.findElement(By.xpath("//input[@id='datepicker_endpoint']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@id='mc-picker__month--next']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[15]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[5]")).click();
			Thread.sleep(1000);

			WebElement st=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[8]"));
			st.click();
			Select s2=new Select(st);
			s2.selectByVisibleText(status);
			driver.findElement(By.xpath("(//button[@class='btn btn-warning'])[1]")).click();

			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();

			//	Thread.sleep(1000);
			//driver.findElement(By.xpath("(//button[@class='close'])[3]")).click();
			//Robot e=new Robot();
			//	e.keyRelease(KeyEvent.VK_ENTER);
			non.saveReport("NonBill"+date+".html",patientBillhtml(Testcase_ID,
					service,
					POS ,
					From,
					To,
					note,
					status,statusmessage));



			Thread.sleep(1000);

			driver.navigate().refresh();

			Thread.sleep(3000);
			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath("(//label[contains(@class,'custom-control-label')])[3]")).click();

		}
		non.saveReport("NonBill"+date+".html","</table>");
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




