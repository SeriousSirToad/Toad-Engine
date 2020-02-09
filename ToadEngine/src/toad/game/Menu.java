package toad.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import toad.ui.GameButton;

public class Menu {

	private BufferedImage menuImage;
	boolean welcomeShown = false;
	Font font = new Font("Comic sans ms", Font.BOLD, 5);
	GameButton start = new GameButton(91, 35, 70, 13) {
		public void onClick() {
			GameState.running = true;
			if(!welcomeShown) {
				GameState.welcome.activate();
				welcomeShown = true;
			}
		}
	};
	GameButton close = new GameButton(91, 65, 70, 13) {
		public void onClick() {
			System.exit(0);
		}
	};

	public Menu() {
		try {
			menuImage = ImageIO.read(getClass().getResource("/images/titleScreen.png"));
		} catch (IOException e) {
			System.out.println("bruh moment has occured");
		}
		start.setColor(Color.GREEN);
		close.setColor(Color.red);
		close.bordered = false;
		start.bordered = false;
	}

	public void tick() {
		start.tick();
		close.tick();
	}

	public void render() {
		Graphics g = Main.g;
		g.drawImage(menuImage, 0, 0, menuImage.getWidth(), menuImage.getHeight(), null);
		start.render(g);
		close.render(g);
	}

}
