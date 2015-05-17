package com.shareIdea.action;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Userbaseinfo;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015��4��4�� ����5:08:24 
 * @version 1.0   
 */
public class FriendOperationAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String friendName;
	private String groupName;
	private String delteGroupName;
	private String newGroupName;
	private String deleteFriendName;
	
	private  HttpServletResponse response = ServletActionContext.getResponse();
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private int userId;
	private int getUserId(){
		return Integer.parseInt((String)request.getSession().getAttribute("userId"));
	}
	
	public String getDeleteFriendName() {
		return deleteFriendName;
	}

	public void setDeleteFriendName(String deleteFriendName) {
		this.deleteFriendName = deleteFriendName;
	}

	public String getNewGroupName() {
		return newGroupName;
	}

	public void setNewGroupName(String newGroupName) {
		this.newGroupName = newGroupName;
	}

	public String getDelteGroupName() {
		return delteGroupName;
	}

	public void setDelteGroupName(String delteGroupName) {
		this.delteGroupName = delteGroupName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 1,昵称必须存在
	 * 2,自己不能添加自己为好友
	 * 3,已经是好友关系的不能添加
	 * @return
	 */
	public String addFriend(){ 
		System.out.println(this.friendName+"''''''''''");
		String errorMsg="";
		Userbaseinfo user = DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(getUserId());
		if(!DAOFactory.getIUserbaseinfoDAOInstance().nickNameExist(this.friendName)){
			errorMsg ="{\"msg\":\"该用户不存在\"}";
			sendMsg(errorMsg);
			return null;
		}
		else if(user.getNickName().equals(this.friendName)){
			errorMsg = "{\"msg\":\"不能添加自己为好友\"}";
			sendMsg(errorMsg);
			return null;
		}else if(!DAOFactory.getIFriendInfoDAOInstance().isFriend(getUserId(), this.friendName)){
			errorMsg = "{\"msg\":\"与该用户已是好友关系\"}";
			sendMsg(errorMsg);
			return null;
		}else if(DAOFactory.getIFriendInfoDAOInstance().addFriend(getUserId(), this.friendName, this.groupName)){
			errorMsg = "{\"msg\":\"添加成功\"}";
			sendMsg(errorMsg);
		}else{ 
			errorMsg = "{\"msg\":\"添加失败\"}";
			sendMsg(errorMsg);
		}
		System.out.println(errorMsg);
		return null;
	}
	/**
	 * 删除该分组。删除Friendgrouping 和 Friendinfo中的所有数据项
	 * @return
	 */
	public String deleteGroupName(){
		if(DAOFactory.getIFriendInfoDAOInstance().deleteGroup(getUserId(), this.delteGroupName)){
			sendMsg("{\"msg\":\"删除成功\"}");
		}else{
			sendMsg("{\"msg\":\"删除失败\"}");
		};
	
		return null;
	}
	private void sendMsg(String errorMsg){
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(errorMsg);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 给当前用户添加一个分组。
	 * @return
	 */
	public String createGroup(){ 
		if(this.newGroupName.equals("全部") ||this.newGroupName.contains(" ") || this.newGroupName.contains(",")){
			sendMsg("{\"msg\":\"非法分组名\"}");
			return null;
		}
		
		if(!DAOFactory.getIFriendInfoDAOInstance().createGroup(getUserId(), this.newGroupName)){
			sendMsg("{\"msg\":\"添加失败\"}");
		}else{
			sendMsg("{\"msg\":\"添加成功\"}");
		};
		return null;
	} 
	
	
	public String deleteFriend(){ 
		if(DAOFactory.getIFriendInfoDAOInstance().deleteFriend(getUserId(), this.deleteFriendName)){
			sendMsg("{\"msg\":\"删除成功\"}");
		}else{
			sendMsg("{\"msg\":\"删除失败\"}");
		}
		
		
		return null;
	}
}

