package com.shareIdea.action.interceptor;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class testInterceptor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6503767114479969387L;

	public void destroy() {
		System.out.println("testInterceptor的销毁");
	}
	public void init() {
		System.out.println("testInterceptor的初始化");
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//获取容器
		ActionContext context = invocation.getInvocationContext();
		// 取得请求的Action名
        String name = context.getName();
        String method = ServletActionContext.getRequest().getMethod();
        if(method.equals("POST")||method.equals("post")){
        	return invocation.invoke();
        }
        
        if (name.equals("UserBaseInfo_checkLogin")||name.equals("UserBaseInfo_checkAccount")||
        		name.equals("SendEmail_execute")||name.equals("UserBaseInfo_checkUser")||
        		name.equals("UserBaseInfo_checkSecCode")||name.equals("UserBaseInfo_insertUser")||
        		name.equals("UserBaseInfo_image")||name.equals("UserBaseInfo_forgetFirst")||name.equals("UserBaseInfo_forgetsecond")
        		||name.equals("UserBaseInfo_forgetThird")||name.equals("SendEmail_getPasswordByEmail")||name.matches("UserBaseInfo_forgetsecond")) {
            // 如果用户想登录，则使之通过
            return invocation.invoke();
        } else {
            // 取得Session。
            Map session = (Map)context.get(ServletActionContext.SESSION);

            if (session == null) {
                // 如果Session为空，则让用户登陆。
            	System.out.println("没有登陆");
            	return "toLogin";
            } else {
                String userId = (String)session.get("userId");
                System.out.println("当前用户的ID为"+userId);
                if (userId == null) {
                    // Session不为空，但Session中没有用户信息，
                    // 则让用户登陆
                	System.out.println("没有userId");
                    return "toLogin";
                } else {
                    // 用户已经登陆，放行~
                    return invocation.invoke();
                }
            }
        }
	}

}
