package toad.game.entities;

import toad.game.GameState;
import toad.game.Main;
import toad.game.level.Level;
import toad.gfx.Animation;
import toad.gfx.Assets;
import toad.io.InputHandler;

public class Player extends Mob {

	InputHandler input = Main.input;

	Animation upr;
	
	public Player(Level level, int x, int y) {
		super(level, x, y, Assets.player, Assets.pl_hz, Assets.pl_u, Assets.pl_dn);
		upr = Assets.pl_ur;
		makeCollider(4, 24, 7, 8);
		
	}

	@Override
	public void update() {

		int xa = 0, ya = 0;

		if (input.A.isPressed()) {
			xa -= speed;
		}
		if (input.W.isPressed()) {
			ya -= speed;
		}
		if (input.D.isPressed()) {
			xa += speed;
		}
		if (input.S.isPressed()) {
			ya += speed;
		}

		move(xa, ya);

	}
	
	public void render() {
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
