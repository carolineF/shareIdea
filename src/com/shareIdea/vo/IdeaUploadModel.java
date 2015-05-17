package com.shareIdea.vo;

import java.io.File;

import javax.servlet.ServletContext;

/** 
 *	@author  feng
 * E-mail: 13720455622@163.com
 * @date  2015��4��8�� ����6:01:51 
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
	
	private File fileField; //�ϴ����ļ�     
	private String fileFieldContentType; //�ļ�����������,uploadfileΪ���涨���ϴ����ļ���ContentType�ǹ̶�д�������ܸı�     
	private String fileFieldFileName; //�ϴ��ļ���,uploadfileΪ���涨���ϴ����ļ���FileName�ǹ̶�д�������ܸı�     
	
	private String note;// �ϴ��ļ�ʱ�ı�ע     
	private ServletContext context; //ֻ����ȡ������·��
	
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

