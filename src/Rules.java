import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//has its own panel and button to access that panel.
//displays the rules
public class Rules extends JPanel {
	ImageIcon guide;
	Rules()
	{
		guide=new ImageIcon ("Rules.png");
		this.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		guide.paintIcon(this, g, 0, 0);
	}
	
}
