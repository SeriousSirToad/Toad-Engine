package toad.game.level;

import toad.game.entities.Player;
import toad.game.entities.buildings.Shop;

public class lvl_bean extends Level {

	public lvl_bean() {
		super("src/res/level/lvl_bean.png");
	}

	public void init() {
		player = new Player(this, 23, 42);
		new Shop(this, 51, 0);
	}

}
