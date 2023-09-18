package toad.gfx;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import toad.game.GameState;
import toad.game.Main;
import toad.game.level.Level;

public class Shader {

	private BufferedImage lightMap0 = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB); // day
	private BufferedImage lightMap1 = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB); // nirbubg
																												// rvrtnhign
	private BufferedImage lightMap2 = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB); // dark
	ArrayList<Light> lights = new ArrayList<Light>();
	public Color ambientColor;
	int ambColR = 0;
	int ambColG = 0;
	int ambColB = 0;
	double intensity = 0;
	boolean enabled = true;
	Level level;
	// RadialGradientPaint p = new RadialGradientPaint(center, radius, fractions,
	// colors)

	public Shader(Color ambientColor, double intensity, Level level) {
		this.ambientColor = ambientColor;
		this.level = level;
		this.intensity = intensity * 255;
		makeLightMap(lightMap0, 0);
		makeLightMap(lightMap1, 1);
		makeLightMap(lightMap2, 2);
	}

	public class Light {
		private BufferedImage image;
		private int x;
		private int y;
		private int radius;
		private Color color;
		public Light(int x, int y, int radius, Color color) {
			// Recommended luminosity between 1 and 2
			this.x = x;
			this.y = y;
			this.radius = radius;
			// Graphics2D g = (Graphics2D) image.getGraphics();
			this.color = color;
			lights.add(this);
			System.out.println("bloob");
		}

		public void render(Graphics2D g2) {
			g2.setColor(color);
			g2.setComposite(AlphaComposite.Clear);
			System.out.println(color.getRed());
			g2.fillRect(x, y, radius, radius);
		}
	}

	public void makeLightMap(BufferedImage lightMap, int map) {
		Graphics2D gl = lightMap.createGraphics();
		Composite original = gl.getComposite();
		for (Light light : lights)
			light.render(gl);
		gl.setComposite(original);
		switch (map) {
		case 0:
			gl.setColor(new Color(0, 0, 0, 0));
			gl.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
			break;
		case 1:
			gl.setColor(new Color(0, 0, 0, 70));
			gl.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
			break;
		case 2:
			gl.setColor(new Color(0, 0, 0, 110));
			gl.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
			break;
		}
		
		// gl.setComposite(oldComp);
		gl.dispose();
	}

	boolean timecheck = false;

	public void update(Graphics g) {
		if (enabled) {
			if (GameState.time >= 360 && GameState.time < 720)
				Main.g.drawImage(lightMap0, -GameState.camera.x, -GameState.camera.y, null);
			else if (GameState.time < 360 || GameState.time >= 720 && GameState.time < 1080)
				Main.g.drawImage(lightMap1, -GameState.camera.x, -GameState.camera.y, null);
			else
				Main.g.drawImage(lightMap2, -GameState.camera.x, -GameState.camera.y, null);
		}
	}

	public void addLight(int x, int y, int radius) {
		lights.add(new Light(x, y, radius, new Color(255, 255, 255, 0)));
		makeLightMap(lightMap0, 0);
		makeLightMap(lightMap1, 1);
		makeLightMap(lightMap2, 2);
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
