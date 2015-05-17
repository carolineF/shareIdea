package com.shareIdea.dao.interfaces;

import com.shareIdea.po.Ideainfo;
import com.shareIdea.po.Userbaseinfo;

/** 
 *	@author  huazi
 * E-mail: huaziHear@gmail.com
 * @date  2015��4��19�� ����2:33:20 
 * @version 1.0   
 */
public interface IIdeaAuthorityDAO {

	/**
	 * @param ideainfo
	 * @param userBaseInfo
	 * @return
	 */
	boolean insertIdeaAuthority(Ideainfo ideainfo, Userbaseinfo userBaseInfo);

}

