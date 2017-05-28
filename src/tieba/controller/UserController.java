package tieba.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ListCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tieba.entity.UserBasic;
import tieba.service.UserServiceInterface;
import tieba.tiezi.simpleClass.SimpleMsgAt;
import tieba.tiezi.simpleClass.SimpleUserBasic;
import tieba.tiezi.simpleClass.SimpleUserDetail;
import tieba.tiezi.simpleClass.SimpleUserOperateRecord;
import tieba.utils.SendMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	UserServiceInterface userService;
	
	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		//System.out.println("index---------------:"+request.getAttribute("message"));
		mv.setViewName("main");
		return mv;
	}
	
	@RequestMapping(value="/getHeader")
	public ModelAndView getHeader(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		UserBasic ub = userService.getUserBasic(request, response);		
		mv.addObject("user",ub);
		mv.setViewName("header");
		return mv;	
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response){
		SendMessage.remove(request);
		ModelAndView mv = new ModelAndView();
		request.getSession().removeAttribute("USERID");
		mv.setViewName("redirect:/login/loginPage");
		return mv;
	}
	
	/***
 	 * http://localhost:8080/TIEBAMODE/user/getUserOperateRecord
 	 * 根据用户ID获得其最近20条操作记录
	 */
	@RequestMapping(value="/getUserOperateRecord")
	public ModelAndView getUserOperateRecord(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		List<SimpleUserOperateRecord> simpleUserOperateRecords = userService.getUserOperateRecordByUserId(request, response);
		if(simpleUserOperateRecords==null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			this.json = gson.toJson("no this user");
			setData(request);
			return null;
		}else{
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//			this.json = gson.toJson(simpleUserOperateRecords);
//			setData(request);
			mv.addObject("records", simpleUserOperateRecords);
			mv.setViewName("operateRecord");
		}
		return mv;
	}

	
	/***
 	 * http://localhost:8080/TIEBAMODE/user/getUserDetailByUserId
 	 * 根据用户ID获得其详细信息		并根据生日更新其年龄
 	 * 包括：
 	 * 		用户ID,名称,邮箱,头像,性别,用户等级,经验,生日,年龄,电话号码,签名,介绍,血型,工作,公司,出生地,居住地
	 */
	@RequestMapping(value="/getUserDetailByUserId", method=RequestMethod.GET)
	public ModelAndView getUserDetailByUserId(HttpServletRequest request, HttpServletResponse response){
		SimpleUserDetail simpleUserDetail = userService.getUserDetailByUserId(request, response);
		ModelAndView mv = new ModelAndView();
		if(simpleUserDetail==null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			this.json = gson.toJson("no this user");
			setData(request);
			return null;
		}else{
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//			this.json = gson.toJson(simpleUserDetail);
//			System.out.println(json);
//			setData(request);
			Integer sessionUserid = (Integer) request.getSession().getAttribute("USERID");
			Integer requsetUserid = Integer.parseInt(request.getParameter("USERID"));
			if(sessionUserid == requsetUserid){
				mv.addObject("isEditable", true);
			}else{
				mv.addObject("isEditable", false);
			}
			mv.addObject("userDetail",simpleUserDetail);
			mv.setViewName("userInfo");
		}
		return mv; 
		
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/user/updateUserDetail
	 * 根据用户ID修改其详细信息
	 * 可能包括：
	 * 		邮箱,头像,性别,生日,电话号码,签名,介绍,血型,工作,公司,出生地,居住地
	 */
	@RequestMapping(value="/updateUserDetail", method=RequestMethod.POST)
	public ModelAndView updateUserDetail(HttpServletRequest request, HttpServletResponse response) {
		String result = userService.updateUserDetail(request, response);
		ModelAndView mv = new ModelAndView();
		if(result.equals("userDetail update success")){
			mv.setViewName("redirect:/user/getUserDetailByUserId?USERID="+request.getSession().getAttribute("USERID"));
		}else{
			//TODO  根据用户ID修改其详细信息失败  跳到错误页面
		}
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		this.json = gson.toJson(result);
//		setData(request);
		return mv;
	}

	/***
	 * http://localhost:8080/TIEBAMODE/user/atSomeone
	 * @某个用户
	 * 源：Session中的USERID
	 * 目标：POST中的targetUser
	 * 内容：POST中的msg
	 */
	@RequestMapping(value="/atSomeone", method=RequestMethod.POST)
	public void atSomeone(HttpServletRequest req, HttpServletResponse resp) {
		String result = userService.insertAtsomeMsg(req, resp);
		
		Integer userid = Integer.parseInt(req.getParameter("targetUser"));
		SendMessage.atSomeone(userid);

		System.out.println(
					"user: "+req.getSession().getAttribute("USERID") +
					"-->target: "+userid+
					"\nmsg: "+req.getParameter("msg")
					
				);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		this.json = gson.toJson(result);
		setData(req);
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/user/getAt?flag=send
	 * 查看某个用户自己的@ flag：send发出的->true，receive：收到的->false
	 */
	@RequestMapping(value="/getAt", method=RequestMethod.GET)
	public void getAt(HttpServletRequest req, HttpServletResponse resp) {
		Boolean flag = false;
		List<SimpleMsgAt> msgAts = null;
		if(req.getParameter("flag").equals("send")){
			flag = true;
		}else if(req.getParameter("flag").equals("receive")){
			flag = false;
		}else{
			return;
		}
		msgAts = userService.getMsgAtByUserId(req, resp, flag);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		this.json = gson.toJson(msgAts);
		setData(req);
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/user/getOnlineUserData
	 * 获取当前在线用户的信息（点击左上角的Online:x后请求该方法）
	 */
	@RequestMapping(value="/getOnlineUserData", method=RequestMethod.GET)
	public void getOnlineUserData(HttpServletRequest req, HttpServletResponse resp){
		List<SimpleUserBasic> subs = userService.getUserBasicSimpleData();
//		for(SimpleUserBasic each : subs){
//			System.out.println(each.getUserid()+"|"+each.getUsername()+"|"+each.getIcon()+"|"+each.getUserLevel());
//		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		this.json = gson.toJson(subs);
		setData(req);
	}
	
//START--------------------------------------消息系统----------------------------------------
	/***
	 * http://localhost:8080/TIEBAMODE/user/sendMessageTo
	 */
	@RequestMapping(value="/sendMessageTo", method=RequestMethod.GET)
	public void sendMessageTo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		SendMessage.send(req);
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/user/getMessageFrom
	 */
	@RequestMapping(value="/getMessageFrom", method=RequestMethod.GET)
	public void getMessageFrom(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		SendMessage.get(req, resp);
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/user/readMseeage
	 */
	@RequestMapping(value="/readMseeage", method=RequestMethod.GET)
	public void readMseeage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		SendMessage.read(req);
	}
//END----------------------------------------消息系统----------------------------------------

	

	
	
}
