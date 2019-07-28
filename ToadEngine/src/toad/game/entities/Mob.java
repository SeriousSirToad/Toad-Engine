package toad.game.entities;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import toad.game.GameState;
import toad.game.Item;
import toad.game.Main;
import toad.game.level.Level;
import toad.gfx.Animation;

public abstract class Mob extends Entity {

	public int speed = 1;
	protected boolean moving = false;
	protected Animation hz;
	protected Animation up;
	protected Animation dn;
	protected int movingDir = 0;
	protected String name;
	protected ArrayList<Item> inventory = new ArrayList<>();

	public Mob(Level level, int x, int y, BufferedImage image, Animation hz, Animation up, Animation dn) {
		super(level, x, y, image);
		this.hz = hz;
		this.up = up;
		this.dn = dn;
		isMob = true;
	}

	public Mob(Level level, int x, int y, BufferedImage image) {
		super(level, x, y, image);
		isMob = true;
	}

	public void tick() {
		if (!initialized)
			init();
		if (moving) {
			hz.update();
			up.update();
			dn.update();
		}
		update();
	}

	@Override
	public abstract void update();

	public void move(int xa, int ya) {

		for (Rectangle r : level.colliders) {

			if (xa != 0 && ya != 0) {
				if (!hasCollided(0, ya, r)) {
					move(0, ya);
				}
				if (!hasCollided(xa, 0, r)) {
					move(xa, 0);
				}

				return;
			} else {
				if (hasCollided(xa, ya, r)) {
					return;
				}
			}
		}

		if (xa != 0 || ya != 0) {
			if (xa < 0) {
				movingDir = 0;
			} else if (xa > 0)
				movingDir = 2;
			else if (ya > 0)
				movingDir = 1;
			else
				movingDir = 3;
			moving = true;
		} else {
			moving = false;
		}

		x += xa;
		y += ya;
		collider.x += xa;
		collider.y += ya;

		if (x < 0) {
			x += speed;
			collider.x += speed;
		}
		if (x + w > level.getWidth()) {
			x -= speed;
			collider.x -= speed;
		}
		if (y < 0) {
			y += speed;
			collider.y += speed;
		}
		if (y + h > level.getHeight()) {
			y -= speed;
			collider.y -= speed;
		}

	}

	public boolean hasCollided(int xa, int ya, Rectangle other) {

		Rectangle temp = new Rectangle(collider.x + xa, collider.y + ya, collider.width, collider.height);

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

	public void render() {
		if (GameState.camera.contains(this)) {

			if (name != null) {
				Main.g.setColor(Color.white);
				Main.g.drawString(name, x - GameState.camera.x, y - GameState.camera.y);
				Main.g.drawString("yeah fr tho", 0, 10);
			}

			if (!moving) {
				if (movingDir == 2) {
					Main.g.drawImage(image, x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else if (movingDir == 0) {
					Main.g.drawImage(image, x - GameState.camera.x + (w), y - GameState.camera.y, -(w), h, null);
				} else if (movingDir == 3) {
					Main.g.drawImage(up.frames[0], x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else {
					Main.g.drawImage(dn.frames[0], x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				}

			} else {
				if (movingDir == 2) {
					Main.g.drawImage(hz.animate(), x - GameState.camera.x, y - GameState.camera.y, (w), h, null);
				} else if (movingDir == 0) {
					Main.g.drawImage(hz.animate(), x - GameState.camera.x + (w), y - GameState.camera.y, -(w), h, null);
				} else if (movingDir == 3) {
					Main.g.drawImage(up.animate(), x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else {
					Main.g.drawImage(dn.animate(), x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				}
			}

		}
	}

}
