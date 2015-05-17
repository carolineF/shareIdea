package com.shareIdea.test;

import java.util.Date;

import org.hibernate.Session;

import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年3月24日 下午1:42:29 
 * @version 1.0   
 */
public class TestUpdate {
	public static void main(String args[]){
		Session session = HibernateTool.getSession();
		session.beginTransaction();
		Userbaseinfo user = new Userbaseinfo();
		user.setUserId(1);
		user.setLastLoginTime(new Date().toString());
		session.saveOrUpdate(user);
		session.getTransaction().commit();
		HibernateTool.closeSession(session);
	}
}

