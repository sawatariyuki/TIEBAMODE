package tieba.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.transaction.annotation.Transactional;

import tieba.tiezi.simpleClass.SimpleTiezi;
import tieba.tiezi.simpleClass.SimpleTieziReplyHeader;
import tieba.tiezi.simpleClass.SimpleTieziReplyWithTieziTitleId;

public interface TieziServiceInterface {
	public List<SimpleTiezi> getAllTiezi(HttpServletRequest req, HttpServletResponse resp);
	public SimpleTieziReplyHeader getTieziRepliesByTieziId(HttpServletRequest req, HttpServletResponse resp);
	public String addReplyToTiezi(HttpServletRequest req, HttpServletResponse resp);
	public String addTiezi(HttpServletRequest req, HttpServletResponse resp);
	public void addTieziViewNum(Integer tieziId);
	public List<SimpleTiezi> getAllTieziByUserId(HttpServletRequest req, HttpServletResponse resp);
	public List<SimpleTieziReplyWithTieziTitleId> getTieziReplyByUserId(HttpServletRequest req, HttpServletResponse resp);
	public String deleteTieziByTieziId(HttpServletRequest req, HttpServletResponse resp);
	public String deleteTieziReplyByIds(HttpServletRequest req, HttpServletResponse resp);
	public void addOperateRecord(Integer userId, Timestamp timestamp, Integer operateId, String ipAddress);
}
