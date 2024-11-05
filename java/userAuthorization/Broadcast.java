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
import org.openqa.selenium.support.ui.Select;

public class Broadcast {



		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			WebDriver driver=new ChromeDriver();
			driver.get("https://app.therapypms.com");
			
				
			Locale locale = new Locale("fr", "FR");
			DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
			String pattern = "MMddyyyyhhMMss";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			System.out.print(date);

			FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
			XSSFWorkbook wb=new XSSFWorkbook(fi);
			XSSFSheet sheet=wb.getSheet("vob auth");
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
			
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//a[@class='dropdown-item'])[7]")).click();
			Thread.sleep(1000);

			
				 

					WebElement we=driver.findElement(By.xpath("//select[@class='form-control form-control-sm broadcast_user_type']"));
			         Select s=new Select(we);
			         s.selectByVisibleText("Patient");
			         Thread.sleep(1000);
			         WebElement web=driver.findElement(By.xpath("(//span[@class='select2-selection select2-selection--multiple'])[2]"));
			         web.click();
					 Thread.sleep(1000);
					 Robot r=new Robot();
						Thread.sleep(1000);
						r.keyPress(KeyEvent.VK_DOWN);
						r.keyPress(KeyEvent.VK_DOWN);
						r.keyPress(KeyEvent.VK_DOWN);
						r.keyPress(KeyEvent.VK_DOWN);
						r.keyRelease(KeyEvent.VK_ENTER);
					driver.findElement(By.xpath("(//li[@class='select2-results__option select2-results__option--selectable'])[2]")).click();
						Thread.sleep(1000);
						
					
					driver.findElement(By.xpath("//textarea[@class='form-control form-control-sm broadcast_message']")).sendKeys("ok");
						driver.findElement(By.xpath("//button[@class='btn btn-warning send_broadcast']")).click();
						Thread.sleep(1000);
						WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
						System.out.println("Toastmessage: " + toastMessage.getText());
						String statusmessage=toastMessage.getText();	
					
			 }
}

