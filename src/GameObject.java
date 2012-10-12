import java.io.Serializable;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import javax.swing.*;


public abstract class GameObject implements Serializable {

	char type;//type printed to the window
	public int xCor;//x coordinate
	public int yCor;//y coordinate
	public int healthPoints;
	public int userInput;
	public int life;
	public int score;
	public boolean whichPic;
	public boolean readyToFire;
	Rectangle2D box;

	public ImageIcon pic1;

	public int yDistanceFromCenter()
	{
		return pic1.getIconHeight()/2;
	}
	public int xDistanceFromCenter()
	{
		return pic1.getIconWidth()/2;
	}
	public int picWidth()
	{
		return pic1.getIconWidth();
	}
	public int picHeight()
	{
		return pic1.getIconHeight();
	}
	abstract void move();//called every cycle for every object
	public void setBox()//creats a box which is returned and acts as the hit box for the collision detection
	{
		box=new Rectangle2D.Double(xCor-(pic1.getIconWidth()),yCor-(pic1.getIconHeight()/2),pic1.getIconWidth(),pic1.getIconHeight());
	}

}
