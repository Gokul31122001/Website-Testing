package Edit_Vob;
import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class edit_vob {

	public static void main(String[] args) throws InterruptedException, AWTException, IOException  {

		Locale locale = new Locale("fr", "FR");
		DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		String pattern = "MMddyyyyhhMMss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String d = simpleDateFormat.format(new Date());
		System.out.print(d);
		WebDriver driver=new ChromeDriver();
		driver.get("https://app.therapypms.com");


		FileInputStream fi=new FileInputStream("C:\\Users\\Admin.DESKTOP-IKQO0G1\\eclipse-workspace\\Amromed\\Testdata\\Testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fi);
		XSSFSheet sheet=wb.getSheet("Edit_Vob");
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

		String tblcss="<style>table { border-spacing: 3px 10px; border-collapse: collapse; border: 1px solid red; }th { border: 1px solid blue; background:#AAC9FF; }td { border: 1px solid black; } .th_title {background:#7fb5da;}</style>\r\n";

		String Columnames="<tr><th>Testcase_ID</th><th>Name</th><th>Insurance</th><th>Benefit</th><th>Therapy</th><th>Relation</th><th>Id</th><th>Effective</th><th>Termination</th><th>Plan</th><th>Other</th><th>Groupname</th><th>Groupno</th><th>Phoneno</th><th>Gender</th><th>Street</th><th>City</th><th>State</th><th>Zip</th><th>Copay</th><th>Amount</th><th>Co-ins</th><th>Plan Ins</th><th>Ded</th><th>Ded Met</th><th>Fam</th><th>Fam Met</th><th>OOP Ind</th><th>OOP Met</th><th>OOP Fam</th><th>OOP Fam Met</th><th>Benefit</th><th>Max Benefit</th><th>Comment</th><th>Ref No</th><th>Ref Name</th><th>Insurance2</th><th>Id2</th><th>Effective2</th><th>Termination2</th><th>Plan2</th><th>Other2</th><th>Groupname2</th><th>Groupno2</th><th>Phoneno2</th><th>Copay2</th><th>Amount2</th><th>Co-ins2</th><th>Plan Ins2</th><th>Ded2</th><th>Ded Met2</th><th>Fam2</th><th>Fam Met2</th><th>OOP Ind2</th><th>OOP Met2</th><th>OOP Fam2</th><th>OOP Fam Met2</th><th>Benefit2</th><th>Max Benefit2</th></tr>";
		saveReport("Edit_Vob"+d+".html",tblcss+"<table border='1'><tr><th colspan=14><center>VOB Authentication</center></th>"+Columnames);


		driver.manage().window().maximize();
		Thread.sleep(2000);

		WebElement mo = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));
		Actions a = new Actions(driver);
		a.moveToElement(mo).perform();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Patient(S)")).click();

		for(int i=6;i<=rowcount;i++) {

			XSSFRow cell=sheet.getRow(i);
			String Testcase_ID=cell.getCell(0).getStringCellValue(); 
			String name =cell.getCell(1).getStringCellValue(); 
			String Insurance =cell.getCell(2).getStringCellValue();
			String Benefit =cell.getCell(3).getStringCellValue();
			String Thearphy =cell.getCell(4).getStringCellValue();
			String Relation =cell.getCell(5).getStringCellValue();
			String Id =cell.getCell(6).getStringCellValue();
			String Effective =cell.getCell(7).getStringCellValue();
			String Termination =cell.getCell(8).getStringCellValue();
			String Plan =cell.getCell(9).getStringCellValue();
			String Other =cell.getCell(10).getStringCellValue();
			String Groupname =cell.getCell(11).getStringCellValue();
			String Groupno =cell.getCell(12).getStringCellValue();
			String Phoneno =cell.getCell(13).getRawValue();
			String Gender =cell.getCell(14).getStringCellValue();
			String street =cell.getCell(15).getStringCellValue();
			String city =cell.getCell(16).getStringCellValue();
			String state =cell.getCell(17).getStringCellValue();
			String zip =cell.getCell(18).getRawValue();
			String copay=cell.getCell(19).getStringCellValue();
			String Amount=cell.getCell(20).getRawValue();
			String co_ins=cell.getCell(21).getStringCellValue();
			String plan_ins=cell.getCell(22).getRawValue();
			String Ded=cell.getCell(23).getStringCellValue();
			String Ded_Met=cell.getCell(24).getRawValue();
			String Fam=cell.getCell(25).getStringCellValue();
			String Fam_Met=cell.getCell(26).getRawValue();
			String OOP_Ind=cell.getCell(27).getStringCellValue();
			String OOP_Met=cell.getCell(28).getRawValue();
			String OOP_Fam=cell.getCell(29).getStringCellValue();
			String OOPfam_Met=cell.getCell(30).getRawValue();
			String Benfit=cell.getCell(31).getRawValue();
			String Max_Benefit=cell.getCell(32).getRawValue();
			String comment=cell.getCell(33).getStringCellValue();
			String Ref_no=cell.getCell(34).getRawValue();
			String Ref_Name=cell.getCell(35).getStringCellValue();
			String Insurance2=cell.getCell(36).getStringCellValue();
			String Id2 =cell.getCell(37).getStringCellValue();
			String Effective2 =cell.getCell(38).getStringCellValue();
			String Termination2 =cell.getCell(39).getStringCellValue();
			String Plan2 =cell.getCell(40).getStringCellValue();
			String Other2 =cell.getCell(41).getStringCellValue();
			String Groupname2 =cell.getCell(42).getStringCellValue();
			String Groupno2 =cell.getCell(43).getStringCellValue();
			String Phoneno2=cell.getCell(44).getRawValue();
			String copay2=cell.getCell(45).getStringCellValue();
			String Amount2=cell.getCell(46).getRawValue();
			String co_ins2=cell.getCell(47).getStringCellValue();
			String plan_ins2=cell.getCell(48).getRawValue();
			String Ded2=cell.getCell(49).getStringCellValue();
			String Ded_Met2=cell.getCell(50).getRawValue();
			String Fam2=cell.getCell(51).getStringCellValue();
			String Fam_Met2=cell.getCell(52).getRawValue();
			String OOP_Ind2=cell.getCell(53).getStringCellValue();
			String OOP_Met2=cell.getCell(54).getRawValue();
			String OOP_Fam2=cell.getCell(55).getStringCellValue();
			String OOPfam_Met2=cell.getCell(56).getRawValue();
			String Benfit2=cell.getCell(57).getRawValue();
			String Max_Benefit2=cell.getCell(58).getRawValue();


			System.out.println(Testcase_ID);



			driver.findElement(By.xpath("(//input[contains(@type,'search')])[1]")).sendKeys(name);



			Thread.sleep(4000);
			WebElement search = driver.findElement(By.xpath("(//a[contains(@class,'mr-2')])[1]"));
			search.click();



			driver.findElement(By.xpath("(//a[@class='nav-link '])[1]")).click();



			driver.findElement(By.xpath("//td[@class='action_td']")).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//a[@class='dropdown-item font-weight-normal add_vob_detail']")).click();

			Thread.sleep(1000);

			WebElement w=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm ml-2'])[1]"));
			w.click();
			Thread.sleep(1000);
			Select s=new Select(w);
			s.selectByVisibleText(Insurance);
			Thread.sleep(1000);
			WebElement we=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm ml-2'])[2]"));
			we.click();
			Thread.sleep(1000);
			Select ss=new Select(we);
			ss.selectByVisibleText(Benefit);
			Thread.sleep(1000);
			WebElement wi=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm ml-2'])[3]"));
			wi.click();
			Thread.sleep(1000);
			Select se=new Select(wi);
			se.selectByVisibleText(Thearphy);
			Thread.sleep(1000);


			WebElement wy=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[2]"));
			wy.click();
			Thread.sleep(1000);
			Select sel=new Select(wy);
			sel.selectByVisibleText(Relation);
			Thread.sleep(1000);



			WebElement mem=driver.findElement(By.xpath("(//input[@class='form-control'])[10]"));
			mem.clear();
			mem.sendKeys( Id);
			WebElement b=driver.findElement(By.xpath("(//input[@class='form-control'])[11]"));
			b.clear();
			b.sendKeys(Effective);
			WebElement te=driver.findElement(By.xpath("(//input[@class='form-control'])[12]"));
			te.clear();
			te.sendKeys(Termination);
			WebElement pl=driver.findElement(By.xpath("(//input[@class='form-control'])[13]"));
			pl.clear();
			pl.sendKeys(Plan);
			WebElement ot=driver.findElement(By.xpath("(//input[@class='form-control'])[14]"));
			ot.clear();
			ot.sendKeys(Other);
			WebElement gr=driver.findElement(By.xpath("(//input[@class='form-control'])[15]"));
			gr.clear();
			gr.sendKeys(Groupname);
			WebElement grpno=driver.findElement(By.xpath("(//input[@class='form-control'])[16]"));
			grpno.clear();
			grpno.sendKeys(Groupno);
			WebElement ph=driver.findElement(By.xpath("(//input[@class='form-control'])[17]"));
			ph.clear();
			ph.sendKeys(Phoneno);
			Thread.sleep(2000);
			WebElement wr=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm'])[3]"));
			wr.click();
			Select sl= new Select(wr);
			sl.selectByVisibleText(Gender);

			WebElement str=driver.findElement(By.xpath("//input[@class='form-control form-control-sm street']"));
			str.clear();
			str.sendKeys(street);

			WebElement ci=driver.findElement(By.xpath("//input[@class='form-control form-control-sm city']"));
			ci.clear();
			ci.sendKeys(city);

			WebElement st=driver.findElement(By.xpath("//select[@class='form-control form-control-sm state ']"));
			Thread.sleep(1000);
			st.click();
			Select s2=new Select(st);
			s2.selectByVisibleText(state);

			WebElement zi= driver.findElement(By.xpath("//input[@class='form-control form-control-sm zip ']"));
			zi.clear();
			zi.sendKeys(zip);

			Thread.sleep(2000);

			driver.findElement(By.xpath("(//input[@class='form-check-input'])[1]")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-check-input'])[3]")).click();
			Thread.sleep(1000);

			WebElement webele=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm mx-1'])[1]"));
			webele.click();
			Select selec= new Select(webele);
			selec.selectByVisibleText(copay);

			WebElement amo=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[1]"));
			amo.clear();
			amo.sendKeys(Amount);

			driver.findElement(By.xpath("(//input[@class='form-check-input'])[4]")).click();
			Thread.sleep(1000);
			WebElement co=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[2]"));
			co.clear();
			co.sendKeys(co_ins);
			WebElement	pla=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[3]"));
			pla.clear();
			pla.sendKeys(plan_ins);
			WebElement de=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[4]"));
			de.sendKeys(Ded);
			WebElement ded=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[5]"));
			ded.clear();
			ded.sendKeys(Ded_Met);
			WebElement fa=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[6]"));
			fa.clear();
			fa.sendKeys(Fam);
			WebElement fame=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[7]"));
			fame.clear();
			fame.sendKeys(Fam_Met);
			WebElement oop=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[8]"));
			oop.clear();
			oop.sendKeys(OOP_Ind);
			WebElement oopm=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[9]"));
			oopm.clear();
			oopm.sendKeys(OOP_Met);
			WebElement oopf=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[10]"));
			oopf.clear();
			oopf.sendKeys(OOP_Fam);
			WebElement oopfm=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[11]"));
			oopfm.clear();
			oopfm.sendKeys(OOPfam_Met);
			WebElement be=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[12]"));
			be.clear();
			be.sendKeys(Benfit);
			WebElement ma=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[13]"));
			ma.clear();
			ma.sendKeys(Max_Benefit);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-check-input'])[6]")).click();
			Thread.sleep(1000);
			WebElement com=driver.findElement(By.xpath("//textarea[@class='form-control']"));
			com.clear();
			com.sendKeys(comment);

			WebElement re=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[14]"));
			re.clear();
			re.sendKeys(Ref_no);

			WebElement	refn=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[15]"));
			refn.clear();
			refn.sendKeys(Ref_Name);

			WebElement webelem=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm ml-2'])[4]"));

			webelem.click();
			Select select= new Select(webelem);
			select.selectByVisibleText(Insurance2);
			Thread.sleep(1000);
			WebElement	memb=driver.findElement(By.xpath("(//input[@class='form-control'])[18]"));
			memb.clear();
			memb.sendKeys(Id2);
			WebElement eff=driver.findElement(By.xpath("(//input[@class='form-control'])[19]"));
			eff.clear();
			eff.sendKeys(Effective2);
			WebElement ter=driver.findElement(By.xpath("(//input[@class='form-control'])[20]"));
			ter.clear();
			ter.sendKeys(Termination2);
			WebElement p=driver.findElement(By.xpath("(//input[@class='form-control'])[21]"));
			p.clear();
			p.sendKeys(Plan2);
			WebElement oth	=driver.findElement(By.xpath("(//input[@class='form-control'])[22]"));
			oth.clear();
			oth.sendKeys(Other2);
			WebElement gro=driver.findElement(By.xpath("(//input[@class='form-control'])[23]"));
			gro.clear();
			gro.sendKeys(Groupno2);
			WebElement gro2=driver.findElement(By.xpath("(//input[@class='form-control'])[24]"));
			gro2.clear();
			gro2.sendKeys(Groupname2);
			WebElement phon=driver.findElement(By.xpath("(//input[@class='form-control'])[25]"));
			phon.clear();
			phon.sendKeys(Phoneno2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-check-input'])[8]")).click();

			driver.findElement(By.xpath("(//input[@class='form-check-input'])[10]")).click();

			WebElement webeleme=driver.findElement(By.xpath("(//select[@class='form-control form-control-sm mx-1'])[2]"));
			webeleme.click();
			Select select1= new Select(webeleme);
			select1.selectByVisibleText(copay2);

			WebElement amou=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[16]"));
			amou.clear();
			amou.sendKeys(Amount2);
			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-check-input'])[11]")).click();

			WebElement coi2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[17]"));
			coi2.clear();
			coi2.sendKeys(co_ins2);
			WebElement pla2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[18]"));
			pla2.clear();
			pla2.sendKeys(plan_ins2);
			WebElement ded2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[19]"));
			ded2.clear();
			ded2.sendKeys(Ded2);
			WebElement	dedm2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[20]"));
			dedm2.clear();
			dedm2.sendKeys(Ded_Met2);
			WebElement f=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[21]"));
			f.clear();
			f.sendKeys(Fam2);
			WebElement	famm=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[22]"));
			famm.clear();
			famm.sendKeys(Fam_Met2);
			WebElement oop2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[23]"));
			oop2.clear();
			oop2.sendKeys(OOP_Ind2);
			WebElement oopme=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[24]"));
			oopme.clear();
			oopme.sendKeys(OOP_Met2);
			WebElement oopf2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[25]"));
			oopf2.clear();
			oopf2.sendKeys(OOP_Fam2);
			WebElement oopfam=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm mx-1'])[26]"));
			oopfam.clear();
			oopfam.sendKeys(OOPfam_Met2);
			WebElement ben2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[3]"));
			ben2.clear();
			ben2.sendKeys(Benfit2);
			WebElement max2=driver.findElement(By.xpath("(//input[@class='form-control form-control-sm'])[4]"));
			max2.clear();
			max2.sendKeys(Max_Benefit2);

			Thread.sleep(1000);
			driver.findElement(By.xpath("(//input[@class='form-check-input'])[13]")).click();

			Thread.sleep(1000);
			driver.findElement(By.xpath("//button[@class='btn btn-warning submit_vob_details']")).click();


			//Thread.sleep(1000);
			//WebElement toastMessage = driver.findElement(By.xpath("//div[@id='toast-container']"));
			//System.out.println("Toastmessage: " + toastMessage.getText());
			//String statusmessage=toastMessage.getText();

		saveReport("Edit_Vob"+d+".html",patientBillhtml(Testcase_ID,
			name ,
			Insurance ,
			Benefit ,
			Thearphy ,
			Relation ,
			Id ,
			Effective ,
			Termination ,
			Plan ,
			Other ,
			Groupname ,
			Groupno , 
			Phoneno , 
			Gender , 
			street , 
			city , 
			state , 
			zip , 
			copay , 
			Amount , 
			co_ins , 
			plan_ins , 
			Ded , 
			Ded_Met , 
			Fam , 
			Fam_Met , 
			OOP_Ind , 
			OOP_Met , 
			OOP_Fam , 
			OOPfam_Met , 
			Benfit , 
			Max_Benefit , 
			comment , 
			Ref_no , 
			Ref_Name , 
			Insurance2 , 
			Id2 ,
			Effective2 ,
			Termination2 ,
			Plan2 ,
			Other2 ,
			Groupname2 ,
			Groupno2 ,
			Phoneno2,
			copay2,
			Amount2,
			co_ins2,
			plan_ins2,
			Ded2,
			Ded_Met2,
			Fam2,
			Fam_Met2,
			OOP_Ind2,
			OOP_Met2,
			OOP_Fam2,
			OOPfam_Met2,
			Benfit2,
			Max_Benefit2));




			Thread.sleep(2000);
			WebElement m = driver.findElement(By.xpath("//img[contains(@alt,'tpms')][2]"));

			a.moveToElement(m).perform();
			Thread.sleep(1000);
			driver.findElement(By.linkText("Patient(S)")).click();



		}
		saveReport("Edit_Vob"+d+".html","</table>");
	}
	public static String  patientBillhtml(String Test_Case_ID,
			String name ,
			String Insurance ,
			String Benefit ,
			String Thearphy ,
			String Relation ,
			String Id ,
			String Effective ,
			String Termination ,
			String Plan ,
			String Other ,
			String Groupname ,
			String Groupno , 
			String Phoneno , 
			String Gender , 
			String street , 
			String city , 
			String state , 
			String zip , 
			String copay , 
			String Amount , 
			String co_ins , 
			String plan_ins , 
			String Ded , 
			String Ded_Met , 
			String Fam , 
			String Fam_Met , 
			String OOP_Ind , 
			String OOP_Met , 
			String OOP_Fam , 
			String OOPfam_Met , 
			String Benfit , 
			String Max_Benefit , 
			String comment , 
			String Ref_no , 
			String Ref_Name , 
			String Insurance2 , 
			String Id2 ,
			String Effective2 ,
			String Termination2 ,
			String Plan2 ,
			String Other2 ,
			String Groupname2 ,
			String Groupno2 ,
			String Phoneno2,
			String copay2,
			String Amount2,
			String co_ins2,
			String plan_ins2,
			String Ded2,
			String Ded_Met2,
			String Fam2,
			String Fam_Met2,
			String OOP_Ind2,
			String OOP_Met2,
			String OOP_Fam2,
			String OOPfam_Met2,
			String Benfit2,
			String Max_Benefit2)


	{

		String retval="";

		retval="<tr><td>"+Test_Case_ID+"</td>"+

			"<td>"+ name +"</td>"+
			"<td>"+ Insurance +"</td>"+
			"<td>"+ Benefit +"</td>"+
			"<td>"+ Thearphy +"</td>"+
			"<td>"+ Relation +"</td>"+
			"<td>"+ Id +"</td>"+
			"<td>"+ Effective +"</td>"+
			"<td>"+ Termination +"</td>"+
			"<td>"+ Plan +"</td>"+
			"<td>"+ Other +"</td>"+
			"<td>"+ Groupname +"</td>"+
			"<td>"+ Groupno +"</td>"+ 
			"<td>"+ Phoneno +"</td>"+ 
			"<td>"+ Gender +"</td>"+ 
			"<td>"+ street +"</td>"+ 
			"<td>"+ city +"</td>"+ 
			"<td>"+ state +"</td>"+ 
			"<td>"+ zip +"</td>"+ 
			"<td>"+ copay +"</td>"+ 
			"<td>"+ Amount +"</td>"+ 
			"<td>"+ co_ins +"</td>"+ 
			"<td>"+ plan_ins +"</td>"+ 
			"<td>"+ Ded +"</td>"+ 
			"<td>"+ Ded_Met +"</td>"+ 
			"<td>"+ Fam +"</td>"+ 
			"<td>"+ Fam_Met +"</td>"+ 
			"<td>"+ OOP_Ind +"</td>"+ 
			"<td>"+ OOP_Met +"</td>"+ 
			"<td>"+ OOP_Fam +"</td>"+ 
			"<td>"+ OOPfam_Met +"</td>"+ 
			"<td>"+ Benfit +"</td>"+ 
			"<td>"+ Max_Benefit +"</td>"+ 
			"<td>"+ comment +"</td>"+ 
			"<td>"+ Ref_no +"</td>"+ 
			"<td>"+ Ref_Name +"</td>"+ 
			"<td>"+ Insurance2 +"</td>"+ 
			"<td>"+ Id2 +"</td>"+
			"<td>"+ Effective2 +"</td>"+
			"<td>"+ Termination2 +"</td>"+
			"<td>"+ Plan2 +"</td>"+
			"<td>"+ Other2 +"</td>"+
			"<td>"+ Groupname2 +"</td>"+
			"<td>"+ Groupno2 +"</td>"+
			"<td>"+ Phoneno2+"</td>"+
			"<td>"+ copay2+"</td>"+
			"<td>"+ Amount2+"</td>"+
			"<td>"+ co_ins2+"</td>"+
			"<td>"+ plan_ins2+"</td>"+
			"<td>"+ Ded2+"</td>"+
			"<td>"+ Ded_Met2+"</td>"+
			"<td>"+ Fam2+"</td>"+
			"<td>"+ Fam_Met2+"</td>"+
			"<td>"+ OOP_Ind2+"</td>"+
			"<td>"+ OOP_Met2+"</td>"+
			"<td>"+ OOP_Fam2+"</td>"+
			"<td>"+ OOPfam_Met2+"</td>"+
			"<td>"+ Benfit2+"</td>"+
			"<td>"+Max_Benefit2+"</td></tr>";
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

