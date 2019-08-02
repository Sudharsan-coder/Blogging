import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BloggingApp {

	private static Map<String,User> userMap = new HashMap<>();

	private static Map<String,List<Blog>> userBlogsMap = new HashMap<>();

	private static Map<Integer,Blog> blogMap = new HashMap<>();

	private static int blogId = 1;
	
	private static int commentId = 1;

	public User login(String userName,String password){
		if(userName==null || userName.isEmpty() || !userMap.containsKey(userName)){
			System.out.println("Invalid Username"); 
			return null;
		}

		if(password==null || password.isEmpty()){
			System.out.println(""); 
			return null;
		}

		User user = userMap.get(userName);
		if(password.equals(user.getPassword())){
			return user; 
		}else{
			System.out.println("Invalid password");
			return null;
		}
	} 

	public User signup(String userName,String password,int age,int userId){
		if(userMap.containsKey(userName)){
			System.out.println("Sorry.Username already exists.Try with different username.");
			return null;
		}

		User user = null;

		if(userName.equals("Sudharshan") && password.equals("Sudharshan")){
			user = new Admin(userName,password,age,userId);
		}else{
			user = new Customer(userName,password,age,userId); 
		}    
     
		userMap.put(userName,user); 
		return user;  
	}

	public static Map<String,User> getUserMap(User user){     
		if(user instanceof Admin){   
			return userMap; 
		}else{  
			System.out.println("Permission Denied.");  
			return null; 
		}
	}

	public static void addBlogForUser(String userName,Blog newBlog){
		List<Blog> userBlogs = userBlogsMap.get(userName);

		if(userBlogs == null){
			userBlogs = new ArrayList<>();
			userBlogsMap.put(userName,userBlogs);
		}

		userBlogs.add(newBlog);
		blogMap.put(newBlog.getBlogId(),newBlog);
		blogId++;
	}

	public static int getBlogId(){
		return blogId;	
	}
	
	public static int getCommentId(){
		return commentId;	
	}
	
	public static int incrementComments(){
		return commentId++;	
	}

	public static List<Blog> getAllBlogs(String userName){
		if(!userBlogsMap.containsKey(userName)){
			System.out.println("No blogs exists for this user."); 
			return null;
		}else{
			return userBlogsMap.get(userName); 
		}
	}

	public static void removeUser(String userName,User user){
		if(user instanceof Admin){ 
			userMap.remove(userName); 
			userBlogsMap.remove(userName);   
			System.out.println("User Removed Successfully.");   
		}else{
			System.out.println("Permission Denied.");    
		}    
 	}      
                            
	public static Blog getBlog(int blogID){
		if(!blogMap.containsKey(blogID)){
			System.out.println("Sorry Blog doesn't exist."); 
			return null;
		}else{
			return blogMap.get(blogID);    
		}
	}

	public static void removeBlog(int blogId,String userName){
		List<Blog> userBlogs = getAllBlogs(userName);	
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

		blogMap.remove(blogId);
	}


	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);      
		BloggingApp blogApp = new BloggingApp();
		User user = null;

		while(true){			
			System.out.print("1.Login ");
			System.out.print("2.Signup ");
			System.out.println("3.doOperations");
			System.out.println("4.Exit");
			System.out.println("Please enter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch(choice){                                  
			case 1 : 
				System.out.println("Username : ");
				String userName = scanner.nextLine();
				System.out.println("Password : ");
				String password = scanner.nextLine();

				user = blogApp.login(userName,password);
				break;
			case 2 : 
				System.out.println("Username : ");
				String username = scanner.nextLine();
				System.out.println("Password : ");
				String passWord = scanner.nextLine();
				System.out.println("Age : ");
				int age = scanner.nextInt();
				user = blogApp.signup(username,passWord,age,userMap.size()+1);
				System.out.println(user.userName);
				break;
			case 3 : 
				if(user!=null){
					user.displayFeaturesAndDoOperations();	
				}else{
					System.out.println("Please login properly.");	
				}
				break;	
			default: 
				System.exit(0);
				break;
			}
		}

	}	
}