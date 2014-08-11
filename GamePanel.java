import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.Random;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	private static int height = 400;
	private static int width = 400;
	private static Image Dirt, Ocean, Grass;
	private static Tile[][] tiles = new Tile[height][width];
	public static int screenWidth, screenHeight;
	private Point mousePoint;
	private int screenx, screeny, mag = 20;
	
	
	public GamePanel(int w, int h){
		setVisible(true);
		if (mag >= 100) mag = 80;
		mousePoint = new Point();
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.WHITE);
		screenx = 0; screeny = 0;
		screenWidth = w;
		screenHeight = h;
		Timer timer = new Timer(1, this);
		timer.setInitialDelay(10);
		setImages();
		setTiles();
		timer.start();
		repaint();
	}
	
	public void setFrameWidth(int w){
		screenWidth = w;
	}
	
	public void setFrameHeight(int h){
		screenHeight = h;
	}
	
	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int xextra = screenx%mag;
		int yextra = screeny%mag;
		int startCol = screenx/mag;
		int startRow = screeny/mag;
		
		for(int row = startRow; row < height; row++){
			for(int col = startCol; col < width; col++){
				g.drawImage(tiles[row][col].getImage(), (col - startCol) *mag - xextra, (row - startRow)*mag - yextra, mag, mag, null);
				if((col - startCol) * mag > screenWidth){
					col = width;
				}
			}
			if((row - startRow) * mag > screenHeight){
				row = height;
			}
		}
	}
	
	public void setImages(){
		try{
			Dirt = ImageIO.read(new File("Images//Dirt.png"));
			Grass = ImageIO.read(new File("Images//Grass.png"));
			Ocean = ImageIO.read(new File("Images//Ocean.png"));
		} catch(Exception e){
			System.out.println("No Image");
		}
	}
	
	public void setTiles(){
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				if(row <= 10 || row >= height - 10 || col <= 10 || col >= width - 10){
					tiles[row][col] = new Tile("Ocean");
					tiles[row][col].setImage(Ocean);
				}
				else{
					Random random = new Random();
					int rand = random.nextInt(3);
					if(rand < 1){
						tiles[row][col] = new Tile("Ocean");
						tiles[row][col].setImage(Ocean);
					}
					else if(rand < 2){
						tiles[row][col] = new Tile("Grass");
						tiles[row][col].setImage(Grass);
					}
					else if(rand < 3){
						tiles[row][col] = new Tile("Dirt");
						tiles[row][col].setImage(Dirt);
					}
				}
			}
		}
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
		//System.out.println(screenx + ", " + screeny);
		//System.out.println(mousePoint.x + ", " + mousePoint.y);
		//System.out.println(screenx + ", " + screeny);
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
		int movement = 50 + 40 - mag;
		if(mousePoint.x < 70){
			if(screenx >= movement) screenx-=movement;
			else{
				screenx = 0;
			}
		}
		if(mousePoint.y < 70){
			if(screeny >= movement) screeny-=movement;
			else{
				screeny = 0;
			}
		}
		if(mousePoint.x > screenWidth - 70){
			if(screenx <= width*mag - screenWidth - movement) screenx+=movement;
			else{
				screenx = width*mag - screenWidth;
			}
		}
		if(mousePoint.y > screenHeight - 70){
			if(screeny <= height*mag - screenHeight - movement) screeny+=movement;
			else{
				screeny = height*mag - screenHeight ;
			}
		}
		repaint();
	}

}
