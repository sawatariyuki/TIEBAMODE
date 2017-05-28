package tieba.tiezi.simpleClass;

import java.sql.Timestamp;

public class SimpleUserOperateRecord implements java.io.Serializable {
	// Fields
	private String operateName;
	private Timestamp operateTime;
	private String ipAddress;
	private Integer exp;
	
	// Constructors
	/** default constructor */
	public SimpleUserOperateRecord() {
	}
	/** full constructor */
	public SimpleUserOperateRecord(String operateName, Timestamp operateTime, String ipAddress, Integer exp) {
		this.operateName = operateName;
		this.operateTime = operateTime;
		this.ipAddress = ipAddress;
		this.exp = exp;
	}
	
	// Property accessors
	public String getOperateName() {
		return operateName;
	}
	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}
	public Timestamp getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Integer getExp() {
		return exp;
	}
	public void setExp(Integer exp) {
		this.exp = exp;
	}
}
