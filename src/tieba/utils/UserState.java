package tieba.utils;

public class UserState {
	/***
	 * 已经登录状态 覆盖 另外两个状态
	 * 另外两个状态彼此同级
	 */
	
	private int msgNum;					//新消息数，0表示没有新消息
	private Boolean isUserhasloggedin;	//用户是否已经登录
	private Boolean isAted;				//是否被at
	
	public UserState() {
		this.msgNum = 0;
		this.isUserhasloggedin = false;
		this.isAted = false;
	}

	public UserState(int msgNum, Boolean isUserhasloggedin, Boolean isAted) {
		this.msgNum = msgNum;
		this.isUserhasloggedin = isUserhasloggedin;
		this.isAted = isAted;
	}

	public int getMsgNum() {
		return msgNum;
	}
	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}
	public Boolean getIsAted() {
		return isAted;
	}
	public void setIsAted(Boolean isAted) {
		this.isAted = isAted;
	}

	public Boolean getIsUserhasloggedin() {
		return isUserhasloggedin;
	}

	public void setIsUserhasloggedin(Boolean isUserhasloggedin) {
		this.isUserhasloggedin = isUserhasloggedin;
	}
	
	//syso
	public void showInfo(){
		System.out.println(	"msgNum: "				+	msgNum	+
							", isUserhasloggedin: "	+	isUserhasloggedin	+
							", isAted: "			+	isAted	);
	}
}
