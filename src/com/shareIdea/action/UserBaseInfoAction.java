package com.shareIdea.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.dao.impl.UserbaseinfoDAO;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.vo.UserLoginModel;
import com.shareIdea.vo.UserRegisterModel;
import com.shareIdea.vo.forgetPassword;

public class UserBaseInfoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String myoldPassword;
	private String mynewPassword;
	private Userbaseinfo userInfo = new Userbaseinfo();
	private UserRegisterModel urm = new UserRegisterModel();
	private String resSurvey;

	private  HttpServletResponse response = ServletActionContext.getResponse();
	private  HttpServletRequest request = ServletActionContext.getRequest();

	public Userbaseinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Userbaseinfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getResSurvey() {
		return resSurvey;
	}

	public void setResSurvey(String resSurvey) {
		this.resSurvey = resSurvey;
	}

	private forgetPassword forgetModel = new forgetPassword();
	

	
	public forgetPassword getForgetModel() {
		return forgetModel;
	}

	public void setForgetModel(forgetPassword forgetModel) {
		this.forgetModel = forgetModel;
	}
	
	public String getMyoldPassword() {
		return myoldPassword;
	}

	public void setMyoldPassword(String myoldPassword) {
		this.myoldPassword = myoldPassword;
	}

	public String getMynewPassword() {
		return mynewPassword;
	}

	public void setMynewPassword(String mynewPassword) {
		this.mynewPassword = mynewPassword;
	}
	private UserLoginModel userLoginModel = new UserLoginModel();
	
	public UserLoginModel getUserLoginModel() {
		return userLoginModel;
	}

	public void setUserLoginModel(UserLoginModel userLoginModel) {
		this.userLoginModel = userLoginModel;
	}
	public UserRegisterModel getUrm() {
		return urm;
	}

	public void setUrm(UserRegisterModel urm) {
		this.urm = urm;
	}

	
	public Userbaseinfo getUser() {
		return user;
	}

	public void setUser(Userbaseinfo user) {
		this.user = user;
	}

	private Userbaseinfo user = new Userbaseinfo();
	
	
	private boolean findOnlineUser(String userid) {
		  HttpSession session = request.getSession();
		  ServletContext application = (ServletContext) session.getServletContext();
		  ArrayList users = (ArrayList) application.getAttribute("users");
		  HashMap ipUser = (HashMap) application.getAttribute("ipusers");
		  if (null != users && users.contains(userid)) {
		   if (ipUser != null && ((String) ipUser.get(userid))
		       .equals((String) request.getRemoteAddr())) {
		    session.setAttribute("username", userid);
		    return true;
		   } else {
		    return false;
		   }
		  } else {
		   session.setAttribute("username", userid);
		   if (users == null) {
		    users = new ArrayList();
		   }
		   users.add(userid);
		   if (ipUser == null) {
		    ipUser = new HashMap();
		   }
		   ipUser.put(userid, request.getRemoteAddr());
		   application.setAttribute("ipusers", ipUser);
		   application.setAttribute("users", users);
		   return true;
		  }
		 }

	public String modifyPassWord() throws Exception{ 
		UserbaseinfoDAO userDao = (UserbaseinfoDAO) DAOFactory.getIUserbaseinfoDAOInstance();	
		String useroldPassword =  request.getParameter("myoldPassword");
		String usernewPassword =  request.getParameter("mynewPassword");
		String userId = (String) ServletActionContext.getRequest().getSession().getAttribute("userId");
		String DaoUserPass = userDao.getUserAllMsgById(Integer.parseInt(userId)).getUserPassword();
		boolean Istrue=true;
		boolean Isrepeat=true;
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] md = md5.digest(useroldPassword.getBytes());
		useroldPassword = new String(md);
		byte[] md1 = md5.digest(usernewPassword.getBytes());
		usernewPassword = new String(md1);
		
       //如果旧密码与数据库密码不匹配,提示用户错误消息
        if(!DaoUserPass.equals(useroldPassword)){   	
        	Istrue = false;
        }else{
        	//比较新密码与原始密码是否一致，如果一致，提示用户新密码与旧密码不能一致  
        	if(!DaoUserPass.equals(usernewPassword) && usernewPassword != ""){ //如果不一致，修改数据库中的密码     
        		Isrepeat = false;  	//没有重复
                System.out.println("用户id="+userId+"用户新密码="+usernewPassword);
                DAOFactory.getIUserbaseinfoDAOInstance().updatePassword(Integer.parseInt(userId), usernewPassword);        	
            }  
        }
        System.out.println(Istrue+"            "+Isrepeat);
        response.getWriter().print("[{\"isTrue\":"+Istrue+"},{\"isRepeat\":"+Isrepeat+"}]");     
        return null;
	}
	/**
	 * @return
	 */
	public String checkLogin(){
		String account = userLoginModel.getAccount();
		String oldPassword = userLoginModel.getPassword();
		String DBPassword;
		String DBId;
		String nickName;
		String DBModel = DAOFactory.getIUserbaseinfoDAOInstance().getPasswordByAccount(account);
		
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(oldPassword.getBytes());
			oldPassword = new String(md);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		if(DBModel == null){
			request.setAttribute("errorContent", "账号不存在！");
			return "toLogin";
		}
		else{
			DBPassword = (DBModel.split(","))[0];
			DBId = (DBModel.split(","))[1];
			
			nickName=(DBModel.split(","))[2];
			if(!findOnlineUser(DBId)){
				request.setAttribute("errorContent", "此账号已在其他地方登陆，请先退出再重新登陆！");
				return "toLogin";
			}
			if(DBPassword.equals(oldPassword)){ 
				
				ServletActionContext.getRequest().getSession().setAttribute("userId", DBId);
				ServletActionContext.getRequest().getSession().setAttribute("nickName", nickName);
				if(!DAOFactory.getIUserbaseinfoDAOInstance().isTested(Integer.parseInt(DBId))){
					return "toSurvey";
				}
				DAOFactory.getIUserbaseinfoDAOInstance().updateLoginTime(Integer.parseInt(DBId));
				return "toPersonSpace";
			}
			request.setAttribute("errorContent", "密码错误！");
			return "toLogin";
		}
	}
	
	public String image() throws IOException{
		BufferedImage buffImage = new BufferedImage(100,42,BufferedImage.TYPE_INT_RGB);
		Graphics gra = buffImage.getGraphics();
		Color color = new Color(200,255,200);
		gra.setColor(color);
		gra.fillRect(0, 0, 100, 42);
		
		char[] ch = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		Random random = new Random();
		int len = ch.length;
		int index;
		StringBuffer strB = new StringBuffer();
		for(int i = 0; i < 4; i++){
			index = random.nextInt(len);
			gra.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
			gra.setFont(new Font("宋体", Font.BOLD, 30));
			gra.drawString(ch[index] + "", (i * 20 + 5), 25);
			strB.append(ch[index]);
		}
		ImageIO.write(buffImage, "JPG", new File(ServletActionContext.getServletContext().getRealPath("image") + "/verify.jpg"));
		
		ServletActionContext.getRequest().getSession().setAttribute("verify", strB.toString());
		System.out.println(strB.toString());
		return "image";
	}
	
	public String forgetFirst(){
		String account = forgetModel.getAccount();
		String verify = forgetModel.getVerify();
		String DBModel = DAOFactory.getIUserbaseinfoDAOInstance().getPasswordByAccount(account);
		String sessionVerify = (String)request.getSession().getAttribute("verify");
		
		if(DBModel == null){
			request.setAttribute("reg", "账号不存在！");
			return "toforgetPassword1";
		}
		else{
			if((sessionVerify.toLowerCase()).equals((verify.toLowerCase()))){
				request.getSession().setAttribute("account", account);
				return "toforgetPassword2";
			}
			request.setAttribute("reg", "验证码错误！");
			return "toforgetPassword1";
		}	
	}
	
	public String forgetsecond(){
		String secondVerify = (String) request.getSession().getAttribute("forgetsecondVerify");
        String formVerify = request.getParameter("verifyName");
        System.out.println("forgetsecondÖÐ     " + formVerify);
        if(null!=formVerify && secondVerify.equals(formVerify)){
        	return "toforgetPassword3";
        }
        request.setAttribute("reg", "验证码错误！");
		return "toforgetPassword2";
	}
	
	public String forgetThird(){
		String newPassword = request.getParameter("newPassword");
		String account = (String) request.getSession().getAttribute("account");
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] md = md5.digest(newPassword.getBytes());
			newPassword = new String(md);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		DAOFactory.getIUserbaseinfoDAOInstance().setPasswordByAccount(account, newPassword);
		return "toLogin";
	}
	
	
	private Userbaseinfo voToPo(UserRegisterModel urm) throws NoSuchAlgorithmException{
		Userbaseinfo userInfo = new Userbaseinfo();
		if(-1 == urm.getAccount().indexOf("@")){
			userInfo.setPhoneNumber(urm.getAccount());
		}else{
			userInfo.setUserEmail(urm.getAccount().toLowerCase());
		}
		userInfo.setNickName(urm.getNikeName());
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		String password = urm.getUserPassword();
		byte[] md = md5.digest(password.getBytes());
		System.out.println("123456经过MD5加密后的字符串 =='"+new String(md)+"'");
		password = new String(md);
	 	userInfo.setUserPassword(password); 
	 	
	 	userInfo.setHeadimage("./headImage/default.jpg");

	 	return userInfo;
	}
	
	public String insertUser() throws Exception {
		userInfo = voToPo(urm);
		return DAOFactory.getIUserbaseinfoDAOInstance().insertUser(userInfo)?"toLogin":"toRegister";
	}
	public String checkUser() throws Exception {
		String existMsg = "{\"isExist\":";
		request.setCharacterEncoding("UTF-8");
		String userNickName= request.getParameter("nickName");
		if(!DAOFactory.getIUserbaseinfoDAOInstance().nickNameExist(userNickName)){
	        existMsg += false +"}";
		}else{
			existMsg += true +"}";
		}
		response.getWriter().print(existMsg);	
		return null;
	}
	/*
	 * 检查对应账号的用户是否存在
	 */
	public String checkAccount() throws Exception {
		String existMsg = "{\"isExist\":";
		String userAccountNo= request.getParameter("accountNo");
		//System.out.println("登陆：用户昵称="+user.getNickName()+"用户密码="+user.getUserPassword()+userD.nickNameExist(user.getNickName()));
		if(-1 == userAccountNo.indexOf("@")){
			//是手机
			if(!DAOFactory.getIUserbaseinfoDAOInstance().phoneNoExist(userAccountNo)){
		        existMsg += false +"}";
			}else{
				existMsg += true +"}";
			}
		}else{
			if(!DAOFactory.getIUserbaseinfoDAOInstance().emailNoExist(userAccountNo)){
				existMsg += false +"}";
			}else{
				existMsg += true +"}";
			}
		}
		response.getWriter().print(existMsg);
		return null;
	}
	/**
	 * 验证用户填写的验证码是否正确
	 * @return
	 * @throws Exception
	 */
	public String checkSecCode() throws Exception{
		String rightMsg = "{\"isRight\":";
		String secCode= request.getParameter("secCode");
		
		String sendSecCode = (String) request.getSession().getAttribute("secCode");
		if(secCode.equals(sendSecCode)){
			rightMsg += true + "}";
		}else{
			rightMsg += false + "}";
		}
		response.getWriter().print(rightMsg);
		return null;
	}


	public String submitSurvey() throws IOException{
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		DAOFactory.getIUserbaseinfoDAOInstance().insertSurvey(userId, this.resSurvey);
		DAOFactory.getIUserbaseinfoDAOInstance().updateLoginTime(userId);
		return null;
	}
	

	public String showInformation() throws IOException{
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
         Userbaseinfo userBase =  (Userbaseinfo) DAOFactory.getIUserbaseinfoDAOInstance().getUserAllMsgById(userId);
		 String info = ("{\"userName\":\""+userBase.getNickName()+"\",\"userSex\":"+ userBase.getUserSex()
				+",\"userEmail\":\""+userBase.getUserEmail()+"\",\"userImg\":\""+userBase.getHeadimage()+"\",\"userJob\":\""+userBase.getUserProf()+"\",\"userAddress\":\""+userBase.getUserAddress()+"\",\"userNumber\":\""+userBase.getPhoneNumber()
			     +"\",\"userSelfInformation\":\""+userBase.getUserIntroduct()+"\"}");
	     request.setAttribute("information",info);
		 return "toShow";
	}
	
	public String  updateMsg(){
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		UserbaseinfoDAO userDAO =(UserbaseinfoDAO) DAOFactory.getIUserbaseinfoDAOInstance();
	    userDAO.updateUserMsg(user,userId);
		try {
			showInformation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}
	public String updateMsgTel(){
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		UserbaseinfoDAO userDAO =(UserbaseinfoDAO) DAOFactory.getIUserbaseinfoDAOInstance();
	    userDAO.updateUserMsgTel(user,userId);
	    try {
			showInformation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}
	public String updateMsgMail(){
		int userId = Integer.parseInt((String)request.getSession().getAttribute("userId"));
		UserbaseinfoDAO userDAO =(UserbaseinfoDAO) DAOFactory.getIUserbaseinfoDAOInstance();
		userDAO.updateUserMsgMail(user,userId);
		try {
			showInformation();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "toUpdate";
	}
}
