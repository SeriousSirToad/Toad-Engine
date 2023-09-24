package toad.game.entities.npcs;

import toad.game.GameState;
import toad.game.entities.Mob;
import toad.game.level.Door;
import toad.game.level.Level;
import toad.gfx.Assets;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Cretin extends Mob {

	boolean seriousmode;
	int tooblar = 0;

	public Cretin(Level level, int x, int y) {
		super(level, x, y, Assets.cretin, Assets.cr_hz, Assets.cr_u, Assets.cr_dn);
		sound = Assets.cretinNoise;
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
			for (Door d : level.doors) {
				if (x - 5 <= d.rect.x && x + 5 >= d.rect.x && collider.y >= d.rect.y) {
					ya--;
					System.out.println("Penis broken");
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
		if (this.level == GameState.player.getLevel() && sound != null) {
			if (!sound.isPlaying()) {
				sound.play();
			}

			double distance = Math.sqrt(Math.pow(GameState.player.x - x, 2) + Math.pow(GameState.player.y - y, 2));
			if (distance < 0) {
				distance = 0;
			}

			float volume = (float) (0.5 - (distance / 200));

			sound.setSourcePos(xf, yf, volume);
		} else {
			if (sound != null && sound.isPlaying()) {
				sound.stop();
			}
		}
	}

	@Override
	public void init() {
		super.init();
	}

	public void shit() {

	}
}
