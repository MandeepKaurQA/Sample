package demo;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
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
		System.out.println("Advance Course");
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
	
	
	void restful_services()
	{
		System.out.println("RestFul Services");
		
		WebElement sn=driver.findElement(By.xpath("//div[@class='page']/span[@id='session_id']"));
		String sid=sn.getText();
		sid=sid.substring(12,sid.length());
		
		System.out.println("Session id : "+sid);
		String reqst="http://10.0.1.86/tatoc/advanced/rest/service/token/"+sid;
		System.out.println("Current Url is :"+ reqst);
		try { //GET scenario
			System.out.println("GET http-connection !!");
			URL get_url=new URL(reqst);
			HttpURLConnection  get_conn=(HttpURLConnection)get_url.openConnection();
			get_conn.setRequestMethod("GET");
			get_conn.setRequestProperty("ACCEPT", "application/json");
			if(get_conn.getResponseCode()!=200){
				throw new RuntimeException("Http error code : "+get_conn.getResponseCode());}
			
			Scanner scan=new Scanner(get_url.openStream());
			String resp=new String();
				while(scan.hasNext()){
					resp +=scan.nextLine(); }
				
			System.out.println("Response is : "+resp);
			scan.close();
			//get_conn.disconnect();
			//put scenario
			System.out.println("JSON object");
			JSONObject obj= new JSONObject(resp);
			String signature= (String )obj.get("token");
			
			String post_url="http://10.0.1.86/tatoc/advanced/rest/service/register";
			
			System.out.println("Signature: "+ signature);
			String params="id="+sid+"&signature="+signature+"&allow_access="+1;
			String url2=post_url+params;
			System.out.println(url2);
				
			URL url1=new URL(url2);
			
			HttpURLConnection post_conn=(HttpURLConnection)url1.openConnection();
			System.out.println("Post Http-Connection");
			post_conn.setRequestMethod("POST");
			post_conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			post_conn.setDoOutput(true);
			
			DataOutputStream writer = new DataOutputStream(post_conn.getOutputStream());
			int responseCode = post_conn.getResponseCode();
			writer.writeBytes(params);
						
			System.out.println("Sending request to URL : " + url1);
			System.out.println("Check1 Post parameters : " + params);
			System.out.println("Check2 Response Code : " + responseCode);
			writer.flush();
			writer.close();
				
			/*
			BufferedReader in1 = new BufferedReader(new InputStreamReader(post_conn.getInputStream()));
			String inputLine1;
			StringBuffer response1 = new StringBuffer();
			
			while ((inputLine1 = in1.readLine()) != null) {
				System.out.println("inputline1 :: "	+response1.append(inputLine1));}
			
			System.out.println("write.write(params)"+response1);
			
			
			
			in1.close();
				*/
		   	if (post_conn.getResponseCode() != 200) {
		        throw new RuntimeException("Failed : HTTP error code : " + post_conn.getResponseCode());
		        }
        	
			get_conn.disconnect();		
			
			driver.findElement(By.xpath("//div[@class='page']/a[text()='Proceed']")).click();	
					
						
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
		}
		
		void close_browser()
	    {
	    	
	    	driver.close();
	    	System.exit(0);
	    	
	    }
		
		
	
}
