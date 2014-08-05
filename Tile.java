import java.awt.Image;

public class Tile {
	private Image image;
	private String type;
	
	public Tile(String t){
		type = t;
	}
	
	public Image getImage(){
		return image;
	}
	
	public String getType(){
		return type;
	}
}
