package toad.game.entities.npcs;

import java.util.Random;

import toad.game.entities.Mob;
import toad.game.level.Door;
import toad.game.level.Level;
import toad.gfx.Assets;

public class Cretin extends Mob {

	public Cretin(Level level, int x, int y) {
		super(level, x, y, Assets.cretin, Assets.cr_hz, Assets.cr_u, Assets.cr_dn);
	}

	int cretinclock = 0;
	int xa = 0, ya = 0;
	boolean isMoving = false;
	int clockspeed = 30;

	@Override
	public void update() {
		Random r = new Random();
		cretinclock++;
		penis: if (cretinclock == clockspeed) {
			xa = 0;
			ya = 0;
			cretinclock = 0;
			int dir1 = r.nextInt(2);
			int dir2 = r.nextInt(2);
			isMoving = r.nextBoolean();
			for(Door d : level.doors) {
				if(x - 5 <= d.rect.x && x + 5 >= d.rect.x && collider.y >= d.rect.y) {
					ya--;
					break penis;
				}
			}
			if (isMoving) {
				if (dir1 == 0)
					xa++;
				if (dir1 == 1)
					xa--;
				if (dir2 == 0)
					ya++;
				if (dir2 == 1)
					ya--;
			}
			clockspeed = r.nextInt(60) + 1;
		}
		move(xa, ya);
	}

	public void shit() {
		
	}
}
