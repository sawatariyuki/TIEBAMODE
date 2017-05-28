package tieba.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tieba.entity.Tiezi;
import tieba.entity.TieziReply;
import tieba.entity.UserOperateRecord;
import tieba.utils.ExpToLevel;
import tieba.utils.TimeUtils;

@Repository
public class TieziDao extends BaseSessionFactory implements TieziDaoInterface {

	//所有帖子 按时间最新排序
	@Override
	public List<Tiezi> getAllTiezi() {
		try {
			String queryString = "from Tiezi tz order by tz.latestReplyTime desc";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setFirstResult(0);
			q.setMaxResults(100);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//一个帖子的所有回帖 按楼层号最小排序
	@Override
	public List<TieziReply> getTieziRepliesByTieziId(Integer tieziId) {
		try {
			String queryString = "from TieziReply tr where tr.id.tiezi.tieziId=:tiezi_id order by tr.id.floorId";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("tiezi_id", tieziId);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//插入一个帖子
	@Override
	public void addReplyToTiezi(TieziReply tieziReply) {
		try {
			Session session = getSession();
			session.save(tieziReply);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//发一个帖子
	@Override
	public void addTiezi(Tiezi tiezi) {
		Session session = getSession();
		try {
			session.save(tiezi);
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	//根据帖子ID查询最大楼层数
	@Override
	public Integer getMaxFloorId(Integer tieziId) {
		try {
			String queryString = "select tz.maxFloorId from Tiezi tz where tz.tieziId=:tieziId";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("tieziId", tieziId);
			return (Integer) q.list().get(0);
		} catch (RuntimeException re) {
			throw re;
		}
	}
		
	//根据帖子ID 更新其最新回复时间和最大楼层ID
	@Override
	public void updateTieziLatestReplyTimeAndMaxFloorId(Integer tieziId, Timestamp latestReplyTime, Integer maxFloorId){
		try {
			Session session = getSession();
			if(maxFloorId==-1){
				String queryString = "update Tiezi tz set tz.latestReplyTime=:latestReplyTime where tz.tieziId=:tieziId";
				Query q = session.createQuery(queryString);
				q.setParameter("latestReplyTime", latestReplyTime);
				q.setParameter("tieziId", tieziId);
				q.executeUpdate();
			}else{
				String queryString = "update Tiezi tz set tz.latestReplyTime=:latestReplyTime,tz.maxFloorId=:maxFloorId where tz.tieziId=:tieziId";
				Query q = session.createQuery(queryString);
				q.setParameter("latestReplyTime", latestReplyTime);
				q.setParameter("maxFloorId", maxFloorId);
				q.setParameter("tieziId", tieziId);
				q.executeUpdate();
			}
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据帖子标题、发帖者ID、发帖时间查询帖子ID
	@Override
	public List<Integer> getTieziId(String title, Integer posterId, Timestamp postTime) {
		try {
			String queryString = "select tz.tieziId from Tiezi tz where tz.title=:title and tz.userBasic.userid=:posterId order by tz.tieziId desc";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("title", title);
			q.setParameter("posterId", posterId);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据帖子ID将其浏览数加1
	@Override
	public void addTieziViewNum(Integer tieziId) {
		try {
			String queryString = "update Tiezi tz set tz.viewNum=tz.viewNum+1 where tz.tieziId=:tiezi_id";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("tiezi_id", tieziId);
			q.executeUpdate();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据用户ID获得他的全部发帖 按时间最新排序
	@Override
	public List<Tiezi> getAllTieziByUserId(Integer userId) {
		try {
			String queryString = "from Tiezi tz where tz.userBasic.userid=:userId order by tz.postTime desc";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("userId", userId);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据用户ID获得他的全部回帖（包括对应帖子的ID、title） 按时间最新排序
	@Override
	public List<TieziReply> getTieziReplyByUserId(Integer userId) {
		try {
			String queryString = "from TieziReply tr where tr.userBasic.userid=:userId and tr.id.floorId!=1 order by tr.replyTime desc";
			Session session = getSession();
			Query q = session.createQuery(queryString);
			q.setParameter("userId", userId);
			return q.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

	//根据帖子ID、用户ID删除该贴
	@Override
	public String deleteTieziByTieziId(Integer tieziId, Integer userId) {
		try {
			Session session = getSession();
			String queryString = "from Tiezi tz where tz.tieziId=:tieziId and tz.userBasic.userid=:userid";
			Query q = session.createQuery(queryString);
			q.setParameter("tieziId", tieziId);
			q.setParameter("userid", userId);
			if(q.list().size()==0){
				System.out.println("TieziDao->deleteTieziByByTieziId->null tiezi with userId:"
									+userId+" and tieziId:"+tieziId);
				return "error";
			}
			queryString = "delete from Tiezi tz where tz.tieziId=:tieziId and tz.userBasic.userid=:userid";
			q = session.createQuery(queryString);
			q.setParameter("tieziId", tieziId);
			q.setParameter("userid", userId);
			q.executeUpdate();
			return "success";
		} catch (RuntimeException re) {
			throw re;			
		}
	}

	//根据帖子ID、楼层ID、用户ID删除该回帖
	@Override
	public String deleteTieziReplyByIds(Integer tieziId, Integer floorId, Integer userId) {
		try {
			Session session = getSession();
			String queryString = "from TieziReply tr where tr.id.tiezi.tieziId=:tieziId and tr.id.floorId=:floorId and tr.userBasic.userid=:userid";
			Query q = session.createQuery(queryString);
			q.setParameter("tieziId", tieziId);
			q.setParameter("floorId", floorId);
			q.setParameter("userid", userId);
			if(q.list().size()==0){
				System.out.println("TieziDao->deleteTieziReplyByIds->null reply with userId:"
									+userId+" and tieziId:"+tieziId+" and floorId:"+floorId);
				return "error";
			}
			queryString = "delete from TieziReply tr where tr.id.tiezi.tieziId=:tieziId and tr.id.floorId=:floorId and tr.userBasic.userid=:userid";
			q = session.createQuery(queryString);
			q.setParameter("tieziId", tieziId);
			q.setParameter("floorId", floorId);
			q.setParameter("userid", userId);
			q.executeUpdate();
			return "success";
		} catch (RuntimeException re) {
			throw re;			
		}
	}
	
	//根据用户ID、操作ID更新该用户的经验值，并更新其等级（在每次有操作记录后调用）
	@Override
	public void addOperateRecordAndUpdateUserExp(Integer userId, Integer operateId, UserOperateRecord userOperateRecord) {
		Session session = getSession();
		//操作ID为1是登录 判断是否是同一天的第一次登录，若不是则不给予登录经验
		if(operateId==1){
			Timestamp timeInRecord = null;
			try {
				String queryString = "select uor.id.operateTime from UserOperateRecord uor "
						+ "where uor.id.userBasic.userid=:userid and uor.operate.operateId=:operateId "
						+ "order by uor.id.operateTime desc";
				Query q = session.createQuery(queryString);
				q.setParameter("userid", userId);
				q.setParameter("operateId", operateId);
				if(q.list().size()!=0){
					timeInRecord = (Timestamp) q.list().get(0);
				}
				
			} catch (RuntimeException re) {
				throw re;
			}
			if(TimeUtils.isTheSameDate(timeInRecord, new Timestamp(new Date().getTime()))){
				System.out.println("login during the same day");
				session.save(userOperateRecord);
				return;
			}
		}
		try {
			String queryString = "select op.exp from Operate op where op.operateId=:operateId";
			Query q = session.createQuery(queryString);
			q.setParameter("operateId", operateId);
			Integer exp = (Integer) q.list().get(0);	//获得操作ID对应的经验值
			
			queryString = "select ub.exp from UserBasic ub where ub.userid=:userid";
			q = session.createQuery(queryString);
			q.setParameter("userid", userId);
			exp += (Integer) q.list().get(0);			//将用户的经验值与操作经验值相加
			
			Integer level = ExpToLevel.getLevelByExp(exp);	//计算用户等级
			queryString = "update UserBasic ub set ub.exp=:exp,ub.userLevel=:userLevel where ub.userid=:userid";
			q = session.createQuery(queryString);
			q.setParameter("userid", userId);
			q.setParameter("userLevel", level);
			q.setParameter("exp", exp);
			q.executeUpdate();
			
			System.out.println("插入操作记录");
			session.save(userOperateRecord);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
