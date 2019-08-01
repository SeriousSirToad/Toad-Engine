package toad.game.entities.npcs;

import java.awt.image.BufferedImage;
import java.util.Random;

import toad.game.GameState;
import toad.game.entities.Mob;
import toad.game.level.Level;
import toad.ui.GameWindow;

public class NPC_generic extends Mob {

	GameWindow npcwindow;

	public NPC_generic(Level level, int x, int y, BufferedImage image, String[] message) {
		super(level, x, y, image);
		name = genericNamer();
		npcwindow = new GameWindow(name, message[0], 100, 80, null);
	}

	int iteration = 0;
	@Override
	public void update() {

		if (hasCollided(GameState.player.collider)) {
			if (!npcwindow.active) {
				npcwindow.activate();
				
			}
		}

	}

	public String genericNamer() {
		Random random = new Random(4);
		switch (random.nextInt()) {
		case 0:
			return "Dolly";
		case 1:
			return "James";
		case 2:
			return "Jacob";
		case 3:
			return "Ian";
		default:
			break;
		}
		return "Bob";
	}

}
