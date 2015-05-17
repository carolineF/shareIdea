package com.shareIdea.dao.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.shareIdea.po.Userbaseinfo;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年3月31日 上午10:22:57 
 * @version 1.0   
 */
public interface IFriendInfoDAO {
	/**
	 * 判断此nickName用户是否为userid的好友
	 * @param userId
	 * @param nickName
	 * @return
	 */
	boolean isFriend(int userId,String nickName);

	/**
	 * 1，给id的“全部”分类下添加nickname用户
	 * 2，给nickname用户添加id用户
	 * 3，若groupName不是“全部” 添加nickName到groupName下
	 * 
	 * nickName是不能当做数据添加的，所以必须找到friendUserId
	 * 
	 * @param userId   要给此id添加好友
	 * @param nickName  好友的nickname
	 * @param gourpName  要添加到的groupName
	 * @return  boolean
	 */
	boolean addFriend(int userId, String nickName, String gourpName);

	/**
	 * 1,删除Friendinfo中的数据
	 * 2,删除Friendgouping的数据
	 * @param groupName
	 * @return
	 */
	boolean deleteGroup(int userId, String groupName);

	/**
	 * 1,Friendgouping中添加该用户的一个新的分组信息
	 * @param userId
	 * @param groupName
	 * @return
	 */
	boolean createGroup(int userId, String groupName);

	/**
	 * 和添加好友一样，删除也是相互的！
	 * 1，删除当前用户friendinfo下的目标friend好友关系信息。
	 * 2，删除目标用户下当前用户的好友关系信息
	 * 在删除的过程中除了删除特定分类下的信息，还要删除‘全部’分类的信息
	 * @param userId
	 * @param friendName
	 * @return
	 */
	boolean deleteFriend(int userId, String friendName);

	/**
	 * @param userId
	 * @return
	 */
	LinkedHashMap<String, ArrayList<Userbaseinfo>> getFriendGroupingMsg(
			int userId);
}

