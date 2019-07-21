package toad.game.level;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import javax.imageio.ImageIO;

import toad.game.GameState;
import toad.game.Main;
import toad.game.entities.Entity;
import toad.game.entities.Player;

public abstract class Level {

	public boolean initialized = false;
	public Player player;
	public BufferedImage image;
	public int season = 0; // 0 is winter, 1 is spring, 2 = summer, 3 = fall
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Rectangle> colliders = new ArrayList<Rectangle>();
	public ArrayList<Door> doors = new ArrayList<Door>();

	public static Level bean = new lvl_bean();
	public static Level test = new lvl_test();
	protected int width = 0;
	protected int height = 0;

	private Comparator<Entity> entitySorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {
			if (a.y + a.h < b.y + b.h)
				return -1;
			return 1;
		}
	};

	public void tick() {
		for (Door d : doors) {
			d.tick();
		}
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		if (!initialized) {
			init();
			initialized = true;
		}
	}

	public void render() {
		Graphics g = Main.g;
		g.drawImage(image, 0 - GameState.camera.x, 0 - GameState.camera.y, width, height, null);
		entities.sort(entitySorter);
		for (Entity e : entities) {
			e.render();
		}
	}

	public Level(String imagePath) {
		try {
			image = ImageIO.read(new File(imagePath));
			width = image.getWidth();
			height = image.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public abstract void init();

	public int getWidth() {
		return (int) (width * GameState.renderScale);
	}

	public int getHeight() {
		return (int) (height * GameState.renderScale);
	}

}
