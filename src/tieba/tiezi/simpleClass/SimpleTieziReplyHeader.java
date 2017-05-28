package tieba.tiezi.simpleClass;

import java.util.List;

public class SimpleTieziReplyHeader implements java.io.Serializable {
	// Fields
	private Integer tieziId;
	private String title;
	private Integer viewNum;
	private Integer replyNum;	// 回帖数
	
	List<SimpleTieziReply> simpleTieziReplyList;
	
	// Constructors
	/** default constructor */
	public SimpleTieziReplyHeader(){
	}
	/** full constructor */
	public SimpleTieziReplyHeader(Integer tieziId, String title,
			Integer viewNum, Integer replyNum,
			List<SimpleTieziReply> simpleTieziReplyList) {
		this.tieziId = tieziId;
		this.title = title;
		this.viewNum = viewNum;
		this.replyNum = replyNum;
		this.simpleTieziReplyList = simpleTieziReplyList;
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
	public Integer getViewNum() {
		return viewNum;
	}
	public void setViewNum(Integer viewNum) {
		this.viewNum = viewNum;
	}
	public Integer getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}
	public List<SimpleTieziReply> getSimpleTieziReplyList() {
		return simpleTieziReplyList;
	}
	public void setSimpleTieziReplyList(List<SimpleTieziReply> simpleTieziReplyList) {
		this.simpleTieziReplyList = simpleTieziReplyList;
	}
}
