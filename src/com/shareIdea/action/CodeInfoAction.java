package com.shareIdea.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Codeinfo;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015楠烇拷閺堬拷1閺冿拷娑撳宕�:02:23 
 * @version 1.0   
 */
public class CodeInfoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deleteIds;
	private ServletContext context; 
	private int codeId;
	private String codeCommentContent;
	
	private  HttpServletResponse response = ServletActionContext.getResponse();
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private int userId;
	private int getUserId(){
		return Integer.parseInt((String)request.getSession().getAttribute("userId"));
	}
	
	public String getCodeCommentContent() {
		return codeCommentContent;
	}
	public void setCodeCommentContent(String codeCommentContent) {
		this.codeCommentContent = codeCommentContent;
	}
	public int getCodeId() {
		return codeId;
	}
	public void setCodeId(int codeId) {
		this.codeId = codeId;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public String getDeleteIds() {
		return deleteIds;
	}


	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	/**
	 * 閼惧嘲绶辨稉顏冩眽缁屾椽妫挎い鐢告桨code娣団剝浼�
	 * @return
	 */
	public String getPersonSpaceCodeMsg(){
		List<Codeinfo> list = DAOFactory.getICodeInfoDAOInstance().getCodeInfoMsg(getUserId());
		String result="{\"str\":[ ";
		for(Codeinfo code:list){
			result+="{\"codeName\":\""+code.getCodeName()+"\",\"codeSize\":\""+code.getCodeSize()+"\","
					+ "\"codeCommentNo\":\""+code.getCodeCommentNo()+"\",\"codeDown\":\""+code.getCodeCommentNo()+"\","
							+ "\"codeClickNo\":\""+code.getCodeClickNo()+"\",\"codeId\":\""+code.getCodeId()+"\"},";
		}
		result=result.substring(0, result.length()-1)+"]}";
		response.setCharacterEncoding("utf-8");
		
		try {
			response.setContentType("application/json");
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteCodeMsg(){
		DAOFactory.getICodeInfoDAOInstance().deleteCodeMsg(this.deleteIds);
		return null;
	}
	
	public String getViewCodeMsg(){
		System.out.println(codeId);
		Codeinfo coed = DAOFactory.getICodeInfoDAOInstance().getViewCodeMsgByCodeId(this.codeId);
		System.out.println(coed.getCodeName());
		String codeComment =DAOFactory.getICodeInfoDAOInstance().getCodeCommentMsg(this.codeId);
		String codeContent = getCodeContent(coed.getCodePath());
		String result="{\"codeName\":\""+coed.getCodeName()+"\",\"author\":\""+coed.getNickName()+"\""
				+ ",\"uploadTime\":\""+coed.getCodeUpdateTime()+"\",\"codeScore\":\""+coed.getCodeScore()+"\","
				+ "\"codeCapacity\":\""+coed.getCodeCategory()+"\",\"downLoadCount\":\""+coed.getCodeDownNo()+"\","
				+ "\"CommentCount\":\""+coed.getCodeCommentNo()+"\",\"parentIdea\":\""+coed.getIdeainfo().getIdeaTitle()+"\","
			+ "\"codeContext\":\"\",\"ideaId\":\""+coed.getIdeainfo().getIdeaId()+"\",\"codeId\":\""+coed.getCodeId()+"\"}";
		try {
			request.setCharacterEncoding("utf-8");
			request.setAttribute("code", result);
			request.setAttribute("codeContent", codeContent);
			request.setAttribute("codeComment", codeComment);
			DAOFactory.getICodeInfoDAOInstance().addDownNo(this.codeId);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "viewCode";
	}
	/**
	 * @param codePath
	 * @return
	 */
	private String getCodeContent(String codePath) {
		String targetDirectory = ServletActionContext.getServletContext().getRealPath(codePath);
		File codeFile = new File(targetDirectory);
		BufferedReader reader = null;
		String tempString = "",tempStr="";
		if(codeFile.exists()){ 
			try{
				InputStreamReader isr = new InputStreamReader(new FileInputStream(codeFile), "GBK");
				reader = new BufferedReader(isr);
				int line = 1;
				while((tempStr = reader.readLine())!=null){
					tempString+=tempStr+"\n";
					line++;
				}
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return tempString;
	}
	
	public String addCodeComment(){
		if(DAOFactory.getICodeInfoDAOInstance().addCodeComment(this.codeCommentContent, this.codeId, getUserId())){
			return null;
		};
		return null;
	}
	
	
	
}

