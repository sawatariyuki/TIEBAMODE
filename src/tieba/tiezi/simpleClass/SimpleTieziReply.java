package tieba.tiezi.simpleClass;

import java.sql.Timestamp;

public class SimpleTieziReply implements java.io.Serializable  {
	// Fields
	private Integer floorId;
	private String contentText;
	private String contentImg;
	private Timestamp replyTime;
	private String replyDevice;
	private String replyLoc;
	private Integer userid;
	private String username;
	private Integer userLevel;
	private String icon;
	private String sign;
	
	// Constructors
	/** default constructor */
	public SimpleTieziReply(){
	}
	/** full constructor */
	public SimpleTieziReply(Integer floorId, String contentText,
			String contentImg, Timestamp replyTime, String replyDevice,
			String replyLoc, Integer userid, String username,
			Integer userLevel, String icon, String sign) {
		this.floorId = floorId;
		this.contentText = contentText;
		this.contentImg = contentImg;
		this.replyTime = replyTime;
		this.replyDevice = replyDevice;
		this.replyLoc = replyLoc;
		this.userid = userid;
		this.username = username;
		this.userLevel = userLevel;
		this.icon = icon;
		this.sign = sign;
	}
	
	// Property accessors
	public Integer getFloorId() {
		return floorId;
	}
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
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
	public Timestamp getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
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
}
