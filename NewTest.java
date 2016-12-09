package demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class NewTest {
 
   //SeleniumMain SD=new SeleniumMain(); 
   String appurl="http://10.0.1.86/tatoc/";
    
   
   SeleniumDemo2 SD1=new SeleniumDemo2();
   
    /*   Basic Course 
  @Test
  public void test1()
  {
	  SD.greenbox_check();
  }
  @Test 
  public void test2()
  {
	 SD.frame_dungeon();
	 
  }
  @Test
  public void test3()
  {
	  SD.drag_drop();
  }
  @Test
  public void test4()
  {
	  SD.Popup_window();
  }
  @Test
  public void test5()
  {
	  SD.cookie_handling();
  }
  */
  
  /*
  @Test 
  public void ACB()
  {
	  System.out.println("C");
  }
  @Test(priority=0)
  public void ABC()
  {
	  System.out.println("B");
  }
  @Test
  public void DAI()
  {
	  System.out.println("D");
  }
  @Test (priority=9)
  public void F()
  {
	  System.out.println("F");
  }
  @Test
  public void P()
  {
	  System.out.println("P");
  }
  */
  
  //Advance course
   @Test
   public void test6()
   {
	   SD1.hover_menu();
   }
  @Test
  public void test7()
  {
	  SD1.query_gate();
  }
  @Test
  public void test8()
  {
      SD1.ooyala_player();
  }
  @Test
  public void test9()
  {
      SD1.restful_services();
  }
  @AfterClass
  public void end_launch()
  {
	  SD1.close_browser();
	  
  }
  
 
@BeforeTest
  public void test_launch() 
  {
	  SD1.launch_browser(appurl);
  }
  
}
