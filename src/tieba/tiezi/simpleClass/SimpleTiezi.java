package tieba.tiezi.simpleClass;

import java.sql.Timestamp;

public class SimpleTiezi implements java.io.Serializable {
	// Fields
	private Integer tieziId;
	private String title;
	private Timestamp postTime;
	private Integer viewNum;
	private Integer replyNum;	// 回帖数
	
	private String contentText;
	private String contentImg;
	private String replyDevice;
	private String replyLoc;
	
	private Integer userid;
	private String username;
	private Integer userLevel;
	private String icon;
	private String sign;
	
	private Timestamp latestReplyTime;
	private String lastReplyUsername;
	private String lastReplySign;
	private Integer lastReplyUserid;
	
	// Constructors
	/** default constructor */
	public SimpleTiezi(){
	}
	
	/** full constructor */
	public SimpleTiezi(Integer tieziId, String title, Timestamp postTime,
			Integer viewNum, Integer replyNum, String contentText,
			String contentImg, String replyDevice, String replyLoc,
			Integer userid, String username, Integer userLevel, String icon, String sign,
			Timestamp latestReplyTime, String lastReplyUsername, String lastReplySign, Integer lastReplyUserid) {
		this.tieziId = tieziId;
		this.title = title;
		this.postTime = postTime;
		this.viewNum = viewNum;
		this.replyNum = replyNum;
		this.contentText = contentText;
		this.contentImg = contentImg;
		this.replyDevice = replyDevice;
		this.replyLoc = replyLoc;
		this.userid = userid;
		this.username = username;
		this.userLevel = userLevel;
		this.icon = icon;
		this.sign = sign;
		this.latestReplyTime = latestReplyTime;
		this.lastReplyUsername = lastReplyUsername;
		this.lastReplySign = lastReplySign;
		this.lastReplyUserid = lastReplyUserid;
	}
	
	// Property accessors
	public Integer getTieziId() {
		return tieziId;
	}

	public void setTieziId(Integer tieziId) {
		this.tieziId = tieziId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getPostTime() {
		return postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public Integer getViewNum() {
		return viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentImg() {
		return contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public String getReplyDevice() {
		return replyDevice;
	}

	public void setReplyDevice(String replyDevice) {
		this.replyDevice = replyDevice;
	}

	public String getReplyLoc() {
		return replyLoc;
	}

	public void setReplyLoc(String replyLoc) {
		this.replyLoc = replyLoc;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	public Timestamp getLatestReplyTime() {
		return latestReplyTime;
	}

	public void setLatestReplyTime(Timestamp latestReplyTime) {
		this.latestReplyTime = latestReplyTime;
	}

	public String getLastReplyUsername() {
		return lastReplyUsername;
	}

	public void setLastReplyUsername(String lastReplyUsername) {
		this.lastReplyUsername = lastReplyUsername;
	}

	public String getLastReplySign() {
		return lastReplySign;
	}

	public void setLastReplySign(String lastReplySign) {
		this.lastReplySign = lastReplySign;
	}

	public Integer getLastReplyUserid() {
		return lastReplyUserid;
	}

	public void setLastReplyUserid(Integer lastReplyUserid) {
		this.lastReplyUserid = lastReplyUserid;
	}
	
}
