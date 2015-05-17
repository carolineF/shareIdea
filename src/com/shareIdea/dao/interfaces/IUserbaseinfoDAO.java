package com.shareIdea.dao.interfaces;

import com.shareIdea.po.Userbaseinfo;

	/** 
	 * @author  huazi
	 * E-mail: huaziHear@gmail.com
	 * @date  2015年3月24日 下午1:29:04 
	 * @version 1.0   
	 */
public interface IUserbaseinfoDAO {
	/**
	 * 增加一个用户对象，user.userId不用给出。
	 * 用于用户注册，添加手机号、邮箱、昵称等内容。
	 * @param user
	 * @return true means insert success 
	 */
	public boolean insertUser(Userbaseinfo user);
	/**
	 * 查看userbaseinfo中是否存在此用户
	 * @param nickName
	 * @return true means exist
	 */
	public boolean nickNameExist(String nickName);
	/**
	 * 通过id 查找到此用户上次登录的时间
	 * @param id
	 * @return
	 */
	public String getLastLoginTime(int id);
	/**
	 * 通过id查找到此用户的所有个人基本信息
	 * @param id
	 * @return
	 */
	public Userbaseinfo getUserAllMsgById(int id);
	/**
	 * @param account
	 * @return
	 */
	String getPasswordByAccount(String account);
	/**
	 * @param userId
	 * @param newPassword
	 * @return
	 */
	boolean updatePassword(int userId, String newPassword);
	
	/**
	 * @param userInfo
	 * @param id
	 * @return
	 */
	boolean updateUserMsg(Userbaseinfo userInfo, int id);
	/**
	 * @param emailNo
	 * @return
	 */
	boolean emailNoExist(String emailNo);
	/**
	 * @param phoneNo
	 * @return
	 */
	boolean phoneNoExist(String phoneNo);
	/**
	 * @param account
	 * @param newPassword
	 */
	void setPasswordByAccount(String account, String newPassword);
	/**
	 * @param userId
	 * @param survey
	 * @return
	 */
	boolean insertSurvey(int userId, String survey);
	/**
	 * @param userId
	 * @return
	 */
	boolean isTested(int userId);
	/**
	 * @param colum
	 * @param userId
	 * @param flag
	 * @param range
	 * @return
	 */
	boolean updateNo(String colum, int userId, boolean flag, int range);
	/**
	 * @param userInfo
	 * @param id
	 * @return
	 */
	boolean updateUserMsgMail(Userbaseinfo userInfo, int id);
	
	public boolean updateUserMsgTel(Userbaseinfo userInfo,int id);
	/**
	 * @param headImage
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean updateHeadImage(String headImage, int userId) throws Exception;
	/**
	 * @param userId
	 */
	void updateLoginTime(int userId);
}

