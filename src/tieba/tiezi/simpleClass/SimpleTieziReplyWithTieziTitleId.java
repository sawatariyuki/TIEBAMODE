package tieba.tiezi.simpleClass;

import java.sql.Timestamp;


public class SimpleTieziReplyWithTieziTitleId implements java.io.Serializable {
	// Fields
	private Integer tieziId;
	private String title;
	private Integer floorId;
	private String contentText;
	private String contentImg;
	private Timestamp replyTime;
	private String replyDevice;
	private String replyLoc;

	
	// Constructors
	/** default constructor */
	public SimpleTieziReplyWithTieziTitleId() {
	}
	/** full constructor */
	public SimpleTieziReplyWithTieziTitleId(Integer tieziId, String title,
			Integer floorId, String contentText, String contentImg,
			Timestamp replyTime, String replyDevice, String replyLoc) {
		this.tieziId = tieziId;
		this.title = title;
		this.floorId = floorId;
		this.contentText = contentText;
		this.contentImg = contentImg;
		this.replyTime = replyTime;
		this.replyDevice = replyDevice;
		this.replyLoc = replyLoc;
	}
	
	// Property accessors
	public Integer getTieziId() {
		return tieziId;
	}
	public void setTieziId(Integer tieziId) {
		this.tieziId = tieziId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getFloorId() {
		return floorId;
	}
	public void setFloorId(Integer floorId) {
		this.floorId = floorId;
	}
	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public String getContentImg() {
		return contentImg;
	}
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
	public Timestamp getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Timestamp replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyDevice() {
		return replyDevice;
	}
	public void setReplyDevice(String replyDevice) {
		this.replyDevice = replyDevice;
	}
	public String getReplyLoc() {
		return replyLoc;
	}
	public void setReplyLoc(String replyLoc) {
		this.replyLoc = replyLoc;
	}
}
