import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame{
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static double screenWidth = screenSize.getWidth();
	public static double screenHeight = screenSize.getHeight();
	
	public GameFrame(){
		super("The Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize((int)screenWidth, (int)screenHeight);
		final GamePanel panel = new GamePanel(this.getWidth(), this.getHeight());
		this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                panel.setFrameWidth(getWidth());
                panel.setFrameHeight(getHeight());
            }
        });
		this.add(panel);
	}
	
}
