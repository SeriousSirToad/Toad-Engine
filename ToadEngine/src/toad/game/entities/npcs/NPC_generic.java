package toad.game.entities.npcs;

import java.awt.image.BufferedImage;

import toad.game.GameState;
import toad.game.Main;
import toad.game.entities.Mob;
import toad.game.level.Level;
import toad.ui.GameWindow;

public class NPC_generic extends Mob {

	GameWindow npcwindow;

	public NPC_generic(Level level, int x, int y, BufferedImage image, String message) {
		super(level, x, y, image);
		npcwindow = new GameWindow(Main.width(), Main.height(), 0xFFFF0000, null);
		npcwindow.addMessage(0, 0, message);
		name = genericNamer();
	}

	@Override
	public void update() {

		if (hasCollided(GameState.player.collider)) {
			npcwindow.activate();
		}

	}

	public String genericNamer() {
		return "Bob";
	}

}
