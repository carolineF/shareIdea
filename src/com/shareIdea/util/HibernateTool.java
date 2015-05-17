package com.shareIdea.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateTool {
	 private static SessionFactory factory;  
	   static{  //待记录
	           try{  
	        	   Configuration configuration = new Configuration();
	        	      configuration.configure("hibernate.cfg.xml");
	        	      ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
	        	    factory = configuration
	        	                .buildSessionFactory(serviceRegistry);
	           }catch(Exception e){  
	        	   System.out.println(e);
	        	   throw e;
	           }  
	   }    
	   /**
	    * 得到一个session对象 
	    * @return
	    */
	   public static Session getSession(){  
	       return factory.openSession();
	   }  
	     
	   /**
	    * 释放此session对象，是释放不是关闭哦
	    * @param session
	    */
	   public static void closeSession(Session session){  
	       if(session!=null){  
	           if(session.isOpen()){  
	               session.close();  
	           }  
	       }  
	   }  
	     
	   public static SessionFactory getSessionFactory(){  
	       return factory;  
	   }  
}
