package tieba.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tieba.dao.TieziDaoInterface;
import tieba.entity.Operate;
import tieba.entity.Tiezi;
import tieba.entity.TieziReply;
import tieba.entity.TieziReplyId;
import tieba.entity.UserBasic;
import tieba.entity.UserDetail;
import tieba.entity.UserOperateRecord;
import tieba.entity.UserOperateRecordId;
import tieba.tiezi.simpleClass.SimpleTiezi;
import tieba.tiezi.simpleClass.SimpleTieziReply;
import tieba.tiezi.simpleClass.SimpleTieziReplyHeader;
import tieba.tiezi.simpleClass.SimpleTieziReplyWithTieziTitleId;
import tieba.utils.CusAccessObjectUtil;
import tieba.utils.ExpToLevel;

@Service
@Transactional
public class TieziService implements TieziServiceInterface {
	@Autowired
	TieziDaoInterface tieziDaoInterface;
	
	/***
	 * 获得所有帖子的基本信息
	 */
	@Override
	public List<SimpleTiezi> getAllTiezi(HttpServletRequest req, HttpServletResponse resp) {
		List<Tiezi> tieziList = tieziDaoInterface.getAllTiezi();
		List<SimpleTiezi> simpleTieziList = new ArrayList<SimpleTiezi>();
		SimpleTiezi simpleTiezi = null;
		
		for(Tiezi tz : tieziList){
			int replyNum = tz.getTieziReplies().size()-1;
			String contentText = null;
			String contentImg = null;
			String replyDevice = null;
			String replyLoc = null;
			String lastReplyUserName = null;
			String sign = null;
			String lastReplySign = null;
			Integer lastReplyUserid = null;
			UserDetail ud = null;
			Iterator iterator = tz.getTieziReplies().iterator();
			TieziReply tr = null;
			Integer maxFloorNum = -1;
			Timestamp lastReplyTime = null;
			while(iterator.hasNext()){	//拿到第一楼回复的数据
				tr = (TieziReply) iterator.next();
				if(tr.getId().getFloorId() == 1){
					contentText = tr.getContentText();
					contentImg = tr.getContentImg();
					replyDevice = tr.getReplyDevice();
					replyLoc = tr.getReplyLoc();
					Iterator udi = tr.getUserBasic().getUserDetails().iterator();
					if(udi.hasNext()){
						ud = (UserDetail) udi.next();
						sign = ud.getSign();
					}
					lastReplyTime = null;
				}else{
					if(maxFloorNum < tr.getId().getFloorId()){
						maxFloorNum = tr.getId().getFloorId();
					}
				}
			}
			iterator = tz.getTieziReplies().iterator();
			while(iterator.hasNext()){	//拿到最新回复的数据
				tr = (TieziReply) iterator.next();
				if(tr.getId().getFloorId()==maxFloorNum){
					lastReplyTime = tr.getReplyTime();
					lastReplyUserName = tr.getUserBasic().getUsername();
					lastReplyUserid = tr.getUserBasic().getUserid();
					Iterator udi = tr.getUserBasic().getUserDetails().iterator();
					if(udi.hasNext()){
						ud = (UserDetail) udi.next();
						lastReplySign = ud.getSign();
					}
				}
			}
			simpleTiezi = new SimpleTiezi(tz.getTieziId(), tz.getTitle(), tz.getPostTime(), tz.getViewNum(), replyNum, contentText, contentImg, replyDevice, replyLoc, tz.getUserBasic().getUserid(), tz.getUserBasic().getUsername(), tz.getUserBasic().getUserLevel(), tz.getUserBasic().getIcon(), sign, lastReplyTime, lastReplyUserName, lastReplySign, lastReplyUserid);
			simpleTieziList.add(simpleTiezi);
		}
		return simpleTieziList;
	}

