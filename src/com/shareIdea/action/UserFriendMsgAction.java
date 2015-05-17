package com.shareIdea.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Userbaseinfo;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月7日 下午4:26:31 
 * @version 1.0   
 */
public class UserFriendMsgAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  HttpServletResponse response = ServletActionContext.getResponse();
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private int userId ;
	public int  getUserId(){
		return Integer.parseInt((String)request.getSession().getAttribute("userId"));
	};
	
	public String execute(){
		LinkedHashMap<String,ArrayList<Userbaseinfo>> hash = null;
		hash = DAOFactory.getIFriendInfoDAOInstance().getFriendGroupingMsg(getUserId());
		String responseMsg = dealHashToJson(hash);
		ServletActionContext.getRequest().setAttribute("json",responseMsg);
		return SUCCESS;
	}
	
	private String dealHashToJson(Map<String,ArrayList<Userbaseinfo>> hash){ 
		String maxFriendCount;
		String pageCount;
		String jsonFriend="var jsonFriend={";
		String result = "",item="";
		String str = "var addressListJson = [";
		ArrayList<String> gName = new ArrayList<String>();
		for (String key:hash.keySet()) {
			
			String groupName = key;
			gName.add(groupName);
			ArrayList<Userbaseinfo> array = hash.get(key);
			result+=groupName+" ";

			maxFriendCount="'"+array.size()+"',";
			pageCount="'"+Math.ceil(array.size()/Float.parseFloat("6"))+"',";
			jsonFriend += "maxFriendCount:"+maxFriendCount+"pageCount:"+pageCount;
			String userIdMsg="";
			for(Userbaseinfo user : array){ 
				result+=user.getNickName()+":";
				userIdMsg+=user.getUserId()+":";
				item = "{headImage:'"+user.getHeadimage()+"',name:'"+user.getNickName()+"',ideaCount:'"
						+ user.getUserIdeaNo()+"',groupName:'"+groupName+"'},"+item;
			}
			result = result.substring(0, result.length()-1);
			result+=" "+userIdMsg+",";
			
		}
		
		jsonFriend+="\"colFriend\":["+item+"]}";
		String[] sort =  result.split(",");
		for(int i=0;i<sort.length;i++){
			if(sort[i].length()>1)
			sort[i]=sort[i].substring(0,sort[i].length()-1);
			if(sort[i].contains(" ")){
				String[] group = sort[i].split(" ");
				if(group.length>0){
					str += "{\"groupName\":\""+group[0]+"\",\"groupList\":\""+group[1]+"\",\"userIds\":\""+group[2]+"\"},"; 
				}
			}else{ 
				str += "{\"groupName\":\""+sort[i]+"\",\"groupList\":\"\",\"userIds\":\"\"},";
			}
		}
		str = str.substring(0, str.length()-1)+"];";
		request.setAttribute("groupName", gName);
		return str+"<"+jsonFriend;
	}
	
	public String getFriendList() throws IOException{
		LinkedHashMap<String,ArrayList<Userbaseinfo>> hash = null;
		hash = DAOFactory.getIFriendInfoDAOInstance().getFriendGroupingMsg(getUserId());
		String responseMsg = dealHashToJson(hash);
		response.setContentType( "text/xml" ); 
		response.setCharacterEncoding( "UTF-8" ); 
		String friendList = responseMsg.substring(21).split("<")[0];
		response.setContentType("application/json");
		response.getWriter().print("{\"root\":"+friendList.substring(0, friendList.length()-1).split("}")[0]+"}]}");
		response.getWriter().flush();
		response.getWriter().close();
		System.out.println("{\"root\":"+friendList.substring(0, friendList.length()-1).split("}")[0]+"}]}");
		return null;
	}
	public static void main(String[] args){
		new UserFriendMsgAction().execute();
	}
}



