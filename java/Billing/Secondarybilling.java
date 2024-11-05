package Billing;

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

public class Secondarybilling {



	public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
		Primarybilling secondary=new Primarybilling();
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
		XSSFSheet sheet=wb.getSheet("Secondary_Billing");
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

		String Columnames="<tr><th>Testcase_ID</th><th>Sort</th><th>Batch</th><th>Sort By</th><th>Status</th><th>Document</th></tr>";
		secondary.saveReport("Secondary Billing"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Billing</center></th>"+Columnames);


		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='ri-file-list-3-line']")).click();


		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String Sort=cell.getCell(1).getStringCellValue();
			String Batch=cell.getCell(2).getRawValue();
			String Sortby=cell.getCell(3).getStringCellValue();
			String Status=cell.getCell(4).getStringCellValue();
			String Document=cell.getCell(5).getStringCellValue();
			System.out.println(Testcase_ID);


			WebElement mov =driver.findElement(By.xpath("//select[@class='form-control form-control-sm filter_by']"));
			mov.click();
			Thread.sleep(1000);
			Select s=new Select(mov);
			s.selectByVisibleText(Sort);

			WebElement w =driver.findElement(By.xpath("//select[@class='form-control form-control-sm batch_id']"));
			w.click();
			Thread.sleep(1000);
			Select ss=new Select(w);
			ss.selectByVisibleText(Batch);

			WebElement we =driver.findElement(By.xpath("//select[@class='form-control form-control-sm filter_by1']"));
			we.click();
			Thread.sleep(1000);
			Select se=new Select(we);
			se.selectByVisibleText(Sortby);


			//WebElement web =driver.findElement(By.xpath("//select[@class='form-control form-control-sm batch_id_one']"));
			//web.click();
			//Thread.sleep(1000);
			//Select sel=new Select(web);
			//sel.selectByIndex(1);

			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary mr-2 claim_btn']")).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@class='claim_id_select claim_id_select_form']")).click();


			WebElement webel =driver.findElement(By.xpath("//select[@class='form-control form-control-sm secondary_billed']"));
			webel.click();
			Thread.sleep(1000);
			Select selec=new Select(webel);
			selec.selectByVisibleText(Status);


			WebElement webe =driver.findElement(By.xpath("//select[@class='form-control form-control-sm select_option_1']"));
			webe.click();
			Thread.sleep(1000);
			Select sele=new Select(webe);
			sele.selectByVisibleText(Document);


			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();

			secondary.saveReport("Secondary Billing"+date+".html",patientBillhtml(Testcase_ID,
					Sort,
					Batch,
					Sortby ,
					Status,Document));

			//		driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])[2]")).click();






		}
		secondary.saveReport("Secondary Billing"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String Sort,
			String Batch,
			String Sortby ,
			String Status,
			String Document)
	{
		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+Sort+"</td>"+
				"<td>"+Batch+"</td>"+	 
				"<td>"+Sortby +"</td>"+
				"<td>"+Status+"</td>"+
				"<td>"+Document+"</td></tr>";
		return retval;
	}
}
