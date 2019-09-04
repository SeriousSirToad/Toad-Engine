package toad.game.entities.objects;

import java.awt.image.BufferedImage;

import toad.game.entities.Entity;
import toad.game.level.Level;

public class GameObject extends Entity {

	public GameObject(Level level, int x, int y, BufferedImage image) {
		super(level, x, y, image);
		this.isSolid = true;
	}

	@Override
	public void update() {
	}

}
