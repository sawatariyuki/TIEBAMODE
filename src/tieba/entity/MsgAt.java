package tieba.entity;

public class MsgAt implements java.io.Serializable {

	// Fields

	private MsgAtId id;
	private String message;
	private Integer isread;

	// Constructors

	/** default constructor */
	public MsgAt() {
	}

	/** full constructor */
	public MsgAt(MsgAtId id, String message, Integer isread) {
		this.id = id;
		this.message = message;
		this.isread = isread;
	}

	// Property accessors

	public MsgAtId getId() {
		return this.id;
	}

	public void setId(MsgAtId id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getIsread() {
		return this.isread;
	}

	public void setIsread(Integer isread) {
		this.isread = isread;
	}

}