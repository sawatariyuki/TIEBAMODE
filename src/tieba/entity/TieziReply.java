package tieba.entity;

import java.sql.Timestamp;

public class TieziReply implements java.io.Serializable {

	// Fields

	private TieziReplyId id;
	private UserBasic userBasic;
	private Timestamp replyTime;
	private String contentText;
	private String contentImg;
	private String replyDevice;
	private String replyLoc;

	// Constructors

	/** default constructor */
	public TieziReply() {
	}

	/** minimal constructor */
	public TieziReply(TieziReplyId id, UserBasic userBasic, Timestamp replyTime) {
		this.id = id;
		this.userBasic = userBasic;
		this.replyTime = replyTime;
	}

	/** full constructor */
	public TieziReply(TieziReplyId id, UserBasic userBasic, Timestamp replyTime,
			String contentText, String contentImg, String replyDevice,
			String replyLoc) {
		this.id = id;
		this.userBasic = userBasic;
		this.replyTime = replyTime;
		this.contentText = contentText;
		this.contentImg = contentImg;
		this.replyDevice = replyDevice;
		this.replyLoc = replyLoc;
	}

	// Property accessors

	public TieziReplyId getId() {
		return this.id;
	}

	public void setId(TieziReplyId id) {
		this.id = id;
	}

	public UserBasic getUserBasic() {
		return this.userBasic;
	}

	public void setUserBasic(UserBasic userBasic) {
		this.userBasic = userBasic;
	}

	public Timestamp getReplyTime() {
		return this.replyTime;
	}

	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}

	public String getContentText() {
		return this.contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getContentImg() {
		return this.contentImg;
	}

	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}

	public String getReplyDevice() {
		return this.replyDevice;
	}

	public void setReplyDevice(String replyDevice) {
		this.replyDevice = replyDevice;
	}

	public String getReplyLoc() {
		return this.replyLoc;
	}

	public void setReplyLoc(String replyLoc) {
		this.replyLoc = replyLoc;
	}

}