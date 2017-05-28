package tieba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tieba.dao.TestConn;
import tieba.entity.UserBasic;

@Service
public class TestServiceImp implements TestService {

	@Autowired
	TestConn testConn;
	
	@Override
	public List<UserBasic> getUsers() {
		return testConn.getUsers();
	}

}
