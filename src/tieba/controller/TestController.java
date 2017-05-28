package tieba.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import tieba.entity.UserBasic;
import tieba.entity.UserDetail;
import tieba.entity.UserOperateRecord;
import tieba.service.TestService;

@Controller
@RequestMapping("/tieba/test")
public class TestController extends BaseController{
	@Autowired
	TestService testService;
	
	/**
	 * ……/tieba/test/getUsers
	 * 获取所有视频的简要信息，包括 视频id、名称、类型、发布时间
	 */
	@RequestMapping(value="/getUsers")
	public void getUsers(HttpServletRequest req, HttpServletResponse resp){
		List<UserBasic> list = testService.getUsers();
		
		for(UserBasic ub : list){
			ub.setUserDetails(null);
			ub.setUserOperateRecords(null);
			ub.setTiezis(null);
			ub.setTieziReplies(null);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		this.json = gson.toJson(list);
		setData(req);
		
		
	}
}
