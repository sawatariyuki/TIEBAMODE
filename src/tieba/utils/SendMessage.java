package tieba.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendMessage {
	/**
	 * UserState中： msgNum 表示新消息数，0表示没有新消息 isUserhasloggedin 表示用户是否已经登录 isAted
	 * 表示state_beAted状态
	 */
	private static Map<Integer, UserState> map = new HashMap<>();

	private static SendMessage instance;

	/**
	 * singleton
	 */
	private SendMessage() {
	}

	public static SendMessage getInstance() {
		if (instance == null) {
			synchronized (SendMessage.class) {
				if (instance == null) {
					instance = new SendMessage();
				}
			}
		}
		return instance;
	}

	/***
	 * 有新消息时，将所有人的状态都改为state_new
	 */
	public static void send(HttpServletRequest req) {
		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		getInstance();
		Iterator iterator = SendMessage.map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			if ((Integer) entry.getKey() != userid) {
				((UserState) entry.getValue()).setMsgNum(((UserState) entry
						.getValue()).getMsgNum() + 1);
			}
		}
	}

	/***
	 * 有人查看是否有新消息时，根据其Session中的USERID查找状态
	 */
	public static void get(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		getMapInfo();

		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		getInstance();
		UserState us = SendMessage.map.get(userid);
		String msg = "";
		if (us.getIsUserhasloggedin()) {
			msg = "账号已在别处登录|";
		} else {
			if (us.getMsgNum() != 0) {
				msg += "你有" + us.getMsgNum() + "个新动态";
			}
			msg += "|";
			if (us.getIsAted()) {
				msg += "new!";
			}
		}

		msg += "|" + SendMessage.map.size();

		// System.out.println("=================================================="+msg);

		resp.setContentType("text/event-stream");
		resp.getWriter().write("data:" + msg + "\n\n");
	}

	/***
	 * 有人登录时，查看其是否在map中， 若在，则其isUserhasloggedin改为true；若不在，则插入
	 */
	public static void add(HttpServletRequest req) {
		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		getInstance();
		if (SendMessage.map.containsKey(userid)) {
			SendMessage.map.get(userid).setIsUserhasloggedin(true);
		} else {
			SendMessage.map.put(userid, new UserState());
		}
	}

	/***
	 * 有人F5刷新时，根据其Session中的USERID将其msgNum改为0
	 */
	public static void read(HttpServletRequest req) throws IOException {
		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		getInstance();
		if (SendMessage.map.containsKey(userid)) {
			SendMessage.map.get(userid).setMsgNum(0);
		}
	}

	/***
	 * 有人@时，根据userid将对应user的isAted改为true
	 */
	public static void atSomeone(Integer userid) {
		getInstance();
		if (SendMessage.map.containsKey(userid)) {
			SendMessage.map.get(userid).setIsAted(true);
		}
	}

	/***
	 * 被@者查看@信息后，根据其Session中的USERID将其isAted改为false
	 */
	public static void readAt(HttpServletRequest req) {
		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		getInstance();
		if (SendMessage.map.containsKey(userid)) {
			SendMessage.map.get(userid).setIsAted(false);
		}
	}

	/***
	 * 用户退出时remove该用户
	 */
	public static void remove(HttpServletRequest req) {
		Integer userid = (Integer) req.getSession().getAttribute("USERID");
		getInstance();
		SendMessage.map.remove(userid);
	}

	/***
	 * 判断用户是否已在map中
	 */
	public static Boolean isUserHasLoggedIn(int userid) {
		if (SendMessage.map.containsKey(userid)) {
			return true;
		}
		return false;
	}

	/***
	 * 定时（服务器时间）清空一次map 暂时定为 每天凌晨4点 参数（注意顺序）：
	 * 秒0-59、分0-59、小时0-23、日期1-31、月份1-12或JAN-DEC、星期1-7或SUN-SAT、年（可选）留空或1970-2099
	 */
	@Scheduled(cron = "0 0 4 * * ?")
	public static void removeAll() {
		SendMessage.map.clear();
		System.out
				.println("SendMseeage->removeAll->run clear: clear all users in map");
	}

	/***
	 * 返回map中的所有用户id
	 */
	public static Set<Integer> getAllUserIdInMap() {
		return SendMessage.map.keySet();
	}

	/***
	 * Console显示map的大小和数据
	 */
	public static void getMapInfo() {
		System.out.println("map size: " + SendMessage.map.size());
		getInstance();
		Iterator iterator = SendMessage.map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry entry = (Map.Entry) iterator.next();
			System.out.println("userid: " + entry.getKey());
			((UserState) entry.getValue()).showInfo();
		}
		System.out.println("---------------------------------------------");
	}
}
