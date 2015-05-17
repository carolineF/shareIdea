package com.shareIdea.util.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年3月26日 上午10:18:21 
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

