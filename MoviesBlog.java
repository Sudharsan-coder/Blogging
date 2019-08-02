import java.sql.Date;

public class MoviesBlog extends Blog{
	
	private MovieType movieType;

	public MoviesBlog(int blogId, int createdByUserId, Date createdDate, String content,MovieType movieType) {
		super(blogId, createdByUserId, createdDate, content);
		this.movieType = movieType;
	}

	public MovieType getMovieType() {
		return movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

}