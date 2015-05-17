package com.shareIdea.dao.impl;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shareIdea.dao.interfaces.IFriendInfoDAO;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Friendgrouping;
import com.shareIdea.po.Friendinfo;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年3月31日 上午10:30:20 
 * @version 1.0   
 */
public class FriendInfoDAO implements IFriendInfoDAO {
	/* 
	 * getFriendGroupingMsg
	 */
	@Override
	public LinkedHashMap<String, ArrayList<Userbaseinfo>> getFriendGroupingMsg(int userId) {
		LinkedHashMap<String, ArrayList<Userbaseinfo>> result = new LinkedHashMap<String, ArrayList<Userbaseinfo>>();
		Session session =null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			
			String HQL = "select new Friendgrouping(friend.groupId,friend.groupName) from Friendgrouping as friend where friend.userbaseinfo.userId=:id "
					+ "order by friend.groupId";
			Query q = session.createQuery(HQL);
			q.setInteger("id",userId);
			@SuppressWarnings("unchecked")
			List<Friendgrouping> fg = q.list();
			
			for(Friendgrouping friend : fg){
				ArrayList<Userbaseinfo> array = new ArrayList<Userbaseinfo>();
				int groupId = friend.getGroupId();
				String groupName = friend.getGroupName();
				
				String HQL1 = "select fi.userbaseinfo.userId from Friendinfo as fi where "
						+ "fi.friendgrouping.groupId=:id";
				Query q1 = session.createQuery(HQL1);
				q1.setInteger("id", groupId);
				@SuppressWarnings("unchecked")
				List<Integer> list1 = q1.list();
				for(Integer l:list1){
					Userbaseinfo user = DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(l);
					array.add(user);
				}
				result.put(groupName, array);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return result;
	}
	/**
	 * 判断此nickName是否为该ID的朋友
	 * 在该ID的全部分类里面遍历nickName属性，若有则true否则false
	 */
	@Override
	public boolean isFriend(int userId,String nickName){
		String HQL = "select fi.userbaseinfo.userId from Friendinfo as fi ,Friendgrouping as fg where "
				+ "fg.userbaseinfo.userId=:id and fg.groupName='全部' and fi.friendgrouping.groupId=fg.groupId";
		
		Session session =null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			q.setInteger("id", userId);
			@SuppressWarnings("unchecked")
			List<Integer> l = q.list();
			for(Integer userid :l){
				String nick = DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(userid).getNickName();
				if(nick.equals(nickName)){
					HibernateTool.closeSession(session);
					return false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
	}
	/**
	 * 1，给id的“全部”分类下添加nickname用户 
	 * 2，给nickname用户添加id用户 
	 * 3，若groupName不是“全部” 添加nickName到groupName下 
	 * 	nickName是不能当做数据添加的，所以必须找到friendUserId
	 * return boolean  成功返回true
	 */
	@Override
	public boolean addFriend(int userId,String nickName,String groupName){ 
		int fUserId = this.getUserId(nickName);
		int groupId = this.getGroupId(userId,groupName);
		int userAllId = this.getGroupId(userId, "全部");
		int fAllId = this.getGroupId(fUserId, "全部");
		if(!this.setFriendRelation(userId, fAllId)){
			return false;
		}
		if(!this.setFriendRelation(fUserId, userAllId)){
			return false;
		}
		if(!groupName.equals("全部")){
			if(!this.setFriendRelation(fUserId, groupId)){
				return false;
			}
		}
		
		modifyFriendNo(userId,"+1");
		return true;
	}
	/**
	 * @param userId
	 */
	private void modifyFriendNo(int userId,String num) {
		String HQL = "update userbaseinfo set userFriendNo=userFriendNo"+num+" where userid = "+userId;
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
	 * 把fUserId添加到userId的groupId分组下。
	 * @param userId
	 * @param fUserId
	 * @param groupId
	 * @return
	 */
	private boolean setFriendRelation(int fUserId,int groupId){
		Friendgrouping fg = new Friendgrouping(groupId);
		Userbaseinfo user = new Userbaseinfo(fUserId);
		Friendinfo fi = new Friendinfo(fg,user);
		Session session=null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(fi);
			session.getTransaction().commit();
		}catch(Exception e){
			return false;
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
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
	 * 通过groupName获得groupId
	 * @param groupName
	 * @return
	 */
	private int getGroupId(int userId,String groupName){ 
		int groupId = 0;
		String HQL = "select fg.groupId from Friendgrouping as fg where "
				+ "fg.groupName='"+groupName+"' and fg.userbaseinfo.userId="+userId;
		Session session =null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			groupId = (Integer)q.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			HibernateTool.closeSession(session);
		}
		return groupId;
	}
	/**
	 * 1,删除Friendinfo中的数据
	 * 2,删除Friendgouping的数据
	 * @param groupName
	 * @return
	 */
	@Override
	public boolean deleteGroup(int userId,String groupName){
		int groupId;
		Session session = null;
		String HQL = "select fg.groupId from Friendgrouping as fg where"
				+ " fg.userbaseinfo.userId="+userId+" and fg.groupName='"+groupName+"'";
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			groupId=(Integer)q.list().get(0);
			
			String HQL2 = "delete from Friendinfo as fi where fi.friendgrouping.groupId="+groupId;
			session.createQuery(HQL2).executeUpdate();
			
			String HQL1 = "delete from Friendgrouping as fg where fg.groupId ="+groupId;
			session.createQuery(HQL1).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			return false;
		}finally{
			HibernateTool.closeSession(session);
		}
		
		return true;
	}
	/**
	 * 1,Friendgouping中添加该用户的一个新的分组信息
	 * 只需要给Friendgrouping插入一条数据即可
	 * @param userId
	 * @param groupName
	 * @return
	 */
	@Override
	public boolean createGroup(int userId,String groupName){ 
		System.out.println("进入DAO");
		Userbaseinfo user = new Userbaseinfo(userId);
		Friendgrouping fg = new Friendgrouping(user,groupName);
		Session session=null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(fg);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
	}

	/**
	 * 测试通过
	 * 
	 * 和添加好友一样，删除也是相互的！
	 * 1，删除当前用户friendinfo下的目标friend好友关系信息。
	 * 2，删除目标用户下当前用户的好友关系信息
	 * 在删除的过程中除了删除特定分类下的信息，还要删除‘全部’分类的信息
	 * 
	 * 因为关系是相互的，所以删除过程抽取出来成为一个方法。deleteRelationShip
	 * @param userId
	 * @param friendName
	 * @return
	 */
	@Override
	public boolean deleteFriend(int userId,String friendName){
		int friendUserId = this.getUserId(friendName);
		try{
			deleteRelationShip(userId,friendUserId);
			deleteRelationShip(friendUserId,userId);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		modifyFriendNo(userId,"-1");
			return true;
	}
	
	private void deleteRelationShip(int userId,int friendId){
		String HQL = "select fg.groupId from Friendgrouping as fg where "
				+ "fg.userbaseinfo.userId="+userId;
		Session session  = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Integer> l =  q.list();
			for(Integer i : l){
				String deleteHQL ="delete from Friendinfo as fi where fi.userbaseinfo="+friendId+" "
						+ "and fi.friendgrouping.groupId="+i;
				session.createQuery(deleteHQL).executeUpdate();
			}
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
	}
}

