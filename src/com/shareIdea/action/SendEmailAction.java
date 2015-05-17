package com.shareIdea.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.util.SendEmailTool;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年3月29日 上午9:28:37 
 * @version 1.0   
 */
public class SendEmailAction extends ActionSupport implements ServletResponseAware{
    /**
     * 向用户发送邮件
     */
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
	
    public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	private String bulidSecCode(){
		String secCode ="";
	    @SuppressWarnings("rawtypes")
		List list = new ArrayList(); 
	    // list.add(1); 
	    for (int i = 0; i < 10; i++) 
	    	list.add(i+"");
	    Random r=new Random();
	    for(int a=0;a<6;a++){
	    	String i=String.valueOf(r.nextInt(10));
	        secCode+=i;
	    }
	       
	    return secCode;
	}
	/**
	 * 注册时发送随机产生的验证码
	 */
	@Override
    public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String userEmail = request.getParameter("email");
		String secCode = bulidSecCode();
        
		System.out.println("验证码："+secCode);
        
        request.getSession().setAttribute("secCode", secCode);
        String mailbody = "<font><i>这是来自ShareIdea共享平台的验证信息，如果您并未在该平台上申请账号验证，请修改自己的邮箱密码，防止被盗。</i></font>"
        		+ "<font size=5><br>本次您的验证密码是："+secCode+"</font><font><br>请注意保密，不要告诉他人！<br>注册成功后请您尽快完善个人资料，增强账户安全。<br>感谢您的支持！</font>";
        SendEmailTool themail = new SendEmailTool("smtp.exmail.qq.com");
        themail.setNeedAuth(true);
        // 标题
        themail.setSubject("验证邮件");
        // 邮件内容 支持html 如 <font color=red>欢迎你,java</font>
        themail.setBody(mailbody);
        // 收件人邮箱
        themail.setTo(userEmail);
        // 发件人邮箱
        themail.setFrom("xuptjsjr@goitsys.com");
        themail.setNamePass("xuptjsjr@goitsys.com", "jsjr20150331"); // 用户名与密码,即您选择一个自己的电邮
        themail.sendout();
        return null;
    }
	
	/**
	 * 忘记密码时发送验证码
	 */
	public String getPasswordByEmail() throws Exception {
		String account = (String)ActionContext.getContext().getSession().get("account");
		System.out.println(account);
		String secCode = bulidSecCode(); 
        System.out.println("验证码"+secCode);
        if(-1 == account.indexOf("@")){
        	//TODO 手机发送验证码
        }else{
        	//发送邮件
        	 String mailbody = "<font><i>这是来自ShareIdea共享平台的验证信息，如果您并未在该平台上申请账号验证，请修改自己的邮箱密码，防止被盗。</i></font>"
             		+ "<font size=5><br>本次您的验证密码是："+secCode+"</font><font><br>请注意保密，不要告诉他人！<br>注册成功后请您尽快完善个人资料，增强账户安全。<br>感谢您的支持！</font>";
             SendEmailTool themail = new SendEmailTool("smtp.exmail.qq.com");
             themail.setNeedAuth(true);
             // 标题
             themail.setSubject("验证邮件");
             // 邮件内容 支持html 如 <font color=red>欢迎你,java</font>
             themail.setBody(mailbody);
             // 收件人邮箱
             themail.setTo(account);
             // 发件人邮箱
             themail.setFrom("xuptjsjr@goitsys.com");
             themail.setNamePass("xuptjsjr@goitsys.com", "jsjr20150331"); // 用户名与密码,即您选择一个自己的电邮
             themail.sendout();
        }
        //jsjr_xupt@163.com
        //jsjr20150331
        ActionContext.getContext().getSession().put("forgetsecondVerify", secCode);
        return null;
    }

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		
	}

}

