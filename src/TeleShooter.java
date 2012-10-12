import java.util.Random;
import java.math.*;

import javax.swing.ImageIcon;

public class TeleShooter extends GameObject { 

	int amount;
	int chunk=0;
	void move()
	{
		if(chunk==0)
		{
			readyToFire=true;//right after teleportting its ready to fire
			Random randGen=new Random();
			int rand=randGen.nextInt()%2;
			if(Math.abs(rand)==0)//decides to teleport up
			{
				if(yCor>70)
					yCor-=60;
				else
					yCor=30;
			}
			else if(Math.abs(rand)==1)//or teleport down
			{
				if(yCor<230)
					yCor+=60;
				else
					yCor=270;
			}
			chunk=50;//waits 50 cycles
		}
		else
			readyToFire=false;
			chunk--;
	}
	TeleShooter(char T, int x, int y,int level)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=1;
		score=0;
		if(level==1)
			pic1=new ImageIcon ("abra.png");
		else if(level==2)
			pic1=new ImageIcon ("alakazam.png");
	}
}