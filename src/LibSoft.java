import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LibSoft
{
	BookDirectory library = new BookDirectory();
	UserDirectory userList = new UserDirectory();
	TransactionDirectory transactionList = new TransactionDirectory();
	Scanner scanner = new Scanner(System.in);

	/*
	 * public void massAdd()
	 *
	 * { // Book Scanner
	 * System.out.println("Please Scan a Book or hit 'q' to exit"); String id =
	 * scanner.nextLine(); if (id.contentEquals("q")) menu(); // Search the library
	 * for the book, and add it if it does not exist Book book =
	 * library.searchForBook(id); if (book == null) ;// newBook(id);
	 * updateLibrary(book, 0); massAdd();
	 *
	 * }
	 */

	

	public void updateLibrary(Book book, int in)
	{
		Path path = Paths.get("Data/library.txt");
		Charset charset = StandardCharsets.UTF_8;

		String content = null;
		try
		{
			content = new String(Files.readAllBytes(path), charset);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (content != null && book != null)
			if (in == 0)
				content = content.replaceAll(book.getBookID() + "~" + book.getBookName() + "~" + book.getQuantity(),
						book.getBookID() + "~" + book.getBookName() + "~" + (book.getQuantity() + 1));
		if (in == 1)
			content = content.replaceAll(book.getBookID() + "~" + book.getBookName() + "~" + book.getQuantity(),
					book.getBookID() + "~" + book.getBookName() + "~" + (book.getQuantity() - 1));
		try
		{
			Files.write(path, content.getBytes(charset));
		} catch (IOException e)
		{

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void trimFile(String path)
	{
		File in = new File(path);
		File temp = new File("Data/temp.txt");
		try
		{
			Scanner reader = new Scanner(new FileReader(in));
			BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
			while (reader.hasNextLine())
			{
				String line = reader.nextLine();
				if (!line.isEmpty())
				{
					writer.write(line);
					if (reader.hasNextLine())
						writer.write("\n");
				}
			}

			writer.flush();
			writer.close();
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		in.setWritable(true);
		in.delete();
		temp.renameTo(new File(path));
	}

	public void checkIn(Book book, User user, String uid)
	{

		// System.out.println("Would you like to check in this book? (y/n)");
		// String query = scanner.nextLine();
		// if (query.equals("y")) {
		File inputFile = new File("Data/transactions.txt");
		File tempFile = new File("Data/temp.txt");
		File outFile = new File("Data/completed_transactions.txt");
		try
		{

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			BufferedWriter writerOut = new BufferedWriter(new FileWriter(outFile, true));

			String lineToRemove = book.getBookID() + "~" + user.getUserId() + "~" + book.getBookName() + "~" + uid;
			String currentLine = reader.readLine();

			while (currentLine != null)
			{
				if (currentLine.contains(lineToRemove))
				{
					writerOut.newLine();
					writerOut.write(
							currentLine + "~" + (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date())));

					currentLine = reader.readLine();
				} else
				{
					writer.write(currentLine);
					currentLine = reader.readLine();
					if (currentLine != null)
					{
						writer.write("\n");
					}
				}
			}

			writer.flush();
			writer.close();
			writerOut.flush();
			writerOut.close();
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		inputFile.setWritable(true);
		inputFile.delete();
		tempFile.renameTo(new File("Data/transactions.txt"));
		trimFile("Data/transactions.txt");
		trimFile("Data/completed_transactions.txt");
		updateLibrary(book, 0);


	}

	public boolean checkBookStatus(Book book, User user, String uid)
	{
		// Transaction a = new Transaction(book.getBookID(), user.getUserId(),
		// book.getBookName(), uid);
		Transaction foundbook = transactionList.searchForTransactionUid(uid);
		if (foundbook == null)
			return true;
		// Edits will need to be made here to match with the quantity feature of books,
		// to ensure that books aren't overstocked
		if (foundbook.getuniqueID().equals(uid))
		{
			System.out.println("All copies of Book:\n " + book + "\n are currently checked out.");
			return false;
		}
		return true;
	}

	public Book newBook(String id, String title)
	{
		library.addBook(new Book(id, title));
		return new Book(id, title);
	}

	public User newUser(String id, String fName, String lName)
	{
		userList.addUser(new User(id, fName, lName));
		return new User(id, fName, lName);

	}

}
