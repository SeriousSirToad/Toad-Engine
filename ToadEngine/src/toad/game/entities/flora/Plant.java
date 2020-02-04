package toad.game.entities.flora;

import java.awt.image.BufferedImage;

import toad.game.entities.Entity;
import toad.game.level.Level;

public class Plant extends Entity {

	private int daysOld;
	private int ageThreshold;
	protected boolean grown;
	
	BufferedImage image_baby, image_grown;
	
	public Plant(Level level, int x, int y, BufferedImage image_baby, BufferedImage image_grown) {
		super(level, x, y, image_baby);
		this.image_baby = image_baby;
		this.image_grown = image_grown;
		daysOld = (int) Math.random() * 10;
		ageThreshold = 40;
	}

	@Override
	public void update() {
		if(grown) return;
		if(daysOld >= ageThreshold) {
			image = image_grown;
			grown = true;
		}
	}

}
