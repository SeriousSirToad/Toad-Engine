package toad.ui;

import java.awt.Graphics;

import toad.game.GameState;
import toad.game.Main;

public class UIEngine {

	public void render() {
		Graphics g = Main.g;
		
		if(GameState.running) {
			InGameUI.render(g);
		}
		
	}

}
