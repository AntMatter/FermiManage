import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class BookDirectory extends Directory{


	public BookDirectory() {

	}
	
	public Book searchForBook(String id)
	{
		BufferedReader reader;
		String fileName = "Data/library.txt";
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				
				String[] formatted = line.split("~");
				if(id.equals(formatted[0]))
				{
					Book checkingBook = new Book(formatted[0], formatted[1], Integer.parseInt(formatted[2]));
					reader.close();
					return checkingBook;
				}
				
				line = reader.readLine();
			} 
			reader.close();
			return null;
			
		} catch 
		(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Book searchForBook(Book book)
	{
		BufferedReader reader;
		String fileName = "Data/library.txt";
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				
				String[] formatted = line.split("~");
				Book checkingBook = new Book(formatted[0], formatted[1],Integer.parseInt(formatted[2]));
				if(book.equals(checkingBook))
				{
					reader.close();
					return checkingBook;
				}
				
				line = reader.readLine();
			} 
			reader.close();
			return null;
			
		} catch 
		(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	


	public void addBook(Book book) {
		String fileName = "Data/library.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName, true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
				bufferedWriter.newLine();
				bufferedWriter.write(book.getBookID() + "~");
				bufferedWriter.write(book.getBookName() + "~");
				bufferedWriter.write(String.valueOf(book.getQuantity()));
			
			// Always close files.
			bufferedWriter.close();

		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}
		trimFile(fileName);
	}

}
