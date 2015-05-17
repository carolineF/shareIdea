package com.shareIdea.action;

import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Userbaseinfo;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年4月18日 下午6:10:24 
 * @version 1.0   
 */
public class IdeaAuthorityAction {
	/**
	 * 添加当前策略对应的好友权限
	 * @param ideainfo
	 * @param userIdList
	 * @return
	 */
	public static boolean addAuthority(Ideainfo ideainfo,String[] userIdList){
		if(ideainfo.getIdeaId()==null){
			return false;
		}
		Userbaseinfo userinfo = new Userbaseinfo();
		for(int i=0; i<userIdList.length; i++){
			if(!userIdList[i].equals(" ")){
				userinfo.setUserId(Integer.parseInt(userIdList[i]));
				DAOFactory.getIIdeaAuthorityDAOInstance().insertIdeaAuthority(ideainfo, userinfo);
			}
		}
		return true;
	}
}

