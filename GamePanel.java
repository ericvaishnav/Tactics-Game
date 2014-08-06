import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel{
	private Tile[][] tiles = new Tile[100][100];
	private String[] types = {"Sea Water", "Sand", "Earth", "Fresh Water"};
	private int mag = 7;
	
	public GamePanel(int width, int height){
		setVisible(true);
		setSize(width, height);
		setBackground(Color.WHITE);
		setTileTypes();
	}
	
	@Override
	public void paint(Graphics g){
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles[0].length; col++){
				Tile tile = tiles[row][col];
				if(tile.getType().equals("Sand")) g.setColor(new Color(255,235,205));
				if(tile.getType().contains("Water")) g.setColor(Color.BLUE);
				if(tile.getType().equals("Earth")) g.setColor(Color.GREEN);
				g.fillRect(col*mag, row*mag, mag, mag);
			}
		}
	}
	
	private void setTileTypes(){
		for(int row = 0; row < tiles.length; row++){
			for(int col = 0; col < tiles[0].length; col++){
				Tile tileToBeAdded = new Tile("Earth");
				if(row <= 3 || row >= 96 || col <= 3 || col >= 96){
					tileToBeAdded = new Tile("Sea Water");
				}
				else if((row <= 22 && col <= 23) || (row <= 23 && col >= 76)){
					tileToBeAdded = new Tile("Sea Water");
				}
				else if((row >= 76 && col <= 23) || (row >= 76 && col >= 76)){
					tileToBeAdded = new Tile("Sea Water");
				}
				else{
					Tile left = tiles[row][col-1];
					Tile above = tiles[row-1][col];
					if(left.getType().equals("Sea Water") || above.getType().equals("Sea Water")){
						Random random = new Random();
						int rand = random.nextInt(3);
						if(rand < 2) tileToBeAdded = new Tile(types[0]);
						else tileToBeAdded = new Tile(types[1]);
					}
					else {
						Random random = new Random();
						int rand = random.nextInt(2);
						tileToBeAdded = new Tile(types[rand+1]);
					}
				}
				tiles[row][col] = tileToBeAdded;
			}
		}
	}
	
}
