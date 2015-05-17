package com.shareIdea.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.shareIdea.dao.interfaces.IIdeaInfoDAO;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Codeinfo;
import com.shareIdea.po.Ideacomment;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月8日 下午5:29:42 
 * @version 1.0   
 */
public class IdeaInfoDAO implements IIdeaInfoDAO{
	
	@SuppressWarnings("rawtypes")
	@Override
	public Ideainfo selectIdeainfoByIdeaNum(String ideaNum) {
		// 通过策略编号查询Ideainfo对象
		Session session =null;
		List list = null;
		try{
			session = HibernateTool.getSession(); 
			session.getTransaction().begin();
			Criteria criteria = session.createCriteria(Ideainfo.class);
			criteria.add(Restrictions.eq("ideaNum", ideaNum));
			list = criteria.list();

			if(list.isEmpty()){
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);	
		}
		return (Ideainfo) list.get(0);
	}

	@Override
	public int insertIdeainfo(Ideainfo ideainfo) {
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(ideainfo);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return ideainfo.getIdeaId();
	}

	@Override
	public boolean updateWithIdeaNum(Ideainfo ideainfo) {
		if(ideainfo== null||ideainfo.getIdeaId()== null){
			return false;
		}
		Session session =null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.saveOrUpdate(ideainfo);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
	}

