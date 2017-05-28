package tieba.entity;

import java.sql.Timestamp;

public class UserOperateRecordId implements java.io.Serializable {

	// Fields

	private UserBasic userBasic;
	private Timestamp operateTime;

	// Constructors

	/** default constructor */
	public UserOperateRecordId() {
	}

	/** full constructor */
	public UserOperateRecordId(UserBasic userBasic, Timestamp operateTime) {
		this.userBasic = userBasic;
		this.operateTime = operateTime;
	}

	// Property accessors

	public UserBasic getUserBasic() {
		return this.userBasic;
	}

	public void setUserBasic(UserBasic userBasic) {
		this.userBasic = userBasic;
	}

	public Timestamp getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Timestamp operateTime) {
		this.operateTime = operateTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserOperateRecordId))
			return false;
		UserOperateRecordId castOther = (UserOperateRecordId) other;

		return ((this.getUserBasic() == castOther.getUserBasic()) || (this
				.getUserBasic() != null && castOther.getUserBasic() != null && this
				.getUserBasic().equals(castOther.getUserBasic())))
				&& ((this.getOperateTime() == castOther.getOperateTime()) || (this
						.getOperateTime() != null
						&& castOther.getOperateTime() != null && this
						.getOperateTime().equals(castOther.getOperateTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserBasic() == null ? 0 : this.getUserBasic().hashCode());
		result = 37
				* result
				+ (getOperateTime() == null ? 0 : this.getOperateTime()
						.hashCode());
		return result;
	}

}