package com.shareIdea.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.shareIdea.dao.interfaces.ICodeInfoDAO;
import com.shareIdea.po.Codecomment;
import com.shareIdea.po.Codeinfo;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.util.HibernateTool;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月11日 下午2:06:38 
 * @version 1.0   
 */
public class CodeInfoDAO implements ICodeInfoDAO {
	
	/* 
	 * 通过UserId 得到该用户的code信息
	 * getCodeInfoMsg
	 */
	@Override
	public boolean insertCodeInfo(Codeinfo codeinfo) {
		Session session =null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(codeinfo);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
		return true;
	}
	@Override
	public List<Codeinfo> getCodeInfoMsg(int userId) {
		String HQL = "select new Codeinfo(code.codeId,code.codeName,code.codeSize,code.codeCommentNo,code.codeDownNo,code.codeClickNo) from "
				+ "Codeinfo as code where code.userbaseinfo.userId="+userId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Codeinfo> code = q.list();
			for(Codeinfo c:code){
				System.out.println(c.getCodeId());
			}
			return code;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return null;
	}
	@Override
	public String deleteCodeMsg(String codeIds){
		String HQL="delete from Codeinfo as code where ";
		String HQL1 = "delete from Codecomment as codec where ";
		String[] codeid = codeIds.split(",");
		for(String s:codeid){
			HQL+=" code.codeId="+s+" or ";
			HQL1+=" codec.codeinfo.codeId="+s+" or ";
		}
		
		HQL=HQL.substring(0,HQL.length()-3);
		HQL1=HQL1.substring(0,HQL1.length()-3);
		
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createQuery(HQL1).executeUpdate();
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
	public Codeinfo getViewCodeMsgByCodeId(int codeId){
	
		String HQL = "from Codeinfo as code where code.codeId = "+codeId;
		System.out.println("111111111111"+HQL);
		Session session = null;
		Codeinfo code=null;
	    try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			code = (Codeinfo) q.list().get(0);
	    }catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return code;
	}
	@Override
	public String getCodeCommentMsg(int codeId){
		System.out.println("ok3");
		String HQL = "from Codecomment as codec where codec.codeinfo.codeId = "+codeId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			@SuppressWarnings("unchecked")
			List<Codecomment> l = q.list();
			
			addClickCodeNo(codeId);
			return dealCodeComment(l);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateTool.closeSession(session);
		}
		return null;
	}
	/**
	 * @param codeId
	 */
	private void addClickCodeNo(int codeId) {
		System.out.println("ok999");
		String HQL = "update codeinfo set codeClickNo = codeClickNo+1 where codeid="+codeId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
	}
		/**
	 * @param l
	 * @return
	 */
	private String dealCodeComment(List<Codecomment> l) {
		if(l.size()<1){
			return "{}";
		}
		double pageCount=Math.ceil(l.size()/Float.parseFloat("6"));
		int i=1;
		String result = "{\"maxBlockCount\":\""+l.size()+"\",\"pageCount\":\""
				+pageCount+"\",\"commentContent\":[";
		System.out.println(result);
		for(Codecomment codec : l){
			result+="{\"headUrl\":\""+codec.getUserbaseinfo().getHeadimage()+"\","
					+ "\"name\":\""+codec.getUserbaseinfo().getNickName()+"\","
					+ "\"content\":\""+codec.getCodeCommentContent()+"\","
					+ "\"num\":\""+i+++"\",\"time\":\""+codec.getCodeCommentTime()+"\"},";
		}
		result=result.substring(0,result.length()-1)+"]}";
		System.out.println("查看代码 ----------"+result);
		return result;
	}
	@Override
	public boolean addCodeComment(String codeCommentContent,int codeId,int userId){
		Codecomment codec = new Codecomment();
		codec.setCodeCommentContent(codeCommentContent);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String date = sdf.format(new Date());
		codec.setCodeCommentTime(date);
		
		codec.setCodeinfo(new Codeinfo(codeId));
		codec.setUserbaseinfo(new Userbaseinfo(userId));
		
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.save(codec);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
		midifyCodeCommentNo(codeId,"+1");
		return true;
	}
	/**
	 * @param codeId
	 * @param string
	 */
	private void midifyCodeCommentNo(int codeId, String string) {
		String HQL = "update codeinfo set codeCommentNo = codeClickNo"+string+ "where codeid="+codeId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(HQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
	}
	@Override
	public String getCodeFilePath(int codeId){
		String HQL = "select code.codePath from Codeinfo as code where code.codeId="+codeId;
		Session session = null;
		String path="";
		try{
			session=HibernateTool.getSession();
			session.getTransaction().begin();
			Query q = session.createQuery(HQL);
			path = (String)q.list().get(0);
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			HibernateTool.closeSession(session);
		}
		return path;
	}

	@Override
	public boolean addDownNo(int codeId){
		String SQL = "update codeinfo set codeDownno=codedownno+1 where codeid="+codeId;
		Session session = null;
		try{
			session = HibernateTool.getSession();
			session.getTransaction().begin();
			session.createSQLQuery(SQL).executeUpdate();
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}finally{
			HibernateTool.closeSession(session);
		};
			return true;
	}
	
	public static void main(String[] args){
		new CodeInfoDAO().deleteCodeMsg("2");
	}
}

