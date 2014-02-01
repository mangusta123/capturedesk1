package com.example.capturedesktop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;

import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;


public class MailSenderActivity extends Activity {

	File imageFile;  //file that the screen goes to
	Bitmap bitmap;   //captured screen
	
	String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()+"/screen1.jpg";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_sender);
   
        final Button send = (Button) this.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

            	
            	
                try {   
                             	
                	//capture
                	                
                 	View v1 = getWindow().getDecorView().getRootView();
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
                	
                	GMailSender sender = new GMailSender("defcon.fused@gmail.com", "realtehluke1");
                    sender.sendMail("Subject blet",   
                            "necesen aleee",   
                            "defcon.fused@gmail.com",   
                            "remarkable02@mail.ru");   
             
                   //and remove it
                    
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

            }
        });
    
    
    
    
    
    
    
    
    
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mail_sender, menu);
        return true;
    }
}
