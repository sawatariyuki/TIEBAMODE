package tieba.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Tiezi implements java.io.Serializable {

	// Fields

	private Integer tieziId;
	private UserBasic userBasic;
	private String title;
	private Timestamp postTime;
	private Integer viewNum;
	private Timestamp latestReplyTime;
	private Integer maxFloorId;
	private Set tieziReplies = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tiezi() {
	}

	/** minimal constructor */
	public Tiezi(Integer tieziId, UserBasic userBasic, String title,
			Timestamp postTime, Integer viewNum) {
		this.tieziId = tieziId;
		this.userBasic = userBasic;
		this.title = title;
		this.postTime = postTime;
		this.viewNum = viewNum;
	}

	/** full constructor */
	public Tiezi(Integer tieziId, UserBasic userBasic, String title,
			Timestamp postTime, Integer viewNum, Timestamp latestReplyTime,
			Integer maxFloorId, Set tieziReplies) {
		this.tieziId = tieziId;
		this.userBasic = userBasic;
		this.title = title;
		this.postTime = postTime;
		this.viewNum = viewNum;
		this.latestReplyTime = latestReplyTime;
		this.maxFloorId = maxFloorId;
		this.tieziReplies = tieziReplies;
	}

	// Property accessors

	public Integer getTieziId() {
		return this.tieziId;
	}

	public void setTieziId(Integer tieziId) {
		this.tieziId = tieziId;
	}

	public UserBasic getUserBasic() {
		return this.userBasic;
	}

	public void setUserBasic(UserBasic userBasic) {
		this.userBasic = userBasic;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getPostTime() {
		return this.postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public Integer getViewNum() {
		return this.viewNum;
	}

	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}

	public Set getTieziReplies() {
		return this.tieziReplies;
	}

	public void setTieziReplies(Set tieziReplies) {
		this.tieziReplies = tieziReplies;
	}

	public Timestamp getLatestReplyTime() {
		return latestReplyTime;
	}

	public void setLatestReplyTime(Timestamp latestReplyTime) {
		this.latestReplyTime = latestReplyTime;
	}

	public Integer getMaxFloorId() {
		return maxFloorId;
	}

	public void setMaxFloorId(Integer maxFloorId) {
		this.maxFloorId = maxFloorId;
	}

}