import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class BloggingDb {

	private static Connection connection = null;
	private static String url = "jdbc:mysql://localhost:3306/Blogging";
	private static String username = "sudharsan";
	private static String password = "password";
	private static PreparedStatement pst=null;
	private static String query;
	private static ResultSet resultSet=null;
	private static Statement statement;

	static{

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url,username,password);
			statement = connection.createStatement();

			query = "CREATE TABLE IF NOT EXISTS Blog(blog_Id int,createdByUserId varchar(20),createdDate DATE,content varchar(200)";
			pst = connection.prepareStatement(query);
			pst.executeUpdate();		
		}
		catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		catch(SQLException e){
			System.out.println(e);
		}

	}
	
	public static boolean createNewBlog(Blog blog) throws SQLException{
		query = "INSERT INTO Blog(blog_Id,createdByUserId,createdDate,content)VALUES('"+blog.getBlogId()+"','"+blog.getCreatedByUserId()+"','"+blog.getContent()+"')'";
		int result = statement.executeUpdate(query);
		return (result>0) ? true : false;           
	}
	
	public static void removeBlog(int blogId) throws SQLException{
		query = "DELETE FROM Blog where blog_Id = '"+blogId+"'";
		int result = statement.executeUpdate(query);
	}
	
	public static void getBlog() throws SQLException{
		
		query = "SELECT * FROM Blog";
		resultSet = statement.executeQuery(query);
		
		while(resultSet.next()){
			getBlogDetails(resultSet);
		}
	}

	private static void getBlogDetails(ResultSet resultSet) throws SQLException {
		int blogId = resultSet.getInt(1);
		int createdByUserId = resultSet.getInt(2);
		Date createdDate = resultSet.getDate(3);
		String content = resultSet.getString(4); 		
	}	
}
