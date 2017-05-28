package tieba.dao;

import java.util.List;

import tieba.entity.UserBasic;
import tieba.entity.UserDetail;

public interface LoginDaoInterface {
	public List<UserBasic> getBasicUser(String username,String password);
	public List<UserBasic> getBasicUser(String username);
	public boolean saveBasicUser(UserBasic userBasic);
}
