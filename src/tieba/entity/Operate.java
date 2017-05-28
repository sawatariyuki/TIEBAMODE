package tieba.entity;

import java.util.HashSet;
import java.util.Set;

public class Operate implements java.io.Serializable {

	// Fields

	private Integer operateId;
	private String operateName;
	private Integer exp;
	private Set userOperateRecords = new HashSet(0);

	// Constructors

	/** default constructor */
	public Operate() {
	}

	/** minimal constructor */
	public Operate(Integer operateId, String operateName, Integer exp) {
		this.operateId = operateId;
		this.operateName = operateName;
		this.exp = exp;
	}

	/** full constructor */
	public Operate(Integer operateId, String operateName, Integer exp,
			Set userOperateRecords) {
		this.operateId = operateId;
		this.operateName = operateName;
		this.exp = exp;
		this.userOperateRecords = userOperateRecords;
	}

	// Property accessors

	public Integer getOperateId() {
		return this.operateId;
	}

	public void setOperateId(Integer operateId) {
		this.operateId = operateId;
	}

	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
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

}