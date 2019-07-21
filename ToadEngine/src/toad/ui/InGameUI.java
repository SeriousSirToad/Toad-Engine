package toad.ui;

import java.awt.Graphics;
import java.util.ArrayList;

public class InGameUI {

	public static ArrayList<GameWindow> windows = new ArrayList<>();
	
	public static void render(Graphics g) {
		for(GameWindow w : windows) {
			w.update();
		}
	}

}
