package tieba.entity;

public class TieziReplyId implements java.io.Serializable {

	// Fields

	private Tiezi tiezi;
	private Integer floorId;

	// Constructors

	/** default constructor */
	public TieziReplyId() {
	}

	/** full constructor */
	public TieziReplyId(Tiezi tiezi, Integer floorId) {
		this.tiezi = tiezi;
		this.floorId = floorId;
	}

	// Property accessors

	public Tiezi getTiezi() {
		return this.tiezi;
	}

	public void setTiezi(Tiezi tiezi) {
		this.tiezi = tiezi;
	}

	public Integer getFloorId() {
		return this.floorId;
	}

	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TieziReplyId))
			return false;
		TieziReplyId castOther = (TieziReplyId) other;

		return ((this.getTiezi() == castOther.getTiezi()) || (this.getTiezi() != null
				&& castOther.getTiezi() != null && this.getTiezi().equals(
				castOther.getTiezi())))
				&& ((this.getFloorId() == castOther.getFloorId()) || (this
						.getFloorId() != null && castOther.getFloorId() != null && this
						.getFloorId().equals(castOther.getFloorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getTiezi() == null ? 0 : this.getTiezi().hashCode());
		result = 37 * result
				+ (getFloorId() == null ? 0 : this.getFloorId().hashCode());
		return result;
	}

}