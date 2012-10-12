import java.util.Random;

import javax.swing.ImageIcon;

public class Straffer extends GameObject { 

	int chunk;
	void move()
	{
		if(chunk!=0)
		{
			if(yCor!=0 && chunk<0)//then moves down
			{
				yCor--;
				chunk++;
			}
			if(yCor!=300 && chunk>0)//or up
			{
				yCor++;
				chunk--;
			}
			if(chunk<0)
				chunk++;
			else if(chunk>0)
				chunk--;
		}
		else
		{
			chunk=200;
			Random randGen= new Random();//takes a random number
			int randomInt=randGen.nextInt()%2;
			if(randomInt==1)
				chunk*=-1;
		}
	}
	Straffer(char T, int x, int y, int level)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=1;
		if(level==0)
			pic1=new ImageIcon ("bulbasaur.png");
		else if(level==1)
			pic1=new ImageIcon ("venusaur.png");
	}

}