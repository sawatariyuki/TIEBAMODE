package tieba.entity;

import java.util.HashSet;
import java.util.Set;

public class UserBasic implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String username;
	private String password;
	private String email;
	private String icon;
	private String gender;
	private Integer userLevel;
	private Integer exp;
	private Set userOperateRecords = new HashSet(0);
	private Set userDetails = new HashSet(0);
	private Set tiezis = new HashSet(0);
	private Set tieziReplies = new HashSet(0);

	// Constructors

	/** default constructor */
	public UserBasic() {
	}

	/** minimal constructor */
	public UserBasic(Integer userid, String username, String password,
			String icon, String gender, Integer userLevel, Integer exp) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.icon = icon;
		this.gender = gender;
		this.userLevel = userLevel;
		this.exp = exp;
	}

	/** full constructor */
	public UserBasic(Integer userid, String username, String password,
			String email, String icon, String gender, Integer userLevel,
			Integer exp, Set userOperateRecords, Set userDetails,
			Set tiezis, Set tieziReplies) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.icon = icon;
		this.gender = gender;
		this.userLevel = userLevel;
		this.exp = exp;
		this.userOperateRecords = userOperateRecords;
		this.userDetails = userDetails;
		this.tiezis = tiezis;
		this.tieziReplies = tieziReplies;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	public Integer getExp() {
		return this.exp;
	}

	public void setExp(Integer exp) {
		this.exp = exp;
	}

	public Set getUserOperateRecords() {
		return this.userOperateRecords;
	}

	public void setUserOperateRecords(Set userOperateRecords) {
		this.userOperateRecords = userOperateRecords;
	}

	public Set getUserDetails() {
		return this.userDetails;
	}

	public void setUserDetails(Set userDetails) {
		this.userDetails = userDetails;
	}

	public Set getTiezis() {
		return this.tiezis;
	}

	public void setTiezis(Set tiezis) {
		this.tiezis = tiezis;
	}

	public Set getTieziReplies() {
		return this.tieziReplies;
	}

	public void setTieziReplies(Set tieziReplies) {
		this.tieziReplies = tieziReplies;
	}

}