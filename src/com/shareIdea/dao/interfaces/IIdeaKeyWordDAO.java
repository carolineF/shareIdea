package com.shareIdea.dao.interfaces;

import java.util.ArrayList;

import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Ideakeyword;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015年4月19日 下午2:31:45 
 * @version 1.0   
 */
public interface IIdeaKeyWordDAO {

	/**
	 * @param keyword
	 * @return
	 */
	boolean insertKeyword(Ideakeyword keyword);

	/**
	 * @param ideaId
	 * @return
	 */
	ArrayList<Ideakeyword> selectKeyWord(Ideainfo ideaId);

}

