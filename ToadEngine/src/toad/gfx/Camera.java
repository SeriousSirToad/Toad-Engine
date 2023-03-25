package toad.gfx;

import java.awt.Color;
import java.awt.Rectangle;

import toad.game.GameState;
import toad.game.Main;
import toad.game.entities.Entity;
import toad.game.entities.Mob;
import toad.game.level.Level;

public class Camera {

	public int x, y;
	private Mob entity; // The entity followed by the camera -- currently always the player
	private Level level;

	/**
	 * Initializes the camera based on the input entity
	 * @param x
	 * @param y
	 * @param attatchedEntity the entity to follow (ex. the player)
	 */
	public Camera(int x, int y, Mob attatchedEntity) {

		this.x = 0;
		this.y = 0;
		this.entity = attatchedEntity;
		this.level = entity.getLevel();

	}

	/**
	 * Initializes the camera based on the input level
	 * @param x
	 * @param y
	 * @param level
	 */
	public Camera(int x, int y, Level level) {
		this.x = 0;
		this.y = 0;
		this.level = level;
	}

	/**
	 * Renders the window displayed to the user
	 */
	public void render() {

		// Default render -- create black window the size of the main JFrame
		Main.g.setColor(Color.black);
		Main.g.fillRect(0, 0, Main.width(), Main.height());

		if (level != null && GameState.running)
			level.render();
		else
			Main.menu.render();

	}

	/**
	 * For every tick call, update the camera position
	 */
	public void tick() {
		if (level != entity.getLevel()) {
			level = entity.getLevel();
		}
		if (entity != null && level != null) {
			followEntity();
		}

		// If the level width is smaller than the main JFrame with
		// Applies to interior levels (ex. Store, apartment, etc.) that are smaller than the main level
		if (level.scaledWidth() <= Main.width()) {
			this.x = ((level.scaledWidth() / 2) - Main.width() / 2) / GameState.renderScale;
		} else {
			// If the player is at the left edge, keep the camera on the level (don't show the void)
			if (x < 0) {
				x = 0;
			}
			// If the player is at the right edge, keep the camera on the level (don't show the void)
			if (x + Main.width() / GameState.renderScale > level.width) {
				x = level.width - Main.width() / GameState.renderScale;
			}
		}

		// If the level height is smaller than the main height
		// Applies to interior levels
		if (level.scaledHeight() <= Main.height()) {
			this.y = ((level.scaledHeight() / 2) - Main.height() / 2) / GameState.renderScale;
		} else {
			// If the player is at the bottom
			if (y < 0) {
				y = 0;
			}
			// If the player is at the top
			if (y + Main.height() / GameState.renderScale > level.height) {
				y = level.height - Main.height() / GameState.renderScale;
			}
		}

	}

	/**
	 * Centers the camera on the entity
	 */
	public void followEntity() {

		int entx = entity.x * GameState.renderScale;
		int enty = entity.y * GameState.renderScale;
		int entw = entity.w * GameState.renderScale;
		int enth = entity.h * GameState.renderScale;

		x = (entx - (Main.WIDTH / 2) + entw / 2) / GameState.renderScale;
		y = (enty - (Main.height() / 2) + enth / 2) / GameState.renderScale;

	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Checks if the following coordinates are contained within the camera fov
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean contains(int x, int y) {
		if (x < this.x || y < this.y)
			return false;
		if (x > this.x + Main.WIDTH || y > this.y + Main.HEIGHT)
			return false;
		return true;
	}

	/**
	 * Checks to see if a particular drawn object r is contained within the current
	 * camera fov
	 * @param r the drawn rectangle object
	 * @return true if the object is contained, false if not
	 */
	public boolean contains(Rectangle r) {
		int ex = r.x * GameState.renderScale;
		int ey = r.y * GameState.renderScale;
		int ew = r.width * GameState.renderScale;
		int eh = r.height * GameState.renderScale;
		if (ex + ew < x || ey + eh < y) {
			return false;
		}
		return true;
	}

	/**
	 * Checks to see if the entity is contained by the camera fov
	 * @param e the entity
	 * @return true if the entity is contained, false if not
	 */
	public boolean contains(Entity e) {
		int ex = e.x * GameState.renderScale;
		int ey = e.y * GameState.renderScale;
		int ew = e.w * GameState.renderScale;
		int eh = e.h * GameState.renderScale;
		if (ex + ew < x || ey + eh < y) {
			return false;
		}
		return true;
	}

}
