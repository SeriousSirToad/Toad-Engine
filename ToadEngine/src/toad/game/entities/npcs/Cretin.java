package toad.game.entities.npcs;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import toad.game.GameState;
import toad.game.entities.Mob;
import toad.game.level.Door;
import toad.game.level.Level;
import toad.gfx.Assets;

import javax.sound.sampled.*;

public class Cretin extends Mob {

	Clip audio;

	public Cretin(Level level, int x, int y) {
		super(level, x, y, Assets.cretin, Assets.cr_hz, Assets.cr_u, Assets.cr_dn);

		try {
			AudioInputStream audioin = AudioSystem.getAudioInputStream(getClass().getResource("/audio/silly_wabble.wav"));
			audio = AudioSystem.getClip();
			audio.open(audioin);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		updateAudio();
	}

	private void updateAudio() {
		double distance = Math.sqrt(Math.pow(GameState.player.x - this.x, 2) + Math.pow(GameState.player.y - this.y, 2));
		float volume = (float) (1.0 - (distance / 100));

		if (volume < 0)
			volume = 0;
		else if (volume > 1)
			volume = 1.0f;

		float pan = (this.x - GameState.player.x) / 100f;
		if (pan < -1)
			pan = -1;
		else if (pan > 1)
			pan = 1;

		FloatControl gainControl = (FloatControl) audio.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(20f * (float) Math.log10(volume));

		FloatControl panControl = (FloatControl) audio.getControl(FloatControl.Type.PAN);
		panControl.setValue(pan);
	}

	@Override
	public void init() {
		super.init();
		audio.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void shit() {
		
	}
}
