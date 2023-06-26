package toad.game.entities;

import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.level.Level;
import toad.io.InputHandler;
import toad.ui.GameWindow;

public class ActionZone extends Entity {
	public Rectangle bounds;
	public GameWindow gw, dialogTip;
	public InputHandler.Key key;

	public ActionZone(Level level, Rectangle bounds, GameWindow gw, InputHandler.Key key) {
		super(level, bounds.x, bounds.y, null);
		this.bounds = bounds;
		this.x = bounds.x;
		this.y = bounds.y;
		this.w = bounds.width;
		this.h = bounds.height;
		this.gw = gw;
		this.key = key;

		dialogTip = new GameWindow("", "'E'", 5, 5, null);
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
			if (!gw.active) dialogTip.activate();
			if (key != null && key.pressedAndReleased())
				if (!gw.active) {
					gw.activate();
					dialogTip.deactivate();
				}
			if (key == null)
				if (!gw.active)
					gw.activate();
		}
		
		if (!bounds.intersects(GameState.player.collider) && (gw.active || dialogTip.active) ){
			gw.deactivate();
			dialogTip.deactivate();
		} 
	}
}
