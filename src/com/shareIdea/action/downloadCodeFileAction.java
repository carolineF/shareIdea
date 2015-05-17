package com.shareIdea.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月18日 下午6:20:55 
 * @version 1.0   
 */
public class downloadCodeFileAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codeId;
	private String fileName;
	
	public int getCodeId() {System.out.println("6");
		return codeId;
	}

	public void setCodeId(int codeId) {System.out.println("5");
		this.codeId = codeId;
	}

	public String getFileName() {System.out.println("4");
		return fileName;
	}

	public void setFileName(String fileName) {System.out.println("3");
		this.fileName = fileName;
	}
	
	private String getCodeFilePath(){
		String path = DAOFactory.getICodeInfoDAOInstance().getCodeFilePath(this.codeId);
		path=ServletActionContext.getServletContext().getRealPath(path);
		return path;
	}
	public InputStream getDownloadFile() throws Exception { 
		InputStream in = null;
		try{
			//in = ServletActionContext.getServletContext().getResourceAsStream(getCodeFilePath());   
			in= new FileInputStream(new File(getCodeFilePath()));
		}catch(Exception e){
	   		e.printStackTrace();
	   	}
		return in;
	}
    @Override  
    public String execute() throws Exception {  
    	return SUCCESS;  
	}  
}

