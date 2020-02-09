package toad.gfx;

import java.awt.image.BufferedImage;

import toad.game.Main;

public class Animation {

	public BufferedImage[] frames;
	public int frameSkip;
	private int frameIndex;
	private boolean skipFirst = false;

	public Animation(int j, BufferedImage[] images, boolean skipFirst) {

		frames = new BufferedImage[images.length];
		this.frameSkip = j;
		if (images.length < 2) {
			this.skipFirst = false;
		} else {
			this.skipFirst = skipFirst;
		}
		frames = images;
		Main.main.animations.add(this);
		
	}

	byte b = 0;

	public BufferedImage animate() {

		return frames[frameIndex];

	}

	public void update() {
		b++;

		if (skipFirst && frameIndex == 0) {
			frameIndex++;
		}

		if (b >= frameSkip) {

			frameIndex++;
			b = 0;

		}

		if (frameIndex >= frames.length) {
			frameIndex = 0;
		}
	}

	public void reset() {

		b = 0;
		frameIndex = 0;

	}

}
