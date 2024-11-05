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

public class payment {
	
		
		
			public static void main(String[] args) throws InterruptedException, AWTException, IOException {
				
				usercreation pay=new usercreation();

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
				XSSFSheet sheet=wb.getSheet("Payment");
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

				driver.manage().window().maximize();
				Thread.sleep(2000);
				WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
				Actions a = new Actions(driver);
				a.moveToElement(mo).perform();

				Thread.sleep(1000);
				driver.findElement(By.linkText("Patient(S)")).click();
				
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; }td { border: 1px solid black; }</style>\r\n";
				String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>cardname</th><th>number</th><th>cvv</th><th>month</th><th>status</th></tr>";
				 pay.saveReport("Userpayment_"+date+".html",tblcss+"<table border='1'>"+Columnames);
					
				
				for(int i=6;i<=7;i++) {

					XSSFRow cell=sheet.getRow(i);


					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();
					String cardname =cell.getCell(2).getStringCellValue();
					String number=convernumericval(cell.getCell(3).getNumericCellValue());
					String cvv=convernumericval(cell.getCell(4).getNumericCellValue());
					String month=cell.getCell(5).getStringCellValue(); 
					//String year=convernumericval(cell.getCell(3).getNumericCellValue());
					String status=cell.getCell(7).getStringCellValue();
					System.out.println(Testcase_ID);

					driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name); 

					Thread.sleep(4000);
					WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
					search.click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//a[contains(@class,'nav-link')])[10]")).click();
					driver.findElement(By.xpath("//a[contains(@class,'btn btn-sm btn-warning ml-3')]")).click();
					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@class='form-control form-control-sm card_name_add']")).sendKeys(cardname);

					WebElement cl=	driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]"));
					Select n=new Select(cl);
					n.selectByVisibleText(status);



					driver.findElement(By.xpath("//input[@class='form-control form-control-sm card_number']")).sendKeys(number);


					WebElement mon=	driver.findElement(By.xpath("//select[@class='form-control form-control-sm month_add']"));
					Select select =new Select(mon);
					select.selectByVisibleText(month);
					//driver.findElement(By.xpath("//select[contains(@name,'year')]")).click();
				     //Robot r2=new Robot();
				     //r2.keyPress(KeyEvent.VK_DOWN);
				     //r2.keyRelease(KeyEvent.VK_ENTER);
					
				

					Thread.sleep(1000);
					driver.findElement(By.xpath("//input[@placeholder='CVV']")).sendKeys(cvv); 
					Thread.sleep(1000);
					driver.findElement(By.xpath("//button[contains(@class,'btn btn-sm btn-warning save_card')]")).click();

				//	WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
				//	System.out.println("Toastmessage: " + toastMessage.getText());
					//String statusmessage=toastMessage.getText();
					 Thread.sleep(1000);
						WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
						System.out.println("Toastmessage: " + toastMessage.getText());
						  String statusmessage=toastMessage.getText();
						  
					pay.saveReport("Userpayment_"+date+".html",updatePatienthtml(Testcase_ID,
							name,   
							cardname,
							number,
							cvv,
							month,
							status));



					Thread.sleep(2000);
					WebElement m = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
					a.moveToElement(m).perform();

					Thread.sleep(1000);
					driver.findElement(By.linkText("Patient(S)")).click();



				}
				pay.saveReport("Userpayment_"+date+".html","</table>");      

				}
				public static String  updatePatienthtml(String Testcase_ID,
						String name,   
						String cardname,
						String number,
						String cvv,
						String month,
						String status)



				{
					String retval="";

					retval="<tr><td>"+Testcase_ID+"</td>"+
							"<td>"+name+"</td>"+   
							"<td>"+cardname+"</td>"+
							"<td>"+number+"</td>"+
							"<td>"+cvv+"</td>"+
							"<td>"+month+"</td>"+
							"<td>"+status+"</td></tr>";
						

					return retval;




				}
			

			private static String convernumericval(double d)
			{
				String retval="";
				System.out.println(d);
				retval=String.valueOf(d);


				return retval;
			}
		}




