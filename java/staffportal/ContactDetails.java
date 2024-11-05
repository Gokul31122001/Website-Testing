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

public class ContactDetails {


	public static void main(String[]args) throws InterruptedException, AWTException, IOException {
		Createprovider Contact=new Createprovider();
		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String
		pattern = "MMddyyyyhhMMss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		System.out.print(date);
		WebDriver driver=new ChromeDriver();
		driver.get("https://app.therapypms.com");


		FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Contact");
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

		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();



		int rowcount=sheet.getLastRowNum();
		int colcount=sheet.getRow(0).getLastCellNum();
		System.out.println("rowcount :"+rowcount+"colcount"+colcount);

        String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";
		
		String Columnames="<tr><th>Testcase_ID</th><th>name</th><th>Address1</th><th>Address2</th><th>City</th><th>State</th><th>State</th><th>Zip</th><th>Mobile</th><th>Fax</th><th>Type</th><th>Additional</th><th>Emergencyname</th><th>Emergency Adress</th><th>City2</th><th>State2</th><th>Zip2</th><th>Number</th><th>Fax2</th><th>Type2</th><th>Notes</th><th>statusmessage</th></tr>";
		Contact.saveReport("StaffContact Details"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);


		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);

			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue();
			String Address1=cell.getCell(2).getStringCellValue();
			String Address2=cell.getCell(3).getStringCellValue();
			String City=cell.getCell(4).getStringCellValue();
			String State=cell.getCell(5).getStringCellValue();
			String Zip=cell.getCell(6).getRawValue();
			String Mobile=cell.getCell(7).getStringCellValue();
			String Fax=cell.getCell(8).getRawValue();
			String Type=cell.getCell(9).getStringCellValue();
			String Addtional=cell.getCell(10).getStringCellValue();
			String Emergencyname=cell.getCell(11).getStringCellValue();
			String Emergaddress1=cell.getCell(12).getStringCellValue();
			String Emergaddress2=cell.getCell(13).getStringCellValue();
			String City2=cell.getCell(14).getStringCellValue();
			String State2=cell.getCell(15).getStringCellValue();
			String Zip2=cell.getCell(16).getRawValue();
			String Number=cell.getCell(17).getStringCellValue();
			String Fax2=cell.getCell(18).getRawValue();
			String Type2=cell.getCell(19).getStringCellValue();
			String Notes=cell.getCell(20).getStringCellValue();
			System.out.println(Testcase_ID);



			driver.findElement(By.xpath("//input[@class='form-control form-control-sm search_name common_selector']")).sendKeys(name);

			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@class='mr-2']")).click();


			driver.findElement(By.xpath("(//a[@class='nav-link'])[1]")).click();


			WebElement a1 =  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]"));
			a1.clear();
			a1.sendKeys(Address1);

			WebElement a2 = driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]"));
			a2.clear();
			a2.sendKeys(Address2);
			WebElement a3= driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]"));
			a3.clear();
			a3.sendKeys(City);

			WebElement webl=  driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]"));
			webl.click();
			Thread.sleep(1000);  
			Select ss = new Select(webl);
			ss.selectByVisibleText(State); 

			Thread.sleep(1000);  

			WebElement a4=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[5]"));
			a4.clear();
			a4.sendKeys(Zip);
			WebElement a5=  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[6]"));
			a5.clear();
			a5.sendKeys(Mobile);
			WebElement a6=  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[7]"));
			a6.clear();
			a6.sendKeys(Fax);
			WebElement a7= driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[8]"));
			a7.clear();
			a7.sendKeys(Type);


			WebElement a8=  driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[1]"));
			a8.clear();
			a8.sendKeys(Addtional);
			driver.findElement(By.xpath("(//button[@class='btn btn-sm btn-primary'])")).click();

			Thread.sleep(2000); 
			driver.findElement(By.xpath("(//a[@class='btn btn-primary text-left btn-block w-100'])[2]")).click();

			Thread.sleep(2000); 
			WebElement s=    driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[9]"));
			s.clear();
			s.sendKeys(Emergencyname);


			WebElement s1=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[11]"));
			s1.clear();
			s1.sendKeys(Emergaddress1);
			WebElement s2=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[12]"));
			s2.clear();
			s2.sendKeys(Emergaddress2);
			WebElement s3=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[13]"));
			s3.clear();
			s3.sendKeys(City2);
			WebElement webe= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[2]"));
			webe.click();
			Thread.sleep(1000); 
			Select se = new Select(webe);
			se.selectByVisibleText(State2);


			WebElement s4=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[14]"));
			s4.clear();
			s4.sendKeys(Zip2);
			Thread.sleep(1000);
			WebElement s5=  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[15]"));
			s5.clear();
			s5.sendKeys(Number);
			WebElement s6=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[16]"));
			s6.clear();
			s6.sendKeys(Fax2);
			WebElement s7=   driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[17]"));
			s7.clear();
			s7.sendKeys(Type2);

			Thread.sleep(1000);
			WebElement s8=    driver.findElement(By.xpath("(//textarea[@class='form-control form-control-sm'])[2]"));
			s8.clear();
			s8.sendKeys(Notes);
			driver.findElement(By.xpath("//button[@class='btn btn btn-primary']")).click();

			Thread.sleep(1000);
			WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			System.out.println("Toastmessage: " + toastMessage.getText());
			String statusmessage=toastMessage.getText(); 
			
			Contact.saveReport("StaffContact Details"+date+".html",patientBillhtml(Testcase_ID,
					name,
					Address1,
					Address2,
					City,
					State,
					Zip,
					Mobile,
					Fax,
					Type,
					Addtional,
					Emergencyname,
					Emergaddress1,
					Emergaddress2,
					City2,
					State2,
					Zip2,
					Number,
					Fax2,
					Type2,
					Notes,statusmessage));


			Thread.sleep(1000);

			driver.navigate().refresh();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();








		}			    

		Contact.saveReport("StaffContact Details"+date+".html","</table>");
	      
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String Address1,
			String Address2,
			String City,
			String State,
			String Zip,
			String Mobile,
			String Fax,
			String Type,
			String Addtional,
			String Emergencyname,
			String Emergaddress1,
			String Emergaddress2,
			String City2,
			String State2,
			String Zip2,
			String Number,
			String Fax2,
			String Type2,
			String Notes,
	    	String statusmessage)
	
	 {
		 
		 String retval="";
		 
		 retval="<tr><td>"+Test_Case_ID+"</td>"+
				 "<td>"+name+"</td>"+
					"<td>"+Address1+"</td>"+
					"<td>"+Address2+"</td>"+
					"<td>"+City+"</td>"+
					"<td>"+State+"</td>"+
					"<td>"+Zip+"</td>"+
					"<td>"+Mobile+"</td>"+
					"<td>"+Fax+"</td>"+
					"<td>"+Type+"</td>"+
					"<td>"+Addtional+"</td>"+
					"<td>"+Emergencyname+"</td>"+
					"<td>"+Emergaddress1+"</td>"+
					"<td>"+Emergaddress2+"</td>"+
					"<td>"+City2+"</td>"+
					"<td>"+State2+"</td>"+
					"<td>"+Zip2+"</td>"+
					"<td>"+Number+"</td>"+
					"<td>"+Fax2+"</td>"+
					"<td>"+Type2+"</td>"+
					"<td>"+Notes+"</td>"+
	    	"<td>"+statusmessage+"</td></tr>";
		 return retval;
	
}


}