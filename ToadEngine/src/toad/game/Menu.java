package toad.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import toad.ui.GameButton;

public class Menu {

	private BufferedImage menuImage;
	Font font = new Font("Comic sans ms", Font.BOLD, 5);
	GameButton start = new GameButton(91, 35, 70, 13) {
		public void onClick() {
			GameState.running = true;
		}
	};

	public Menu() {
		try {
			menuImage = ImageIO.read(new File("res/titleScreen.png"));
		} catch (IOException e) {
			System.out.println("bruh moment has occured");
		}
		start.setColor(Color.GREEN);
	}

	public void tick() {
		start.tick();
	}

	public void render() {
		Graphics g = Main.g;
		g.drawImage(menuImage, 0, 0, menuImage.getWidth(), menuImage.getHeight(), null);
		start.render();
	}

}
