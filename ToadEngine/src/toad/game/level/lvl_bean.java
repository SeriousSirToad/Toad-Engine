package toad.game.level;

import toad.game.entities.Player;
import toad.game.entities.buildings.Apartment_generic;
import toad.game.entities.buildings.Shop;
import toad.game.entities.flora.Tree;
import toad.game.entities.npcs.NPC_generic;
import toad.gfx.Assets;

public class lvl_bean extends Level {

	public lvl_bean() {
		super("/images/level/lvl_bean.png");
	}

	public void init() {
		player = new Player(this, 295, 152);
		new NPC_generic(this, 126, 61, Assets.npc1, 0, new String[] {"Just chilling."}).setName("Chill guy");
		new Shop(this, 51, 0);
		new Shop(this, 167, 0);
		new Apartment_generic(this, 116, 155);
		new Tree(this, 338, 180, 0);
		new Tree(this, 338, 256, 0);
	}

}