	@Override
	public boolean updateWithIdeaAuthority(Ideainfo ideainfo) {
		if(ideainfo == null||ideainfo.getIdeaId() == null){
			return false;
		}
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.saveOrUpdate(ideainfo);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);	
		}
		return true;
	}
	
	/* 获得当前用户 sortName分类下的第pageNum页的pageSize个数据
	 * 
	 * Hibernate提供了分页操作的API所以 简单的封装一个方法来实现分页查询
	 * 
	 * getIdeaMsg
	 */
	@Override
	public String getIdeaMsg(int userId, String sortName, int pageSize,
			int pageNum) throws Exception {
		String HQL="select new Ideainfo(idea.ideaId,idea.ideaTitle,idea.ideaComtent,idea.nickName,idea.ideaClickNo,idea.ideaCategory) "
				+ "from Ideainfo as idea where idea.userbaseinfo.userId="+userId+" and"
						+ " idea.ideaCategory like '%"+sortName+"%' order by idea.ideaId";
		Session session = null;
		String result="{";
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			
			int maxBlockCount = q.list().size();
			int pageCount = (int) Math.ceil(maxBlockCount/Float.parseFloat(""+pageSize));
			result+="\"maxBlockCount\":"+maxBlockCount+", \"pageCount\":"+pageCount+",\"Block\": [ ";
			
			q.setFirstResult(pageSize*(pageNum-1));
			q.setMaxResults(pageSize);
			
			@SuppressWarnings("unchecked")
			List<Ideainfo> l = q.list();
			for(Ideainfo idea:l){
				result+="{\"title\":\""+idea.getIdeaTitle()+"\",\"content\":\""+idea.getIdeaComtent()+"\","
						+ "\"author\":\""+idea.getNickName()+"\",\"userId\":\""+userId+"\",\"count\":\""+idea.getIdeaClickNo()+"\",\"id\":\""+idea.getIdeaId()+"\"},";
			}
			result = result.substring(0, result.length()-1);
			result+="]}";
			System.out.println(result);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			HibernateTool.closeSession(session);
		}
	}
	/**
	 * 得到策略仓库的策略信息
	 */
	@Override
	public String getStrategyIdeaMsg(String sortName, int pageSize,int pageNum) throws Exception {
		String[] temp = sortName.split(" ");
		if(temp.length==2){
			sortName="'"+temp[0]+"%' and idea.ideaCategory like '"+temp[1]+"'";
		}else{
			sortName="'"+sortName+"%'";
		}
		String HQL="select new Ideainfo(idea.ideaId,idea.ideaTitle,idea.ideaComtent,idea.nickName,idea.ideaClickNo,idea.ideaCategory) "
				+ "from Ideainfo as idea where "
						+ " idea.ideaCategory like "+sortName+" and idea.publicAuthority=1 order by idea.ideaClickNo desc";
		
		Session session = null;
		String result="{";
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			
			int maxBlockCount = q.list().size();
			int pageCount = (int) Math.ceil(maxBlockCount/Float.parseFloat(""+pageSize));
			
			result+="\"maxBlockCount\":"+maxBlockCount+", \"pageCount\":"+pageCount+",\"Block\": [ ";
			
			q.setFirstResult(pageSize*(pageNum-1));
			q.setMaxResults(pageSize);
			@SuppressWarnings("unchecked")
			List<Ideainfo> l = q.list();
			
			for(Ideainfo idea:l){
				result+="{\"title\":\""+idea.getIdeaTitle()+"\",\"content\":\""+idea.getIdeaComtent()+"\","
						+ "\"author\":\""+idea.getNickName()+"\",\"userId\":\""+getUserId(idea.getNickName())+"\",\"count\":\""+idea.getIdeaClickNo()+"\",\"id\":\""+idea.getIdeaId()+"\"},";
			}
			result = result.substring(0, result.length()-1);
			result+="]}";
			System.out.println(result);
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			HibernateTool.closeSession(session);
		}
	}
	/**
	 * 通过nickName获得此用户 的userId
	 * @param nickName
	 * @return userId
	 */
	private int getUserId(String nickName){
		int userId=0;
		String HQL = "select user.userId from Userbaseinfo as user where "
				+ "user.nickName='"+nickName+"'";
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			userId = (Integer)q.list().get(0);
		}catch(Exception e){
			return -1;
		}finally{
			HibernateTool.closeSession(session);
		}
		return userId;
	}
	/**
	 * 批量删除策略
	 * 
	 *同时删除权限表中的数据
	 */
	@Override
	public boolean deleteIdeas(int userId,String ideaIds){
		String h="";
		for(String id:ideaIds.split(",")){
			h+="idea.ideaId="+id+" or ";
		}
		h=h.substring(0, h.length()-3);
		System.out.println(userId+ideaIds);
		String HQL="from Ideainfo as idea where "+h;
		Session session = HibernateTool.getSession();
		session.getTransaction().begin();
		Query q = session.createQuery(HQL);
		List<Ideainfo> l = q.list();
		for(Ideainfo idea:l){
			session.delete(idea);
		}
		session.getTransaction().commit();
		modifyIdeaNo(userId,"-"+ideaIds.split(",").length);
		HibernateTool.closeSession(session);
		return true;
	}
	
	/**
	 * @param userId
	 * @param string
	 */
	private void modifyIdeaNo(int userId, String length) {
		String SQL = "update userbaseinfo set userIdeaNo=userIdeaNo"+length+" where userId="+userId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(SQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
	}
	
	/**
	 * 通过UserId得到权限范围内的好友idea信息
	 */
	@Override
	public String getFriendIdeaMsg(int userId,int pageNum,int pageSize,String sortName){
		//1通过id找到所有的ideaId
		String HQL = "select it.ideainfo.ideaId from Ideaauthority as it "
				+ "where it.userbaseinfo.userId="+userId;
		Session session = null;
		
		String query = "select new Ideainfo(idea.ideaId,idea.ideaTitle,idea.ideaComtent,idea.nickName,idea.ideaClickNo,idea.ideaCategory) from "
				+ "Ideainfo as idea where (";
		
		String result="{";
		
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Integer> l = q.list();
			for(int str : l){
				query+=" idea.ideaId="+str+" or";
			}
			
			query = query.substring(0,query.length()-2)+ ") and (idea.ideaCategory like '%"+sortName+"%') order by idea.ideaId";
			System.out.println(query);
			Query q1 = session.createQuery(query);
			
			int maxBlockCount = q1.list().size();
			int pageCount = (int) Math.ceil(maxBlockCount/Float.parseFloat(""+pageSize));
			
			result+="\"maxBlockCount\":"+maxBlockCount+", \"pageCount\":"+pageCount+",\"Block\": [ ";
			
			q1.setFirstResult(pageSize*(pageNum-1));
			q1.setMaxResults(pageSize);
			@SuppressWarnings("unchecked")
			List<Ideainfo> ll = q1.list();
			
			for(Ideainfo idea:ll){
				result+="{\"title\":\""+idea.getIdeaTitle()+"\",\"content\":\""+idea.getIdeaComtent()+"\","
						+ "\"author\":\""+idea.getNickName()+"\",\"count\":\""+idea.getIdeaClickNo()+"\",\"id\":\""+idea.getIdeaId()+"\"},";
			}
			result = result.substring(0, result.length()-1);
			result+="]}";
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}finally{
			HibernateTool.closeSession(session);
		}
	}
	@Override
	public String getIdeaCommentMsg(int ideaId){
		String HQL = "from Ideacomment as ideac where ideac.ideainfo.ideaId = "+ideaId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Ideacomment> l = q.list();
			addClickDeal(ideaId);
			return dealIdeaComment(l);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return null;
	}
		/**
	 * @param ideaId
	 */
	private void addClickDeal(int ideaId) {
		String HQL = "update ideainfo set ideaClickNo = ideaClickNo+1 where ideaid="+ideaId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);	
		}
	}

		/**
	 * @param l
	 * @return
	 */
	private String dealIdeaComment(List<Ideacomment> l) {
		
		double pageCount=Math.ceil(l.size()/Float.parseFloat("6"));
		if(l.size()<1){
			return "{\"maxBlockCount\":\""+l.size()+"\",\"pageCount\":\""
				+pageCount+"\",\"commentContent\":[]}";
		}
		int i=1;
		String result = "{\"maxBlockCount\":\""+l.size()+"\",\"pageCount\":\""
				+pageCount+"\",\"commentContent\":[";
		for(Ideacomment ideac : l){
			result+="{\"headUrl\":\""+ideac.getUserbaseinfo().getHeadimage()+"\","
					+ "\"name\":\""+ideac.getUserbaseinfo().getNickName()+"\","
					+ "\"content\":\""+ideac.getIdeaCommentContent()+"\","
					+ "\"num\":\""+i+++"\",\"time\":\""+ideac.getIdeaCommentTime()+"\"},";
		}
		result=result.substring(0,result.length()-1)+"]}";
		return result;
	}
	@Override
	public boolean addIdeaComment(String ideaCommentContent,int ideaId,int userId){
		Ideacomment ideac = new Ideacomment();
		ideac.setIdeaCommentContent(ideaCommentContent);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = sdf.format(new Date());
		ideac.setIdeaCommentTime(date);
		
		ideac.setIdeainfo(new Ideainfo(ideaId));
		ideac.setUserbaseinfo(new Userbaseinfo(userId));
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(ideac);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		modifyIdeaCommentNo(ideaId,"+1");
		return true;
	}
	/**
	 * @param userId
	 * @param string
	 */
	private void modifyIdeaCommentNo(int ideaId, String length) {
		String SQL = "update ideainfo set IdeaCommentNo=IdeaCommentNo"+length+" where ideaId="+ideaId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(SQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
	}

	//查看策略
	@Override
	public String getIdeainfo(int ideaId){
		String result = null;
		String HQL = "select new Ideainfo(idea.ideaTitle,idea.nickName,idea.updateTime,idea.ideaCommentNo,idea.ideaComtent,idea.userbaseinfo,idea.ideaId,idea.ideaNum) from Ideainfo as idea where idea.ideaId="+ ideaId;
		Session session = null;
		try{
			session= HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Ideainfo> idea = q.list();
			if(idea.size()>0){
				for(Ideainfo c:idea){
					result = c.getIdeaTitle() + "," + c.getNickName() + "," + c.getUpdateTime() + "," + c.getIdeaCommentNo() + "," + c.getIdeaComtent()+","+c.getUserbaseinfo().getHeadimage()+","+c.getIdeaId()+","+c.getIdeaNum();
				System.out.println(result);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return result;
	}
	

	@Override
	public String getIdeaCodeMsg(int ideaid){
		String HQL = "select new Codeinfo(code.nickName,code.codeName,code.codeId) from Codeinfo as code where code.ideainfo.ideaId = "+ideaid;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Codeinfo> code = q.list();
			return getIdeaCodeMsgToString(code);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return null;
	}
/**
	 * @param code
	 * @return
	 */

	private String getIdeaCodeMsgToString(List<Codeinfo> code) {
		String result = "{\"code\":[";
		if(code.size()>0){
			for(Codeinfo c :code){
				result+="{\"codeName\":\""+c.getCodeName()+"\",\"codeAuthor\":\""+c.getNickName()+"\",\"codeId\":\""+c.getCodeId()+"\"},";
			}
			result = result.substring(0, result.length()-1)+"]}";
		}else{
			result="{}";
		}
		System.out.println("getIdeaCodeMsg+++++++++:"+result);
		return result;
	}
	@Override
	public String modifyIdea(int ideaId,String newIdea){
		String HQL = "update Ideainfo as idea  set idea.ideaComtent='"+newIdea+"' where idea.ideaId="+ideaId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
		return null;
	}
	@Override
	public String showOtherSpaceIdeaMsg(int userId,int otherUserId){
		String HQL = "select new Ideainfo(idea.ideaId,idea.ideaTitle,idea.ideaComtent,idea.nickName,idea.ideaClickNo,idea.ideaCategory) from "
				+ "Ideainfo as idea , Ideaauthority as it where idea.userbaseinfo.userId="+otherUserId+" and it.ideainfo.ideaId=idea.ideaId and it.userbaseinfo.userId="+userId;
		
		Session session = null;
		String result = "{";
		try{
				session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Ideainfo> l = q.list();
			int maxBlockCount = q.list().size();
			int pageCount = (int) Math.ceil(maxBlockCount/Float.parseFloat(""+9));
			
			result+="\"maxBlockCount\":"+maxBlockCount+", \"pageCount\":"+pageCount+",\"Block\": [ ";
			
			for(Ideainfo idea:l){
				result+="{\"title\":\""+idea.getIdeaTitle()+"\",\"content\":\""+idea.getIdeaComtent()+"\","
						+ "\"author\":\""+idea.getNickName()+"\",\"userId\":\""+getUserId(idea.getNickName())+"\",\"count\":\""+idea.getIdeaClickNo()+"\",\"id\":\""+idea.getIdeaId()+"\"},";
			}
			result = result.substring(0, result.length()-1);
			result+="]}";
			System.out.println(result);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}finally{
			HibernateTool.closeSession(session);
		}
		return result;
	}
	
	public static void main(String[] args)
	{
		new IdeaInfoDAO().deleteIdeas(3, "2");
	}
}

