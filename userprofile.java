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

public class userprofile {
	

	
			public static void main(String[]args) throws InterruptedException, AWTException, IOException {
				usercreation padd=new usercreation();
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
				XSSFSheet sheet=wb.getSheet("patient info");
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



				//driver.findElement(By.className("img-fluid logo-small")).click();
				Thread.sleep(2000);
				WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
				Actions a = new Actions(driver);
				a.moveToElement(mo).perform();
				Thread.sleep(1000);
				driver.findElement(By.partialLinkText("Patient(S)")).click();
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; }td { border: 1px solid black; }</style>\r\n";

				String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>preferd</th><th>midlle</th><th>street</th><th>city</th><th>contactname</th><th>issue</th><th>emergency_contact</th><th>provider</th><th>fax</th><th>relation</th><th>memeberId</th><th>referringid</th><th>npi</th><th>phone1</th><th>phone2</th><th>type1</th><th>type2</th><th>email</th><th>type3</th><th>mail</th><th>behaviour</th><th>refer1</th><th>D1</th><th>D2</th><th>D3</th><th>D4</th><th>Mental</th><th>refer2</th><th>D11</th><th>D22</th><th>D33</th><th>D44</th><th>Multi</th><th>refer3</th><th>D111</th><th>D222</th><th>D333</th><th>D444</th><th>Music</th><th>refer4</th><th>D1111</th><th>D2222</th><th>D3333</th><th>D4444</th><th>occupational</th><th>refer5</th><th>A1</th><th>A2</th><th>A3</th><th>A4</th><th>physical</th><th>refer6</th><th>A11</th><th>A22</th><th>A33</th><th>A44</th><th>speech</th><th>refer7</th><th>A111</th><th>A222</th><th>A333</th><th>A444</th><th>statusmessage</th></tr>";
		      padd.saveReport("Userprofileupdation_"+date+".html",tblcss+"<table border='1'>"+Columnames);
				for(int i=7;i<=8;i++) {

					XSSFRow cell=sheet.getRow(i);
					String Testcase_ID=cell.getCell(0).getStringCellValue(); 
					String name =cell.getCell(1).getStringCellValue();   
					String preferd=cell.getCell(2).getStringCellValue();
					String midlle=cell.getCell(3).getStringCellValue();
					String street=cell.getCell(4).getStringCellValue();
					String city=cell.getCell(5).getStringCellValue();
					String contactname=cell.getCell(6).getStringCellValue();
					String issue=cell.getCell(7).getStringCellValue();
					String emergency_contact= convernumericval(cell.getCell(8).getNumericCellValue());
					String provider= convernumericval(cell.getCell(9).getNumericCellValue());
					String fax= convernumericval(cell.getCell(10).getNumericCellValue());
					String relation=cell.getCell(11).getStringCellValue();
					String memeberId= convernumericval(cell.getCell(12).getNumericCellValue());
					String referringid=cell.getCell(13).getStringCellValue();
					String npi= convernumericval(cell.getCell(14).getNumericCellValue());
					String phone1= convernumericval(cell.getCell(15).getNumericCellValue());
					String phone2= cell.getCell(16).getRawValue();
					String type1=cell.getCell(17).getStringCellValue();
					String type2=cell.getCell(18).getStringCellValue();
					String email=cell.getCell(19).getStringCellValue();
					String type3=cell.getCell(20).getStringCellValue();
					String mail=cell.getCell(21).getStringCellValue();
					//String first=cell.getCell(22).getStringCellValue();
					//String last=cell.getCell(23).getStringCellValue();
					//String place=cell.getCell(24).getStringCellValue();
					String behaviour=cell.getCell(25).getStringCellValue();
					String refer1=cell.getCell(26).getStringCellValue();
					String D1=cell.getCell(27).getStringCellValue();
					String D2=cell.getCell(28).getStringCellValue();
					String D3=cell.getCell(29).getStringCellValue();
					String D4=cell.getCell(30).getStringCellValue();
					String Mental=cell.getCell(31).getStringCellValue();
					String refer2=cell.getCell(32).getStringCellValue();
					String D11=cell.getCell(33).getStringCellValue();
					String D22=cell.getCell(34).getStringCellValue();
					String D33=cell.getCell(35).getStringCellValue();
					String D44=cell.getCell(36).getStringCellValue();
					String Multi=cell.getCell(37).getStringCellValue();
					String refer3=cell.getCell(38).getStringCellValue();
					String D111=cell.getCell(39).getStringCellValue();
					String D222=cell.getCell(40).getStringCellValue();
					String D333=cell.getCell(41).getStringCellValue();
					String D444=cell.getCell(42).getStringCellValue();
					String Music=cell.getCell(43).getStringCellValue();
					String refer4=cell.getCell(44).getStringCellValue();
					String D1111=cell.getCell(45).getStringCellValue();
					String D2222=cell.getCell(46).getStringCellValue();
					String D3333=cell.getCell(47).getStringCellValue();
					String D4444=cell.getCell(48).getStringCellValue();
					String occupational=cell.getCell(49).getStringCellValue();
					String refer5=cell.getCell(50).getStringCellValue();
					String A1=cell.getCell(51).getStringCellValue();
					String A2=cell.getCell(52).getStringCellValue();
					String A3=cell.getCell(53).getStringCellValue();
					String A4=cell.getCell(54).getStringCellValue();
					String physical=cell.getCell(55).getStringCellValue();
					String refer6=cell.getCell(56).getStringCellValue();
					String A11=cell.getCell(57).getStringCellValue();
					String A22=cell.getCell(58).getStringCellValue();
					String A33=cell.getCell(59).getStringCellValue();
					String A44=cell.getCell(60).getStringCellValue();
					String speech=cell.getCell(61).getStringCellValue();
					String refer7=cell.getCell(62).getStringCellValue();
					String A111=cell.getCell(63).getStringCellValue();
					String A222=cell.getCell(64).getStringCellValue();
					String A333=cell.getCell(65).getStringCellValue();
					String A444=cell.getCell(66).getStringCellValue();


					System.out.println(Testcase_ID);
					Thread.sleep(1000);
					driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name); 

