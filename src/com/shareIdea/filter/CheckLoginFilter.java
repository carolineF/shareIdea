package com.shareIdea.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class CheckLoginFilter
 */
@WebFilter("/CheckLoginFilter")
public class CheckLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public CheckLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("checkLogin被销毁了！");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//获取请求的URL
		String url = httpRequest.getRequestURL().toString();
		System.out.println("doFilter中的请求的url___________________"+url);
		//获取session中的对象
		HttpSession session = httpRequest.getSession();
		String userId = (String) session.getAttribute("userId");
		System.out.println("doFilter中的userId_________________"+ userId);
		if(url.endsWith("login.jsp") || userId!=null || url.endsWith("index.jsp") || url.endsWith(".css") || 
				url.endsWith(".js")|| url.endsWith(".gif")|| url.endsWith(".png")|| url.endsWith(".jpg")||url.endsWith("register.jsp")||
				url.endsWith("SSH_market/")||url.endsWith("strategy.jsp") ){
			System.out.println("满足条件可以继续执行了……");
			chain.doFilter(request, response);
		}else{
			//不满足条件就跳转到其他页面
			System.out.println("不满足条件返回登录界面……");
			
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp"); 
		}

		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("checkLogin被初始化了");
	}

}
