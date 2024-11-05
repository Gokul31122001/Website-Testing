package userinfo;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Document {



		public static void main(String[] args) throws InterruptedException, AWTException, IOException {
				
			usercreation document=new usercreation();
				
				WebDriver driver = new ChromeDriver();
				driver.get("https://app.therapypms.com");
				
				Locale locale = new Locale("fr", "FR");
				DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
				String pattern = "MMddyyyyhhMMss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(new Date());
				System.out.print(date);



				FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				XSSFSheet sheet=wb.getSheet("Document");
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
			    WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
			     Actions a = new Actions(driver);
			     a.moveToElement(mo).perform();
			     
			     int rowcount=sheet.getLastRowNum();
					int colcount=sheet.getRow(0).getLastCellNum();
					System.out.println("rowcount :"+rowcount+"colcount"+colcount);
			     	
			     Thread.sleep(1000);
			     driver.findElement(By.linkText("Patient(S)")).click();
			     
			     String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; }td { border: 1px solid black; }</style>\r\n";
			     String Columnames="<tr><th>Testcase_ID</th><th>name</th></tr>";
			     document.saveReport("Userdocument_"+date+".html",tblcss+"<table border='1'>"+Columnames);
			     for(int i=6;i<=6;i++) {

						XSSFRow cell=sheet.getRow(i);

						String Testcase_ID=cell.getCell(0).getStringCellValue(); 
						String name =cell.getCell(1).getStringCellValue();
					    System.out.println(Testcase_ID);

						driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name);
						
						Thread.sleep(4000);
						WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
						search.click();
			     Thread.sleep(1000);
			 	driver.findElement(By.xpath("//a[@class='nav-link  ']")).click();
			     
			 WebElement provider=driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[1]"));
			 provider.click();
			 
			 driver.findElement(By.xpath("//button[@title='Beckett, Anne']")).click();
			 	
			 //	driver.findElement(By.xpath("(//button[@class='dropdown-item multiselect-all'])[1]")).click();
			 	Thread.sleep(1000);
			 	//driver.findElement(By.xpath("(//span[@class='multiselect-selected-text'])[1]")).click();
			     
			     Thread.sleep(1000);
			 	driver.findElement(By.xpath("//select[@class='form-control form-control-sm ses_location']")).click();
			     Robot r=new Robot();
			     r.keyPress(KeyEvent.VK_DOWN);
			     r.keyRelease(KeyEvent.VK_DOWN);
			 	driver.findElement(By.xpath("//select[@class='form-control form-control-sm ses_location']")).click();
			     Thread.sleep(1000);
			     driver.findElement(By.xpath("//input[@class='form-control form-control-sm ses_reportrange reportrange']")).click();
			     driver.findElement(By.xpath("//li[text()='This Year']")).click();
			     
			     Thread.sleep(1000);
			     driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning go_btn']")).click();
			     Thread.sleep(9000);
			     driver.findElement(By.xpath("(//span[@class='badge badge-primary'])[3]")).click();
			     
			     
			     document.saveReport("Userdocument_"+date+".html",updatePatienthtml(Testcase_ID,
							name));  
						
			     }
			     document.saveReport("Userdocument_"+date+".html","</table>");      

					}
					public static String  updatePatienthtml(String Testcase_ID,
							String name)
					
					{
						String retval="";

						retval="<tr><td>"+Testcase_ID+"</td>"+
								"<td>"+name+"</td></tr>";
						
						return retval;
			

		}

		}




