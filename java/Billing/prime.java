package Billing;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class prime {

		
		public static void main(String[] args) throws InterruptedException, AWTException  {
			WebDriver driver=new ChromeDriver();
			driver.get("https://app.therapypms.com");
			driver.findElement(By.name("email")).sendKeys("admin@admin.com");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Niyaz@1007");
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			driver.manage().window().maximize();
			
		
		
		
		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();

		driver.findElement(By.xpath("//i[@class='ri-bill-line']")).click();

		driver.findElement(By.xpath("//input[@class='form-control form-control-sm select_date']")).click(); Thread.sleep(1000);

		driver.findElement(By.xpath("//td[@class='mc-date mc-date--active mc-date--picked mc-date--today']")).click();

		driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-warning'])[1]")).click();

		Thread.sleep(1000);

		WebElement w= driver.findElement(By.xpath("//select[@class='form-control form-control-sm insurance_type']"));

		w.click();

		Thread.sleep(1000);

		Select s=new Select(w);

		s.selectByIndex(1);

		WebElement ww=driver.findElement(By.xpath("(//span[@class='multiselect-selected-text']) [1]"));

		ww.click();

		Thread.sleep(2000);

		driver.findElement(By.xpath("(//span[@class='form-check']) [2]")).click();

		Thread.sleep(1000);

		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[2]")).click();

		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@class='form-check-input']) [5]")).click();

		driver.findElement(By.xpath("//button[@class='btn btn-sm mr-1 view_btn btn-primary']")).click();

		Thread.sleep(1000); 
		driver.findElement(By.xpath("(//td[@class='checkbox1_td'])[1]")).click();

		WebElement webe= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]")); 
		webe.click();
		Thread.sleep(1000);
		Select sw=new Select(webe);
		sw.selectByIndex(2);
		Thread.sleep(1000);
		 WebElement was=driver.findElement(By.xpath("//select[@class='form-control form-control-sm generate_batch_action']"));
		 was.click();
		 Thread.sleep(1000);
		 Robot r=new Robot();
		 r.keyPress(KeyEvent.VK_DOWN);
		 r.keyRelease(KeyEvent.VK_ENTER);
		 
		
	driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
		
		
		
		
		
		
		
		
		

		}
	}


