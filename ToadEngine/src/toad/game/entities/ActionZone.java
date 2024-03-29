package toad.game.entities;

import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.level.Level;
import toad.io.InputHandler;
import toad.ui.InteractionTip;
import toad.ui.GameWindow;

public class ActionZone extends Entity {
	public Rectangle bounds;
	public GameWindow gw;
	public InputHandler.Key key;
	public InteractionTip interactionTip;

	public ActionZone(Level level, Rectangle bounds, GameWindow gw, InputHandler.Key key) {
		super(level, bounds.x, bounds.y, null);
		this.bounds = bounds;
		this.x = bounds.x;
		this.y = bounds.y;
		this.w = bounds.width;
		this.h = bounds.height;
		this.gw = gw;
		this.key = key;
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
			if (key != null && key.pressedAndReleased())
				if (!gw.active) {
					gw.activate();
				}
			if (key == null)
				if (!gw.active)
					gw.activate();
		}

		if (!bounds.intersects(GameState.player.collider) && (gw.active)) {
			gw.deactivate();
		}

		if (interactionTip != null)
			updateTip();
	}

	private void updateTip() {
		if (bounds.intersects(GameState.player.collider)) {
			if (!gw.active) {
				if (!interactionTip.active)
					interactionTip.activate();

			} else
				interactionTip.deactivate();
		}

		if (!bounds.intersects(GameState.player.collider) && (gw.active || interactionTip.active)) {
			interactionTip.deactivate();
		}
	}

	public void setInteractionTip(InteractionTip tip) {
		interactionTip = tip;
	}
}
