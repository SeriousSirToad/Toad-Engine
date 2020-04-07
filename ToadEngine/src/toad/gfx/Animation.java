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

	long lastTime = System.currentTimeMillis();

	public BufferedImage animate() {

		long now = System.currentTimeMillis();
		if ((now - lastTime) / 16 >= frameSkip) {
			frameIndex++;
			if (frameIndex >= frames.length) {
				if (skipFirst)
					frameIndex = 1;
				else
					frameIndex = 0;
			}
			lastTime = now;
		}

		return frames[frameIndex];

	}

	public void reset() {

		b = 0;
		frameIndex = 0;

	}

}
