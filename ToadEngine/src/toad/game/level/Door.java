package toad.game.level;

import java.awt.Point;
import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.entities.Player;

public class Door {

	Rectangle rect;
	Level nativeLevel;
	Level level;
	Point tpLocation;

	public Door(Level nativeLevel, Rectangle rect, Level level, Point tpLocation) {

		this.rect = rect;
		this.level = level;
		this.tpLocation = tpLocation;
		nativeLevel.doors.add(this);

	}

	public void tick() {
		if (!GameState.camera.contains(rect.x, rect.y)) {
			return;
		}
		Player player = GameState.player;
		if (rect.intersects(player.collider)) {
			player.setLevel(level);
			player.x = (int) tpLocation.getX();
			player.y = (int) tpLocation.getY();
			player.collider = new Rectangle((int) tpLocation.getX() + 4, (int) tpLocation.getY() + 24, 7, 8);
		}
	}

}
