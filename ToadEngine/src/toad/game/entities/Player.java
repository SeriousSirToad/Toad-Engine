package toad.game.entities;

import toad.game.Main;
import toad.game.Sound;
import toad.game.level.Level;
import toad.gfx.Assets;
import toad.io.InputHandler;

import static org.lwjgl.openal.AL10.AL_POSITION;
import static org.lwjgl.openal.AL10.alListener3f;

public class Player extends Mob {

	InputHandler input = Main.input;

	public Player(Level level, int x, int y) {
		super(level, x, y, Assets.player, Assets.pl_hz, Assets.pl_u, Assets.pl_dn);
		makeCollider(4, 24, 7, 8);
		speed = 0.75f;
	}

	@Override
	public void update() {

		float xa = 0, ya = 0;

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
		alListener3f(AL_POSITION, xf, yf, 0); // player is the listener
	}

}
