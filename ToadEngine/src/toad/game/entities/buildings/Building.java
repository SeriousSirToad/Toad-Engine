package toad.game.entities.buildings;

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
	public boolean inside = false;
	public Level interior;
	public Rectangle renderBounds;

	public Building(Level level, int x, int y, BufferedImage image, Level interior) {
		super(level, x, y, image);
		this.isSolid = true;
		collider = new Rectangle(x, h - (33), 96, 33);
		renderBounds = new Rectangle(this.x, this.y, w, h);
		this.interior = interior;
	}

	@Override
	public void update() {

		if (interior == null || door == null) {
			return;
		}

	}

	public void render() {
		if (GameState.camera.contains(this) && !renderBounds.contains(GameState.player.collider)) {
			Main.g.drawImage(image, (x) - GameState.camera.x, (y) - GameState.camera.y, w, h, null);
		}
	}

	protected void makeStandardDoor(int x, int y) {
		Rectangle doorRect = new Rectangle(this.x + x, this.h - y, 32, 1);
		Rectangle door2 = new Rectangle(64, 112, 32, 8);
		door = new Door(level, doorRect, interior, new Point(64, 79));
		new Door(interior, door2, level, new Point(this.x + x, this.h - y));
	}

}
