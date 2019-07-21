package toad.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	static SpriteSheet entities = new SpriteSheet("res/ss_entity.png");
	static SpriteSheet buildings = new SpriteSheet("res/ss_bldg.png");
	// Mobs
	public static final BufferedImage player = entities.getImage(0, 0, 16, 32);
	public static final Animation pl_hz = new Animation(10, entities.getAnimation(16, 0, 16, 32, 4), false);
	public static final Animation pl_u = new Animation(20, entities.getAnimation(128, 0, 16, 32, 3), true);
	public static final Animation pl_dn = new Animation(20, entities.getAnimation(80, 0, 16, 32, 3), true);
	
	public static final BufferedImage cretin = entities.getImage(0, 48, 16, 16);
	public static final Animation cr_hz = new Animation(10, entities.getAnimation(16, 48, 16, 32, 4), false);
	public static final Animation cr_u = new Animation(10, entities.getAnimation(128, 48, 16, 32, 3), true);
	public static final Animation cr_dn = new Animation(10, entities.getAnimation(80, 48, 16, 32, 3), true);

	// Flora
	public static BufferedImage getTree(int type) {
		switch (type) {
		case 1:
			return entities.getImage(32, 96, 32, 64);
		default:
			return entities.getImage(0, 96, 32, 64);
		}
	}

	//Decor
	public static final BufferedImage counter = entities.getImage(0, 160, 32, 32);
	
	//Buildings
	public static final BufferedImage shop = buildings.getImage(0, 0, 96, 80);
	public static final BufferedImage pharmacy = buildings.getImage(96, 0, 96, 80);
	public static final BufferedImage popostation = buildings.getImage(192, 0, 96, 80);
	public static final BufferedImage cabin = buildings.getImage(288, 0, 96, 80);

}