					//			Robot r=new Robot();
					//			r.keyPress(KeyEvent.VK_DOWN);
					//			r.keyRelease(KeyEvent.VK_ENTER);
					Thread.sleep(4000);
					WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
					search.click();

					//			Robot r=new Robot();
					//		     r.keyPress(KeyEvent.VK_DOWN);
					//			r.keyRelease(KeyEvent.VK_ENTER);
					//			Select s = new Select(search);
					//			s.selectByIndex(1);
					//search.click();
					//			Actions ab = new Actions(driver);
					//	         ab.doubleClick(search).perform();

					//driver.findElement(By.xpath("//a[contains(@class,'mr-2')])[1]")).click();

					WebElement mi= driver.findElement(By.xpath("//input[contains(@name,'client_middle')]"));
					mi.clear();
					mi.sendKeys(midlle);
					WebElement p=	driver.findElement(By.name("preferred_name"));
					p.clear();
					p.sendKeys(preferd);


					WebElement Relationship = driver.findElement(By.xpath("(//select[contains(@name,'relationship')])[1]"));
					Relationship.click();
					Select s =new Select(Relationship);
					s.selectByVisibleText(relation);

					// Robot r=new Robot();
					//  r.keyPress(KeyEvent.VK_DOWN);
					// r.keyRelease(KeyEvent.VK_ENTER);
					WebElement ad= driver.findElement(By.name("client_street"));
					ad.clear();
					ad.sendKeys(street);
					WebElement area=    driver.findElement(By.name("client_city"));
					area.clear();
					area.sendKeys(city);
					driver.findElement(By.name("client_state")).click();
					Robot r11=new Robot();
					r11.keyPress(KeyEvent.VK_DOWN);
					r11.keyPress(KeyEvent.VK_DOWN);
					r11.keyRelease(KeyEvent.VK_ENTER);
					WebElement zip=  driver.findElement(By.name("client_zip"));
					zip.clear();
					zip.sendKeys("621316");
					driver.findElement(By.xpath("//select[contains(@name,'default_pos')]")).click();
					Robot r12=new Robot();
					r12.keyPress(KeyEvent.VK_DOWN);
					r12.keyPress(KeyEvent.VK_DOWN);
					r12.keyPress(KeyEvent.VK_DOWN);
					r12.keyRelease(KeyEvent.VK_ENTER);

