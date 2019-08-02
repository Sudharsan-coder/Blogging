
public abstract class User {
	
	protected int userId;

	protected String userName;

	protected String password;

	protected int age;

	public User(String userName, String password, int age , int userId) {
		super();
		this.userName = userName;
		this.password = password;
		this.age = age;
		this.userId = userId;
	}

	void changePassword(String password){
		this.password = password;
	}

	void changeAge(int age){
		this.age = age; 
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getAge() {
		return age;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	abstract void displayFeaturesAndDoOperations();

}
