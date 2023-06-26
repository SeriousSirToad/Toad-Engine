package toad.game.entities.npcs;

import java.awt.image.BufferedImage;
import java.util.Random;

import toad.game.Main;
import toad.game.entities.ActionZone;
import toad.game.level.Level;
import toad.io.InputHandler;
import toad.ui.DialogWindow;

public class NPC_generic extends NPC {

	public NPC_generic(Level level, int x, int y, BufferedImage image, int dir, String[] message) {
		super(level, x, y, image, dir);
		name = genericNamer();
		npcwindow = new DialogWindow(name, message[0], 100, 80, "Close");
		zone = new ActionZone(level, collider, npcwindow, Main.input.E);
	}
	
	int iteration = 0;
	@Override
	public void update() {
		
	}

	public String genericNamer() {
		Random random = new Random();
		int next = random.nextInt(4);
		System.out.println("Int = " + next);
		switch (next) {
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
