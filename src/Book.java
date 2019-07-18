

public class Book {

	private String bookID;
	private String bookName;
	private int quantity;

	public Book(String bookID, String bookName) {
		this.bookID = bookID;
		this.bookName = bookName;
		this.quantity = 1;
	
	}
	public Book(String bookID, String bookName, int quantitiy)
	{
		this.bookID = bookID;
		this.bookName = bookName;
		this.quantity = quantitiy;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public int getQuantity()
	{
		return quantity;
	}
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		if (this.bookID.equals(((Book) obj).bookID) && this.bookName.equals(((Book) obj).bookName))
			return true;
		else
			return false;

	}
	@Override
	public String toString()
	{
		return "Book Number: " + bookID + " Title: "+bookName + " Current Available: " + quantity;
	}

}