					WebElement Region = driver.findElement(By.xpath("//select[contains(@name,'zone')]"));
					Region.click();
					Robot r1=new Robot();
					r1.keyPress(KeyEvent.VK_DOWN);
					r1.keyPress(KeyEvent.VK_DOWN);
					r1.keyRelease(KeyEvent.VK_ENTER);

					driver.findElement(By.xpath("//input[@class='form-control form-control-sm phone_e']")).sendKeys(phone1);
					WebElement t1=driver.findElement(By.xpath("//select[contains(@name,'client_phone_type')]"));
					t1.click();
					Select s1=new Select(t1);
					s1.selectByVisibleText(type1);

					driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])[1]")).click();
					driver.findElement(By.xpath("//input[@class='form-control form-control-sm phone_appned phone_e']")).sendKeys(phone2);

					WebElement t2=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[3]"));
					t2.click();
					Select s2=new Select(t2);
					s2.selectByVisibleText(type2);
					driver.findElement(By.xpath("//input[@class='form-check-input extra_sms']")).click();


					WebElement t3=driver.findElement(By.xpath("//select[contains(@name,'client_email_type')]"));
					t3.click();
					Select s3=new Select(t3);
					s3.selectByVisibleText(type3);
					driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])[2]")).click();


					driver.findElement(By.xpath("//input[contains(@name,'new_email[]')]")).sendKeys(mail);

					WebElement t4=driver.findElement(By.xpath("//select[contains(@name,'new_email_type[]')]"));
					t4.click();
					Select s4=new Select(t4);
					s3.selectByVisibleText("Home");


					//	Thread.sleep(1000);
					//driver.findElement(By.xpath("(//input[@class='form-check-input is_gran'])")).click();
					//Thread.sleep(2000);
					//driver.findElement(By.xpath("//input[contains(@name,'guarantor_first_name')]")).sendKeys(first);
					//Thread.sleep(1000);
					//driver.findElement(By.xpath("//input[contains(@name,'guarantor_last_name')]")).sendKeys(last);	
					//driver.findElement(By.xpath("//button[@class='btn btn-sm btn-secondary']")).click();


					WebElement  in=   driver.findElement(By.xpath("(//select[contains(@name,'payor_id')])[1]"));
					in.click();
					 Robot r2=new Robot();
					    r2.keyPress(KeyEvent.VK_DOWN);
					    r2.keyPress(KeyEvent.VK_DOWN);
					    r2.keyPress(KeyEvent.VK_DOWN);
					    r2.keyPress(KeyEvent.VK_ENTER);


					driver.findElement(By.name("member_id")).sendKeys(memeberId);
					WebElement is=   driver.findElement(By.xpath("//textarea[contains(@name,'client_notes')]"));
					is.clear();
					is.sendKeys(issue);

					WebElement phone=driver.findElement(By.name("referring_provider_phone"));
					phone.clear();
					phone.sendKeys(provider);

					driver.findElement(By.xpath("//input[contains(@name,'emergency_contact_email')]")).sendKeys(mail);


					driver.findElement(By.name("race_ethnicity")).click();
					Robot r10=new Robot();
					r10.keyPress(KeyEvent.VK_DOWN);
					r10.keyRelease(KeyEvent.VK_ENTER);
					WebElement lang = driver.findElement(By.name("preferred_language"));
					lang.click();
					Robot a1=new Robot();
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyPress(KeyEvent.VK_DOWN);
					a1.keyRelease(KeyEvent.VK_ENTER);
					WebElement trtmt = driver.findElement(By.xpath("(//select[contains(@name,'treatment_type')])[1]"));	
					trtmt.click(); 
					Robot r21=new Robot();
					r21.keyPress(KeyEvent.VK_DOWN);
					r21.keyPress(KeyEvent.VK_DOWN);
					r21.keyPress(KeyEvent.VK_DOWN);
					r21.keyRelease(KeyEvent.VK_ENTER);
					WebElement pdr = driver.findElement(By.name("default_provider"));	
					pdr.click(); 
					Robot r22=new Robot();
					r22.keyPress(KeyEvent.VK_DOWN);
					r22.keyPress(KeyEvent.VK_DOWN);
					r22.keyPress(KeyEvent.VK_DOWN);
					r22.keyRelease(KeyEvent.VK_ENTER);
					WebElement emergencyname=  driver.findElement(By.name("emergency_contact_name"));
					emergencyname.clear();
					emergencyname.sendKeys(contactname);

					WebElement nme = driver.findElement(By.name("emergency_relationship"));	
					nme.click(); 
					Robot r23=new Robot();			     
					r23.keyPress(KeyEvent.VK_DOWN);
					r23.keyPress(KeyEvent.VK_DOWN);
					r23.keyRelease(KeyEvent.VK_ENTER);
					WebElement eme= driver.findElement(By.name("emergency_contact_phone"));
					eme.clear();
					eme.sendKeys( emergency_contact);
					WebElement emema=driver.findElement(By.xpath("//input[contains(@name,'emergency_contact_email')]"));
					emema.clear();
					emema.sendKeys(email);


					Thread.sleep(1000);
					driver.findElement(By.xpath("//a[@class='btn btn-sm btn-warning text-white']")).click();

					WebElement th1=driver.findElement(By.xpath("//select[contains(@name,'phy_type[]')][1]"));
					Select s6=new Select(th1);
					s6.selectByVisibleText(behaviour);

					WebElement ref1=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[1]"));
					Select s7=new Select(ref1);
					s7.selectByVisibleText(refer1);
					WebElement beh1=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis1[]')])[1]"));
					beh1.clear();
					beh1.sendKeys(D1);
					WebElement beh2=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[1]"));
					beh2.clear();
					beh2.sendKeys(D2);
					WebElement beh3=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis3[]')])[1]"));
					beh3.clear();
					beh3.sendKeys(D3);
					WebElement beh4=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis4[]')])[1]"));
					beh4.sendKeys(D4);

					WebElement th2=driver.findElement(By.xpath("(//select[contains(@name,'phy_type[]')])[2]"));
					Select s8=new Select(th2);
					s8.selectByVisibleText(Mental);

					WebElement ref2=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[2]"));
					Select s9=new Select(ref2);
					s9.selectByVisibleText(refer2);
					WebElement Men1=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[2]"));
					Men1.clear();
					Men1.sendKeys(D11);
					WebElement Men2	=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[2]"));
					Men2.clear();
					Men2.sendKeys(D22);
					WebElement Men3	=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]"));
					Men3.clear();
					Men3.sendKeys(D33);
					WebElement Men4=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]"));
					Men4.clear();
					Men4.sendKeys(D44);

					WebElement th3=driver.findElement(By.xpath("(//select[contains(@name,'phy_type[]')])[3]"));
					Select s10=new Select(th3);
					s10.selectByVisibleText(Multi);

					WebElement ref3=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[3]"));
					Select s11=new Select(ref3);
					s11.selectByVisibleText(refer3);
					WebElement Mul1=  driver.findElement(By.xpath("(//input[contains(@name,'diagnosis1[]')])[3]"));
					Mul1.clear();
					Mul1.sendKeys(D111);
					WebElement Mul2=		driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[3]"));
					Mul2.sendKeys(D222);
					WebElement Mul3=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis3[]')])[3]"));
					Mul3.clear();
					Mul3.sendKeys(D333);
					WebElement Mul4=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis4[]')])[3]"));
					Mul4.clear();
					Mul4.sendKeys(D444);


					WebElement th4=driver.findElement(By.xpath("(//select[contains(@name,'phy_type[]')])[4]"));
					Select s12=new Select(th4);
					s12.selectByVisibleText(Music);

					WebElement ref4=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[4]"));
					Select s13=new Select(ref4);
					s13.selectByVisibleText(refer4);
					WebElement Mus1=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis1[]')])[4]"));
					Mus1.clear();
					Mus1.sendKeys(D1111);
					WebElement Mus2=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[4]"));
					Mus2.clear();
					Mus2.sendKeys(D2222);
					WebElement Mus3=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis3[]')])[4]"));
					Mus3.clear();
					Mus3.sendKeys(D3333);
					WebElement Mus4=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis4[]')])[4]"));
					Mus4.clear();
					Mus4.sendKeys(D4444);

					WebElement th5=driver.findElement(By.xpath("(//select[contains(@name,'phy_type[]')])[5]"));
					Select s14=new Select(th5);
					s14.selectByVisibleText(occupational);

					WebElement ref5=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[5]"));
					Select s15=new Select(ref5);
					s15.selectByVisibleText(refer5);
					WebElement occu1=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis1[]')])[5]"));
					occu1.clear();
					occu1.sendKeys(A1);
					WebElement occu2=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[5]"));
					occu2.clear();
					occu2.sendKeys(A2);
					WebElement occu3=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis3[]')])[5]"));
					occu3.clear();
					occu3.sendKeys(A3);
					WebElement occu4=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis4[]')])[5]"));
					occu4.clear();
					occu4.sendKeys(A4);
					WebElement th6=driver.findElement(By.xpath("(//select[contains(@name,'phy_type[]')])[6]"));
					Select s16=new Select(th6);
					s16.selectByVisibleText(physical);

					WebElement ref6=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[6]"));
					Select s17=new Select(ref6);
					s17.selectByVisibleText(refer6);
					WebElement phy1=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis1[]')])[6]"));
					phy1.clear();
					phy1.sendKeys(A11);
					WebElement phy2=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[6]"));
					phy2.clear();
					phy2.sendKeys(A22);
					WebElement phy3=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis3[]')])[6]"));
					phy3.clear();
					phy3.sendKeys(A33);
					WebElement phy4=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis4[]')])[6]"));
					phy4.clear();
					phy4.sendKeys(A44);

					WebElement th7=driver.findElement(By.xpath("(//select[contains(@name,'phy_type[]')])[7]"));
					Select s18=new Select(th7);
					s18.selectByVisibleText(speech);

					WebElement ref7=driver.findElement(By.xpath("(//select[@name='client_reffered_by[]'])[7]"));
					Select s19=new Select(ref7);
					s19.selectByVisibleText(refer7);
					WebElement spee1=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis1[]')])[7]"));
					spee1.clear();
					spee1.sendKeys(A111);
					WebElement spee2=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis2[]')])[7]"));
					spee2.clear();
					spee2.sendKeys(A222);
					WebElement spee3=driver.findElement(By.xpath("(//input[contains(@name,'diagnosis3[]')])[7]"));
					spee3.clear();
					spee3.sendKeys(A333);
					WebElement spee4=	driver.findElement(By.xpath("(//input[contains(@name,'diagnosis4[]')])[7]"));
					spee4.clear();
					spee4.sendKeys(A444);






					WebElement providerid =  driver.findElement(By.name("referring_provider_id"));
					providerid.click();
					Select data =new Select(providerid);
					data.selectByVisibleText(referringid);

					WebElement f=  driver.findElement(By.xpath("//input[@placeholder='Fax Number']"));
					f.clear();
					f.sendKeys(fax);

					WebElement r= driver.findElement(By.name("referring_provider_npi"));
					r.clear();
					r.sendKeys(npi);


					 driver.findElement(By.xpath("(//button[contains(@id,'info_save_btn')])[1]")).click();

					WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
					System.out.println("Toastmessage: " + toastMessage.getText());
					String statusmessage=toastMessage.getText();

					padd.saveReport("Userprofileupdation_"+date+".html",updatePatienthtml(Testcase_ID,
							name,   
							preferd,
							midlle,
							street,
							city,
							contactname,
							issue,
							emergency_contact,
							provider,
							fax,
							relation,
							memeberId,
							referringid,
							npi,
							phone1,
							phone2,
							type1,
							type2,
							email,
							type3,
							mail,
							behaviour,
							refer1,
							D1,
							D2,
							D3,
							D4,
							Mental,
							refer2,
							D11,
							D22,
							D33,
							D44,
							Multi,
							refer3,
							D111,
							D222,
							D333,
							D444,
							Music,
							refer4,
							D1111,
							D2222,
							D3333,
							D4444,
							occupational,
							refer5,
							A1,
							A2,
							A3,
							A4,
							physical,
							refer6,
							A11,
							A22,
							A33,
							A44,
							speech,
							refer7,
							A111,
							A222,
							A333,
							A444,statusmessage));
					Thread.sleep(2000);
					WebElement m = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));

					a.moveToElement(m).perform();
					driver.findElement(By.partialLinkText("Patient(S)")).click();





				}


				padd.saveReport("Userprofileupdation_"+date+".html","</table>");      

			}
			public static String  updatePatienthtml(String Testcase_ID,
					String name,   
					String preferd,
					String midlle,
					String street,
					String city,
					String contactname,
					String issue,
					String emergency_contact,
					String provider,
					String fax,
					String relation,
					String memeberId,
					String referringid,
					String npi,
					String phone1,
					String phone2,
					String type1,
					String type2,
					String email,
					String type3,
					String mail,
					String behaviour,
					String refer1,
					String D1,
					String D2,
					String D3,
					String D4,
					String Mental,
					String refer2,
					String D11,
					String D22,
					String D33,
					String D44,
					String Multi,
					String refer3,
					String D111,
					String D222,
					String D333,
					String D444,
					String Music,
					String refer4,
					String D1111,
					String D2222,
					String D3333,
					String D4444,
					String occupational,
					String refer5,
					String A1,
					String A2,
					String A3,
					String A4,
					String physical,
					String refer6,
					String A11,
					String A22,
					String A33,
					String A44,
					String speech,
					String refer7,
					String A111,
					String A222,
					String A333,
					String A444,
					String statusmessage)
			{
				String retval="";

				retval="<tr><td>"+Testcase_ID+"</td>"+
						"<td>"+name+"</td>"+   
						"<td>"+preferd+"</td>"+
						"<td>"+midlle+"</td>"+
						"<td>"+street+"</td>"+
						"<td>"+city+"</td>"+
						"<td>"+contactname+"</td>"+
						"<td>"+issue+"</td>"+
						"<td>"+emergency_contact+"</td>"+
						"<td>"+provider+"</td>"+
						"<td>"+fax+"</td>"+
						"<td>"+relation+"</td>"+
						"<td>"+memeberId+"</td>"+
						"<td>"+referringid+"</td>"+
						"<td>"+npi+"</td>"+
						"<td>"+phone1+"</td>"+
						"<td>"+phone2+"</td>"+
						"<td>"+type1+"</td>"+
						"<td>"+type2+"</td>"+
						"<td>"+email+"</td>"+
						"<td>"+type3+"</td>"+
						"<td>"+mail+"</td>"+
						"<td>"+behaviour+"</td>"+
						"<td>"+refer1+"</td>"+
						"<td>"+D1+"</td>"+
						"<td>"+D2+"</td>"+
						"<td>"+D3+"</td>"+
						"<td>"+D4+"</td>"+
						"<td>"+Mental+"</td>"+
						"<td>"+refer2+"</td>"+
						"<td>"+D11+"</td>"+
						"<td>"+D22+"</td>"+
						"<td>"+D33+"</td>"+
						"<td>"+D44+"</td>"+
						"<td>"+Multi+"</td>"+
						"<td>"+refer3+"</td>"+
						"<td>"+D111+"</td>"+
						"<td>"+D222+"</td>"+
						"<td>"+D333+"</td>"+
						"<td>"+D444+"</td>"+
						"<td>"+Music+"</td>"+
						"<td>"+refer4+"</td>"+
						"<td>"+D1111+"</td>"+
						"<td>"+D2222+"</td>"+
						"<td>"+D3333+"</td>"+
						"<td>"+D4444+"</td>"+
						"<td>"+occupational+"</td>"+
						"<td>"+refer5+"</td>"+
						"<td>"+A1+"</td>"+
						"<td>"+A2+"</td>"+
						"<td>"+A3+"</td>"+
						"<td>"+A4+"</td>"+
						"<td>"+physical+"</td>"+
						"<td>"+refer6+"</td>"+
						"<td>"+A11+"</td>"+
						"<td>"+A22+"</td>"+
						"<td>"+A33+"</td>"+
						"<td>"+A44+"</td>"+
						"<td>"+speech+"</td>"+
						"<td>"+refer7+"</td>"+
						"<td>"+A111+"</td>"+
						"<td>"+A222+"</td>"+
						"<td>"+A333+"</td>"+
						"<td>"+A444+"</td>"+
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





