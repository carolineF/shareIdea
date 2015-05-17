package com.shareIdea.vo;

import java.io.File;

import javax.servlet.ServletContext;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015年4月8日 下午6:01:51 
 * @version 1.0   
 */
public class IdeaUploadModel {
	private String uploadMethod;
	private String firstCategory;
	private String secondCategory;
	private String thirdCategory;
	private String forthCategory;
	private String ideaTitle;
	private String ideaKeyWord;
	private String ideaAuthority;
	private String friendList;
	private Integer codeScore;
	private String ideaContent;
	private String fileSize;
	private Integer ideaId;
	
	private File fileField; //上传的文件     
	private String fileFieldContentType; //文件的内容类型,uploadfile为上面定义上传的文件，ContentType是固定写法，不能改变     
	private String fileFieldFileName; //上传文件名,uploadfile为上面定义上传的文件，FileName是固定写法，不能改变     
	
	private String note;// 上传文件时的备注     
	private ServletContext context; //只用来取服务器路径
	
	public String getUploadMethod() {
		return uploadMethod;
	}
	public void setUploadMethod(String uploadMethod) {
		this.uploadMethod = uploadMethod;
	}
	public String getFirstCategory() {
		return firstCategory;
	}
	public void setFirstCategory(String firstCategory) {
		this.firstCategory = firstCategory;
	}
	public String getSecondCategory() {
		return secondCategory;
	}
	public void setSecondCategory(String secondCategory) {
		this.secondCategory = secondCategory;
	}
	public String getThirdCategory() {
		return thirdCategory;
	}
	public void setThirdCategory(String thirdCategory) {
		this.thirdCategory = thirdCategory;
	}
	public String getForthCategory() {
		return forthCategory;
	}
	public void setForthCategory(String forthCategory) {
		this.forthCategory = forthCategory;
	}
	public String getIdeaTitle() {
		return ideaTitle;
	}
	public void setIdeaTitle(String ideaTitle) {
		this.ideaTitle = ideaTitle;
	}
	public String getIdeaKeyWord() {
		return ideaKeyWord;
	}
	public void setIdeaKeyWord(String ideaKeyWord) {
		this.ideaKeyWord = ideaKeyWord;
	}
	public String getIdeaAuthority() {
		return ideaAuthority;
	}
	public void setIdeaAuthority(String ideaAuthority) {
		this.ideaAuthority = ideaAuthority;
	}
	public String getFriendList() {
		return friendList;
	}
	public void setFriendList(String friendList) {
		this.friendList = friendList;
	}
	public Integer getCodeScore() {
		return codeScore;
	}
	public void setCodeScore(Integer codeScore) {
		this.codeScore = codeScore;
	}
	public Integer getIdeaId() {
		return ideaId;
	}
	public void setIdeaId(Integer ideaId) {
		this.ideaId = ideaId;
	}
	public String getIdeaContent() {
		return ideaContent;
	}
	public void setIdeaContent(String ideaContent) {
		this.ideaContent = ideaContent;
	}
	public File getFileField() {
		return fileField;
	}
	public void setFileField(File fileField) {
		this.fileField = fileField;
	}
	public String getFileFieldContentType() {
		return fileFieldContentType;
	}
	public void setFileFieldContentType(String fileFieldContentType) {
		this.fileFieldContentType = fileFieldContentType;
	}
	public String getFileFieldFileName() {
		return fileFieldFileName;
	}
	public void setFileFieldFileName(String fileFieldFileName) {
		this.fileFieldFileName = fileFieldFileName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
}

