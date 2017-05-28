package tieba.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tieba.dao.LoginDaoInterface;
import tieba.entity.UserBasic;
import tieba.entity.UserDetail;
import tieba.utils.CusAccessObjectUtil;

@Service
@Transactional
public class LoginServiceImp implements LoginServiceInterface {

	@Autowired
	LoginDaoInterface loginDao;
	@Autowired
	TieziServiceInterface tieziServiceInterface;

	@Override
	public String isValid(HttpServletRequest request,
			HttpServletResponse response) {

		// 获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		List<UserBasic> ubl = loginDao.getBasicUser(username, password);
		if (ubl.size() == 0) {
			return "登录失败";
		} else {
			int userid = ubl.get(0).getUserid();
			request.getSession().setAttribute("USERID", userid);
			//积分操作（登录20分  操作ID:1）
			Timestamp timestamp = new Timestamp(new Date().getTime());
			String ipAddress = CusAccessObjectUtil.getIpAddress(request);
			tieziServiceInterface.addOperateRecord(userid, timestamp, 1, ipAddress);
			return "登录成功";
		}
	}
	
	@Override
	public String register(HttpServletRequest request,
			HttpServletResponse response) {
		// 获取表单数据
		String username = request.getParameter("username");
		String password = request.getParameter("password");
			
		if(username == null || password == null){
			return "用户名密码不能为空";
		}else{
			List<UserBasic> ubl = loginDao.getBasicUser(username);
			if(ubl.size() > 0){
				return "用户名已存在";
			}
			UserBasic userBasic = new UserBasic(null,username, password, null, null, null, null, null, null, null, null, null);	
			loginDao.saveBasicUser(userBasic);
			ubl = loginDao.getBasicUser(username);
			int userid = ubl.get(0).getUserid();
			request.getSession().setAttribute("USERID",userid);
			
			//注册成功后自动登录 所以加一天登录记录并修改经验
			String ipAddress = CusAccessObjectUtil.getIpAddress(request);
			tieziServiceInterface.addOperateRecord(userid, new Timestamp(new Date().getTime()), 1, ipAddress);
			return "注册成功";
		}	
	}
}
