package tieba.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tieba.entity.MsgAt;
import tieba.entity.UserBasic;
import tieba.entity.UserDetail;
import tieba.entity.UserOperateRecord;
import tieba.tiezi.simpleClass.SimpleUserBasic;
import tieba.utils.TimeUtils;

@Repository
public class UserDaoImp extends BaseSessionFactory implements UserDaoInterface{

	@Override
	public List<UserBasic> getUserBasic(int userid) {
		Session session = getSession();
		Query q = session.createQuery("from UserBasic u where u.userid =:userid");
		q.setParameter("userid", userid);
		List<UserBasic> ubl = q.list();
		return ubl;
	}

	//根据用户ID获得其前20条操作记录
	@Override
	public List<UserOperateRecord> getUserOperateRecordByUserId(Integer userid) {
		try {
			String queryString = "from UserOperateRecord uor where uor.id.userBasic.userid=:userid order by uor.id.operateTime desc";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("userid", userid);
			q.setFirstResult(0);
			q.setMaxResults(20);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据用户ID获得其详细信息		并根据生日更新其年龄
	@Override
	public List<UserBasic> getUserDetailByUserId(Integer userid) {
		try {
			Session session = getSession();
			String queryString = "select ud.birthday from UserDetail ud where ud.userid=:userid";
			Query q = session.createQuery(queryString);
			q.setParameter("userid", userid);
			if(q.list().size()!=0){
				if(q.list().get(0)!=null){
					Timestamp birthday = (Timestamp) q.list().get(0);
					Integer age = TimeUtils.getAgeFromBirthday(birthday);
					queryString = "update UserDetail ud set ud.age=:age where ud.userid=:userid";
					q = session.createQuery(queryString);
					q.setParameter("age", age);
					q.setParameter("userid", userid);
					q.executeUpdate();
				}
			}
			queryString = "from UserBasic ud where ud.userid=:userid";
			q = session.createQuery(queryString);
			q.setParameter("userid", userid);
			
			System.out.println("------------ "+q.list().size());
			
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//根据用户ID修改其详细信息
	@Override
	public String updateUserDetail(Integer userid, UserDetail userDetail, UserBasic userBasic){
		try {
			Session session = getSession();
			String queryString = "select ud.userid from UserDetail ud where ud.userid=:userid";
			Query q = session.createQuery(queryString);
			q.setParameter("userid", userid);
			
			if(q.list().size()==0){
				session.save(userDetail);
			}else{
				session.update(userDetail);
			}
	
			queryString = "update UserBasic ub set ub.email=:email,ub.icon=:icon,ub.gender=:gender"
					+ " where ub.userid=:userid";
			q = session.createQuery(queryString);
			q.setParameter("userid", userid);
			q.setParameter("email", userBasic.getEmail());
			q.setParameter("icon", userBasic.getIcon());
			q.setParameter("gender", userBasic.getGender());
			q.executeUpdate();
			return "userDetail update success";
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//插入一个@记录
	@Override
	public void insertAtsomeMsg(MsgAt msgAt) {
		try {
			Session session = getSession();
			session.save(msgAt);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据用户id查询其所有的@，flag：true表示是发出的@，false表示是收到的@
	//限制为前50条
	@Override
	public List<MsgAt> getMsgAtByUserId(Integer userid, Boolean flag) {
		try {
			Session session = getSession();
			String queryString = null;
			if(flag){
				queryString = "from MsgAt m where m.id.userBasic.userid=:userid order by m.id.atTime desc";
			}else{
				queryString = "from MsgAt m where m.id.userBasic_1.userid=:userid order by m.isread, m.id.atTime desc";
			}
			Query q = session.createQuery(queryString);
			q.setParameter("userid", userid);
			q.setFirstResult(0);
			q.setMaxResults(50);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//被@者读完后将isread更新为1，表示已读
	@Override
	public void readAt(Integer dest_id) {
		try {
			Session session = getSession();
			String queryString = "update MsgAt m set m.isread=:isread where m.id.userBasic_1.userid=:userid";
			Query q = session.createQuery(queryString);
			q.setParameter("isread", 1);
			q.setParameter("userid", dest_id);
			q.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//查询是否有新的被@（在用户下线后） true有，false无
	@Override
	public Boolean isNewMsgAt(Integer userid) {
		try {
			Session session = getSession();
			String queryString = "from MsgAt m where m.id.userBasic_1.userid=:userid and m.isread=0";
			Query q = session.createQuery(queryString);
			q.setParameter("userid", userid);	
			if(q.list()==null){
				return false;
			}
			if(q.list().size()>0){
				return true;
			}
		} catch (RuntimeException re) {
			throw re;
		}
		return false;
	}

	//根据用户id查询最基础的信息
	//包括：用户id、用户名、用户头像、用户等级
	@Override
	public List<Object[]> getUserBasicSimpleData(List<Integer> userids) {
		try {
			String queryString = "select ub.userid,ub.username,ub.icon,ub.userLevel from UserBasic ub where ub.userid in (:userids)";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameterList("userids", userids);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
