import java.util.Random;

import javax.swing.ImageIcon;


public class Coin extends GameObject {

	void move()//literally does nothing
	{
		life--;//sits and waits for you to grab it
	}//you only get 7 seconds

	Coin(char T, int x, int y)
	{
		type=T;
		xCor=x;
		yCor=y;
		healthPoints=1;
		life=350;
		score=0;

		Random randGen= new Random();
		int randomInt=Math.abs(randGen.nextInt())%6;//randomly picks an image to use to add variety
		switch(randomInt)
		{
		case 0:
			pic1=new ImageIcon ("sunstone.png");
			break;
		case 1:
			pic1=new ImageIcon ("firestone.png");
			break;
		case 2:
			pic1=new ImageIcon ("waterstone.png");
			break;
		case 3:
			pic1=new ImageIcon ("leafstone.png");
			break;
		case 4:
			pic1=new ImageIcon ("thunderstone.png");
			break;
		case 5:
			pic1=new ImageIcon ("moonstone.png");
			break;
		}
	}

}
