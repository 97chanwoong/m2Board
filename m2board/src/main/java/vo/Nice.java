package vo;

public class Nice {
	private int boardNo;
	private String memberId;
	private String createDate;
	public Nice() {
		super();
	}
	public Nice(int boardNo, String memberId, String createDate) {
		super();
		this.boardNo = boardNo;
		this.memberId = memberId;
		this.createDate = createDate;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Nice [boardNo=" + boardNo + ", memberId=" + memberId + ", createDate=" + createDate + "]";
	}
	
}
