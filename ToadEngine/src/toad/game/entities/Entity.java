package toad.game.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import toad.game.GameState;
import toad.game.Main;
import toad.game.level.Level;
import toad.gfx.Animation;

public abstract class Entity {

	public BufferedImage image;
	public int x, y;
	public float xf, yf;
	public int w, h;
	int speed = 1;

	public boolean isMob = false;
	protected boolean initialized;
	protected boolean moving = false;

	protected Level level;
	// colliderstuff
	protected boolean isSolid = false;
	public Rectangle collider;
	public Point colliderLoc;

	Animation[] animations;

	protected int movingDir = 0;

	public Entity(Level level, int x, int y, BufferedImage image) {
		this.x = x;
		this.y = y;
		xf = x;
		yf = y;
		this.image = image;
		this.level = level;
		level.entities.add(this);
		if (image != null) {
			w = image.getWidth();
			h = image.getHeight();
		} else {
			w = 0;
			h = 0;
		}
		collider = new Rectangle(x, y + 3 * (h / 4), w, h / 4);
		colliderLoc = colliderLocation();

	}

	public void tick() {
		if (!initialized)
			init();
		update();
	}

	public void move(float xa, float ya) {

		for (Rectangle r : level.colliders) {

			if (GameState.camera.contains(r)) {
				if (xa != 0 && ya != 0) {
					if (!hasCollided(0, Math.round(ya), r)) {
						move(0, ya);
					}
					if (!hasCollided(Math.round(xa), 0, r)) {
						move(xa, 0);
					}
					return;
				} else {

					if (hasCollided(Math.round(xa), Math.round(ya), r)) {
						return;
					}

				}
			}

		}

		if (xa != 0f || ya != 0f) {
			if (xa < 0) {
				movingDir = 0;
			} else if (xa > 0f) {
				movingDir = 2;
			} else if (ya > 0f) {
				movingDir = 1;
			} else {
				movingDir = 3;
			}
			moving = true;
		} else {
			moving = false;
		}

		xf += xa;
		yf += ya;

		x = Math.round(xf);
		y = Math.round(yf);
		collider.x = colliderLoc.x + x;
		collider.y = colliderLoc.y + y;
	}

	public abstract void update();

	public void render() {
		if (GameState.camera.contains(this)) {
			Main.g.drawImage(image, x - GameState.camera.x, y - GameState.camera.y, w, h, null);
		}
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		level.tick();
		this.level.entities.remove(this);
		level.entities.add(this);
		this.level = level;
	}

	public void init() {
		if (isSolid) {
			level.colliders.add(collider);
		}
		initialized = true;
	}

	public void makeCollider(int x, int y, int w, int h) {
		collider = new Rectangle(this.x + x, this.y + y, w, h);
		colliderLoc = colliderLocation();
	}

	public Point colliderLocation() {

		return new Point(collider.x - x, collider.y - y);
	}

	public boolean hasCollided(int xa, int ya, Rectangle other) {

		Rectangle temp = new Rectangle(collider.x + xa + 1, collider.y + ya + 1, collider.width, collider.height);

		if (temp.intersects(other)) {
			return true;
		}

		return false;
	}

	public boolean hasCollided(Rectangle other) {

		if (collider.intersects(other)) {
			return true;
		}

		return false;
	}

}
