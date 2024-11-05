package userinfo;
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

public class vob {

		
			public static void main(String[] args) throws InterruptedException, IOException {
				usercreation vob=new usercreation();

				WebDriver driver = new ChromeDriver();
				driver.get("https://app.therapypms.com");

				Locale locale = new Locale("fr", "FR");
				DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
				String pattern = "MMddyyyyhhMMss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String d = simpleDateFormat.format(new Date());
				System.out.print(d);

				FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(fi);
				XSSFSheet sheet=wb.getSheet("VOB");
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
				String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>street</th><th>city</th><th>state</th><th>zip</th><th>cpt</th><th>Diagnosis</th><th>refname</th><th>contact</th><th>insurance</th><th>polcy</th><th>grpnum</th><th>adition</th><th>date</th><th>statusmessage</th></tr>";
				vob.saveReport("Uservob_"+d+".html",tblcss+"<table border='1'>"+Columnames);

				for(int i=6;i<=rowcount;i++) {

					XSSFRow cell=sheet.getRow(i);
					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();  
					String street =cell.getCell(2).getStringCellValue();
					String city =cell.getCell(3).getStringCellValue();
					String state =cell.getCell(4).getStringCellValue();
					String zip=convernumericval(cell.getCell(5).getNumericCellValue());
					String cpt =cell.getCell(6).getStringCellValue();
					String Diagnosis =cell.getCell(7).getStringCellValue();
					String refname =cell.getCell(8).getStringCellValue();
					String contact=convernumericval(cell.getCell(9).getNumericCellValue());
					String insurance =cell.getCell(10).getStringCellValue();
					String polcy =cell.getCell(11).getStringCellValue();
					String grpnum =cell.getCell(12).getStringCellValue();
					String adition =cell.getCell(13).getStringCellValue();
					String date =cell.getCell(14).getStringCellValue();

					System.out.println(Testcase_ID);



					driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name);



					Thread.sleep(4000);
					WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
					search.click();


					driver.findElement(By.xpath("(//a[contains(@class,'nav-link')])[3]")).click();
					driver.findElement(By.linkText("Create VOB Request")).click();
					WebElement s=driver.findElement(By.xpath("//input[contains(@id,'address')]"));
					s.clear();
					s.sendKeys(street);
					WebElement t =	driver.findElement(By.xpath("//input[contains(@id,'city')]"));
					t.clear();
					t.sendKeys(city);
					WebElement st=driver.findElement(By.xpath("//input[contains(@id,'state')]"));
					st.clear();
					st.sendKeys(state);

					WebElement z=driver.findElement(By.xpath("//input[contains(@id,'zip')]"));
					z.clear();
					z.sendKeys(zip);
					driver.findElement(By.xpath("//input[contains(@id,'home')]")).click();
					driver.findElement(By.xpath("//input[contains(@id,'cpt')]")).sendKeys(cpt);
					driver.findElement(By.xpath("//input[contains(@id,'diagnose')]")).sendKeys(Diagnosis);
					driver.findElement(By.xpath("(//input[contains(@id,'st')])[2]")).click();
					driver.findElement(By.xpath("//input[contains(@id,'mt')]")).click();
					driver.findElement(By.xpath("//input[contains(@id,'rfname')]")).sendKeys(refname);
					driver.findElement(By.xpath("//input[contains(@id,'rfnpi')]")).sendKeys(contact);

					driver.findElement(By.xpath("(//input[contains(@id,'insuranc-name')])[1]")).sendKeys(insurance);
					driver.findElement(By.xpath("(//input[contains(@id,'policy-no')])[1]")).sendKeys(polcy);
					driver.findElement(By.xpath("(//input[contains(@id,'group-no')])[1]")).sendKeys(grpnum);
					driver.findElement(By.xpath("//textarea[contains(@name,'additona_text')]")).sendKeys(adition);


					driver.findElement(By.xpath("(//input[contains(@name,'ins_yes')])[1]")).click();
					driver.findElement(By.xpath("(//input[contains(@name,'signature')])[1]")).click();


					driver.findElement(By.xpath("//input[contains(@name,'sing_date')]")).sendKeys(date);


					driver.findElement(By.xpath("//button[contains(@type,'submit')]")).click();

					WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
					System.out.println("Toastmessage: " + toastMessage.getText());
					String statusmessage=toastMessage.getText();

					vob.saveReport("Uservob_"+d+".html",updatePatienthtml(Testcase_ID,
							name,
							street,
							city,
							state,
							zip,
							cpt,
							Diagnosis,
							refname,
							contact,
							insurance,
							polcy,
							grpnum,
							adition,
							date,statusmessage));





					Thread.sleep(2000);
					WebElement m = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));

					a.moveToElement(m).perform();
					WebElement first=driver.findElement(By.partialLinkText("Patient(S)"));
					first.click();



				}

				vob.saveReport("Uservob_"+d+".html","</table>");

			}
			public static String  updatePatienthtml(String Testcase_ID,
					String name,   
					String street,
					String city,
					String state,
					String zip,
					String cpt,
					String Diagnosis,
					String refname,
					String contact,
					String insurance,
					String polcy,
					String grpnum,
					String adition,
					String date,
					String statusmessage)

			{
				String retval="";

				retval="<tr><td>"+Testcase_ID+"</td>"+
						"<td>"+name+"</td>"+   
						"<td>"+street+"</td>"+
						"<td>"+city+"</td>"+
						"<td>"+state+"</td>"+
						"<td>"+zip+"</td>"+   
						"<td>"+cpt+"</td>"+
						"<td>"+Diagnosis+"</td>"+
						"<td>"+refname+"</td>"+   
						"<td>"+contact+"</td>"+
						"<td>"+insurance+"</td>"+
						"<td>"+polcy+"</td>"+   
						"<td>"+grpnum+"</td>"+
						"<td>"+adition+"</td>"+
						"<td>"+date+"</td>"+   
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




