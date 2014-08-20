import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageToBiomeData {
	
	public static ArrayList<GameImage> gameImages = new ArrayList<GameImage>();
	public static Image Dirt, Ocean, Grass, Lava, Sand, Snow;
	
	static {
		try{
			Dirt = ImageIO.read(new File("Images//Dirt.png"));
			Grass = ImageIO.read(new File("Images//Grass.png"));
			Ocean = ImageIO.read(new File("Images//Ocean.png"));
			Lava = ImageIO.read(new File("Images//Lava.png"));
			Sand = ImageIO.read(new File("Images//Sand.png"));
			Snow = ImageIO.read(new File("Images//Snow.png"));
		} catch(Exception e){
			System.out.println("No Image");
		}
		
		GamePanel.biomes.add(new Biome("Ocean", Ocean, Ocean, Ocean, Ocean, Ocean));
		GamePanel.biomes.add(new Biome("Mountain", Ocean, Sand, Grass, Dirt, Snow));
	}
}
