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

		if (level.scaledWidth() <= Main.width()) {
			this.x = ((level.scaledWidth() / 2) - Main.width() / 2) / GameState.renderScale;
		} else {
			if (x < 0) {
				x = 0;
			}
			if (x + Main.width() / GameState.renderScale > level.width) {
				x = level.width - Main.width() / GameState.renderScale;
			}
		}

		if (level.scaledHeight() <= Main.height()) {
			this.y = ((level.scaledHeight() / 2) - Main.height() / 2) / GameState.renderScale;
		} else {
			if (y < 0) {
				y = 0;
			}
			if (y + Main.height() / GameState.renderScale > level.height) {
				y = level.height - Main.height() / GameState.renderScale;
			}
		}

	}

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

	public boolean contains(int x, int y) {
		if (x < this.x || y < this.y)
			return false;
		if (x > this.x + Main.WIDTH || y > this.y + Main.HEIGHT)
			return false;
		return true;
	}

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
