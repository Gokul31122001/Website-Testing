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

public class Insauthorization {

		

			public static void main(String[]args) throws InterruptedException, AWTException, IOException {
				usercreation ins=new usercreation();
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
				XSSFSheet sheet=wb.getSheet("Ins_Author");
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
				WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
				Actions a = new Actions(driver);
				a.moveToElement(mo).perform();

				
				Thread.sleep(1000);
				driver.findElement(By.linkText("Patient(S)")).click();
				
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; }td { border: 1px solid black; }</style>\r\n";
		        String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>description</th><th>insurance</th><th>auhtno</th><th>insno</th><th>cop</th><th>coins</th><th>deductible</th><th>copayper</th><th>notes</th><th>authnotes</th><th>cms4</th><th>cms11</th><th><copay></th><th>type</th><th>scid</th><th>relation</th><th>auid</th><th>statusmessage</th></tr>";
				ins.saveReport("Userauhtorization_"+date+".html",tblcss+"<table border='1'>"+Columnames);

				for(int i=6;i<=7;i++) {

					XSSFRow cell=sheet.getRow(i);

					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();
					String description =cell.getCell(2).getStringCellValue();
					String insurance =cell.getCell(3).getStringCellValue();
					String authno=cell.getCell(4).getStringCellValue();
					String insno=cell.getCell(5).getStringCellValue();
					String cop=cell.getCell(6).getStringCellValue();   

					String coins=convernumericval(cell.getCell(7).getNumericCellValue());

					String deductible=convernumericval(cell.getCell(8).getNumericCellValue());
					String copayper=cell.getCell(9).getStringCellValue();

					String notes =cell.getCell(10).getStringCellValue();
					String authnotes=cell.getCell(11).getStringCellValue();

					String cms4 =cell.getCell(12).getStringCellValue();
					String cms11 =cell.getCell(13).getStringCellValue();
					String copay=convernumericval(cell.getCell(14).getNumericCellValue());
					String type =cell.getCell(15).getStringCellValue();
					String scid =cell.getCell(16).getStringCellValue();

					String relation =cell.getCell(17).getStringCellValue();
					String auid =cell.getCell(18).getStringCellValue();


					System.out.println(Testcase_ID);





					driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name);

					Thread.sleep(4000);
					WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
					search.click();

					driver.findElement(By.xpath("(//a[contains(@class,'nav-link')])[4]")).click();
					driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning dropdown-toggle']")).click();
					driver.findElement(By.xpath("//a[text()='Add Authorization']")).click();



					driver.findElement(By.xpath("//input[contains(@name,'description')]")).sendKeys(description);

					WebElement therapy=driver.findElement(By.xpath("//select[contains(@name,'treatment_type')]"));
					therapy.click();
					Select s1=new Select(therapy);
					s1.selectByVisibleText(type);
					WebElement id=driver.findElement(By.xpath("//select[contains(@name,'supervisor_id')]"));
					id.click();
					Select select=new Select(id);
					select.selectByVisibleText(insurance);
					id.click();

					driver.findElement(By.xpath("//input[contains(@name,'select_date')]")).click();
					driver.findElement(By.xpath("//li[text()='Last 7 Days']")).click();

					driver.findElement(By.xpath("//input[contains(@name,'authorization_number')]")).sendKeys(authno);
					driver.findElement(By.xpath("//input[@class='form-control form-control-sm ins_number']")).sendKeys(insno);

					WebElement c3=driver.findElement(By.xpath("//select[contains(@name,'is_primary')]"));
					c3.click();
					Select s=new Select(c3);
					s.selectByVisibleText(cop);

					WebElement t=driver.findElement(By.xpath("(//button[@class='btn dropdown-toggle btn-light bs-placeholder'])[1]"));
					t.click();

					Robot r=new Robot();
					r.keyPress(KeyEvent.VK_DOWN);
					r.keyPress(KeyEvent.VK_DOWN);
					r.keyPress(KeyEvent.VK_DOWN);
					r.keyPress(KeyEvent.VK_ENTER);

					WebElement t1=driver.findElement(By.xpath("//div[text()='Select a Diagnosis2']"));
					t1.click();

					Robot a1=new Robot();
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyPress(KeyEvent.VK_ENTER);

					WebElement t2=driver.findElement(By.xpath("(//div[@class='filter-option-inner-inner'])[3]"));
					t2.click();

					Robot a2=new Robot();
					a2.keyPress(KeyEvent.VK_DOWN);
					a2.keyPress(KeyEvent.VK_DOWN);
					a2.keyPress(KeyEvent.VK_DOWN);
					a2.keyPress(KeyEvent.VK_DOWN);
					a2.keyPress(KeyEvent.VK_DOWN);
					a2.keyPress(KeyEvent.VK_ENTER);


