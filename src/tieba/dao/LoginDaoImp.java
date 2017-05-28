package tieba.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tieba.entity.UserBasic;
import tieba.entity.UserDetail;

@Repository
public class LoginDaoImp extends BaseSessionFactory implements LoginDaoInterface {

	@Override
	public List<UserBasic> getBasicUser(String username, String password) {

		Session session = getSession();
		Query q = session.createQuery("from UserBasic u where u.username =:username and u.password =:password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		
		List<UserBasic> ubl = q.list();
		return ubl;
	}

	@Override
	public boolean saveBasicUser(UserBasic userBasic) {
		Session session = getSession();
		try {
			session.save(userBasic);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<UserBasic> getBasicUser(String username) {
		Session session = getSession();
		Query q = session.createQuery("from UserBasic u where u.username =:username");
		q.setParameter("username", username);
		List<UserBasic> ubl = q.list();
		return ubl;
	}

}
