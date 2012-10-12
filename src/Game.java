
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.lang.String;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;
import javax.swing.*;

public class Game extends JPanel implements ActionListener, KeyListener//Implements both JFrame and key listener
{
	IO files;//creates an instance of my file IO
	ArrayList<Player> Players;
	Player thisGame;
	int i;
	int j;
	int playerChoice;
	boolean started;
	Timer t;
	ImageIcon bkgrd;
	boolean loaded;
	boolean dead=false;
	boolean win = false;
	static int loadedInt;
	int endScore;
	ImageIcon died=new ImageIcon("died.png");//creates an image for the kill screen
	ImageIcon won = new ImageIcon("win.png");//creates an image for the win screen 
	void RunGame()//when running the game
	{
		createLevel();//creates the level
		loaded=true;
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		t.start();//and starts the timer
		started=true;
	}

	void NewGame(String newName)//if a new game is prompted
	{
		Players=IO.ReadInPlayers();
		if(Players==null)//and the file doesn't exist
		{
			Players=new ArrayList<Player>();//create a new array
		}
		Player newPlayer=new Player(newName);//or/ and use the name given in the constructor
		Players.add(newPlayer);
		playerChoice=Players.size()-1;
		thisGame = Players.get(playerChoice);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		if(!dead)//if it isn't dead
		{
			if(started && thisGame.saveGame.size()!=0)//and the array isn't empty
			{
				if(thisGame.get(0).xCor<670)//and the player isn't at the last third of the level for the background to move accordingly
				{
					bkgrd.paintIcon(this, g, -thisGame.get(0).xCor,20);//paint the background
					for(i=0; i<Players.get(playerChoice).size(); i++)
					{
						switch(Players.get(playerChoice).get(i).type)
						{
						case '>':
							Players.get(playerChoice).get(i).pic1.paintIcon(this, g, 20, Players.get(playerChoice).get(i).yCor+30);//paints the player
							break;
						default://paints everything else
							Players.get(playerChoice).get(i).pic1.paintIcon(this, g, Players.get(playerChoice).get(i).xCor-Players.get(playerChoice).get(0).xCor+20, Players.get(playerChoice).get(i).yCor+30);
							break;
						}
					}
				}
				else//if the player can see the end goal, the the player moves in the background, not the other way around
				{
					bkgrd.paintIcon(this, g, -(1250-570),20);
					for(i=0; i<Players.get(playerChoice).size(); i++)
					{
						switch(Players.get(playerChoice).get(i).type)
						{
						case '>':
							Players.get(playerChoice).get(i).pic1.paintIcon(this, g, 600-(1250-Players.get(playerChoice).get(0).xCor), Players.get(playerChoice).get(i).yCor+30);
							break;
						default:
							Players.get(playerChoice).get(i).pic1.paintIcon(this, g, 600-(1250-Players.get(playerChoice).get(i).xCor), Players.get(playerChoice).get(i).yCor+30);
							break;
						}
					}
				}
				if(Players.get(playerChoice).get(0).type=='>')//reads the health point of the ship, and prints it out accordingly
				{
					ImageIcon heart=new ImageIcon ("life.png");
					switch (Players.get(playerChoice).get(0).healthPoints)//paint the healt points, a lot for testing
					{
					case 10:
						heart.paintIcon(this, g, 370, 330);
					case 9:
						heart.paintIcon(this, g, 330, 330);
					case 8:
						heart.paintIcon(this, g, 290, 330);
					case 7:
						heart.paintIcon(this, g, 250, 330);
					case 6:
						heart.paintIcon(this, g, 210, 330);
					case 5:
						heart.paintIcon(this, g, 170, 330);
					case 4:
						heart.paintIcon(this, g, 130, 330);
					case 3:
						heart.paintIcon(this, g, 90, 330);
					case 2:
						heart.paintIcon(this, g, 50, 330);
					case 1:
						heart.paintIcon(this, g, 10, 330);
						break;
					}

				}
				g.setColor(Color.WHITE);//prints out the score
				g.setFont(new Font("SERIF", Font.BOLD, 28));
				g.drawString("Score:"+Players.get(playerChoice).get(0).score, 12, 46);
				g.setColor(Color.BLACK);//prints out the score shadow
				g.setFont(new Font("SERIF", Font.BOLD, 28));
				g.drawString("Score:"+Players.get(playerChoice).get(0).score, 10, 45);
			}
			
			if(win == true)
			{
				won.paintIcon(this, g, -20, -25);
				win = false;
			}
		}
		else //if the player is dead, it paints the kill screen
		{
			died.paintIcon(this, g, 0, 0);
			dead=false;
		}
	}

