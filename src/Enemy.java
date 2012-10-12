import javax.swing.ImageIcon;

public class Enemy extends GameObject { 
	
	void move()
	{
		if(xCor>0)//every cycle it moves to the left
			xCor--;
	}
	Enemy(char T, int x, int y, int level)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=1;
		score=0;
		readyToFire=false;
		if(level==0)
			pic1=new ImageIcon ("squirtle.png");
		else if(level==1)
			pic1=new ImageIcon ("blastoise.png");
	}

}

