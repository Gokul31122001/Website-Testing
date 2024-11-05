package Billing;
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
import org.openqa.selenium.support.ui.Select;

public class Manage {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
		Primarybilling manage=new Primarybilling();
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
		XSSFSheet sheet=wb.getSheet("Manage_Billing");
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
		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();

		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>Sort</th><th>Batch</th><th>Status</tr>";
		manage.saveReport("Manage Billing"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Billing</center></th>"+Columnames);




		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();

		driver.findElement(By.xpath("//i[@class='ri-bill-line']")).click();


		driver.findElement(By.xpath("//a[@class='nav-link']")).click();

		for(int i=6;i<=6;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String Sort =cell.getCell(1).getStringCellValue();
			String Batch=cell.getCell(2).getRawValue();
			String Status=cell.getCell(3).getStringCellValue();
			System.out.println(Testcase_ID);


			driver.findElement(By.xpath("//input[@class='form-control form-control-sm mc_date_range reportrange']")).click();


			WebElement wds=driver.findElement(By.xpath("(//li[text()='Last 7 Days'])[1]"));
			wds.click();
			//a.doubleClick(wds).perform();


			//driver.findElement(By.linkText("(//li[text()='Custom Range'])[1]")).click();

			Thread.sleep(2000);
			//driver.findElement(By.xpath("//td[@class='today active start-date available']")).click();

			//driver.findElement(By.xpath("//td[@class='today available']")).click();
			//		driver.findElement(By.xpath("(//td[@class='in-range available'])[15]")).click();
			//		driver.findElement(By.xpath("(//td[@class='in-range available'])[15]")).click();



			//driver.findElement(By.xpath("//td[@class='active start-date active end-date available']")).click();
			//Thread.sleep(1000);
			//driver.findElement(By.xpath("(//button[@class='applyBtn btn btn-sm btn-primary'])[1]")).click();



			WebElement w=driver.findElement(By.xpath("//select[@class='form-control form-control-sm filter_by']"));
			w.click();
			Thread.sleep(1000);
			Select s=new Select(w);
			s.selectByVisibleText(Sort);


			WebElement ws=driver.findElement(By.xpath("//select[@class='form-control form-control-sm batch_id']"));
			ws.click();
			Thread.sleep(1000);
			Select ss=new Select(ws);
			ss.selectByVisibleText(Batch);



			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning mr-2 claim_btn']")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//input[@class='claim_id_select claim_id_select_form']")).click();


			WebElement wsa=driver.findElement(By.xpath("//select[@class='form-control form-control-sm select_option_1']"));
			wsa.click();
			Thread.sleep(1000);
			Select sea=new Select(wsa);
			sea.selectByVisibleText(Status);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])[1]")).click();


		manage.saveReport("Manage Billing"+date+".html",patientBillhtml(Testcase_ID,
					Sort,
					Batch,
					Status));







		}
		manage.saveReport("Primary Billing"+date+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String Sort,
			String Batch,
			String Status)
	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+Sort+"</td>"+
				"<td>"+Batch+"</td>"+	 
				"<td>"+Status+"</td></tr>";
		return retval;
}
}