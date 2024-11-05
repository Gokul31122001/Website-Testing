package Billing;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

public class Primarybilling {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
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
		XSSFSheet sheet=wb.getSheet("primary_Biling");
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

		String Columnames="<tr><th>Testcase_ID</th><th>Payor Id</th><th>Status</th><th>Actions</th><th>Batch</th><th>statusmessage</th></tr>";
		saveReport("Primary Billing"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Billing</center></th>"+Columnames);


		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();

		driver.findElement(By.xpath("//i[@class='ri-bill-line']")).click();


		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String payorId =cell.getCell(1).getStringCellValue();
			String Status=cell.getCell(2).getStringCellValue();
			String Actions=cell.getCell(3).getStringCellValue();
			String Batch=cell.getCell(4).getStringCellValue();
			System.out.println(Testcase_ID);

			driver.findElement(By.xpath("//input[@class='form-control form-control-sm select_date']")).click(); Thread.sleep(1000);

			driver.findElement(By.xpath("//td[@class='mc-date mc-date--active mc-date--picked mc-date--today']")).click();

			driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-warning'])[1]")).click();

			Thread.sleep(1000);

			WebElement w= driver.findElement(By.xpath("//select[@class='form-control form-control-sm insurance_type']"));

			w.click();

			Thread.sleep(1000);

			Select s=new Select(w);

			s.selectByVisibleText(payorId);

			WebElement ww=driver.findElement(By.xpath("(//span[@class='multiselect-selected-text']) [1]"));

			ww.click();

			WebElement st=driver.findElement(By.xpath("(//input[@type='search'])[1]"));
			st.sendKeys(Status);
			Thread.sleep(1000);
			Robot r1=new Robot();
			r1.keyPress(KeyEvent.VK_DOWN);
			r1.keyPress(KeyEvent.VK_DOWN);
			r1.keyPress(KeyEvent.VK_ENTER);



			Thread.sleep(2000);

			driver.findElement(By.xpath("(//span[@class='form-check']) [2]")).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[2]")).click();


			Thread.sleep(2000);
			driver.findElement(By.xpath("(//input[@class='form-check-input']) [5]")).click();

			driver.findElement(By.xpath("//button[@class='btn btn-sm mr-1 view_btn btn-primary']")).click();

			Thread.sleep(2000); 
			driver.findElement(By.xpath("(//td[@class='checkbox1_td'])[2]")).click();

			WebElement webe= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]")); 

			Thread.sleep(1000);
			Select sw=new Select(webe);
			sw.selectByVisibleText(Actions);
			
			WebElement was=driver.findElement(By.xpath("//select[@class='form-control form-control-sm generate_batch_action']"));

			Thread.sleep(1000);
			Select s1=new Select(was);
			s1.selectByVisibleText(Batch);


			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();


			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();


			saveReport("Primary Billing"+date+".html",patientBillhtml(Testcase_ID,
					payorId,
					Status,
					Actions ,
					Batch,statusmessage));

			driver.navigate().refresh();
			driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();

			driver.findElement(By.xpath("//i[@class='ri-bill-line']")).click();

			//			driver.findElement(By.xpath("//input[@class='form-control form-control-sm select_date']")).click(); Thread.sleep(1000);





		}
		saveReport("Primary Billing"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String payorId,
			String Status,
			String Actions ,
			String Batch,
			String statusmessage)
	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+payorId+"</td>"+
				"<td>"+Status+"</td>"+	 
				"<td>"+Actions +"</td>"+
				"<td>"+Batch +"</td>"+
				"<td>"+statusmessage+"</td></tr>";
		return retval;

	}
	public static void saveReport(String filename,String file_value)
	{
		Path path
		= Paths.get("C:\\Users\\Automation\\Testoutput"+filename);

		// Custom string as an input
		String str
		= "Geeks for Geeks \nWelcome to computer science portal \nHello Geek";

		// Try block to check for exceptions
		try {
			// Now calling Files.writeString() method
			// with path , content & standard charsets
			Files.writeString(path, file_value,
					StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		}

		// Catch block to handle the exception
		catch (IOException ex) {
			// Print messqage exception occurred as
			// invalid. directory local path is passed
			System.out.print("Invalid Path");
		}
	}
}
