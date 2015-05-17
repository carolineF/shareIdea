package com.shareIdea.action;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.mapping.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.shareIdea.dao.interfaces.IIdeaInfoDAO;
import com.shareIdea.factory.DAOFactory;
import com.shareIdea.po.Codeinfo;
import com.shareIdea.po.Ideaauthority;
import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Ideakeyword;
import com.shareIdea.po.Userbaseinfo;
import com.shareIdea.vo.IdeaUploadModel;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年4月7日 下午7:14:04 
 * @version 1.0   
 */
public class UploadIdeaAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3023806284100571165L;
	
	private Ideainfo ideaBaseInfo = new Ideainfo();
	private Codeinfo codeBaseInfo = new Codeinfo();
	private ArrayList<Ideakeyword> keyWordList = new ArrayList<Ideakeyword>();
	private ArrayList<Ideaauthority> authorityList = new ArrayList<Ideaauthority>();
	private IdeaUploadModel ideaUploadMode = new IdeaUploadModel();
	private HashMap<String,String> categoryToNo = new HashMap<String, String>(); 
	private  HttpServletRequest request = ServletActionContext.getRequest();
	private int userId;
	private int getUserId(){
		return Integer.parseInt((String)request.getSession().getAttribute("userId"));
	}
	
	public UploadIdeaAction(){
		categoryToNo.put("现货", "A");
		categoryToNo.put("期货", "B");
		categoryToNo.put("期权", "C");
		categoryToNo.put("外汇", "D");
		categoryToNo.put("组合", "E");
		categoryToNo.put("通用", "F");
		categoryToNo.put("高频", "G");
		categoryToNo.put("保守型", "H");
		categoryToNo.put("稳健型", "I");
		categoryToNo.put("激励型", "J");
		categoryToNo.put("权变型", "K");
	}
	
	public IdeaUploadModel getIdeaUploadMode() {
		return ideaUploadMode;
	}

	public void setIdeaUploadMode(IdeaUploadModel ideaUploadMode) {
		this.ideaUploadMode = ideaUploadMode;
	}

	public ArrayList<Ideaauthority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(ArrayList<Ideaauthority> authorityList) {
		this.authorityList = authorityList;
	}

	public ArrayList<Ideakeyword> getKeyWordList() {
		return keyWordList;
	}

	public void setKeyWordList(ArrayList<Ideakeyword> keyWordList) {
		this.keyWordList = keyWordList;
	}

	public Codeinfo getCodeBaseInfo() {
		return codeBaseInfo;
	}

	public void setCodeBaseInfo(Codeinfo codeBaseInfo) {
		this.codeBaseInfo = codeBaseInfo;
	}

	public Ideainfo getIdeaBaseInfo() {
		return ideaBaseInfo;
	}

	public void setIdeaBaseInfo(Ideainfo ideaBaseInfo) {
		this.ideaBaseInfo = ideaBaseInfo;
	}

	public String insertIdeaInfo() throws Exception{	
		HttpSession session = ServletActionContext.getRequest().getSession();
		String category = ideaUploadMode.getFirstCategory()+","+ ideaUploadMode.getSecondCategory()+","+
				ideaUploadMode.getThirdCategory() +","+ ideaUploadMode.getForthCategory();
		String keyWord = "undefined".equals(ideaUploadMode.getIdeaKeyWord())?"null":ideaUploadMode.getIdeaKeyWord();
		
		String categoryNo = categoryToNo.get(ideaUploadMode.getFirstCategory())+categoryToNo.get(ideaUploadMode.getSecondCategory())+
				categoryToNo.get(ideaUploadMode.getThirdCategory());
		String nickName = session.getAttribute("nickName").toString();
		if("上传策略".equals(ideaUploadMode.getUploadMethod())){
			System.out.println("策略分类"+category);
			ideaBaseInfo.setIdeaCategory(category);
			codeBaseInfo.setCodeCategory(category);
			ideaBaseInfo.setIdeaTitle(ideaUploadMode.getIdeaTitle());
			System.out.println(ideaUploadMode.getIdeaTitle());
			String[] ideaKeyWordList = keyWord.split(",|，");
			for(int i=0; i< ideaKeyWordList.length;i++){
				Ideakeyword word = new Ideakeyword();
				word.setKeyWord(ideaKeyWordList[i]);
				word.setIdeainfo(ideaBaseInfo);
				keyWordList.add(word);	
			}
			
			ideaBaseInfo.setIdeaComtent(ideaUploadMode.getIdeaContent());
			
			Userbaseinfo userBaseInfo = new Userbaseinfo();
			userBaseInfo.setUserId(getUserId());
			ideaBaseInfo.setUserbaseinfo(userBaseInfo);
			SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH-mm");
			String date ="";
			date = sdf.format(new Date());
			ideaBaseInfo.setUpdateTime(date);
			ideaBaseInfo.setIdeaClickNo(0);
			ideaBaseInfo.setIdeaCommentNo(0);
			ideaBaseInfo.setNickName(nickName);
			//保存策略的基本信息到数据库                                           
			IIdeaInfoDAO ideainfoDao =  DAOFactory.getIIdeaInfoDAOInstance();
			int ideaId =ideainfoDao.insertIdeainfo(ideaBaseInfo);
			ideaBaseInfo.setIdeaId(ideaId);
			//更新Ideainfo中的策略编号字段
			int length = 7-(ideaId+"").length();
			String  temp = Math.pow(10,length)+"";
			String ideaNum = categoryNo + ((length>0) ?(temp.substring(1,temp.lastIndexOf("."))+ideaId) : ideaId);
			ideaBaseInfo.setIdeaNum(ideaNum);
			System.out.println("策略编号"+ideaNum);
			ideainfoDao.updateWithIdeaNum(ideaBaseInfo);
			//修改userbaseinfo中的基本信息
			DAOFactory.getIUserbaseinfoDAOInstance().updateNo("userIdeaNo", getUserId(), true,1);
			//保存关键字
			IdeaKeyWordAction.insertMoreNum(keyWordList);
			// 处理策略权限
			String authority = ideaUploadMode.getIdeaAuthority();
			if("所有人".equals(authority)){
				System.out.println("所有人");
			}else{
				//修改IdeaInfo中的publicAuthority属性为0（私有）
				ideainfoDao.updateWithIdeaAuthority(ideaBaseInfo);
				if("所有好友".equals(authority)||"部分好友".equals(authority)){
					System.out.println(ideaUploadMode.getFriendList()+"---163");
				String[] friendList = ideaUploadMode.getFriendList().split(" ");
				System.out.println(ideaUploadMode.getFriendList());
				IdeaAuthorityAction.addAuthority(ideaBaseInfo, friendList);
				}
			}
			//处理代码资源分
			if(ideaUploadMode.getCodeScore() != null){
				uploadCodeFile(session,getUserId());
			}
		}else{
			//根据策略编号做处理
			//做上传代码的操作
			ideaBaseInfo.setIdeaId(ideaUploadMode.getIdeaId());
			uploadCodeFile(session,getUserId());
		}
		//上传成功后跳转到什么位置比较合适
		return "toPersonSpace";
	}
	/**
	 * 将文件保存到本地并返回保存的路径
	 * @param session
	 * @param maxNum
	 * @return filePath
	 * @throws IOException
	 */
	private String saveCodeFile(HttpSession session,int maxNum) throws IOException{
		String codePath = "";
		int idNo = getUserId()/maxNum;
		if(getUserId()%maxNum != 0){
			idNo += 1;
		}
		String idRange = (maxNum*(idNo-1)+1)+"-"+(maxNum*idNo);
		System.out.println(idRange);
		codePath = "code\\"+idRange+"\\"+getUserId();
		String filePath = ServletActionContext.getServletContext().getRealPath(codePath);
		System.out.println(filePath);
		File path = new File(filePath);
		if(!path.exists()){
			path.mkdirs();
		}
		File codeFile = new File(filePath,ideaUploadMode.getFileFieldFileName());
		if(codeFile.exists()){
			codeFile.delete();
		}
		FileUtils.copyFile(ideaUploadMode.getFileField(), codeFile);
		System.out.println(codePath+"\\"+ideaUploadMode.getFileFieldFileName());
		return codePath+"\\"+ideaUploadMode.getFileFieldFileName();
	}
	/**
	 * 上传代码文件并保存到数据库
	 * @param session
	 * @throws IOException
	 */
	private void uploadCodeFile(HttpSession session,int userId) throws IOException{
		System.out.println("资源分"+ideaUploadMode.getCodeScore());
		codeBaseInfo.setCodeScore(ideaUploadMode.getCodeScore());
		codeBaseInfo.setCodePath(saveCodeFile(session,100));
		codeBaseInfo.setCodeName(ideaUploadMode.getFileFieldFileName());
		codeBaseInfo.setCodeCommentNo(0);
		codeBaseInfo.setCodeDownNo(0);
		codeBaseInfo.setCodeClickNo(0);
		SimpleDateFormat sdf =  new SimpleDateFormat("yyyy-MM-dd HH-mm");
		String date ="";
		date = sdf.format(new Date());
		codeBaseInfo.setCodeUpdateTime(date);
		codeBaseInfo.setNickName(session.getAttribute("nickName").toString());
		codeBaseInfo.setCodeSize(ideaUploadMode.getFileSize());
		System.out.println("文件大小"+ideaUploadMode.getFileSize());
		Userbaseinfo userBaseInfo = new Userbaseinfo();
		userBaseInfo.setUserId(getUserId());
		codeBaseInfo.setUserbaseinfo(userBaseInfo);
		codeBaseInfo.setIdeainfo(ideaBaseInfo);
		DAOFactory.getICodeInfoDAOInstance().insertCodeInfo(codeBaseInfo);
	}
}

