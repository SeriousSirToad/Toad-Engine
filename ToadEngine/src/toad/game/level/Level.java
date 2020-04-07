package toad.game.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;

import javax.imageio.ImageIO;

import toad.game.GameState;
import toad.game.Main;
import toad.game.entities.Entity;
import toad.game.entities.Player;
import toad.gfx.Shader;

public abstract class Level {

	public boolean initialized = false;
	protected boolean interior = false;

	public Player player;
	public BufferedImage image;
	public int time = 0;
	public int season = 1; // 0 is winter, 1 is spring, 2 = summer, 3 = fall
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Rectangle> colliders = new ArrayList<Rectangle>();
	public ArrayList<Door> doors = new ArrayList<Door>();
	public Rectangle topwall;
	Rectangle leftwall;
	Rectangle rightwall;
	Rectangle bottomwall;

	public static Level bean = new lvl_bean();
	public static Level test = new lvl_test();
	public int width = 0;
	public int height = 0;

	private Comparator<Entity> entitySorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if (a.y + a.h < b.y + b.h)
				return -1;
			return 1;
		}
	};

	public void tick() {

		if (!initialized) {
			init();
			topwall = new Rectangle(0, 0, width, 24);
			leftwall = new Rectangle(-1, 0, 1, height);
			rightwall = new Rectangle(width, 0, 1, height);
			bottomwall = new Rectangle(0, height+1, width, 1);
			colliders.add(topwall);
			colliders.add(leftwall);
			colliders.add(rightwall);
			colliders.add(bottomwall);
			initialized = true;
			return;
		}

		for (Door d : doors) {
			d.tick();
		}

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}

	}

	public Shader shader = new Shader(new Color(0, 0, 0), 0.0);

	public void render() {
		Graphics g = Main.g;
		g.drawImage(image, 0 - GameState.camera.x, 0 - GameState.camera.y, width, height, null);
		entities.sort(entitySorter);
		for (Entity e : entities) {
			e.render();
		}
		shader.update();
	}

	public Level(String imagePath) {
		URL path = getClass().getResource(imagePath);
		System.out.println(path);
		try {
			image = ImageIO.read(getClass().getResource(imagePath));
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void init();

	public int scaledWidth() {
		return (int) (width * GameState.renderScale);
	}

	public int scaledHeight() {
		return (int) (height * GameState.renderScale);
	}

}
