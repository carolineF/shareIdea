package com.shareIdea.action;

import java.io.IOException;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class ExitAction extends ActionSupport{
private static final long serialVersionUID = -1947669707379922419L;
	/*ÍË³ö*/
	@Override
	public String execute() throws IOException{
		//Ïû³ýsession
		 ServletActionContext.getRequest().getSession().invalidate();
		 return "toExit";
	}
}