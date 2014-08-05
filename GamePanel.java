import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{
	private Tile[][] tiles = new Tile[100][100];
	private String[] types = {"Desert", "Water", "Earth"};
	private int mag = 32;
	
	public GamePanel(int width, int height){
		setVisible(true);
		setSize(width, height);
		setBackground(Color.WHITE);
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles[0].length; col++){
				Random rand = new Random();
				int r = rand.nextInt(3);
				Tile tileToBeAdded = new Tile(types[r]);
				tiles[row][col] = tileToBeAdded;
			}
		}
	}
	
	@Override
	public void paint(Graphics g){
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles[0].length; col++){
				Tile tile = tiles[row][col];
				if(tile.getType().equals("Desert")) g.setColor(new Color(255,235,205));
				if(tile.getType().equals("Water")) g.setColor(Color.BLUE);
				if(tile.getType().equals("Earth")) g.setColor(Color.GREEN);
				g.fillRect(col*mag, row*mag, mag, mag);
			}
		}
	}
	
}
