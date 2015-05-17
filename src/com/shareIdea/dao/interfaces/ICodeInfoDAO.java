package com.shareIdea.dao.interfaces;

import java.util.List;

import com.shareIdea.po.Codeinfo;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月11日 下午2:06:03 
 * @version 1.0   
 */
public interface ICodeInfoDAO {
	public List<Codeinfo> getCodeInfoMsg(int userId);

	/**
	 * @param codeIds
	 * @return
	 */
	String deleteCodeMsg(String codeIds);

	/**
	 * @param ideaId
	 * @param codeName
	 * @return
	 */
	Codeinfo getViewCodeMsgByCodeId(int codeId);

	/**
	 * @param codeId
	 * @return
	 */
	String getCodeCommentMsg(int codeId);

	/**
	 * @param codeCommentContent
	 * @param codeId
	 * @param userId
	 * @return
	 */
	boolean addCodeComment(String codeCommentContent, int codeId, int userId);

	/**
	 * @param codeId
	 * @return
	 */
	String getCodeFilePath(int codeId);

	/**
	 * @param codeinfo
	 * @return
	 */
	boolean insertCodeInfo(Codeinfo codeinfo);

	/**
	 * @param codeId
	 * @return
	 */
	boolean addDownNo(int codeId);
	
}

