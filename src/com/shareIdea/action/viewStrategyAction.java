package com.shareIdea.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;

public class viewStrategyAction extends ActionSupport  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int ideaId;
	private  HttpServletRequest request = ServletActionContext.getRequest();
	
	public int getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(int ideaId) {
		this.ideaId = ideaId;
	}

	public String viewStrategy(){
		String ideaInformation = DAOFactory.getIIdeaInfoDAOInstance().getIdeainfo(ideaId);
		String jsonCode = DAOFactory.getIIdeaInfoDAOInstance().getIdeaCodeMsg(this.ideaId);
		String commentMsg = DAOFactory.getIIdeaInfoDAOInstance().getIdeaCommentMsg(ideaId);
		String[] res = ideaInformation.split(",");
		String result = "{\"ideaTitle\":\"" + res[0] + "\",\"author\":\"" + res[1] + "\",\"upLoadTime\":\"" + res[2] + "\",\"commentCon\":\"" + res[3] + "\",\"ideaContent\":\"" + res[4] + "\",\"headImage\":\""+res[5]+"\",\"ideaId\":\""+res[6]+"\",\"ideaNum\":\""+res[7]+"\" }";
		request.setAttribute("jsoninfo", result);
		request.setAttribute("jsonCode", jsonCode);
		request.setAttribute("comment", commentMsg);
		System.out.println(jsonCode);
		System.out.println(result);
		System.out.println(commentMsg);
		return "toViewStrategy";
	}
}
