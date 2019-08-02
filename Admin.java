import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Admin extends User{

	private Scanner scanner = new Scanner(System.in);      

	public Admin(String userName, String password, int age, int userId) {
		super(userName, password, age, userId);
	}

	public void displayAllCustomers(){
		Map<String,User> userMap = BloggingApp.getUserMap(this);	

		for(User user : userMap.values()){
			if(user instanceof Customer){
				System.out.println(user.getUserId()); 
				System.out.println(user.getUserName()); 
				System.out.println(user.getAge());
				System.out.println("########################################################################");
			}
		}
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

	public void removeBlog(int blogId){
		List<Blog> userBlogs = BloggingApp.getAllBlogs(userName);	
		int indexOfBlogToBeRemoved = -1;

		for(int pos=0;pos<userBlogs.size();pos++){
			if(userBlogs.get(pos).getBlogId() == blogId){
				indexOfBlogToBeRemoved = pos;
				break;
			}
		}

		if(indexOfBlogToBeRemoved != -1){
			userBlogs.remove(indexOfBlogToBeRemoved); 
		}	
	}
	
	public void removeUser(String userName){
	   BloggingApp.removeUser(userName,this);	
	}
	
	@Override
	void displayFeaturesAndDoOperations() {
		boolean temp=true;
		while(temp){
			System.out.println("1.Display All Customers");
			System.out.println("2.Display All Blogs Of Specific User");
			System.out.println("3.Remove Blog");
			System.out.println("4.Remove User");
			System.out.println("5.Exit");

			System.out.println("Please enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch(choice){			
			case 1 : 
				displayAllCustomers();
				break;

			case 2 : 
				System.out.println("Username : ");
				String username = scanner.nextLine();
				displayAllBlogs(username);
				break;

			case 3 :
				System.out.println("Blog Id : ");
				int blogId = scanner.nextInt();
				removeBlog(blogId);
				break;

			case 4 : 
				System.out.println("UserName Id : ");
				int blogIdToRemove = scanner.nextInt();
				removeBlog(blogIdToRemove);
				break;

			case 5 : 
				System.out.println("Username : ");
				String userNameToRemove = scanner.nextLine();
				removeUser(userNameToRemove);
				break;

			default :
				temp=false;
				break;
			}
		}
	}

}