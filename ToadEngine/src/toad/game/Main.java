package toad.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import toad.io.InputHandler;
import toad.ui.InGameUI;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = -5198863677834462653L;

	public int tickCount;

	public static Main main;

	public static int WIDTH = 800;
	public static int HEIGHT = 600;
	private static final String NAME = "TrainEngine";

	public boolean running = false;
	public JFrame frame = new JFrame();
	static Dimension gameDimension;
	public static InputHandler input;

	private static boolean devOptions = true;

	public static Menu menu;

	public Main() {

		main = this;

		gameDimension = new Dimension((int) (WIDTH), (int) (HEIGHT));
		frame.setTitle(NAME);
		frame.setSize(gameDimension);
		frame.setPreferredSize(gameDimension);
		frame.setMinimumSize(gameDimension);
		frame.setMaximumSize(gameDimension);
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setUndecorated(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.pack();

		System.out.println(frame.getInsets().top + ", " + frame.getInsets().bottom);

		input = new InputHandler(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		System.out.println(WIDTH + ", " + HEIGHT);
		// SwingUtilities.invokeLater(this);

		// frame.setLayout(null);
		// con = frame.getContentPane();

		menu = new Menu();

	}

	String playerCoords;

	float frames = 0;

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60;
		int ticks = 0;
		float frames = 0;
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

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				if (firstFPS) {
					InGameUI.replaceRenderOrder("FPS: " + this.frames, "FPS: " + frames);
				}
				System.out.println(ticks + " ticks, " + frames + " frames");
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

		} else {
			menu.tick();
		}

		if (devOptions) {
			playerCoords = "x " + GameState.player.x + ", y " + GameState.player.y;
			if (showCoords && !oldPlayerCoords.equals(null)) {
				InGameUI.replaceRenderOrder(oldPlayerCoords, playerCoords);
			}
			if (input.shift.isPressed() && input.NUM_1.pressedAndReleased()) {
				showCoords = !showCoords;
				if (showCoords) {
					InGameUI.addToRendOrder(playerCoords);
				} else {
					InGameUI.removeFromRendOrder(playerCoords);
				}
			}
			oldPlayerCoords = playerCoords;
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

		g.clearRect(0, 0, width(), width());
		g.scale(GameState.renderScale, GameState.renderScale);
		g.setFont(InGameUI.standardFont);
		GameState.camera.render();
		InGameUI.render(0, g);
		InGameUI.render(1, g);

		bs.show();
		g.dispose();

	}

	public void start() {

		running = true;
		new Thread(this).start();

	}

	public static int width() {
		return (int) (WIDTH);
	}

	public static int height() {
		return (int) (HEIGHT);
	}

	public static void main(String[] args) {
		new Main().start();
	}

}
