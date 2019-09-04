package toad.game.entities;

import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.level.Level;
import toad.ui.GameWindow;

public class ActionZone extends Entity {
	public Rectangle bounds;
	public GameWindow gw;

	public ActionZone(Level level, int x, int y, int w, int h, GameWindow gw) {
		super(level, x, y, null);
		bounds = new Rectangle(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.gw = gw;
	}

	public ActionZone(Level level, Rectangle bounds, GameWindow gw) {
		super(level, bounds.x, bounds.y, null);
		this.bounds = bounds;
		this.x = bounds.x;
		this.y = bounds.y;
		this.w = bounds.width;
		this.h = bounds.height;
		this.gw = gw;
	}

	@Override
	public void update() {
		if (bounds.intersects(GameState.player.collider)) {
			if (!gw.active)
				gw.activate();
		}
	}

}
