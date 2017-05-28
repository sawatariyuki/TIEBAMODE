package tieba.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tieba.dao.UserDaoInterface;
import tieba.entity.MsgAt;
import tieba.entity.MsgAtId;
import tieba.entity.UserBasic;
import tieba.entity.UserDetail;
import tieba.entity.UserOperateRecord;
import tieba.tiezi.simpleClass.SimpleMsgAt;
import tieba.tiezi.simpleClass.SimpleUserBasic;
import tieba.tiezi.simpleClass.SimpleUserDetail;
import tieba.tiezi.simpleClass.SimpleUserOperateRecord;
import tieba.utils.SendMessage;

@Service
@Transactional
public class UserServiceImp implements UserServiceInterface {

	@Autowired
	UserDaoInterface userDao;
	
	@Override
	public UserBasic getUserBasic(HttpServletRequest request, HttpServletResponse response) {
		int userid = (int) request.getSession().getAttribute("USERID");
		List<UserBasic> ubl = userDao.getUserBasic(userid);
		if(ubl != null){
			if(userDao.isNewMsgAt(userid)){
				SendMessage.atSomeone(userid);	//用户下线后是否有被@
			}
			return ubl.get(0);
		}
		return null;
	}

	/***
	 * 根据用户ID获得前20条操作记录
	 */
	@Override
	public List<SimpleUserOperateRecord> getUserOperateRecordByUserId(HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute("USERID")==null){
			System.out.println("UserServiceImp->getUserOperateRecordByUserId->null USERID");
			return null;
		}
		Integer userid = (Integer) request.getSession().getAttribute("USERID");
		List<UserOperateRecord> userOperateRecords = userDao.getUserOperateRecordByUserId(userid);
		
