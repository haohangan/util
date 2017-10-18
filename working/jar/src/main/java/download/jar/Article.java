package download.jar;

public class Article {
	private long ID;
	private String ARTICLE;
	private String CONTENT;

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getARTICLE() {
		return ARTICLE;
	}

	public void setARTICLE(String aRTICLE) {
		ARTICLE = aRTICLE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	@Override
	public String toString() {
		return "Article [ID=" + ID + ", ARTICLE=" + ARTICLE + ", CONTENT=" + CONTENT + "]";
	}

}
