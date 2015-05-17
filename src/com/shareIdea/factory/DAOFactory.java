package com.shareIdea.factory;

import com.shareIdea.dao.impl.CodeInfoDAO;
import com.shareIdea.dao.impl.FriendInfoDAO;
import com.shareIdea.dao.impl.IdeaAuthorityDAO;
import com.shareIdea.dao.impl.IdeaInfoDAO;
import com.shareIdea.dao.impl.IdeaKeyWordDAO;
import com.shareIdea.dao.impl.UserbaseinfoDAO;
import com.shareIdea.dao.interfaces.ICodeInfoDAO;
import com.shareIdea.dao.interfaces.IFriendInfoDAO;
import com.shareIdea.dao.interfaces.IIdeaAuthorityDAO;
import com.shareIdea.dao.interfaces.IIdeaInfoDAO;
import com.shareIdea.dao.interfaces.IIdeaKeyWordDAO;
import com.shareIdea.dao.interfaces.IUserbaseinfoDAO;
import com.shareIdea.po.Ideaauthority;
/** 
 * 	DAO工程类，此类中可以获得所有所需的DAO操作类。通过此类获得的对象，再调用对象的方法来操作数据库。
 *	@author  huazi
 *  E-mail: huaziHear@gmail.com
 *  @date  2015年3月25日 上午11:16:10 
 *  @version 1.0   
 */
public class DAOFactory {
	/**
	 * 返回接口的对象的一个实例Userbaseinfo
	 * @return IUserbaseinfoDAO
	 */
	public static IUserbaseinfoDAO getIUserbaseinfoDAOInstance(){ 
		return new UserbaseinfoDAO();
	}
	public static IFriendInfoDAO getIFriendInfoDAOInstance(){ 
		return new FriendInfoDAO();
	}
	public static IIdeaInfoDAO getIIdeaInfoDAOInstance(){ 
		return new IdeaInfoDAO();
	}
	public static ICodeInfoDAO getICodeInfoDAOInstance(){
		return new CodeInfoDAO();
	}
	public static IIdeaAuthorityDAO getIIdeaAuthorityDAOInstance(){
		return new IdeaAuthorityDAO();
	}
	public static IIdeaKeyWordDAO getIIdeaKeyWordDAOInstance(){
		return new IdeaKeyWordDAO();
	}
}

