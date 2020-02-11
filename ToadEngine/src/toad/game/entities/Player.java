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
	Animation dnr;

	public Player(Level level, int x, int y) {
		super(level, x, y, Assets.player, Assets.pl_hz, Assets.pl_u, Assets.pl_dn);
		upr = Assets.pl_ur;
		dnr = Assets.pl_dr;
		makeCollider(4, 24, 7, 8);

	}

	@Override
	public void update() {

		int xa = 0, ya = 0;

		if (input.A.isPressed()) {
			xa -= speed;
			HZDir = xa;
		}
		if (input.W.isPressed()) {
			ya -= speed;
			VDir = ya;
		}
		if (input.D.isPressed()) {
			xa += speed;
			HZDir = xa;
		}
		if (input.S.isPressed()) {
			ya += speed;
			VDir = ya;
		}

		move(xa, ya);

	}

	public void render() {
		if (!moving) {
			if (VDir == 1) {
				if (HZDir == 1) {
					Main.g.drawImage(image, x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else if (HZDir == -1) {
					Main.g.drawImage(image, x - GameState.camera.x + (w), y - GameState.camera.y, -(w), h, null);
				} else {
					Main.g.drawImage(dn.frames[0], x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				}
			} else {
				if (HZDir == 1) {
					Main.g.drawImage(upr.frames[0], x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else if (HZDir == -1) {
					Main.g.drawImage(upr.frames[0], x - GameState.camera.x + (w), y - GameState.camera.y, -(w), h,
							null);
				} else {
					Main.g.drawImage(up.frames[0], x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				}
			}

		} else {
			if (VDir == 1) {
				if (HZDir == 1) {
					Main.g.drawImage(dnr.animate(), x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else if (HZDir == -1) {
					Main.g.drawImage(dnr.animate(), x - GameState.camera.x + (w), y - GameState.camera.y, -(w), h,
							null);
				} else {
					Main.g.drawImage(dn.animate(), x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				}
			} else {
				if (HZDir == 1) {
					Main.g.drawImage(upr.animate(), x - GameState.camera.x, y - GameState.camera.y, w, h, null);
				} else if (HZDir == -1) {
					Main.g.drawImage(upr.animate(), x - GameState.camera.x + (w), y - GameState.camera.y, -(w), h,
							null);
				}
			}
		}
	}

}
