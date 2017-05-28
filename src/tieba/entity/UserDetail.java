package tieba.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class UserDetail implements java.io.Serializable {

	// Fields

	private Integer userid;
	private UserBasic userBasic;
	private Timestamp birthday;
	private Integer age;
	private BigDecimal phoneNum;
	private String sign;
	private String intro;
	private String bloodType;
	private String occupation;
	private String company;
	private String birthLand;
	private String liveLand;

	// Constructors

	/** default constructor */
	public UserDetail() {
	}

	/** minimal constructor */
	public UserDetail(Integer userid, UserBasic userBasic) {
		this.userid = userid;
		this.userBasic = userBasic;
	}

	/** full constructor */
	public UserDetail(Integer userid, UserBasic userBasic, Timestamp birthday,
			Integer age, BigDecimal phoneNum, String sign, String intro,
			String bloodType, String occupation, String company,
			String birthLand, String liveLand) {
		this.userid = userid;
		this.userBasic = userBasic;
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
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public UserBasic getUserBasic() {
		return this.userBasic;
	}

	public void setUserBasic(UserBasic userBasic) {
		this.userBasic = userBasic;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public BigDecimal getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(BigDecimal phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getBloodType() {
		return this.bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getOccupation() {
		return this.occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBirthLand() {
		return this.birthLand;
	}

	public void setBirthLand(String birthLand) {
		this.birthLand = birthLand;
	}

	public String getLiveLand() {
		return this.liveLand;
	}

	public void setLiveLand(String liveLand) {
		this.liveLand = liveLand;
	}

}