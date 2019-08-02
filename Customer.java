import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Customer extends User{

	private Scanner scanner = new Scanner(System.in);      

	public Customer(String userName, String password, int age, int userId) {
		super(userName, password, age, userId);
	}

	public Blog createNewBlog(String blogType,int blogId,String content){
		Blog newBlog = null;
		
		switch(blogType){
		case "CRICKET":
			newBlog = new SportsBlog(blogId,userId,new Date(System.currentTimeMillis()),content,SportsType.CRICKET);
		case "FOOTBALL":
			newBlog = new SportsBlog(blogId,userId,new Date(System.currentTimeMillis()),content,SportsType.FOOTBALL);
		case "BASEBALL":
			newBlog = new SportsBlog(blogId,userId,new Date(System.currentTimeMillis()),content,SportsType.BASEBALL);
		case "TENNIS":
			newBlog = new SportsBlog(blogId,userId,new Date(System.currentTimeMillis()),content,SportsType.TENNIS);			
		case "HORROR":
			newBlog = new MoviesBlog(blogId,userId,new Date(System.currentTimeMillis()),content,MovieType.HORROR);
		case "COMEDY":
			newBlog = new MoviesBlog(blogId,userId,new Date(System.currentTimeMillis()),content,MovieType.COMEDY);
		case "ROMANTIC":
			newBlog = new MoviesBlog(blogId,userId,new Date(System.currentTimeMillis()),content,MovieType.ROMATIC);
		case "THRILLER":
			newBlog = new MoviesBlog(blogId,userId,new Date(System.currentTimeMillis()),content,MovieType.THRILLER);
		}

		BloggingApp.addBlogForUser(userName,newBlog);

		return newBlog;
	}

	public void displayAllBlogs(){
		List<Blog> userBlogs = BloggingApp.getAllBlogs(userName);

		for(Blog blog : userBlogs){
			System.out.println("ID : "+blog.getBlogId());
			System.out.println("Content : "+blog.getContent());
			System.out.println("Likes : "+blog.getLikes());
			System.out.println("Dislikes : "+blog.getDisLikes());
			System.out.println("Comments");
			System.out.println("--------------------------------------------------------------------------------------");
			for(Comment comment : blog.getAllComments()){
				comment.display();
				System.out.println("##################################################################################");
			}
			System.out.println();
			System.out.println();
		}
	}

	public void displayBlog(int blogId){
		Blog blog = BloggingApp.getBlog(blogId);
		System.out.println("ID : "+blog.getBlogId());
		System.out.println("Content : "+blog.getContent());
		System.out.println("Likes : "+blog.getLikes());
		System.out.println("Dislikes : "+blog.getDisLikes());
		System.out.println("Comments");
		System.out.println("--------------------------------------------------------------------------------------");

		for(Comment comment : blog.getAllComments()){
			comment.display();
			System.out.println("##################################################################################");
		}
	}


	public void editBlog(int blogId,String content){
		Blog blog = BloggingApp.getBlog(blogId);
		if(blog != null){
			blog.editContent(content);
		}
	}

	public void removeBlog(int blogId){
		BloggingApp.removeBlog(blogId,userName);
	}

	public void displayAllBlogs(String userName){
		List<Blog> userBlogs = BloggingApp.getAllBlogs(userName);

		for(Blog blog : userBlogs){
			System.out.println("ID : "+blog.getBlogId());
			System.out.println("Content : "+blog.getContent());
			System.out.println("Likes : "+blog.getLikes());
			System.out.println("Dislikes : "+blog.getDisLikes());
			System.out.println("Comments");
			System.out.println("--------------------------------------------------------------------------------------");
			for(Comment comment : blog.getAllComments()){
				comment.display();
				System.out.println("##################################################################################");
			}
			System.out.println();
			System.out.println();
		}	
	}

	public void commentOnBlog(int blogId,String comment){
		Blog blog = BloggingApp.getBlog(blogId);
		blog.comment(comment);
	}
	
	public void replyForComment(int blogId,int commentId,String reply){
		Blog blog = BloggingApp.getBlog(blogId);
		if(blog != null){
		   Comment comment = blog.getComment(commentId);
		   comment.reply(reply);
		}
	}
	
	public void displayComments(int blogId){
		Blog blog = BloggingApp.getBlog(blogId);
		if(blog != null){
		   for(Comment comment : blog.getAllComments()){
			   comment.display();
		   }	
		}
	}

	public void likeBlog(String userName,int blogId){
		List<Blog> userBlogs = BloggingApp.getAllBlogs(userName);

		for(Blog blog : userBlogs){
			if(blogId == blog.getBlogId()){
				blog.like();
				return;
			}
		}
	}

	public void disLikeBlog(String userName,int blogId){
		List<Blog> userBlogs = BloggingApp.getAllBlogs(userName);

		for(Blog blog : userBlogs){
			if(blogId == blog.getBlogId()){
				blog.disLike();
				return;
			}
		}
	}

	@Override
	void displayFeaturesAndDoOperations(){
		boolean temp=true;
		while(temp){
			System.out.println("1.Create New Blog");
			System.out.println("2.Display All Blogs");
			System.out.println("3.Display Specific Blog");
			System.out.println("4.Edit Content In Blog");
			System.out.println("5.Remove Blog");
			System.out.println("6.Display Other Users Blogs");
			System.out.println("7.Like Blog");
			System.out.println("8.Dislike Blog");
			System.out.println("9.Comment on blog");
			System.out.println("10.Reply to comment");
			System.out.println("11.Display Comments");
			System.out.println("12.Exit");

			System.out.println("Please enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch(choice){			
			case 1 : 
				System.out.println("Blog Type : ");
				String blogType = scanner.nextLine();
				System.out.println("Content : ");
				String content = scanner.nextLine();
				createNewBlog(blogType,BloggingApp.getBlogId(),content);
				break;

			case 2 : 
				displayAllBlogs();
				break;

			case 3 :
				System.out.println("Blog Id : ");
				int blogId = scanner.nextInt();
				scanner.nextLine();
				displayBlog(blogId);
				break;

			case 4 : 
				System.out.println("Blog Id : ");
				int blogIdToEdit = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Content : ");
				String newContent = scanner.nextLine();
				editBlog(blogIdToEdit,newContent);
				break;

			case 5 : 
				System.out.println("Blog Id : ");
				int blogIdToRemove = scanner.nextInt();
				scanner.nextLine();
				removeBlog(blogIdToRemove);
				break;

			case 6 : 
				System.out.println("User Name : ");
				String userNameToViewBlogs = scanner.nextLine();
				displayAllBlogs(userNameToViewBlogs);
				break;

			case 7 : 
				System.out.println("User Name : ");
				String userNameToLikeBlog = scanner.nextLine();
				System.out.println("Blog Id : ");
				int blogIdToLike = scanner.nextInt();
				scanner.nextLine();
				likeBlog(userNameToLikeBlog,blogIdToLike);
				break;

			case 8 : 
				System.out.println("User Name : ");
				String userNameToDisLikeBlog = scanner.nextLine();
				System.out.println("Blog Id : ");
				int blogIdToDisLike = scanner.nextInt();
				scanner.nextLine();
				disLikeBlog(userNameToDisLikeBlog,blogIdToDisLike);;
				break;
				
			case 9:
				System.out.println("Blog Id to comment: ");
				int blogIdToComment = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Comment: ");
				String comment = scanner.nextLine();
				commentOnBlog(blogIdToComment, comment);
				break;
			
			case 10:
				System.out.println("Blog Id: ");
				int blogIdToReply = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Comment Id: ");
				int commentId = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Reply: ");
				String reply = scanner.nextLine();
				replyForComment(blogIdToReply, commentId, reply);
				break;
				
			case 11:
				System.out.println("Blog Id: ");
				int blogIdToDisplay = scanner.nextInt();
				displayComments(blogIdToDisplay);
				break;
				
			default :
				temp=false;
				break;
			}
		}

	}

}