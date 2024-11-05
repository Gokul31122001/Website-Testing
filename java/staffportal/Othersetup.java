package staffportal;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import userAuthorization.Billin;

public class Othersetup {



	public static void main(String[]args) throws InterruptedException, AWTException, IOException {
		Createprovider Other=new Createprovider();
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
		XSSFSheet sheet=wb.getSheet("Othersetup");
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

			String Columnames="<tr><th>Testcase_ID</th><th>Name</th><th>Behavioral</th><th>Mentalhealth</th><th>Speech</th><th>Occupational</th><th>Physical</th><th>Music</th><th>Multi</th><th>Id1</th><th>Id2</th><th>Id3</th><th>Id4</th><th>Id5</th><th>Id6</th><th>Id7</th><th>statusmessage</th></tr>";
			Other.saveReport("Othersetup"+date+".html",tblcss+"<table border='1'><tr><th colspan=14><center>Staff Portal</center></th>"+Columnames);

	
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
	 			String Behavioral=cell.getCell(2).getRawValue();
	 			String Mentalhealth=cell.getCell(3).getRawValue();
	 			String Speech =cell.getCell(4).getRawValue();
	 			String Occupational=cell.getCell(5).getRawValue();
	 			String Physical	=cell.getCell(6).getRawValue();
	 			String Music=cell.getCell(7).getRawValue();
	 			String Multi=cell.getCell(8).getRawValue();
	 			String Id1=cell.getCell(9).getStringCellValue();
	 			String Id2=cell.getCell(10).getStringCellValue();
	 			String Id3=cell.getCell(11).getStringCellValue();
	 			String Id4=cell.getCell(12).getStringCellValue();
	 			String Id5=cell.getCell(13).getStringCellValue();
	 			String Id6=cell.getCell(14).getStringCellValue();
	 			String Id7=cell.getCell(15).getStringCellValue();
	 			
	 			
	 			

	 			System.out.println(Testcase_ID);


	  			driver.findElement(By.xpath("//input[@class='form-control form-control-sm search_name common_selector']")).sendKeys(name);
	  			
	  			Thread.sleep(2000);
	 			driver.findElement(By.xpath("//a[@class='mr-2']")).click();

		    
		    
		    
		    
		    driver.findElement(By.xpath("(//a[@class='nav-link'])[6]")).click();
		    Thread.sleep(1000);
		    
		    
		    
		  WebElement w1=  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[1]"));
		  w1.clear();
		  w1.sendKeys(Behavioral);
		 WebElement w2= driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[2]"));
		 w2.clear();
		 w2.sendKeys( Mentalhealth);
		  WebElement w3=  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]"));
		  w3.clear();
		  w3.sendKeys(Speech);
		   WebElement w4= driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]"));
		   w4.clear();
		   w4.sendKeys(Occupational);
		    WebElement w5=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[5]"));
		    w5.clear();
		    w5.sendKeys(Physical);
		  WebElement w6=  driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[6]"));
		  w6.clear();
		  w6.sendKeys(Music);
		   WebElement w7= driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[7]"));
		   w7.clear();
		   w7.sendKeys(Multi);
		    
		    Thread.sleep(1000);
		    
		    WebElement w= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[1]"));
		    w.click();
		    Select s= new Select(w);
			  s.selectByVisibleText(Id1);
			  Thread.sleep(1000);
		  WebElement ww= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[2]"));
		  ww.click();
		  Select ss= new Select(ww);
		  ss.selectByVisibleText(Id2);
		  Thread.sleep(1000);
		  WebElement we= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[3]"));
		  we.click();
	   Select se= new Select(we);
	  se.selectByVisibleText(Id3);
		    
	  Thread.sleep(1000);
		   WebElement web= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[4]"));
		   web.click();
		   Select sel= new Select(web);
		  sel.selectByVisibleText(Id4);
		  Thread.sleep(1000);
		    
		  WebElement webe= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[5]"));
		   webe.click();
		   Select sele= new Select(webe);
		   sele.selectByVisibleText(Id5);
		   Thread.sleep(1000);
			   WebElement webelem= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[6]"));
			    webelem.click();
	     	    Select selec= new Select(webelem);
			  selec.selectByVisibleText(Id6);
			  Thread.sleep(1000);
		    
			  WebElement webeleme= driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[7]"));
			webeleme.click();
			 Select select= new Select(webeleme);
			 select.selectByVisibleText(Id7);
			 Thread.sleep(1000);
		    
			 driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
		    
		    
				Thread.sleep(1000);
				WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
				System.out.println("Toastmessage: " + toastMessage.getText());
				String statusmessage=toastMessage.getText(); 
				
				Other.saveReport("Othersetup"+date+".html",patientBillhtml(Testcase_ID,
						name,
						Behavioral,
						Mentalhealth ,
						Speech,
						Occupational,
						Physical,
						Music,
						Multi,
			            Id1,
			            Id2,
			            Id3,
			            Id4,
			            Id5,
			            Id6,
			            Id7,statusmessage));
				
				driver.navigate().refresh();
				
		         Thread.sleep(1000);
		         driver.findElement(By.xpath("//i[@class='fa fa-user-md']")).click();	    
		    
		    

}
	 	Other.saveReport("Othersetup"+date+".html","</table>");
}
	public static String  patientBillhtml(String Test_Case_ID,
			String name,
			String Behavioral,
			String Mentalhealth ,
			String Speech,
			String Occupational,
			String Physical,
			String Music,
			String Multi,
			String Id1,
			String Id2,
			String Id3,
			String Id4,
			String Id5,
			String Id6,
			String Id7,
			String statusmessage)
	
	{
		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+
				"<td>"+name+"</td>"+
				"<td>"+Behavioral +"</td>"+
				"<td>"+Mentalhealth +"</td>"+
				"<td>"+Speech+"</td>"+
				"<td>"+Occupational+"</td>"+
				"<td>"+Physical+"</td>"+
				"<td>"+Music +"</td>"+
				"<td>"+Multi +"</td>"+
				"<td>"+Id1 +"</td>"+
				"<td>"+Id2 +"</td>"+
				"<td>"+Id3 +"</td>"+
				"<td>"+Id4 +"</td>"+
				"<td>"+Id5 +"</td>"+
				"<td>"+Id6 +"</td>"+
				"<td>"+Id7 +"</td>"+
				"<td>"+statusmessage+"</td></tr>";
		return retval;

	}		

}
