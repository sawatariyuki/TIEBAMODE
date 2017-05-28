package tieba.dao;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import tieba.entity.UserBasic;

@Repository
public class TestConnImp extends BaseSessionFactory implements TestConn {

	@Override
	public List<UserBasic> getUsers() {
		try {
			String queryString = "from UserBasic";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			throw re;
		}
	}

}
