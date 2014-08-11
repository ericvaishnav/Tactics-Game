import java.awt.Image;

public class Tile {
	private Image image;
	private String type;
	
	public Tile(String type){
		this.type = type;
	}
	
	public Image getImage(){
		return image;
	}
	
	public void setImage(Image img){
		image = img;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String r){
		type = r;
	}
}
