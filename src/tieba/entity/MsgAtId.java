package tieba.entity;

import java.sql.Timestamp;

public class MsgAtId implements java.io.Serializable {

	// Fields

	private UserBasic userBasic;
	private UserBasic userBasic_1;
	private Timestamp atTime;

	// Constructors

	/** default constructor */
	public MsgAtId() {
	}

	/** full constructor */
	public MsgAtId(UserBasic userBasic, UserBasic userBasic_1, Timestamp atTime) {
		this.userBasic = userBasic;
		this.userBasic_1 = userBasic_1;
		this.atTime = atTime;
	}

	// Property accessors

	public UserBasic getUserBasic() {
		return this.userBasic;
	}

	public void setUserBasic(UserBasic userBasic) {
		this.userBasic = userBasic;
	}

	public UserBasic getUserBasic_1() {
		return this.userBasic_1;
	}

	public void setUserBasic_1(UserBasic userBasic_1) {
		this.userBasic_1 = userBasic_1;
	}

	public Timestamp getAtTime() {
		return this.atTime;
	}

	public void setAtTime(Timestamp atTime) {
		this.atTime = atTime;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof MsgAtId))
			return false;
		MsgAtId castOther = (MsgAtId) other;

		return ((this.getUserBasic() == castOther.getUserBasic()) || (this
				.getUserBasic() != null && castOther.getUserBasic() != null && this
				.getUserBasic().equals(castOther.getUserBasic())))
				&& ((this.getUserBasic_1() == castOther.getUserBasic_1()) || (this
						.getUserBasic_1() != null
						&& castOther.getUserBasic_1() != null && this
						.getUserBasic_1().equals(castOther.getUserBasic_1())))
				&& ((this.getAtTime() == castOther.getAtTime()) || (this
						.getAtTime() != null && castOther.getAtTime() != null && this
						.getAtTime().equals(castOther.getAtTime())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getUserBasic() == null ? 0 : this.getUserBasic().hashCode());
		result = 37
				* result
				+ (getUserBasic_1() == null ? 0 : this.getUserBasic_1()
						.hashCode());
		result = 37 * result
				+ (getAtTime() == null ? 0 : this.getAtTime().hashCode());
		return result;
	}

}