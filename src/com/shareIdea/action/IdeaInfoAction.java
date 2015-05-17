package com.shareIdea.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Ideakeyword;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月9日 上午11:53:34 
 * @version 1.0   
 */
public class IdeaInfoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageSize;
	private int pageNo;
	private String ideaSortName;
	private String deleteIds;
	private int ideaId;
	private String ideaCommentContent;
	private String newIdea;
	private int viewUserId;
	

	private  HttpServletResponse response = ServletActionContext.getResponse();
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private int userId;
	private int getUserId(){
		return Integer.parseInt((String)request.getSession().getAttribute("userId"));
	}
	
	public int getViewUserId() {
		return viewUserId;
	}

	public void setViewUserId(int viewUserId) {
		this.viewUserId = viewUserId;
	}
	public String getNewIdea() {
		return newIdea;
	}

	public void setNewIdea(String newIdea) {
		this.newIdea = newIdea;
	}

	public int getIdeaId() {
		return ideaId;
	}

	public void setIdeaId(int ideaId) {
		this.ideaId = ideaId;
	}

	public String getIdeaCommentContent() {
		return ideaCommentContent;
	}

	public void setIdeaCommentContent(String ideaCommentContent) {
		this.ideaCommentContent = ideaCommentContent;
	}

	public String getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(String deleteIds) {
		this.deleteIds = deleteIds;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getIdeaSortName() {
		return ideaSortName;
	}

	public void setIdeaSortName(String ideaSortName) {
		this.ideaSortName = ideaSortName;
	}

	/**
	 * 初始化个人空间页面时候的策略显示
	 * 默认显示全部分类的前6条信息 第二页之后归类到条件筛选
	 * 需要的数据有当前用户ID  页面显示策略的最大数目
	 * @return
	 */
	public String initPageIdeaMsg(){
		try {
			String result = DAOFactory.getIIdeaInfoDAOInstance().
					getIdeaMsg(getUserId(), "", 6, 1);
			ServletActionContext.getRequest().setAttribute("ideaMsg", result);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return "toPersonSpace";
	}
	/**
	 *选择分类刷新页面的ajax查询
	 * @return
	 */
	public String flushIdeaMsg(){
		try {
			String result = DAOFactory.getIIdeaInfoDAOInstance().
					getIdeaMsg(getUserId(), this.ideaSortName, this.pageSize, this.pageNo);
			System.out.println(this.ideaSortName+ this.pageSize+this.pageNo);
			response.setCharacterEncoding("utf-8");
			
			response.setContentType("application/json");
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return null;
	}
	
	/**
	 * 删除策略
	 * @return
	 */
	public String deleteIdeaMsg(){ 
		DAOFactory.getIIdeaInfoDAOInstance().deleteIdeas(getUserId(), this.deleteIds);
		return null;
	}
	/**
	 * 策略仓库中策略的展示
	 * @return
	 */
	public String getStrategyIdeaMsg(){
		try {
			String result = DAOFactory.getIIdeaInfoDAOInstance().
					getStrategyIdeaMsg(this.ideaSortName, this.pageSize, this.pageNo);
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().print(result);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * 个人空间里好友策略的展示
	 * 1，得到当前用户的userId信息 查询ideaAuthority表中的信息
	 * 得到好友对当前用户开放的策略信息
	 * @return
	 */
	public String getFriendIdea(){
		try{
			String result = DAOFactory.getIIdeaInfoDAOInstance().getFriendIdeaMsg(getUserId(), this.pageNo, this.pageSize,this.ideaSortName);
			System.out.println(this.ideaSortName+ this.pageSize+this.pageNo);
			
			response.setCharacterEncoding("utf-8");
			
			response.setContentType("application/json");
			response.getWriter().print(result);
			response.getWriter().flush();
			response.getWriter().close();
		}catch(Exception e){
			System.out.println(e);
		}
		return null;
	}
	
	public String addIdeaComment(){
		if(DAOFactory.getIIdeaInfoDAOInstance().addIdeaComment(this.ideaCommentContent, this.ideaId, getUserId())){
			System.out.println("ok");
			return null;
		};
		return null;
	}
	
	public String modifyIdea(){
		DAOFactory.getIIdeaInfoDAOInstance().modifyIdea(ideaId, newIdea);
		return null;
	}
	
	public String getIdeaByIdeanum() throws IOException{
		String ideaNum = request.getParameter("ideaNum");
		//根据策略编号去数据库中查找
		Ideainfo ideaInfo = DAOFactory.getIIdeaInfoDAOInstance().selectIdeainfoByIdeaNum(ideaNum);
		String category = "";
		String content = "";
		String keyWord = "";
		String data = "error";
		int ideaId;
		if(ideaInfo != null){
			category = ideaInfo.getIdeaCategory();
			content = ideaInfo.getIdeaComtent();
			ideaId = ideaInfo.getIdeaId();
			//通过策略id查找策略关键字
			ArrayList<Ideakeyword> keyWords = new ArrayList<Ideakeyword>();
			keyWords = DAOFactory.getIIdeaKeyWordDAOInstance().selectKeyWord(ideaInfo);
			if(keyWords!= null){
				for(int i=0; i<keyWords.size();i++){
					keyWord += keyWords.get(i).getKeyWord()+",";
				}
				keyWord = keyWord.substring(0, keyWord.length()-1);
			}
			data = "{\"ideaCatogry\" :\""+category+"\",\"ideaContent\":\""+
					content+"\",\"ideaKeyWord\":\""+keyWord+"\",\"ideaId\":\""+
					ideaId+"\"}";
		}
		
		response = ServletActionContext.getResponse();
		response.setContentType( "text/xml" ); 
		response.setCharacterEncoding( "UTF-8" ); 
		response.getWriter().print(data);
		return null;
	}
	/**
	 * 初始化查看他人个人空间信息
	 * 需要参数，当前用户id 查看用户的昵称和id
	 * @return
	 */
	public String showOtherSpaceIdea(){
		System.out.println("action"+"             "+getUserId()+"/"+viewUserId);
		if(getUserId()==viewUserId){
			return initPageIdeaMsg();
		}
		String result = DAOFactory.getIIdeaInfoDAOInstance().showOtherSpaceIdeaMsg(getUserId(), viewUserId);
		System.out.println(result);
		try {
			request.setCharacterEncoding("utf-8");
			request.setAttribute("ideaMsg", result);
			request.setAttribute("userMsg", DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(viewUserId));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "viewOtherSpace";
	}
}

