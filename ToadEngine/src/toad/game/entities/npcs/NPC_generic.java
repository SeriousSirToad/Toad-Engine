package toad.game.entities.npcs;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.Clip;

import toad.game.GameState;
import toad.game.Main;
import toad.game.Sound;
import toad.game.entities.ActionZone;
import toad.game.level.Level;
import toad.gfx.Assets;
import toad.ui.InteractionTip;
import toad.ui.DialogWindow;

public class NPC_generic extends NPC {

	ArrayList<Sound> noises = new ArrayList<>();

	public NPC_generic(Level level, int x, int y, BufferedImage image, int dir, String[] message) {
		super(level, x, y, image, dir);
		name = genericNamer();
		npcwindow = new DialogWindow(name, message[0], 100, 80, "Close");
		zone = new ActionZone(level, collider, npcwindow, Main.input.E);
		zone.setInteractionTip(new InteractionTip("E", collider.x + 5, collider.y - 26, 6, 6));
		noises.add(Assets.huh);
		noises.add(Assets.heywazzup);
		noises.add(Assets.whaddayawant1);
		noises.add(Assets.whaddayawant2);
		sound = noises.get(0);
	}

	int iteration = 0;

	boolean audiobool = false;

	public void update() {
		if (collider.intersects(GameState.player.collider)) {
			if (!audiobool) {
				System.out.println("play sound");
				audiobool = true;
				sound = noises.get((int)(Math.random() * 3.49));
				sound.play();
			}
		} else {
			sound.stop();
			audiobool = false;
		}
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
