package toad.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.ArrayList;

import javax.swing.JFrame;

import toad.dev.DevOptions;
import toad.gfx.Animation;
import toad.io.InputHandler;
import toad.ui.InGameUI;

public class Main extends Canvas implements Runnable {

	@Serial
	private static final long serialVersionUID = -5198863677834462653L;

	public int tickCount;

	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	private static final String NAME = "TrainEngine";

	public boolean running = false;
	public JFrame frame = new JFrame();
	static Dimension gameDimension;
	public static InputHandler input;

	public static boolean devOptions = true;
	public DevOptions devWindow;

	public static ArrayList<Animation> animations = new ArrayList<>();
	
	public static Menu menu;

	public Main() {

		gameDimension = new Dimension(WIDTH, HEIGHT);
		frame.setTitle(NAME);
		frame.setSize(gameDimension);
		frame.setPreferredSize(gameDimension);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		input = new InputHandler(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println("Game Dimensions: " + WIDTH + ", " + HEIGHT);

		menu = new Menu();

	}

	String playerCoords;

	public static int frames = 0;

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60;
		@SuppressWarnings("unused")
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		double delta2 = 0;

		while (running) {
			
			if (!stateInit) {
				stateInit = true;
				GameState.init();
			}
			long now = System.nanoTime();
			double renderTime = 1000000000D / 60;
			delta += (now - lastTime) / nsPerTick;
			delta2 += (now - lastTime) / renderTime;
			lastTime = now;
			boolean shouldRender = true;
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
			}

			while (delta2 >= 1) {
				delta2 -= 1;
				shouldRender = true;
			}

		//	try {
		//		Thread.sleep(1);
		//	} catch (InterruptedException e) {
		//		e.printStackTrace();
		//	}

			if (shouldRender) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				if (firstFPS) {
					InGameUI.replaceRenderOrder("FPS: " + this.frames, "FPS: " + frames);
				}
				this.frames = frames;
				ticks = 0;
				frames = 0;
			}

		}
	}

	boolean stateInit = false;

	String oldPlayerCoords;
	boolean firstFPS = false;

	public void tick() {

		tickCount++;

		if (GameState.running) {
			GameState.player.getLevel().tick();
			GameState.camera.tick();

			if (devOptions) {
				if (!firstFPS) {
					firstFPS = true;
					InGameUI.addToRendOrder("FPS: " + frames);
				}
			}

			if (input.esc.isPressed()) {
				GameState.running = false;
				InGameUI.clearRenderOrder();
			}

		} else {
			menu.tick();
		}
		
		if (devOptions) {
			playerCoords = "x " + GameState.player.x + ", y " + GameState.player.y;
			if (showCoords && !oldPlayerCoords.equals(null)) {
				InGameUI.replaceRenderOrder(oldPlayerCoords, playerCoords);
			}
			oldPlayerCoords = playerCoords;
		}
		
		if (devOptions) {
			devWindow.render();
		}

	}

	BufferStrategy bs;
	public static Graphics2D g;

	int xOffset = 0;
	int yOffset = 0;

	boolean showCoords = false;
	
	public void render() {

		// Creating graphics object
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		// General Rendering

		g.clearRect(0, 0, width(), height());
		g.scale(GameState.renderScale, GameState.renderScale);
		g.setFont(InGameUI.standardFont);
		GameState.camera.render();
		if (GameState.running) {
			InGameUI.render(0, g);
			InGameUI.render(1, g);
		}

		bs.show();
		g.dispose();
	}

	public void start() {
		running = true;
		new Thread(this).start();
		if (devOptions)
			devWindow = new DevOptions();
	}

	public static int width() {
		return WIDTH;
	}

	public static int height() {
		return HEIGHT;
	}

	public static void main(String[] args) {
		new Main().start();
	}

}
