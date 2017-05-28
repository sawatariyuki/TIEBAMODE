package tieba.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tieba.service.TieziServiceInterface;
import tieba.tiezi.simpleClass.SimpleTiezi;
import tieba.tiezi.simpleClass.SimpleTieziReplyHeader;
import tieba.tiezi.simpleClass.SimpleTieziReplyWithTieziTitleId;
import tieba.utils.CusAccessObjectUtil;
import tieba.utils.SendMessage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/tiezi")
public class TieziController extends BaseController{
	@Autowired
	TieziServiceInterface tieziService;
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/getAllTiezi
	 * 获得所有帖子的基本信息
	 * 内容:
	 * tieziId title postTime viewNum replyNum 
	 * contentText contentImg replyDevice replyLoc 
	 * userid username userLevel icon
	 */
	@RequestMapping(value="/getAllTiezi")
	public ModelAndView getAllTiezi(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		List<SimpleTiezi> simpleTieziList = tieziService.getAllTiezi(req, resp);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		this.json = gson.toJson(simpleTieziList);
//		System.out.println(json);
//		setData(req);
		//读掉新信息
		SendMessage.read(req);
		ModelAndView mv = new ModelAndView();
		mv.addObject("tieziList",simpleTieziList);
		mv.setViewName("index");
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/getTieziRepliesByTieziId
	 * 获得点击某个帖子后获得该帖子的所有回帖
	 * GET请求中用  tiezi_id 来表示帖子ID
	 * 内容:
	 * tieziId title viewNum replyNum 
	 * simpleTieziReplyList:
	 * [floorId contentText contentImg replyTime replyDevice replyLoc userid username userLevel icon]
	 */
	@RequestMapping(value="/getTieziRepliesByTieziId")
	public ModelAndView getTieziRepliesByTieziId(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ModelAndView mv = new ModelAndView();
		SimpleTieziReplyHeader simpleTieziReplyHeader = tieziService.getTieziRepliesByTieziId(req, resp);
		if(simpleTieziReplyHeader==null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			this.json = gson.toJson("cannot find this tiezi");
//			setData(req);
		}else{
			//读掉新信息
			SendMessage.read(req);
			mv.addObject("reply",simpleTieziReplyHeader);
			mv.setViewName("tiezi");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			this.json = gson.toJson(simpleTieziReplyHeader);
		}
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/addReplyToTiezi
	 * 向数据库中插入一个回帖
	 * 返回执行状态
	 */
	@RequestMapping(value="/addReplyToTiezi", method=RequestMethod.POST)
	public ModelAndView addReplyToTiezi(HttpServletRequest req, HttpServletResponse resp) {
		String result = tieziService.addReplyToTiezi(req, resp);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		this.json = gson.toJson(result);
//		setData(req);
		//有新信息
		SendMessage.send(req);
		ModelAndView mv = new ModelAndView();
		String tieziId = (String) req.getParameter("tiezi_id");
		if(result.equals("reply insert success")){
			mv.setViewName("redirect:/tiezi/getTieziRepliesByTieziId?tiezi_id=" + tieziId);
		}else{
			mv.setViewName("redirect:/tiezi/getTieziRepliesByTieziId?tiezi_id=" + tieziId);
		}
		mv.addObject("message",result);
		return mv;
		
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/addTiezi
	 * 向数据库中插入一个帖子和一楼的回帖
	 * 返回执行状态
	 */
	@RequestMapping(value="/addTiezi", method=RequestMethod.POST)
	public ModelAndView addTiezi(HttpServletRequest req, HttpServletResponse resp) {
		String result = tieziService.addTiezi(req, resp);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		this.json = gson.toJson(result);
//		setData(req);
		//有新信息
		SendMessage.send(req);
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",result);
		mv.setViewName("redirect:/tiezi/getAllTiezi");
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/getAllTieziByUserId
	 * 根据用户ID获得他的所有发帖（帖子的基本信息）
	 */
	@RequestMapping(value="/getAllTieziByUserId")
	public ModelAndView getAllTieziByUserId(HttpServletRequest req, HttpServletResponse resp) {
		List<SimpleTiezi> simpleTieziList = tieziService.getAllTieziByUserId(req, resp);
		ModelAndView mv = new ModelAndView();
		if(simpleTieziList==null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			this.json = gson.toJson("no this user");
//			setData(req);
		}else{
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//			this.json = gson.toJson(simpleTieziList);
//			setData(req);
			Integer sessionUserid = (Integer) req.getSession().getAttribute("USERID");
			Integer requsetUserid = Integer.parseInt(req.getParameter("userId"));
			if(sessionUserid == requsetUserid){
				mv.addObject("isEditable", true);
			}else{
				mv.addObject("isEditable", false);
			}
			mv.addObject("tieziList", simpleTieziList);
			mv.setViewName("tieziPosted");
		}
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/getTieziReplyByUserId
	 * 根据用户ID获得他的全部回帖（包括对应帖子的ID、title） 按时间最新排序
	 */
	@RequestMapping(value="/getTieziReplyByUserId")
	public ModelAndView getTieziReplyByUserId(HttpServletRequest req, HttpServletResponse resp) {
		List<SimpleTieziReplyWithTieziTitleId> simpleReplyList = tieziService.getTieziReplyByUserId(req, resp);
		ModelAndView mv = new ModelAndView();
		if(simpleReplyList==null){
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			this.json = gson.toJson("no this user");
//			setData(req);
		}else{
//			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//			this.json = gson.toJson(simpleReplyList);
//			setData(req);
			Integer sessionUserid = (Integer) req.getSession().getAttribute("USERID");
			Integer requsetUserid = Integer.parseInt(req.getParameter("userId"));
			if(sessionUserid == requsetUserid){
				mv.addObject("isEditable", true);
			}else{
				mv.addObject("isEditable", false);
			}
			mv.addObject("replyList", simpleReplyList);
			mv.setViewName("replyPosted");
		}
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/deleteTiezi
	 * 根据帖子ID删除该贴（还需用户ID）
	 */
	@RequestMapping(value="/deleteTiezi")
	public ModelAndView deleteTieziByByTieziId(HttpServletRequest req, HttpServletResponse resp) {
		String result = tieziService.deleteTieziByTieziId(req, resp);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		this.json = gson.toJson(result);
//		setData(req);
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",result);
		mv.setViewName("redirect:/tiezi/getAllTieziByUserId?userId="+req.getSession().getAttribute("USERID"));
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/deleteReply
	 * 根据帖子ID、楼层ID、用户ID删除该回帖
	 */
	@RequestMapping(value="/deleteReply")
	public ModelAndView deleteTieziReplyByIds(HttpServletRequest req, HttpServletResponse resp){
		String result = tieziService.deleteTieziReplyByIds(req, resp);
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		this.json = gson.toJson(result);
//		setData(req);
		ModelAndView mv = new ModelAndView();
		mv.addObject("message",result);
		mv.setViewName("redirect:/tiezi/getTieziReplyByUserId?userId="+req.getSession().getAttribute("USERID"));
		return mv;
	}
	
	/***
	 * http://localhost:8080/TIEBAMODE/tiezi/testCode
	 * 用于测试
	 */
	@RequestMapping(value="/testCode")
	public void testMyCode(HttpServletRequest req, HttpServletResponse resp){
		Timestamp timestamp = new Timestamp(new Date().getTime());
		String ipAddress = CusAccessObjectUtil.getIpAddress(req);
		tieziService.addOperateRecord(4, timestamp, 2, ipAddress);
	}
}
