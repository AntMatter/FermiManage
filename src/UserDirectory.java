import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UserDirectory extends Directory{
	public User searchForUser(String id)
	{
		BufferedReader reader;
		String fileName = "Data/users.txt";
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
					User checkingUser = new User(formatted[0], formatted[1], formatted[2]);
					reader.close();
					return checkingUser;
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
	public User searchForUser(User user)
	{
		BufferedReader reader;
		String fileName = "Data/users.txt";
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				
				String[] formatted = line.split("~");
				User checkingUser = new User(formatted[0], formatted[1], formatted[2]);
				if(user.equals(checkingUser))
				{
					reader.close();
					return checkingUser;
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

	public void addUser(User user) {
		String fileName = "Data/users.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName, true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
				bufferedWriter.newLine();
				bufferedWriter.write(user.getUserId() + "~");
				bufferedWriter.write(user.getFirstName() + "~");
				bufferedWriter.write(user.getLastName());
			
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
