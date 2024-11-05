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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class AR_Ledger {

		
		public static void main(String[] args) throws InterruptedException, AWTException, IOException  {
			Locale locale = new Locale("fr", "FR");
			DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
			String pattern = "MMddyyyyhhMMss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String d = simpleDateFormat.format(new Date());
			System.out.print(d);

			
			
			WebDriver driver=new ChromeDriver();
			driver.get("https://app.therapypms.com");
			
			FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFSheet sheet=wb.getSheet("AR_Ledger");
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
		
		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//i[@class='ri-article-line']")).click();
		
		for(int i=6;i<=6;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String Sortby =cell.getCell(1).getStringCellValue();
			String Type =cell.getCell(2).getStringCellValue();
			System.out.println(Testcase_ID);
		
	
		WebElement w=driver.findElement(By.xpath("//select[@class='form-control form-control-sm sort_by']"));

		Thread.sleep(1000);
		Select s=new Select(w);
		s.selectByVisibleText(Sortby);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[1]")).click();
		
		driver.findElement(By.xpath("(//button[@class='dropdown-item multiselect-all'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control form-control-sm reportrange']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[text()='Last 30 Days']")).click();

		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary view_btn']")).click();
		
		Thread.sleep(6000);
		driver.findElement(By.xpath("(//input[@type='checkbox'])[189]")).click();
		
		Thread.sleep(2000);
		
	WebElement st=driver.findElement(By.xpath("//select[@class='form-control form-control-sm select_btn ledger_transa_status']"));
	st.click();
	Select s2=new Select(st);
	s2.selectByVisibleText(Type);
		
	Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,700)");
		
		
		
		
		

	}
	}

}
