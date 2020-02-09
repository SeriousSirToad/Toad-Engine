package toad.dev;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import toad.game.GameState;
import toad.gfx.Animation;

public class AnimationPreview extends Canvas{

	private static final long serialVersionUID = 1L;
	
	public JFrame frame = new JFrame();
	Dimension dim = new Dimension(16 * GameState.renderScale, 32 * GameState.renderScale);
	Animation anim;
	
	public AnimationPreview(Animation anim) {
		this.anim = anim;
		
		frame.setSize(dim);
		frame.setPreferredSize(dim);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	Graphics2D g;
	BufferStrategy bs;
	public void render() {
		
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		g = (Graphics2D) bs.getDrawGraphics();
		
		g.clearRect(0, 0, getWidth(), getHeight());
		g.scale(GameState.renderScale, GameState.renderScale);
		
		g.drawImage(anim.animate(), 0, 0, null);
		
		bs.show();
		g.dispose();
		
	}
	
	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}

}
