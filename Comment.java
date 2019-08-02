import java.util.LinkedList;
import java.util.List;

public class Comment {

	private int commentId;

	private String content;

	private List<String> replies;

	private int blogId;

	public Comment(int commentId,String content,int blogId){
		this.commentId = commentId;
		this.content = content;
		this.blogId = blogId;
		this.replies = new LinkedList<>();
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<String> getReplies() {
		return replies;
	}

	public void reply(String reply){
	   this.replies.add(reply);	
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	
	public void display(){
		System.out.println("Comment Id : "+commentId);
		System.out.println("Content : "+content);
		System.out.println("Replies : ");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		for(String reply : replies){
			System.out.println(reply);
			System.out.println("***********************************************************************************");
		}
	}
}