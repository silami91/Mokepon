import javax.swing.ImageIcon;


public class EnemyLaser extends GameObject {
	
	void move()
	{
		if(xCor>3)//every cycle it moves to the left
			xCor-=4;
	}
	
	EnemyLaser(char T, int x, int y, int pic)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=75;
		score=0;
		readyToFire=false;
		if(pic==0)//depending on the type being passed in, it sets the image
			pic1=new ImageIcon ("shadowball.png");
		if(pic==1)
			pic1=new ImageIcon ("beam.png");
	}

}