					WebElement t3=driver.findElement(By.xpath("(//div[@class='filter-option-inner-inner'])[4]"));
					t3.click();
					Robot a3=new Robot();
					a3.keyPress(KeyEvent.VK_DOWN);
					a3.keyPress(KeyEvent.VK_DOWN);
					a3.keyPress(KeyEvent.VK_DOWN);
					a3.keyPress(KeyEvent.VK_DOWN);
					a3.keyPress(KeyEvent.VK_DOWN);
					a3.keyPress(KeyEvent.VK_ENTER);


					driver.findElement(By.xpath("//input[contains(@name,'coins')]")).sendKeys(coins);
					driver.findElement(By.xpath("//input[contains(@name,'deductible')]")).sendKeys(deductible);
					WebElement co=driver.findElement(By.xpath("//select[contains(@name,'copay_per')]"));
					co.click();

					Select c1=new Select(co);
					c1.selectByValue(copayper);

					driver.findElement(By.xpath("//input[contains(@name,'copay')]")).sendKeys(copay);

					driver.findElement(By.xpath("//textarea[contains(@name,'copay_notes')]")).sendKeys(notes);
					driver.findElement(By.xpath("(//textarea[contains(@name,'notes')])[2]")).sendKeys(authnotes);

					driver.findElement(By.xpath("//input[contains(@name,'cms_four')]")).sendKeys(cms4);
					driver.findElement(By.xpath("//input[contains(@name,'csm_eleven')]")).sendKeys(cms11);

					driver.findElement(By.xpath("//a[@class='add_secondary_btn']")).click();
					

					WebElement se=driver.findElement(By.xpath("//select[contains(@name,'sec_payor_id')]"));
					se.click();
				    Robot r1=new Robot();
				    r1.keyPress(KeyEvent.VK_DOWN);
				    r1.keyPress(KeyEvent.VK_DOWN);
				    r1.keyPress(KeyEvent.VK_DOWN);
				    r1.keyPress(KeyEvent.VK_ENTER);

				    
					
					driver.findElement(By.xpath("//input[contains(@name,'sec_uci_id')]")).sendKeys(scid);
					driver.findElement(By.xpath("//input[contains(@name,'sec_auth_no')]")).sendKeys(auid);
					
					WebElement re=driver.findElement(By.xpath("//select[contains(@name,'sec_relationship')]"));
					Select s5=new Select(re);
					s5.selectByVisibleText(relation);

					driver.findElement(By.xpath("//button[contains(@id,'saveauth')]")).click();

					WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
					System.out.println("Toastmessage: " + toastMessage.getText());
					String statusmessage=toastMessage.getText();
					
					ins.saveReport("Userauhtorization_"+date+".html",updatePatienthtml(Testcase_ID,
							 name,
							 description, 
							 insurance, 
							 authno,
							 insno,
							 cop,   
							 coins,
							 deductible,
							 copayper,
							 notes, 
							 authnotes,
							 cms4, 
							 cms11,
							 copay,
							 type, 
							 scid, 
							 relation,
							 auid ,statusmessage));

					Thread.sleep(2000);
					WebElement m = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));

					a.moveToElement(m).perform();
					WebElement first=driver.findElement(By.partialLinkText("Patient(S)"));
					first.click();
				}
				
				ins.saveReport("Userauhtorization_"+date+".html","</table>");      

			}
			public static String  updatePatienthtml(String Testcase_ID,
					String name, 
					String description, 
					String insurance,
					String authno,
					String insno,
					String cop,   
		            String coins,
					String deductible,
					String copayper,
					String notes, 
					String authnotes,
					String cms4, 
					String cms11,
					String copay,
					String type ,
					String scid, 
					String relation,
					String auid,
					String statusmessage)
			
		{
			String retval="";

			retval="<tr><td>"+Testcase_ID+"</td>"+
					"<td>"+name+"</td>"+
					"<td>"+description+"</td>"+
					"<td>"+insurance+"</td>"+
					"<td>"+authno+"</td>"+
					"<td>"+insno+"</td>"+
					"<td>"+cop+"</td>"+
					"<td>"+coins+"</td>"+
					"<td>"+deductible+"</td>"+
					"<td>"+copayper+"</td>"+
					"<td>"+notes+"</td>"+
					"<td>"+authnotes+"</td>"+
					"<td>"+cms4+"</td>"+
					"<td>"+cms11+"</td>"+
					"<td>"+copay+"</td>"+
					"<td>"+type+"</td>"+
					"<td>"+scid+"</td>"+
					"<td>"+relation+"</td>"+
					"<td>"+auid+"</td>"+
					"<td>"+statusmessage+"</td></tr>";
			
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




