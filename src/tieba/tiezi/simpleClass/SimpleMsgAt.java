package tieba.tiezi.simpleClass;

import java.sql.Timestamp;

public class SimpleMsgAt implements java.io.Serializable {
	// Fields
	private Integer src_userid;
	private String src_username;
	private Integer src_userLevel;
	private String src_icon;
	private String src_sign;
	
	private Integer dest_userid;
	private String dest_username;
	private Integer dest_userLevel;
	private String dest_icon;
	private String dest_sign;
	
	private String message;
	private Timestamp atTime;
	private Integer isread;

	// Constructors
	/** default constructor */
	public SimpleMsgAt() {
	}
	/** full constructor */
	public SimpleMsgAt(Integer src_userid, String src_username,
			Integer src_userLevel, String src_icon, String src_sign,
			Integer dest_userid, String dest_username, Integer dest_userLevel,
			String dest_icon, String dest_sign, String message,
			Timestamp atTime, Integer isread) {
		this.src_userid = src_userid;
		this.src_username = src_username;
		this.src_userLevel = src_userLevel;
		this.src_icon = src_icon;
		this.src_sign = src_sign;
		this.dest_userid = dest_userid;
		this.dest_username = dest_username;
		this.dest_userLevel = dest_userLevel;
		this.dest_icon = dest_icon;
		this.dest_sign = dest_sign;
		this.message = message;
		this.atTime = atTime;
		this.isread = isread;
	}
	
	// Property accessors
	public Integer getSrc_userid() {
		return src_userid;
	}
	public void setSrc_userid(Integer src_userid) {
		this.src_userid = src_userid;
	}
	public String getSrc_username() {
		return src_username;
	}
	public void setSrc_username(String src_username) {
		this.src_username = src_username;
	}
	public Integer getSrc_userLevel() {
		return src_userLevel;
	}
	public void setSrc_userLevel(Integer src_userLevel) {
		this.src_userLevel = src_userLevel;
	}
	public String getSrc_icon() {
		return src_icon;
	}
	public void setSrc_icon(String src_icon) {
		this.src_icon = src_icon;
	}
	public String getSrc_sign() {
		return src_sign;
	}
	public void setSrc_sign(String src_sign) {
		this.src_sign = src_sign;
	}
	public Integer getDest_userid() {
		return dest_userid;
	}
	public void setDest_userid(Integer dest_userid) {
		this.dest_userid = dest_userid;
	}
	public String getDest_username() {
		return dest_username;
	}
	public void setDest_username(String dest_username) {
		this.dest_username = dest_username;
	}
	public Integer getDest_userLevel() {
		return dest_userLevel;
	}
	public void setDest_userLevel(Integer dest_userLevel) {
		this.dest_userLevel = dest_userLevel;
	}
	public String getDest_icon() {
		return dest_icon;
	}
	public void setDest_icon(String dest_icon) {
		this.dest_icon = dest_icon;
	}
	public String getDest_sign() {
		return dest_sign;
	}
	public void setDest_sign(String dest_sign) {
		this.dest_sign = dest_sign;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getAtTime() {
		return atTime;
	}
	public void setAtTime(Timestamp atTime) {
		this.atTime = atTime;
	}
	public Integer getIsread() {
		return isread;
	}
	public void setIsread(Integer isread) {
		this.isread = isread;
	}
}