	/***
	 * 根据帖子ID获得该帖子的所有回帖
	 * 请求中用  tiezi_id 来表示帖子ID
	 */
	@Override
	public SimpleTieziReplyHeader getTieziRepliesByTieziId(HttpServletRequest req, HttpServletResponse resp) {
		Integer tieziId = 0;
		if(req.getParameter("tiezi_id")!=null){
			tieziId = Integer.parseInt(req.getParameter("tiezi_id"));
		}else{
			return null;
		}
		System.out.println("TieziService->getTieziRepliesByTieziId->tieziId:"+tieziId);
		List<TieziReply> tieziReplyList = tieziDaoInterface.getTieziRepliesByTieziId(tieziId);
		if(tieziReplyList.size()==0){
			return null;
		}
		SimpleTieziReplyHeader simpleTieziReplyHeader = null;
		List<SimpleTieziReply> simpleTieziReplyList = new ArrayList<SimpleTieziReply>();
		SimpleTieziReply simpleTieziReply = null;
		UserDetail ud = null;
		for(TieziReply tr : tieziReplyList){
			String sign = null;
			Iterator udi = tr.getUserBasic().getUserDetails().iterator();
			if(udi.hasNext()){
				ud = (UserDetail) udi.next();
				sign = ud.getSign();
			}
			simpleTieziReply = new SimpleTieziReply(tr.getId().getFloorId(), tr.getContentText(), tr.getContentImg(), tr.getReplyTime(), tr.getReplyDevice(), tr.getReplyLoc(), tr.getUserBasic().getUserid(), tr.getUserBasic().getUsername(), tr.getUserBasic().getUserLevel(), tr.getUserBasic().getIcon(), sign);
			simpleTieziReplyList.add(simpleTieziReply);
		}
		simpleTieziReplyHeader = new SimpleTieziReplyHeader(tieziReplyList.get(0).getId().getTiezi().getTieziId(), tieziReplyList.get(0).getId().getTiezi().getTitle(), tieziReplyList.get(0).getId().getTiezi().getViewNum(), tieziReplyList.size()-1, simpleTieziReplyList);
		//将该帖子的浏览数加1
		addTieziViewNum(tieziId);
		return simpleTieziReplyHeader;
	}

	/***
	 * 根据用户ID、帖子ID、已有回帖楼层数、当前时间、该等待插入的回复的内容（文字、图片路径）、回复是的设备和地点
	 * 向数据库中插入一个回帖
	 */
	@Override
	public String addReplyToTiezi(HttpServletRequest req, HttpServletResponse resp) {
		Integer tieziId = 0;
		Integer userid = 0;
		String contentText = "";
		String contentImg = "";
		String replyDevice = "";
		String replyLoc = "";
		Integer floorId = 0;
		if(req.getSession().getAttribute("USERID")==null){
			System.out.println("TieziService->addReplyToTiezi->null USERID");
			return "null USERID";
		}
		if(req.getParameter("tiezi_id")==""){
			System.out.println("TieziService->addReplyToTiezi->null tiezi_id");
			return "cannot find this tiezi";
		}
		if(req.getParameter("contentText")=="" && req.getParameter("contentImg")==""){
			System.out.println("TieziService->addReplyToTiezi->null content");
			return "null content";
		}
		tieziId = Integer.parseInt(req.getParameter("tiezi_id"));
		userid = (Integer) req.getSession().getAttribute("USERID");
		contentText = req.getParameter("contentText");
		contentImg = req.getParameter("contentImg");
		replyDevice = req.getParameter("replyDevice");
		replyLoc = req.getParameter("replyLoc");
//		List<TieziReply> tieziReplyList = tieziDaoInterface.getTieziRepliesByTieziId(tieziId);
//		if(tieziReplyList.size()==0){
//			return "cannot find this tiezi";
//		}
		floorId = tieziDaoInterface.getMaxFloorId(tieziId) + 1;
//		floorId = tieziReplyList.get(tieziReplyList.size()-1).getId().getFloorId() + 1;
		Timestamp timestamp = new Timestamp(new Date().getTime());	//回帖时间
		UserBasic userBasic = new UserBasic(userid, null, null, null, null, null, null);	//用户ID
		Tiezi tiezi = new Tiezi(tieziId, userBasic, null, null, null, null, null, null);	//帖子ID
		TieziReplyId trId = new TieziReplyId(tiezi, floorId);	//已有回复楼层数
		TieziReply tr = new TieziReply(trId, userBasic, timestamp, contentText, contentImg, replyDevice, replyLoc);
		tieziDaoInterface.addReplyToTiezi(tr);
		
		//更新帖子内的 最新回复时间 和 最大回复楼层ID
		tieziDaoInterface.updateTieziLatestReplyTimeAndMaxFloorId(tieziId, timestamp, floorId);
		//积分操作（回帖3分 操作ID:3）
		String ipAddress = CusAccessObjectUtil.getIpAddress(req);
		addOperateRecord(userid, timestamp, 3, ipAddress);
		return "reply insert success";
	}

