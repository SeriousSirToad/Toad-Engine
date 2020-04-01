package toad.gfx;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteSheet {

	public BufferedImage sheet;

	public SpriteSheet(String imagePath) {

		try {
			sheet = ImageIO.read(getClass().getResource(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public BufferedImage getImage(int x, int y, int w, int h) {
		return sheet.getSubimage(x, y, w, h);
	}

	public BufferedImage[] getAnimation(int startX, int startY, int cellW, int cellH, int numFrames) {

		BufferedImage[] frames = new BufferedImage[numFrames];
		for (int i = 0; i < numFrames; i++) {

			frames[i] = sheet.getSubimage(startX + (i * cellW), startY, cellW, cellH);

		}

		return frames;
	}
	
	public BufferedImage[] getManualAnimation(ArrayList<Rectangle> frames) {
		BufferedImage[] anim = new BufferedImage[frames.size()];
		for (int i = 0; i < frames.size(); i++) {

			anim[i] = sheet.getSubimage(frames.get(i).x, frames.get(i).y, frames.get(i).width, frames.get(i).height);

		}
		return anim;
	}

}
