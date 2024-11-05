package userinfo;
import java.awt.AWTException;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class usercreation {
		
			private 
		 WebElement toastMessage;
			private static WebDriver driver = new ChromeDriver();
			public static void main(String[] args) throws InterruptedException, AWTException, IOException {
				
				
				Locale locale = new Locale("fr", "FR");
				DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
				String
				pattern = "MMddyyyyhhMMss";
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
				String date = simpleDateFormat.format(new Date());
				System.out.print(date);
				
				driver.get("https://app.therapypms.com/");
					FileInputStream file=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
				XSSFWorkbook wb=new XSSFWorkbook(file);
				XSSFSheet sheet=wb.getSheet("user creation");
					XSSFRow cell=sheet.getRow(1);

					
					 String eMail =cell.getCell(0).getStringCellValue();
				   String password=cell.getCell(3).getStringCellValue();
				   

				WebElement username = driver.findElement(By.name("email"));
				username.sendKeys(eMail);
				
				WebElement pass = driver.findElement(By.name("password"));
				pass.sendKeys(password);
				
				
				WebElement signin = driver.findElement(By.xpath("//button[@type='submit']"));
				signin.click();
				clickpluspatient();
				/*
				WebElement image = driver.findElement(By.xpath("(//img[@alt='tpms'])[2]"));
				Actions a = new Actions(driver);
		  		a.moveToElement(image).perform();
			
				WebElement patients = driver.findElement(By.partialLinkText("Patient(S)"));
				patients.click();
				driver.manage().window().maximize();
				
				WebElement k = driver.findElement(By.xpath("(//i[contains(@class,'las')])[6]"));
				k.click();
				
				
				WebElement Create  = driver.findElement(By.xpath("(//a[@class='dropdown-item'])[1]"));
			    Create.click();
			    Thread.sleep(2000);
			    
				*/
			    Robot r = new Robot();
				 
		   //  driver.findElement(By.xpath("//input[contains(@type,'text')])[1]"));
			   // String uid="";
			//LocalDate date = LocalDate.now();
			  //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmSS");
			    //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");  
			  //  LocalDateTime now = LocalDateTime.now();  
			  //  System.out.println(dtf.format(now));  
			   // String nameval="kumar"+dtf.format(now);
			    int rowcount=sheet.getLastRowNum();
				int colcount=sheet.getRow(0).getLastCellNum();
				System.out.println("rowcount :"+rowcount+"colcount"+colcount);
				String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";
						
				String Columnames="<tr><th>Test_Case_ID</th><th>name</th><th>lastname </th><th>gender</th><th>location</th><th>parent</th><th>parentlastname</th><th>email</th><th>job</th><th>mobiletype</th><th>status</th><th>insurance</th><th>Additional</th><th>Out put</th></tr>";
				saveReport("Usercreation_"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Patient Creation</center></th>"+Columnames);


			    for(int i=6;i<=rowcount;i++) {
			    	XSSFRow c=sheet.getRow(i);
			    		  
			        
			    	String Test_Case_ID =c.getCell(0).getStringCellValue(); 
			    	String name =c.getCell(1).getStringCellValue(); 
			    	String lastname =c.getCell(2).getStringCellValue();
			    	String gender=c.getCell(3).getStringCellValue();
			    	String location=c.getCell(4).getStringCellValue();
			    	String parent=c.getCell(5).getStringCellValue();
			    	String parentlastname=c.getCell(6).getStringCellValue();
			    	String email=c.getCell(7).getStringCellValue();
			    	String job=c.getCell(8).getStringCellValue();
			    	String mobiletype=c.getCell(9).getStringCellValue();
			    	String status=c.getCell(10).getStringCellValue();
			    	String insurance=c.getCell(11).getStringCellValue();
			    	String Additional=c.getCell(12).getStringCellValue();
		         System.out.println(name);

			    Robot robot=new Robot();
		         driver.findElement(By.name("client_first_name")).sendKeys(name);

			     driver.findElement(By.name("client_last_name")).sendKeys(lastname);
			     
			     driver.findElement(By.name("client_dob")).sendKeys("12/10/2002");
			     driver.findElement(By.name("client_gender")).sendKeys(gender);
			  WebElement pos=  driver.findElement(By.name("location"));
			  Select s=new Select(pos);
			  s.selectByVisibleText(location);
			    
			     driver.findElement(By.name("parent_first_name")).sendKeys(parent);
			     driver.findElement(By.name("parent_last_name")).sendKeys(parentlastname);
			     driver.findElement(By.name("email")).sendKeys(email);
			     driver.findElement(By.name("email_type")).sendKeys(job);
			     driver.findElement(By.name("email_reminder")).click();
			     driver.findElement(By.name("phone_type")).sendKeys(mobiletype);
			     driver.findElement(By.name("is_send_sms")).click();
			     driver.findElement(By.name("active_status")).sendKeys(status);
			    driver.findElement(By.xpath("(//span[@class='select2-selection__rendered'])[1]"));
			    robot.keyPress(KeyEvent.VK_DOWN);
			    
			     driver.findElement(By.className("ri-add-fill")).click();
			     

			     driver.findElement(By.name("other_details")).sendKeys(Additional);
			     
			     driver.findElement(By.id("info_save_btn_modal")).click();
			     JavascriptExecutor js=(JavascriptExecutor)driver;
			     WebElement scroll=driver.findElement(By.xpath("//label[text()='Portal Invitation']"));
			     js.executeScript("arguments[0].scrollIntoView(true)",scroll);
			     
			    Thread.sleep(2000);
			    
			   //driver.findElement(By.xpath("//button[text()='Proceed']")).click();
			  
			    
			  //for() 
			 
			     
			   
			    // driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
			     
			     
			   
			
		    
		   //	System.out.println("Toastmessage: " + toastMessage.getText());*/
			     Thread.sleep(3000);
			     WebElement toastMessage = driver.findElement(By.xpath("//div[contains(@id,'toast-container')]"));
					System.out.println("Toastmessage: " + toastMessage.getText());
				//	saveReport("Usercreation_"+date+".txt","Test_Case_ID"+Test_Case_ID+"User Name:"+name+" Output:"+toastMessage.getText()+"\n");
					
					saveReport("Usercreation_"+date+".html",createPatienthtml(Test_Case_ID,
					    	name,
					    	lastname ,
					    	gender,
					    	location,
					    	parent,
					    	parentlastname,
					    	email,
					    	job,
					    	mobiletype,
					    	status,
					    	insurance,
					    	Additional,toastMessage.getText()));
					if(toastMessage.getText().equals("Patient Successfully Created"))
					  {
						  driver.findElement(By.xpath("//button[text()='Proceed']")).click();
						  r.keyPress(KeyEvent.VK_ENTER);
						 	Thread.sleep(3000);
					  }  
					  else
					  {	driver.findElement(By.xpath("(//button[@class='close'])[2]")).click();
					   r.keyPress(KeyEvent.VK_ENTER);
					 	Thread.sleep(2000);
					  }
				  
		   	clickpluspatient();
			}
			    saveReport("Usercreation_"+date+".html","</table>");
			   
			    
			    vob b=new vob();
			    b.main(null);
			    
			    Insauthorization c=new Insauthorization();
			   c.main(null);
			//   Document d=new Document();
			  //  d.main(null);
			    payment e=new payment();
			    e.main(null);
			    
			    callLog f=new callLog();
			    f.main(null);
			    
			    userprofile a =new userprofile();
			    a.main(null);
			  //  clinicalteam f=new clinicalteam();
			   // f.main(null);
			}
			 private static void clickpluspatient()
			    {
				 driver.manage().window().maximize();
					WebElement image = driver.findElement(By.xpath("(//img[@alt='tpms'])[2]"));
					Actions a = new Actions(driver);
			  		a.moveToElement(image).perform();
				
					WebElement patients = driver.findElement(By.partialLinkText("Patient(S)"));
					patients.click();
							
							
					
					WebElement k = driver.findElement(By.xpath("(//i[contains(@class,'las')])[6]"));
					k.click();
					
					
					WebElement Create  = driver.findElement(By.xpath("(//a[@class='dropdown-item'])[1]"));
				    Create.click();
				    try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 
			   }
			 
			 
			 public static String  createPatienthtml(String Test_Case_ID,
				    	String name,
				    	String lastname ,
				    	String gender,
				    	String location,
				    	String parent,
				    	String parentlastname,
				    	String email,
				    	String job,
				    	String mobiletype,
				    	String status,
				    	String insurance,
				    	String Additional,
				    	String statusmessage)
			 {
				 String retval="";
				 
				 retval="<tr><td>"+Test_Case_ID+"</td>"+
			    	"<td>"+name+"</td>"+
			    	"<td>"+lastname +"</td>"+
			    	"<td>"+gender+"</td>"+
			    	"<td>"+location+"</td>"+
			    	"<td>"+parent+"</td>"+
			    	"<td>"+parentlastname+"</td>"+
			    	"<td>"+email+"</td>"+
			    	"<td>"+job+"</td>"+
			    	"<td>"+mobiletype+"</td>"+
			    	"<td>"+status+"</td>"+
			    	"<td>"+insurance+"</td>"+
			    	"<td>"+Additional+"</td>"+
			    	"<td>"+statusmessage+"</td></tr>";
				 return retval;
			 }
			 
			 public static void saveReport(String filename,String file_value)
			 {
			 Path path
		     = Paths.get("C:\\Users\\Automation\\Testoutput"+filename);

		 // Custom string as an input
		 String str
		     = "Geeks for Geeks \nWelcome to computer science portal \nHello Geek";

		 // Try block to check for exceptions
		 try {
		     // Now calling Files.writeString() method
		     // with path , content & standard charsets
		     Files.writeString(path, file_value,
		    		 StandardOpenOption.CREATE,
						StandardOpenOption.APPEND);
		 }

		 // Catch block to handle the exception
		 catch (IOException ex) {
		     // Print messqage exception occurred as
		     // invalid. directory local path is passed
		     System.out.print("Invalid Path");
		 }
			 }
			 
			 
			 
			 
		}




