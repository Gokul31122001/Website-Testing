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

public class Blockoff {


	public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
		IndividualTheraphy Blockoff=new IndividualTheraphy();

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
		XSSFSheet sheet=wb.getSheet("Blockoff Time");
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

		String Columnames="<tr><th>Testcase_ID</th><th>Name</th><th>Start Time</th><th>End Time</th><th>Description</th><th>statusmessage</th></tr>";
		Blockoff.saveReport("Blockoff Time"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Create Appointment</center></th>"+Columnames);



		driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();


		Thread.sleep(1000);

		driver.findElement(By.xpath("(//div[@class='card-icon'])[5]")).click();
		
		
		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue();
			String StartTime=cell.getCell(2).getStringCellValue();
			String EndTime=cell.getCell(3).getStringCellValue();
			String Description=cell.getCell(4).getStringCellValue();
			System.out.println(Testcase_ID);
		

		Robot r=new Robot();
		 Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[@class='select2-selection select2-selection--single'])[3]")).click();
       
		WebElement n=driver.findElement(By.xpath("(//input[@type='search'])[2]"));
		n.sendKeys(name);
		r.keyPress(KeyEvent.VK_ENTER);



		driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[10]")).click();
		Thread.sleep(1000);
		//			driver.findElement(By.xpath("//td[@class='mc-date mc-date--active mc-date--picked']")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control form-control-sm bo_st']")).sendKeys(StartTime);


		driver.findElement(By.xpath("//input[@class='form-control form-control-sm bo_et']")).sendKeys(EndTime);

		driver.findElement(By.xpath("//textarea[@class='form-control form-control-sm bo_description']")).sendKeys(Description);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@class='btn btn-warning'])[1]")).click();


		Thread.sleep(1000);
		WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
		System.out.println("Toastmessage: " + toastMessage.getText());
		String statusmessage=toastMessage.getText();
		
		Blockoff.saveReport("Blockoff Time"+date+".html",patientBillhtml(Testcase_ID,
				name,
				StartTime ,
				EndTime,
				Description,statusmessage));

		
		driver.navigate().refresh(); 
		driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("(//div[@class='card-icon'])[5]")).click();
		
		
	}
		Blockoff.saveReport("Blockoff Time"+date+".html","</table>");
}
	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String StartTime ,
			String EndTime,
			String Description,
			String statusmessage)
	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+StartTime+"</td>"+ 
				"<td>"+EndTime +"</td>"+
				"<td>"+Description+"</td>"+
				"<td>"+statusmessage+"</td></tr>";
		return retval;
}
}