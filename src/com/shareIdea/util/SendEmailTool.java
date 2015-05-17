package com.shareIdea.util;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletResponse;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年3月29日 下午3:00:29 
 * @version 1.0   
 */
public class SendEmailTool {
	  private MimeMessage mimeMsg; // MIME邮件对象

	    private Session session; // 邮件会话对象

	    private Properties props; // 系统属性

	    private boolean needAuth = false; // smtp是否需要认证

	    private String username = ""; // smtp认证用户名和密码

	    private String password = "";

	    private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成
	    
	    public SendEmailTool() {

		}

	    public SendEmailTool(String smtp) {
	        setSmtpHost(smtp);
	        createMimeMessage();
	    }

	    public void setSmtpHost(String hostName) {
	        System.out.println("设置系统属性：mail.smtp.host = " + hostName);
	        if (props == null)
	            props = System.getProperties(); // 获得系统属性对象
	        props.put("mail.smtp.host", hostName); // 设置SMTP主机
	    }

	    public boolean createMimeMessage() {
	        try {
	            System.out.println("准备获取邮件会话对象！");
	            session = Session.getDefaultInstance(props, null); // 获得邮件会话对象
	        } catch (Exception e) {
	            System.err.println("获取邮件会话对象时发生错误！" + e);
	            return false;
	        }
	        System.out.println("准备创建MIME邮件对象！");
	        try {
	            mimeMsg = new MimeMessage(session); // 创建MIME邮件对象
	            mp = new MimeMultipart(); // mp 一个multipart对象
	            // Multipart is a container that holds multiple body parts.
	            return true;
	        } catch (Exception e) {
	            System.err.println("创建MIME邮件对象失败！" + e);
	            return false;
	        }
	    }

	    public void setNeedAuth(boolean need) {
	        System.out.println("设置smtp身份认证：mail.smtp.auth = " + need);
	        if (props == null)
	            props = System.getProperties();
	        if (need) {
	            props.put("mail.smtp.auth", "true");
	        } else {
	            props.put("mail.smtp.auth", "false");
	        }
	    }

	    public void setNamePass(String name, String pass) {
	        System.out.println("程序得到用户名与密码");
	        username = name;
	        password = pass;
	    }

	    public boolean setSubject(String mailSubject) {
	        System.out.println("设置邮件主题！");
	        try {
	            mimeMsg.setSubject(mailSubject);
	            return true;
	        } catch (Exception e) {
	            System.err.println("设置邮件主题发生错误！");
	            return false;
	        }
	    }

	    public boolean setBody(String mailBody) {
	        try {
	            System.out.println("设置邮件体格式");
	            BodyPart bp = new MimeBodyPart();
	            // 转换成中文格式
	            bp.setContent(
	                    "<meta http-equiv=Content-Type content=text/html; charset=gb2312>"
	                            + mailBody, "text/html;charset=GB2312");
	            mp.addBodyPart(bp);
	            return true;
	        } catch (Exception e) {
	            System.err.println("设置邮件正文时发生错误！" + e);
	            return false;
	        }
	    }

	    public boolean setFrom(String from) {
	        System.out.println("设置发信人！");
	        try {
	            mimeMsg.setFrom(new InternetAddress(from)); // 设置发信人
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean setTo(String to) {
	        System.out.println("设置收信人");
	        if (to == null)
	            return false;
	        try {
	            mimeMsg.setRecipients(Message.RecipientType.TO, InternetAddress
	                    .parse(to));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean setCopyTo(String copyto) {
	        System.out.println("发送附件到");
	        if (copyto == null)
	            return false;
	        try {
	            mimeMsg.setRecipients(Message.RecipientType.CC,
	                    (Address[]) InternetAddress.parse(copyto));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public boolean sendout() {
	        try {
	            mimeMsg.setContent(mp);
	            mimeMsg.saveChanges();
	            System.out.println("正在发送邮件....");
	            Session mailSession = Session.getInstance(props, null);
	            Transport transport = mailSession.getTransport("smtp");
	            transport.connect((String) props.get("mail.smtp.host"), username,
	                    password);
	            transport.sendMessage(mimeMsg, mimeMsg
	                    .getRecipients(Message.RecipientType.TO));
	            // transport.send(mimeMsg);
	            System.out.println("发送邮件成功！");
	            transport.close();
	            return true;
	        } catch (Exception e) {
	            System.err.println("邮件发送失败！" + e);
	            return false;
	        }
	    }
}

