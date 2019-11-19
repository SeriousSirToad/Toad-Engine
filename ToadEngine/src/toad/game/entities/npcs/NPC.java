package toad.game.entities.npcs;

import java.awt.image.BufferedImage;

import toad.game.entities.ActionZone;
import toad.game.entities.Mob;
import toad.game.level.Level;
import toad.gfx.Animation;
import toad.ui.GameWindow;

public class NPC extends Mob {

	GameWindow npcwindow;
	ActionZone zone;
	
	public NPC(Level level, int x, int y, BufferedImage image, Animation hz, Animation up, Animation dn) {
		super(level, x, y, image, hz, up, dn);
	}
	public NPC(Level level, int x, int y, BufferedImage image, int dir) {
		super(level, x, y, image, dir);
	}
	

	public void setName(String name) {
		this.name = name;
		npcwindow.setTitle(name);
	}
	

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
