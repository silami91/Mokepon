import javax.swing.ImageIcon;

public class Obstacle extends GameObject {
	
	void move()//literally does nothing
	{

	}
	Obstacle(char T, int x, int y, int level)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=1;
		score=0;
		if(level==0)
			pic1=new ImageIcon ("magikarp.png");
		else if(level==1)
			pic1=new ImageIcon ("gyarados.png");
	}
}
