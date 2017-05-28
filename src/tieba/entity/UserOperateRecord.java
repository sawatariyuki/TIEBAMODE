package tieba.entity;

public class UserOperateRecord implements java.io.Serializable {

	// Fields

	private UserOperateRecordId id;
	private Operate operate;
	private String ipAddress;

	// Constructors

	/** default constructor */
	public UserOperateRecord() {
	}

	/** full constructor */
	public UserOperateRecord(UserOperateRecordId id, Operate operate,
			String ipAddress) {
		this.id = id;
		this.operate = operate;
		this.ipAddress = ipAddress;
	}

	// Property accessors

	public UserOperateRecordId getId() {
		return this.id;
	}

	public void setId(UserOperateRecordId id) {
		this.id = id;
	}

	public Operate getOperate() {
		return this.operate;
	}

	public void setOperate(Operate operate) {
		this.operate = operate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}