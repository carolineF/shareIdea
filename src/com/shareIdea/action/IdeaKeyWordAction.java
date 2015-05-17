package com.shareIdea.action;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Ideakeyword;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年4月10日 下午1:11:15 
 * @version 1.0   
 */
public class IdeaKeyWordAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -213601193378155632L;
	
	public static boolean insertMoreNum(ArrayList<Ideakeyword> list){
		for(int i=0; i<list.size(); i++){
			DAOFactory.getIIdeaKeyWordDAOInstance().insertKeyword(list.get(i));
		}
		return false;
	}

}

