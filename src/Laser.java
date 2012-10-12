import javax.swing.ImageIcon;


public class Laser extends GameObject {
	
	void move()
	{
		if(xCor<1250)//every cycle it moves to the left
			xCor+=3;
		life--;
	}
	
	Laser(char T, int x, int y)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=75;
		score=0;
		pic1=new ImageIcon ("Fireball.png");
	}

}
