import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	public static String title = "The Game";
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenHeight = (int) screenSize.getHeight();
	public static int screenWidth = (int) screenSize.getWidth();
	
	
	public GameFrame(){
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, screenWidth, screenHeight);
		add(new GamePanel(screenWidth, screenHeight));
	}
	
}
