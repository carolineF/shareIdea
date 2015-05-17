package com.shareIdea.action;

import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Userbaseinfo;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015��4��18�� ����6:10:24 
 * @version 1.0   
 */
public class IdeaAuthorityAction {
	/**
	 * ��ӵ�ǰ���Զ�Ӧ�ĺ���Ȩ��
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

