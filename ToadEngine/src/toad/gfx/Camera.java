package toad.gfx;

import java.awt.Color;

import toad.game.GameState;
import toad.game.Main;
import toad.game.entities.Entity;
import toad.game.entities.Mob;
import toad.game.level.Level;

public class Camera {

	public int x, y;
	private Mob entity;
	private Level level;

	public Camera(int x, int y, Mob attatchedEntity) {

		this.x = 0;
		this.y = 0;
		this.entity = attatchedEntity;
		this.level = entity.getLevel();

	}

	public Camera(int x, int y, Level level) {
		this.x = 0;
		this.y = 0;
		this.level = level;
	}

	public void render() {
		Main.g.setColor(Color.black);
		Main.g.fillRect(0, 0, Main.width(), Main.height());
		if (level != null && GameState.running)
			level.render();
		else
			Main.menu.render();

	}

	public void tick() {
		if (level != entity.getLevel()) {
			level = entity.getLevel();
		}
		if (entity != null && level != null) {
			followEntity();
		}

		if (level.getWidth() <= Main.width()) {
			this.x = ((level.getWidth() / 2) - Main.width() / 2) / GameState.renderScale;
		}
		if (level.getHeight() <= Main.height()) {
			this.y = ((level.getHeight() / 2) - Main.height() / 2) / GameState.renderScale;
		}
	}

	public void followEntity() {

		int entx = entity.x * GameState.renderScale;
		int enty = entity.y * GameState.renderScale;
		int entw = entity.w * GameState.renderScale;
		int enth = entity.h * GameState.renderScale;

		if (x > -1 && entx > (Main.WIDTH / 2) - (entw / 2))
			x = (entx - (Main.WIDTH / 2) + entw / 2) / GameState.renderScale;
		else
			x = 0;

		if (y > -1 && enty > (Main.height() / 2) - (enth / 2))
			y = (enty - (Main.height() / 2) + enth / 2) / GameState.renderScale;
		else
			y = 0;
	}

	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean contains(int x, int y) {
		if (x < this.x || y < this.y)
			return false;
		if (x > this.x + Main.width() || y > this.y + Main.height())
			return false;
		return true;
	}

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
