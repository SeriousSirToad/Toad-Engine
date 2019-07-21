package toad.game;

import toad.game.entities.Player;
import toad.game.level.*;
import toad.gfx.Camera;

public class GameState {

	public static Player player;
	public static Camera camera;
	public static Level currentLevel;
	public static int renderScale = 4;
	public static int levelSeason = 0;
	public static boolean running = false;

	public static void init() {
		Level.bean.tick();
		player = Level.bean.player;
		camera = new Camera(0, 0, player);
		currentLevel = player.getLevel();
		System.out.println(renderScale);
	}

}
