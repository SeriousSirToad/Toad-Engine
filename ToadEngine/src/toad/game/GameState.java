package toad.game;

import toad.game.entities.Player;
import toad.game.level.Level;
import toad.gfx.Camera;
import toad.ui.GameWindow;

public class GameState {

	public static Player player;
	public static Camera camera;
	public static Level currentLevel;
	public static int renderScale = 4;
	public static int levelSeason = 0;
	public static boolean running = false;
	private static final String welcomeMessage = "You're playing the super duper pre\n"
											   + "alpha. Press the button to close this\n"
											   + "window. Press E to interact with the\n"
											   + "bed when you find it";
	public static GameWindow welcome = new GameWindow("Welcome to game", welcomeMessage, 80, 60, null);

	public static void init() {
		Level.bean.tick();
		player = Level.bean.player;
		camera = new Camera(0, 0, player);
		currentLevel = player.getLevel();
	}

	public static int gameWidth() {
		return Main.WIDTH / renderScale;
	}

	public static int gameHeight() {
		return Main.HEIGHT / renderScale;
	}

}
