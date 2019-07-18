import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionDirectory extends Directory{
	public Transaction searchForUser(String userId)
	{
		BufferedReader reader;
		String fileName = "Data/transactions.txt";
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				if(line.equals("")) continue;
				
				String[] formatted = line.split("~");
				
				if(userId.equals(formatted[1]))
				{
					Transaction checkingTransaction = new Transaction(formatted[0], formatted[1], formatted[2], formatted[3]);
					reader.close();
					return checkingTransaction;
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
	public Transaction searchForTransaction(String uniqueID)
	{
		BufferedReader reader;
		String fileName = "Data/transactions.txt";
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				if(line.equals("")) continue;
				String[] formatted = line.split("~");
				
				if(uniqueID.equals(formatted[3]))
				{
					Transaction checkingTransaction = new Transaction(formatted[0], formatted[1], formatted[2], formatted[3]);
					reader.close();
					return checkingTransaction;
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
	public ArrayList<String[]> getAllOpenTransactions()
	{
		BufferedReader reader;
		String fileName = "Data/transactions.txt";
		ArrayList<String[]> list = new ArrayList<String[]>();
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				if(line.equals("")) continue;
				String[] formatted = line.split("~");
				list.add(formatted);

				
				line = reader.readLine();
			} 
			reader.close();
			return list;
			
		} catch 
		(IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

public Transaction searchForTransactionUid(String uid)
{
	BufferedReader reader;
	String fileName = "Data/transactions.txt";
	try
	{
		reader = new BufferedReader(new FileReader(fileName));
		//reader.readLine();
		String line = reader.readLine();
		while(line != null)
		{
			if(line.equals("")) continue;
			String[] formatted = line.split("~");
			Transaction checkingTransaction = new Transaction(formatted[0], formatted[1], formatted[2], formatted[3]);
			if(uid.equals(checkingTransaction.getuniqueID()))
			{
				reader.close();
				return checkingTransaction;
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
	public Transaction searchForTransaction(Transaction transaction)
	{
		BufferedReader reader;
		String fileName = "Data/transactions.txt";
		try
		{
			reader = new BufferedReader(new FileReader(fileName));
			//reader.readLine();
			String line = reader.readLine();
			while(line != null)
			{
				if(line.equals("")) continue;
				String[] formatted = line.split("~");
				Transaction checkingTransaction = new Transaction(formatted[0], formatted[1], formatted[2], formatted[3]);
				if(transaction.equals(checkingTransaction))
				{
					reader.close();
					return checkingTransaction;
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

	public void addTransaction(Transaction transaction) {
		String fileName = "Data/transactions.txt";

		try {
			// Assume default encoding.
			FileWriter fileWriter = new FileWriter(fileName, true);

			// Always wrap FileWriter in BufferedWriter.
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			// Note that write() does not automatically
			// append a newline character.
				bufferedWriter.newLine();
				bufferedWriter.write(transaction.getBookID() + "~");
				bufferedWriter.write(transaction.getUserID() + "~");
				bufferedWriter.write(transaction.getBookName() + "~");
				bufferedWriter.write(transaction.getuniqueID() + "~");
				bufferedWriter.write(transaction.getDate());
				
			
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