	public void keyPressed(KeyEvent e) //key listener
	{
		if(loaded)
		{
			switch (e.getKeyCode()) {
			case KeyEvent.VK_DOWN: //the input is used in the .move() funtion to tell which way for the player to move
				Players.get(playerChoice).get(0).userInput=-1; 
				break;
			case KeyEvent.VK_UP: 
				Players.get(playerChoice).get(0).userInput=1; 
				break;
			case KeyEvent.VK_LEFT:
				Players.get(playerChoice).get(0).userInput=3; 
				break;
			case KeyEvent.VK_RIGHT: 
				Players.get(playerChoice).get(0).userInput=0;
				break;
			case KeyEvent.VK_SPACE://if space is pressed, then the controlled shoots out a laser
				Players.get(playerChoice).get(0).userInput=2;
				if(Players.get(playerChoice).get(1).type!='~')
				{
					Laser frag=new Laser('~', Players.get(playerChoice).get(0).xCor+40,Players.get(playerChoice).get(0).yCor);
					Players.get(playerChoice).insert(1, frag);
				}
				break;
			}
		}
	}
	public void keyReleased(KeyEvent e) 
	{
		//empty
	}
	public void keyTyped(KeyEvent e)
	{
		//empty
	}
	public void actionPerformed(ActionEvent ae) 
	{
		if(!loaded)//if it isnt loaded yet
		{
			createLevel();//create the level
			t.start();//start the timer
		}
		if(Players.get(playerChoice).get(0).xCor<1174)//if you haven't won yet
		{
			if(Players.get(playerChoice).get(0).type == '>')//and you haven't died
			{
				ArrayList<Integer> Lasers=new ArrayList <Integer> ();
				for(i=0; i<Players.get(playerChoice).saveGame.size(); i++)
				{
					Players.get(playerChoice).get(i).move();//move every object
				}
				for(i=0; i<Players.get(playerChoice).saveGame.size(); i++)
				{
					if(Players.get(playerChoice).get(i).readyToFire==true)//if the object is ready to fire
					{
						Players.get(playerChoice).get(i).readyToFire=false;
						EnemyLaser missile=null;//then create a new enemy fire 
						if(Players.get(playerChoice).get(i).type=='K')//determine what the picture should be
							missile =new EnemyLaser('<',Players.get(playerChoice).get(i).xCor-20,Players.get(playerChoice).get(i).yCor,0);
						if(Players.get(playerChoice).get(i).type=='P')//and pass that into the constructor
							missile =new EnemyLaser('<',Players.get(playerChoice).get(i).xCor-20,Players.get(playerChoice).get(i).yCor,1);
						Players.get(playerChoice).saveGame.add(missile);
					}
				}
				for(i=0;i<Players.get(playerChoice).size();i++)
				{
					Players.get(playerChoice).get(i).setBox();//determines the hit box based on the im age loaded in
				}
				for(i=1; i<Players.get(playerChoice).size(); i++)
				{
					if(Players.get(playerChoice).get(0).box.intersects(Players.get(playerChoice).get(i).box))//a hit box intersects with the controlled's box
					{
						if(Players.get(playerChoice).get(i).type!='~')//and it isnt its own laser
						{
							if(Players.get(playerChoice).get(i).type=='O')//if its a collectible, it increase the score
							{
								Players.get(playerChoice).get(0).score++;
								endScore=Players.get(playerChoice).get(0).score;
							}
							else
								Players.get(playerChoice).get(0).healthPoints--;//if it is anything else then it removes a health point
							Players.get(playerChoice).remove(i);//then it removes the object it ran into
						}
					}
				}
				for(i=0; i<Players.get(playerChoice).size(); i++)//then it checks to see if any objects trigger a removal 
				{
					if(Players.get(playerChoice).get(i).life<1 || Players.get(playerChoice).get(i).healthPoints<1 || Players.get(playerChoice).get(i).xCor<20)
						Players.get(playerChoice).remove(i);
				}
				for(i=1; i<Players.get(playerChoice).saveGame.size(); i++)
				{
					if(Players.get(playerChoice).saveGame.get(i).type == '~')
					{
						for(j=0; j<Players.get(playerChoice).saveGame.size(); j++)//then while there are lasers to be checked
						{
							if(i!=j)
							{
								if(Players.get(playerChoice).saveGame.get(i).box.intersects(Players.get(playerChoice).saveGame.get(j).box) && Players.get(playerChoice).saveGame.get(j).type != 'O')//it checks the collision detection
								{
									Players.get(playerChoice).saveGame.remove(j);//if it hits, it does the same as a normal hit, except it creates a coin to drop in the enemies place
									Coin ball= new Coin('O',Players.get(playerChoice).saveGame.get(i).xCor,Players.get(playerChoice).saveGame.get(i-1).yCor);
									ball.setBox();
									Players.get(playerChoice).saveGame.remove(i);
									Players.get(playerChoice).saveGame.add(ball);
								}
							}
						}
					}
				}
				this.repaint();
			}
			else if(Players.get(playerChoice).get(0).type != '>')//when the controlled dies
			{
				t.stop();
				if(Players.get(playerChoice).get(0).score>Players.get(playerChoice).highScore)
					Players.get(playerChoice).highScore=Players.get(playerChoice).get(0).score;//it sets the new high score
				dead=true;
				this.repaint();
			}
		}
		else if(Players.get(playerChoice).get(0).xCor>117 && Players.get(playerChoice).get(0).type=='>')//when you make it all the way to the end of each level
		{
			if(Players.get(playerChoice).level!=2)//as long as it isnt the last level
			{
				t.stop();//it adds 10 to your score
				Players.get(playerChoice).get(0).score+=10;
				Players.get(playerChoice).level++;
				endScore=Players.get(playerChoice).get(0).score;
				Players.get(playerChoice).saveGame.clear();
				loaded=false;
				createLevel();
				t.start();
			}
			else
			{
				win = true;
				if(Players.get(playerChoice).get(0).score>Players.get(playerChoice).highScore)
					Players.get(playerChoice).highScore=Players.get(playerChoice).get(0).score+15;
				t.stop();
				IO.ReadOutPlayers(Players);
			}
		}
	}
	public Game()//Constructor
	{
		t= new Timer(50,this);
		t.addActionListener(this);
	}

