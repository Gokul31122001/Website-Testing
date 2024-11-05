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
public class Eligibility {

	public static void main(String[] args) throws InterruptedException, AWTException  {
		WebDriver driver=new ChromeDriver();
		driver.get("https://app.therapypms.com");
		driver.findElement(By.name("email")).sendKeys("admin@admin.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Niyaz@1007");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//label[@class='custom-control-label'])[1]")).click();	
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		driver.manage().window().maximize();




		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt, 'tpms')][2]"));
		Actions a = new Actions(driver);

		a.moveToElement(mo).perform();

		driver.findElement(By.xpath("//i[@class='ri-exchange-dollar-line']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//i[@class='las la-address-card']")).click();		
		Thread.sleep(1000);


		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='form-check-input'])[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@class='form-check-input'])[7]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control form-control-sm reportrange']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//li[text()='Last Year']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary view_btn select-form-grid-btn getData']")).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath("(//button[@type='button'])[27]")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//span[@class='select2-selection select2-selection--single'])[1]")).click();
		Thread.sleep(1000);
		WebElement web=driver.findElement(By.xpath("//input[@class='select2-search__field']"));
		web.sendKeys("Zaid, Ahmed");
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//select[@class='form-control form-control-sm eligibility_auth']")).click();
		Robot r2=new Robot();
		r2.keyPress(KeyEvent.VK_DOWN);
		r2.keyPress(KeyEvent.VK_DOWN);
		r2.keyPress(KeyEvent.VK_ENTER);

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='form-control form-control-sm date-time eligibility_datetime']")).sendKeys("11/04/2024");

	WebElement s=driver.findElement(By.xpath("//select[@class='form-control form-control-sm eligibility_npi']"));
		Select select=new Select(s);
		select.selectByVisibleText("Ansiya A Ansar (npi)");
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@id='request_eligibility']")).click();
		















	}
}


