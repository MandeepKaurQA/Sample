package demo;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumDemo2 {
	public static WebDriver driver;
	SeleniumDemo2()
	{
		
	 driver= new FirefoxDriver();
	
	}	
	void launch_browser(String aurl)
	{
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.get(aurl);
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//a[text()='Advanced Course']")).click();;
	         
	}

	void hover_menu()
	{
		WebElement hm=driver.findElement(By.xpath("//span[@class='menutitle']"));
		Actions act=new Actions(driver);
		act.moveToElement(hm).build().perform();
		
		WebElement opt=driver.findElement(By.xpath("//span[text()='Go Next']"));
		opt.click();
		
	}
	void query_gate()
	{
		System.out.println("Query Gate");
		WebElement symbol=driver.findElement(By.xpath("//form[@id='symbolform']/div[@id='symboldisplay']"));
		String sym=symbol.getText();
		System.out.println("Sysmbol : "+sym);
		
		
		try {
		    System.out.println("Connection");	
		    int id=0 ;
		    String name="",pkey="";
		    String dburl="jdbc:mysql://10.0.1.86/";
		    String uname="tatocuser",pswd="tatoc01",db="tatoc";
		    
		    Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(dburl+db,uname,pswd);
			
			Statement stm=con.createStatement();
			ResultSet rs=stm.executeQuery("select id from identity where symbol like '"+ sym+"'");
      		while(rs.next())
      		{
      		  id=rs.getInt(1);
      		  
      		}
      		Statement stm2=con.createStatement();
      		ResultSet rs1=stm2.executeQuery("select name,passkey from credentials where id="+id);
      		while(rs1.next())
      		{
      			name=rs1.getString(1);
      			pkey=rs1.getString(2);
      		}
      		System.out.println("Name and Passkey are "+name +" : "+pkey);
      		driver.switchTo().defaultContent();
      		driver.findElement(By.xpath("//form[@id='symbolform']/input[@id='name']")).sendKeys(name);
      		driver.findElement(By.xpath("//form[@id='symbolform']/input[@id='passkey']")).sendKeys(pkey);
      		
      		
      		driver.findElement(By.xpath("//input[@id='submit']")).click();
      		
      		
			
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	void ooyala_player()
	{
		//by pass task-- video expired
			driver.navigate().to("http://10.0.1.86/tatoc/advanced/rest");
			//driver.findElement(By.xpath("//div[@id='ooyalaplayer']"));
		   // driver.findElement(By.xpath("//a[text()='Proceed']")).click();
	}
	
	
	void restful()
	{
		WebElement sn=driver.findElement(By.xpath("//span[@id='session_id'"));
		String sid=sn.getText();
		sid=sid.substring(12,sid.length());
		String reqst=driver.getCurrentUrl()+sid;
		try {
			URL url1=new URL(reqst);
			HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
			connection.setRequestMethod("POST");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
}
