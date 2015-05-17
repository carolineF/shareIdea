package com.shareIdea.action;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Userbaseinfo;
/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年3月28日 下午5:44:53 
 * @version 1.0   
 */
public class uploadHeadImageAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private File ufile; //上传的文件     
	private String ufileContentType; //文件的内容类型,ufile为上面定义上传的文件，ContentType是固定写法，不能改变     
	private String ufileFileName; //上传文件名,ufile为上面定义上传的文件，FileName是固定写法，不能改变     
	
	private String note;// 上传文件时的备注     
	private ServletContext context; //只用来取服务器路径     
	
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private int userId;
	private int getUserId(){
		return Integer.parseInt((String)request.getSession().getAttribute("userId"));
	}
	
	
	public File getUfile() {
		return ufile;
	}
	public void setUfile(File ufile) {
		this.ufile = ufile;
	}
	public String getUfileContentType() {
		return ufileContentType;
	}
	public void setUfileContentType(String ufileContentType) {
		this.ufileContentType = ufileContentType;
	}
	public String getUfileFileName() {
		return ufileFileName;
	}
	public void setUfileFileName(String ufileFileName) {
		this.ufileFileName = ufileFileName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	/**
	 * 获得当前用户的userId判断此Id应该存放的目录
	 * 打开此目录，不存在则新建
	 * 原头像存在，删除。存入新头像
	 * 得到完整imgae路径，存入数据库
	 */
	@Override 
	public String execute() throws Exception {
		int number = getUserId()/100;
		int n = getUserId()%100;
		if(n!=0){
			number++;
		}
		File path = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"+number));
		if(!path.exists()){System.out.println("bu存在");
			path.mkdirs();
		}
		
		String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload"+number);
		String targetFileName = getUserId()+".jpg"; 
		File oldImage = new File(path+File.separator+targetFileName);
		if(oldImage.exists()){ System.out.println("存在");
			oldImage.delete();
		}
		File target = new File(targetDirectory, targetFileName);
		System.out.println(targetDirectory);
		try{
			FileUtils.copyFile(ufile, target);
		}catch(Exception e){
			System.out.println(e);
		}
		DAOFactory.getIUserbaseinfoDAOInstance().updateHeadImage("upload"+number+"/"+targetFileName, getUserId());
		return SUCCESS; 
	}    
	public void setServletContext(ServletContext servletContext) {   
		this.context=servletContext; 
	}
}

