package toad.game.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import toad.game.GameState;
import toad.game.Item;
import toad.game.Main;
import toad.game.level.Level;
import toad.gfx.Animation;

public abstract class Mob extends Entity {

	public float speed = 1f;
	protected Animation hz;
	protected Animation up;
	protected Animation dn;
	protected String name;
	protected ArrayList<Item> inventory = new ArrayList<>();
	protected boolean isNPC = false;

	public Mob(Level level, int x, int y, BufferedImage image, Animation hz, Animation up, Animation dn) {
		super(level, x, y, image);
		this.hz = hz;
		this.up = up;
		this.dn = dn;
		animations = new Animation[]{hz, up, dn};
		isMob = true;
	}

	public Mob(Level level, int x, int y, BufferedImage image, int dir) {
		super(level, x, y, image);
		isMob = true;
		movingDir = dir;
	}

	public void tick() {
		if (!initialized)
			init();
		update();
	}

	@Override
	public abstract void update();

	public void render() {
		if (GameState.camera.contains(this)) {

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
		Main.g.drawRect(collider.x - GameState.camera.x, collider.y - GameState.camera.y, collider.width, collider.height);;
	}

}
