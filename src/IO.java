import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class IO {
	public static ArrayList<Player> ReadInPlayers()
	{
		ObjectInputStream In;//creates a new in object stream
		try 
		{
			In =new ObjectInputStream(new FileInputStream("save.dat"));//reads from save.dat
		}
		catch(Exception e)//catches any exception thrown
		{
			return null;//returns null to let game know there was a problem
		}
		try
		{
			return (ArrayList<Player>) In.readObject();//returns the world previously saved
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
	public static void ReadOutPlayers(ArrayList<Player> Players)
	{
		ObjectOutputStream Out=null;//creates a new object out put stream
		try
		{
			Out=new ObjectOutputStream(new FileOutputStream("save.dat"));//writes to save.dat
		}
		catch(Exception e)//catches any exception
		{
			System.out.println("Please enter a valid file");
			ReadOutPlayers(Players);
		}
		try
		{
			Out.writeObject(Players);//writes out, and the file is created.
		}
		catch(Exception e)
		{
			System.out.println("File cannot be written");
		}
	}
}
