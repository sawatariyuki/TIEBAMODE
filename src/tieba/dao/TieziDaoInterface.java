package tieba.dao;

import java.sql.Timestamp;
import java.util.List;

import tieba.entity.Tiezi;
import tieba.entity.TieziReply;
import tieba.entity.UserOperateRecord;

public interface TieziDaoInterface {
	public List<Tiezi> getAllTiezi();
	public List<TieziReply> getTieziRepliesByTieziId(Integer tieziId);
	public void addReplyToTiezi(TieziReply tieziReply);
	public void addTiezi(Tiezi tiezi);
	public Integer getMaxFloorId(Integer tieziId);
	public void updateTieziLatestReplyTimeAndMaxFloorId(Integer tieziId,Timestamp latestReplyTime, Integer maxFloorId);
	public List<Integer> getTieziId(String title, Integer posterId, Timestamp postTime);
	public void addTieziViewNum(Integer tieziId);
	public List<Tiezi> getAllTieziByUserId(Integer userId);
	public List<TieziReply> getTieziReplyByUserId(Integer userId);
	public String deleteTieziByTieziId(Integer tieziId, Integer userId);
	public String deleteTieziReplyByIds(Integer tieziId, Integer floorId, Integer userId);
	public void addOperateRecordAndUpdateUserExp(Integer userId, Integer operateId, UserOperateRecord userOperateRecord);
}
