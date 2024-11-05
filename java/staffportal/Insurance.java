package staffportal;
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

public class Insurance {


		public static void main(String[]args) throws InterruptedException, AWTException, IOException {
			Createprovider insurance=new Createprovider();
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
			XSSFSheet sheet=wb.getSheet("Insurance");
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
				System.out.println("rowcount :"+rowcount+"colcount"+colcount);
				
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

				String Columnames="<tr><th>Testcase_ID</th><th>Name</th><th>Insurance</th></tr>";
				insurance.saveReport("Staff Insurance"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);


				

				
		
		          Thread.sleep(2000);
		        WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
		         Actions a = new Actions(driver);
		         a.moveToElement(mo).perform();
		         	
		         Thread.sleep(1000);
		         driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
		         
		         
			 		for(int i=6;i<=rowcount;i++) {

			 			XSSFRow cell=sheet.getRow(i);
			 						

			 			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			 			String name =cell.getCell(1).getStringCellValue();
			 			String Insurance=cell.getCell(2).getStringCellValue();

			 			System.out.println(Testcase_ID);


			  			driver.findElement(By.xpath("//input[@class='form-control form-control-sm search_name common_selector']")).sendKeys(name);
			  			
			  			Thread.sleep(2000);
			 			driver.findElement(By.xpath("//a[@class='mr-2']")).click();

			    
			    
			    driver.findElement(By.xpath("(//a[@class='nav-link'])[8]")).click();
			    Thread.sleep(1000);
		
			WebElement b= driver.findElement(By.xpath("//select[@class='form-control-sm form-control all_payor']"));
			Select s=new Select(b);
			s.selectByVisibleText(Insurance);
		
			 //   driver.findElement(By.xpath("(//option[text()='ABA Medicaid'])[1]")).click();
		
			    driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
			    Thread.sleep(2000);
				
			    
			    driver.findElement(By.xpath("(//i[@class='fa fa-times text-danger deleteasspayor'])[3]")).click();
			    
			    insurance.saveReport("Staff Insurance"+date+".html",patientBillhtml(Testcase_ID,
						name,
						Insurance));

		
			    driver.navigate().refresh();
				
		         Thread.sleep(1000);
		         driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();
		         
		
		}
			 		insurance.saveReport("Staff Insurance"+date+".html","</table>");
	}
		public static String  patientBillhtml(String Test_Case_ID,
				String name,
				String Insurance)
		{
			String retval="";

			retval="<tr><td>"+Test_Case_ID+"</td>"+
					"<td>"+name+"</td>"+
					"<td>"+Insurance+"</td></tr>";
			return retval;

		}

}