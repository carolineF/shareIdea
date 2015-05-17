package com.shareIdea.dao.interfaces;

import java.util.ArrayList;

import com.shareIdea.po.Ideainfo;


public interface IIdeaInfoDAO {
	public String getIdeaMsg(int userId,String sortName,int pageSize,int pageNum) throws Exception;

	/**
	 * 批量删除idea
	 * 
	 * @param userId
	 * @param ideaIds
	 * @return
	 */
	public boolean deleteIdeas(int userId, String ideaIds);

	/**
	 * @param sortName
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public String getStrategyIdeaMsg(String sortName, int pageSize, int pageNum)
			throws Exception;

	/**
	 * 通过userId得到此id对应的好友策略信息
	 * 
	 * @param userId
	 * @return
	 */
	public String getFriendIdeaMsg(int userId,int pageNum,int pageSize,String sortName);

	/**
	 * @param ideaCommentContent
	 * @param ideaId
	 * @param userId
	 * @return
	 */
	public boolean addIdeaComment(String ideaCommentContent, int ideaId, int userId);

	/**
	 * @param ideaId
	 * @return
	 */
	public String getIdeaCommentMsg(int ideaId);

	/**
	 * @param ideaId
	 * @return
	 */
	public String getIdeainfo(int ideaId);

	/**
	 * @param ideaid
	 * @return
	 */
	public String getIdeaCodeMsg(int ideaid);

	/**
	 * @param ideaId
	 * @param newIdea
	 * @return
	 */
	public String modifyIdea(int ideaId, String newIdea);

	/**
	 * @param ideainfo
	 * @return
	 */
	boolean updateWithIdeaAuthority(Ideainfo ideainfo);

	/**
	 * @param ideainfo
	 * @return
	 */
	public boolean updateWithIdeaNum(Ideainfo ideainfo);

	/**
	 * @param ideaNum
	 * @return
	 */
	public Ideainfo selectIdeainfoByIdeaNum(String ideaNum);

	/**
	 * @param ideainfo
	 * @return
	 */
	public int insertIdeainfo(Ideainfo ideainfo);

	/**
	 * @param userId
	 * @param otherUserId
	 * @return
	 */
	public String showOtherSpaceIdeaMsg(int userId, int otherUserId);
}

