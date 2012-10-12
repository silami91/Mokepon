import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//just the title panel, which once exited from isnt seen again
//is its own panel
public class Title extends JPanel {
	ImageIcon screen;
	Title()
	{
		screen=new ImageIcon ("title.png");
		this.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		screen.paintIcon(this, g, 0, 0);
	}
	
}