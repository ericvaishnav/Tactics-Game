import java.awt.Image;

public class Biome {

	private String name;
	private Image lvl1, lvl2, lvl3, lvl4, lvl5;
	
	public Biome(String name){
		this.name = name;
		lvl1 = null;
		lvl2 = null;
		lvl3 = null;
		lvl4 = null;
		lvl5 = null;
	}
	
	public Biome(String name, Image one, Image two, Image three, Image four, Image five){
		this.name = name;
		lvl1 = one;
		lvl2 = two;
		lvl3 = three;
		lvl4 = four;
		lvl5 = five;
	}
	
	public String getName(){
		return name;
	}
	
	public Image getLvl1Image(){
		return lvl1;
	}
	
	public Image getLvl2Image(){
		return lvl2;
	}
	
	public Image getLvl3Image(){
		return lvl3;
	}
	
	public Image getLvl4Image(){
		return lvl4;
	}
	
	public Image getLvl5Image(){
		return lvl5;
	}
	
}
