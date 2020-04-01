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


}