	/***
	 * 发帖:
	 * 根据帖子标题、用户ID、发帖时间
	 * 向数据库中插入帖子
	 * 根据用户ID、帖子ID、已有回帖楼层数、当前时间、该等待插入的回复的内容（文字、图片路径）、回复是的设备和地点
	 * 向数据库中插入一个回帖
	 */
	@Override
	public String addTiezi(HttpServletRequest req, HttpServletResponse resp) {
		String title;
		Integer userid = 0;
		Timestamp postTime = null;
		Tiezi tiezi = null;
		if(req.getSession().getAttribute("USERID")==null){
			System.out.println("TieziService->addTiezi->null USERID");
			return "null USERID";
		}
		if(req.getParameter("title")==""){
			System.out.println("TieziService->addTiezi->null title");
			return "null title";
		}
		if(req.getParameter("contentText")=="" && req.getParameter("contentImg")==""){
			System.out.println("TieziService->addTiezi->null content");
			return "null content";
		}
		userid = (Integer) req.getSession().getAttribute("USERID");
		title = req.getParameter("title");
		UserBasic userBasic = new UserBasic(userid, null, null, null, null, null, null);	//用户ID
		postTime = new Timestamp(new Date().getTime());	//发帖时间
		tiezi = new Tiezi(null, userBasic, title, postTime, null);	//帖子
		tieziDaoInterface.addTiezi(tiezi);
		//	↑插入帖子	↓插入该帖子的第一楼回帖
		Integer tieziId = tieziDaoInterface.getTieziId(title, userid, postTime).get(0);
		String contentText = "";
		String contentImg = "";
		String replyDevice = "";
		String replyLoc = "";
		contentText = req.getParameter("contentText");
		contentImg = req.getParameter("contentImg");
		replyDevice = req.getParameter("replyDevice");
		replyLoc = req.getParameter("replyLoc");
		tiezi = new Tiezi(tieziId, userBasic, null, null, null, postTime, null, null);	//帖子ID
		TieziReplyId trId = new TieziReplyId(tiezi, 1);	//已有回复楼层数
		TieziReply tr = new TieziReply(trId, userBasic, postTime, contentText, contentImg, replyDevice, replyLoc);
		tieziDaoInterface.addReplyToTiezi(tr);
		
		//更新帖子内的 最新回复时间 和 最大回复楼层ID
		tieziDaoInterface.updateTieziLatestReplyTimeAndMaxFloorId(tieziId, postTime, 1);
		//积分操作（发帖10分  操作ID:2）
		String ipAddress = CusAccessObjectUtil.getIpAddress(req);
		addOperateRecord(userid, postTime, 2, ipAddress);
		return "tiezi insert success";
	}

	/***
	 * 根据帖子ID将其浏览数加1
	 */
	@Override
	public void addTieziViewNum(Integer tieziId) {
		tieziDaoInterface.addTieziViewNum(tieziId);
	}