	public void createLevel()
	{
		t.stop();
		Random randGen= new Random();
		int xCor;
		int yCor;
		if(Players.get(playerChoice).level==0)
		{
			bkgrd=new ImageIcon("Route1.png");
			Controlled ship=new Controlled('>',20,150,Players.get(playerChoice).level,0);
			Players.get(playerChoice).add(ship);
			for(i=0; i<10; i++)//adds 70 enemies
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%3000);//most of which are past the world, but still move
				randomInt=randGen.nextInt();//this is to make sure there is a constant stream of enemies
				yCor=Math.abs(randomInt%250);
				if(xCor<150)//prevents the enemy from spawning to close and gives the player a bufferzone of safety
					xCor+=150;
				Enemy pimp=new Enemy('H',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(pimp);

			}
			for(i=0; i<10; i++)//same for the obstacles, except all are stored within the world
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1180);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				Obstacle crack=new Obstacle('X',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(crack);
			}
			for(i=0; i<7; i++)
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1180);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				Straffer hoe=new Straffer('+',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(hoe);
			}
			loaded=true;
		}
		if(Players.get(playerChoice).level==1)
		{
			bkgrd=new ImageIcon("cave.png");
			Controlled ship=new Controlled('>',20,150,Players.get(playerChoice).level, endScore);
			Players.get(playerChoice).add(ship);
			for(i=0; i<5; i++)//adds 70 enemies
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%3000);//most of which are past the world, but still move
				randomInt=randGen.nextInt();//this is to make sure there is a constant stream of enemies
				yCor=Math.abs(randomInt%250);
				if(xCor<150)//prevents the enemy from spawning to close and gives the player a bufferzone of safety
					xCor+=150;
				Enemy pimp=new Enemy('H',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(pimp);

			}
			for(i=0; i<5; i++)//same for the obstacles, except all are stored within the world
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1180);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				Obstacle crack=new Obstacle('X',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(crack);
			}
			for(i=0; i<3; i++)
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1180);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				Straffer hoe=new Straffer('+',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(hoe);
			}
			for(i=0; i<3; i++)
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1000);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				StraffeShooter hoe=new StraffeShooter('K',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(hoe);
			}
			for(i=0; i<1; i++)
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1180);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				TeleShooter hoe=new TeleShooter('P',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(hoe);
			}
			loaded=true;
		}
		if(Players.get(playerChoice).level==2)
		{
			bkgrd=new ImageIcon("gym.png");
			Controlled ship=new Controlled('>',20,150,Players.get(playerChoice).level, endScore);
			Players.get(playerChoice).add(ship);
			Boss mewTwo=new Boss('@',1100,100);
			Players.get(playerChoice).add(mewTwo);
			for(i=0; i<5; i++)
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1500);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				StraffeShooter hoe=new StraffeShooter('K',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(hoe);
			}
			for(i=0; i<5; i++)
			{
				int randomInt=randGen.nextInt();
				xCor=Math.abs(randomInt%1000);
				randomInt=randGen.nextInt();
				yCor=Math.abs(randomInt%300);
				if(xCor<150)
					xCor+=150;
				TeleShooter hoe=new TeleShooter('P',xCor,yCor,Players.get(playerChoice).level);
				Players.get(playerChoice).add(hoe);
			}
			loaded=true;
		}
	}
	public void Game()
	{
		files=new IO ();//creates an instance of my file IO
		loaded=false;
		started=false;
		this.setVisible(true);
	}
}