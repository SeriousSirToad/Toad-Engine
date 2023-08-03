package toad.gfx;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.net.URL;
import java.util.Objects;

public class Assets {

	static SpriteSheet entities = new SpriteSheet("/images/ss_entity.png");
	static SpriteSheet buildings = new SpriteSheet("/images/ss_bldg.png");
	static SpriteSheet texture = new SpriteSheet("/images/ss_texture.png");
	// Mobs
	public static final BufferedImage player = entities.getImage(0, 0, 16, 32);
	public static final Animation pl_hz = new Animation(10, entities.getAnimation(0, 0, 16, 32, 9), true);
	public static final Animation pl_u = new Animation(10, entities.getAnimation(256, 0, 16, 32, 3), true);
	public static final Animation pl_dn = new Animation(10, entities.getAnimation(144, 0, 16, 32, 7), true);
	
	public static final BufferedImage npc1 = entities.getImage(0, 64, 16, 32);
	
	public static final BufferedImage cretin = entities.getImage(0, 48, 16, 16);
	public static final Animation cr_hz = new Animation(10, entities.getAnimation(16, 48, 16, 16, 4), false);
	public static final Animation cr_u = new Animation(10, entities.getAnimation(128, 48, 16, 16, 3), true);
	public static final Animation cr_dn = new Animation(10, entities.getAnimation(80, 48, 16, 16, 3), true);

	public static final Audio cretinNoise = new Audio("/audio/silly_wabble.wav");

	// Flora
	public static BufferedImage getTree(int type) {
		if (type == 1) {
			return entities.getImage(35, 96, 25, 53);
		}
		return entities.getImage(3, 107, 25, 53);
	}

	//Decor
	public static final BufferedImage counter = entities.getImage(0, 160, 32, 32);
	public static final BufferedImage bed = entities.getImage(16, 195, 48, 29);
	
	//Buildings
	public static final BufferedImage shop = buildings.getImage(0, 0, 96, 80);
	public static final BufferedImage pharmacy = buildings.getImage(96, 0, 96, 80);
	public static final BufferedImage popostation = buildings.getImage(192, 0, 96, 80);
	public static final BufferedImage apt_generic = buildings.getImage(0, 160, 96, 128);
	public static final BufferedImage cabin = buildings.getImage(288, 0, 96, 80);
	
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public static BufferedImage[] textures = {texture.getImage(16, 0, 32, 32)};

}
