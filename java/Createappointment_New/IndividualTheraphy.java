package Createappointment_New;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import userAuthorization.Noauthorization;
import userAuthorization.Nonbill;

public class IndividualTheraphy {

	public static void main(String[]args) throws InterruptedException, AWTException, IOException {
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String pattern = "MMddyyyyhhMMss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.print(date);
		WebDriver driver=new ChromeDriver();
		driver.get("https://app.therapypms.com");


		FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Individualtheraphy");
		XSSFRow c=sheet.getRow(1);

		String eMail =c.getCell(0).getStringCellValue();
		String password=c.getCell(1).getStringCellValue();


		WebElement username = driver.findElement(By.name("email"));
		username.sendKeys(eMail);
		WebElement pass = driver.findElement(By.name("password"));
		pass.sendKeys(password);
		WebElement signin = driver.findElement(By.xpath("//button[@type='submit']"));
		signin.click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@class='custom-control-label']")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//button[@type='submit'])[1]")).click();
		driver.manage().window().maximize();




		driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
		driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='col-10'])[1]")).click();

		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);


		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>provider</th><th>From</th><th>To</th><th>schedule</th><th>status</th><th>statusmessage</th></tr>";
		saveReport("Individual Therapy"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Create Appointment</center></th>"+Columnames);


		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue();
			String From=cell.getCell(2).getStringCellValue();
			String To=cell.getCell(3).getStringCellValue();
			String notes=cell.getCell(7).getStringCellValue();
			String schedule=cell.getCell(4).getStringCellValue();
			String status=cell.getCell(5).getStringCellValue();
			String provider=cell.getCell(6).getStringCellValue();


			System.out.println(Testcase_ID);



			Robot r=new Robot();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[text()='Select Patient']")).click();

			WebElement n=driver.findElement(By.xpath("//input[@type='search']"));
			n.sendKeys(name);
			r.keyPress(KeyEvent.VK_ENTER);

			//Robot r=new Robot();
			//    r.keyPress(KeyEvent.VK_DOWN);
			//r.keyRelease(KeyEvent.VK_ENTER);
			//driver.findElement(By.xpath("//span[text()='Aameen, Dua']")).click();



			//   WebElement ins = driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[3]"));
			//  ins.click();
			// Thread.sleep(2000);
			//Select select=new Select(ins);
			//select.selectByIndex(2);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[4]")).click();

			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[@class='form-check'])[2]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='multiselect dropdown-toggle custom-select text-center'])[4]")).click();


			Thread.sleep(2000);
			WebElement pn=driver.findElement(By.xpath("//span[@id='select2-sc_provider_id-container']"));
			pn.click();
			Thread.sleep(2000);
			Robot r2=new Robot();
			r2.keyPress(KeyEvent.VK_DOWN);
			r2.keyRelease(KeyEvent.VK_ENTER);
			r2.keyPress(KeyEvent.VK_DOWN);
			r2.keyRelease(KeyEvent.VK_ENTER);
			r2.keyPress(KeyEvent.VK_DOWN);

			r2.keyRelease(KeyEvent.VK_ENTER);
			// Thread.sleep(2000);
			// driver.findElement(By.xpath("(//li[@class='select2-results__option select2-results__option--selectable'])[4]")).click();
			Robot r1=new Robot();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@class='select2-selection__rendered']"));
			WebElement pro=driver.findElement(By.xpath("//input[@class='select2-search__field']"));
			pro.sendKeys(provider);
			r1.keyPress(KeyEvent.VK_ENTER);

			Thread.sleep(1000);
			WebElement we =driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[4]"));
			we.click();

			Thread.sleep(3000);
			Select select3=new Select(we);
			select3.selectByIndex(3);

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]")).click();
			Thread.sleep(1000);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[9]")).click();


			driver.findElement(By.xpath("//button[@class='btn btn-sm btn-warning']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//button[@class='btn btn-danger'])[6]")).click();

			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]")).sendKeys(From);
			driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]")).sendKeys(To);

			driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]")).sendKeys(notes);
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//label[@class='custom-control-label'])[3]")).click();




			driver.findElement(By.xpath("//input[@name='repeat_every']")).click();

			WebElement re = driver.findElement(By.xpath("//select[@name='repeat_each']"));
			Select rs=new Select(re);
			rs.selectByVisibleText(schedule);
			Thread.sleep(1000);
			driver.findElement(By.xpath("//input[@id='datepicker_endpoint']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//td[@class='mc-date mc-date--active'])[23]")).click();

			driver.findElement(By.xpath("(//div[@class='day-name repeat_day'])[5]")).click();

			WebElement st =  driver.findElement(By.xpath("//select[@id='sc_status']"));
			Thread.sleep(1000);
			Select ss=new Select(st);
			ss.selectByVisibleText(status);



			driver.findElement(By.xpath("//button[@id='sc_sub_btn']")).click();
			Thread.sleep(1000);
			//driver.findElement(By.xpath("//button[@class='btn btn-secondary']")).click();


			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText();

			saveReport("Individual Therapy"+date+".html",patientBillhtml(Testcase_ID,
					name,
					provider,
					From ,
					To,
					schedule,
					status,statusmessage));

			Thread.sleep(2000);

			driver.navigate().refresh(); 

			driver.findElement(By.xpath("(//i[contains(@class,'las la-plus')])[1]")).click();
			driver.findElement(By.xpath("//a[contains(@id,'sc_btn')]")).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//div[@class='col-10'])[1]")).click();



		}
		saveReport("Individual Therapy"+date+".html","</table>");

		GroupTheraphy a=new GroupTheraphy();
		a.main(null);

		NonBill b=new NonBill();
		b.main(null);

			No_authIndividual f=new No_authIndividual();
		f.main(null);

			No_authGroup g=new No_authGroup();
		g.main(null);

		Blockoff c1=new Blockoff();
		c1.main(null);

		//	VOB d=new VOB();
		//d.main(null);

		Authrequest e=new Authrequest();
		e.main(null);
	}    
	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String provider,
			String From ,
			String to,
			String schedule,
			String status,
			String statusmessage)


	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+provider+"</td>"+	 
				"<td>"+From +"</td>"+
				"<td>"+to+"</td>"+
				"<td>"+schedule+"</td>"+
				"<td>"+status+"</td>"+
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
