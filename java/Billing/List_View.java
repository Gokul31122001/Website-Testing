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

public class List_View {
	
		
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
			XSSFSheet sheet=wb.getSheet("List_View");
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
			
		
		
		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@class='iq-waves-effect collapsed'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//a[@class='iq-waves-effect'])[3]")).click();
		
		driver.findElement(By.xpath("//input[@class='form-control form-control-sm ses_reportrange reportrange']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[text()='This Month']")).click();
		
		WebElement w=driver.findElement(By.xpath("//select[@class='form-control form-control-sm ses_tx_type']"));
		w.click();
		Thread.sleep(1000);
		Select s=new Select(w);
		s.selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='form-check-input'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@class='form-check-input'])[25]")).click();
		Thread.sleep(1000);
	//	driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[3]")).click();
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("(//input[@class='form-check-input'])[29]")).click();
		Thread.sleep(1000);
		WebElement we=driver.findElement(By.xpath("//select[@class='form-control form-control-sm ses_location']"));
		we.click();
		Thread.sleep(1000);
		Select ss=new Select(we);
		ss.selectByIndex(2);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[4]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@class='form-check-input'])[72]")).click();
		Thread.sleep(1000);
		WebElement web=driver.findElement(By.xpath("//select[@class='form-control form-control-sm notes_avail']"));
		web.click();
		Thread.sleep(1000);
		Select se=new Select(web);
		se.selectByIndex(0);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning go_btn']")).click();
		
		//Thread.sleep(2000);
		//driver.findElement(By.xpath("(//input[@class='check_box  data_checkbox_appoinment'])[1]")).click();
		Thread.sleep(1000);
	    driver.findElement(By.xpath("(//input[contains(@name,'data_checkbox_appoinment')])[3]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//td[@class='checkbox1_td'])[6]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//td[@class='checkbox1_td'])[7]")).click();
		
		WebElement webe=driver.findElement(By.xpath("//select[@class='form-control form-control-sm chnage_session_status']"));
		webe.click();
		Thread.sleep(1000);
		Select sel=new Select(webe);
		sel.selectByIndex(1);
		
		WebElement webel=driver.findElement(By.xpath("//select[@class='form-control form-control-sm update_status_box_select']"));
		webel.click();
		Thread.sleep(1000);
		Select sele=new Select(webel);
		sele.selectByIndex(9);

	}
	}

//class=m-0 single_checkbox claim_checked


