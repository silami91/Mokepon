import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;


public class Application extends JFrame
{
	static CardLayout cardLayout=new CardLayout (0,0);

	static JPanel cardPanel=new JPanel(cardLayout);
	static JPanel appPanel, loadPanel, rulePanel, gamePanel, titlePanel;
	static JButton newGame, loadGame, highScores, rules, pause, save, game;
	boolean somethingToSave=false;

	public Application ()
	{
		appPanel=new JPanel();
		appPanel.add(newGame=new JButton("New Game"));
		appPanel.add(loadGame=new JButton("Load Game"));
		appPanel.add(highScores=new JButton("HighScores"));
		appPanel.add(rules=new JButton("Rules"));
		appPanel.add(pause=new JButton("Pause"));
		appPanel.add(save=new JButton("Save"));
		appPanel.add(game=new JButton("Game"));



		final Game Mokepon=new Game();//creates the game panel

		final Title titlePage= new Title();//creates the title panel

		final Rules rulePage= new Rules();//creates the rule panel

		cardPanel.add(titlePage, "TITLE");
		cardPanel.add(Mokepon, "GAME");
		cardPanel.add(rulePage, "RULES");


		add(appPanel, BorderLayout.SOUTH);
		add(cardPanel, BorderLayout.CENTER);
		newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String newName= JOptionPane.showInputDialog(null, "Enter in the name you would like to use", "QWAT NAME VOU VANT DO JOOSE", JOptionPane.QUESTION_MESSAGE);
				Mokepon.NewGame(newName);//if the user wants a new game 
				cardLayout.show(cardPanel, "GAME");//make its and start the game
				Mokepon.requestFocusInWindow();
				Mokepon.started=true;
				somethingToSave=true;
				Mokepon.RunGame();
			}

		});

		loadGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(IO.ReadInPlayers()==null)//if the file doesnt exist treat it like a new game
				{
					String newName= JOptionPane.showInputDialog(null, "Enter in the name you would like to use", "QWAT NAME VOU VANT DO JOOSE", JOptionPane.QUESTION_MESSAGE);
					Mokepon.NewGame(newName);
					cardLayout.show(cardPanel, "GAME");
					Mokepon.requestFocusInWindow();
					Mokepon.started=true;
					Mokepon.RunGame();
				}
				else//other wise create a massive string to inpliment
				{
					String prompt="Enter in the name you would like to use:"+'\n';
					String playerName;
					String playerList="\n";
					Mokepon.Players=IO.ReadInPlayers();
					for(int i=0; i<Mokepon.Players.size(); i++)
					{
						playerName=i+". "+Mokepon.Players.get(i).name+'\n';
						playerList+=playerName;
					}
					prompt+=playerList;//and prompt the user for an int which will be used as PlayerChoice
					Mokepon.playerChoice = Integer.parseInt(JOptionPane.showInputDialog(null, prompt, "QWAT NAME VOU VANT DO JOOSE", JOptionPane.QUESTION_MESSAGE));
				}
				somethingToSave=true;
				Mokepon.RunGame();
				cardLayout.show(cardPanel, "GAME");
			}
		});

		highScores.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				String scoreList="HighScores:"+'\n';//creates a new dialog panel
				String playerName;
				String playerList="\n";
				Mokepon.Players=IO.ReadInPlayers();
				ArrayList<Player> top = Mokepon.Players;
				if(IO.ReadInPlayers()!=null)
				{
					for(int i=0; i<Mokepon.Players.size(); i++)
					{
						for(int j=0; j<Mokepon.Players.size()-1; j++)
						{
							if(Mokepon.Players.get(j).highScore>Mokepon.Players.get(j+1).highScore)
							{
								Player temp=top.get(j);
								top.get(j).set(top.get(j+1));
								top.get(j+1).set(temp);
							}
						}
					}
					ArrayList <Player> reverse = new ArrayList<Player>();
					for(int i=top.size()-1; i>=0; i--)
						reverse.add(top.get(i));
					if(Mokepon.Players.size()<10)
					{
						for(int i=0; i<Mokepon.Players.size(); i++)
						{
							playerName=i+1+".   Score: "+reverse.get(i).highScore+"    Name:  "+reverse.get(i).name+'\n';//and prints out the scores accordignly
							playerList+=playerName;
						}
					}
					else
					{
						for(int i=0; i<10; i++)
						{
							playerName=i+1+".   Score: "+reverse.get(i).highScore+"    Name:  "+reverse.get(i).name+'\n';
							playerList+=playerName;
						}
					}
					scoreList+=playerList;
					JOptionPane.showMessageDialog(null, scoreList);
				}
			}

		});


		rules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(cardPanel, "RULES");
			}

		});

		game.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cardLayout.show(cardPanel, "GAME");
			}

		});

		pause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Mokepon.t.isRunning())

					Mokepon.t.stop();
				else
					Mokepon.t.start();

			}

		});

		save.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(Mokepon.Players!=null)
				{
					Mokepon.Players.get(Mokepon.playerChoice).highScore=Mokepon.endScore;
					Mokepon.files.ReadOutPlayers(Mokepon.Players);
				}
			}

		});


	}

	public static void main (String[] args)
	{
		Application Menu=new Application();
		Menu.setTitle("Moke'pon");//GUI functions
		Menu.setSize(600, 450);
		Menu.setLocationRelativeTo(null);
		Menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Menu.setResizable(false);
		cardLayout.show(cardPanel, "TITLE");
		Menu.setVisible(true);
	}

}
