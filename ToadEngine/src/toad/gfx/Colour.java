package toad.gfx;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Colour {

	public static BufferedImage replaceColor(BufferedImage image, Color oldColor) {
		BufferedImage newImage = Assets.deepCopy(image);
		for (int y = 0; y < newImage.getHeight(); y++) {
			for (int x = 0; x < newImage.getWidth(); x++) {
				int color = newImage.getRGB(x, y);
				if (color == oldColor.getRGB()) {
					System.out.println(color);
					newImage.setRGB(x, y, 00);
				}
			}
		}
		return newImage;
	}

	public static BufferedImage darkerImage(BufferedImage Image) {

		BufferedImage image = Assets.deepCopy(Image);

		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				// new Color(image.getRGB(x, y)).darker().getRGB()
				image.setRGB(x, y, new Color(image.getRGB(x, y)).darker().getRGB());
			}
		}

		return image;

	}

}