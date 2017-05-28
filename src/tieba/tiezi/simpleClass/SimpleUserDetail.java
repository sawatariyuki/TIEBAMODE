package tieba.tiezi.simpleClass;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class SimpleUserDetail implements java.io.Serializable {
	// Fields
	private Integer userid;
	private String username;	//
	private String email;		//
	private String icon;		
	private String gender;		//
	private Integer userLevel;	//
	private Integer exp;		//
	private Timestamp birthday;	//
	private Integer age;		//
	private BigDecimal phoneNum;//
	private String sign;		//
	private String intro;		//
	private String bloodType;	//
	private String occupation;	//
	private String company;		//
	private String birthLand;	//
	private String liveLand;	//

	// Constructors
	/** default constructor */
	public SimpleUserDetail() {
	}
	/** full constructor */
	public SimpleUserDetail(Integer userid, String username, String email,
			String icon, String gender, Integer userLevel, Integer exp,
			Timestamp birthday, Integer age, BigDecimal phoneNum, String sign,
			String intro, String bloodType, String occupation, String company,
			String birthLand, String liveLand) {
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.icon = icon;
		this.gender = gender;
		this.userLevel = userLevel;
		this.exp = exp;
		this.birthday = birthday;
		this.age = age;
		this.phoneNum = phoneNum;
		this.sign = sign;
		this.intro = intro;
		this.bloodType = bloodType;
		this.occupation = occupation;
		this.company = company;
		this.birthLand = birthLand;
		this.liveLand = liveLand;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
	public Timestamp getBirthday() {
		return birthday;
	}
	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public BigDecimal getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(BigDecimal phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBirthLand() {
		return birthLand;
	}
	public void setBirthLand(String birthLand) {
		this.birthLand = birthLand;
	}
	public String getLiveLand() {
		return liveLand;
	}
	public void setLiveLand(String liveLand) {
		this.liveLand = liveLand;
	}
}
