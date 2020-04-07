package toad.game.level;

import java.awt.Rectangle;

public class lvl_test extends Level {


	public lvl_test() {
		super("/images/level/lvl_test.png");
	}

	public void init() {
		colliders.add(new Rectangle(223, 0, 32, 248));
	}

}
