import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {
	private static int mapHeight = 700;
	private static int mapWidth = 1000;
	private static Tile[][] tiles = new Tile[mapHeight][mapWidth];
	public static int screenWidth, screenHeight;
	private Point mousePoint;
	private int screenx, screeny, mag = 20;
	public static ArrayList<Biome> biomes = new ArrayList<Biome>();
	private static int numBiomes = 6;

	public GamePanel(int w, int h) {
		setVisible(true);
		if (mag >= 100)
			mag = 80;
		mousePoint = new Point();
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		screenx = 0;
		screeny = 1000;
		screenWidth = w;
		screenHeight = h;
		Timer timer = new Timer(1, this);
		timer.setInitialDelay(20);
		makeTilesReal();
		setTiles();
		setOriginTiles();
		timer.start();
		repaint();
	}

	private void setOriginTiles() {

	}

	public void setFrameWidth(int w) {
		screenWidth = w;
	}

	public void setFrameHeight(int h) {
		screenHeight = h;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int xextra = screenx % mag;
		int yextra = screeny % mag;
		int startCol = screenx / mag;
		int startRow = screeny / mag;
		int endCol = 0;
		int endRow = 0;

		for (int row = startRow; row < mapHeight; row++) {
			for (int col = startCol; col < mapWidth; col++) {
				g.drawImage	(tiles[row][col].getImage(), 
							(col - startCol) * mag - xextra,
							(row - startRow) * mag - yextra,
							mag, mag, null);
				if ((col - startCol) * mag > screenWidth) {
					endCol = col;
					col = mapWidth;
				}
				else endCol = mapWidth;
			}
			if ((row - startRow) * mag > screenHeight) {
				endRow = row;
				row = mapHeight;
			}
			else endRow = mapHeight;
		}
		
		drawMiniMap(g, startCol, startRow, endCol, endRow);

	}

	public void drawMiniMap(Graphics g, int startCol, int startRow, int endCol, int endRow){
		g.setColor(Color.BLACK);
		int w = 300;
		int h = 180;
		int x = screenWidth - 360;
		int y = 40;
		g.fillRect(x - 2, y - 2, w + 4, h+2);
		int miniSize = 3;
		int colDif = endCol - startCol;
		int rowDif = endRow - startRow;
		int miniCol = startCol - ((w/miniSize - colDif) / 2);
		int miniRow = startRow - ((h/miniSize - rowDif) / 2);
		int mendCol = endCol + ((w / miniSize - colDif) / 2);
		int mendRow = endRow + ((h / miniSize - rowDif) / 2);
		for (int r = miniRow; r < mendRow+1; r++) {
			for (int c = miniCol; c < mendCol+1; c++) {
				if (r >= 0 && c >= 0 && r < mapHeight && c < mapWidth)
					g.drawImage	(tiles[r][c].getImage(),
								(c - miniCol) * miniSize + x,
								(r - miniRow) * miniSize + y,
								miniSize, miniSize, null);
				else
					g.fillRect(	(c - miniCol) * miniSize + x,
								(r - miniRow) * miniSize + y,
								miniSize,
								miniSize);
			}
		}
		g.setColor(Color.YELLOW);
		g.drawRect(	(startCol - miniCol) * miniSize + x,
					(startRow - miniRow) * miniSize + y,
					colDif * miniSize,
					rowDif * miniSize);
	}
	
	public void makeTilesReal() {
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				tiles[row][col] = new Tile("Ocean");
				tiles[row][col].setImage(ImageToBiomeData.Ocean);
			}
		}
	}

	public void setTiles() {
		float[][] perlinNums = SimplexNoise.generateOctavedSimplexNoise(
				mapHeight, mapWidth, 10, 0.5f, 0.003f);
		for (int row = 0; row < mapHeight; row++) {
			for (int col = 0; col < mapWidth; col++) {
				if (perlinNums[row][col] < 2.0f / numBiomes - 1.0f) {
					tiles[row][col] = new Tile("Ocean");
					tiles[row][col].setImage(ImageToBiomeData.Ocean);
				} else if (perlinNums[row][col] < 4.0f / numBiomes - 1.0f) {
					tiles[row][col] = new Tile("Sand");
					tiles[row][col].setImage(ImageToBiomeData.Sand);
				} else if (perlinNums[row][col] < 6.0f / numBiomes - 1.0f) {
					tiles[row][col] = new Tile("Grass");
					tiles[row][col].setImage(ImageToBiomeData.Grass);
				} else if (perlinNums[row][col] < 8.0f / numBiomes - 1.0f) {
					tiles[row][col] = new Tile("Dirt");
					tiles[row][col].setImage(ImageToBiomeData.Dirt);
				} else if (perlinNums[row][col] < 10.0f / numBiomes - 1.0f) {
					tiles[row][col] = new Tile("Grass");
					tiles[row][col].setImage(ImageToBiomeData.Grass);
				} else {
					tiles[row][col] = new Tile("Snow");
					tiles[row][col].setImage(ImageToBiomeData.Snow);
				}
			}
		}

	}

	public ArrayList<Tile> getTiles(int range, int row, int col) {
		ArrayList<Tile> surTiles = new ArrayList<Tile>();
		int rstart = 0, cstart = 0, rend = 0, cend = 0;
		rstart = (row - range >= 0) ? (row - range) : 0;
		cstart = (col - range >= 0) ? (col - range) : 0;
		rend = (row + range < mapHeight) ? (row + range) : mapHeight - 1;
		cend = (col + range < mapWidth) ? (col + range) : mapWidth - 1;
		for (int r = rstart; r <= rend; r++) {
			for (int c = cstart; c <= cend; c++) {
				if (r >= row - range && r <= row + range) {
					if (c >= col - range && c <= col + range) {
						if (r != row || c != col) {
							if (Math.abs(row - r) + Math.abs(col - c) <= range) {
								surTiles.add(tiles[r][c]);
							}
						}
					}
				}
			}
		}
		return surTiles;
	}

	@Override
	public void mouseClicked(MouseEvent event) {

	}

	@Override
	public void mouseEntered(MouseEvent event) {

	}

	@Override
	public void mouseExited(MouseEvent event) {

	}

	@Override
	public void mousePressed(MouseEvent event) {
		
	}

	@Override
	public void mouseReleased(MouseEvent event) {

	}

	@Override
	public void mouseDragged(MouseEvent event) {

	}

	@Override
	public void mouseMoved(MouseEvent event) {
		mousePoint = event.getPoint();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int movement = mag / 10 + 68;
		if (mousePoint.x < 70) {
			if (screenx >= movement)
				screenx -= movement;
			else {
				screenx = 0;
			}
		}
		if (mousePoint.y < 70) {
			if (screeny >= movement)
				screeny -= movement;
			else {
				screeny = 0;
			}
		}
		if (mousePoint.x > screenWidth - 70) {
			if (screenx <= mapWidth * mag - screenWidth - movement)
				screenx += movement;
			else {
				screenx = mapWidth * mag - screenWidth;
			}
		}
		if (mousePoint.y > screenHeight - 70) {
			if (screeny <= mapHeight * mag - screenHeight - movement)
				screeny += movement;
			else {
				screeny = mapHeight * mag - screenHeight;
			}
		}
		repaint();
	}

}
