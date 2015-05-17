package com.shareIdea.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015��3��26�� ����10:18:21 
 * @version 1.0   
 */
public class MyAuthenticator  extends Authenticator{
	String userName=null;  
    String password=null;  
       
    public MyAuthenticator(){  
    }  
    public MyAuthenticator(String username, String password) {   
        this.userName = username;   
        this.password = password;   
    }   
    protected PasswordAuthentication getPasswordAuthentication(){  
        return new PasswordAuthentication(userName, password);  
    }  
}

