import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private String bookID;
	private String userID;
	private String bookName;
	private String uniqueID;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Date transactionDate;
	

	public Transaction(String bookID, String userID, String bookName, String uniqueID) {
		this.bookID = bookID;
		this.userID = userID;
		this.bookName = bookName;
		this.uniqueID = uniqueID;
		transactionDate = new Date(); 
	
	}
	public Transaction(Book book, String user, String uniqueID)
	{
		this.bookID = book.getBookID();
		this.userID = book.getBookName();
		this.bookName = user;
		this.uniqueID = uniqueID;
		transactionDate = new Date(); 
	}
	public String getDate()
	{
		return dateFormat.format(transactionDate);
	}
	public String getuniqueID()
	{
		return uniqueID;
	}
	public void setuniqueID(String uid)
	{
		this.uniqueID = uid;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String bookName) {
		this.userID = bookName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String userCheck) {
		this.bookName = userCheck;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		if (this.bookID.equals(((Transaction) obj).bookID) && this.userID.equals(((Transaction) obj).userID) && this.uniqueID.equals(((Transaction) obj).uniqueID))
			return true;
		else
			return false;

	}
	@Override
	public String toString()
	{
		return "Unique Book ID: " + uniqueID + " ISBN Number: " + bookID +" Title: "+userID;
	}
}
