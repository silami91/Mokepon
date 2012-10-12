import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Controlled extends GameObject {

	void move()//takes in from the user to determine which way to move
	{
		if(userInput==1)//up
		{
			if(yCor>2)
				yCor-=5;
			userInput=2;
		}
		else if(userInput==-1)//down
		{
			if(yCor<300)
				yCor+=5;
			userInput=2;
		}
		else if(userInput==0)//to the right
		{
			if(xCor<1180)
				xCor+=5;
			userInput=2;
		}else if(userInput==3)//to the right
		{
			if(xCor>2)
				xCor-=5;
			userInput=2;
		}
		else if(userInput==2)
		{

		}
		else
		{
			//nothing just in case
		}
	}

	
	Controlled(char T, int x, int y,int level, int s)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=10;
		life=1;
		score=s;
		readyToFire=false;
		if(level==0)
			pic1=new ImageIcon ("charmander.png");
		if(level==1)
			pic1=new ImageIcon ("charmeleon.png");
		if(level==2)
			pic1=new ImageIcon ("charizard.png");
	}
}
