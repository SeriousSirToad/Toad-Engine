package toad.game.level;

import java.awt.Point;
import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.entities.Entity;
import toad.game.entities.Player;
import toad.ui.InGameUI;

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
			Entity e = level.entities.get(i);
			InGameUI.replaceRenderOrder("hello", "what");
			if (rect.intersects(e.collider)) {
				InGameUI.replaceRenderOrder("what", "hello");
				e.setLevel(level);
				e.x = (int) tpLocation.getX();
				e.y = (int) tpLocation.getY();
			}
		}
	}

}
