package Createappointment_New;
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
import org.openqa.selenium.support.ui.Select;

public class No_authIndividual {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
		IndividualTheraphy NoauthIndividual =new IndividualTheraphy();
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
		XSSFSheet sheet=wb.getSheet("No_authIndidvidual");
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

		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);

		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>Therapy</th><th>Provider</th><th>pos</th><th>From</th><th>To</th><th>schedule</th><th>status</th><th>statusmessage</th></tr>";
		NoauthIndividual.saveReport("No_authIndividual"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Create Appointment</center></th>"+Columnames);




		driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();


		Thread.sleep(1000);

		driver.findElement(By.xpath("(//div[@class='card-icon'])[4]")).click();
		Thread.sleep(1000);


		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue();
			String Therapy =cell.getCell(2).getStringCellValue();
			String provider=cell.getCell(8).getStringCellValue();
			String pos =cell.getCell(3).getStringCellValue();
			String From =cell.getCell(4).getStringCellValue();
			String To =cell.getCell(5).getStringCellValue();
			String schedule =cell.getCell(6).getStringCellValue();
			String status =cell.getCell(7).getStringCellValue();

			System.out.println(Testcase_ID);

			Robot r=new Robot();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[text()='Select Patient']")).click();

			WebElement n=driver.findElement(By.xpath("//input[@type='search']"));
			n.sendKeys(name);
			r.keyPress(KeyEvent.VK_ENTER);




			WebElement we=driver.findElement(By.xpath("//select[@class='form-control form-control-sm sc_treatment_type']"));
			we.click();
			Thread.sleep(1000);
			Select s=new Select(we);
			s.selectByVisibleText(Therapy);

			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[2]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[@class='form-check'])[3]")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[2]")).click();

			Robot r1=new Robot();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[@class='select2-selection__rendered'])[4]")).click();
			WebElement pro=driver.findElement(By.xpath("//input[@class='select2-search__field']"));
			pro.sendKeys(provider);
			r1.keyPress(KeyEvent.VK_ENTER);

			Thread.sleep(1000);
			WebElement po =driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[4]"));
			po.click();
			Thread.sleep(3000);
			Select select3=new Select(po);
			select3.selectByVisibleText(pos);

			//	 driver.findElement(By.xpath("//li[@class='select2-results__option select2-results__option--selectable select2-results__option--highlighted']")).click();
			// Thread.sleep(1000);

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//td[@class='mc-date mc-date--active mc-date--picked mc-date--today']")).click();

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]")).sendKeys(From);

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]")).sendKeys(To);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[3]")).click();

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[8]")).sendKeys("1");

			WebElement ww= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[6]"));
			ww.click();
			Select ss=new Select(ww);
			ss.selectByVisibleText(schedule);

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[9]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[25]")).click();

			driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[5]")).click();

			Thread.sleep(1000);
			WebElement www= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[7]"));
			www.click();
			Select sss=new Select(www);
			sss.selectByVisibleText(status);
			
			driver.findElement(By.xpath("//button[@class='btn btn-warning']")).click();

			//Thread.sleep(1000);
	//Robot a=new Robot();
	//a.keyPress(KeyEvent.VK_ENTER);
			//driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		
		
			
			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();

			NoauthIndividual.saveReport("No_authIndividual"+date+".html",patientBillhtml(Testcase_ID,
					name,
					Therapy,
					provider,
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

			driver.findElement(By.xpath("(//div[@class='card-icon'])[4]")).click();




		}
		NoauthIndividual.saveReport("No_authIndividual"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String Therapy,
			String provider,
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