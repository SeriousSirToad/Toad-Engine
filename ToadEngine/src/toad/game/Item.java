package toad.game;

import java.awt.image.BufferedImage;

public class Item {
	
	private String id, displayName;
	private BufferedImage image;
	
	public Item(String id, String displayName, BufferedImage image) {
		this.id = id;
		this.displayName = displayName;
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public String id() {
		return id;
	}
	
	public String displayName() {
		return displayName;
	}
	
}
