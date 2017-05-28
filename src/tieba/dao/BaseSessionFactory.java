package tieba.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseSessionFactory {
	@Autowired
	SessionFactory sessionFactory;
	
	public Session getSession(){
		System.out.println("BaseSessionFactory->getSession(getCurrentSesson)");
		return sessionFactory.getCurrentSession();
	}
}