		List<SimpleUserOperateRecord> simpleUserOperateRecords = new ArrayList<SimpleUserOperateRecord>();
		SimpleUserOperateRecord simpleUserOperateRecord = null;
		for(UserOperateRecord uor : userOperateRecords){
			simpleUserOperateRecord = new SimpleUserOperateRecord(uor.getOperate().getOperateName(), uor.getId().getOperateTime(), uor.getIpAddress(), uor.getOperate().getExp());
			simpleUserOperateRecords.add(simpleUserOperateRecord);
		}
		return simpleUserOperateRecords;
	}

	/***
	 * 根据用户ID获得其详细信息		并根据生日更新其年龄 （USERID为GET请求）
	 */
	@Override
	public SimpleUserDetail getUserDetailByUserId(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("USERID")==null || request.getParameter("USERID")==""){
			System.out.println("UserServiceImp->getUserDetailByUserId->null USERID");
			return null;
		}
		Integer userid = Integer.parseInt(request.getParameter("USERID"));
		List<UserBasic> UserBasics = userDao.getUserDetailByUserId(userid);
		if(userDao.getUserDetailByUserId(userid).size()==0){
			return null;
		}
		UserBasic ub = userDao.getUserDetailByUserId(userid).get(0);
		Iterator ubi = ub.getUserDetails().iterator();
		Timestamp birthday = null;
		Integer age = null;
		BigDecimal phoneNum = null;
		String sign = null;
		String intro = null;
		String bloodType = null;
		String occupation = null;
		String company = null;
		String birthLand = null;
		String liveLand = null;
		if(ubi.hasNext()){
			UserDetail ud = (UserDetail) ubi.next();
			birthday = ud.getBirthday();
			age = ud.getAge();
			phoneNum = ud.getPhoneNum();
			sign = ud.getSign();
			intro = ud.getIntro();
			bloodType = ud.getBloodType();
			occupation = ud.getOccupation();
			company = ud.getCompany();
			birthLand = ud.getBirthLand();
			liveLand = ud.getLiveLand();
		}
		SimpleUserDetail simpleUserDetial = new SimpleUserDetail(userid, ub.getUsername(), ub.getEmail(), ub.getIcon(), ub.getGender(), ub.getUserLevel(), ub.getExp(), birthday, age, phoneNum, sign, intro, bloodType, occupation, company, birthLand, liveLand);
		return simpleUserDetial;
	}

	/***
	 * 根据用户ID修改其详细信息
	 */
	@Override
	public String updateUserDetail(HttpServletRequest request, HttpServletResponse response) {
		if(request.getSession().getAttribute("USERID")==null){
			System.out.println("UserServiceImp->updateUserDetail->null USERID");
			return "null USERID";
		}
		Integer userid = (Integer) request.getSession().getAttribute("USERID");
		String email = request.getParameter("email");
		String icon = request.getParameter("icon");
		String gender = request.getParameter("gender");
		Timestamp birthday = null;
		if(request.getParameter("birthday")!=""){
			birthday = Timestamp.valueOf(request.getParameter("birthday").trim()+" 00:00:00");
		}
		BigDecimal phoneNum = null;
		if(request.getParameter("phoneNum")!=""){
			phoneNum = new BigDecimal(request.getParameter("phoneNum"));
		}
		String sign = request.getParameter("sign");
		String intro = request.getParameter("intro");
		String bloodType = request.getParameter("bloodType");
		String occupation = request.getParameter("occupation");
		String company = request.getParameter("company");
		String birthLand = request.getParameter("birthLand");
		String liveLand = request.getParameter("liveLand");
		if(icon==null || icon==""){
			icon = "default";
		}
		UserBasic userBasic = new UserBasic();
		userBasic.setGender(gender);
		userBasic.setEmail(email);
		userBasic.setIcon(icon);
		UserDetail userDetail = new UserDetail(userid, null, birthday, null, phoneNum, sign, intro, bloodType, occupation, company, birthLand, liveLand);
		userDao.updateUserDetail(userid, userDetail, userBasic);
		return "userDetail update success";
	}

	/***
	 * 插入一个@记录
	 */
	@Override
	public String insertAtsomeMsg(HttpServletRequest req, HttpServletResponse resp) {
		if(req.getSession().getAttribute("USERID")==null){
			System.out.println("UserServiceImp->insertAtsomeMsg->null USERID");
			return "null USERID";
		}
		if(req.getParameter("targetUser")==""){
			System.out.println("UserServiceImp->insertAtsomeMsg->null targetUser");
			return "null targetUser";
		}
		if(req.getParameter("msg")==""){
			System.out.println("UserServiceImp->insertAtsomeMsg->null msg");
			return "null msg";
		}
		Integer src_id = (Integer) req.getSession().getAttribute("USERID");
		Integer dest_id = Integer.parseInt(req.getParameter("targetUser"));
		String msg = req.getParameter("msg");
		UserBasic src_ub = new UserBasic(src_id, null, null, null, null, null, null);
		UserBasic dest_ub = new UserBasic(dest_id, null, null, null, null, null, null);
		MsgAtId msgAtId = new MsgAtId(src_ub, dest_ub, new Timestamp(new Date().getTime()));
		MsgAt msgAt = new MsgAt(msgAtId, msg, 0);
		userDao.insertAtsomeMsg(msgAt);
		return "@ insert success";
	}
	
	/***
	 * 根据用户id查询其所有的@，flag：true表示是发出的@，false表示是收到的@
	 */
	@Override
	public List<SimpleMsgAt> getMsgAtByUserId(HttpServletRequest req, HttpServletResponse resp, Boolean flag) {
		if(req.getSession().getAttribute("USERID")==null){
			System.out.println("UserServiceImp->getMsgAtByUserId->null USERID");
			return null;
		}
		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		List<MsgAt> msgAts = userDao.getMsgAtByUserId(userid, flag);
		List<SimpleMsgAt> simpleMsgAts = new ArrayList<SimpleMsgAt>();
		SimpleMsgAt sm = null;
		for(MsgAt m : msgAts){
			String src_sign = null;
			String dest_sign = null;
			Iterator iterator = null;
			UserDetail ud = null;
			iterator = m.getId().getUserBasic().getUserDetails().iterator();
			if(iterator.hasNext()){
				ud = (UserDetail) iterator.next();
				src_sign = ud.getSign();
			}
			iterator = m.getId().getUserBasic_1().getUserDetails().iterator();
			if(iterator.hasNext()){
				ud = (UserDetail) iterator.next();
				dest_sign = ud.getSign();
			}
			sm = new SimpleMsgAt(m.getId().getUserBasic().getUserid(), m.getId().getUserBasic().getUsername(), m.getId().getUserBasic().getUserLevel(), m.getId().getUserBasic().getIcon(), src_sign, m.getId().getUserBasic_1().getUserid(), m.getId().getUserBasic_1().getUsername(), m.getId().getUserBasic_1().getUserLevel(), m.getId().getUserBasic_1().getIcon(), dest_sign, m.getMessage(), m.getId().getAtTime(), m.getIsread());
			simpleMsgAts.add(sm);
		}
		//如果是查看@，则先返回各个@，再将其收到的所有@的isread改为1，表示已读		
		if(!flag){
			userDao.readAt(userid);
			SendMessage.readAt(req);	//并将消息系统的new@提示去除
		}
		return simpleMsgAts;
	}

	//根据用户id查询最基础的信息
	//包括：用户id、用户名、用户头像、用户等级
	@Override
	public List<SimpleUserBasic> getUserBasicSimpleData() {
		Set<Integer> userSet = SendMessage.getAllUserIdInMap();
		List<Integer> userids = new ArrayList<>();
		Iterator iterator = userSet.iterator();
		while(iterator.hasNext()){
			userids.add((Integer) iterator.next());
		}
		List<SimpleUserBasic> subs = new ArrayList<>();
		SimpleUserBasic sub = null;
		for(Object[] each : userDao.getUserBasicSimpleData(userids)){
			sub = new SimpleUserBasic((Integer)each[0], (String)each[1], (String)each[2], (Integer)each[3]);
			subs.add(sub);
		}
		return subs;
	}
	
	
}
