import java.sql.Date;

public class SportsBlog extends Blog{
	
	private SportsType sportsType;

	public SportsBlog(int blogId, int createdByUserId, Date createdDate, String content,SportsType sportsType) {
		super(blogId, createdByUserId, createdDate, content);
		this.sportsType = sportsType;		
	}

	public SportsType getSportsType() {
		return sportsType;
	}

	public void setSportsType(SportsType sportsType) {
		this.sportsType = sportsType;
	}

}