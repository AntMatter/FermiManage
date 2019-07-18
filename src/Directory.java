import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Directory {
	public void trimFile(String path)
	{
		File in = new File(path);
		File temp = new File("Data/temp.txt");
		try
		{
			Scanner reader = new Scanner(new FileReader(in));
			BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
			 while (reader.hasNextLine()) {
	                String line = reader.nextLine();
	                if (!line.isEmpty()) {
	                    writer.write(line);
	                    if(reader.hasNextLine())
	                    writer.write("\n");
	                }
	            }


			writer.flush();
			writer.close(); 
			reader.close();
			}
			  catch (IOException e)
			 {
				 e.printStackTrace();
			 }

			 in.setWritable(true);
			 in.delete();
			 temp.renameTo(new File(path));
	}

}
