package toad.gfx;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import toad.game.GameState;
import toad.game.Main;

public class Shader {

	private BufferedImage lightMap = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);
	ArrayList<Light> lights = new ArrayList<Light>();
	public Color ambientColor;
	int ambColR = 0;
	int ambColG = 0;
	int ambColB = 0;
	double intensity = 0;
	boolean enabled = false;
	// RadialGradientPaint p = new RadialGradientPaint(center, radius, fractions,
	// colors)

	public Shader(Color ambientColor, double intensity) {
		this.ambientColor = ambientColor;
		ambColB = ambientColor.getBlue();
		ambColG = ambientColor.getGreen();
		ambColR = ambientColor.getRed();
		this.intensity = intensity * 255;
		makeLightMap(lightMap);
	}

	public class Light {
		private BufferedImage image;
		private int x;
		private int y;

		public Light(int x, int y, int radius, float luminosity, Color color) {
			// Recommended luminosity between 1 and 2
			this.x = x;
			this.y = y;
			image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = (Graphics2D) image.getGraphics();

			for (int i = 0; i < radius; i++) {
				double luma = 1.0D - ((i + 0.0001) / radius);
				int alpha = (int) Math.min((255 * luma * luminosity), 255);
				g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
				g.setStroke(new BasicStroke(2));
				g.drawOval(radius - i, radius - i, i * 2, i * 2);
			}
		}

		public void render(Graphics2D g2) {
			g2.drawImage(image, x - image.getWidth() / 2, y - image.getHeight() / 2, image.getWidth(),
					image.getHeight(), null);
		}
	}

	public void makeLightMap(BufferedImage lightMap) {
		Graphics2D gl = lightMap.createGraphics();
		gl.setColor(new Color(ambColR, ambColG, ambColB, (int) intensity));
		gl.fillRect(0, 0, Main.width(), Main.height());
		Composite oldComp = gl.getComposite();
		gl.setComposite(AlphaComposite.DstOut);

		for (Light light : lights)
			light.render(gl);

		gl.setComposite(oldComp);
		gl.dispose();
	}

	public void update() {
		if (enabled)
			Main.g.drawImage(lightMap, -GameState.camera.x, -GameState.camera.y, null);
	}

	public void addLight(int x, int y, int radius, float luminosity) {
		lights.add(new Light(x, y, radius, luminosity, Color.blue));
		makeLightMap(lightMap);
	}
	
	public void disable() {
		enabled = false;
	}

	public void enable() {
		enabled = true;
	}
	
	
	public void toggleShader() {
		enabled = !enabled;
	}
	
	public boolean enabled() {
		return enabled;
	}
}
