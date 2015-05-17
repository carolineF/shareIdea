package com.shareIdea.util;

import com.shareIdea.util.mail.MailSenderInfo;
import com.shareIdea.util.mail.SimpleMailSender;
/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年3月26日 上午10:07:43 
 * @version 1.0   
 */
public class RegisterEmailVali {
	public static void sendMail(String emailAddress,String valiDate){ 
		MailSenderInfo mailInfo = new MailSenderInfo();   
	    mailInfo.setMailServerHost("smtp.exmail.qq.com");   
	    mailInfo.setMailServerPort("25");   
	    mailInfo.setValidate(true);   
	    mailInfo.setUserName("xuptjsjr@goitsys.com");   
	    mailInfo.setPassword("jsjr20150331");//您的邮箱密码   
	    mailInfo.setFromAddress("xuptjsjr@goitsys.com");   
	    mailInfo.setToAddress(emailAddress);   
	    mailInfo.setSubject("shareIdea邮箱验证");   
	    mailInfo.setContent(valiDate);   
	    //这个类主要来发送邮件  
	    SimpleMailSender sms = new SimpleMailSender();  
	    //sms.sendTextMail(mailInfo);//发送文体格式   
	    sms.sendHtmlMail(mailInfo);//发送html格式  
	}
	public static void main(String args[]){ 
		new RegisterEmailVali().sendMail("444981081@qq.com","123");
	}
}

