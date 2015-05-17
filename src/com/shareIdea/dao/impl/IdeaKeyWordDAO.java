package com.shareIdea.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shareIdea.dao.interfaces.IIdeaKeyWordDAO;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Ideakeyword;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年4月16日 下午7:25:45 
 * @version 1.0   
 */
public class IdeaKeyWordDAO implements IIdeaKeyWordDAO {

	@Override
	public boolean insertKeyword(Ideakeyword keyword) {
		// 增加关键字
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(keyword);
			session.getTransaction().commit();
		}catch(Exception e){
			HibernateTool.closeSession(session);
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Ideakeyword> selectKeyWord(Ideainfo ideaId) {
		//通过策略id查找关键字
		if(ideaId==null){
			return null;
		}
		ArrayList<Ideakeyword> list = new ArrayList<Ideakeyword>();
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query query = session.createQuery("from Ideakeyword as keyword where keyword.ideainfo.ideaId ="+ideaId.getIdeaId());
			 if(query.list().isEmpty()){
				 return null;
			 }
			 
			 list = (ArrayList<Ideakeyword>) query.list();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			 HibernateTool.closeSession(session);
		}
		
		 return list;
	}

}