	/***
	 * 根据用户ID获得他的所有发帖（帖子的基本信息）  按时间最新排序
	 */
	@Override
	public List<SimpleTiezi> getAllTieziByUserId(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getParameter("userId")==""||req.getParameter("userId")==null){
			System.out.println("TieziService->getAllTieziByUserId->null userId");
			return null;
		}
		Integer userId = Integer.parseInt(req.getParameter("userId"));
		List<Tiezi> tieziList = tieziDaoInterface.getAllTieziByUserId(userId);
		List<SimpleTiezi> simpleTieziList = new ArrayList<SimpleTiezi>();
		SimpleTiezi simpleTiezi = null;
		for(Tiezi tz : tieziList){
			int replyNum = tz.getTieziReplies().size()-1;
			String contentText = null;
			String contentImg = null;
			String replyDevice = null;
			String replyLoc = null;
			String lastReplyUserName = null;
			String sign = null;
			String lastReplySign = null;
			Integer lastReplyUserid = null;
			UserDetail ud = null;
			Iterator iterator = tz.getTieziReplies().iterator();
			TieziReply tr = null;
			Integer maxFloorNum = -1;
			Timestamp lastReplyTime = null;
			while(iterator.hasNext()){	//拿到第一楼回复的数据
				tr = (TieziReply) iterator.next();
				if(tr.getId().getFloorId() == 1){
					contentText = tr.getContentText();
					contentImg = tr.getContentImg();
					replyDevice = tr.getReplyDevice();
					replyLoc = tr.getReplyLoc();
					Iterator udi = tr.getUserBasic().getUserDetails().iterator();
					if(udi.hasNext()){
						ud = (UserDetail) udi.next();
						sign = ud.getSign();
					}
					lastReplyTime = null;
				}else{
					if(maxFloorNum < tr.getId().getFloorId()){
						maxFloorNum = tr.getId().getFloorId();
					}
				}
			}
			iterator = tz.getTieziReplies().iterator();
			while(iterator.hasNext()){	//拿到最新回复的数据
				tr = (TieziReply) iterator.next();
				if(tr.getId().getFloorId()==maxFloorNum){
					lastReplyTime = tr.getReplyTime();
					lastReplyUserName = tr.getUserBasic().getUsername();
					lastReplyUserid = tr.getUserBasic().getUserid();
					Iterator udi = tr.getUserBasic().getUserDetails().iterator();
					if(udi.hasNext()){
						ud = (UserDetail) udi.next();
						lastReplySign = ud.getSign();
					}
				}
			}
			simpleTiezi = new SimpleTiezi(tz.getTieziId(), tz.getTitle(), tz.getPostTime(), tz.getViewNum(), replyNum, contentText, contentImg, replyDevice, replyLoc, tz.getUserBasic().getUserid(), tz.getUserBasic().getUsername(), tz.getUserBasic().getUserLevel(), tz.getUserBasic().getIcon(), sign, lastReplyTime, lastReplyUserName, lastReplySign, lastReplyUserid);
			simpleTieziList.add(simpleTiezi);
		}
		return simpleTieziList;
	}

	/***
	 * 根据用户ID获得他的全部回帖（包括对应帖子的ID、title） 按时间最新排序
	 */
	@Override
	public List<SimpleTieziReplyWithTieziTitleId> getTieziReplyByUserId(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getParameter("userId")==""||req.getParameter("userId")==null){
			System.out.println("TieziService->getTieziReplyByUserId->null userId");
			return null;
		}
		Integer userId = Integer.parseInt(req.getParameter("userId"));
		List<TieziReply> replyList = tieziDaoInterface.getTieziReplyByUserId(userId);
		
		List<SimpleTieziReplyWithTieziTitleId> simpleReplyList = new ArrayList<SimpleTieziReplyWithTieziTitleId>();
		SimpleTieziReplyWithTieziTitleId simpleReply = null;
		for(TieziReply tr : replyList){
			simpleReply = new SimpleTieziReplyWithTieziTitleId(tr.getId().getTiezi().getTieziId(), tr.getId().getTiezi().getTitle(), tr.getId().getFloorId(), tr.getContentText(), tr.getContentImg(), tr.getReplyTime(), tr.getReplyDevice(), tr.getReplyLoc());
			simpleReplyList.add(simpleReply);
		}
		return simpleReplyList;
	}

	/***
	 * 根据帖子ID删除该贴（还需用户ID）
	 */
	@Override
	public String deleteTieziByTieziId(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getParameter("tieziId")==""||req.getParameter("tieziId")==null){
			System.out.println("TieziService->deleteTieziByByTieziId->null tieziId");
			return "null tieziId";
		}
		String result;
		Integer userId = (Integer) req.getSession().getAttribute("USERID");
		Integer tieziId = Integer.parseInt(req.getParameter("tieziId"));
		result = tieziDaoInterface.deleteTieziByTieziId(tieziId, userId);
		if(result=="error"){
			return "no tiezi:"+tieziId+" under userId:"+userId;
		}
		return "delete tiezi:"+tieziId+" success";
	}

	/***
	 * 根据帖子ID、楼层ID、用户ID删除该回帖
	 */
	@Override
	public String deleteTieziReplyByIds(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getParameter("tieziId")==""||req.getParameter("tieziId")==null){
			System.out.println("TieziService->deleteTieziReplyByIds->null tieziId");
			return "null tieziId";
		}
		if(req.getParameter("floorId")==""||req.getParameter("floorId")==null){
			System.out.println("TieziService->deleteTieziReplyByIds->null floorId");
			return "null floorId";
		}
		String result;
		Integer userId = (Integer) req.getSession().getAttribute("USERID");
		Integer tieziId = Integer.parseInt(req.getParameter("tieziId"));
		Integer floorId = Integer.parseInt(req.getParameter("floorId"));
		result = tieziDaoInterface.deleteTieziReplyByIds(tieziId, floorId, userId);
		//更新帖子的最新回复时间（最大楼层ID不变） 若帖子数据有变化只会因为删除的是最后一楼
		Timestamp latestReplyTime = null;
		List<TieziReply> tieziReplyList =  tieziDaoInterface.getTieziRepliesByTieziId(tieziId);
		latestReplyTime = tieziReplyList.get(tieziReplyList.size()-1).getReplyTime();
		tieziDaoInterface.updateTieziLatestReplyTimeAndMaxFloorId(tieziId, latestReplyTime, -1);
		
		if(result=="error"){
			return "no reply of tiezi:"+tieziId+" and floor:"+floorId+" under userId:"+userId;
		}
		return "delete reply:"+tieziId+"-"+floorId+" success";
	}
	
	/***
	 * 根据用户ID、操作类型ID、操作时间插入操作记录
	 */
	@Override
	public void addOperateRecord(Integer userId, Timestamp timestamp, Integer operateId, String ipAddress) {
		UserBasic userBasic = new UserBasic(userId, null, null, null, null, null, null);
		UserOperateRecordId id = new UserOperateRecordId(userBasic, timestamp);
		Operate operate = new Operate(operateId, null, null);
		UserOperateRecord userOperateRecord = new UserOperateRecord(id, operate, ipAddress);
		tieziDaoInterface.addOperateRecordAndUpdateUserExp(userId, operateId, userOperateRecord);
	}
}
