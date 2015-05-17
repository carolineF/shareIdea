package com.shareIdea.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shareIdea.dao.interfaces.IUserbaseinfoDAO;
import com.shareIdea.po.Friendgrouping;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  huazi
 *  E-mail: huaziHear@gmail.com
 *  @date  2015年3月24日 下午2:44:34 
 *  @version 1.0   
 */
public class UserbaseinfoDAO implements IUserbaseinfoDAO {
	@Override
	public boolean updateNo(String colum, int userId, boolean flag,int range) {
		String temp ="";
		if(flag){
			temp = colum+" + " + range;
		}else{
			temp = colum+" + " + range;
		}
		String sql = "update Userbaseinfo set "+ colum +"="+temp+
		" where userId = :userId";
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query query = session.createSQLQuery(sql);
			query.setInteger("userId", userId);
			query.executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
	}

	/* 
	 * insertUser 测试通过
	 * 插入之前不用确定nickname的唯一性。因为在注册提交表单的时候，
	 * 用Ajax会保证nickname的唯一性
	 */
	@Override 
	public boolean insertUser(Userbaseinfo user){
		Session session = null;
		user.setUserFriendNo(0);
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(user);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		int id = getUserId(user.getNickName())==-1?null:getUserId(user.getNickName());
		return initAddress(id);
	}
	private boolean initAddress(int userId){
		Userbaseinfo user = new Userbaseinfo(userId);
		Friendgrouping fg= new Friendgrouping(user,"全部");
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
	private int getUserId(String nickName){
		int userId=0;
		String HQL = "select user.userId from Userbaseinfo as user where "
				+ "user.nickName='"+nickName+"'";
		Session session = null;
		
		try{
			session= HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			userId = (Integer)q.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
		finally{
			HibernateTool.closeSession(session);
		}
		return userId;
	}
	/* 
	 * updateUserMsg 测试通过
	 * 更新修改用户信息
	 * 插入日期的时候，必须格式化成 yyyy-MM-dd hh-mm-ss
	 */
	@Override 
	public boolean updateHeadImage(String headImage,int userId) throws Exception{
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query query = session.createQuery("update Userbaseinfo t set t.headimage = '"+headImage+"' where id = "+userId);
			query.executeUpdate();
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
	 * constructor
	 */
	public UserbaseinfoDAO() {
	}
	
	/* 
	 * nickNameExist  测试通过
	 * 查询此昵称是否可用！
	 */
	@Override
	public boolean nickNameExist(String nickName) {
		Session session =null;
		boolean flog = false;
		try{
			session =  HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery("select userId from Userbaseinfo as user where user.nickName=:name");
			q.setString("name", nickName);
			flog = !q.list().isEmpty();
		}catch(Exception r){
			r.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return  flog;
	}
	/* 
	 * getLastLoginTime 测试通过
	 */
	@Override
	public String getLastLoginTime(int id) {
		Session session = null;
		String time ="";
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery("select user.lastLoginTime from Userbaseinfo as user where user.userId=:id");
			q.setInteger("id",id);
			time =  (String)q.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return time;
	}
	/* 
	 * getUserAllMsgById  测试通过
	 */
	@Override
	public Userbaseinfo getUserAllMsgById(int id) {
		Session session = null;
		Userbaseinfo user =null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery("from Userbaseinfo as user where user.userId=:id");
			q.setInteger("id",id);
			if(q.list().size()>0)
			    user=  (Userbaseinfo)q.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return user;
	}
	
	@Override
	public String getPasswordByAccount(String account) {
		String HQL = ""; 
		int i = account.indexOf("@");
		String mqStr;
		String loginModel;
		 if(i == -1){ 
			 mqStr = "phoneNumber";
		 }
		 else{
			 mqStr = "userEmail";
		 }
		 HQL = "select user.userPassword from Userbaseinfo as user where user." + mqStr + "=:account";
		 Session sessionPassword = HibernateTool.getSession();
		 sessionPassword.getTransaction().begin();
		 Query quPassword = sessionPassword.createQuery(HQL);
		 quPassword.setString("account", account);
		 if(quPassword.list().isEmpty()){
			 return null;
		 }
		 String password = (String)quPassword.list().get(0);
		 
		 HQL = "select user.nickName from Userbaseinfo as user where user." + mqStr + "=:account";
		 Session sessionNickName = HibernateTool.getSession();
		 sessionNickName.getTransaction().begin();
		 Query quNickName = sessionNickName.createQuery(HQL);
		 quNickName.setString("account", account);
		 if(quNickName.list().isEmpty()){
			 return null;
		 }
		 String nickName = (String)quNickName.list().get(0);
		 
		 HQL = "select user.userId from Userbaseinfo as user where user." + mqStr + "=:account";
		 Session sessionId = HibernateTool.getSession();
		 sessionId.getTransaction().begin();
		 Query quId = sessionId.createQuery(HQL);
		 quId.setString("account", account);
		 if(quId.list().isEmpty()){
			 return null;
		 }
		 int id = (int)quId.list().get(0);
		 String strId = id + "";
		 loginModel = password + "," + strId +  "," + nickName;
		 return loginModel;
	}
	@Override
	public boolean updatePassword(int userId, String newPassword) {
		String HQL = "update Userbaseinfo as user set userPassword='"+newPassword+
				"' where userId="+userId;
		Session session=null;
		int update=0;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			update = q.executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
			return false;
		}finally{
			HibernateTool.closeSession(session);
		}
			
		return update == 0 ? false:true;
	}
	
	@Override
	public boolean phoneNoExist(String phoneNo) {
		Session session = null;
		boolean flog = false;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery("select userId from Userbaseinfo as user where user.phoneNumber=:name");
			q.setString("name", phoneNo);
			flog = !q.list().isEmpty();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		
		
		return  flog;
	}

	@Override
	public boolean emailNoExist(String emailNo) {
		Session session = null;
		boolean flog = false;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery("select userId from Userbaseinfo as user where user.userEmail=:name");
			q.setString("name", emailNo);
			flog = !q.list().isEmpty();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return  flog;
	}
	
	@Override
	public void setPasswordByAccount(String account, String newPassword) {
		String accountType = "userEmail";
		if(account.indexOf("@") == -1){
			accountType = "phoneNumber";
			System.out.println("手机");
		}
		String hql = "update Userbaseinfo as user set user.userPassword=:pass where user." + accountType + "=:account";
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(hql);
			q.setString("pass", newPassword);
			q.setString("account", account);
			q.executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
	}
	/**
	 * 更新用户的登陆时间
	 * 有两项 1 lastLoginTime nowTime
	 * 每次登陆只需要把nowTime赋值给lastLoginTime 
	 * 然后给nowTime赋值为当前时间
	 * 
	 * 判断nowTime是否为空，为空返回false；  用作判断是否通过调研测试
	 * @return
	 */
	@Override 
	public boolean isTested(int userId){
		String nowTime = "select user.nowTime from Userbaseinfo as user where user.userId="+userId;
		
		Session session=null;
		int flog=1;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(nowTime);
			String time = (String)q.list().get(0);
			if(time==null){
				flog=0;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
			return flog==1?true:false;
	}
	
	@Override 
	public boolean insertSurvey(int userId, String survey) {
		String HQL = "update Userbaseinfo  as user set user.survey='"+survey+"' where user.userId="+userId;	
		Session session = null;
		
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			q.executeUpdate();
			session.getTransaction().commit();
			updateLoginTime(userId);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
	}

	/**
	 * @param userId
	 */
	@Override
	public void updateLoginTime(int userId) {

		String updateHQL = "update Userbaseinfo  set lastLoginTime= nowTime where "
				+ "userId="+userId; 
		
		Session session = null;
		
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(updateHQL).executeUpdate();
			
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String newTime = sd.format(new Date());
			
			String Hql = "update Userbaseinfo as user set user.nowTime='"+newTime+"' where user.userId="+userId;
			session.createQuery(Hql).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
	}

	@Override
	public boolean updateUserMsg(Userbaseinfo userInfo,int id) {
		String HQL = "update Userbaseinfo as user set userSex="+userInfo.getUserSex()+",userProf='"+userInfo.getUserProf()+
				"',userAddress='"+userInfo.getUserAddress()+"',userIntroduct='"+userInfo.getUserIntroduct()+
				"',headImage='"+userInfo.getHeadimage()+"' where userId="+id+"";
		Session session=null;
		int update=-1;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			update=session.createQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
			
		return update == 0 ? false:true;
	}
	@Override
	public boolean updateUserMsgMail(Userbaseinfo userInfo,int id) {
		String HQL = "update Userbaseinfo as user set userEmail='"+userInfo.getUserEmail()+"' where userId="+id+"";
		Session session=null;
		int update=-1;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			update=session.createQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			HibernateTool.closeSession(session);
		}
			
		return update == 0 ? false:true;
	}
	
	@Override
	public boolean updateUserMsgTel(Userbaseinfo userInfo,int id) {
		String HQL = "update Userbaseinfo as user set phoneNumber='"+userInfo.getPhoneNumber()+"' where userId="+id+"";
		Session session=null;
		int update=-1;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			update=session.createQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			System.out.println(e);
		}finally{
			HibernateTool.closeSession(session);
		}
		return update == 0 ? false:true;
	}
	
	public static void main(String[] args){
		new UserbaseinfoDAO().updateLoginTime(2);
	}
}

