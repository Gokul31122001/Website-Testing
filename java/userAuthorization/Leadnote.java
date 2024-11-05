package userAuthorization;
import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Leadnote {
	

		public static void main(String[]args) throws InterruptedException, AWTException {
			WebDriver driver=new ChromeDriver();
			driver.get("https://app.therapypms.com");
			
				
				driver.findElement(By.name("email")).sendKeys("admin@admin.com");
				driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Niyaz@1007");
				driver.findElement(By.xpath("//button[@type='submit']")).click();
				driver.manage().window().maximize();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("(//a[@class='dropdown-item'])[7]")).click();
				Thread.sleep(1000);
				
				driver.findElement(By.xpath("//a[@class='chat_data']")).click();
				Thread.sleep(1000);
				driver.findElement(By.xpath("//input[@name='follow_up_date']")).sendKeys("02.02.2003");
				//WebElement we=driver.findElement(By.xpath("//select[@class='form-control form-control-sm notes_status215']"));
		         //we.click();
		        // Thread.sleep(1000);
		         //Select s=new Select(we);
		         //s.selectByIndex(4);
		         driver.findElement(By.xpath("//i[@class='las la-file-pdf text-primary mr-2']")).click();
		       //  Thread.sleep(1000);
		        // driver.findElement(By.xpath("//textarea[@class='form-control form-control-sm notes_textarea notes215']")).sendKeys("text");
		       
		      //   driver.findElement(By.xpath("//div[@class='btn-comment ml-auto my-1  d-flex justify-content-between']")).click();
		     //    driver.findElement(By.xpath("//input[@class='my-2 attachments215']")).click();
		         Thread.sleep(1000);
		         driver.findElement(By.xpath("//button[@class='btn btn-info add_comment my-2']")).click();
				
				
				
	}
	}


