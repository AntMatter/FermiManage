

public class User {
	private String userId;
	private String firstName;
	private String lastName;
	
	public User(String userID, String firstName, String lastName) {
		this.userId = userID;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o == null) return false;
		if(this.getClass() != o.getClass()) return false;
		if(this.firstName.equals(((User)o).firstName) && this.lastName.equals(((User)o).lastName) && this.userId.equals(((User)o).userId))
		return true;
			return false;
	}
	@Override
	public String toString()
	{
		return "Student ID Number: " + userId + " First Name: "+firstName+ " Last Name: " + lastName;
	}

}
