package toad.gfx;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class Audio{

    private Clip clip;
    public Audio(String audioPath) {
        URL sillyWabbleWav = Objects.requireNonNull(Assets.class.getResource(audioPath));

        try {
        	AudioInputStream audioin = AudioSystem.getAudioInputStream(getClass().getResource(audioPath));
            clip = AudioSystem.getClip();
            clip.open(audioin);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public Clip getClip() { return clip; }
}
