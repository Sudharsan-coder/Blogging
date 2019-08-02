import java.sql.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Blog {

	private int blogId;

	private int createdByUserId;

	private Date createdDate;

	private String content;

	private int likes;

	private int dislikes;

	private Map<Integer,Comment> commentMap;
	
	public Blog(int blogId, int createdByUserId, Date createdDate, String content) {
		super();
		this.blogId = blogId;
		this.createdByUserId = createdByUserId;
		this.createdDate = createdDate;
		this.content = content;
		this.commentMap = new HashMap<>();
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public int getCreatedByUserId() {
		return createdByUserId;
	}

	public void setCreatedByUserId(int createdByUserId) {
		this.createdByUserId = createdByUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getContent() {
		return content;
	}

	public void editContent(String content) {
		this.content = content;
	}
	
	public void like(){
	   this.likes++;	
	}

	public void disLike(){
	   this.dislikes++;	
	}
	
	public int getLikes(){
	   return likes;	
	}
	
	public int getDisLikes(){
	   return dislikes;	
	}
	
	public void comment(String comment){
	   int commentId = BloggingApp.getCommentId();	
	   Comment newComment = new Comment(commentId,comment,blogId);
	   commentMap.put(commentId,newComment);
	   BloggingApp.incrementComments();
	}
	
	public Collection<Comment> getAllComments(){
	   return commentMap.values();	
	}

    public Comment getComment(int commentId){
       if(!commentMap.containsKey(commentId)){
    	   System.out.println("Sorry comment not found.");
    	   return null;
       }else{
    	   return commentMap.get(commentId);
       }
    }
}
