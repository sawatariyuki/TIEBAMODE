package tieba.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tieba.entity.UserBasic;
import tieba.tiezi.simpleClass.SimpleMsgAt;
import tieba.tiezi.simpleClass.SimpleUserBasic;
import tieba.tiezi.simpleClass.SimpleUserDetail;
import tieba.tiezi.simpleClass.SimpleUserOperateRecord;

public interface UserServiceInterface {
	public UserBasic getUserBasic(HttpServletRequest request, HttpServletResponse response);
	public List<SimpleUserOperateRecord> getUserOperateRecordByUserId(HttpServletRequest request, HttpServletResponse response);
	public SimpleUserDetail getUserDetailByUserId(HttpServletRequest request, HttpServletResponse response);
	public String updateUserDetail(HttpServletRequest request, HttpServletResponse response);
	public String insertAtsomeMsg(HttpServletRequest req, HttpServletResponse resp);
	public List<SimpleMsgAt> getMsgAtByUserId(HttpServletRequest req, HttpServletResponse resp, Boolean flag);
	public List<SimpleUserBasic> getUserBasicSimpleData();
}
