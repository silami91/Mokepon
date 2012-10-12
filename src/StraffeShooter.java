import java.util.Random;

import javax.swing.ImageIcon;

public class StraffeShooter extends GameObject { 

	int count;
	boolean moveUp;
	void move()
	{
		if(moveUp == true)
		{
			xCor--;
			yCor--;
		}
		else if(moveUp == false)
		{
			xCor--;
			yCor++;
		}
		count++;
		if(count == 90)
		{
			if(moveUp)
				moveUp = false;
			else
				moveUp = true;
			readyToFire=true;
			count = 0;
		}
		if(yCor < 0)
		{
			moveUp = false;
			count = -10;
		}
		else if(yCor > 300)
		{
			moveUp = true;
			count = -10;
		}
		
	}
	StraffeShooter(char T, int x, int y, int level)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=1;
		score=0;
		count=0;
		if((int)Math.random()%2 == 1)
			moveUp = true;
		else
			moveUp = false;
		if(level==1)
			pic1=new ImageIcon ("gastly.png");
		else if(level==2)
			pic1=new ImageIcon ("gengar.png");
	}
}