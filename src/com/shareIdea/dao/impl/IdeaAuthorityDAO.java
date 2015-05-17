package com.shareIdea.dao.impl;

import org.hibernate.Session;

import com.shareIdea.dao.interfaces.IIdeaAuthorityDAO;
import com.shareIdea.po.Ideaauthority;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年4月18日 下午6:09:47 
 * @version 1.0   
 */
public class IdeaAuthorityDAO implements IIdeaAuthorityDAO {
	@Override
	public boolean insertIdeaAuthority(Ideainfo ideainfo,Userbaseinfo userBaseInfo) {
		//添加策略对好友的权限关系
		Ideaauthority authority = new Ideaauthority(ideainfo,userBaseInfo);
		Session session =null;
		try{
			session =  HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(authority);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return false;
	}
}

