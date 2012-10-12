import java.io.Serializable;
import java.util.ArrayList;


public class Player implements Serializable {

	String name;
	ArrayList <GameObject> saveGame= new ArrayList <GameObject>();
	int highScore;
	int level;
	
	void add(GameObject g)
	{
		saveGame.add(g);//adds an object
	}
	
	int size()
	{
		return saveGame.size();//returns the size of savegame
	}
	
	GameObject get(int index)
	{
		return saveGame.get(index);//returns an object in save game
	}
	
	void remove(int index)
	{
		saveGame.remove(index);//removes and object
	}
	
	void insert(int index, GameObject g)
	{
		saveGame.add(index, g);//adds an object to a specific place
	}
	
	void set(Player set)//used to set one ArrayList <Player> to another
	{
		name=set.name;
		saveGame=set.saveGame;
		level=set.level;
		highScore=set.highScore;
	}
	
	Player(String s)//constructor
	{
		name=s;
		ArrayList <GameObject> SaveGame = new ArrayList <GameObject> ();
		level=0;
	}
}
