package webDriverExtensionPage;

import org.openqa.selenium.JavascriptExecutor;

public class AjaxUtil {

	 public static void WaitForAjaxCallsToFinish()
     {
		 int maxTimeLimit = 45;
         JavascriptExecutor js = (JavascriptExecutor)Page.getWebDriver();
        
         InjectJQuery();
         
         String numberofRequests = "";
         int counter = 0;
        
         do{
        	 try
        	 {
        		 Thread.sleep(1000);
        		 
        	 }catch(InterruptedException e){
        		 e.printStackTrace();
        	 }
        	 numberofRequests = js.executeScript("return jQuery.active").toString();
        	 counter++;
         } while (!numberofRequests.equals("0") && counter<maxTimeLimit);           
     }	

     public static void WaitForDocumentToBeReady()
     {
         JavascriptExecutor js = (JavascriptExecutor)Page.getWebDriver();

         String state ="";
         try {
			Thread.sleep(200);
         } catch (InterruptedException e) {			
			e.printStackTrace();
         }
         
         do
         {               
             state = js.executeScript("return document.readyState").toString();
         } while (!state.equalsIgnoreCase("complete"));
     }
	
     private static void InjectJQuery()
     {
    	 WaitForDocumentToBeReady();
    	 
    	 JavascriptExecutor js = (JavascriptExecutor)Page.getWebDriver();
    	 Boolean jQuery = (Boolean)js.executeScript("return typeof jQuery == 'undefined'");
    	 if(jQuery)
    		 js.executeScript("var jq = document.createElement('script');jq.src = 'https://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.0.min.js';document.getElementsByTagName('head')[0].appendChild(jq);");
     }
}
