package toad.game.entities.npcs;

import toad.game.entities.Mob;
import toad.game.level.Level;
import toad.gfx.Assets;

public class Cretin extends Mob {

	public Cretin(Level level, int x, int y) {
		super(level, x, y , Assets.cretin, Assets.cr_hz, Assets.cr_u, Assets.cr_dn);
	}

	@Override
	public void update() {
		
	}

}
