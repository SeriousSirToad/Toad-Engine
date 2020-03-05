package toad.dev;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

import toad.game.GameState;
import toad.game.Main;
import toad.gfx.Assets;
import toad.io.InputHandler;
import toad.ui.GameButton;

public class DevOptions extends Canvas {

	private static final long serialVersionUID = 2743521187358433780L;

	public JFrame frame = new JFrame();
	Dimension dim = new Dimension(640, 480);
	public InputHandler input = new InputHandler(this);
	
	ArrayList<GameButton> buttons = new ArrayList<>();

	public DevOptions() {

		frame.setTitle("DevOptions");
		frame.setSize(dim);
		frame.setPreferredSize(dim);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setUndecorated(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

		WindowListener exitListener = new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				Main.devOptions = false;
				if (previewActive)
					activePreview.close();
			}
		};
		frame.addWindowListener(exitListener);

	}

	boolean previewActive = false;
	AnimationPreview activePreview;
	GameButton Animpreview = new GameButton(0, 0, 16, 8, input, buttons) {
		public void onClick() {
			if (!previewActive) {
				activePreview = new AnimationPreview(Assets.pl_dr);
				previewActive = true;
			} else {
				activePreview.close();
				activePreview = null;
				previewActive = false;
			}
		}
	};
	GameButton previewup = new GameButton(0, 8, 16, 8, input, buttons) {
		public void onClick() {
			if (!previewActive) {
				activePreview = new AnimationPreview(Assets.pl_ur);
				previewActive = true;
			} else {
				activePreview.close();
				activePreview = null;
				previewActive = false;
			}
		}
	};
	GameButton previewHZ = new GameButton(0, 16, 16, 8, input, buttons) {
		public void onClick() {
			if (!previewActive) {
				activePreview = new AnimationPreview(Assets.pl_hz);
				previewActive = true;
			} else {
				activePreview.close();
				activePreview = null;
				previewActive = false;
			}
		}
	};
	GameButton toggleShader = new GameButton(16, 0, 16, 8, input, buttons){
		public void onClick() {
			GameState.currentLevel.shader.toggleShader();
		}
	};

	BufferStrategy bs;
	public static Graphics2D g;

	public void render() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		g.clearRect(0, 0, getWidth(), getHeight());
		g.scale(GameState.renderScale, GameState.renderScale);
		
		for(GameButton b : buttons) {
			b.render(g);
			b.tick();
		}
		
		g.drawString("" + Main.main.frames, 100, 100);

		if (previewActive)
			activePreview.render();

		bs.show();
		g.dispose();

	}

}
