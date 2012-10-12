import javax.swing.ImageIcon;
import java.lang.Math;

public class Boss extends GameObject {

	int rootX;
	int rootY;
	int makeMove = 0;

	double degrees=0;
	void move()
	{
		if(makeMove == 0){
			xCor=(int) (Math.cos(degrees)*30.0)+rootX;
			yCor=(int) (Math.sin(degrees)*30.0)+rootY;
			degrees++;
			makeMove=1;
		}
		else if(makeMove == 1)
		{
			makeMove = 2;
		}
		else
		{
			makeMove=0;
		}
		
	}

	Boss(char T, int x, int y)
	{
		readyToFire=false;
		type=T;
		rootX=x;
		rootY=y;
		healthPoints=3;
		life=75;
		score=0;
		pic1=new ImageIcon ("mewTwo.png");
	}
}