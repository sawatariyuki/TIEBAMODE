package tieba.tiezi.simpleClass;

import java.util.HashSet;
import java.util.Set;

public class SimpleUserBasic implements java.io.Serializable {
	// Fields
	private Integer userid;
	private String username;
	private String icon;
	private Integer userLevel;

	// Constructors
	/** default constructor */
	public SimpleUserBasic() {
	}
	/** full constructor */
	public SimpleUserBasic(Integer userid, String username, String icon,
			Integer userLevel) {
		this.userid = userid;
		this.username = username;
		this.icon = icon;
		this.userLevel = userLevel;
	}
	
	// Property accessors
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
}
