package toad.game.level;

import java.awt.Point;
import java.awt.Rectangle;
import toad.game.entities.Entity;

public class Door {

	public Rectangle rect;
	Level nativeLevel;
	Level level;
	Point tpLocation;

	public Door(Level nativeLevel, Rectangle rect, Level level, Point tpLocation) {

		this.rect = rect;
		this.level = level;
		this.tpLocation = tpLocation;
		this.nativeLevel = nativeLevel;
		nativeLevel.doors.add(this);

	}

	public void tick() {
		for (int i = 0; i < nativeLevel.entities.size(); i++) {
			Entity e = nativeLevel.entities.get(i);
			if (rect.intersects(e.collider)) {
				e.setLevel(level);
				e.teleport(tpLocation.x, tpLocation.y);
			}
		}
	}
	
	public void render() {
//		Main.g.drawRect(rect.x - GameState.camera.x, rect.y - GameState.camera.y, rect.width, rect.height);
//		Main.g.drawRect(tpLocation.x - GameState.camera.x, tpLocation.y - GameState.camera.y, 1, 1);
	}
}
