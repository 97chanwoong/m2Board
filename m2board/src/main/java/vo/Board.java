package vo;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContents;
	private String memberId;
	private String createDate;
	private int boardViews;
	private int boardNice;
	public Board() {
		super();
	}
	public Board(int boardNo, String boardTitle, String boardContents, String memberId, String createDate,
			int boardViews, int boardNice) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContents = boardContents;
		this.memberId = memberId;
		this.createDate = createDate;
		this.boardViews = boardViews;
		this.boardNice = boardNice;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContents() {
		return boardContents;
	}
	public void setBoardContents(String boardContents) {
		this.boardContents = boardContents;
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
	public int getBoardViews() {
		return boardViews;
	}
	public void setBoardViews(int boardViews) {
		this.boardViews = boardViews;
	}
	public int getBoardNice() {
		return boardNice;
	}
	public void setBoardNice(int boardNice) {
		this.boardNice = boardNice;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContents=" + boardContents
				+ ", memberId=" + memberId + ", createDate=" + createDate + ", boardViews=" + boardViews
				+ ", boardNice=" + boardNice + "]";
	}
	
	
}
