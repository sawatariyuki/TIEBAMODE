package tieba.dao;

import java.util.List;

import tieba.entity.MsgAt;
import tieba.entity.UserBasic;
import tieba.entity.UserDetail;
import tieba.entity.UserOperateRecord;
import tieba.tiezi.simpleClass.SimpleUserBasic;

public interface UserDaoInterface {
	public List<UserBasic> getUserBasic(int userid);
	public List<UserOperateRecord> getUserOperateRecordByUserId(Integer userid);
	public List<UserBasic> getUserDetailByUserId(Integer userid);
	public String updateUserDetail(Integer userid, UserDetail userDetail, UserBasic userBasic);
	public void insertAtsomeMsg(MsgAt sgAt);
	public List<MsgAt> getMsgAtByUserId(Integer userid, Boolean flag);
	public void readAt(Integer dest_id);
	public Boolean isNewMsgAt(Integer userid);
	public List<Object[]> getUserBasicSimpleData(List<Integer> userids);
}
