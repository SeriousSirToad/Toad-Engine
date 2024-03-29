package toad.game.entities.buildings;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import toad.game.GameState;
import toad.game.Main;
import toad.game.entities.Entity;
import toad.game.level.Door;
import toad.game.level.Level;

public class Building extends Entity {

	public Door door;
	public Door doorInterior;
	public boolean inside = false;
	public Level interior;
	public Rectangle renderBounds;
	Color transluscent = new Color(0, 0, 0, 50);

	public Building(Level level, int x, int y, BufferedImage image, Level interior) {
		super(level, x, y, image);
		this.isSolid = true;
		collider = new Rectangle(x, y + h - (33), w, 33);
		System.out.println("y of building " + this.y);
		renderBounds = new Rectangle(x, y, w, h);
		this.interior = interior;
	}

	@Override
	public void update() {

	}

	public void render() {
		if (GameState.camera.contains(this) && !renderBounds.contains(GameState.player.collider)) {
			Main.g.drawImage(image, (x) - GameState.camera.x, (y) - GameState.camera.y, w, h, null);
		} else if (GameState.camera.contains(this) && renderBounds.contains(GameState.player.collider)) {
			Main.g.setColor(transluscent);
			Main.g.fillRect(x - GameState.camera.x, y - GameState.camera.y, w, h);
		}
	}

	// So it took me a while to figure out but this method is designed with the x +
	// y coords
	// to make it customizeable as to where the door is relative to the building 
	protected void makeStandardDoor(int x, int y, Point innerTpLoc, Point outerTpLoc) {
		//makes the collider for the door that leads to the interior
		Rectangle doorRect = new Rectangle(this.x + x, this.y + this.h - y, 32, 1);
		//creates the corresponding interior door
		Rectangle door2 = new Rectangle(interior.width / 2 - 16, interior.height - 1, 32, 8);
		door = new Door(level, doorRect, interior, innerTpLoc);
		doorInterior = new Door(interior, door2, level, outerTpLoc);
	}

}
