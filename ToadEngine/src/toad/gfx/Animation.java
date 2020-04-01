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
		this.skipFirst = skipFirst;
		frames = images;
		Main.main.animations.add(this);

	}

	byte b = 0;

	public BufferedImage animate() {

		return frames[frameIndex];

	}

	public void update() {
		b++;

		if (b >= frameSkip) {

			frameIndex++;
			b = 0;

		}

		if (frameIndex >= frames.length) {
			if (skipFirst)
				frameIndex = 1;
			else
				frameIndex = 0;
		}
	}

	public void reset() {

		b = 0;
		frameIndex = 0;

	}

}
