package demo;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

class SeleniumMain {

	public static WebDriver driver;
	SeleniumMain()
	{
		
	 driver= new FirefoxDriver();
	
	}	

	void launch_browser(String aurl)
	{
		System.out.println("Basic Course");
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.get(aurl);
	  driver.manage().window().maximize();
	  WebElement bcourse=driver.findElement(By.xpath("//a[text()='Basic Course']"));
	  bcourse.click();
	         
	}
	    
    void greenbox_check()
    {
      System.out.println("Page title is : " + driver.getTitle());
      //find green box
      WebElement green_element=driver.findElement(By.xpath("//div[@class='greenbox']"));
      green_element.click();
        
    }
      
    void frame_dungeon()
    {
    	 String color1,color2;
    	 System.out.println("Page title is : " + driver.getTitle());
        //find copy frame
    	  	while(true)
    	     {
    	  		
               	driver.switchTo().frame(driver.findElement(By.id("main")));
               	WebElement txtbox1=driver.findElement(By.xpath("//div[text()='Box 1']"));
                color1=txtbox1.getAttribute("class");
                System.out.println(" Color of textbox 1 "+color1);
                
                driver.switchTo().frame(driver.findElement(By.id("child")));
    	        //driver.switchTo().defaultContent();
                WebElement txtbox2=driver.findElement(By.xpath("//div[text()='Box 2']"));
            	color2=txtbox2.getAttribute("class");
            	System.out.println("Color of textbox2 "+color2);
            	driver.switchTo().defaultContent();
            	
                if(!(color1.equals(color2)))
       	        { 
                	System.out.println("If Block");
                	
        	        driver.switchTo().frame(driver.findElement(By.id("main")));
        	        WebElement btn1=driver.findElement(By.xpath("//a[@onclick='reloadChildFrame();']"));
         	        btn1.click();
         	       driver.switchTo().defaultContent();
                
       	       }
                else
                {
                	
                     System.out.println("Else Block");
                     driver.switchTo().frame(driver.findElement(By.id("main")));
                	WebElement btn2=driver.findElement(By.xpath("//a[@onclick='gonext();']"));
                    btn2.click();
           	        break;
                }
               
    	              
    	     	
    	}
    	  	
    }
    
   void drag_drop()
   {
	  //System.out.println("Drop function .");
	   WebElement dragbx=driver.findElement(By.xpath("//div[@id='dragbox']"));
	   WebElement dropbx=driver.findElement(By.xpath("//div[@id='dropbox']"));
	   Actions action=new Actions(driver);
	   action.dragAndDrop(dragbx, dropbx).build().perform();
	   System.out.println("Drag and Drop function");
	   WebElement btn_proceed=driver.findElement(By.xpath("//a[text()='Proceed']"));
	   btn_proceed.click();
   }
   
   void Popup_window()
   {
	 WebElement ppw=driver.findElement(By.xpath("//a[text()='Launch Popup Window']"));
	 
	 String main_window=driver.getWindowHandle();
	 System.out.println("Parent window"+ main_window);
	 ppw.click();
	 
	 Set <String> child=driver.getWindowHandles();
	 Iterator <String> it1=child.iterator();
	 while(it1.hasNext())
	 {
		 String child_win=it1.next();
		 
		 if(!main_window.equalsIgnoreCase(child_win))
		 {
	      driver.switchTo().window(child_win);
	      System.out.println("Child window "+ child_win);
	      driver.findElement(By.id("name")).sendKeys("mandeepkaur");;
	   
	      driver.findElement(By.id("submit")).click();
	      System.out.println("after submit");
	    
	 	 }
	 }
	 
	 System.out.println("out of while");
	 driver.switchTo().window(main_window);
	 driver.findElement(By.xpath("//a[text()='Proceed']")).click();
		 
   }
    void cookie_handling()
    {
    	System.out.println("Cookie funtion");
    	driver.findElement(By.xpath("//a[text()='Generate Token']")).click();
    	
		String value="";
    	
    	WebElement abc=driver.findElement(By.xpath("//span[@id='token']"));
    	value=abc.getText();
    	String v1;
    	v1=value.substring(7,value.length());
    	value="";
    	value="Token="+v1;
    	System.out.println("New Value "+v1);
    	Cookie ck=new Cookie("mycookie",value);
    	System.out.println("Cookie is "+ck);
    	driver.manage().addCookie(ck);
    	System.out.println("Cookie added");
    	driver.manage().getCookieNamed("mycookie");
    	driver.findElement(By.xpath("//a[text()='Proceed']")).click();
    	driver.close();	
    	
    }
    
    void close_browser()
    {
    	
    	driver.close();
    	System.exit(0);
    	
    }
}

